package com.liquorstore.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liquorstore.dao.ProductDAO;
import com.liquorstore.model.Product;
import com.liquorstore.model.User;

@WebServlet("/addproduct")
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // check if user is logged in
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // if not logged in, redirect
            response.sendRedirect("login.jsp");
            return;
        }

        // get form data
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String type = request.getParameter("type");
        
        // parse numbers
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantityOnHand"));
        
        // create product linked to user
        Product p = new Product(name, brand, type, price, quantity, user.getId());

        // save to database
        ProductDAO.instance.save(p);

        // back to inventory
        response.sendRedirect("inventory");
    }
}