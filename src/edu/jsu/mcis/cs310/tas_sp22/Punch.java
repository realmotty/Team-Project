package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Punch {
    
    private int id;
    
    private int punchTypeid;
    private int terminalId;
    private Badge badge;

    private String adjustmentType;
    private LocalDateTime punchTime;
    private LocalDateTime ajustedPunchTime;
    
    
   Punch(int terminalId, Badge badge, int punchTypeId){
       
       this.terminalId = terminalId;
       this.badge = badge;
       this.punchTypeid = punchTypeId;
       
       /*default values*/
       
       this.punchTime = LocalDateTime.now();
       this.adjustmentType = null;
       this.ajustedPunchTime = null;
       this.id = 0;
       
       
   }

}
