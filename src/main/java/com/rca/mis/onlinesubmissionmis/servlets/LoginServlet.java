package com.rca.mis.onlinesubmissionmis.servlets;

import com.rca.mis.onlinesubmissionmis.models.Instructor;
import com.rca.mis.onlinesubmissionmis.models.Student;
import com.rca.mis.onlinesubmissionmis.models.User;
import com.rca.mis.onlinesubmissionmis.utils.BCryptUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("OnlineSubmissionMIS");
        getServletContext().setAttribute("emf", emf);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();

        EntityManager em = emf.createEntityManager();
        try {
            User user = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();

            if (user != null && BCryptUtil.checkPassword(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // Role-based redirection
                if (user instanceof Student) {
                    response.sendRedirect("student-dashboard.jsp");
                } else if (user instanceof Instructor) {
                    response.sendRedirect("instructor-dashboard.jsp");
                } else {
                    request.setAttribute("error", "User role not recognized.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Invalid email or password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (NoResultException e) {
            request.setAttribute("error", "Invalid email or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }
}
