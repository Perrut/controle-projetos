<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Projects</title>
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
        <c:if test="${addProjectSuccess}">
            <div>Successfully added Project with ID: ${savedProject.id}</div>
        </c:if>
        <c:if test="${updateProjectSuccess}">
            <div>Successfully updated Project with ID: ${updatedProject.id}</div>
        </c:if>
        <c:if test="${deleteProjectSuccess}">
            <div>Successfully deleted Project with ID: ${deletedProjectId}</div>
        </c:if>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
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
                        <td>${project.id}</td>
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
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>