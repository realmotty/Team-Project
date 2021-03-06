package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.text.html.HTMLDocument.RunElement;

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
        public Punch(int terminalId, Badge badge, int punchTypeId) {

                this.terminalId = terminalId;
                this.badge = badge;
                this.punchTypeid = punchTypeId;

                this.punchType = PunchType.values()[punchTypeId];

                /* set punch type enum based of (int)punchTypeId */
                /*
                 * switch (this.punchTypeid) {
                 * 
                 * case 0:
                 * this.punchType = PunchType.CLOCK_OUT;
                 * break;
                 * case 1:
                 * this.punchType = PunchType.CLOCK_IN;
                 * break;
                 * case 2:
                 * this.punchType = PunchType.TIME_OUT;
                 * break;
                 * default:
                 * this.punchType = null;
                 * }
                 */

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

        public Punch(int id, int terminalId, String badgeid, LocalDateTime timestamp, int punchTypeId) {
                this.id = id;
                this.terminalId = terminalId;
                this.badge = new Badge(badgeid);
                this.punchTime = timestamp;
                /* set punch type enum based of (int)punchTypeId */

                this.punchType = PunchType.values()[punchTypeId];
                /*
                 * switch (punchTypeId) {
                 * case 0:
                 * this.punchType = PunchType.CLOCK_OUT;
                 * break;
                 * case 1:
                 * this.punchType = PunchType.CLOCK_IN;
                 * break;
                 * case 2:
                 * this.punchType = PunchType.TIME_OUT;
                 * break;
                 * default:
                 * this.punchType = null;
                 * }
                 */

        }

        // adjust punch times
        // todo for feature 4
        /**
         * Adjust Punch time based on company ruleset for early,late, and missed punches
         * 
         * @param S Shift to use for adjustment
         */
        public void adjust(Shift S) {
                if (this.punchType == PunchType.CLOCK_IN) {
                        this.ajustedPunchTime = adjustClockIn(S);

                } else if (this.punchType == PunchType.CLOCK_OUT) {
                        this.ajustedPunchTime = adjustClockOut(S);
                } else {
                        this.ajustedPunchTime = this.punchTime.withNano(0);
                }
        }

        // Getters
        public LocalDateTime getPunchTime() {
                return punchTime;
        }

        public int getId() {
                return id;
        }

        public int getPunchTypeid() {
                return punchTypeid;
        }

        public int getTerminalid() {
                return terminalId;
        }

        public Badge getBadge() {
                return badge;
        }

        public String getAdjustmentType() {
                return adjustmentType;
        }

        public LocalDateTime getOriginalTimestamp() {
                return punchTime;
        }

        public LocalDateTime getAjustedPunchTime() {
                return ajustedPunchTime;
        }

        public PunchType getPunchtype() {
                return punchType;
        }

        // String Output Methods

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

        public String printAdjusted() {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");

                StringBuilder sb = new StringBuilder();
                sb.append("#").append(badge.getId()).append(" ");
                sb.append(punchType).append(": ");
                sb.append(this.ajustedPunchTime.withNano(0).format(format).toUpperCase());
                sb.append(" (").append(this.adjustmentType).append(")");

                String result = sb.toString();
                return result;

        }

        /* Helper Functions */

        // Begin Helper Functions For Adjust()
        private LocalDateTime adjustClockIn(Shift S) {
                LocalDateTime adjustmentResult = null;
                LocalTime adjustmentTimeResult = null;

                if (this.punchTime.toLocalTime().isBefore(S.getShiftStart())) {
                        if (checkInRoundIntervalBeforeShift(S)) {
                                this.adjustmentType = "Shift Start";
                                // adjusted time is set to the start of the shift
                                adjustmentTimeResult = S.getShiftStart();
                        } else {
                                // round to next interval
                                this.adjustmentType = "Interval Round";
                                adjustmentTimeResult = adjustRoundInterval(S);
                        }
                } else if (this.punchTime.toLocalTime().isAfter(S.getShiftStart())) {
                        if (checkInLunchBreak(S)) {
                                this.adjustmentType = "Lunch Stop";
                                adjustmentTimeResult = S.getLunchStop();
                        } else if (checkInGracePeriod(S)) {
                                this.adjustmentType = "Grace Period";
                                // adjusted time is set to the start of the shift
                                adjustmentTimeResult = S.getShiftStart();
                        } else if (checkInDockPeriod(S)) {
                                // dock penalty
                                this.adjustmentType = "Shift Dock";
                                adjustmentTimeResult = S.getShiftStart().plusMinutes(S.getDockPenalty());
                        } else {
                                // round to next interval
                                adjustmentTimeResult = adjustRoundInterval(S);

                        }
                } else {
                        adjustmentTimeResult = this.punchTime.withNano(0).toLocalTime();
                }

                adjustmentResult = LocalDateTime.of(this.punchTime.toLocalDate(), adjustmentTimeResult);
                return adjustmentResult;
        }

        private LocalDateTime adjustClockOut(Shift S) {
                LocalDateTime adjustmentResult = null;
                LocalTime adjustmentTimeResult = null;

                if (this.punchTime.toLocalTime().isBefore(S.getShiftStop())) {
                        if (checkInLunchBreak(S)) {
                                this.adjustmentType = "Lunch Start";
                                adjustmentTimeResult = S.getLunchStart();

                        } else if (checkInGracePeriod(S)) {
                                adjustmentTimeResult = S.getShiftStop();
                                this.adjustmentType = "Shift Stop";

                        } else if (checkInDockPeriod(S)) {
                                this.adjustmentType = "Shift Dock";
                                adjustmentTimeResult = S.getShiftStop().minusMinutes(S.getDockPenalty());
                        } else {
                                // interval round
                                this.adjustmentType = "Interval Round";

                                adjustmentTimeResult = adjustRoundInterval(S);

                        }

                } else if (this.punchTime.toLocalTime().isAfter(S.getShiftStop()))
                        if (checkInRoundIntervalAfterShift(S)) {
                                // set to scheduled stop
                                adjustmentTimeResult = S.getShiftStop();
                                this.adjustmentType = "Shift Stop";
                        } else {
                                adjustmentTimeResult = adjustRoundInterval(S);
                        }

                else {
                        adjustmentTimeResult = this.punchTime.withNano(0).toLocalTime();
                }

                adjustmentResult = LocalDateTime.of(this.punchTime.toLocalDate(), adjustmentTimeResult);
                return adjustmentResult;
        }

        private LocalTime adjustRoundInterval(Shift S) {
                LocalTime temp;
                int minutes = this.punchTime.getMinute();
                int timeToRoundInterval = minutes % S.getRoundInterval();
                if (timeToRoundInterval == 0) {
                        temp = punchTime.toLocalTime().withNano(0);
                        this.adjustmentType = "None";
                } else if (timeToRoundInterval < S.getRoundInterval() / 2) {
                        temp = punchTime.minusMinutes(timeToRoundInterval).toLocalTime();
                        this.adjustmentType = "Interval Round";
                } else {
                        temp = this.punchTime.plusMinutes(S.getRoundInterval() - timeToRoundInterval).toLocalTime();
                        this.adjustmentType = "Interval Round";
                }

                return temp.withSecond(0);

        }

        /*
         * Boolean Helper Functions for AdjustClockIn, AdjustClockOut, and
         * AdjsutRoundInterval functions
         */
        private boolean checkInRoundIntervalBeforeShift(Shift S) {
                boolean result = false;
                LocalTime clockpunch = this.punchTime.toLocalTime().withNano(0);
                LocalTime startOfShift = S.getShiftStart().withNano(0);
                LocalTime beginOfIntervalBeforeShift = startOfShift.minusMinutes(S.getRoundInterval()).withNano(0);

                if (clockpunch.isAfter(beginOfIntervalBeforeShift) || clockpunch == beginOfIntervalBeforeShift) {
                        result = true;
                }

                return result;

        }

        private boolean checkInRoundIntervalAfterShift(Shift S) {
                boolean result = false;
                LocalTime clockpunch = this.punchTime.toLocalTime().withNano(0);
                LocalTime endOfShift = S.getShiftStop().withNano(0);
                LocalTime endOfIntervalAfterShift = endOfShift.plusMinutes(S.getRoundInterval()).withNano(0);

                if (clockpunch.isBefore(endOfIntervalAfterShift) || clockpunch == endOfIntervalAfterShift) {
                        result = true;
                }

                return result;

        }

        private boolean checkInDockPeriod(Shift S) {
                boolean result = false;

                LocalTime clockpunch = this.punchTime.toLocalTime().withNano(0);
                LocalTime shiftStart = S.getShiftStart().withNano(0);
                LocalTime shiftStop = S.getShiftStop();

                if (this.punchType == punchType.CLOCK_IN) {

                        LocalTime endDockTime = shiftStart.plusMinutes(S.getDockPenalty());
                        LocalTime endGracePeriod = shiftStart.plusMinutes(S.getGracePeriod());

                        if ((clockpunch.isAfter(endGracePeriod) && clockpunch.isBefore(endDockTime))
                                        || clockpunch == endDockTime) {
                                result = true;
                        }
                }

                if (this.punchType == punchType.CLOCK_OUT) {
                        LocalTime endDockTime = shiftStop.minusMinutes(S.getDockPenalty());
                        LocalTime endGracePeriod = shiftStop.minusMinutes(S.getGracePeriod());

                        if (clockpunch.isBefore(endGracePeriod) && clockpunch.isAfter(endDockTime)) {
                                result = true;
                        } else if (clockpunch.compareTo(endDockTime) == 0) {
                                result = true;
                        }
                }
                return result;

        }

        private boolean checkInGracePeriod(Shift S) {
                boolean result = false;
                LocalTime clockpunch = this.punchTime.toLocalTime().withNano(0);

                LocalTime shiftStart = S.getShiftStart().withNano(0);
                LocalTime shiftStop = S.getShiftStop();
                if (this.punchType == PunchType.CLOCK_IN) {

                        LocalTime endGracePeriod = shiftStart.plusMinutes(S.getGracePeriod());

                        if (clockpunch.isBefore(endGracePeriod) || clockpunch == endGracePeriod) {
                                result = true;
                        }
                }
                if (this.punchType == PunchType.CLOCK_OUT) {

                        LocalTime endGracePeriod = shiftStop.minusMinutes(S.getGracePeriod());

                        if (clockpunch.isAfter(endGracePeriod) || clockpunch == endGracePeriod) {
                                result = true;
                        }

                }
                return result;
        }

        private boolean checkInLunchBreak(Shift S) {
                boolean result = false;
                LocalTime lunchStart = S.getLunchStart();
                LocalTime lunchEnd = S.getLunchStop();
                LocalTime clockpunch = this.punchTime.toLocalTime().withNano(0);

                if (clockpunch.isAfter(lunchStart) && clockpunch.isBefore(lunchEnd)) {
                        result = true;
                }
                if (clockpunch == lunchStart || clockpunch == lunchEnd) {
                        result = true;
                }
                return result;

        }
        // End Helper Functions For Adjust()

}
