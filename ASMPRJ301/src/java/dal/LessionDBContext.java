
package dal;

import entity.Attendence;
import entity.Lession;
import entity.Students;
import entity.Lecturer;
import entity.Room;
import entity.Group;
import entity.Subjects;
import entity.Timeslots;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnt
 */
public class LessionDBContext extends DBContext<Lession> {

    public void takeAttendenceAfter(String lesid, ArrayList<Attendence> atts) {
        try {
            String spl_sosanh = "UPDATE [dbo].[Attendence]\n"
                    + "   SET [description] = ?\n"
                    + "      ,[present] = ?\n"
                    + "      ,[capturedtime] = getDate()\n"
                    + " WHERE [lesid] = ? and [sid] = ? and ( [description] != ? or [present] != ?  )";
            for (Attendence att : atts) {
                PreparedStatement stm_ss = connection.prepareStatement(spl_sosanh);
                stm_ss.setString(1, att.getDescription());
                stm_ss.setBoolean(2, att.isPresent());
                stm_ss.setString(3, lesid);
                stm_ss.setString(4, att.getStudent().getSid());
                stm_ss.setString(5, att.getDescription());
                stm_ss.setBoolean(6, att.isPresent());
                stm_ss.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void takeAttendance(String lesid, ArrayList<Attendence> atts) {
        try {
            connection.setAutoCommit(false);
            String sql_remove_atts = "DELETE Attendence WHERE lesid = ?";
            PreparedStatement stm_remove_atts = connection.prepareStatement(sql_remove_atts);
            stm_remove_atts.setString(1, lesid);
            stm_remove_atts.executeUpdate();

            String sql_insert_att = "INSERT INTO [Attendence]\n"
                    + "           ([lesid]\n"
                    + "           ,[sid]\n"
                    + "           ,[description]\n"
                    + "           ,[present]\n"
                    + "           ,[capturedtime])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,GETDATE())";
            for (Attendence att : atts) {
                PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                stm_insert_att.setString(1, lesid);
                stm_insert_att.setString(2, att.getStudent().getSid());
                stm_insert_att.setString(3, att.getDescription());
                stm_insert_att.setBoolean(4, att.isPresent());
                stm_insert_att.executeUpdate();
            }

            String sql_update_less = "UPDATE Lession SET atd = 1  WHERE lesid = ?";
            PreparedStatement stm_update_less = connection.prepareStatement(sql_update_less);
            stm_update_less.setString(1, lesid);
            stm_update_less.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Attendence> getAttendencesByLession(String lesid) {
        ArrayList<Attendence> atts = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "s.img,s.sid,s.sname,a.atdid,a.present,a.description,a.capturedtime\n"
                    + "FROM Student s INNER JOIN Erollment e ON s.sid = e.sid\n"
                    + "               INNER JOIN [group] g ON g.gid = e.gid\n"
                    + "               INNER JOIN Lession les ON les.gid = g.gid\n"
                    + "               LEFT JOIN Attendence a ON les.lesid = a.lesid\n"
                    + "                AND a.sid = s.sid\n"
                    + "WHERE les.lesid = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lesid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendence att = new Attendence();
                Students s = new Students();
                Lession les = new Lession();
                s.setImg(rs.getString("img"));
                s.setSid(rs.getString("sid"));
                s.setSname(rs.getString("sname"));
                att.setStudent(s);

                les.setLesid(lesid);
                att.setLession(les);

                att.setAtdid(rs.getInt("atdid"));
                if (att.getAtdid()!= 0) {
                    att.setDescription(rs.getString("description"));
                    att.setPresent(rs.getBoolean("present"));
                    att.setTime(rs.getTimestamp("capturedtime"));
                }
                atts.add(att);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atts;
    }

    public ArrayList<Lession> getLessionBy(String lid, Date from, Date to) {
        ArrayList<Lession> lessions = new ArrayList<>();
        try {

            String sql = "SELECT \n"
                    + "le.lesid,le.date,le.atd,\n"
                    + "g.gid,g.gname,\n"
                    + "su.suid,su.suname,\n"
                    + "t.tid,t.description,\n"
                    + "r.rid,r.number,\n"
                    + "l.lid,l.lname\n"
                    + "                     FROM Lession le INNER JOIN [Group] g ON le.gid = g.gid\n"
                    + "                    				INNER JOIN TimeSlot t ON t.tid = le.tid\n"
                    + "                   				INNER JOIN Room r ON r.rid = le.rid\n"
                    + "                   				INNER JOIN Lecturer l ON le.lid = l.lid\n"
                    + "                				INNER JOIN [Subject] su ON su.suid = g.suid\n"
                    + "                   WHERE l.lid=? AND le.[date] >= ? AND le.[date] <=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Lession le = new Lession();
                Group g = new Group();
                Subjects sub = new Subjects();
                Lecturer l = new Lecturer();
                Room r = new Room();
                Timeslots slot = new Timeslots();
                le.setLesid(rs.getString("lesid"));
                le.setDate(rs.getDate("date"));
                le.setAtd(rs.getBoolean("atd"));

                g.setGid(rs.getString("gid"));
                g.setGname(rs.getString("gname"));
                sub.setSuid(rs.getString("suid"));
                sub.setSuname(rs.getString("suname"));
                g.setSubjects(sub);
                le.setGroup(g);

                slot.setTid(rs.getString("tid"));
                slot.setDescription(rs.getString("description"));
                le.setSlot(slot);

                r.setRid(rs.getString("rid"));
                r.setNumber(rs.getString("number"));
                le.setRoom(r);

                l.setLid(lid);
                l.setLname(rs.getString("lname"));
                le.setLecturer(l);

                lessions.add(le);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lessions;
    }


}
