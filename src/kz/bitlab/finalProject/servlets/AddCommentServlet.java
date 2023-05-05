package kz.bitlab.finalProject.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.finalProject.db.Comments;
import kz.bitlab.finalProject.db.DBConnection;
import kz.bitlab.finalProject.db.News;
import kz.bitlab.finalProject.db.Users;

import java.io.IOException;

@WebServlet("/addcomment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect="/login";
        request.setCharacterEncoding("utf8");
        Users currentUser=(Users) request.getSession().getAttribute("CURRENT_USER");
        if(currentUser!=null){
            String comTxt=request.getParameter("comment");
            Long newsId=Long.parseLong(request.getParameter("news_id"));
            News news= DBConnection.getNewsById(newsId);
            if(news!=null){
                Comments comment=new Comments();
                comment.setComment(comTxt);
                comment.setUser(currentUser);
                comment.setNews(news);

                if(DBConnection.addComment(comment)){
                    redirect="/news-details?id="+newsId;
                }
            }
        }
        response.sendRedirect(redirect);
    }
}
