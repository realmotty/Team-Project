package edu.jsu.mcis.cs310.tas_sp22;

public class Department {
        private final int id;
        private final String description;
        private final int terminalId;

        Department(int id, String description, int terminalId) {
                this.id = id;
                this.description = description;
                this.terminalId = terminalId;
        }

        public int getTerminalId() {
                return terminalId;
        }

        @Override
        public String toString() {
                StringBuilder sb = new StringBuilder();

                sb.append("#").append(id).append(" ");
                sb.append("(").append(description).append("): ");
                sb.append("terminalid: ").append(terminalId);

                return sb.toString();
        }

}
