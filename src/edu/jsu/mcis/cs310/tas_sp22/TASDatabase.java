package edu.jsu.mcis.cs310.tas_sp22;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import org.json.simple.*;
import org.json.simple.parser.*;

public class TASDatabase {
    private final Connection connection;

    public TASDatabase(String username, String password, String address) {

        this.connection = openConnection(username, password, address);

    }

    // get punch
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
                resultset.close();
                pstSelect.close();
                connection.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    // get badge

    // get employee

    // get employee 2

    // get shift

    // get shift 2

    /* PRIVATE METHODS */

    private Connection openConnection(String u, String p, String a) {

        Connection c = null;

        if (a.equals("") || u.equals("") || p.equals(""))

            System.err.println(
                    "*** ERROR: MUST SPECIFY ADDRESS/USERNAME/PASSWORD BEFORE OPENING DATABASE CONNECTION ***");

        else {

            try {

                String url = "jdbc:mysql://" + a
                        + "/jsu_sp22_v1?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=EXCEPTION&serverTimezone=America/Chicago";
                // System.err.println("Connecting to " + url + " ...");

                c = DriverManager.getConnection(url, u, p);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return c;

    }

    private String getResultSetAsJSON(ResultSet resultset) {

        String result;

        /* Create JSON Containers */

        JSONArray json = new JSONArray();
        JSONArray keys = new JSONArray();

        try {

            /* Get Metadata */

            ResultSetMetaData metadata = resultset.getMetaData();
            int columnCount = metadata.getColumnCount();

            /* Get Keys */

            for (int i = 1; i <= columnCount; ++i) {

                keys.add(metadata.getColumnLabel(i));

            }

            /* Get ResultSet Data */

            while (resultset.next()) {

                /* Create JSON Container for New Row */

                JSONObject row = new JSONObject();

                /* Get Row Data */

                for (int i = 1; i <= columnCount; ++i) {

                    /* Get Value; Pair with Key */

                    Object value = resultset.getObject(i);
                    row.put(keys.get(i - 1), String.valueOf(value));

                }

                /* Add Row Data to Collection */

                json.add(row);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Encode JSON Data and Return */

        result = JSONValue.toJSONString(json);
        return result;

    }
}