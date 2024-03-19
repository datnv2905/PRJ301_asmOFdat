
package controller.authentication;

import dal.AccountDBContext;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class LoginController extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("view/authentication/login.jsp").forward(request, response);
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        AccountDBContext db = new AccountDBContext();
        Account account = db.getAccountByUsernamePassword(username, password);
        if(account != null)
        {
            HttpSession session = request.getSession();
            
            //implement remember me!
            Cookie c_username = new Cookie("username", username);
            c_username.setMaxAge(3600*24*7);
            
            Cookie c_password = new Cookie("password", password);
            c_password.setMaxAge(3600*24*7);
            
            response.addCookie(c_username);
            response.addCookie(c_password);
            
            
            session.setAttribute("account", account);
            
            request.getRequestDispatcher("home").forward(request, response);
        }
        else
        {
            response.getWriter().println("login failed!");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
