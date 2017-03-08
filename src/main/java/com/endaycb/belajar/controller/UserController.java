/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.endaycb.belajar.controller;

import com.endaycb.belajar.dao.UserDao;
import com.endaycb.belajar.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MASHIRO
 */
public class UserController extends HttpServlet {

    UserDao userDao = new UserDao();
    RequestDispatcher rd;

    private String urlRedirect = "";
    final private String urlInsert = "/insert.jsp";
    final private String urlLogin = "/login.jsp";
    final private String urlEdit = "/edit.jsp";
    final private String urlHome = "/home.jsp";
    final private String urlUsers = "/users.jsp";
    final private String urlAddUser = "/addUser.jsp";

    public UserController() {
        super();
    }

    public String login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");
        try {
            if (loggedInUser != null) {
                urlRedirect = urlHome;
            } else {
                String username = req.getParameter("username");
                String password = req.getParameter("password");

                if (userDao.login(username, password) == "login sukses") {
                    loggedInUser = new User();
                    loggedInUser.setUsername(username);
                    loggedInUser.setPassword(password);

                    req.getSession(true).setAttribute("loggedIn", true);
                    req.getSession(true).setAttribute("loggedInUser", loggedInUser);

                    urlRedirect = urlHome;
                } else {
                    urlRedirect = urlLogin;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return urlRedirect;
    }

    public void processReqRes(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");
        String go = req.getParameter("go");

        if (loggedInUser == null) {
            login(req, res);
            System.out.println("a");
        } else if (go != null) {
            if (go.equalsIgnoreCase("users")) {
                System.out.println("users");
                urlRedirect = urlUsers;
            } else if (go.equalsIgnoreCase("delete")){
                userDao.delete(Integer.parseInt(req.getParameter("id")));
            } else if(go.equalsIgnoreCase("addUser")){
                urlRedirect = urlAddUser;
            } else if(go.equalsIgnoreCase("insert")){
                User user = new User();
                user.setName(req.getParameter("name"));
                user.setUsername(req.getParameter("username"));
                user.setPassword(req.getParameter("password"));
                
                userDao.insert(user);
                urlRedirect = urlUsers;
            }else{
                urlRedirect = urlUsers;
            }
        } else {
            urlRedirect = urlUsers;
        }
        
        
        if(urlRedirect == urlUsers){
            req.setAttribute("listUser", userDao.getAll());
        }
        rd = req.getRequestDispatcher(urlRedirect);
        rd.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processReqRes(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processReqRes(req, res);
    }

}
