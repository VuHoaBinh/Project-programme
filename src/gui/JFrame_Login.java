/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import connectDB.ConnectDB;
import dao.TaiKhoan_DAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class JFrame_Login extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form JFrame_Login
     *
     * @throws java.lang.Exception
     */
    public JFrame_Login() throws Exception {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initComponents();
        setResizable(false);
        addEvents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_DangNhap = new javax.swing.JPanel();
        txt_dangNhap = new javax.swing.JLabel();
        btn_dangNhap = new javax.swing.JButton();
        lb_matKhau = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        txt_taiKhoan = new javax.swing.JTextField();
        lb_tenNhanVien = new javax.swing.JLabel();
        UI_login = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pn_DangNhap.setLayout(null);

        txt_dangNhap.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        txt_dangNhap.setForeground(new java.awt.Color(255, 255, 255));
        txt_dangNhap.setText("Đăng nhập");
        pn_DangNhap.add(txt_dangNhap);
        txt_dangNhap.setBounds(140, 220, 290, 120);

        btn_dangNhap.setBackground(new java.awt.Color(51, 153, 255));
        btn_dangNhap.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btn_dangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btn_dangNhap.setText("Đăng nhập");
        btn_dangNhap.setFocusPainted(false);
        btn_dangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangNhapActionPerformed(evt);
            }
        });
        pn_DangNhap.add(btn_dangNhap);
        btn_dangNhap.setBounds(140, 530, 300, 50);

        lb_matKhau.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lb_matKhau.setForeground(new java.awt.Color(255, 255, 255));
        lb_matKhau.setText("Mật khẩu:");
        pn_DangNhap.add(lb_matKhau);
        lb_matKhau.setBounds(120, 460, 110, 40);

        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        pn_DangNhap.add(txt_password);
        txt_password.setBounds(250, 460, 220, 40);

        txt_taiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_taiKhoanActionPerformed(evt);
            }
        });
        pn_DangNhap.add(txt_taiKhoan);
        txt_taiKhoan.setBounds(250, 380, 220, 40);

        lb_tenNhanVien.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lb_tenNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lb_tenNhanVien.setText("Tên nhân viên:");
        pn_DangNhap.add(lb_tenNhanVien);
        lb_tenNhanVien.setBounds(80, 380, 160, 40);

        UI_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/screen_Login.png"))); // NOI18N
        pn_DangNhap.add(UI_login);
        UI_login.setBounds(0, 0, 940, 760);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_DangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void btn_dangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_dangNhapActionPerformed

    private void txt_taiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_taiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_taiKhoanActionPerformed

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
            java.util.logging.Logger.getLogger(JFrame_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFrame_Login().setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel UI_login;
    private javax.swing.JButton btn_dangNhap;
    private javax.swing.JLabel lb_matKhau;
    private javax.swing.JLabel lb_tenNhanVien;
    private javax.swing.JPanel pn_DangNhap;
    private javax.swing.JLabel txt_dangNhap;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_taiKhoan;
    // End of variables declaration//GEN-END:variables
    private TaiKhoan_DAO tk_dao = new TaiKhoan_DAO();

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btn_dangNhap)) {
            if (txt_taiKhoan.getText().equalsIgnoreCase("")||txt_password.getPassword().equals("")) {
                JOptionPane.showMessageDialog(this, "Tên tài khoản hoặc mật khẩu không được để trống");
                txt_taiKhoan.requestFocus();
            } else {
                try {
                    tk_dao.dNhap(txt_taiKhoan, txt_password);
                } catch (Exception ex) {
                    Logger.getLogger(JFrame_Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void addEvents(){
        btn_dangNhap.addActionListener(this);
    }
}
