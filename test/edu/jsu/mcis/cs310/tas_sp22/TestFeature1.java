package edu.jsu.mcis.cs310.tas_sp22;

import org.junit.*;
import static org.junit.Assert.*;

public class TestFeature1 {
        private TASDatabase db;

        @Before
        public void setup() {

                db = new TASDatabase("tas_user", "warroomc", "localhost");

        }

        @Test
        public void testGetBadge() {
                /* Retrieve Badges from Database */
                Badge b1 = db.getBadge("12565C60");
                Badge b2 = db.getBadge("08D01475");
                Badge b3 = db.getBadge("D2CC71D4");
                /* Compare to Expected Values */

                assertEquals("#12565C60 (Chapman, Joshua E)", b1.toString());
                assertEquals("#08D01475 (Littell, Amie D)", b2.toString());
                assertEquals("#D2CC71D4 (Lawson, Matthew J)", b3.toString());

        }

        @Test
        public void testGetPunch() {
                /* Retrieve Punches from Database */
                Punch p1 = db.getPunch(3433);
                Punch p2 = db.getPunch(3325);
                Punch p3 = db.getPunch(1963);

                Punch p4 = db.getPunch(5702);
                Punch p5 = db.getPunch(4976);
                Punch p6 = db.getPunch(2193);

                Punch p7 = db.getPunch(954);
                Punch p8 = db.getPunch(258);
                Punch p9 = db.getPunch(717);
                /* Compare to Expected Values */
                assertEquals("#D2C39273 CLOCK IN: WED 09/05/2018 07:00:07",
                                p1.printOriginal());
                assertEquals("#DFD9BB5C CLOCK IN: TUE 09/04/2018 08:00:00",
                                p2.printOriginal());
                assertEquals("#99F0C0FA CLOCK IN: SAT 08/18/2018 06:00:00",
                                p3.printOriginal());

                assertEquals("#0FFA272B CLOCK OUT: MON 09/24/2018 17:30:04",
                                p4.printOriginal());
                assertEquals("#FCE87D9F CLOCK OUT: TUE 09/18/2018 17:34:00",
                                p5.printOriginal());
                assertEquals("#FCE87D9F CLOCK OUT: MON 08/20/2018 17:30:00",
                                p6.printOriginal());

                assertEquals("#618072EA TIME OUT: FRI 08/10/2018 00:12:35",
                                p7.printOriginal());
                assertEquals("#0886BF12 TIME OUT: THU 08/02/2018 06:06:38",
                                p8.printOriginal());
                assertEquals("#67637925 TIME OUT: TUE 08/07/2018 23:12:34",
                                p9.printOriginal());

        }

        @Test
        public void testGetShiftByID() {
                /* Retrieve Shift Rulesets from Database */
                Shift s1 = db.getShift(1);
                Shift s2 = db.getShift(2);
                Shift s3 = db.getShift(3);
                /* Compare to Expected Values */
                assertEquals("Shift 1: 07:00 - 15:30 (510 minutes); Lunch: 12:00 - 12:30 (30 minutes)", s1.toString());

                assertEquals("Shift 2: 12:00 - 20:30 (510 minutes); Lunch: 16:30 - 17:00 (30 minutes)", s2.toString());
                assertEquals("Shift 1 Early Lunch: 07:00 - 15:30 (510 minutes); Lunch: 11:30 - 12:00 (30 minutes)",
                                s3.toString());
        }

        @Test
        public void testGetEmployeeByID() {
                /* Retrieve Employees from Database */
                Employee e1 = db.getEmployee(10);
                Employee e2 = db.getEmployee(6);
                Employee e3 = db.getEmployee(8);
                Employee e4 = db.getEmployee(22);
                Employee e5 = db.getEmployee(19);
                Employee e6 = db.getEmployee(76);
                /* Compare to Expected Values */
                assertEquals("#12565C60 (Chapman, Joshua E): employeetypeid: 0, departmentid: 5, shiftid: 1, active: 2015-09-11, inactive: none",
                                e1.toString());
                assertEquals("#0B8C3085 (King, Harry L): employeetypeid: 1, departmentid: 1, shiftid: 1, active: 2016-10-30, inactive: none",
                                e2.toString());
                assertEquals("#0FFA272B (Corwin, John L): employeetypeid: 1, departmentid: 7, shiftid: 1, active: 2015-10-11, inactive: none",
                                e3.toString());
                assertEquals("#2A972897 (White, Margaret M): employeetypeid: 0, departmentid: 4, shiftid: 1, active: 2015-09-11, inactive: none",
                                e4.toString());
                assertEquals("#29C3C7D4 (Gomez, Rose M): employeetypeid: 0, departmentid: 1, shiftid: 1, active: 2015-11-02, inactive: none",
                                e5.toString());
                assertEquals("#9D527CFB (Rodriquez, Jarvis B): employeetypeid: 0, departmentid: 8, shiftid: 1, active: 2015-09-22, inactive: none",
                                e6.toString());
        }

        @Test
        public void testGetShiftByBadge() {

                /* Create Badge Objects */

                Badge b1 = db.getBadge("B6902696");
                Badge b2 = db.getBadge("76E920D9");
                Badge b3 = db.getBadge("4382D92D");

                /* Retrieve Shift Rulesets from Database */
                Shift s1 = db.getShift(b1);
                Shift s2 = db.getShift(b2);
                Shift s3 = db.getShift(b3);
                /* Compare to Expected Values */
                assertEquals("Shift 1: 07:00 - 15:30 (510 minutes); Lunch: 12:00 - 12:30 (30 minutes)", s1.toString());
                assertEquals("Shift 2: 12:00 - 20:30 (510 minutes); Lunch: 16:30 - 17:00 (30 minutes)", s2.toString());
                assertEquals("Shift 1 Early Lunch: 07:00 - 15:30 (510 minutes); Lunch:11:30 - 12:00 (30 minutes)",
                                s3.toString());
        }

    @Test
    public void testGetEmployeeByBadge() {
        
        /* Create Badge Objects */
        
        Badge b1 = db.getBadge("1B2052DE");
        Badge b2 = db.getBadge("0886BF12");
        Badge b3 = db.getBadge("29C03912");
        Badge b4 = db.getBadge("2CD387C2");
        Badge b5 = db.getBadge("28DC3FB8");
        Badge b6 = db.getBadge("124A2DED");
        
        /* Retrieve Shift Rulesets from Database */
        Employee e1 = db.getEmployee(b1);
        Employee e2 = db.getEmployee(b2);
        Employee e3 = db.getEmployee(b3);
        Employee e4 = db.getEmployee(b4);
        Employee e5 = db.getEmployee(b5);
        Employee e6 = db.getEmployee(b6);
        /* Compare to Expected Values */
        assertEquals("#1B2052DE (Sanchez, Katherine H): employeetypeid: 1, departmentid: 7, shiftid: 1, active: 2015-09-22, inactive: none", e1.toString());
        assertEquals("#0886BF12 (Gibson, Theresa E): employeetypeid: 0, departmentid: 1, shiftid: 1, active: 2015-08-14, inactive: none", e2.toString());
        assertEquals("#29C03912 (McKain, Ethel H): employeetypeid: 1, departmentid:4, shiftid: 1, active: 2017-02-11, inactive: none", e3.toString());
        assertEquals("#2CD387C2 (Horner, Nicholas M): employeetypeid: 1, departmentid: 5, shiftid: 1, active: 2015-07-28, inactive: none", e4.toString());
        assertEquals("#28DC3FB8 (Woods, Matthew S): employeetypeid: 1, departmentid: 4, shiftid: 1, active: 2015-07-28, inactive: none", e5.toString());
        assertEquals("#124A2DED (Ford, Nicholas R): employeetypeid: 0, departmentid: 5, shiftid: 1, active: 2015-09-01, inactive: none", e6.toString());
        
    }
}