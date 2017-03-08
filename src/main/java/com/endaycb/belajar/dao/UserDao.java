/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.endaycb.belajar.dao;

import com.endaycb.belajar.config.Conn;
import com.endaycb.belajar.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mashiro
 */
public class UserDao {

    private Connection connection;

    public UserDao() {
        Conn conn = new Conn();
        connection = conn.Conn();
    }

    public List<User> getAll() {
        String readAll = "SELECT * FROM user";
        
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
                
                listUser.add(user);
            }
            
        } catch (SQLException ex) {
             ex.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }

        }

        return listUser;
    }

    public User getById(Integer id) {
        String readById = "SELECT * FROM user WHERE id = ?";
        
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
            }

        } catch (SQLException ex) {
             ex.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }

        }

        return user;
    }

    public void insert(User user){
        String save = "INSERT INTO user (name, username, password) VALUES (?, ?, ?)";
        
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(save);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            if(ps != null) try {
                ps.close();
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
        }
    }
    
    public void update(User user){
        String update = "UPDATE user SET name = ?, username = ?, password = ? WHERE id = ?";
        
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
                ex.printStackTrace();
            }
        }
    }
    
    public void delete(Integer id) {
        String delete = "DELETE FROM user WHERE id = ?";
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(delete);
            ps.setInt(1, id);
            
            ps.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        } finally{
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public String login(String username, String password){
        String login = "SELECT * FROM user WHERE username = ? AND password = ?";
        String message =  null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(login);
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next() && username.equals(rs.getString("username")) && password.equals(rs.getString("password"))){
                message = "login sukses";
            }else{
                message = "login gagal";
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return message;
    }
}
