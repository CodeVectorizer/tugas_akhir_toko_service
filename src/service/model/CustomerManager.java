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
public class CustomerManager {

    public static List<Customer> showAllCustomer(String q) {
        List<Customer> customerList = new ArrayList<>();
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tb_customer "
                    + "WHERE id_customer "
                    + "LIKE '%"+q+"%'");
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId_customer(rs.getInt("id_customer"));
                customer.setNama(rs.getString("nama_customer"));
                customer.setAlamat(rs.getString("alamat"));
                customer.setNo_hp(rs.getString("no_hp"));
                customerList.add(customer);
            }
            conn.close();
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return customerList;
    }

    public static Customer showCustomer(int id_customer) {
        Customer customer = new Customer();
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tb_customer "
                    + "WHERE id_customer = " + id_customer);

            while (rs.next()) {
                customer.setId_customer(rs.getInt("id_customer"));
                customer.setNama(rs.getString("nama_customer"));
                customer.setAlamat(rs.getString("alamat"));
                customer.setNo_hp(rs.getString("no_hp"));
            }

            System.out.println(customer.getId_customer());
            conn.close();
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
        }

        return customer;
    }

    public static void insertCustomer(Customer customer) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO tb_customer"
                    + "(nama_customer, alamat, no_hp) VALUES(?, ?, ?)");
            pstm.setString(1, customer.getNama());
            pstm.setString(2, customer.getAlamat());
            pstm.setString(3, customer.getNo_hp());
            pstm.execute();
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void updateCustomer(Customer customer, int id_customer) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            PreparedStatement pstm = conn.prepareStatement("UPDATE tb_customer "
                    + "SET nama_customer = ?, alamat = ?, no_hp = ? WHERE id_customer = ?");
            pstm.setString(1, customer.getNama());
            pstm.setString(2, customer.getAlamat());
            pstm.setString(3, customer.getNo_hp());
            pstm.setInt(4, id_customer);
            pstm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public static int getLastIdCustomer() {
        int id_customer = 0;
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT id_customer FROM tb_customer "
                    + "ORDER BY id_customer DESC LIMIT 1");

            while (rs.next()) {
                id_customer = rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return id_customer;
    }

    public static void deleteCustomer(int id_customer) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM tb_customer "
                    + "WHERE id_customer=?");
            pstm.setInt(1, id_customer);
            pstm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, e);

        }

    }
}
