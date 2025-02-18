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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.matsim.core.config.Config;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.testcases.MatsimTestUtils;

/**
 * @author nagel
 *
 */
public class RunMatsim4MunichWEquilTest{
	
	@RegisterExtension
	public MatsimTestUtils utils = new MatsimTestUtils() ;

	private static Logger log = LogManager.getLogger(RunMatsim4MunichTest.class);

	@Test
	public final void test() {
		try {
//			URL context = org.matsim.examples.ExamplesUtils.getTestScenarioURL( "equil" );
			RunMatsim4Munich matsim = new RunMatsim4Munich( new String [] {"scenarios/equil/config.xml"} ) ;
			Config config = matsim.prepareConfig() ;
			config.controller().setWriteEventsInterval(1);
			config.controller().setLastIteration(1);
			config.controller().setOutputDirectory( utils.getOutputDirectory() );
			config.controller().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
			matsim.run() ;
		} catch ( Exception ee ) {
			log.fatal("there was an exception: \n") ;
			ee.printStackTrace();
			// if one catches an exception, then one needs to explicitly fail the test:
			Assertions.fail();
		}


	}

}
