/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_sp22;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
/**
 *
 * @author Jonathan Carter
 */
public class TAS {
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift){
           int CLOCK_IN = 0;
           int CLOCK_OUT = 0;
           int TIME_OUT = 0;
           
           
        for (Punch punch: ){
            switch (punch.getPunchtypeID()){
                case 0: 
                    out = punch
            }
        
    }
           
           
}
    
    
    public static void main(String[] args) {
        TASDatabase db = new TASDatabase("tasuser", "warroomc", "localhost");

        System.out.println("\nTEST -- getBadge");
        Badge b = db.getBadge("12565C60");
        System.out.println(b.toString());

        System.out.println("\nTEST -- getPunch");
        Punch p1 = db.getPunch(3433);
        System.out.println(p1.printOriginal());

        System.out.println("\nTEST -- getShift by id");/* get get Shift by id */
        Shift s1 = db.getShift(1);
        System.out.println(s1.toString());

        System.out.println("\nTEST -- getEmployee by id");
        Employee e1 = db.getEmployee(10);
        System.out.println(e1.toString());
    }

}


   
            

