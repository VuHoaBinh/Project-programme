/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.Phong_DAO;
import entity.Phong;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author M S I
 */
public class JPanel_InfoPhong extends javax.swing.JPanel {

    private Phong_DAO p_dao;

    /**
     * Creates new form InfoPhong
     */
    public JPanel_InfoPhong(String maPhong) throws IOException, SQLException {
        initComponents();
        loadData(maPhong);
    }

    public void loadData(String maPhong) throws IOException, SQLException {
        p_dao = new Phong_DAO();
        Phong phong = p_dao.getPhongTheoTenPhong(maPhong).getFirst();
        txtTen.setText(phong.getTenPhong());
        String dienTich = String.format("%.0f" + " m^2", phong.getDienTich());
        txtDienTich.setText(dienTich);
        txtSoGiuong.setText(String.valueOf(phong.getSoGiuong()));
        txtView.setText(phong.getView());
        txtLoaiPhong.setText(phong.getLoaiPhong().toString());
        txtTrangThai.setText(phong.getTrangThaiPhong().toString());
        if (phong.isGiuongPhu()) {
            rdCoGiuong.setSelected(true);
        } else {
            rdKhongGiuong.setSelected(true);
        }
        if (phong.isHutThuoc()) {
            rdCoHut.setSelected(true);
        } else  {
            rdKhongHut.setSelected(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSoGiuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDienTich = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtView = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdCoGiuong = new javax.swing.JRadioButton();
        rdKhongGiuong = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        rdCoHut = new javax.swing.JRadioButton();
        rdKhongHut = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtLoaiPhong = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 153, 153));
        setFocusable(false);
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);
        setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mã Phòng");
        add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jLabel2.setText("Tên:");

        txtTen.setEditable(false);
        txtTen.setEnabled(false);
        txtTen.setFocusable(false);
        txtTen.setRequestFocusEnabled(false);
        txtTen.setVerifyInputWhenFocusTarget(false);

        jLabel3.setText("Số Giường:");

        txtSoGiuong.setEditable(false);
        txtSoGiuong.setEnabled(false);
        txtSoGiuong.setFocusable(false);
        txtSoGiuong.setRequestFocusEnabled(false);

        jLabel4.setText("Giường Phụ:");

        txtDienTich.setEditable(false);
        txtDienTich.setEnabled(false);
        txtDienTich.setFocusable(false);
        txtDienTich.setRequestFocusEnabled(false);

        jLabel5.setText("View:");

        txtView.setEditable(false);
        txtView.setEnabled(false);
        txtView.setFocusable(false);
        txtView.setRequestFocusEnabled(false);

        jLabel6.setText("Diện Tích:");

        rdCoGiuong.setText("Có");
        rdCoGiuong.setEnabled(false);
        rdCoGiuong.setFocusPainted(false);
        rdCoGiuong.setVerifyInputWhenFocusTarget(false);
        rdCoGiuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdCoGiuongActionPerformed(evt);
            }
        });

        rdKhongGiuong.setText("Không");
        rdKhongGiuong.setEnabled(false);
        rdKhongGiuong.setFocusPainted(false);
        rdKhongGiuong.setFocusable(false);

        jLabel7.setText("Hút Thuốc:");

        rdCoHut.setText("Có");
        rdCoHut.setEnabled(false);
        rdCoHut.setFocusPainted(false);
        rdCoHut.setVerifyInputWhenFocusTarget(false);

        rdKhongHut.setText("Không");
        rdKhongHut.setEnabled(false);
        rdKhongHut.setFocusPainted(false);
        rdKhongHut.setVerifyInputWhenFocusTarget(false);

        jLabel8.setText("Loại Phòng");

        txtLoaiPhong.setEditable(false);
        txtLoaiPhong.setEnabled(false);
        txtLoaiPhong.setFocusable(false);
        txtLoaiPhong.setRequestFocusEnabled(false);

        jLabel9.setText("Trạng Thái:");

        txtTrangThai.setEditable(false);
        txtTrangThai.setEnabled(false);
        txtTrangThai.setFocusable(false);
        txtTrangThai.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                                .addGap(51, 51, 51))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLoaiPhong)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdCoGiuong)
                                .addGap(18, 18, 18)
                                .addComponent(rdKhongGiuong)
                                .addGap(0, 115, Short.MAX_VALUE))
                            .addComponent(txtDienTich)
                            .addComponent(txtTen))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtTrangThai))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(rdCoHut)
                        .addGap(18, 18, 18)
                        .addComponent(rdKhongHut)
                        .addGap(0, 172, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtView)
                            .addComponent(txtSoGiuong))))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoGiuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDienTich, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtView, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdCoGiuong)
                    .addComponent(rdKhongGiuong)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdCoHut)
                    .addComponent(rdKhongHut))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void rdCoGiuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdCoGiuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdCoGiuongActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JRadioButton rdCoGiuong;
    private javax.swing.JRadioButton rdCoHut;
    private javax.swing.JRadioButton rdKhongGiuong;
    private javax.swing.JRadioButton rdKhongHut;
    private javax.swing.JTextField txtDienTich;
    private javax.swing.JTextField txtLoaiPhong;
    private javax.swing.JTextField txtSoGiuong;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTrangThai;
    private javax.swing.JTextField txtView;
    // End of variables declaration//GEN-END:variables
}
