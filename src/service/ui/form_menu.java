/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.ui;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import service.helper.ConnectionHelper;
import service.model.Customer;
import service.model.CustomerManager;
import service.model.Service;
import service.model.ServiceManager;
import service.model.User;
import service.model.UserManager;
import static service.ui.form_datapelanggan.listCustomer;
import static service.ui.form_datapelanggan.tableModel;
import static service.ui.form_dataservice.listService;
import static service.ui.form_dataservice.tableModel;
import static service.ui.form_datauser.listCustomer;
import static service.ui.form_datauser.tableModel;

/**
 *
 * @author CodeVector
 */
public class form_menu extends javax.swing.JFrame {

    CardLayout cardLayout;
    public static DefaultTableModel tableModel;

    public static List<User> listUser;
    User userGlobal;

    public static List<Customer> listCustomer;
    Customer customerGlobal;

    public static List<Service> listService;
    public static List<Service> listServiceProses;
        
    public form_menu() {
        initComponents();
        cardLayout = (CardLayout) (card_layout.getLayout());        
        setDashboard();
        initTableUser("");
        initTableCustomer("");
        initTableService("");                
    }
    
    public void setLbl_online(String user){
        lbl_online.setText(user);
    }
    
    public void setDashboard(){
        int[] item = ServiceManager.serviceDashboard();
        lbl_totalService.setText(String.valueOf(item[0]));
        lbl_proses.setText(String.valueOf(item[1]));
        lbl_selesai.setText(String.valueOf(item[2]));
        lbl_ambil.setText(String.valueOf(item[3]));
    }

    public void showMessage(String message, int type) {
        if (type == 1) {
            JOptionPane.showMessageDialog(this, message, "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void roleTeknisi(){
        menu.remove(jButton5);
        menu.remove(jButton6);
    }

    public void initTableUser(String q) {
        tb_user.setRowHeight(30);

        tableModel = (DefaultTableModel) tb_user.getModel();
        tableModel.setRowCount(0);

        listUser = new ArrayList<>();

        listUser = UserManager.showAllUser(q);

        listUser.forEach(user -> {
            tableModel.addRow(new Object[]{
                user.getId_user(),
                user.getNama(),
                user.getUsername(),
                user.getNo_telp(),
                user.getRole()
            });
        });
    }

    private boolean validateDataUser() {
        boolean isCompleted = false;
        User user = new User();

        if (nama_user.getText().isEmpty()) {
            isCompleted = false;
            nama_user.requestFocus(true);
        } else {
            isCompleted = true;
            user.setNama(nama_user.getText());
        }

        if (username.getText().isEmpty()) {
            isCompleted = false;
            username.requestFocus(true);
        } else {
            isCompleted = true;
            user.setUsername(username.getText());
        }

        if (password.getText().isEmpty()) {
            isCompleted = false;
            password.requestFocus(true);
        } else {
            isCompleted = true;
            user.setPassword(password.getText());
        }

        if (telp_user.getText().isEmpty() && telp_user.getText().length() <= 12) {
            isCompleted = false;
            telp_user.requestFocus(true);
        } else {
            isCompleted = true;
            user.setNo_telp(telp_user.getText());
        }
        user.setRole(role.getSelectedItem().toString());
        userGlobal = user;
        return isCompleted;

    }

    public void resetFieldUser() {
        id_user.setText("");
        nama_user.setText("");
        username.setText("");
        password.setText("");
        telp_user.setText("");
        role.setSelectedIndex(0);
    }

    public void setUserToComponent(int id_user) {
        resetFieldUser();
        User user = UserManager.showUser(id_user);
        this.id_user.setText(String.valueOf(user.getId_user()));
        nama_user.setText(user.getNama());
        username.setText(user.getUsername());
        password.setText(user.getPassword());
        telp_user.setText(user.getNo_telp());       
        if (user.getRole() == "Admin") {
            role.setSelectedIndex(0);
        } else {
            role.setSelectedIndex(1);
        }
    }

    //FORM DATA CUSTOMER
    public void initTableCustomer(String q) {
        tb_customer.setRowHeight(30);

        tableModel = (DefaultTableModel) tb_customer.getModel();
        tableModel.setRowCount(0);

        listCustomer = new ArrayList<>();

        listCustomer = CustomerManager.showAllCustomer(q);
        System.out.println(listCustomer);
        listCustomer.forEach(customer -> {
            tableModel.addRow(new Object[]{
                customer.getId_customer(),
                customer.getNama(),
                customer.getAlamat(),
                customer.getNo_hp(),});
        });
    }

    private boolean validateDataCustomer() {
        boolean isCompleted = false;
        Customer customer = new Customer();

        if (nama_customer.getText().isEmpty()) {
            isCompleted = false;
            nama_customer.requestFocus(true);
        } else {
            isCompleted = true;
            customer.setNama(nama_customer.getText());
        }

        if (alamat_customer.getText().isEmpty()) {
            isCompleted = false;
            alamat_customer.requestFocus(true);
        } else {
            isCompleted = true;
            customer.setAlamat(alamat_customer.getText());
        }

        if (telp_customer.getText().isEmpty()) {
            isCompleted = false;
            telp_customer.requestFocus(true);
        } else {
            isCompleted = true;
            customer.setNo_hp(telp_customer.getText());
        }

        customerGlobal = customer;
        return true;

    }

    public void resetFieldCustomer() {
        id_customer.setText("");
        nama_customer.setText("");
        alamat_customer.setText("");
        telp_customer.setText("");

    }

    public void setCustomerToComponent(int id_customer) {
        resetFieldCustomer();
        Customer customer = CustomerManager.showCustomer(id_customer);
        this.id_customer.setText(String.valueOf(customer.getId_customer()));
        nama_customer.setText(customer.getNama());
        alamat_customer.setText(customer.getAlamat());
        telp_customer.setText(customer.getNo_hp());

    }

    //DATA FORM SERVICE
    public void initTableService(String q) {
        setDashboard();
        tb_service.setRowHeight(30);

        tableModel = (DefaultTableModel) tb_service.getModel();
        tableModel.setRowCount(0);

        listService = new ArrayList<>();

        listService = ServiceManager.showAllService(q);
        listService.forEach(service -> {
            tableModel.addRow(new Object[]{
                service.getId_service(),
                service.getNama_barang(),
                service.getKerusakan(),
                service.getCustomer_nama(),
                service.getCustomer_alamat(),
                service.getCustomer_no_hp(),
                service.getHarga_service(),
                service.getTgl_service(),
                service.getStatus(),});
        });
        
        listServiceProses = ServiceManager.showAllServiceProses("");
        tb_serviceDashboard.setRowHeight(30);
         tableModel = (DefaultTableModel) tb_serviceDashboard.getModel();
        tableModel.setRowCount(0);
         listServiceProses.forEach(service -> {
            tableModel.addRow(new Object[]{
                service.getId_service(),
                service.getNama_barang(),
                service.getKerusakan(),
                service.getCustomer_nama(),
                service.getCustomer_alamat(),
                service.getCustomer_no_hp(),
                service.getHarga_service(),
                service.getTgl_service(),
                service.getStatus(),});
        });
         tableModel.setRowCount(5);
         
    }

    public void resetService() {
        initTableService("");
        setDashboard();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        lbl_online = new javax.swing.JLabel();
        card_layout = new javax.swing.JPanel();
        pnl1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lbl_totalService = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        lbl_proses = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        lbl_selesai = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        lbl_ambil = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_serviceDashboard = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        btn_tambahService1 = new javax.swing.JButton();
        form_dataservice = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_service = new javax.swing.JTable();
        btn_tambahService = new javax.swing.JButton();
        btn_editService = new javax.swing.JButton();
        btn_hapusService = new javax.swing.JButton();
        btn_tambahService3 = new javax.swing.JButton();
        id_customer1 = new javax.swing.JTextField();
        btn_ambilService = new javax.swing.JButton();
        form_datauser = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nama_user = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btn_tambahUser = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_user = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        role = new javax.swing.JComboBox<>();
        telp_user = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btn_editUser = new javax.swing.JButton();
        btn_hapusUser = new javax.swing.JButton();
        txt_cariUser = new javax.swing.JTextField();
        id_user = new javax.swing.JLabel();
        form_datacustomer1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        nama_customer = new javax.swing.JTextField();
        telp_customer = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        alamat_customer = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        btn_tambahCustomer = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_customer = new javax.swing.JTable();
        cari_customer = new javax.swing.JTextField();
        btn_editCustomer = new javax.swing.JButton();
        btn_hapusCustomer = new javax.swing.JButton();
        id_customer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jSplitPane2.setBackground(new java.awt.Color(231, 233, 235));
        jSplitPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(231, 233, 235)));
        jSplitPane2.setDividerSize(1);
        jSplitPane2.setForeground(new java.awt.Color(231, 233, 235));

        menu.setBackground(new java.awt.Color(248, 248, 248));

        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(39, 52, 71));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Swirty Computer");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/service/images/logo.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        jButton3.setBackground(new java.awt.Color(231, 233, 235));
        jButton3.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(137, 144, 155));
        jButton3.setText("Home");
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(137, 144, 155)));
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setOpaque(true);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(231, 233, 235));
        jButton4.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(137, 144, 155));
        jButton4.setText("Service");
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(231, 233, 235));
        jButton5.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(137, 144, 155));
        jButton5.setText("Customer");
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(231, 233, 235));
        jButton6.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(137, 144, 155));
        jButton6.setText("User");
        jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(39, 52, 71));
        jLabel3.setText("Developed By Lukman.");

        jButton7.setBackground(new java.awt.Color(231, 233, 235));
        jButton7.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(137, 144, 155));
        jButton7.setText("Logout");
        jButton7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton7MouseExited(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        lbl_online.setFont(new java.awt.Font("Poppins Medium", 2, 14)); // NOI18N
        lbl_online.setForeground(new java.awt.Color(51, 51, 51));
        lbl_online.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_online.setText("Admin");
        lbl_online.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(lbl_online, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lbl_online))
                .addGap(113, 113, 113)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jSplitPane2.setLeftComponent(menu);

        card_layout.setBackground(new java.awt.Color(54, 57, 63));
        card_layout.setLayout(new java.awt.CardLayout());

        pnl1.setBackground(new java.awt.Color(248, 248, 248));

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 2, 0, new java.awt.Color(204, 204, 204)));

        jLabel17.setFont(new java.awt.Font("Poppins Medium", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Total Service");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbl_totalService.setFont(new java.awt.Font("Poppins Medium", 1, 48)); // NOI18N
        lbl_totalService.setForeground(new java.awt.Color(51, 51, 51));
        lbl_totalService.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_totalService.setText("10");
        lbl_totalService.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(lbl_totalService, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_totalService, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(248, 248, 248));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 2, 0, new java.awt.Color(204, 204, 204)));

        jLabel24.setFont(new java.awt.Font("Poppins Medium", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Proses");
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbl_proses.setFont(new java.awt.Font("Poppins Medium", 1, 48)); // NOI18N
        lbl_proses.setForeground(new java.awt.Color(51, 51, 51));
        lbl_proses.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_proses.setText("5");
        lbl_proses.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(lbl_proses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_proses, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(248, 248, 248));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 2, 0, new java.awt.Color(204, 204, 204)));

        jLabel26.setFont(new java.awt.Font("Poppins Medium", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Selesai");
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbl_selesai.setFont(new java.awt.Font("Poppins Medium", 1, 48)); // NOI18N
        lbl_selesai.setForeground(new java.awt.Color(51, 51, 51));
        lbl_selesai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_selesai.setText("2");
        lbl_selesai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(lbl_selesai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_selesai, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(248, 248, 248));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 2, 0, new java.awt.Color(204, 204, 204)));

        jLabel28.setFont(new java.awt.Font("Poppins Medium", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Ambil");
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbl_ambil.setFont(new java.awt.Font("Poppins Medium", 1, 48)); // NOI18N
        lbl_ambil.setForeground(new java.awt.Color(51, 51, 51));
        lbl_ambil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ambil.setText("3");
        lbl_ambil.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(lbl_ambil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(lbl_ambil, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addContainerGap())
        );

        jLabel30.setFont(new java.awt.Font("Poppins Medium", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Dashboard Service");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jScrollPane5.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane5.setBorder(null);

        tb_serviceDashboard.setBackground(new java.awt.Color(248, 248, 248));
        tb_serviceDashboard.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        tb_serviceDashboard.setForeground(new java.awt.Color(137, 144, 155));
        tb_serviceDashboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No Service", "Nama Barang", "Kerusakan", "Nama Pelanggan", "Alamat Pelanggan", "No. HP", "Tgl Service", "Total Harga", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_serviceDashboard.setGridColor(new java.awt.Color(102, 102, 102));
        jScrollPane5.setViewportView(tb_serviceDashboard);

        jLabel31.setFont(new java.awt.Font("Poppins Medium", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Service Terbaru");
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn_tambahService1.setBackground(new java.awt.Color(39, 52, 71));
        btn_tambahService1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btn_tambahService1.setForeground(new java.awt.Color(248, 248, 248));
        btn_tambahService1.setText("Service");
        btn_tambahService1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));
        btn_tambahService1.setContentAreaFilled(false);
        btn_tambahService1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_tambahService1.setOpaque(true);
        btn_tambahService1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahService1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl1Layout = new javax.swing.GroupLayout(pnl1);
        pnl1.setLayout(pnl1Layout);
        pnl1Layout.setHorizontalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_tambahService1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnl1Layout.setVerticalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tambahService1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        card_layout.add(pnl1, "card2");

        form_dataservice.setBackground(new java.awt.Color(248, 248, 248));

        jLabel16.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(39, 52, 71));
        jLabel16.setText("Data Service");
        jLabel16.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jScrollPane3.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane3.setBorder(null);

        tb_service.setBackground(new java.awt.Color(248, 248, 248));
        tb_service.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        tb_service.setForeground(new java.awt.Color(137, 144, 155));
        tb_service.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No Service", "Nama Barang", "Kerusakan", "Nama Pelanggan", "Alamat Pelanggan", "No. HP", "Tgl Service", "Total Harga", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_service.setGridColor(new java.awt.Color(102, 102, 102));
        jScrollPane3.setViewportView(tb_service);

        btn_tambahService.setBackground(new java.awt.Color(39, 52, 71));
        btn_tambahService.setFont(new java.awt.Font("Poppins SemiBold", 0, 10)); // NOI18N
        btn_tambahService.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambahService.setText("Tambah");
        btn_tambahService.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        btn_tambahService.setContentAreaFilled(false);
        btn_tambahService.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_tambahService.setOpaque(true);
        btn_tambahService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahServiceActionPerformed(evt);
            }
        });

        btn_editService.setBackground(new java.awt.Color(39, 52, 71));
        btn_editService.setFont(new java.awt.Font("Poppins SemiBold", 0, 10)); // NOI18N
        btn_editService.setForeground(new java.awt.Color(255, 255, 255));
        btn_editService.setText("Edit");
        btn_editService.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        btn_editService.setContentAreaFilled(false);
        btn_editService.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_editService.setOpaque(true);
        btn_editService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editServiceActionPerformed(evt);
            }
        });

        btn_hapusService.setBackground(new java.awt.Color(39, 52, 71));
        btn_hapusService.setFont(new java.awt.Font("Poppins SemiBold", 0, 10)); // NOI18N
        btn_hapusService.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapusService.setText("Hapus");
        btn_hapusService.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        btn_hapusService.setContentAreaFilled(false);
        btn_hapusService.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_hapusService.setOpaque(true);
        btn_hapusService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusServiceActionPerformed(evt);
            }
        });

        btn_tambahService3.setBackground(new java.awt.Color(39, 52, 71));
        btn_tambahService3.setFont(new java.awt.Font("Poppins SemiBold", 0, 10)); // NOI18N
        btn_tambahService3.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambahService3.setText("Cetak");
        btn_tambahService3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        btn_tambahService3.setContentAreaFilled(false);
        btn_tambahService3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_tambahService3.setOpaque(true);
        btn_tambahService3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahService3ActionPerformed(evt);
            }
        });

        id_customer1.setBackground(new java.awt.Color(248, 248, 248));
        id_customer1.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        id_customer1.setForeground(new java.awt.Color(137, 144, 155));
        id_customer1.setText("Cari");
        id_customer1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        id_customer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_customer1ActionPerformed(evt);
            }
        });
        id_customer1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_customer1KeyReleased(evt);
            }
        });

        btn_ambilService.setBackground(new java.awt.Color(39, 52, 71));
        btn_ambilService.setFont(new java.awt.Font("Poppins SemiBold", 0, 10)); // NOI18N
        btn_ambilService.setForeground(new java.awt.Color(255, 255, 255));
        btn_ambilService.setText("Ambil Service");
        btn_ambilService.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        btn_ambilService.setContentAreaFilled(false);
        btn_ambilService.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ambilService.setOpaque(true);
        btn_ambilService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ambilServiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout form_dataserviceLayout = new javax.swing.GroupLayout(form_dataservice);
        form_dataservice.setLayout(form_dataserviceLayout);
        form_dataserviceLayout.setHorizontalGroup(
            form_dataserviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form_dataserviceLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(form_dataserviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form_dataserviceLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_ambilService, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, form_dataserviceLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_tambahService, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_editService, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_hapusService, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(282, 282, 282)
                        .addComponent(btn_tambahService3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id_customer1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        form_dataserviceLayout.setVerticalGroup(
            form_dataserviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form_dataserviceLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(form_dataserviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(btn_ambilService, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(form_dataserviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_hapusService, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(form_dataserviceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_tambahService3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_tambahService, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_editService, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(id_customer1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addGap(49, 49, 49))
        );

        card_layout.add(form_dataservice, "form_dataservice");

        form_datauser.setBackground(new java.awt.Color(248, 248, 248));

        jLabel4.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(39, 52, 71));
        jLabel4.setText("Data User");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel5.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(39, 52, 71));
        jLabel5.setText("No User");

        jLabel6.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(39, 52, 71));
        jLabel6.setText("Nama User");

        nama_user.setBackground(new java.awt.Color(248, 248, 248));
        nama_user.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        nama_user.setForeground(new java.awt.Color(137, 144, 155));
        nama_user.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        nama_user.setSelectedTextColor(new java.awt.Color(137, 144, 155));

        password.setBackground(new java.awt.Color(248, 248, 248));
        password.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        password.setForeground(new java.awt.Color(137, 144, 155));
        password.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        password.setSelectedTextColor(new java.awt.Color(137, 144, 155));

        jLabel7.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(39, 52, 71));
        jLabel7.setText("Password");

        username.setBackground(new java.awt.Color(248, 248, 248));
        username.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        username.setForeground(new java.awt.Color(137, 144, 155));
        username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        username.setSelectedTextColor(new java.awt.Color(137, 144, 155));

        jLabel8.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(39, 52, 71));
        jLabel8.setText("Username");

        btn_tambahUser.setBackground(new java.awt.Color(39, 52, 71));
        btn_tambahUser.setFont(new java.awt.Font("Poppins SemiBold", 0, 10)); // NOI18N
        btn_tambahUser.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambahUser.setText("Tambah");
        btn_tambahUser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        btn_tambahUser.setContentAreaFilled(false);
        btn_tambahUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_tambahUser.setOpaque(true);
        btn_tambahUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahUserActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setBorder(null);

        tb_user.setBackground(new java.awt.Color(248, 248, 248));
        tb_user.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        tb_user.setForeground(new java.awt.Color(137, 144, 155));
        tb_user.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Nama User", "Username", "No Telpon", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_user.setGridColor(new java.awt.Color(102, 102, 102));
        jScrollPane1.setViewportView(tb_user);

        jLabel9.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(39, 52, 71));
        jLabel9.setText("Role");

        role.setBackground(new java.awt.Color(153, 153, 255));
        role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Teknisi" }));
        role.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        role.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        role.setOpaque(false);

        telp_user.setBackground(new java.awt.Color(248, 248, 248));
        telp_user.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        telp_user.setForeground(new java.awt.Color(137, 144, 155));
        telp_user.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        telp_user.setSelectedTextColor(new java.awt.Color(137, 144, 155));

        jLabel10.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(39, 52, 71));
        jLabel10.setText("No Telp");

        btn_editUser.setBackground(new java.awt.Color(39, 52, 71));
        btn_editUser.setFont(new java.awt.Font("Poppins SemiBold", 0, 10)); // NOI18N
        btn_editUser.setForeground(new java.awt.Color(255, 255, 255));
        btn_editUser.setText("Edit");
        btn_editUser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        btn_editUser.setContentAreaFilled(false);
        btn_editUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_editUser.setOpaque(true);
        btn_editUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editUserActionPerformed(evt);
            }
        });

        btn_hapusUser.setBackground(new java.awt.Color(39, 52, 71));
        btn_hapusUser.setFont(new java.awt.Font("Poppins SemiBold", 0, 10)); // NOI18N
        btn_hapusUser.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapusUser.setText("Hapus");
        btn_hapusUser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        btn_hapusUser.setContentAreaFilled(false);
        btn_hapusUser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_hapusUser.setOpaque(true);
        btn_hapusUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusUserActionPerformed(evt);
            }
        });

        txt_cariUser.setBackground(new java.awt.Color(248, 248, 248));
        txt_cariUser.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        txt_cariUser.setForeground(new java.awt.Color(204, 204, 204));
        txt_cariUser.setText("Cari");
        txt_cariUser.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        txt_cariUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariUserActionPerformed(evt);
            }
        });
        txt_cariUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cariUserKeyReleased(evt);
            }
        });

        id_user.setBackground(new java.awt.Color(255, 255, 255));
        id_user.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        id_user.setForeground(new java.awt.Color(137, 144, 155));
        id_user.setText("0");
        id_user.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));

        javax.swing.GroupLayout form_datauserLayout = new javax.swing.GroupLayout(form_datauser);
        form_datauser.setLayout(form_datauserLayout);
        form_datauserLayout.setHorizontalGroup(
            form_datauserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form_datauserLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(form_datauserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(form_datauserLayout.createSequentialGroup()
                        .addGroup(form_datauserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10))
                        .addGap(128, 128, 128))
                    .addGroup(form_datauserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(role, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nama_user, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(username, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(password, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(telp_user, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, form_datauserLayout.createSequentialGroup()
                            .addComponent(btn_tambahUser, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_editUser, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_hapusUser, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(id_user, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(form_datauserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cariUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        form_datauserLayout.setVerticalGroup(
            form_datauserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form_datauserLayout.createSequentialGroup()
                .addGroup(form_datauserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form_datauserLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, form_datauserLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_cariUser, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(form_datauserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form_datauserLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_user, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nama_user, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telp_user, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(form_datauserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_tambahUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_editUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_hapusUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        card_layout.add(form_datauser, "form_datauser");

        form_datacustomer1.setBackground(new java.awt.Color(248, 248, 248));

        jLabel19.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(39, 52, 71));
        jLabel19.setText("Data Pelanggan");
        jLabel19.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jLabel20.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(39, 52, 71));
        jLabel20.setText("No Pelanggan");

        jLabel21.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(39, 52, 71));
        jLabel21.setText("Nama Pelanggan");

        nama_customer.setBackground(new java.awt.Color(248, 248, 248));
        nama_customer.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        nama_customer.setForeground(new java.awt.Color(137, 144, 155));
        nama_customer.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));

        telp_customer.setBackground(new java.awt.Color(248, 248, 248));
        telp_customer.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        telp_customer.setForeground(new java.awt.Color(137, 144, 155));
        telp_customer.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        telp_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telp_customerActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(39, 52, 71));
        jLabel22.setText("No HP");

        alamat_customer.setBackground(new java.awt.Color(248, 248, 248));
        alamat_customer.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        alamat_customer.setForeground(new java.awt.Color(137, 144, 155));
        alamat_customer.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));

        jLabel23.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(39, 52, 71));
        jLabel23.setText("Alamat Pelanggan");

        btn_tambahCustomer.setBackground(new java.awt.Color(39, 52, 71));
        btn_tambahCustomer.setFont(new java.awt.Font("Poppins SemiBold", 0, 10)); // NOI18N
        btn_tambahCustomer.setForeground(new java.awt.Color(248, 248, 248));
        btn_tambahCustomer.setText("Tambah");
        btn_tambahCustomer.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btn_tambahCustomer.setContentAreaFilled(false);
        btn_tambahCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_tambahCustomer.setOpaque(true);
        btn_tambahCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahCustomerActionPerformed(evt);
            }
        });

        jScrollPane4.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane4.setBorder(null);

        tb_customer.setBackground(new java.awt.Color(248, 248, 248));
        tb_customer.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        tb_customer.setForeground(new java.awt.Color(137, 144, 155));
        tb_customer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Nama Pelanggan", "Alamat Pelanggan", "No HP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_customer.setGridColor(new java.awt.Color(102, 102, 102));
        jScrollPane4.setViewportView(tb_customer);

        cari_customer.setBackground(new java.awt.Color(248, 248, 248));
        cari_customer.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        cari_customer.setForeground(new java.awt.Color(137, 144, 155));
        cari_customer.setText("Cari");
        cari_customer.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        cari_customer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cari_customerKeyReleased(evt);
            }
        });

        btn_editCustomer.setBackground(new java.awt.Color(39, 52, 71));
        btn_editCustomer.setFont(new java.awt.Font("Poppins SemiBold", 0, 10)); // NOI18N
        btn_editCustomer.setForeground(new java.awt.Color(248, 248, 248));
        btn_editCustomer.setText("Edit");
        btn_editCustomer.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btn_editCustomer.setContentAreaFilled(false);
        btn_editCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_editCustomer.setOpaque(true);
        btn_editCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editCustomerActionPerformed(evt);
            }
        });

        btn_hapusCustomer.setBackground(new java.awt.Color(39, 52, 71));
        btn_hapusCustomer.setFont(new java.awt.Font("Poppins SemiBold", 0, 10)); // NOI18N
        btn_hapusCustomer.setForeground(new java.awt.Color(248, 248, 248));
        btn_hapusCustomer.setText("Hapus");
        btn_hapusCustomer.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btn_hapusCustomer.setContentAreaFilled(false);
        btn_hapusCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_hapusCustomer.setOpaque(true);
        btn_hapusCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusCustomerActionPerformed(evt);
            }
        });

        id_customer.setBackground(new java.awt.Color(255, 255, 255));
        id_customer.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        id_customer.setForeground(new java.awt.Color(137, 144, 155));
        id_customer.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        id_customer.setEnabled(false);

        javax.swing.GroupLayout form_datacustomer1Layout = new javax.swing.GroupLayout(form_datacustomer1);
        form_datacustomer1.setLayout(form_datacustomer1Layout);
        form_datacustomer1Layout.setHorizontalGroup(
            form_datacustomer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form_datacustomer1Layout.createSequentialGroup()
                .addGroup(form_datacustomer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form_datacustomer1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(form_datacustomer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19)
                            .addComponent(jLabel22)
                            .addComponent(jLabel20)
                            .addComponent(jLabel23)
                            .addComponent(jLabel21)
                            .addComponent(nama_customer)
                            .addComponent(alamat_customer)
                            .addComponent(telp_customer)
                            .addGroup(form_datacustomer1Layout.createSequentialGroup()
                                .addComponent(btn_tambahCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_editCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_hapusCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(id_customer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, form_datacustomer1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cari_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        form_datacustomer1Layout.setVerticalGroup(
            form_datacustomer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form_datacustomer1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cari_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form_datacustomer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form_datacustomer1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nama_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alamat_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telp_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(form_datacustomer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambahCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapusCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(216, Short.MAX_VALUE))
        );

        card_layout.add(form_datacustomer1, "form_datacustomer2");

        jSplitPane2.setRightComponent(card_layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(137, 144, 155)));
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        cardLayout.show(card_layout, "card2");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(137, 144, 155)));
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        cardLayout.show(card_layout, "form_dataservice");

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(137, 144, 155)));
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        cardLayout.show(card_layout, "form_datacustomer2");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btn_tambahUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahUserActionPerformed
        if (btn_tambahUser.getText() == "Simpan") {
            try {
                if (validateDataUser()) {
                    UserManager.updateUser(userGlobal, Integer.parseInt(id_user.getText()));
                    initTableUser("");
                    resetFieldUser();
                    btn_tambahUser.setText("Tambah");
                    btn_hapusUser.setText("Hapus");
                    btn_editUser.setEnabled(true);
                }
            } catch (Exception e) {
                Logger.getLogger(form_datapelanggan.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            try {
                if (validateDataUser()) {
                    UserManager.insertUser(userGlobal);
                    initTableUser("");
                    resetFieldUser();
                }
            } catch (Exception e) {
                Logger.getLogger(form_datapelanggan.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }//GEN-LAST:event_btn_tambahUserActionPerformed

    private void btn_tambahServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahServiceActionPerformed
        // TODO add your handling code here:
        form_tambahservice a = new form_tambahservice();
        a.a = this;
        a.setVisible(true);
    }//GEN-LAST:event_btn_tambahServiceActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(137, 144, 155)));
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        cardLayout.show(card_layout, "form_datauser");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btn_editServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editServiceActionPerformed
        // TODO add your handling code here:
        if (btn_hapusService.getText() == "Batal") {
            btn_tambahService.setText("Tambah");
            btn_hapusService.setText("Hapus");
            btn_editService.setEnabled(true);
        } else {
            try {
                int selectedRow = tb_service.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Pilih baris dulu");
                } else {
                    Service service = listService.get(selectedRow);
                    form_editservice a = new form_editservice(service.getId_service());
                    a.a = this;
                    a.setVisible(true);
                }
            } catch (Exception e) {
                Logger.getLogger(form_menu.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }//GEN-LAST:event_btn_editServiceActionPerformed

    private void btn_hapusServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusServiceActionPerformed
        // TODO add your handling code here:
        btn_editService.setEnabled(false);
        try {
            int selectedRow = tb_service.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null ,"Pilih baris dulu");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin "
                        + "akan menghapus data ini ?", "Konfirmasi", 1);
                if (confirm == JOptionPane.YES_OPTION) {
                    Service service = listService.get(selectedRow);
                    ServiceManager.deleteService(service.getId_service());
                    initTableService("");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(form_menu.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btn_hapusServiceActionPerformed

    private void btn_tambahService3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahService3ActionPerformed
        // TODO add your handling code here:
        try {
            int selectedRow = tb_service.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null,"Pilih baris dulu");
            } else {
                Service service = listService.get(selectedRow);
                HashMap parameter = new HashMap();
                parameter.put("no_service", service.getId_service());
                parameter.put("tanggal_service", service.getCustomer_nama());
                parameter.put("nama_pelanggan", service.getCustomer_alamat());
                parameter.put("telpon", service.getCustomer_no_hp());
                parameter.put("nama_barang", service.getNama_barang());
                parameter.put("jenis_barang", service.getJenis_barang());
                parameter.put("kerusakan", service.getKerusakan());
                System.out.println(parameter.toString());
                JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream(""
                        + "/service/report/ReportService.jasper"), 
                        parameter, ConnectionHelper.getConnection());

                JasperViewer.viewReport(jp, false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }//GEN-LAST:event_btn_tambahService3ActionPerformed

    private void btn_editUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editUserActionPerformed
       
        try {
            int selectedRow = tb_user.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null,"Pilih baris dulu");
            } else {
                 btn_tambahUser.setText("Simpan");
        btn_hapusUser.setText("Batal");
        btn_editUser.setEnabled(false);
                User user = listUser.get(selectedRow);
                setUserToComponent(user.getId_user());
            }
        } catch (Exception e) {
            Logger.getLogger(form_menu.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btn_editUserActionPerformed

    private void btn_hapusUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusUserActionPerformed
        if (btn_hapusUser.getText() == "Batal") {
            btn_tambahUser.setText("Tambah");
            btn_hapusUser.setText("Hapus");
            btn_editUser.setEnabled(true);
            resetFieldUser();
        } else {
            try {
                int selectedRow = tb_user.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Pilih baris dulu");
                } else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin "
                            + "akan menghapus data ini ?", "Konfirmasi", 1);
                    if (confirm == JOptionPane.YES_OPTION) {
                        User user = listUser.get(selectedRow);
                        UserManager.deleteUser(user.getId_user());
                        initTableUser("");
                    }
                }
            } catch (Exception e) {
                Logger.getLogger(form_menu.class.getName()).log(Level.SEVERE, null, e);

            }

        }
    }//GEN-LAST:event_btn_hapusUserActionPerformed

    private void btn_tambahCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahCustomerActionPerformed
        // TODO add your handling code here:
        if (btn_tambahCustomer.getText() == "Simpan") {
            try {
                if (validateDataCustomer()) {
                    CustomerManager.updateCustomer(customerGlobal, 
                            Integer.parseInt(id_customer.getText()));
                    initTableCustomer("");
                    resetFieldCustomer();
                    btn_tambahCustomer.setText("Tambah");
                    btn_hapusCustomer.setText("Hapus");
                    btn_editCustomer.setEnabled(true);
                }
            } catch (Exception e) {
                Logger.getLogger(form_menu.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            try {
                if (validateDataCustomer()) {
                    CustomerManager.insertCustomer(customerGlobal);
                    initTableCustomer("");
                    resetFieldCustomer();
                }
            } catch (Exception e) {
                Logger.getLogger(form_menu.class.getName()).log(Level.SEVERE, null, e);

            }
        }
    }//GEN-LAST:event_btn_tambahCustomerActionPerformed

    private void btn_editCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editCustomerActionPerformed
        // TODO add your handling code here:
        btn_tambahCustomer.setText("Simpan");
        btn_hapusCustomer.setText("Batal");
        btn_editCustomer.setEnabled(false);
        try {
            int selectedRow = tb_customer.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null,"Pilih baris dulu");
            } else {
                Customer customer = listCustomer.get(selectedRow);
                setCustomerToComponent(customer.getId_customer());
            }
        } catch (Exception e) {
            Logger.getLogger(form_menu.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btn_editCustomerActionPerformed

    private void btn_hapusCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusCustomerActionPerformed
        if (btn_hapusCustomer.getText() == "Batal") {
            btn_tambahCustomer.setText("Tambah");
            btn_hapusCustomer.setText("Hapus");
            btn_editCustomer.setEnabled(true);

            resetFieldCustomer();
        } else {
            try {
                int selectedRow = tb_customer.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null,"Pilih baris dulu");
                } else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin "
                            + "akan menghapus data ini ?", "Konfirmasi", 1);
                    if (confirm == JOptionPane.YES_OPTION) {
                        Customer customer = listCustomer.get(selectedRow);
                        CustomerManager.deleteCustomer(customer.getId_customer());
                        initTableCustomer("");
                    }
                }
            } catch (Exception e) {
                Logger.getLogger(form_menu.class.getName()).log(Level.SEVERE, null, e);

            }

        }
    }//GEN-LAST:event_btn_hapusCustomerActionPerformed

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        // TODO add your handling code here:
        jButton3.setOpaque(true);
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        // TODO add your handling code here:
        jButton4.setOpaque(true);

    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        // TODO add your handling code here:
        jButton5.setOpaque(true);

    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        // TODO add your handling code here:
        jButton6.setOpaque(true);

    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        // TODO add your handling code here:
        jButton6.setOpaque(false);

    }//GEN-LAST:event_jButton6MouseExited

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        // TODO add your handling code here:
        jButton3.setOpaque(false);

    }//GEN-LAST:event_jButton3MouseExited

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        // TODO add your handling code here:
        jButton5.setOpaque(false);

    }//GEN-LAST:event_jButton5MouseExited

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
        // TODO add your handling code here:
        jButton4.setOpaque(false);
    }//GEN-LAST:event_jButton4MouseExited

    private void telp_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telp_customerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telp_customerActionPerformed

    private void txt_cariUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariUserActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_cariUserActionPerformed

    private void id_customer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_customer1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_id_customer1ActionPerformed

    private void txt_cariUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariUserKeyReleased
        // TODO add your handling code here:
        initTableUser(txt_cariUser.getText());
    }//GEN-LAST:event_txt_cariUserKeyReleased

    private void cari_customerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari_customerKeyReleased
        // TODO add your handling code here:
        initTableCustomer(cari_customer.getText());
    }//GEN-LAST:event_cari_customerKeyReleased

    private void id_customer1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_customer1KeyReleased
        // TODO add your handling code here:
        initTableService(id_customer1.getText());
    }//GEN-LAST:event_id_customer1KeyReleased

    private void btn_ambilServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ambilServiceActionPerformed
        // TODO add your handling code here:
        form_pengambilanservice a =  new form_pengambilanservice();
        a.a = this;
        a.setVisible(true);        
    }//GEN-LAST:event_btn_ambilServiceActionPerformed

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7MouseExited

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int logout = JOptionPane.showConfirmDialog(null, "Apakah anda yakin akan logout?");
        if(logout == JOptionPane.YES_OPTION){
            this.dispose();
            new form_login().setVisible(true);
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btn_tambahService1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahService1ActionPerformed
        // TODO add your handling code here:
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(137, 144, 155)));
        jButton5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(248, 248, 248)));
        cardLayout.show(card_layout, "form_dataservice");
    }//GEN-LAST:event_btn_tambahService1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamat_customer;
    private javax.swing.JButton btn_ambilService;
    private javax.swing.JButton btn_editCustomer;
    private javax.swing.JButton btn_editService;
    private javax.swing.JButton btn_editUser;
    private javax.swing.JButton btn_hapusCustomer;
    private javax.swing.JButton btn_hapusService;
    private javax.swing.JButton btn_hapusUser;
    private javax.swing.JButton btn_tambahCustomer;
    private javax.swing.JButton btn_tambahService;
    private javax.swing.JButton btn_tambahService1;
    private javax.swing.JButton btn_tambahService3;
    private javax.swing.JButton btn_tambahUser;
    private javax.swing.JPanel card_layout;
    private javax.swing.JTextField cari_customer;
    private javax.swing.JPanel form_datacustomer1;
    private javax.swing.JPanel form_dataservice;
    private javax.swing.JPanel form_datauser;
    private javax.swing.JLabel id_customer;
    private javax.swing.JTextField id_customer1;
    private javax.swing.JLabel id_user;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JLabel lbl_ambil;
    private javax.swing.JLabel lbl_online;
    private javax.swing.JLabel lbl_proses;
    private javax.swing.JLabel lbl_selesai;
    private javax.swing.JLabel lbl_totalService;
    private javax.swing.JPanel menu;
    private javax.swing.JTextField nama_customer;
    private javax.swing.JTextField nama_user;
    private javax.swing.JTextField password;
    private javax.swing.JPanel pnl1;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JTable tb_customer;
    private javax.swing.JTable tb_service;
    private javax.swing.JTable tb_serviceDashboard;
    private javax.swing.JTable tb_user;
    private javax.swing.JTextField telp_customer;
    private javax.swing.JTextField telp_user;
    private javax.swing.JTextField txt_cariUser;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
