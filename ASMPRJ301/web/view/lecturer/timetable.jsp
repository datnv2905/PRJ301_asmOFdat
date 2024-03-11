<%-- 
    Document   : timetable
    Created on : Mar 11, 2024, 2:41:10 AM
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
        <h1>Hello World!</h1>
        <form action="timetable" method="GET">
            <input type="hidden" name="lid" value="${param.lid}"/>
            Period: <input type="date" value="${requestScope.from}" name="from"/> - <input value="${requestScope.to}" type="date" name="to"/> 
        <input type="submit" value="Show"/>
        </form>
        <table border="1px">
            <tr>
                <td> </td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <th>${d}</th>
                </c:forEach>
            </tr>
            <c:forEach items="${requestScope.slots}" var="slot">
              <tr>
                <th>${slot.tid}</th>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>
                        <c:forEach items="${requestScope.lessions}" var="les">
                            <c:if test="${d eq les.date and les.slot.tid eq slot.tid}">
                                ${les.group.gname} - ${les.group.subjects.suid}  <br>
                                at ${les.room.number}
                                
                                <a href="">
                                    Take
                                </a>
                            </c:if>
                        </c:forEach>
                    </td>
                </c:forEach>
            </tr>  
            </c:forEach>
            
        </table>  
            
            
        </form>
        
    </body>
</html>
