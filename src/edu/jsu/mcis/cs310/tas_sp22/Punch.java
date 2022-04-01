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

        //adjust punch times
        // todo for feature 4

        public void adjust (Shift S){

         if(this.punchTime.toLocalTime().isBefore(S.shiftStart)){ // checking to see if punch in time is before the scheduled shift start
                 //what ever needs done
                 //make the grace localtime\
                 //check if clock in before 
                 //if clock before punish]
                 //else adjust with rules
         }
         //; seeing when the shift starts
        // this.adjustmentType; the type of punch in 
        // this.ajustedPunchTime; the time in which the person clocked in 

        //shift end 9

        //this.start 8;45
        //this.clockout 9;20
        //if shift.clockinTime is over 15 before this.shiftclockintime then adjust with punishment

        //create a loacldatetime for the shift start then copy it

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
