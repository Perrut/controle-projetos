<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>${projectAction}</title>
    </head>
    <body>
        <c:if test="${addProject}">
            <c:url var="action_project_url" value="/project/new"/>
        </c:if>
        <c:if test="${updateProject}">
            <c:url var="action_project_url" value="/project/${project.id}/edit"/>
        </c:if>

        <form:form action="${action_project_url}" method="post" modelAttribute="project">
            <form:label path="name">Name: </form:label> <form:input type="text" path="name"/>
            <form:label path="startDate">Start Date (dd/mm/yyyy): </form:label> <form:input type="text" path="startDate"/>
            <form:label path="expectedEndDate">Expected end date (dd/mm/yyyy): </form:label> <form:input path="expectedEndDate"/>
            <form:label path="endDate">End date (dd/mm/yyyy): </form:label> <form:input path="endDate"/>
            <form:label path="budget">Budget: </form:label> <form:input path="budget"/>
            <form:label path="description">Description: </form:label> <form:input path="description"/>
            <form:label path="status">Status: </form:label> <form:input path="status"/>
            <form:label path="risk">Risk: </form:label> <form:input path="risk"/>
            <form:label path="managerId">Manager: </form:label>
            <select name="managerId">
              <c:forEach items="${eligibleManagers}" var="manager" varStatus="loop">
                <option value="${manager.id}">
                  ${manager.id} - ${manager.name}
                </option>
              </c:forEach>
            </select>

            <input type="submit" value="submit"/>
        </form:form>

        <c:if test="${updateProject}">
            <p>Project Members</p>
            <p>${projectMember.id} ${projectMember.name}</p>
            <c:url var="add_member_to_project_url" value="/project/${project.id}/membership"/>
            <form:form action="${add_member_to_project_url}" method="post" modelAttribute="member">
              <input type="hidden" name="projectId" value="${project.id}" />
              <select name="personId">
                <c:forEach items="${employees}" var="employee" varStatus="loop">
                  <option value="${employee.id}">
                    ${employee.id} - ${employee.name}
                  </option>
                </c:forEach>
              </select>
              <input type="submit" value="submit"/>
            </form:form>
        </c:if>
    </body>
</html>