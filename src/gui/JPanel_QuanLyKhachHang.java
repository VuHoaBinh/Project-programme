/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import connectDB.ConnectDB;
import entity.KhachHang;
import dao.KhachHang_DAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import static java.time.LocalDateTime.now;
import static java.time.Year.now;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M S I
 */
public class JPanel_QuanLyKhachHang extends javax.swing.JPanel implements ActionListener, MouseListener {

    private String id;

    /**
     * Creates new form JPanel_QuanLyNhanVien
     */
    public JPanel_QuanLyKhachHang() throws SQLException {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
        }
        initComponents();
        addEvents();
        loadData();
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_KhachHang = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        frm_ThongTin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_CCCD = new javax.swing.JTextField();
        txt_MaKH = new javax.swing.JTextField();
        txt_Ten = new javax.swing.JTextField();
        rd_nu = new javax.swing.JRadioButton();
        rd_nam = new javax.swing.JRadioButton();
        rd_dsd = new javax.swing.JRadioButton();
        rd_bt = new javax.swing.JRadioButton();
        btn_ngaySinh = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Rong = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        search_Engine = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txt_maCanTim = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        cb_gt = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cb_tt = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btn_Loc = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(931, 800));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 0));
        jPanel3.setLayout(new java.awt.BorderLayout());

        String[] colHeader = {"Mã khách hàng", "Họ và Tên", "Giới tính", "CCCD", "Ngày sinh", "Trạng thái khách hàng"};
        modelKhachHang = new DefaultTableModel(colHeader, 0);
        tbl_KhachHang.setModel(modelKhachHang);
        jScrollPane1.setViewportView(tbl_KhachHang);
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        tbl_KhachHang.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

        // Tạo renderer để căn trái cho cột thứ 6
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        // Căn giữa các cột cụ thể
        tbl_KhachHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbl_KhachHang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbl_KhachHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbl_KhachHang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbl_KhachHang.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbl_KhachHang.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel11.setBackground(new java.awt.Color(51, 255, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Danh Sách Thông Tin Khách Hàng");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel11.setMaximumSize(new java.awt.Dimension(270, 25));
        jLabel11.setMinimumSize(new java.awt.Dimension(270, 25));
        jLabel11.setPreferredSize(new java.awt.Dimension(265, 25));
        jPanel3.add(jLabel11, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        frm_ThongTin.setPreferredSize(new java.awt.Dimension(350, 0));

        jLabel1.setText("SĐT:");

        jLabel2.setText("Giới tính:");

        jLabel6.setText("Tên:");

        jLabel7.setText("CCCD:");

        jLabel8.setText("Ngày sinh:");

        jLabel9.setText("Trạng Thái:");

        rd_nu.setText("Nữ");

        rd_nam.setText("Nam");
        rd_nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_namActionPerformed(evt);
            }
        });

        rd_dsd.setText("Danh sách đen");

        rd_bt.setText("Bình thường");
        rd_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_btActionPerformed(evt);
            }
        });

        btn_ngaySinh.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout frm_ThongTinLayout = new javax.swing.GroupLayout(frm_ThongTin);
        frm_ThongTin.setLayout(frm_ThongTinLayout);
        frm_ThongTinLayout.setHorizontalGroup(
            frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frm_ThongTinLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(frm_ThongTinLayout.createSequentialGroup()
                        .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(frm_ThongTinLayout.createSequentialGroup()
                                .addComponent(rd_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rd_nu, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_Ten)
                            .addComponent(txt_MaKH)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frm_ThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frm_ThongTinLayout.createSequentialGroup()
                        .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(frm_ThongTinLayout.createSequentialGroup()
                                .addComponent(rd_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rd_dsd, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_ngaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(16, 16, 16))
        );
        frm_ThongTinLayout.setVerticalGroup(
            frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frm_ThongTinLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rd_nu)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rd_nam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(txt_CCCD))
                .addGap(18, 18, 18)
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rd_dsd)
                    .addComponent(rd_bt)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(btn_ngaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(252, Short.MAX_VALUE))
        );

        jPanel4.add(frm_ThongTin, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(350, 160));

        btnThem.setText("Thêm");
        btnThem.setToolTipText("");
        btnThem.setBackground(new Color(0, 102, 102));
        btnThem.setForeground(Color.WHITE);
        btnThem.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 16));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btn_Xoa.setText("Danh sách đen");
        btn_Xoa.setBackground(new Color(0, 102, 102));
        btn_Xoa.setForeground(Color.WHITE);
        btn_Xoa.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 16));
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_Sua.setText("Cập nhật");
        btn_Sua.setBackground(new Color(0, 102, 102));
        btn_Sua.setForeground(Color.WHITE);
        btn_Sua.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 16));

        btn_Rong.setText("Xóa rỗng");
        btn_Rong.setBackground(new Color(0, 102, 102));
        btn_Rong.setForeground(Color.WHITE);
        btn_Rong.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 16));
        btn_Rong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Rong, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(btn_Xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Rong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel4.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel5.setLayout(new java.awt.BorderLayout());
        jPanel4.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel9.setLayout(new java.awt.BorderLayout());
        jPanel4.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel4, java.awt.BorderLayout.EAST);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        search_Engine.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        search_Engine.setLayout(new javax.swing.BoxLayout(search_Engine, javax.swing.BoxLayout.X_AXIS));

        jPanel6.setPreferredSize(new java.awt.Dimension(386, 50));

        txt_maCanTim.setText("");
        txt_maCanTim.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã khách hàng...");
        txt_maCanTim.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("./icon/search_1.svg"));
        txt_maCanTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maCanTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(txt_maCanTim, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(txt_maCanTim, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        search_Engine.add(jPanel6);

        cb_gt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam", "Nữ"}));
        cb_gt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_gtActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Trạng Thái:");

        cb_tt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Bình thường", "Danh sách đen"}));
        cb_tt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ttActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Giới tính:");
        jLabel3.setPreferredSize(new java.awt.Dimension(20, 16));

        btn_Loc.setText("Lọc");

        btn_refresh.setIcon(new FlatSVGIcon("./icon/reload.svg"));
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_gt, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(cb_tt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175)
                .addComponent(btn_Loc, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cb_gt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cb_tt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        search_Engine.add(jPanel7);

        add(search_Engine, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void cb_gtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_gtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_gtActionPerformed

    private void cb_ttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_ttActionPerformed

    private void rd_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_btActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_btActionPerformed

    private void btn_RongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_RongActionPerformed

    private void rd_namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_namActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rd_namActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void txt_maCanTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maCanTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maCanTimActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_XoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    public javax.swing.JButton btn_Loc;
    private javax.swing.JButton btn_Rong;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Xoa;
    private com.toedter.calendar.JDateChooser btn_ngaySinh;
    public javax.swing.JButton btn_refresh;
    private javax.swing.JComboBox<String> cb_gt;
    private javax.swing.JComboBox<String> cb_tt;
    private javax.swing.JPanel frm_ThongTin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rd_bt;
    private javax.swing.JRadioButton rd_dsd;
    private javax.swing.JRadioButton rd_nam;
    private javax.swing.JRadioButton rd_nu;
    private javax.swing.JPanel search_Engine;
    private javax.swing.JTable tbl_KhachHang;
    private javax.swing.JTextField txt_CCCD;
    public javax.swing.JTextField txt_MaKH;
    private javax.swing.JTextField txt_Ten;
    private javax.swing.JTextField txt_maCanTim;
    // End of variables declaration//GEN-END:variables
    private KhachHang_DAO kh_dao = new KhachHang_DAO();
    private DefaultTableModel modelKhachHang;
    javax.swing.ButtonGroup gr_gt = new javax.swing.ButtonGroup();
    javax.swing.ButtonGroup gr_tt = new javax.swing.ButtonGroup();

    public KhachHang taoKH() {
        String ma = txt_MaKH.getText().toString();
        String ten = txt_Ten.getText().toString();
        boolean gioiTinh = rd_nam.isSelected();
        String cccd = txt_CCCD.getText().toString();
        LocalDate ngaySinh = convertToLocalDate(btn_ngaySinh.getDate());
        boolean trangThai = rd_bt.isSelected();
        
        return new KhachHang(ma, ten, gioiTinh, cccd, ngaySinh, trangThai);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btn_Xoa)) {
            int row = tbl_KhachHang.getSelectedRow();
            String ma = tbl_KhachHang.getValueAt(row, 0).toString();
            kh_dao.updateTrangThaiKhachHang(ma);
            delData();
            try {
                loadData();
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (o.equals(btnThem)) {
            if (kiemTraCuPhap()) {
                KhachHang kh = taoKH();
                String gt = "";
                String tt = "";
                modelKhachHang.addRow(new Object[]{kh.getMaKhachHang(), kh.getHoTenKhachHang(), gt = kh.isGioiTinh() ? "Nam" : "Nữ",
                    kh.getCCCD(),kh.getNgaySinh(), tt = kh.isTrangThaiKhachHang()? "Bình thường" : "Danh sách đen"});
                try {
//                    String imagePath_relative = "/gui/emp_pic/" + nv.getMaNhanVien() + ".png";
//                    nv.setHinhAnh("/gui/emp_pic/" + nv.getMaNhanVien() + ".png");
                    kh_dao.createKhachHang(kh);
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel_QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (o.equals(btn_Loc)) {
            try {
                ArrayList<KhachHang> list;
                if (txt_maCanTim.getText().equals("")) {
                    list = kh_dao.getAllTableKhachHang();
                    delData();
                } else {
                    list = kh_dao.getDsKhachHangTheoMa(txt_maCanTim.getText());
                    delData();
                }
                System.out.println(list);

                String gt = "";
                String tt = "";
                for (KhachHang kh : list) {
                    modelKhachHang.addRow(new Object[]{
                        kh.getMaKhachHang(),
                        kh.getHoTenKhachHang(),
                        gt = kh.isGioiTinh() ? "Nam" : "Nữ",
                        kh.getCCCD(),
                        kh.getNgaySinh(),
                        tt =kh.isTrangThaiKhachHang()? "Bình thường" : "Danh sách đen",
                    });
                }
                for (int i = 0; i < tbl_KhachHang.getRowCount(); i++) {
                    if (cb_gt.getSelectedIndex() == 1 && !tbl_KhachHang.getValueAt(i, 2).toString().equals("Nam")) {
                        modelKhachHang.removeRow(i);
                        i--;
                    }
                    if (cb_gt.getSelectedIndex() == 2 && tbl_KhachHang.getValueAt(i, 2).toString().equals("Nam")) {
                        modelKhachHang.removeRow(i);
                        i--;
                    }
                }
                for (int i = 0; i < tbl_KhachHang.getRowCount(); i++) {
                    if (cb_tt.getSelectedIndex() == 1 && tbl_KhachHang.getValueAt(i, 5).toString().equals("Danh sách đen")) {
                        modelKhachHang.removeRow(i);
                        i--;
                    }
                    if (cb_tt.getSelectedIndex() == 2 && !tbl_KhachHang.getValueAt(i, 5).toString().equals("Danh sách đen")) {
                        modelKhachHang.removeRow(i);
                        i--;
                    }
                }
//                for (int i = 0; i < tbl_KhachHang.getRowCount(); i++) {
//                    if (cb_tt.getSelectedIndex() == 1 && tbl_KhachHang.getValueAt(i, 5).toString().equals("Danh sách đen")) {
//                        modelKhachHang.removeRow(i);
//                        i--;
//                    }
//                    if (cb_tt.getSelectedIndex() == 2 && !tbl_KhachHang.getValueAt(i, 5).toString().equals("Danh sách đen")) {
//                        modelKhachHang.removeRow(i);
//                        i--;
//                    }
//                }
                if (tbl_KhachHang.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Không tìm thấy!",
                            "Thông báo không tìm thấy",
                            JOptionPane.WARNING_MESSAGE
                    );
                    modelKhachHang.setRowCount(0);
                    this.revalidate();
                    this.repaint();
                }
            } catch (IOException ex) {
                Logger.getLogger(JPanel_QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (o.equals(btn_Rong)) {
            txt_MaKH.setText("");
            txt_Ten.setText("");
            rd_nam.setSelected(true);
            txt_CCCD.setText("");
            rd_bt.setSelected(true);
            btn_ngaySinh.setDate(null);
        }
        if (o.equals(btn_refresh)) {
            delData();
            try {
                loadData();
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (o.equals(btn_Sua)) {
            int selectedRow = tbl_KhachHang.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để sửa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (kiemTraCuPhap()) {
                KhachHang new_nv = taoKH();
                kh_dao.updateKhachHang(new_nv);
                delData();
                try {
                    loadData();
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel_QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void delData() {
        DefaultTableModel dm = (DefaultTableModel) tbl_KhachHang.getModel();
        dm.getDataVector().removeAllElements();
    }

    public boolean kiemTraCuPhap() {
        if (txt_Ten.getText().isEmpty() || !txt_Ten.getText().matches("^([\\p{L}\\s']{2,})+([\\p{L}\\s']{2,})$")) {
            JOptionPane.showMessageDialog(this, "Nhập tên sai cú pháp");
            return false;
        }
//        LocalDate ngaySinh = convertToLocalDate(btn_ngaySinh.getDate());
//        String ngaySinhBangChu = btn_ngaySinh.getDateFormatString();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        if (btn_ngaySinh.getDate() == null) {
//			JOptionPane.showMessageDialog(this, "Không được để ngày sinh trống");
//			return false;
//		} else if (LocalDate.now().getYear() - LocalDate.parse(ngaySinhBangChu, formatter).getYear() <= 17) {
//			JOptionPane.showMessageDialog(this, "Tuổi phải trên 18");
//			return false;
//		}
        return true;
    }

    public void addEvents() {
        btn_refresh.addActionListener(this);
        tbl_KhachHang.addMouseListener(this);
        btnThem.addActionListener(this);
        btn_Xoa.addActionListener(this);
        btn_Rong.addActionListener(this);
        btn_Sua.addActionListener(this);
        btn_Loc.addActionListener(this);
    }

    public void loadData() throws SQLException {
        ArrayList<KhachHang> list =kh_dao.getAllTableKhachHang();
        for (KhachHang kh : list) {
            modelKhachHang.addRow(new Object[]{kh.getMaKhachHang(), kh.getHoTenKhachHang(), kh.isGioiTinh() ? "Nam" : "Nữ",
                kh.getCCCD(),kh.getNgaySinh(), kh.isTrangThaiKhachHang()? "Bình thường" : "Danh sách đen",});
        }
    }
    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tbl_KhachHang.getSelectedRow();

        if (row >= 0 && row < tbl_KhachHang.getRowCount()) {
            txt_MaKH.setText(modelKhachHang.getValueAt(row, 0).toString());
            txt_Ten.setText(modelKhachHang.getValueAt(row, 1).toString());
            rd_nam.setSelected((modelKhachHang.getValueAt(row, 2) == "Nam"));
            rd_nu.setSelected((modelKhachHang.getValueAt(row, 2) != "Nam"));
            txt_CCCD.setText(modelKhachHang.getValueAt(row, 3).toString());
            LocalDate ngaySinhLocalDate = (LocalDate) modelKhachHang.getValueAt(row, 4);
            Date date = Date.from(ngaySinhLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            btn_ngaySinh.setDate(date);
            rd_bt.setSelected((modelKhachHang.getValueAt(row, 5) == "Bình thường"));
            rd_dsd.setSelected((modelKhachHang.getValueAt(row, 5) == "Danh sách đen"));
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
