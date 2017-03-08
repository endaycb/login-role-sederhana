<%-- 
    Document   : login
    Created on : Mar 8, 2017, 12:53:18 PM
    Author     : MASHIRO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <form method="post" action="user?go=login">
           <input type="text" placeholder="username" name="username"/>
           <input type="password" placeholder="password" name="password"/>
           <input type="hidden" name="act" value="login">
           <input type="submit" value="login"/> 
        </form>
        
    </body>
</html>
