<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>${projectAction}</title>
    </head>
    <body>
        <c:if test="${addProjectSuccess}">
            <div>Successfully added Project with ID: ${savedProject.id}</div>
        </c:if>

        <c:url var="add_project_url" value="/project/new"/>
        <form:form action="${add_project_url}" method="post" modelAttribute="project">
            <form:label path="name">Name: </form:label> <form:input type="text" path="name"/>
            <form:label path="startDate">Start Date: </form:label> <form:input type="text" path="startDate"/>
            <form:label path="expectedEndDate">Expected end date: </form:label> <form:input path="expectedEndDate"/>
            <form:label path="endDate">End date: </form:label> <form:input path="endDate"/>
            <form:label path="description">Description: </form:label> <form:input path="description"/>
            <form:label path="budget">Budget: </form:label> <form:input path="budget"/>
            <form:label path="risk">Risk: </form:label> <form:input path="risk"/>
            <form:label path="managerId">Manager: </form:label> <form:input path="managerId"/>
            <form:label path="status">Status: </form:label> <form:input path="status"/>
            <input type="submit" value="submit"/>
        </form:form>
    </body>
</html>