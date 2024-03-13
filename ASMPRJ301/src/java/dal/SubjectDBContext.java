/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Subjects;
import entity.Timeslots;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author admin
 */
public class SubjectDBContext extends DBContext<Subjects>{
     public ArrayList<Subjects> list() {
         ArrayList<Subjects> sub = new ArrayList<>();
         try {
             
             String sql = "select suid, suname from subject";
             PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery();
             while(rs.next())
             {
                 Subjects s = new Subjects();
                 s.setSuid(rs.getString("suid"));
                 s.setSuname(rs.getString("suname"));
                 sub.add(s);
             }
         } catch (SQLException ex) {
             Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
         }
         return sub;
    }
}
