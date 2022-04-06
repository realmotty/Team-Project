package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Punch {

        private int id;

        private int punchTypeid;
        private int terminalId;
        private Badge badge;

        private String adjustmentType;
        private LocalDateTime punchTime;
        private LocalDateTime ajustedPunchTime;

        private PunchType punchType;

        /**
         * Construct instance with not from database
         * 
         * @param terminalId
         * @param badge
         * @param punchTypeId
         */
        Punch(int terminalId, Badge badge, int punchTypeId) {

                this.terminalId = terminalId;
                this.badge = badge;
                this.punchTypeid = punchTypeId;

                /* set punch type enum based of (int)punchTypeId */
                switch (this.punchTypeid) {

                        case 0:
                                this.punchType = PunchType.CLOCK_OUT;
                                break;
                        case 1:
                                this.punchType = PunchType.CLOCK_IN;
                                break;
                        case 2:
                                this.punchType = PunchType.TIME_OUT;
                                break;
                        default:
                                this.punchType = null;
                }

                /* default values */

                this.punchTime = LocalDateTime.now().withNano(0);
                this.adjustmentType = null;
                this.ajustedPunchTime = null;
                this.id = 0;

        }

        /**
         * 
         * @param id
         * @param terminalId
         * @param badgeid
         * @param timestamp
         * @param punchTypeId
         */

        Punch(int id, int terminalId, String badgeid, LocalDateTime timestamp, int punchTypeId) {
                this.id = id;
                this.terminalId = terminalId;
                this.badge = new Badge(badgeid);
                this.punchTime = timestamp;
                /* set punch type enum based of (int)punchTypeId */
                switch (punchTypeId) {
                        case 0:
                                this.punchType = PunchType.CLOCK_OUT;
                                break;
                        case 1:
                                this.punchType = PunchType.CLOCK_IN;
                                break;
                        case 2:
                                this.punchType = PunchType.TIME_OUT;
                                break;
                        default:
                                this.punchType = null;
                }

        }

        // adjust punch times
        // todo for feature 4
        /**
         * Adjust Punch time based on company ruleset for early,late, and missed punches
         * 
         * @param S Shift to use for adjustment
         */
        public void adjust(Shift S) {

                // Check type of punch
                if (this.punchType == PunchType.CLOCK_IN) {

                        // do something

                        // if clock in punch:

                        // Store adjusted timestamp into "adjustedtimestamp" = LocalDateTime

                        // Store a description of which rule triggers the adjustment in a String

                        if (this.punchTime.toLocalTime().isBefore(S.shiftStart)) { // checking to see if punch in time is before Shift start


                                 // grace period for before shift

                                if (this.punchTime.toLocalTime().isBefore(S.shiftStart.minusMinutes(S.gracePeriod))) {

                                }

                                // now shift the shift start time accordingly

                                // the scheduled shift start

                                // make the grace localtime for before the shift

                                {
                                } 
                                
                                // check if clock in before grace period

                                // if clock before punish

                                // else adjust with rules

                        }

                        else if (this.punchTime.toLocalTime().isAfter(S.shiftStart)) { // else check to see if punch is
                                                                                       // after Shift start


                        }

                                
                        } 
                        
                         if (this.punchTime.toLocalTime().isBefore(S.shiftStart)){  // check if in shift start grace period
                        // appropriate action
                        
                        if (this.punchTime.toLocalTime().isBefore(S.lunchStop)) {}  // chekc if in lunch stop grace period
                        // appropriate action

                        
                        // else punch right on time
                }

                else if (this.punchType == PunchType.CLOCK_OUT) {  // check if punch type is clock out punch


                        if (this.punchTime.toLocalTime().isBefore(S.shiftStop)) {
                        } // check if before shift clock out

                        if (this.punchTime.toLocalTime().isBefore(S.lunchStart)) {
                        } // check if in lunch grace period

                        if (this.punchTime.toLocalTime().isBefore(S.shiftStop.minusMinutes(S.gracePeriod))) {

                        } // check if in early clock out grace period

                        else if (this.punchTime.toLocalTime().isAfter(S.shiftStop)) {
                        } // check if after shift clock out

                        if (this.punchTime.toLocalTime().isAfter(S.shiftStop.minusMinutes(S.gracePeriod))) {} // check if in late clock out grace period

                }

                // else this is code ran for the PunchType. timeout

                else {
                        // timeout stuff

                }

        }

        // Getters

        public Badge getBadge() {
                return this.badge;
        }

        public LocalDateTime getOriginalTimestamp() {
                return this.punchTime;
        }

        public int getTerminalid() {
                return this.terminalId;
        }

        public PunchType getPunchtype() {
                return this.punchType;
        }

        public int getPunchtypeID() {
                return punchTypeid;
        }

        @Override
        public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("Punch{id=").append(id);
                sb.append(", punchTypeid=").append(punchTypeid);
                sb.append(", terminalId=").append(terminalId);
                sb.append(", badge=").append(badge);
                sb.append(", adjustmentType=").append(adjustmentType);
                sb.append(", punchTime=").append(punchTime);
                sb.append(", ajustedPunchTime=").append(ajustedPunchTime);
                sb.append('}');
                return sb.toString();
        }

        public String printOriginal() {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");

                StringBuilder sb = new StringBuilder();
                sb.append("#").append(badge.getId()).append(" ");
                sb.append(punchType).append(": ");
                sb.append(punchTime.format(format).toUpperCase());
                String result = sb.toString();
                return result;

        }
}
