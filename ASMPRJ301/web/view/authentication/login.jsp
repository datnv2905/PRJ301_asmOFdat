<%-- 
    Document   : login
    Created on : Mar 13, 2024, 2:01:44 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login" method="POST">
            Username: <input type="text" name="username"/> <br/>
            Password: <input type="password" name="password"/> <br/>
            <input type="checkbox" name="remember"/> Remember me. <br/>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
