package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.*;
import static org.junit.Assert.*;

public class Feature2 {
    
    private TASDatabase db;
    
    @Before
    public void setup() {
        
        db = new TASDatabase();
        
    }
    
    @Test
    public void testGetDepartment() {
		
        /* Retrieve Departments from Database */

        Department d1 = db.getDepartment(1);
        Department d2 = db.getDepartment(2);
        Department d3 = db.getDepartment(3);
        Department d4 = db.getDepartment(4);
        Department d5 = db.getDepartment(5);
        Department d6 = db.getDepartment(6);
        Department d7 = db.getDepartment(7);
        Department d8 = db.getDepartment(8);
        Department d9 = db.getDepartment(9);
        Department d10 = db.getDepartment(10);
		
        /* Compare to Expected Values */

        assertEquals("#1 (Assembly): terminalid: 103", d1.toString());
        assertEquals("#2 (Cleaning): terminalid: 107", d2.toString());
        assertEquals("#3 (Warehouse): terminalid: 106", d3.toString());
        assertEquals("#4 (Grinding): terminalid: 104", d4.toString());
        assertEquals("#5 (Hafting): terminalid: 105", d5.toString());
        assertEquals("#6 (Office): terminalid: 102", d6.toString());
        assertEquals("#7 (Press): terminalid: 104", d7.toString());
        assertEquals("#8 (Shipping): terminalid: 107", d8.toString());
        assertEquals("#9 (Tool and Die): terminalid: 104", d9.toString());
        assertEquals("#10 (Maintenance): terminalid: 104", d10.toString());

    }
    
    @Test
    public void testInsertCheckNewPunch1() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
        /* Create New Punch Object */

        Punch p1 = new Punch(103, db.getBadge("021890C0"), 1);
        
        /* Create Timestamp Objects */
        
        LocalDateTime ots, rts;
		
        /* Get Punch Properties */
        
        String badgeid = p1.getBadge().getId();
        ots = p1.getOriginalTimestamp();
        int terminalid = p1.getTerminalid();
        PunchType punchtype = p1.getPunchtype();
		
        /* Insert Punch Into Database */
        
        int punchid = db.insertPunch(p1);
		
        /* Retrieve New Punch */
        
        Punch p2 = db.getPunch(punchid);
		
        /* Compare Punches */

        assertEquals(badgeid, p2.getBadge().getId());
        
        rts = p2.getOriginalTimestamp();
        
        assertEquals(terminalid, p2.getTerminalid());
        assertEquals(punchtype, p2.getPunchtype());
        assertEquals(ots.format(dtf), rts.format(dtf));
        
    }
    
    @Test
    public void testInsertCheckNewPunch2() {
		
        /* Create New Punch Object */

        Punch p1 = new Punch(104, db.getBadge("021890C0"), 1);
		
        /* Attempt to Insert Punch Into Database */
        
        int punchid = db.insertPunch(p1);
		
        /* Check Result (should fail) */

        assertEquals(0, punchid);
        
    }
    
    @Test
    public void testInsertCheckNewPunch3() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
        /* Create New Punch Object */

        Punch p1 = new Punch(0, db.getBadge("0D87987C"), 1);
        
        /* Create Timestamp Objects */
        
        LocalDateTime ots, rts;
		
        /* Get Punch Properties */
        
        String badgeid = p1.getBadge().getId();
        ots = p1.getOriginalTimestamp();
        int terminalid = p1.getTerminalid();
        PunchType punchtype = p1.getPunchtype();
		
        /* Insert Punch Into Database */
        
        int punchid = db.insertPunch(p1);
		
        /* Retrieve New Punch */
        
        Punch p2 = db.getPunch(punchid);
		
        /* Compare Punches */

        assertEquals(badgeid, p2.getBadge().getId());
        
        rts = p2.getOriginalTimestamp();
        
        assertEquals(terminalid, p2.getTerminalid());
        assertEquals(punchtype, p2.getPunchtype());
        assertEquals(ots.format(dtf), rts.format(dtf));
        
    }
    
    @Test
    public void testInsertCheckNewPunch4() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
        /* Create New Punch Object */

        Punch p1 = new Punch(104, db.getBadge("07901755"), 1);
        
        /* Create Timestamp Objects */
        
        LocalDateTime ots, rts;
		
        /* Get Punch Properties */
        
        String badgeid = p1.getBadge().getId();
        ots = p1.getOriginalTimestamp();
        int terminalid = p1.getTerminalid();
        PunchType punchtype = p1.getPunchtype();
		
        /* Insert Punch Into Database */
        
        int punchid = db.insertPunch(p1);
		
        /* Retrieve New Punch */
        
        Punch p2 = db.getPunch(punchid);
		
        /* Compare Punches */

        assertEquals(badgeid, p2.getBadge().getId());
        
        rts = p2.getOriginalTimestamp();
        
        assertEquals(terminalid, p2.getTerminalid());
        assertEquals(punchtype, p2.getPunchtype());
        assertEquals(ots.format(dtf), rts.format(dtf));
        
    }
    
    @Test
    public void testInsertCheckNewPunch5() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
        /* Create New Punch Object */

        Punch p1 = new Punch(104, db.getBadge("3282F212"), 0);
        
        /* Create Timestamp Objects */
        
        LocalDateTime ots, rts;
		
        /* Get Punch Properties */
        
        String badgeid = p1.getBadge().getId();
        ots = p1.getOriginalTimestamp();
        int terminalid = p1.getTerminalid();
        PunchType punchtype = p1.getPunchtype();
		
        /* Insert Punch Into Database */
        
        int punchid = db.insertPunch(p1);
		
        /* Retrieve New Punch */
        
        Punch p2 = db.getPunch(punchid);
		
        /* Compare Punches */

        assertEquals(badgeid, p2.getBadge().getId());
        
        rts = p2.getOriginalTimestamp();
        
        assertEquals(terminalid, p2.getTerminalid());
        assertEquals(punchtype, p2.getPunchtype());
        assertEquals(ots.format(dtf), rts.format(dtf));
        
    }
    
}