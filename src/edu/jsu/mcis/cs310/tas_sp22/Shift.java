package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalTime;
import java.util.HashMap;

public class Shift {
        int id;
        int roundInterval;
        int gracePeriod;
        int dockPenalty;
        int lunchThreshold;

        String description;

        LocalTime shiftStart;
        LocalTime shiftStop;
        LocalTime lunchStart;
        LocalTime lunchStop;

        public Shift(HashMap<String, Object> Input) {
                this.id = (Integer) Input.get("id");
                this.description = (String) Input.get("description");
                this.shiftStart = (LocalTime) Input.get("shiftstart");
                this.shiftStop = (LocalTime) Input.get("shiftstop");
                this.roundInterval = (Integer) Input.get("roundinterval");
                this.gracePeriod = (Integer) Input.get("graceperiod");
                this.dockPenalty = (Integer) Input.get("dockpenalty");
                this.lunchStart = (LocalTime) Input.get("lunchstart");
                this.lunchStop = (LocalTime) Input.get("lunchstop");
                this.lunchThreshold = (Integer) Input.get("lunchthreshold");
        }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shift{id=").append(id);
        sb.append(", roundInterval=").append(roundInterval);
        sb.append(", gracePeriod=").append(gracePeriod);
        sb.append(", dockPenalty=").append(dockPenalty);
        sb.append(", lunchThreshold=").append(lunchThreshold);
        sb.append(", description=").append(description);
        sb.append(", shiftStart=").append(shiftStart);
        sb.append(", shiftStop=").append(shiftStop);
        sb.append(", lunchStart=").append(lunchStart);
        sb.append(", lunchStop=").append(lunchStop);
        sb.append('}');
        return sb.toString();
    }

}
