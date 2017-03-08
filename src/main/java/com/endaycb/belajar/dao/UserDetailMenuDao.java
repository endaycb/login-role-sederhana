/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.endaycb.belajar.dao;

import com.endaycb.belajar.config.Conn;
import com.endaycb.belajar.domain.User;
import com.endaycb.belajar.domain.UserDetailMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mashiro
 */
public class UserDetailMenuDao {

    private Connection connection;

    final private String readAll = "SELECT * FROM user";
    final private String readById = "SELECT * FROM user WHERE id = ?";
    
    final private String save = "INSERT INTO user_detail_menu (id_user, menu, is_allowed) VALUES (?, ?, ?)";
    
    final private String update = "UPDATE user_detail_menu SET is_allowed = ? WHERE id = ?";
    
    final private String delete = "DELETE FROM user_detail_menu WHERE id = ?";
    
    public UserDetailMenuDao() {
        Conn conn = new Conn();
        connection = conn.Conn();
    }

    public List<UserDetailMenu> getAll() {
        List<UserDetailMenu> list = null;
        
        Statement st = null;
        try {
            list = new ArrayList<UserDetailMenu>();
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(readAll);

            while (rs.next()) {
                UserDetailMenu udm = new UserDetailMenu();
                udm.setId(rs.getInt("id"));
                udm.setId_user(rs.getInt("id_user"));
                udm.setMenu(rs.getInt("menu"));
                udm.setIsAllowed(rs.getInt("id"));
                
                list.add(udm);
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

        return list;
    }

    public UserDetailMenu getById(Integer id) {
        UserDetailMenu udm = null;
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(readById);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                udm = new UserDetailMenu();
                udm.setId(rs.getInt("id"));
                udm.setId_user(rs.getInt("id_user"));
                udm.setMenu(rs.getInt("menu"));
                udm.setIsAllowed(rs.getInt("id"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

        return udm;
    }

    public void update(User user) {
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(update);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            
            ps.executeUpdate();
            
            
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        } finally{
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void delete(Integer id) {
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(update);
            ps.setInt(1, id);
            
            ps.executeUpdate();
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        } finally{
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
