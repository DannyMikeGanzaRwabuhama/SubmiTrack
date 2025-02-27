package com.rca.mis.onlinesubmissionmis.servlets;

import com.rca.mis.onlinesubmissionmis.dao.ClassDAO;
import com.rca.mis.onlinesubmissionmis.dao.DepartmentDAO;
import com.rca.mis.onlinesubmissionmis.models.*;
import com.rca.mis.onlinesubmissionmis.models.Class;
import com.rca.mis.onlinesubmissionmis.utils.BCryptUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("OnlineSubmissionMIS");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        ClassDAO classDao = new ClassDAO(em);
        DepartmentDAO departmentDao = new DepartmentDAO(em);

        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String role = request.getParameter("role").trim();
        String hashedPassword = BCryptUtil.hashPassword(password);

        User user = null;

        try {
            em.getTransaction().begin();

            if ("student".equalsIgnoreCase(role)) {
                String className = request.getParameter("className").trim();
                String dob = request.getParameter("dob").trim();

                if (className.isEmpty() || dob.isEmpty()) {
                    request.setAttribute("error", "Class name and date of birth are required for students.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }

                Optional<Class> studentClassOpt = ClassDAO.findByName(className);
                if (studentClassOpt.isEmpty()) {
                    request.setAttribute("error", "Class not found.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }

                Student student = new Student(firstName, lastName, email, hashedPassword, LocalDateTime.now(), studentClassOpt.get(), dob);
                user = student;

            } else if ("instructor".equalsIgnoreCase(role)) {
                String departmentName = request.getParameter("departmentName").trim();

                if (departmentName.isEmpty()) {
                    request.setAttribute("error", "Department name is required for instructors.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }

                Optional<Department> departmentOpt = departmentDao.findByName(departmentName);
                if (departmentOpt.isEmpty()) {
                    request.setAttribute("error", "Department not found.");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }

                Instructor instructor = new Instructor(firstName, lastName, email, hashedPassword, LocalDateTime.now(), departmentOpt.get());
                user = instructor;
            } else {
                request.setAttribute("error", "Invalid role selected.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            em.persist(user);
            em.getTransaction().commit();

            response.sendRedirect("login.jsp?success=Registration successful!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            request.setAttribute("error", "An error occurred. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }

    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }
}
