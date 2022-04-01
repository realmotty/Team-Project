package edu.jsu.mcis.cs310.tas_sp22;

import org.junit.*;
import static org.junit.Assert.*;

public class Feature4 {
    
    private TASDatabase db;
    
    @Before
    public void setup() {
        
        db = new TASDatabase();
        
    }
    
    @Test
    public void testAdjustPunchesShift1Weekday() {
		
        /* Get Shift Ruleset and Punch Data */
        
        Shift s1 = db.getShift(1);

        Punch p1 = db.getPunch(3634);
        Punch p2 = db.getPunch(3687);
        Punch p3 = db.getPunch(3688);
        Punch p4 = db.getPunch(3716);
		
        /* Adjust Punches According to Shift Rulesets */
        
        p1.adjust(s1);
        p2.adjust(s1);
        p3.adjust(s1);
        p4.adjust(s1);
		
        /* Compare Adjusted Timestamps to Expected Values */
        
        assertEquals("#28DC3FB8 CLOCK IN: FRI 09/07/2018 06:50:35", p1.printOriginal());
        assertEquals("#28DC3FB8 CLOCK IN: FRI 09/07/2018 07:00:00 (Shift Start)", p1.printAdjusted());
        
        assertEquals("#28DC3FB8 CLOCK OUT: FRI 09/07/2018 12:03:54", p2.printOriginal());
        assertEquals("#28DC3FB8 CLOCK OUT: FRI 09/07/2018 12:00:00 (Lunch Start)", p2.printAdjusted());
        
        assertEquals("#28DC3FB8 CLOCK IN: FRI 09/07/2018 12:23:41", p3.printOriginal());
        assertEquals("#28DC3FB8 CLOCK IN: FRI 09/07/2018 12:30:00 (Lunch Stop)", p3.printAdjusted());

        assertEquals("#28DC3FB8 CLOCK OUT: FRI 09/07/2018 15:34:13", p4.printOriginal());
        assertEquals("#28DC3FB8 CLOCK OUT: FRI 09/07/2018 15:30:00 (Shift Stop)", p4.printAdjusted());
        
    }

    @Test
    public void testAdjustPunchesShift1Weekend() {
		
        /* Get Shift Ruleset and Punch Data */
        
        Shift s1 = db.getShift(1);

        Punch p1 = db.getPunch(1087);
        Punch p2 = db.getPunch(1162);
		
        /* Adjust Punches According to Shift Rulesets */
        
        p1.adjust(s1);
        p2.adjust(s1);
		
        /* Compare Adjusted Timestamps to Expected Values */

        assertEquals("#F1EE0555 CLOCK IN: SAT 08/11/2018 05:54:58", p1.printOriginal());
        assertEquals("#F1EE0555 CLOCK IN: SAT 08/11/2018 06:00:00 (Interval Round)", p1.printAdjusted());
        
        assertEquals("#F1EE0555 CLOCK OUT: SAT 08/11/2018 12:04:02", p2.printOriginal());
        assertEquals("#F1EE0555 CLOCK OUT: SAT 08/11/2018 12:00:00 (Interval Round)", p2.printAdjusted());
        
    }
    
    @Test
    public void testAdjustPunchesShift2Weekday() {
		
        /* Get Shift Ruleset and Punch Data */
        
        Shift s2 = db.getShift(2);

        Punch p1 = db.getPunch(4943);
        Punch p2 = db.getPunch(5004);
		
        /* Adjust Punches According to Shift Rulesets */
        
        p1.adjust(s2);
        p2.adjust(s2);
        
        /* Compare Adjusted Timestamps to Expected Values */

        assertEquals("#08D01475 CLOCK IN: TUE 09/18/2018 11:59:33", p1.printOriginal());
        assertEquals("#08D01475 CLOCK IN: TUE 09/18/2018 12:00:00 (Shift Start)", p1.printAdjusted());
        
        assertEquals("#08D01475 CLOCK OUT: TUE 09/18/2018 21:30:27", p2.printOriginal());
        assertEquals("#08D01475 CLOCK OUT: TUE 09/18/2018 21:30:00 (None)", p2.printAdjusted());
        
    }
    
    @Test
    public void testAdjustPunchesShift2Weekend() {
		
        /* Get Shift Ruleset and Punch Data */
        
        Shift s2 = db.getShift(2);

        Punch p1 = db.getPunch(5463);
        Punch p2 = db.getPunch(5541);
		
        /* Adjust Punches According to Shift Rulesets */
        
        p1.adjust(s2);
        p2.adjust(s2);
		
        /* Compare Adjusted Timestamps to Expected Values */

        assertEquals("#08D01475 CLOCK IN: SAT 09/22/2018 05:49:00", p1.printOriginal());
        assertEquals("#08D01475 CLOCK IN: SAT 09/22/2018 05:45:00 (Interval Round)", p1.printAdjusted());
        
        assertEquals("#08D01475 CLOCK OUT: SAT 09/22/2018 12:04:15", p2.printOriginal());
        assertEquals("#08D01475 CLOCK OUT: SAT 09/22/2018 12:00:00 (Interval Round)", p2.printAdjusted());
        
    }
    
    @Test
    public void testAdjustPunchesShift1SpecialCases() {
		
        /* Get Shift Ruleset and Punch Data */
        
        Shift s1 = db.getShift(1);

        Punch p1 = db.getPunch(151);  // Interval Adjustment Before Shift (In)
        Punch p2 = db.getPunch(2439); // Grace Period (In)
        Punch p3 = db.getPunch(2693); // Shift Dock (In)
        Punch p4 = db.getPunch(3953); // Interval Round During Shift (Out)
        Punch p5 = db.getPunch(2079); // Grace Period (Out)
        Punch p6 = db.getPunch(1358); // Shift Dock (Out)
        Punch p7 = db.getPunch(4119); // Interval Adjustment After Shift (Out)
        
        /* Adjust Punches According to Shift Ruleset */
        
        p1.adjust(s1);
        p2.adjust(s1);
        p3.adjust(s1);
        p4.adjust(s1);
        p5.adjust(s1);
        p6.adjust(s1);
        p7.adjust(s1);
        
        /* Compare Adjusted Timestamps to Expected Values */

        assertEquals("#BE51FA92 CLOCK IN: WED 08/01/2018 06:48:20", p1.printOriginal());
        assertEquals("#BE51FA92 CLOCK IN: WED 08/01/2018 07:00:00 (Shift Start)", p1.printAdjusted());
        
        assertEquals("#3DA8B226 CLOCK IN: FRI 08/24/2018 07:02:23", p2.printOriginal());
        assertEquals("#3DA8B226 CLOCK IN: FRI 08/24/2018 07:00:00 (Shift Start)", p2.printAdjusted());

        assertEquals("#8E5F0240 CLOCK IN: MON 08/27/2018 07:08:57", p3.printOriginal());
        assertEquals("#8E5F0240 CLOCK IN: MON 08/27/2018 07:15:00 (Shift Dock)", p3.printAdjusted());
        
        assertEquals("#D2C39273 CLOCK OUT: MON 09/10/2018 15:07:52", p4.printOriginal());
        assertEquals("#D2C39273 CLOCK OUT: MON 09/10/2018 15:15:00 (Interval Round)", p4.printAdjusted());

        assertEquals("#408B195F CLOCK OUT: TUE 08/21/2018 15:28:13", p5.printOriginal());
        assertEquals("#408B195F CLOCK OUT: TUE 08/21/2018 15:30:00 (Shift Stop)", p5.printAdjusted());

        assertEquals("#1B2052DE CLOCK OUT: TUE 08/14/2018 15:15:00", p6.printOriginal());
        assertEquals("#1B2052DE CLOCK OUT: TUE 08/14/2018 15:15:00 (Shift Dock)", p6.printAdjusted());

        assertEquals("#ADD650A8 CLOCK OUT: TUE 09/11/2018 15:37:12", p7.printOriginal());
        assertEquals("#ADD650A8 CLOCK OUT: TUE 09/11/2018 15:30:00 (Shift Stop)", p7.printAdjusted());
        
    }
    
}