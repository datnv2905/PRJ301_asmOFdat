/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.lecturer;

import dal.LessionDBContext;
import dal.TimeSlotDBContext;
import entity.Lession;
import entity.Timeslots;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import util.DateTimeHelper;

/**
 *
 * @author admin
 */
public class TimeTableController extends HttpServlet {
   
  
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        String lid = req.getParameter("lid");
        TimeSlotDBContext timeDB = new TimeSlotDBContext();
        ArrayList<Timeslots> slots = timeDB.list();
        
        
        String raw_from = req.getParameter("from");
        String raw_to = req.getParameter("to");
        Date from = null;
        Date to = null;
        java.util.Date today = new java.util.Date();
        if(raw_from ==null)
        {
           from = DateTimeHelper.convertUtilToSql(DateTimeHelper.getBeginningOfWeek(today));
        }
        else
        {
           from = Date.valueOf(raw_from);
        }
        
        if(raw_to == null)
        {
           java.util.Date beginWeek = DateTimeHelper.getBeginningOfWeek(today);
           to= DateTimeHelper.convertUtilToSql(DateTimeHelper.addDaysToDate(beginWeek, 6));
        }
        else
        {
            to = Date.valueOf(raw_to);
        }
        
        LessionDBContext lessDB = new LessionDBContext();
        ArrayList<Lession> lessions = lessDB.getLessionBy(lid, from, to);
        
        req.setAttribute("dates", DateTimeHelper.toList(from, to));
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("slots", slots);
        req.setAttribute("lessions", lessions);
        req.getRequestDispatcher("../view/lecturer/timetable.jsp").forward(req, resp);
    }
     
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
