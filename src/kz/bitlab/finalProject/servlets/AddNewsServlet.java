package kz.bitlab.finalProject.servlets;

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

@WebServlet("/addnews")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUser=(Users) request.getSession().getAttribute("CURRENT_USER");
        if(currentUser!=null){
            request.getRequestDispatcher("/addnews.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect="/login";
        request.setCharacterEncoding("utf8");
        Users currentUser=(Users) request.getSession().getAttribute("CURRENT_USER");
        if(currentUser!=null){
            redirect="/addnews?news";
            String title=request.getParameter("title");
            String content=request.getParameter("content");
            Long categoryId=Long.parseLong(request.getParameter("categoryId"));
            News news=new News();
            news.setTitle(title);
            news.setContent(content);
            news.setUser(currentUser);
            news.setCategory(new Categories(categoryId, ""));
            if(DBConnection.addNews(news)){
                redirect="/addnews?success";
            }
        }
        response.sendRedirect(redirect);
    }
}
