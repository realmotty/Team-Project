
package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Employee {
    private final Integer employeeId;
    private final String badgeid;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final Integer departmentId;
    private final Integer shiftID;
    private final Integer employeeTypeId;

    public LocalDateTime getActiveTime() {
        return activeTime;
    }

    public LocalDateTime getInactiveTime() {
        return inactiveTime;
    }

    private final LocalDateTime activeTime;
    private final LocalDateTime inactiveTime;

    /**
     * 
     * @param Input- Hash map containing the employee information
     */
    public Employee(HashMap<String, Object> Input) {
        this.employeeId = (Integer) Input.get("id");
        this.badgeid = (String) Input.get("badgeid");
        this.firstName = (String) Input.get("firstname");
        this.middleName = (String) Input.get("middlename");
        this.lastName = (String) Input.get("lastname");
        this.departmentId = (Integer) Input.get("departmentid");
        this.shiftID = (Integer) Input.get("shiftid");
        this.employeeTypeId = (Integer) Input.get("employeetypeid");

        this.activeTime = (LocalDateTime) Input.get("active");
        this.inactiveTime = (LocalDateTime) Input.get("inactive");

    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getBadgeid() {
        return badgeid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public Integer getShiftID() {
        return shiftID;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY-MM-dd");

        StringBuilder sb = new StringBuilder();
        sb.append("#").append(badgeid);
        sb.append(" (").append(lastName).append(", ");
        sb.append(firstName).append(" ").append(middleName).append("): ");
        sb.append("employeetypeid: ").append(employeeTypeId).append(", ");
        sb.append("departmentid: ").append(departmentId).append(", ");
        sb.append("shiftid").append(": ").append(shiftID).append(", ");
        sb.append("active").append(": ").append(activeTime.format(format)).append(", ");
        // add active time stamp;
        sb.append("inactive:").append(" ").append("none");

        return sb.toString();
    }

}
