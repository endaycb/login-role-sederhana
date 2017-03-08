<%-- 
    Document   : userlist
    Created on : Mar 8, 2017, 6:46:32 PM
    Author     : MASHIRO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>user</title>
    </head>
    <body>
        <h1>Halo <c:out value="${loggedInUser.username}"/></h1>
        <form method="POST" action="user?go=update">
            <table>
                <tr>
                    <td>nama</td>
                    <td><input type="text" name="name" placeholder="name" id="formName"/></td>
                </tr>
                <tr>
                    <td>username</td>
                    <td><input type="text" name="username" placeholder="username" id="formUsername"/></td>
                </tr>
                <tr>
                    <td>password</td>
                    <td><input type="password" name="password" placeholder="password" id="formPassword"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="update">
                        <input type="reset" value="reset">
                        <input type="hidden" name="id" value="" id="formId" >
                    </td>
                </tr>
            </table>
        </form>
        <hr/>
        <a href="user?go=addUser">Add</a> | <a href="user?go=logout">Logout</a>
        <table border="1">
            <tr>
                <td>nama</td>
                <td>username</td>
                <td>password</td>
                <td>action</td>
            </tr>
            <c:forEach items="${listUser}" var="user">
                <tr>
                    <td><c:out value='${user.name}' /></td>
                    <td><c:out value="${user.username}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td><span class="edit" id="<c:out value='${user.id}'/>">Edit</span> | <a href="user?go=delete&id=<c:out value='${user.id}'/>">Delete</a></td>

                </tr> 
            </c:forEach>

        </table>
    </body>
    <script src="http://code.jquery.com/jquery-latest.js">
    </script>
    <script>
        $(document).ready(function () {
            $(".edit").click(function (event){
                $.ajax({
                    url: "user?go=getDetail&idUser="+this.id,
                    type: "GET",
                    error: function(x){
                      alert("a");  
                    },
                    success: function(data){
                       $("#formName").val(data["name"]);
                       $("#formUsername").val(data["username"]);
                       $("#formPassword").val(data["password"]);
                       $("#formId").val(data["id"]);
                    }
                    
                });
            });
            /*$('#submit').click(function (event) {
                var username = $('#user').val();
                $.get('ActionServlet', {user: username}, function (responseText) {
                    $('#welcometext').text(responseText);
                });
            });*/
        });
    </script>
</html>