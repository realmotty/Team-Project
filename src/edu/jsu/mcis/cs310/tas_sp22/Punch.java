package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
                switch (punchTypeId) {
                        case 0:
                                this.punchType = PunchType.CLOCK_OUT;
                        case 1:
                                this.punchType = PunchType.CLOCK_IN;
                        case 2:
                                this.punchType = PunchType.TIME_OUT;
                }

                /* default values */

                this.punchTime = LocalDateTime.now();
                this.adjustmentType = null;
                this.ajustedPunchTime = null;
                this.id = 0;

                this.punchType = null;

        }

        // todo add other constructor

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
                StringBuilder sb = new StringBuilder();
                sb.append("#").append(badge.getId());

                return sb.toString();

        }
}
