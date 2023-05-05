package kz.bitlab.finalProject.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.finalProject.db.DBConnection;

import java.io.IOException;

@WebServlet("/delete-news")
public class DeleteNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id=Long.parseLong((request.getParameter("delNews_id")));
        DBConnection.deleteNews(id);
        response.sendRedirect("/");
    }
}
