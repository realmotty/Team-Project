/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_sp22;

/**
 *
 * @author johnc
 */
public class TAS {
    public static void main(String[] args) {
        TASDatabase db = new TASDatabase("tasuser", "warroomc", "localhost");

        Badge b = db.getBadge("12565C60");
        System.out.println(b.toString());

        Punch p1 = db.getPunch(3433);
        System.out.println(p1.printOriginal());
        
         Shift s1 = db.getShift(1);
         System.out.println(s1.toString());
    }

}
