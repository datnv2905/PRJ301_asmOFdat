<%-- 
    Document   : att
    Created on : Mar 11, 2024, 11:02:29 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="att" method="POST">
            <input type="hidden" name="atd" value="${param.atd}"/>
            <input type="hidden" name="lesid" value="${param.lesid}"/>
            <table border="1px">
                <tr>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Presented</td>
                    <td>Description</td>
                    <td>Time</td>
                </tr>
                <c:forEach items="${requestScope.atts}" var="a">
                 <tr>
                    <td>${a.student.sid}</td>
                    <td>${a.student.sname}</td>
                    <td>
                        <input 
                               ${!a.present?"checked=\"checked\"":""}
                               type="radio" value="no" name="present${a.student.sid}"/> No
                        <input type="radio"
                               ${a.present?"checked=\"checked\"":""}
                               value="yes" name="present${a.student.sid}" /> Yes
                    </td>
                    <td>
                        <input name="description${a.student.sid}" type="text" value="${a.description}"/>
                    </td>
                    <td>${a.time}</td>
                </tr>   
                </c:forEach>
            </table>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
