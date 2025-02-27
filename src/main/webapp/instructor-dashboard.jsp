        <%--
  Created by IntelliJ IDEA.
  User: mike
  Date: 2/14/25
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.rca.mis.onlinesubmissionmis.models.User" %>
<%@ page import="com.rca.mis.onlinesubmissionmis.models.Class" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.persistence.EntityManagerFactory" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="com.rca.mis.onlinesubmissionmis.models.Course" %>
<%@ page import="com.rca.mis.onlinesubmissionmis.models.Assignment" %>
<%
    User user = (User) session.getAttribute("user");
    if (!(user instanceof com.rca.mis.onlinesubmissionmis.models.Instructor)) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Instructor Dashboard</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h2>Welcome, <%=user.getFirstName()%> (Instructor)</h2>

<nav>
    <ul>
<%--        <li><a href="manage-assignments.jsp">Manage Assignments</a></li>--%>
<%--        <li><a href="view-submissions.jsp">View Student Submissions</a></li>--%>
        <li><a href="logout">Logout</a></li>
    </ul>
</nav>

<p>This is your instructor dashboard where you can manage assignments and track student progress.</p>

<h2>Assignments</h2>
<table>
    <tr>
        <th>Title</th>
        <th>Class</th>
        <th>Course</th>
        <th>Due Date</th>
    </tr>
    <%
        EntityManagerFactory emf = (EntityManagerFactory) application.getAttribute("emf");
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory is not initialized.");
        }
        EntityManager em = emf.createEntityManager();
        List<Assignment> assignments = em.createQuery("SELECT a FROM Assignment a WHERE a.instructor = :instructor", Assignment.class)
                .setParameter("instructor", user)
                .getResultList();
        for (Assignment a : assignments) {
    %>
    <tr>
        <td><%=a.getTitle()%></td>
        <td><%=a.getAssignedClass().getName()%></td>
        <td><%=a.getCourse().getName()%></td>
        <td><%=a.getDueDate()%></td>
    </tr>
    <%
        }
        em.close();
    %>
</table>


<form action="create-assignment" method="post">
    <label>Title:</label>
    <input type="text" name="title" required>

    <label>Description:</label>
    <textarea name="description" required></textarea>

    <label>Due Date:</label>
    <input type="datetime-local" name="dueDate" required>

    <label>Allowed File Types:</label>
    <input type="text" name="allowedFileTypes" placeholder="e.g., pdf, docx, zip" required>

    <label>Class:</label>
    <select name="classId">
        <%-- Fetch classes dynamically --%>
            <%
                emf = (EntityManagerFactory) application.getAttribute("emf");
                em = emf.createEntityManager();
                List<Class> classes = em.createQuery("SELECT c FROM Class c", Class.class).getResultList();
                for (Class c : classes) {
            %>
            <option value="<%=c.getId()%>"><%=c.getName()%></option>
            <%
                }
                em.close();
            %>

    </select>

    <label>Course:</label>
    <select name="courseId">
        <%-- Fetch courses dynamically --%>
            <%
                em = emf.createEntityManager();
                List<Course> courses = em.createQuery("SELECT co FROM Course co", Course.class).getResultList();
                for (Course co : courses) {
            %>
            <option value="<%=co.getId()%>"><%=co.getName()%></option>
            <%
                }
                em.close();
            %>

    </select>

    <button type="submit">Create Assignment</button>
</form>
</body>
</html>

