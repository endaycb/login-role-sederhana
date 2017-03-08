<%-- 
    Document   : home
    Created on : Mar 8, 2017, 1:55:45 PM
    Author     : MASHIRO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<c:choose>
    <c:when  test="${loggedIn != true}">
        <jsp:forward page="/user?go=login" />
    </c:when>
    <c:otherwise>
        <jsp:forward page="/user"/>
    </c:otherwise>
</c:choose>

