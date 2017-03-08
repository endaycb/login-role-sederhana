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
public class UserDao {

    private Connection connection;

    final private String readAll = "SELECT * FROM user";
    final private String readAllUserMenu = "SELECT * FROM user_detail_menu INNER JOIN user ON user_detail_menu.id_user = user.id WHERE id = ?";
    final private String readById = "SELECT * FROM user WHERE id = ?";
    
    final private String save = "INSERT INTO user (nama, username, password) VALUES (?, ?, ?)";
    final private String saveUserMenu = "INSERT INTO menu (id_user, menu, is_allowed) VALUES (?, ?, ?)";
    
    final private String update = "UPDATE user SET name = ?, username = ?, password = ? WHERE id = ?";
    final private String updateUserMenu = "UPDATE user_detail_menu SET is_allowed = ? WHERE id_user = ? AND menu = ?";
    
    final private String delete = "DELETE FROM user WHERE id = ?";
    final private String deleteUserMenu = "DELETE FROM user_detail_menu WHERE id_user = ?";

    public UserDao() {
        Conn conn = new Conn();
        connection = conn.Conn();
    }

    public List<User> getAll() {
        List<User> listUser = null;
        
        Statement st = null;
        try {
            listUser = new ArrayList<User>();
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(readAll);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                
                Statement st2 = connection.createStatement();
                ResultSet rs2 = st2.executeQuery(readAllUserMenu);
                
                
                listUser.add(user);
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

        return listUser;
    }

    public User getById(Integer id) {
        User user = null;
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(readById);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setId(id);
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                
                Statement st2 = connection.createStatement();
                ResultSet rs2 = st2.executeQuery(readAllUserMenu);
                
                
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

        return user;
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
