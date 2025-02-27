package com.rca.mis.onlinesubmissionmis.servlets;

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
import java.util.UUID;

@WebServlet("/create-assignment")
public class CreateAssignmentServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("OnlineSubmissionMIS");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title").trim();
        String description = request.getParameter("description").trim();
        LocalDateTime dueDate = LocalDateTime.parse(request.getParameter("dueDate"));
        String allowedFileTypes = request.getParameter("allowedFileTypes").trim();
        UUID classId = UUID.fromString(request.getParameter("classId"));
        UUID courseId = UUID.fromString(request.getParameter("courseId"));

        EntityManager em = emf.createEntityManager();
        try{

        }catch (Exception e){
            em.getTransaction().rollback();
            request.setAttribute("error", "Failed to create assignment.");
            request.getRequestDispatcher("instructor-dashboard.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }
}
