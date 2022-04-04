package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalTime;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

public class Shift {
    int id;
    int roundInterval;
    int gracePeriod;
    int dockPenalty;
    int lunchThreshold;
    int shiftLength;
    int lunchLength;

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

        this.shiftLength = (int) (shiftStart.until(shiftStop, ChronoUnit.MINUTES));
        this.lunchLength = (int) (lunchStart.until(lunchStop, ChronoUnit.MINUTES));
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");

        StringBuilder sb = new StringBuilder();
        sb.append(description).append(": ");

        sb.append(shiftStart.format(format)).append(" - ");
        sb.append(shiftStop.format(format)).append(" (");
        sb.append(shiftLength).append(" minutes); ");

        sb.append("Lunch: ").append(lunchStart.format(format)).append(" - ");
        sb.append(lunchStop.format(format)).append(" (");
        sb.append(lunchLength).append(" minutes)");

        return sb.toString();
    }

}
