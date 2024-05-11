<%-- 
    Document   : viewgrade
    Created on : Mar 14, 2024, 1:48:46 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="grade" method="GET">
            <input type="hidden" name="sid" value="${param.sid}">
            Subject 
            <select name="suid">
                <c:forEach items="${requestScope.subject}" var="sub">
                    
                    <option <c:if test="${sub.suid==suid}">selected="suid""</c:if> value="${sub.suid}">
                        ${sub.suname} &ensp; (${sub.suid})
                    </option>
                    </c:forEach>
            </select>
            <br><br>
            <input type="submit" value="Show Grade">
        </form>
        <table border="1px">
            <tr>
                <th>GRADE CATEGORY</th>
                <th>GRADE ITEM</th>
                <th>WEIGHT</th>
                <th>VALUE </th>
                <th>COMMENT </th>
            </tr>
            
            <c:forEach items="${requestScope.grade}" var="g">
            <tr>
                <td>${g.exam.assessment.name} </td>
                <td>
                    ${g.exam.assessment.name} 
                    <hr><!-- comment -->
                    Total
                </td>
                <td>
                    ${g.exam.assessment.weght} %
                    <hr><!-- comment -->
                    ${g.exam.assessment.weght} % 
                </td>
                <td>
                    ${g.score} 
                    <hr><!-- comment -->
                    ${g.score} 
                </td>
                <td>
                    ${g.comments} 
                    <hr><!-- comment -->
                </td>
            </tr>
            </c:forEach>
        </table>
            <h1>COURSE TOTAL: </h1>
            <h1>${requestScope.getavg} </h1>
            <h1 style="${(requestScope.getavg > 5) ? '' : 'color: red;'}">${(requestScope.getavg>5)?"PASSED" :"NOT PASS"} </h1>
    </body>
</html>
