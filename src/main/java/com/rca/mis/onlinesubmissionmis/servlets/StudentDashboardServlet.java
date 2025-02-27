package com.rca.mis.onlinesubmissionmis.servlets;

import com.rca.mis.onlinesubmissionmis.models.Assignment;
import com.rca.mis.onlinesubmissionmis.models.Notification;
import com.rca.mis.onlinesubmissionmis.models.Student;
import com.rca.mis.onlinesubmissionmis.models.Submission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/student-dashboard")
public class StudentDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (EntityManager em = Persistence.createEntityManagerFactory("OnlineSubmissionMIS").createEntityManager()) {
            // Get student info from session
            Student student = (Student) session.getAttribute("user");

            TypedQuery<Assignment> assignmentQuery = em.createQuery(
                    "SELECT a FROM Assignment a WHERE a.assignedClass = :class ORDER BY a.dueDate ASC", Assignment.class);
            assignmentQuery.setParameter("class", student.getClassName());
            List<Assignment> upcomingAssignments = assignmentQuery.getResultList();

            // Fetch recent submissions for student
            TypedQuery<Submission> submissionQuery = em.createQuery(
                    "SELECT s FROM Submission s WHERE s.student.id = :studentId ORDER BY s.submittedAt DESC", Submission.class);
            submissionQuery.setParameter("studentId", student.getId());
            List<Submission> recentSubmissions = submissionQuery.getResultList();

            // Fetch notifications
            TypedQuery<Notification> notificationQuery = em.createQuery(
                    "SELECT n FROM Notification n WHERE n.className = :className ORDER BY n.date DESC", Notification.class);
            notificationQuery.setParameter("className", student.getClassName());
            List<Notification> notifications = notificationQuery.getResultList();

            // Set attributes for JSP
            request.setAttribute("student", student);
            request.setAttribute("upcomingAssignments", upcomingAssignments);
            request.setAttribute("recentSubmissions", recentSubmissions);
            request.setAttribute("notifications", notifications);

            // Forward to the JSP page
            request.getRequestDispatcher("student-dashboard.jsp").forward(request, response);
        }
    }
}
