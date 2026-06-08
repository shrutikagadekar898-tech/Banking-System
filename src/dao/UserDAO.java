package dao;

import model.User;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
public int getUserIdByUsername(String username) {
    int id=0;
    try {
        Connection con = DBConnection.getConnection();
        String sql = "Select user_id from users where username=?";
        PreparedStatement ps= con.prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            id = rs.getInt("user_id");
        }
    }catch(Exception e) {
        e.printStackTrace();
    }
    return id;
}
    // ✅ REGISTER USER
    public boolean registerUser(String username, String password, String role) {

        String sql = "INSERT INTO users (username, password_hash, role, status) VALUES (?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.setString(4, "ACTIVE");

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            if (e.getMessage().contains("duplicate")) {
                System.out.println("Username already exists");
            } else {
                e.printStackTrace();
            }

        }

        return false;
    }

    // ✅ LOGIN USER
    public boolean loginUser(String username, String password, String role) {

        String sql = "SELECT * FROM users WHERE username=? AND password_hash=? AND role=? AND status='ACTIVE'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ GET ALL USERS (Admin Panel)
    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();

        String sql = "SELECT * FROM users ORDER BY user_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));

                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ UPDATE USER
    public boolean updateUser(int userId, String username, String role, String status) {

        String sql = "UPDATE users SET username=?, role=?, status=? WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, role);
            ps.setString(3, status);
            ps.setInt(4, userId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
// ADD user
    public boolean addUser(String username, String password,String role, String status) {
        String sql= "insert into users(username,password_hash, role, status) values (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2,password);
            ps.setString(3, role);
            ps.setString(4, status);

             ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // ✅ DELETE USER
    public boolean deleteUser(int userId) {

        String sql = "DELETE FROM users WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ CHECK USER EXISTS
    public boolean userExists(String username) {

        String sql = "SELECT * FROM users WHERE username=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}