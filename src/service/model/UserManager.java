/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.helper.ConnectionHelper;

/**
 *
 * @author CodeVector
 */
public class UserManager {

    public static List<User> showAllUser(String q) {
        List<User> userList = new ArrayList<>();
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tb_user "
                    + "WHERE id_user LIKE '%" + q + "%'");

            while (rs.next()) {
                User user = new User();
                user.setId_user(rs.getInt("id_user"));
                user.setNama(rs.getString("nama_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNo_telp(rs.getString("no_telp"));
                user.setRole(rs.getString("role"));
                userList.add(user);
            }
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return userList;
    }

    public static User showUser(int id_user) {
        User user = new User();
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tb_user "
                    + "WHERE id_user = " + id_user);

            while (rs.next()) {
                user.setId_user(rs.getInt("id_user"));
                user.setNama(rs.getString("nama_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNo_telp(rs.getString("no_telp"));
                user.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
        }

        return user;
    }

    public static User checkUser(String username, String password) {
        ResultSet rs = null;
        User user = new User();
        try {
            Connection conn = ConnectionHelper.getConnection();
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM tb_user "
                    + "WHERE username = ? AND password = ?");
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                user.setId_user(rs.getInt("id_user"));
                user.setNama(rs.getString("nama_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setNo_telp(rs.getString("no_telp"));
                user.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return user;
    }

    public static void insertUser(User user) {
        try {
            System.out.println(user.getRole());
            Connection conn = ConnectionHelper.getConnection();
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO tb_user"
                    + "(nama_user, username, password, no_telp, role) "
                    + "VALUES(?, ?, ?, ?, ?)");
            pstm.setString(1, user.getNama());
            pstm.setString(2, user.getUsername());
            pstm.setString(3, user.getPassword());
            pstm.setString(4, user.getNo_telp());
            pstm.setString(5, user.getRole());
            pstm.execute();
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void updateUser(User user, int id_user) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            PreparedStatement pstm = conn.prepareStatement("UPDATE tb_user "
                    + "SET nama_user=?, username=?, no_telp=?, role=? "
                    + "WHERE id_user = ?");
            pstm.setString(1, user.getNama());
            pstm.setString(2, user.getUsername());
            pstm.setString(3, user.getNo_telp());
            pstm.setString(4, user.getRole());
            pstm.setInt(5, id_user);
            pstm.execute();
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void deleteUser(int id_user) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM tb_user "
                    + "WHERE id_user=?");
            pstm.setInt(1, id_user);
            pstm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);

        }

    }
}
