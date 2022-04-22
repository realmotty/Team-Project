/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_sp22;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Month;

import java.util.Map;

import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;



/**
 *
 * @author Jonathan Carter
 */
public class TAS {

    public static String getPunchListAsJSON(ArrayList<Punch> dailypunchlist){
        
         Map Punchmap =  String.valueOf();
         JSONArray keyArray = new JSONArray();
         JSONArray HeaderArray = new JSONArray();
         JSONArray dataArray = new JSONArray();

            int counter = 0;
            while (iterator.hasNext()) {
                String[] temp = iterator.next();
                // first row is added to the columnHeaderArray
                if (counter == 0) {
                    for (String element : temp) {
                        columnHeaderArray.add(element);
                    }
                }
                    for (int i = 0; i < HeaderArray.size(); i++) {
            
            
         }
     
            
            }
    }
    
            
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
            if (punch.getPunchType() == PunchType.CLOCK_IN) {

                // current start punchh = this punch
                startPunch = punch;
            }
            // if clock out punch
            else if (punch.getPunchType() == PunchType.CLOCK_OUT) {

                // cuurrent end punchj = this punch
                endPunch = punch;
                if (startPunch != null || endPunch != null) {
                    isPair = true;
                }
            }
            // if time out punch
            else if (punch.getPunchType() == PunchType.TIME_OUT) {
                // renull the values
                startPunch = null;
                endPunch = null;
            }
            // todo end switch conversion

            if (isPair) {
                dailyMinutes += (int) (startPunch.getPunchTime().until(endPunch.getPunchTime(), ChronoUnit.MINUTES));
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
