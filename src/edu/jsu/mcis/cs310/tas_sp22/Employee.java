
package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class Employee {
    private Integer employeeId;
    private String badgeid;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer departmentId;
    private Integer shiftID;
    private LocalDate activeDate;
    private LocalTime activeTime;
    private LocalDate inactiveDate;
    private LocalTime inactiveTime;

    /**
     * 
     * @param Input- Hash map containing the employee information
     */
    public Employee(HashMap<String, String> Input) {
        this.employeeId = Integer.parseInt(Input.get("employeeId"));
        this.badgeid = Input.get("badgeid");
        this.firstName = Input.get("firstName");
        this.middleName = Input.get("middleName");
        this.lastName = Input.get("lastName");
        this.departmentId = Integer.parseInt(Input.get("departmentId"));
        this.shiftID = Integer.parseInt(Input.get("shiftID"));

        this.activeDate = LocalDate.of(Integer.parseInt(Input.get("activeYear")),
                Integer.parseInt(Input.get("activeMonth")),
                Integer.parseInt(Input.get("activeDay")));
        this.activeTime = LocalTime.of(Integer.parseInt(Input.get("activeHour")),
                Integer.parseInt(Input.get("activeMinute")));

        this.inactiveDate = LocalDate.of(Integer.parseInt(Input.get("inactiveYear")),
                Integer.parseInt(Input.get("inactiveMonth")),
                Integer.parseInt(Input.get("inactiveDay")));
        this.inactiveTime = LocalTime.of(Integer.parseInt(Input.get("inactiveHour")),
                Integer.parseInt(Input.get("inactiveMinute")));

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

    public LocalDate getActiveDate() {
        return activeDate;
    }

    public LocalTime getActiveTime() {
        return activeTime;
    }

    public LocalDate getInactiveDate() {
        return inactiveDate;
    }

    public LocalTime getInactiveTime() {
        return inactiveTime;
    }

}
