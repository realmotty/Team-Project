/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_sp22;

import java.util.ArrayList;

/**
 *
 * @author Jonathan Carter
 */
public class TAS {

    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift) {
        // including lunch = 4 punches
        // not including lunch = 2 punches

        // if minutes work is enough and only two punches add artificial lunch (minus
        // his lunch minutes for the shift)
        Punch startPeriod = null;
        Punch endPeriod = null;
        int dailyMinutes = 0;

        // loop through all daily punches
        for (Punch punch : dailypunchlist) {

            // see what kind of punch
            // if clock in punch
            // todo replace with switch statement
            if (punch.getPunchtype() == PunchType.CLOCK_IN) {

                // current start punchh = this punch
            }
            // if clock out punch
            else if (punch.getPunchtype() == PunchType.CLOCK_OUT) {

                // cuurrent end punchj = this punch
            }
            // if time out punch
            else if (punch.getPunchtype() == PunchType.TIME_OUT) {
                // renull the values
            }
            // todo end switch conversion

            if (startPeriod != null || endPeriod != null) {
                // calucalte minute and add to counter
                // renull the values
            }

            // if clock in and clock ouut not null then calculate minutes add to minute
            // counter and null out the values of clock in and clock out
        }
        return dailyMinutes;
    }

    public static void main(String[] args) {
        TASDatabase db = new TASDatabase("tasuser", "warroomc", "localhost");

        System.out.println("\nTEST -- getBadge");
        Badge b = db.getBadge("12565C60");
        System.out.println(b.toString());

        System.out.println("\nTEST -- getPunch");
        Punch p1 = db.getPunch(3433);
        System.out.println(p1.printOriginal());

        System.out.println("\nTEST -- getShift by id");/* get get Shift by id */
        Shift s1 = db.getShift(1);
        System.out.println(s1.toString());

        System.out.println("\nTEST -- getEmployee by id");
        Employee e1 = db.getEmployee(10);
        System.out.println(e1.toString());
    }

}
