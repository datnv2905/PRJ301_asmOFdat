/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import entity.Students;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnt
 */
public class StudentDBContext extends DBContext<Students> {

    public ArrayList<Students> getStudentsByLessionId(int leid) {
        ArrayList<Students> students = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "s.sid,s.sname\n"
                    + "FROM Student s INNER JOIN Enrollment e ON s.sid = e.sid\n"
                    + "						INNER JOIN group g ON g.gid = e.gid\n"
                    + "						INNER JOIN Lession les ON les.gid = g.gid\n"
                    + "WHERE les.leid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, leid);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
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

    @Override
    public ArrayList<Students> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Students entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Students entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Students entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Students get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
