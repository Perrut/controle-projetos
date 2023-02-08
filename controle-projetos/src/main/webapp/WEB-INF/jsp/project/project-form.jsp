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
            <c:url var="action_project_url" value="/project/new"/>
        </c:if>
        <c:if test="${updateProjectSuccess}">
            <div>Successfully updated Project with ID: ${updatedProject.id}</div>
            <c:url var="action_project_url" value="/project/${updatedProject.id}/edit"/>
        </c:if>

        <form:form action="${action_project_url}" method="post" modelAttribute="project">
            <form:label path="name">Name: </form:label> <form:input type="text" path="name"/>
            <form:label path="startDate">Start Date: </form:label> <form:input type="text" path="startDate"/>
            <form:label path="managerId">Manager: </form:label> <form:input path="managerId"/>
            <form:label path="expectedEndDate">Expected end date: </form:label> <form:input path="expectedEndDate"/>
            <form:label path="endDate">End date: </form:label> <form:input path="endDate"/>
            <form:label path="budget">Budget: </form:label> <form:input path="budget"/>
            <form:label path="description">Description: </form:label> <form:input path="description"/>
            <form:label path="status">Status: </form:label> <form:input path="status"/>
            <form:label path="risk">Risk: </form:label> <form:input path="risk"/>
            <input type="submit" value="submit"/>
        </form:form>
    </body>
</html>