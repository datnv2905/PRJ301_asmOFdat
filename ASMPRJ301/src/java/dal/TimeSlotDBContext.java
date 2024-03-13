/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Timeslots;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnt
 */
public class TimeSlotDBContext extends DBContext<Timeslots> {

    public ArrayList<Timeslots> list() {
        ArrayList<Timeslots> slots = new ArrayList<>();
        try {
            
            String sql = "SELECT Tid,description FROM timeslot";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Timeslots t = new Timeslots();
                t.setTid(rs.getString("Tid"));
                t.setDescription(rs.getString("description"));
                slots.add(t);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(TimeSlotDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
         return slots;
    }

   
}
