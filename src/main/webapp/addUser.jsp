<%-- 
    Document   : addUser
    Created on : Mar 8, 2017, 7:24:42 PM
    Author     : MASHIRO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
    </head>
    <body>
        <h1>Add User</h1>
        <form method="POST" action="user?go=insert">
            <table>
                <tr>
                    <td>nama</td>
                    <td><input type="text" name="name" placeholder="name"/></td>
                </tr>
                <tr>
                    <td>username</td>
                    <td><input type="text" name="username" placeholder="username"/></td>
                </tr>
                <tr>
                    <td>password</td>
                    <td><input type="passowrd" name="password" placeholder="password"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="save">
                        <input type="reset" value="reset">
                    </td>
                </tr>
            </table>
            <a href="user">Cancel</a>
        </form>
    </body>
</html>
