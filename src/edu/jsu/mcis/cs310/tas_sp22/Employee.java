
package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class Employee {
    Integer employeeId;
    String badgeid;
    String firstName;
    String middleName;
    String lastName;
    Integer departmentId;
    Integer shiftID;
    LocalDate activeDate;
    LocalTime activeTime;
    LocalDate inactiveDate;
    LocalTime inactiveTime;

    public Employee(HashMap<String, String> Input) {
        this.employeeId = Integer.parseInt(Input.get("employeeId"));
        this.badgeid = Input.get("badgeid");
        this.firstName = Input.get("firstName");
        this.middleName = Input.get("middleName");
        this.lastName = Input.get("lastName");
        this.departmentId = Integer.parseInt(Input.get("departmentId"));
        this.shiftID = Integer.parseInt(Input.get("shiftID"));
        this.activeDate = new LocalDate(Integer.parseInt(Input.get("year")), Integer.parseInt(Input.get("month")),
                Integer.parseInt(Input.get("day")));

    }

}
