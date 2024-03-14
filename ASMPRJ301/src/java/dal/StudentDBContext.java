/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Students;
import entity.Subjects;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.Subject;

/**
 *
 * @author sonnt
 */
public class StudentDBContext extends DBContext<Students> {

    public ArrayList<Students> getStudentsByLessionId(String lesid) {
        ArrayList<Students> students = new ArrayList<>();
        try {
            String sql = "SELECT s.sid,s.sname\n"
                    + "       FROM Student s INNER JOIN Erollment e ON s.sid = e.sid\n"
                    + "					  INNER JOIN [group] g ON g.gid = e.gid\n"
                    + "                  	  INNER JOIN Lession les ON les.gid = g.gid\n"
                    + "       WHERE les.lesid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lesid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Students s = new Students();
                s.setSid(rs.getString("sid"));
                s.setSname(rs.getString("sname"));
                students.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public ArrayList<Students> getStudentByClass(String suid, String gname) {
        ArrayList<Students> stu = new ArrayList<>();
        try {
            String sql = "select s.sid,s.sname,s.email,s.img \n"
                    + "from student s inner join erollment e on s.sid=e.sid\n"
                    + "		      inner join [group] g on e.gid = g.gid\n"
                    + "		      inner join subject su on g.suid=su.suid\n"
                    + " where su.suid = ? and g.gname=? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, suid);
            stm.setString(2, gname);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Students s = new Students();
                s.setSid(rs.getString("sid"));
                s.setSname(rs.getString("sname"));
                s.setEmail(rs.getString("email"));
                s.setImg(rs.getString("img"));
                stu.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stu;
    }

    
    
}
