package com.liquorstore.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liquorstore.dao.ProductDAO;
import com.liquorstore.model.Product;

// public list of all products
@WebServlet("/allproducts")
public class AllProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // get all products
        List<Product> allProducts = ProductDAO.instance.getAllProducts();

        // send to jsp
        request.setAttribute("allProducts", allProducts);
        request.getRequestDispatcher("all_products.jsp").forward(request, response);
    }
}