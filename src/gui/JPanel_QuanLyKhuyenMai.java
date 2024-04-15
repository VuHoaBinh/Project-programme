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
import dao.KhuyenMai_DAO;
import entity.KhuyenMai;
import entity.TrangThaiPhong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M S I
 */
public class JPanel_QuanLyKhuyenMai extends javax.swing.JPanel implements ActionListener {

    private KhuyenMai_DAO km_DAO;

    /**
     * Creates new form JPanel_QuanLyNhanVien
     */
    public JPanel_QuanLyKhuyenMai() throws SQLException {
        initComponents();
        addEvents();
        AllListKhuyenMai();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Quản Lý Nhân Viên");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel_QuanLyKhuyenMai panel = null;
                try {
                    panel = new JPanel_QuanLyKhuyenMai();
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel_QuanLyKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.add(panel);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public void DeleteForm() {
        txt_maKhuyenMai.setText("");
        txt_maKhuyenMai.setEnabled(true);
        txt_maKhuyenMai.requestFocus();

        txt_giaTri.setText("");
        txt_ngayBatDau.setText("");
        txt_ngayKetThuc.setText("");
        txt_noiDung.setText("");
        cb_trangThai.setSelected(false);

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
        tbl_khuyenMai = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        frm_ThongTin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_ngayBatDau = new javax.swing.JTextField();
        txt_maKhuyenMai = new javax.swing.JTextField();
        txt_giaTri = new javax.swing.JTextField();
        txt_ngayKetThuc = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_noiDung = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        cb_trangThai = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        search_Engine = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        cb_tt = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btn_search = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(931, 800));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setLayout(new java.awt.BorderLayout());

        tbl_khuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khuyến mãi", "Giá trị", "Ngày bắt đầu", "Ngày kết thúc", "Nội dung", "Trạng Thái khuyến mãi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_khuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_khuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_khuyenMai);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel11.setBackground(new java.awt.Color(51, 255, 0));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Danh Sách Thông Tin Khuyến Mãi");
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

        jLabel1.setText("Mã khuyến mãi: ");

        jLabel6.setText("Giá trị:");

        jLabel7.setText("Ngày bắt đầu:");

        jLabel8.setText("Ngày kết thúc:");

        jLabel9.setText("Nội dung:");

        txt_noiDung.setColumns(20);
        txt_noiDung.setRows(5);
        jScrollPane2.setViewportView(txt_noiDung);

        jLabel2.setText("Trạng thái: ");

        cb_trangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_trangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frm_ThongTinLayout = new javax.swing.GroupLayout(frm_ThongTin);
        frm_ThongTin.setLayout(frm_ThongTinLayout);
        frm_ThongTinLayout.setHorizontalGroup(
            frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frm_ThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frm_ThongTinLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frm_ThongTinLayout.createSequentialGroup()
                        .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(frm_ThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 5, Short.MAX_VALUE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frm_ThongTinLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_ngayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(txt_maKhuyenMai)
                            .addComponent(txt_ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frm_ThongTinLayout.createSequentialGroup()
                                .addComponent(txt_giaTri)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_trangThai)
                                .addGap(18, 18, 18)))))
                .addGap(12, 12, 12))
        );
        frm_ThongTinLayout.setVerticalGroup(
            frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frm_ThongTinLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_maKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frm_ThongTinLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_giaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(frm_ThongTinLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(cb_trangThai)))
                .addGap(18, 18, 18)
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(frm_ThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frm_ThongTinLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frm_ThongTinLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        jPanel4.add(frm_ThongTin, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(350, 180));

        btnThem.setText("Thêm");
        btnThem.setToolTipText("");
        btnThem.setBackground(new Color(255, 102, 102));
        btnThem.setForeground(Color.WHITE);
        btnThem.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 16));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnHuy.setToolTipText("");
        btnHuy.setBackground(new Color(255, 102, 102));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 16));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnHuy.setToolTipText("");
        btnHuy.setBackground(new Color(255, 102, 102));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 16));

        btnHuy.setText("Hủy");
        btnHuy.setToolTipText("");
        btnHuy.setBackground(new Color(255, 102, 102));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 16));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
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
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jPanel4.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel4, java.awt.BorderLayout.EAST);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        search_Engine.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        search_Engine.setLayout(new javax.swing.BoxLayout(search_Engine, javax.swing.BoxLayout.X_AXIS));

        jPanel6.setPreferredSize(new java.awt.Dimension(386, 50));

        txt_search.setText("Mã khuyến mãi ....");
        txt_search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã nhân viên...");
        txt_search.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("./icon/home.svg"));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_search, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        search_Engine.add(jPanel6);

        cb_tt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_tt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ttActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("jLabel3");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("jLabel3");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("jLabel3");
        jLabel3.setPreferredSize(new java.awt.Dimension(20, 16));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cb_tt, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cb_tt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        search_Engine.add(jPanel7);

        btn_search.setText("Lọc");
        btnThem.setBackground(new Color(0,0,255));
        btnThem.setForeground(Color.WHITE);
        btnThem.setFont(new Font("Roboto Mono Medium", Font.PLAIN, 16));
        btn_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_searchMouseClicked(evt);
            }
        });
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_search, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        search_Engine.add(jPanel8);

        add(search_Engine, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void cb_ttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_ttActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        DeleteForm();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        int rowChoice = tbl_khuyenMai.getSelectedRow();

        String maKhuyenMai = txt_maKhuyenMai.getText();

        Double giaTri;
        giaTri = Double.parseDouble(txt_giaTri.getText());

        String ngayBatDau = txt_ngayBatDau.getText();
        String ngayKetThuc = txt_ngayKetThuc.getText();
        Boolean trangThaiKhuyenMai = cb_trangThai.isSelected();
        String noiDung = txt_noiDung.getText();

        String trangThaiKM = null;
        if (trangThaiKhuyenMai == true) {
            trangThaiKM = "Hết Hạn";
        } else {
            trangThaiKM = "Còn Hạn";
        }

        LocalDate ngayBatDauLocalDate = null;
        try {
            ngayBatDauLocalDate = LocalDate.parse(ngayBatDau, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        LocalDate ngayKetThucLocalDate = null;
        try {
            ngayKetThucLocalDate = LocalDate.parse(ngayKetThuc, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        KhuyenMai sp = new KhuyenMai(maKhuyenMai, trangThaiKhuyenMai, giaTri, ngayBatDauLocalDate, ngayKetThucLocalDate, noiDung);

        // Update 
        try {
            km_DAO.updateKhuyenMai(sp);

            if (tbl_khuyenMai.getModel() == null) {
                String[] arr = {"Mã khuyến mãi", "Giá trị", "Ngày bắt đầu", "Ngày kết thúc", "Nội dung", "Trạng thái khuyến mãi"};
                DefaultTableModel modelKhuyenMai = new DefaultTableModel(arr, 0);
                tbl_khuyenMai.setModel(modelKhuyenMai);

            }
            DefaultTableModel model = (DefaultTableModel) tbl_khuyenMai.getModel();

            model.setValueAt(maKhuyenMai, rowChoice, 0);
            model.setValueAt(giaTri, rowChoice, 1);
            model.setValueAt(ngayBatDauLocalDate, rowChoice, 2);
            model.setValueAt(ngayKetThucLocalDate, rowChoice, 3);
            model.setValueAt(noiDung, rowChoice, 4);
            model.setValueAt(trangThaiKM, rowChoice, 5);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nè 1!! btnCapNhatActionPerformed()");

        }
        DeleteForm();

    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void cb_trangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_trangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_trangThaiActionPerformed

    private void tbl_khuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_khuyenMaiMouseClicked
        // TODO add your handling code here:
        int rowSelection = tbl_khuyenMai.getSelectedRow();

        if (rowSelection >= 0 && rowSelection < tbl_khuyenMai.getRowCount()) {
            txt_maKhuyenMai.setText(tbl_khuyenMai.getValueAt(rowSelection, 0).toString());
            txt_maKhuyenMai.setEnabled(false);
            txt_giaTri.setText(tbl_khuyenMai.getValueAt(rowSelection, 1).toString());
            txt_ngayBatDau.setText(tbl_khuyenMai.getValueAt(rowSelection, 2).toString());
            txt_ngayKetThuc.setText(tbl_khuyenMai.getValueAt(rowSelection, 3).toString());
            txt_noiDung.setText(tbl_khuyenMai.getValueAt(rowSelection, 4).toString());

            String trangThaiKhuyenMai = tbl_khuyenMai.getValueAt(rowSelection, 5).toString();

            if (trangThaiKhuyenMai.equals("Hết Hạn")) {
                cb_trangThai.setSelected(true);
            } else {
                cb_trangThai.setSelected(false);
            }
            System.out.println("gui.JPanel_QuanLyDoAnUong.tbl_DoAnUongMouseClicked()");
        } else {
            System.out.println("Lỗi nè! tbl_DoAnUongMouseClicked()");
        }
    }//GEN-LAST:event_tbl_khuyenMaiMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        int rowChoice = tbl_khuyenMai.getSelectedRow();

        String maKhuyenMai = txt_maKhuyenMai.getText();

        Double giaTri;
        giaTri = Double.parseDouble(txt_giaTri.getText());

        String ngayBatDau = txt_ngayBatDau.getText();
        String ngayKetThuc = txt_ngayKetThuc.getText();
        Boolean trangThaiKhuyenMai = cb_trangThai.isSelected();
        String noiDung = txt_noiDung.getText();

        String trangThaiKM = null;
        if (trangThaiKhuyenMai == true) {
            trangThaiKM = "Hết Hạn";
        } else {
            trangThaiKM = "Còn Hạn";
        }

        LocalDate ngayBatDauLocalDate = null;
        try {
            ngayBatDauLocalDate = LocalDate.parse(ngayBatDau, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        LocalDate ngayKetThucLocalDate = null;
        try {
            ngayKetThucLocalDate = LocalDate.parse(ngayKetThuc, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        KhuyenMai sp = new KhuyenMai(maKhuyenMai, trangThaiKhuyenMai, giaTri, ngayBatDauLocalDate, ngayKetThucLocalDate, noiDung);

        // Update 
        try {
            km_DAO.createKhuyenMai(sp);

            if (tbl_khuyenMai.getModel() == null) {
                String[] arr = {"Mã khuyến mãi", "Giá trị", "Ngày bắt đầu", "Ngày kết thúc", "Nội dung", "Trạng thái khuyến mãi"};
                DefaultTableModel modelKhuyenMai = new DefaultTableModel(arr, 0);
                tbl_khuyenMai.setModel(modelKhuyenMai);

            }
            DefaultTableModel model = (DefaultTableModel) tbl_khuyenMai.getModel();

            model.addRow(new Object[]{maKhuyenMai, giaTri, ngayBatDau, ngayKetThuc, noiDung, trangThaiKM});

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nè 1!! btnCapNhatActionPerformed()");

        }
        DeleteForm();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btn_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_searchMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_btn_searchMouseClicked

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (txt_search.getText().equals("")) {
            try {
                AllListKhuyenMai();
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_QuanLyDoAnUong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            ConnectDB.getInstance().connect();
            String timkiem = txt_search.getText();

            // Xóa toàn bộ dữ liệu trong tbl_QL_NhanVien
            DefaultTableModel model = (DefaultTableModel) tbl_khuyenMai.getModel();
            model.setRowCount(0);

            km_DAO = new KhuyenMai_DAO();
            ArrayList<KhuyenMai> listKM = km_DAO.getPhongTheoMaKhuyenMai(timkiem);

            if (model.getColumnCount() == 0) {
                String[] arr = {"Mã khuyến mãi", "Giá trị", "Ngày bắt đầu", "Ngày kết thúc", "Nội dung", "Trạng thái khuyến mãi"};
                model.setColumnIdentifiers(arr);
            }

            for (KhuyenMai km : listKM) {
                
                Boolean trangThaiKhuyenMai = cb_trangThai.isSelected();
                

                String trangThaiKM = null;
                if (trangThaiKhuyenMai == true) {
                    trangThaiKM = "Hết Hạn";
                } else {
                    trangThaiKM = "Còn Hạn";
                }

                model.addRow(new Object[]{km.getMaKhuyenMai(), km.getGiaTri(), km.getNgayBatDau(), km.getNgayKetThuc(), trangThaiKM});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void AllListKhuyenMai() throws SQLException {
        tbl_khuyenMai.removeAll();
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        km_DAO = new KhuyenMai_DAO();
        ArrayList<KhuyenMai> listKM = km_DAO.getAllTableKhuyenMai();

        if (tbl_khuyenMai.getModel() == null) {
            String[] arr = {"Mã khuyến mãi", "Giá trị", "Ngày bắt đầu", "Ngày kết thúc", "Nội dung", "Trạng thái khuyến mãi"};
            DefaultTableModel modelKhuyenMai = new DefaultTableModel(arr, 0);
            tbl_khuyenMai.setModel(modelKhuyenMai);
        }
        DefaultTableModel model = (DefaultTableModel) tbl_khuyenMai.getModel();
        model.setRowCount(0);

        for (KhuyenMai km : listKM) {

            Boolean isTrangThaiKhuyenMai = km.isTrangThaiKhuyenMai();
            String trangThaiKhuyenMai;
            if (isTrangThaiKhuyenMai == true) {
                trangThaiKhuyenMai = "Hết Hạn";
            } else {
                trangThaiKhuyenMai = "Còn Hạn";
            }
            model.addRow(new Object[]{km.getMaKhuyenMai(), km.getGiaTri(), km.getNgayBatDau(), km.getNgayKetThuc(),
                km.getNoiDung(), trangThaiKhuyenMai});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    public javax.swing.JButton btn_search;
    private javax.swing.JCheckBox cb_trangThai;
    private javax.swing.JComboBox<String> cb_tt;
    private javax.swing.JPanel frm_ThongTin;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel search_Engine;
    private javax.swing.JTable tbl_khuyenMai;
    private javax.swing.JTextField txt_giaTri;
    public javax.swing.JTextField txt_maKhuyenMai;
    private javax.swing.JTextField txt_ngayBatDau;
    private javax.swing.JTextField txt_ngayKetThuc;
    private javax.swing.JTextArea txt_noiDung;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btn_search)) {
            System.out.println("gui.JPanel_QuanLyNhanVien.actionPerformed()");
        }
    }

    public void addEvents() {
        btn_search.addActionListener(this);
    }
}