<%-- 
    Document   : viewclass
    Created on : Mar 13, 2024, 3:44:41 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <c:set var="suid" value="${requestScope.suid}"/> 
        <c:set var="gname" value="${requestScope.gname}"/> 
        <form action="class" method="GET">
            Please choose subject
            <select name="suid">
                <c:forEach items="${requestScope.subject}" var="sub">
                    <option <c:if test="${sub.suid==suid}">selected="suid""</c:if> value="${sub.suid}">
                        ${sub.suname}
                    </option>
                    </c:forEach>
            </select>
            &emsp;
            Please choose class
            <select name="gname">
                 <c:forEach items="${requestScope.group}" var="g">
                     <option <c:if test="${g.gname==gname}">selected="gname""</c:if>  value="${g.gname}">
                        ${g.gname}
                    </option>
                    </c:forEach>
            </select>
            &emsp;
            <input type="submit" value="show">
        </form>
        <hr>
        <table border="1px">
            <tr>
                <th>Mssv</th>
                <th>Full name</th>
                <th>Image</th>
                <th>Email</th>
            </tr>
            
            <c:forEach items="${requestScope.student}" var="s">
                <tr>
                    <td>${s.sid}</td>
                    <td>${s.sname}</td>
                    <td><img width="70" src="../img_asm/${s.img}" alt="img"/> </td>
                    <td>${s.email}</td>
                </tr>
            </c:forEach>
        </table>
            
    </body>
</html>
