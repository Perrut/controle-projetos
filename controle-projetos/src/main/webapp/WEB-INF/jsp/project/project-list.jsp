<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Projects</title>
        <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
        <script>
          function confirmAction(event, id) {
            event.preventDefault();
            const remove = confirm('Do you want do delete Project ' + id + '?');
            if (remove) {
              document.getElementById('form-' + id).submit();
            }
          }
        </script>
    </head>
    <body>
        <c:if test="${deleteProjectSuccess}">
            <div>Successfully deleted Project with ID: ${deletedProjectId}</div>
        </c:if>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Risk</th>
                    <th colspan="3">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${projects}" var="project">
                    <c:url var="project_url" value="/project/${project.id}"/>
                    <c:url var="edit_project_url" value="/project/${project.id}/edit"/>
                    <tr>
                        <td>${project.name}</td>
                        <td>${project.status}</td>
                        <td>${project.risk}</td>
                        <td>
                          <a href="${project_url}">View</a>
                        </td>
                        <td>
                          <a href="${edit_project_url}">Edit</a>
                        </td>
                        <td>
                          <a href=""
                            onclick="confirmAction(event, ${project.id})">
                            Delete
                          </a>
                          <form class="delete-form"
                            id="form-${project.id}"
                            action="${project_url}" method="post">
                          </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>