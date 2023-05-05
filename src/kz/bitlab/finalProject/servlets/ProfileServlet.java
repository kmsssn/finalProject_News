package kz.bitlab.finalProject.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.finalProject.db.Users;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException{
        Users currentUser=(Users) request.getSession().getAttribute("CURRENT_USER");
        if (currentUser!=null){
            request.getRequestDispatcher("/profile.jsp").forward(request, response);
        }else{
            response.sendRedirect("/login");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

    }
}
