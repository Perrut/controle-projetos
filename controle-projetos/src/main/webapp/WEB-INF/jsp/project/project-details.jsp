<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Project ${project.id}</title>
    </head>
    <body>
        <p>${project.name}</p>
        <p>${project.startDate}</p>
        <p>${project.expectedEndDate}</p>
        <p>${project.endDate}</p>
        <p>${project.description}</p>
        <p>${project.budget}</p>
        <p>${project.risk}</p>
        <p>${project.manager.name}</p>
        <p>${project.status}</p>

        <p>Project Members</p>
        <p>${projectMember.id} ${projectMember.name}</p>
    </body>
</html>