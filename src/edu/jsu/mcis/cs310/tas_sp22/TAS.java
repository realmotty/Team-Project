/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Month;

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
        Punch startPunch = null;
        Punch endPunch = null;
        boolean isPair = false;
        int dailyMinutes = 0;

        // loop through all daily punches
        for (Punch punch : dailypunchlist) {

            // see what kind of punch
            // if clock in punch
            // todo replace with switch statement
            if (punch.getPunchtype() == PunchType.CLOCK_IN) {

                // current start punchh = this punch
                startPunch = punch;
            }
            // if clock out punch
            else if (punch.getPunchtype() == PunchType.CLOCK_OUT) {

                // cuurrent end punchj = this punch
                endPunch = punch;
                if (startPunch != null || endPunch != null) {
                    isPair = true;
                }
            }
            // if time out punch
            else if (punch.getPunchtype() == PunchType.TIME_OUT) {
                // renull the values
                startPunch = null;
                endPunch = null;
            }
            // todo end switch conversion

            if (isPair) {
                dailyMinutes += (int) (startPunch.punchTime.until(endPunch.punchTime, ChronoUnit.MINUTES));
                /*
                 * dailyMinutes = (dailyMinutes / 1000) * 60;
                 */
                startPunch = null;
                endPunch = null;

                isPair = false; // make pair flag false again

            }

            // if clock in and clock ouut not null then calculate minutes add to minute
            // counter and null out the values of clock in and clock out
        }
        return dailyMinutes;
    }

    public static void main(String[] args) {
        TASDatabase db = new TASDatabase("tasuser", "warroomc", "localhost");

        System.out.println("Test get daily punch list");
        db.getDailyPunchList(db.getBadge("67637925"), LocalDate.of(2018, Month.SEPTEMBER, 17));
    }

}
