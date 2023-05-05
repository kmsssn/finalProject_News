package kz.bitlab.finalProject.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.finalProject.db.Comments;
import kz.bitlab.finalProject.db.DBConnection;
import kz.bitlab.finalProject.db.News;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/news-details")
public class NewsDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id=Long.parseLong(request.getParameter("id"));
        News news = DBConnection.getNewsById(id);
        request.setAttribute("news", news);

        if (news!=null){
            ArrayList<Comments> comments=DBConnection.getComments(news.getId());
            request.setAttribute("comments", comments);
        }

        request.getRequestDispatcher("/newsdetails.jsp").forward(request, response);
        }

    }
