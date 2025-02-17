/* *********************************************************************** *
 * project: org.matsim.*												   *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */
package org.matsim.run;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.QSimConfigGroup;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.AllowsConfiguration;
import org.matsim.core.controler.Controler;
import org.matsim.core.population.algorithms.PermissibleModesCalculatorImpl;
import org.matsim.core.replanning.PlanStrategy;
import org.matsim.core.replanning.PlanStrategyImpl;
import org.matsim.core.replanning.modules.ReRoute;
import org.matsim.core.replanning.modules.SubtourModeChoice;
import org.matsim.core.replanning.selectors.RandomPlanSelector;
import org.matsim.core.router.TripRouter;
import org.matsim.core.router.TripStructureUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.timing.TimeInterpretation;

import java.util.*;

/**
 * @author nagel
 *
 */
public class RunMatsim4Munich{
	// cf CNEMunich in ikaddoura playground

	private final String[] args;
	private Config config = null ;
	private Scenario scenario = null ;
	private Controler controler = null ;

	public static void main ( String [] args ) {
		new RunMatsim4Munich( args ).run() ;
	}

	public RunMatsim4Munich( String [] args ) {
		this.args = args ;
	}

	public final Config prepareConfig() {
		if ( args!=null && args.length > 0 ) {
			config = ConfigUtils.loadConfig( args[0] ) ;
		} else{
			throw new RuntimeException("need to provide path to config file. aborting ...") ;
		}
//		create different act types
		MunichUtils.createActivityTypes( config );

//		some vehicles are allowed to pass each other
		config.qsim().setLinkDynamics( QSimConfigGroup.LinkDynamics.PassingQ );

		final String[] availableModes = {"car", "pt_COMMUTER_REV_COMMUTER"};
		final String[] chainBasedModes = {"car", "bike"};

		config.subtourModeChoice().setModes(availableModes);
		config.subtourModeChoice().setChainBasedModes(chainBasedModes);
		config.subtourModeChoice().setProbaForRandomSingleTripMode(0.5);

		return config ;
	}

	public final Scenario prepareScenario() {
		if ( config==null ) {
			prepareConfig() ;
		}
		scenario = ScenarioUtils.loadScenario( config ) ;

//		add car, ride, bike as allowed modes on all network links
		for( Link link : scenario.getNetwork().getLinks().values() ){
			link.setAllowedModes( new HashSet<>( Arrays.asList( TransportMode.car, TransportMode.bike, TransportMode.ride ) ) ) ;
		}

//		delete route for all bike legs
		for( Person person : scenario.getPopulation().getPersons().values() ){
			Plan plan = person.getSelectedPlan() ;
			List<Leg> legs = TripStructureUtils.getLegs( plan );
			for( Leg leg : legs ){
				if ( leg.getMode().equals( TransportMode.bike ) ) {
					leg.setRoute( null );
				}
			}
		}

		return scenario ;
	}

	public final void run() {
		if ( controler==null ) {
			prepareControler() ;
		}
		controler.run() ;
	}

	public final AllowsConfiguration prepareControler() {
		if ( scenario==null ) {
			prepareScenario() ;
		}
		controler = new Controler( scenario ) ;

		// use the (congested) car travel time for the teleported ride mode
		// Seems like a nice trick, but does not work so well: All ride trips found in the 0th iteration use the free speed travel time, which is much too fast.  And they
		// remember this forever. kai, mar'19
		controler.addOverridingModule( new AbstractModule() {
			@Override public void install() {
				addTravelTimeBinding( TransportMode.ride ).to( carTravelTime() );
				addTravelDisutilityFactoryBinding( TransportMode.ride ).to( carTravelDisutilityFactoryKey() );
			}
		} );

		controler.addOverridingModule(new AbstractModule() {
			@Override
			public void install() {
				final Provider<TripRouter> tripRouterProvider = binder().getProvider(TripRouter.class );

				// this here "registers" an additional stratetegy under "SubtourModeChoice_COMMUTER_REV_COMMUTER".  We want the commuters have a different pt than the urban population, and this cannot be
				// configured by config alone.  See here: https://github.com/matsim-org/matsim-code-examples/issues/92 .
				addPlanStrategyBinding( "SubtourModeChoice_COMMUTER_REV_COMMUTER" ).toProvider( new Provider<PlanStrategy>() {
					@Inject
                    Scenario sc;
					@Override public PlanStrategy get() {
						final PlanStrategyImpl.Builder builder = new PlanStrategyImpl.Builder(new RandomPlanSelector<>());
						builder.addStrategyModule(new SubtourModeChoice(sc.getConfig().global(), sc.getConfig().subtourModeChoice(), new PermissibleModesCalculatorImpl(config)));
						builder.addStrategyModule(new ReRoute(sc, tripRouterProvider, TimeInterpretation.create(config)) );
						return builder.build();
					}
				} );
			}
		});


		return controler ;
	}

}
