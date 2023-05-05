package kz.bitlab.finalProject.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kz.bitlab.finalProject.db.DBConnection;
import kz.bitlab.finalProject.db.News;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
            ArrayList<News> news= DBConnection.getNews();
            request.setAttribute("news", news);
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

