<%--
  Created by IntelliJ IDEA.
  User: mike
  Date: 2/13/25
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.rca.mis.onlinesubmissionmis.models.Assignment" %>
<%@ page import="com.rca.mis.onlinesubmissionmis.models.Notification" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    HttpSession sessionUser = request.getSession(false);
    if (sessionUser == null || sessionUser.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    // Get assignments safely
    List<?> assignmentsRaw = (List<?>) request.getAttribute("assignments");
    List<Assignment> assignments = new ArrayList<>();
    if (assignmentsRaw != null) {
        for (Object obj : assignmentsRaw) {
            if (obj instanceof Assignment) {
                assignments.add((Assignment) obj);
            }
        }
    }

    // Get notifications safely
    List<?> notificationsRaw = (List<?>) request.getAttribute("notifications");
    List<Notification> notifications = new ArrayList<>();
    if (notificationsRaw != null) {
        for (Object obj : notificationsRaw) {
            if (obj instanceof Notification) {
                notifications.add((Notification) obj);
            }
        }
    }
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<nav>
    <h2>Student Dashboard</h2>
    <a href="logout">Logout</a>
</nav>

<div class="container">
    <section class="assignments">
        <h3>📚 Upcoming Assignments</h3>
        <% if (!assignments.isEmpty()) { %>
        <ul>
            <% for (Assignment a : assignments) { %>
            <li>
                <strong><%= a.getTitle() %></strong> - Due: <%= a.getDueDate() %>
            </li>
            <% } %>
        </ul>
        <% } else { %>
        <p>No upcoming assignments.</p>
        <% } %>
    </section>

    <section class="notifications">
        <h3>🔔 Notifications</h3>
        <% if (!notifications.isEmpty()) { %>
        <ul>
            <% for (Notification n : notifications) { %>
            <li><%= n.getMessage() %> - <em><%= n.getDate() %></em></li>
            <% } %>
        </ul>
        <% } else { %>
        <p>No notifications.</p>
        <% } %>
    </section>
</div>
</body>
</html>
