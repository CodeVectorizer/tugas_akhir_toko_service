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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.helper.ConnectionHelper;

/**
 *
 * @author CodeVector
 */
public class ServiceManager {

    public static List<Service> showAllServiceProses(String q) {
        List<Service> userList = new ArrayList<>();
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM `tb_service` "
                    + "LEFT JOIN `tb_customer` "
                    + "ON `tb_customer`.id_customer = `tb_service`.id_customer "    
                    + "LEFT JOIN `tb_user` "
                    + "ON `tb_user`.id_user = `tb_service`.id_service "
                    + "WHERE id_service LIKE '%" + q + "%' AND status = 'proses' ORDER BY id_service DESC");

            while (rs.next()) {
                Service service = new Service();
                service.setId_service(rs.getInt("id_service"));
                service.setId_customer(rs.getInt("id_customer"));
                service.setId_user(rs.getInt("id_user"));
                    service.setCustomer_nama(rs.getString("nama_customer"));
                service.setCustomer_alamat(rs.getString("alamat"));
                service.setCustomer_no_hp(rs.getString("no_hp"));
                service.setNama_barang(rs.getString("nama_barang"));
                service.setKerusakan(rs.getString("kerusakan"));
                service.setJenis_barang(rs.getString("jenis_barang"));
                service.setTgl_service(rs.getString("tgl_service"));
                service.setTgl_selesai(rs.getString("tgl_selesai"));
                service.setSparepart(rs.getString("sparepart"));
                service.setHarga_sparepart(rs.getInt("harga_sparepart"));
                service.setHarga_service(rs.getInt("harga_service"));
                service.setTotal_harga(rs.getInt("total_harga"));
                service.setTotal_bayar(rs.getInt("total_bayar"));
                service.setTotal_kembalian(rs.getInt("total_kembalian"));
                service.setTgl_ambil(rs.getString("tgl_ambil"));
                service.setStatus(rs.getString("status"));
                
                userList.add(service);
            }

        } catch (Exception e) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, e);
        }        
        return userList;
    }

    public static List<Service> showAllService(String q) {
        List<Service> userList = new ArrayList<>();
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM `tb_service` "
                    + "LEFT JOIN `tb_customer` "
                    + "ON `tb_customer`.id_customer = `tb_service`.id_customer "
                    + "LEFT JOIN `tb_user` "
                    + "ON `tb_user`.id_user = `tb_service`.id_service "
                    + "WHERE id_service LIKE '%" + q + "%' ORDER BY id_service DESC");

            while (rs.next()) {
                Service service = new Service();
                service.setId_service(rs.getInt("id_service"));
                service.setId_customer(rs.getInt("id_customer"));
                service.setId_user(rs.getInt("id_user"));
                service.setCustomer_nama(rs.getString("nama_customer"));
                service.setCustomer_alamat(rs.getString("alamat"));
                service.setCustomer_no_hp(rs.getString("no_hp"));
                service.setNama_barang(rs.getString("nama_barang"));
                service.setKerusakan(rs.getString("kerusakan"));
                service.setJenis_barang(rs.getString("jenis_barang"));
                service.setTgl_service(rs.getString("tgl_service"));
                service.setTgl_selesai(rs.getString("tgl_selesai"));
                service.setSparepart(rs.getString("sparepart"));
                service.setHarga_sparepart(rs.getInt("harga_sparepart"));
                service.setHarga_service(rs.getInt("harga_service"));
                service.setTotal_harga(rs.getInt("total_harga"));
                service.setTotal_bayar(rs.getInt("total_bayar"));
                service.setTotal_kembalian(rs.getInt("total_kembalian"));
                service.setTgl_ambil(rs.getString("tgl_ambil"));
                service.setStatus(rs.getString("status"));
                userList.add(service);
            }

        } catch (Exception e) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, e);
        }        
        return userList;
    }

    public static Service showService(int id_service) {
        Service service = new Service();
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM `tb_service` "
                    + "LEFT JOIN `tb_customer` "
                    + "ON `tb_customer`.id_customer = `tb_service`.id_customer "
                    + "LEFT JOIN `tb_user` "
                    + "ON `tb_user`.id_user = `tb_service`.id_service "
                    + "WHERE id_service = " + id_service);
            while (rs.next()) {
                service.setId_service(rs.getInt("id_service"));
                service.setId_customer(rs.getInt("id_customer"));
                service.setId_user(rs.getInt("id_user"));
                service.setCustomer_nama(rs.getString("nama_customer"));
                service.setCustomer_alamat(rs.getString("alamat"));
                service.setCustomer_no_hp(rs.getString("no_hp"));
                service.setNama_barang(rs.getString("nama_barang"));
                service.setKerusakan(rs.getString("kerusakan"));
                service.setJenis_barang(rs.getString("jenis_barang"));
                
                service.setTgl_service(rs.getString("tgl_service"));
                service.setTgl_selesai(rs.getString("tgl_selesai"));
                service.setSparepart(rs.getString("sparepart"));
                service.setHarga_sparepart(rs.getInt("harga_sparepart"));
                service.setHarga_service(rs.getInt("harga_service"));
                service.setTotal_harga(rs.getInt("total_harga"));
                service.setTotal_bayar(rs.getInt("total_bayar"));
                service.setTotal_kembalian(rs.getInt("total_kembalian"));
                service.setTgl_ambil(rs.getString("tgl_ambil"));
                service.setStatus(rs.getString("status"));
                service.setCustomer_nama(rs.getString("nama_customer"));
                service.setCustomer_no_hp(rs.getString("no_hp"));
                service.setCustomer_alamat(rs.getString("alamat"));
            }
        } catch (Exception e) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, e);
        }

        return service;
    }

    public static int getLastIdService() {
        int id_service = 0;
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT id_service FROM tb_service "
                    + "ORDER BY id_service DESC LIMIT 1");

            while (rs.next()) {
                id_service = rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return id_service;
    }

    public static void insertService(Service service) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO `tb_service` "
                    + "(`id_customer`, `id_user`, `nama_barang`, `kerusakan`, "
                    + "`jenis_barang`,`tgl_service`, `tgl_selesai`, `sparepart`, "
                    + "`harga_sparepart`, `harga_service`,"
                    + " `total_harga`, `status`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pstm.setInt(1, service.getId_customer());
            pstm.setInt(2, service.getId_user());
            pstm.setString(3, service.getNama_barang());
            pstm.setString(4, service.getKerusakan());
            pstm.setString(5, service.getJenis_barang());
            pstm.setString(6, service.getTgl_service());
            pstm.setString(7, service.getTgl_selesai());
            pstm.setString(8, service.getSparepart());
            pstm.setInt(9, service.getHarga_sparepart());
            pstm.setInt(10, service.getHarga_service());
            pstm.setInt(11, service.getTotal_harga());
            pstm.setString(12, "Proses");
            pstm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void updateService(Service service, int id_service) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            PreparedStatement pstm = conn.prepareStatement("UPDATE tb_service "
                    + "SET nama_barang = ?, kerusakan = ?, "
                    + "jenis_barang = ?, sparepart = ?, "
                    + "harga_sparepart = ?, harga_service = ?, "
                    + "total_harga = ? WHERE id_service = ?");
            pstm.setString(1, service.getNama_barang());
            pstm.setString(2, service.getKerusakan());
            pstm.setString(3, service.getJenis_barang());
            pstm.setString(4, service.getSparepart());
            pstm.setInt(5, service.getHarga_sparepart());
            pstm.setInt(6, service.getHarga_service());
            pstm.setInt(7, service.getTotal_harga());
            pstm.setInt(8, id_service);
            pstm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void deleteService(int id_service) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM tb_service WHERE id_service = " + id_service);
        } catch (Exception e) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void updateAmbilService(Service service, int id_service) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            PreparedStatement pstm = conn.prepareStatement("UPDATE tb_service "
                    + "SET total_bayar = ?, total_kembalian = ?, "
                    + "tgl_ambil = ?, status = ? WHERE id_service = ?");
            pstm.setInt(1, service.getTotal_bayar());
            pstm.setInt(2, service.getTotal_kembalian());
            pstm.setString(3, service.getTgl_ambil());
            pstm.setString(4, "Ambil");
            pstm.setInt(5, id_service);
            pstm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static int[] serviceDashboard() {
        int[] countService = new int[4];
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT "
                    + "COUNT(id_service) AS all_service, "
                    + "(SELECT COUNT(id_service) FROM tb_service "
                    + "WHERE status ='proses') AS proses, "
                    + "(SELECT COUNT(id_service) FROM tb_service "
                    + "WHERE status ='selesai') AS selesai, "
                    + "(SELECT COUNT(id_service) FROM tb_service "
                    + "WHERE status ='ambil') AS ambil FROM`tb_service`LIMIT 1");
            while (rs.next()) {
                countService[0] = rs.getInt("all_service");
                countService[1] = rs.getInt("proses");
                countService[2] = rs.getInt("selesai");
                countService[3] = rs.getInt("ambil");
            }
        } catch (Exception e) {
        }
        return countService;
    }
}
