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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

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
        Boolean loggedInUser = (Boolean) req.getSession().getAttribute("loggedInUser");
        try {
            if (loggedInUser != null) {
                urlRedirect = urlHome;
            } else {
                String username = req.getParameter("username");
                String password = req.getParameter("password");

                if (userDao.login(username, password) == "login sukses") {
                    req.getSession(true).setAttribute("loggedIn", true);

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
        Boolean loggedInUser = (Boolean) req.getSession().getAttribute("loggedIn");
        String go = req.getParameter("go");

        if (loggedInUser == null) {
            login(req, res);
        } else if (go != null) {
            if(go.equalsIgnoreCase("addUser")){
                urlRedirect = urlAddUser;
            } else if(go.equalsIgnoreCase("insert")){
                User user = new User();
                user.setName(req.getParameter("name"));
                user.setUsername(req.getParameter("username"));
                user.setPassword(req.getParameter("password"));
                
                userDao.insert(user);
                urlRedirect = urlUsers;
            }else if (go.equalsIgnoreCase("delete")){
                userDao.delete(Integer.parseInt(req.getParameter("id")));
            }else if(go.equalsIgnoreCase("getDetail")){
                
                User user = userDao.getById(Integer.parseInt(req.getParameter("idUser")));
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("id", user.getId());
                jsonObj.put("name", user.getName());
                jsonObj.put("username", user.getUsername());
                jsonObj.put("password", user.getPassword());
                 
                  
                res.setContentType("application/json");
                res.getWriter().write(jsonObj.toString());
                return;
                
            }else if(go.equalsIgnoreCase("update")){
                User user = new User();
                user.setId(Integer.parseInt(req.getParameter("id")));
                user.setName(req.getParameter("name"));
                user.setUsername(req.getParameter("username"));
                user.setPassword(req.getParameter("password"));
                
                userDao.update(user);
                
                urlRedirect = urlUsers;
            
            }else if(go.equalsIgnoreCase("logout")){
                req.getSession().removeAttribute("loggedIn");
                res.sendRedirect("");
                return;
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
