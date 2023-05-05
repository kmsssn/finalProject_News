package kz.bitlab.finalProject.servlets;

import com.sun.net.httpserver.HttpsServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.finalProject.db.Categories;
import kz.bitlab.finalProject.db.DBConnection;
import kz.bitlab.finalProject.db.News;
import kz.bitlab.finalProject.db.Users;

import java.io.IOException;

@WebServlet("/update-news")
public class UpdateNewsServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException{
        Users currentUser=(Users) request.getSession().getAttribute("CURRENT_USER");
        if(currentUser!=null){
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                Long categoryId = Long.parseLong(request.getParameter("categoryId"));
                Long id = Long.parseLong(request.getParameter("id"));

                News news = DBConnection.getNewsById(id);
                if (news != null) {
                    news.setTitle(title);
                    news.setContent(content);
                    news.setUser(currentUser);
                    news.setCategory((new Categories(categoryId, "")));

                    DBConnection.updateNews(news);

                    response.sendRedirect("/news-details?id=" + id);
                } else {
                    response.sendRedirect("/");
                }
        }else{
            response.sendRedirect("/login");
        }
    }
}
