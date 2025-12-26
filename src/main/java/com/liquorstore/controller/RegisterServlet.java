package com.liquorstore.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liquorstore.dao.UserDAO;
import com.liquorstore.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // get form data
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        // create new user
        User user = new User(email, password, name);

        // attempt to save
        boolean success = UserDAO.instance.save(user);

        if (success) {
            // if success, go to login
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // failed, email already exists
            request.setAttribute("error", "Email already exists. Try another one.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}