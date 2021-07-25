/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.model.Customer;
import service.model.Service;
import service.model.ServiceManager;
import static service.ui.form_menu.listService;
import static service.ui.form_menu.tableModel;
import static service.ui.form_pilihpelanggan.listCustomer;

/**
 *
 * @author CodeVector
 */
public class form_pilihservice extends javax.swing.JFrame {

    public static DefaultTableModel tableModel;
    public static List<Service> listService;
    Service serviceGlobal;

    public form_pengambilanservice pengambilanService = null;

    /**
     * Creates new form form_pilihservice
     */
    public form_pilihservice() {
        initComponents();
        initTableService("");
    }

    public void initTableService(String q) {
        tb_service.setRowHeight(30);

        tableModel = (DefaultTableModel) tb_service.getModel();
        tableModel.setRowCount(0);

        listService = new ArrayList<>();

        listService = ServiceManager.showAllServiceProses(q);
        listService.forEach(service -> {
            tableModel.addRow(new Object[]{
                service.getId_service(),
                service.getId_customer(),
                service.getId_user(),
                service.getNama_barang(),
                service.getKerusakan(),
                service.getJenis_barang(),
                service.getTgl_service(),
                service.getTgl_selesai(),
                service.getSparepart(),
                service.getHarga_sparepart(),
                service.getHarga_service(),
                service.getTotal_harga(),
                service.getTotal_bayar(),
                service.getTotal_kembalian(),
                service.getTgl_ambil(),
                service.getStatus(),});
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_service = new javax.swing.JTable();
        btn_pilihService = new javax.swing.JButton();
        pencarian = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(248, 248, 248));

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));

        jLabel16.setFont(new java.awt.Font("Poppins SemiBold", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(39, 52, 71));
        jLabel16.setText("Pilih Service");
        jLabel16.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));

        jScrollPane3.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane3.setBorder(null);

        tb_service.setBackground(new java.awt.Color(248, 248, 248));
        tb_service.setFont(new java.awt.Font("Poppins SemiBold", 0, 12)); // NOI18N
        tb_service.setForeground(new java.awt.Color(137, 144, 155));
        tb_service.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No Service", "Nama Barang", "Kerusakan", "Nama Pelanggan", "Alamat Pelanggan", "No. HP", "Total Harga", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_service.setGridColor(new java.awt.Color(102, 102, 102));
        tb_service.setSelectionForeground(new java.awt.Color(137, 144, 155));
        jScrollPane3.setViewportView(tb_service);

        btn_pilihService.setBackground(new java.awt.Color(39, 52, 71));
        btn_pilihService.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btn_pilihService.setForeground(new java.awt.Color(248, 248, 248));
        btn_pilihService.setText("Pilih Service");
        btn_pilihService.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        btn_pilihService.setContentAreaFilled(false);
        btn_pilihService.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_pilihService.setOpaque(true);
        btn_pilihService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilihServiceActionPerformed(evt);
            }
        });

        pencarian.setBackground(new java.awt.Color(248, 248, 248));
        pencarian.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        pencarian.setForeground(new java.awt.Color(204, 204, 204));
        pencarian.setText("Cari");
        pencarian.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        pencarian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pencarianKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(btn_pilihService, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_pilihService, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_pilihServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilihServiceActionPerformed
        // TODO add your handling code here:
        try {
            int selectedRow = tb_service.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "", "Pilih baris dulu", 2);
            } else {
                String nama = tb_service.getValueAt(selectedRow, 1).toString();
                Service service = listService.get(selectedRow);
                System.out.println(this.pengambilanService.toString());
                System.out.println(service.getId_service());
                pengambilanService.setServiceToComponent(service.getId_service());
                this.dispose();
            }
        } catch (Exception e) {
            Logger.getLogger(form_pilihpelanggan.class.getName()).log(Level.SEVERE, null, e);

        }

    }//GEN-LAST:event_btn_pilihServiceActionPerformed

    private void pencarianKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pencarianKeyReleased
        // TODO add your handling code here:
        initTableService(pencarian.getText());
    }//GEN-LAST:event_pencarianKeyReleased

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
            java.util.logging.Logger.getLogger(form_pilihservice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_pilihservice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_pilihservice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_pilihservice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_pilihservice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_pilihService;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField pencarian;
    private javax.swing.JTable tb_service;
    // End of variables declaration//GEN-END:variables
}