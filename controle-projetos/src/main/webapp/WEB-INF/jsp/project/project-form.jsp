<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
      <title>${projectAction}</title>
      <link href="<c:url value="/css/application.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
      <div class="container">
        <c:if test="${addProject}">
            <h2 class="page-title">New Project</h2>
            <c:url var="action_project_url" value="/project/new"/>
        </c:if>
        <c:if test="${updateProject}">
            <h2 class="page-title">Update Project</h2>
            <c:url var="action_project_url" value="/project/${project.id}/edit"/>
        </c:if>

        <form:form action="${action_project_url}" method="post" modelAttribute="project">
            <form:label path="name">Name: </form:label> <form:input type="text" path="name"/>
            <form:label path="startDate">Start Date (dd/mm/yyyy): </form:label> <form:input type="text" path="startDate"/>
            <form:label path="expectedEndDate">Expected end date (dd/mm/yyyy): </form:label> <form:input path="expectedEndDate"/>
            <form:label path="endDate">End date (dd/mm/yyyy): </form:label> <form:input path="endDate"/>
            <form:label path="budget">Budget: </form:label> <form:input path="budget"/>
            <form:label path="description">Description: </form:label> <form:input path="description"/>
            <form:label path="status">Status: </form:label>
            <select name="status">
              <option value="ANALYZING">
                ANALYZING
              </option>
              <option value="PERFORMED_ANALYSIS">
                PERFORMED_ANALYSIS
              </option>
              <option value="ANALYSIS_APPROVED">
                ANALYSIS_APPROVED
              </option>
              <option value="STARTED">
                STARTED
              </option>
              <option value="PLANNED">
                PLANNED
              </option>
              <option value="IN_PROGRESS">
                IN_PROGRESS
              </option>
              <option value="FINISHED">
                FINISHED
              </option>
              <option value="CANCELED">
                CANCELED
              </option>
            </select>
            <form:label path="risk">Risk: </form:label>
            <select name="risk">
              <option value="LOW">
                LOW
              </option>
              <option value="MEDIUM">
                MEDIUM
              </option>
              <option value="HIGH">
                HIGH
              </option>
            </select>
            <form:label path="managerId">Manager: </form:label>
            <select name="managerId">
              <c:forEach items="${eligibleManagers}" var="manager" varStatus="loop">
                <option value="${manager.id}">
                  ${manager.id} - ${manager.name}
                </option>
              </c:forEach>
            </select>

            <input class="btn btn-primary mt-2" type="submit" value="Submit"/>
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
              <input class="btn btn-primary mt-2" type="submit" value="Submit"/>
            </form:form>
        </c:if>

        <a class="btn btn-primary mt-2" href="/project/list">Back to projects</a>
      </div>
      <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>