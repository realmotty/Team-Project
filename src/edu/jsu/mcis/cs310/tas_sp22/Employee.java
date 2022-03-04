
package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Employee {
    private final Integer employeeId;
    private final String badgeid;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final Integer departmentId;
    private final Integer shiftID;

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
        this.employeeId = (Integer) Input.get("employeeId");
        this.badgeid = (String) Input.get("badgeid");
        this.firstName = (String) Input.get("firstName");
        this.middleName = (String) Input.get("middleName");
        this.lastName = (String) Input.get("lastName");
        this.departmentId = (Integer) Input.get("departmentId");
        this.shiftID = (Integer) Input.get("shiftID");

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

    

}
