
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

    public Employee(HashMap<String, String> Input) {
        this.employeeId = Integer.parseInt(Input.get("employeeId"));
        this.badgeid = Input.get("badgeid");
        this.firstName = Input.get("firstName");
        this.middleName = Input.get("middleName");
        this.lastName = Input.get("lastName");
        this.departmentId = Integer.parseInt(Input.get("departmentId"));
        this.shiftID = Integer.parseInt(Input.get("shiftID"));
        this.activeDate = new LocalDate(Integer.parseInt(Input.get("year")), Integer.parseInt(Input.get("month")),
        //Integer.parseInt(Input.get("day")));

    }

}
