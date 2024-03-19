/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Role;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnt
 */
public class RoleDBContext extends DBContext<Role> {

    public ArrayList<Role> getByUsernameAndUrl(String username, String url) {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT r.roleid,r.rolename FROM Account a \n"
                    + "	INNER JOIN [Role_Account] ra ON ra.username = a.username\n"
                    + "	INNER JOIN [Role] r ON r.roleid = ra.roleid\n"
                    + "	INNER JOIN [Role_Feature] rf ON rf.roleid = r.roleid\n"
                    + "	INNER JOIN [Feature] f ON f.fid = rf.fid \n"
                    + "WHERE\n"
                    + "a.username = ? AND f.url = ?";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Role r = new Role();
                r.setRoleid(rs.getInt("roleid"));
                r.setRolename(rs.getString("rolename"));
                roles.add(r);
                        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RoleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }

   
}
