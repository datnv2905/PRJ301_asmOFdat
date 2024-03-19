/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author sonnt
 */
public abstract class BaseRequiredAuthenticationController extends HttpServlet {
    
    private Account getAuthentication(HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        Account account = (Account)session.getAttribute("account");
        return account ;
    }
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthentication(req);
        if(account!=null)
        {
            doPost(req, resp, account);
        }
        else
        {
            resp.getWriter().println("access denied!");
        }
    }
    
    
    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp,Account account) 
            throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Account account = getAuthentication(req);
        if(account!=null)
        {
            doGet(req, resp, account);
        }
        else
        {
            resp.getWriter().println("access denied!");
        }
    
    }
    
    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp,Account account) 
            throws ServletException, IOException;
    
}
