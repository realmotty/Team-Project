package edu.jsu.mcis.cs310.tas_sp22;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class Feature5 {
    
    private TASDatabase db;
    
    @Before
    public void setup() {
        
        db = new TASDatabase();
        
    }
    
    @Test
    public void testMinutesAccruedShift1Weekday() {
		
        /* Get Punch */
        
        Punch p = db.getPunch(3634);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
        
        for (Punch punch : dailypunchlist) {
            punch.adjust(s);
        }
		
        /* Compute Pay Period Total */
        
        int m = TAS.calculateTotalMinutes(dailypunchlist, s);
		
        /* Compare to Expected Value */
        
        assertEquals(480, m);
        
    }
    
    @Test
    public void testMinutesAccruedShift1WeekdayWithTimeout() {
		
        /* Get Punch */
        
        Punch p = db.getPunch(436);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
        
        for (Punch punch : dailypunchlist) {
            punch.adjust(s);
        }
		
        /* Compute Pay Period Total */
        
        int m = TAS.calculateTotalMinutes(dailypunchlist, s);
		
        /* Compare to Expected Value */
        
        assertEquals(0, m);
        
    }
    
    @Test
    public void testMinutesAccruedShift1Weekend() {
		
        /* Get Punch */
        
        Punch p = db.getPunch(1087);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
        
        for (Punch punch : dailypunchlist) {
            punch.adjust(s);
        }
		
        /* Compute Pay Period Total */
        
        int m = TAS.calculateTotalMinutes(dailypunchlist, s);
		
        /* Compare to Expected Value */
        
        assertEquals(360, m);
        
    }

    @Test
    public void testMinutesAccruedShift2Weekday() {
		
        /* Get Punch */
        
        Punch p = db.getPunch(4943);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);
        
        ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
        
        for (Punch punch : dailypunchlist) {
            punch.adjust(s);
        }
        
        /* Compute Pay Period Total */
        
        int m = TAS.calculateTotalMinutes(dailypunchlist, s);
		
        /* Compare to Expected Value */
        
        assertEquals(540, m);
        
    }
    
}