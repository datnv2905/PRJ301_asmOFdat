/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import dal.GroupDBContext;
import dal.StudentDBContext;
import dal.SubjectDBContext;
import entity.Group;
import entity.Students;
import entity.Subjects;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class StudentclassController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String suid = req.getParameter("suid");
        String gname = req.getParameter("gname");

        GroupDBContext grtable = new GroupDBContext();
        ArrayList<Group> group = grtable.list();

        SubjectDBContext sutable = new SubjectDBContext();
        ArrayList<Subjects> subject = sutable.list();

        StudentDBContext students = new StudentDBContext();
        ArrayList<Students> student = students.getStudentByClass(suid, gname);

        req.setAttribute("group", group);
        req.setAttribute("subject", subject);
        req.setAttribute("student", student);
        req.setAttribute("suid", suid);
        req.setAttribute("gname", gname);
        req.getRequestDispatcher("../view/student/viewclass.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
