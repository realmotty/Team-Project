package edu.jsu.mcis.cs310.tas_sp22;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import org.json.simple.*;
import org.json.simple.parser.*;

public class TASDatabase {
    private final Connection connection;

    public TASDatabase(String username, String password, String address) {

        this.connection = openConnection(username, password, address);

    }

    /**
     * 
     * @param punchId
     * @return
     */
    public Punch getPunch(int punchId) {
        ResultSet resultset = null;
        boolean hasresults;
        Punch result = null;
        String query = null;
        PreparedStatement pstSelect = null;

        try {
            /* Prepare Select Query */
            query = "SELECT * FROM event e WHERE id = ?";
            pstSelect = connection.prepareStatement(query);
            pstSelect.setInt(1, punchId);

            /* Execute Select Query */
            hasresults = pstSelect.execute();

            /* Check for Results */
            if (hasresults) {

                /* Get Results set */
                resultset = pstSelect.getResultSet();
                resultset.next();

                /* Get individual columsn from result set */
                int id = resultset.getInt("id");
                int terminalId = resultset.getInt("terminalid");
                String badgeid = resultset.getString("badgeid");
                int eventtypeid = resultset.getInt("eventtypeid");

                /* get timestamp and convert to LocalDateTime */
                java.sql.Timestamp timestamp = resultset.getTimestamp("timestamp");
                LocalDateTime eventTime = timestamp.toLocalDateTime();

                /* create Punch object */

                result = new Punch(id, terminalId, badgeid, eventTime, eventtypeid);

                /* Close connections */

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * 
     * @param badgeId
     * @return
     */
    public Badge getBadge(String badgeId) {
        ResultSet resultset = null;
        boolean hasresults;
        Badge result = null;
        String query = null;
        PreparedStatement pstSelect = null;

        try {
            /* Prepare Select Query */
            query = "SELECT * FROM badge b WHERE id = ?";
            pstSelect = connection.prepareStatement(query);
            pstSelect.setString(1, badgeId);

            /* Execute Select Query */
            hasresults = pstSelect.execute();

            /* Check for Results */
            if (hasresults) {

                /* Get Results set */
                resultset = pstSelect.getResultSet();
                resultset.next();

                /* Get individual columsn from result set */
                String id = resultset.getString("id");
                String description = resultset.getString("description");

                /* create Badge object */
                result = new Badge(id, description);

                /* Close connections */

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * 
     * @param employeeId
     * @return
     */
    public Employee getEmployee(int employeeId) {

        Employee result = null;

        try {
            /* Prepare Select Query */
            String query = "SELECT * FROM employee e WHERE id = ?";
            PreparedStatement pstSelect = connection.prepareStatement(query);
            pstSelect.setInt(1, employeeId);

            /* Execute Select Query */
            boolean hasresults = pstSelect.execute();

            /* Check for Results */
            if (hasresults) {

                /* Get Results set */
                ResultSet resultset = pstSelect.getResultSet();

                if (resultset.next()) {

                    // map to hold parameters
                    HashMap<String, Object> attributes = new HashMap<>();

                    /* Populate the attribute map */
                    attributes.put("id", resultset.getInt("id"));
                    attributes.put("badgeid", resultset.getString("badgeid"));
                    attributes.put("firstname", resultset.getString("firstname"));
                    attributes.put("middlename", resultset.getString("middlename"));
                    attributes.put("lastname", resultset.getString("lastname"));
                    attributes.put("employeetypeid", resultset.getInt("employeetypeid"));
                    attributes.put("departmentid", resultset.getInt("departmentid"));
                    attributes.put("shiftid", resultset.getInt("shiftid"));
                    attributes.put("active", resultset.getTimestamp("active").toLocalDateTime());

                    java.sql.Timestamp inactive = resultset.getTimestamp("inactive");

                    if (inactive != null) {
                        attributes.put("inactive", inactive.toLocalDateTime());
                    }

                    /* create Employee object */
                    result = new Employee(attributes);

                    /* DEBUGGING LOG INFO */
                    System.out.println("Attributes for each employee from Query");
                    attributes.entrySet().forEach(entry -> {
                        System.out.println(entry.getKey() + " => " + entry.getValue());
                    });

                    System.out.println("\n\n");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    // get employee 2
    public Employee getEmployee(Badge badgeId) {

        Employee result = null;

        try {
            /* Prepare Select Query */
            String query = "SELECT * FROM employee e WHERE badgeid = ?";
            PreparedStatement pstSelect = connection.prepareStatement(query);
            pstSelect.setString(1, badgeId.getId());

            /* Execute Select Query */
            boolean hasresults = pstSelect.execute();

            /* Check for Results */
            if (hasresults) {

                /* Get Results set */
                ResultSet resultset = pstSelect.getResultSet();
                if (resultset.next()) {

                    int id = resultset.getInt("id");
                    result = getEmployee(id);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    // get shift
    public Shift getShift(int ShiftId) {
        ResultSet resultset = null;
        boolean hasresults;
        Shift result = null;
        String query = null;
        PreparedStatement pstSelect = null;

        try {
            /* Prepare Select Query */
            query = "SELECT * FROM shift s WHERE id = ?";
            pstSelect = connection.prepareStatement(query);
            pstSelect.setInt(1, ShiftId);

            /* Execute Select Query */
            hasresults = pstSelect.execute();

            /* Check for Results */
            if (hasresults) {

                /* Get Results set */
                resultset = pstSelect.getResultSet();
                resultset.next();

                // map to hold parameters
                HashMap<String, Object> attributes = new HashMap<String, Object>();

                /* get timestamp and convert to LocalTime */
                java.sql.Timestamp shiftstartTimestamp = resultset.getTimestamp("shiftstart");
                if (shiftstartTimestamp != null) {
                    attributes.put("shiftstart", shiftstartTimestamp.toLocalDateTime().toLocalTime());
                }

                java.sql.Timestamp shiftstopTimestamp = resultset.getTimestamp("shiftstop");
                if (shiftstartTimestamp != null) {
                    attributes.put("shiftstop", shiftstopTimestamp.toLocalDateTime().toLocalTime());
                }

                java.sql.Timestamp lunchstartTimestamp = resultset.getTimestamp("lunchstart");
                if (shiftstartTimestamp != null) {
                    attributes.put("lunchstart", lunchstartTimestamp.toLocalDateTime().toLocalTime());
                }

                java.sql.Timestamp lunchstopTimestamp = resultset.getTimestamp("lunchstop");
                if (shiftstartTimestamp != null) {
                    attributes.put("lunchstop", lunchstopTimestamp.toLocalDateTime().toLocalTime());
                }

                /* Populate the attribute map */

                attributes.put("id", resultset.getObject("id"));
                attributes.put("description", resultset.getObject("description"));
                attributes.put("roundinterval", resultset.getObject("roundinterval"));
                attributes.put("graceperiod", resultset.getObject("graceperiod"));
                attributes.put("dockpenalty", resultset.getObject("dockpenalty"));
                attributes.put("lunchthreshold", resultset.getObject("lunchthreshold"));

                /* create Employee object */
                result = new Shift(attributes);

                /* Close connections */

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * 
     * @param badgeId
     * @return
     */
    public Shift getShift(Badge badgeId) {
        Shift result = null;

        try {
            Employee employee = getEmployee(badgeId);

            /* Prepare Select Query */
            String query = "SELECT * FROM shift s WHERE id = ?";
            PreparedStatement pstSelect = connection.prepareStatement(query);
            pstSelect.setInt(1, employee.getShiftID());

            /* Execute Select Query */
            Boolean hasresults = pstSelect.execute();

            /* Check for Results */
            if (hasresults) {

                /* Get Results set */
                ResultSet resultset = pstSelect.getResultSet();
                if (resultset.next()) {

                    int id = resultset.getInt("id");
                    result = getShift(id);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    /* PRIVATE METHODS */

    private Connection openConnection(String u, String p, String a) {

        Connection c = null;

        if (a.equals("") || u.equals("") || p.equals(""))

            System.err.println(
                    "*** ERROR: MUST SPECIFY ADDRESS/USERNAME/PASSWORD BEFORE OPENING DATABASE CONNECTION ***");

        else {

            try {

                String url = "jdbc:mysql://" + a
                        + "/tas_sp22_v1?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=EXCEPTION&serverTimezone=America/Chicago";
                // System.err.println("Connecting to " + url + " ...");

                c = DriverManager.getConnection(url, u, p);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return c;

    }

}