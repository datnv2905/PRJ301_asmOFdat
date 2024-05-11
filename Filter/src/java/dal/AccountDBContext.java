/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

/**
 *
 * @author admin
 */
public class AccountDBContext extends DBContext {

    public ArrayList<Account> getACC(String username, String password) {
        ArrayList<Account> acc = new ArrayList<>();
        try {

        String sql = "SELECT  [username]\n"
                + "      ,[password]\n"
                + "  FROM [test4].[dbo].[Account]"
                + "WHERE username = ? AND password = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2,password );
            ResultSet rs = stm.executeQuery();
        if(rs.next())
            {
                Account account = new Account();
                account.setUsername(username);
                account.setPassword(password);
                return acc;
            }
                    
        } catch (Exception e) {
        }
        return null;
    }


}
