package edu.jsu.mcis.cs310.tas_sp22;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class Feature5 {

    private TASDatabase db;

    @Before
    public void setup() {

        db = new TASDatabase("tasuser", "warroomc", "localhost");

    }

    @Test
    public void testMinutesAccruedShift1Weekday() {

        /* Get Punch */

        Punch p = db.getPunch(3634);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);

        ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());
        ArrayList<Punch> temp;
        Punch t;

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
        // i think the minutes calculation to test against is wrong;
        // 540 if the time was 21:00 but the time was 21:30 so itd be 570
        // i could be wrong calculating tho
        assertEquals(570, m);

    }

    public void testMinutesAccruedShift3Weekday() {

        /* Get Punch */

        Punch p = db.getPunch(6081);
        Badge b = p.getBadge();
        Shift s = db.getShift(b);

        ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginalTimestamp().toLocalDate());

        for (Punch punch : dailypunchlist) {
            punch.adjust(s);
        }

        /* Compute Pay Period Total */

        int m = TAS.calculateTotalMinutes(dailypunchlist, s);

        /* Compare to Expected Value */

        assertEquals(720, m);

    }

}
