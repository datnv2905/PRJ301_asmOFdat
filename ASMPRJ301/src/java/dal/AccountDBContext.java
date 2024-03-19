/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnt
 */
public class AccountDBContext extends DBContext<Account> {

    public Account getAccountByUsernamePassword(String username, String password) {
        try {
            String sql = "SELECT username,[password],displayname,id FROM Account \n"
                    + "WHERE username = ? AND password = ?";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
            {
                Account account = new Account();
                account.setUsername(username);
                account.setPassword(password);
                account.setDisplayname(rs.getString("displayname"));
                account.setId(rs.getString("id"));
                return account;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   
}
