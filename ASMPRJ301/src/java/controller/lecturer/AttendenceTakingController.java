 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.lecturer;

import controller.authentication.authorization.BaseRBACController;
import dal.LessionDBContext;
import dal.StudentDBContext;
import entity.Account;
import entity.Attendence;
import entity.Lession;
import entity.Role;
import entity.Students;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
@WebServlet(name="AttendenceTakingController", urlPatterns={"/lecturer/att"})
public class AttendenceTakingController extends BaseRBACController {
   
   

  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles)
    throws ServletException, IOException {
      String leid = req.getParameter("lesid"); 
        LessionDBContext lesDB = new LessionDBContext();
        ArrayList<Attendence> atts = lesDB.getAttendencesByLession(leid);
        req.setAttribute("atts", atts);
        req.getRequestDispatcher("../view/lecturer/att.jsp").forward(req, resp);

    } 

   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles)
    throws ServletException, IOException {
        String lesid = req.getParameter("lesid");
        StudentDBContext db = new StudentDBContext();
        ArrayList<Students> students = db.getStudentsByLessionId(lesid);
        Lession lession = new Lession();
        lession.setLesid(lesid);
        ArrayList<Attendence> atts = new ArrayList<>();
        for (Students student : students) {
            Attendence att = new Attendence();
            att.setStudent(student);
            att.setLession(lession);
            att.setDescription(req.getParameter("description" + student.getSid()));
            att.setPresent(req.getParameter("present" + student.getSid()).equals("yes"));           
            atts.add(att);
        }
        String atd = req.getParameter("atd");
        LessionDBContext lesDB = new LessionDBContext();
        
        if(atd.equals("true")){
            lesDB.takeAttendenceAfter(lesid, atts);
        }else{
            lesDB.takeAttendance(lesid, atts);
        }
        resp.sendRedirect("att?lesid=" + lesid + "&atd="+atd);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

  
  

}
