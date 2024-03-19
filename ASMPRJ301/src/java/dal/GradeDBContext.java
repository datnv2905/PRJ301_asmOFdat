/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Assessment;
import entity.Exam;
import entity.Grade;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class GradeDBContext extends DBContext<Grade> {

    public ArrayList<Grade> getGradeBySid(String sid,String suid) {
        ArrayList<Grade> gra = new ArrayList<>();
        String sql = "select a.name,a.weght*100 as weght,g.score,e.eid\n"
                + "from Assessment a inner join Exam e on a.assmid = e.assmid\n"
                + "                  inner join Grade g on e.eid = g.eid \n"
                + "		     inner join Student s on g.sid=s.sid\n"
                + "   where s.sid =? and a.suid = ?\n"
                + "   order by a.assmid asc"; 
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            stm.setString(2, suid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Assessment as = new Assessment();
                Grade g = new Grade();
                Exam e = new Exam();
                as.setName(rs.getString("name"));
                as.setWeght(rs.getFloat("weght"));
                e.setAssessment(as);
                e.setEid(rs.getString("eid"));
                g.setExam(e);
                g.setScore(rs.getFloat("score"));
                gra.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gra;

    }
    
    public float getAvgGrade(String sid, String suid) {
        float avgGrade = 0;
        
        String sql = "select SUM(g.score *a.weght)  AS avg from Assessment a inner join Exam e on a.assmid = e.assmid\n"
                + "                           inner join Grade g on e.eid = g.eid \n"
                + "			               inner join Student s on g.sid=s.sid\n"
                + "				where s.sid =? and a.suid =? \n"
                + "				GROUP BY s.sid";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, sid);
            stm.setString(2, suid);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                avgGrade = (float) rs.getDouble("avg");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avgGrade;
        
    }
}
