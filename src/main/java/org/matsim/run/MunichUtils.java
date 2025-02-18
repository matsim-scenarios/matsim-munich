package org.matsim.run;

import org.matsim.core.config.Config;
import org.matsim.core.config.groups.ScoringConfigGroup;

class MunichUtils{
	static void createActivityTypes( Config config ){
		// activities (alphabetic).  Presumably revealed activity durations from MidMUC.  This may explain why they extend to different maximum number of hours.  However, a
		// pickup activity of 22hours does not make sense, so it is not entirely clear.  kai, mar'19
		// v0 presumably has activity types from MidMUC.  v2 has modified versions of them.  kai/ihab, sep'19
		// ---
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "busi0.5H" );
			params.setTypicalDuration( 0.5 * 3600. );
			config.scoring().addActivityParams( params );
		}
		for ( long ii = 1 ; ii <= 13; ii+=1 ) {
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "busi" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "educ0.5H" );
			params.setTypicalDuration( 0.5 * 3600. );
			config.scoring().addActivityParams( params );
		}
		for ( long ii = 1 ; ii <= 23; ii+=1 ) {
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "educ" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		// I don't know what the following is.  Maybe "visit friends"?  kai, mar'19  Or a mis-spelt "freight"?
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "frie0.5H" );
			params.setTypicalDuration( 0.5 * 3600. );
			config.scoring().addActivityParams( params );
		}
		for ( long ii = 4 ; ii <= 4; ii+=1 ) { // not sure why; other values were not in original config file.  kai, mar'19
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "frie" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "home0.5H" );
			params.setTypicalDuration( 0.5 * 3600. );
			config.scoring().addActivityParams( params );
		}
		for ( long ii = 1 ; ii <= 27; ii+=1 ) {
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "home" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "leis0.5H" );
			params.setTypicalDuration( 0.5 * 3600. );
			config.scoring().addActivityParams( params );
		}
		for ( long ii = 1 ; ii <= 24; ii+=1 ) {
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "leis" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "othe0.5H" );
			params.setTypicalDuration( 0.5 * 3600. );
			config.scoring().addActivityParams( params );
		}
		for ( long ii = 1 ; ii <= 17; ii+=1 ) {
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "othe" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "pick0.5H" );
			params.setTypicalDuration( 0.5 * 3600. );
			config.scoring().addActivityParams( params );
		}
		for ( long ii = 1 ; ii <= 22; ii+=1 ) {
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "pick" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "priv0.5H" );
			params.setTypicalDuration( 0.5 * 3600. );
			config.scoring().addActivityParams( params );
		}
		for ( long ii = 1 ; ii <= 20; ii+=1 ) {
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "priv" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "shop0.5H" );
			params.setTypicalDuration( 0.5 * 3600. );
			config.scoring().addActivityParams( params );
		}
		for ( long ii = 1 ; ii <= 22; ii+=1 ) {
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "shop" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		//		{
		//			final ActivityParams params = new ActivityParams( "spor0.5H" );
		//			params.setTypicalDuration( 0.5 * 3600. );
		//			config.planCalcScore().addActivityParams( params );
		//		}
		for ( long ii = 2 ; ii <= 3; ii+=1 ) {  // only with these two values in original config file.  kai, mar'19
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "spor" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		// Theoretically, "other" (above) means activity types that are known but are aggregated into the new type.  In contrast, "unknown" means that they are not known at
		// all.  But I don't know if it was designed like that.  kai, mar'19
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "unkn0.5H" );
			params.setTypicalDuration( 0.5 * 3600. );
			config.scoring().addActivityParams( params );
		}
		for ( long ii = 1 ; ii <= 30; ii+=1 ) {
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "unkn" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "with0.5H" );
			params.setTypicalDuration( 0.5 * 3600. );
			config.scoring().addActivityParams( params );
		}
		for ( long ii = 1 ; ii <= 20; ii+=1 ) {
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "with" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "work0.5H" );
			params.setTypicalDuration( 0.5 * 3600. );
			config.scoring().addActivityParams( params );
		}
		for ( long ii = 1 ; ii <= 23; ii+=1 ) {
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "work" + ii + ".0H" ) ;
			params.setTypicalDuration( ii * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		// presumably, the following are commuters from outside MUC to inside.  For these, we did not have MiD, so they were derived from Pendlerstatistik (or from BVWP).
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "pvHome" );
			params.setTypicalDuration( 6. * 3600. ); // not sure why this is set to 6hrs; does not make sense.  Maybe should have been 16 and is a typo.  kai, mar'19
			config.scoring().addActivityParams( params );
		}
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "pvWork" );
			params.setTypicalDuration( 9. * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		// freight traffic ("Gueterverkehr").  Derived from BVWP.
		{
			final ScoringConfigGroup.ActivityParams params = new ScoringConfigGroup.ActivityParams( "gvHome" );  // the "home" does not make sense here. kai, mar'19
			params.setTypicalDuration( 6. * 3600. );
			config.scoring().addActivityParams( params );
		}
		// ---
		// none of the above has opening/closing time, so one cannot use this with time mutation.
	}
}
