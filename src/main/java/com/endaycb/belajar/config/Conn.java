/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.endaycb.belajar.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mashiro
 */
public class Conn {

    private Connection connection;
    final private String driver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost/login_sederhana";
    final private String user = "root";
    final private String password = "root";

    public Connection Conn() {
        try {
            if (connection != null) {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("connected");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return connection;
    }
}
