<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error</title>
    </head>
    <body>
        <p>Project ${project.id} cannot be deleted, because its status is ${project.status}</p>
    </body>
</html>