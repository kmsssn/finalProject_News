package kz.bitlab.finalProject.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.finalProject.db.DBConnection;
import kz.bitlab.finalProject.db.Users;

import java.io.IOException;

@WebServlet("/register")

public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect="/register?emailerror";
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String repPassword=request.getParameter("repeat_password");
        String fullName=request.getParameter("full_name");
        int role_id= Integer.valueOf(request.getParameter("role_id"));

        Users checkUser= DBConnection.getUser(email);
            if(checkUser==null){
                redirect="/register?passworderror";
                if(password.equals(repPassword)){
                    Users user=new Users();
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setFullName(fullName);
                    user.setRole(role_id);
                    if(DBConnection.addUser(user)){
                        redirect="/register?success";
                    }
                }
            }
            response.sendRedirect(redirect);
    }
}
