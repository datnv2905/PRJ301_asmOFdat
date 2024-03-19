/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.student;

import controller.authentication.authorization.BaseRBACController;
import dal.GradeDBContext;
import dal.SubjectDBContext;
import entity.Account;
import entity.Grade;
import entity.Role;
import entity.Subjects;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class StudentGradeController extends BaseRBACController {
   
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles)
    throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String suid = request.getParameter("suid");
        
        SubjectDBContext sutable = new SubjectDBContext();
        ArrayList<Subjects> subject = sutable.list();
        
        GradeDBContext gratable = new GradeDBContext();
        ArrayList<Grade> grade = gratable.getGradeBySid(sid, suid);
        float getavg = gratable.getAvgGrade(sid, suid);
        
        request.setAttribute("suid", suid);
        request.setAttribute("grade", grade);
        request.setAttribute("subject", subject);
        request.setAttribute("getavg", getavg);
        request.getRequestDispatcher("../view/student/viewgrade.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account, ArrayList<Role> roles)
    throws ServletException, IOException {
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
