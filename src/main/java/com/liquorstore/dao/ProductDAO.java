package com.liquorstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.liquorstore.model.Product;

// enum singleton pattern
public enum ProductDAO {
    instance;

    // connect to database
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:hsqldb:hsql://localhost/oneDB", "sa", "");
        return con;
    }

    // save new product
    public boolean save(Product p) {
        Connection con = null;
        String sql = "INSERT INTO product (name, brand, type, price, quantityOnHand, user_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getBrand());
            stmt.setString(3, p.getType());
            stmt.setDouble(4, p.getPrice());
            stmt.setInt(5, p.getQuantityOnHand());
            stmt.setInt(6, p.getUserId()); // links to owner

            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // get all products for a specific user
    public List<Product> getProductsByUser(int userId) {
        Connection con = null;
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE user_id = ?";

        try {
            con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            
            ResultSet rs = stmt.executeQuery();
            
            // build product list from results
            while (rs.next()) {
                Product p = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("brand"),
                    rs.getString("type"),
                    rs.getDouble("price"),
                    rs.getInt("quantityOnHand"),
                    rs.getInt("user_id")
                );
                products.add(p);
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return products;
    }
    
    // get all products from all users
    public List<Product> getAllProducts() {
        Connection con = null;
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try {
            con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Product p = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("brand"),
                    rs.getString("type"),
                    rs.getDouble("price"),
                    rs.getInt("quantityOnHand"),
                    rs.getInt("user_id")
                );
                products.add(p);
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return products;
    }
}