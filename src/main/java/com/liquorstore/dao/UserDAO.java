package com.liquorstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.liquorstore.model.User;

// enum singleton pattern
public enum UserDAO {
    instance;

    // connect to database
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:hsqldb:hsql://localhost/oneDB", "sa", "");
        return con;
    }

    // register new user, returns false if email exists
    public boolean save(User user) {
        Connection con = null;
        try {
            con = getConnection();
            
            // check if email already exists
            String checkSql = "SELECT COUNT(*) FROM user WHERE email = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, user.getEmail());
            
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // email already taken
                rs.close();
                checkStmt.close();
                return false; 
            }
            rs.close();
            checkStmt.close();

            // insert new user
            String insertSql = "INSERT INTO user (email, password, name) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = con.prepareStatement(insertSql);
            
            insertStmt.setString(1, user.getEmail());
            insertStmt.setString(2, user.getPassword());
            insertStmt.setString(3, user.getName());

            int rowsAffected = insertStmt.executeUpdate();
            insertStmt.close();

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

    // validate login, returns user object or null
    public User checkLogin(String email, String password) {
        Connection con = null;
        User user = null;
        
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        
        try {
            con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            // if found, build user object
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
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
        
        return user;
    }
}