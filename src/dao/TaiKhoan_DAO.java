/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;
import gui.JFrame_Login;
import gui.JFrame_TrangChu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author M S I
 */
public class TaiKhoan_DAO {

    private static String tenNhanVien;
    NhanVien_DAO nv_dao = new NhanVien_DAO();

    public TaiKhoan_DAO() {
    }

    ;
    public static TaiKhoan_DAO getInstance() {
        return new TaiKhoan_DAO();
    }

    public void dNhap(JTextField txtTaiKhoan, JPasswordField txtMatKhau) throws Exception {
        PreparedStatement stmt = null;
        Connection con = ConnectDB.getConnection();
        try (Statement statement = con.createStatement()) {
            String user = txtTaiKhoan.getText();
            char[] pass = txtMatKhau.getPassword();
            String sql = "SELECT * FROM TaiKhoan WHERE nhanVien = ? AND matkhau = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, user);
            String pwd = new String(pass);
            stmt.setString(2, pwd);

            NhanVien nv;
            ResultSet rs = stmt.executeQuery();
            if (nv_dao.getNhanVienTheoMa(txtTaiKhoan.getText()).size() != 0) {
                nv = nv_dao.getNhanVienTheoMa(txtTaiKhoan.getText()).get(0);
            } else {
                JOptionPane.showMessageDialog(null, "Tên tài khoản hoặc mật khẩu không đúng");
                txtMatKhau.setText("");
                txtMatKhau.requestFocus();
                return;
            }

            if (rs.next()) {
                if (nv.isTrangThaiLamViec()) {
                    new JFrame_TrangChu(nv_dao.getNhanVienTheoMa(txtTaiKhoan.getText()).getFirst()).setVisible(true);
                    tenNhanVien = nv_dao.getHoTenNhanVienTheoMa(txtTaiKhoan.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Nhân viên đã không còn làm việc!");
                    txtMatKhau.setText("");
                    txtMatKhau.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tên tài khoản hoặc mật khẩu không đúng");
                txtMatKhau.setText("");
                txtMatKhau.requestFocus();
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoan_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void dMatKhau(String txtTaiKhoan, String txtMatKhau, String txtmatKhauMoi) throws Exception {
        String querySelect = "SELECT * FROM TaiKhoan WHERE nhanVien = ? AND matkhau = ?";
        String queryUpdate = "UPDATE TaiKhoan SET matkhau = ? WHERE nhanVien = ?";

        try (Connection con = ConnectDB.getConnection(); PreparedStatement stmtSelect = con.prepareStatement(querySelect); PreparedStatement stmtUpdate = con.prepareStatement(queryUpdate)) {

            // Set parameters for the SELECT statement
            stmtSelect.setString(1, txtTaiKhoan);
            stmtSelect.setString(2, txtMatKhau);

            ResultSet rs = stmtSelect.executeQuery();

            if (rs.next()) {
                // Set parameters for the UPDATE statement
                stmtUpdate.setString(1, txtmatKhauMoi);
                stmtUpdate.setString(2, txtTaiKhoan);

                int rowsUpdated = stmtUpdate.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Password updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update password.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tên tài khoản hoặc mật khẩu không đúng");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoan_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Database error: " + ex.getMessage());
        }
    }

    public static String getTenNhanVien() {
        return tenNhanVien;
    }

    public TaiKhoan selectById(String t) {
        TaiKhoan result = null;
        try {
            Connection con = (Connection) ConnectDB.getConnection();
            String sql = "SELECT * FROM TaiKhoan WHERE nhanVien=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String nhanVien = rs.getString("nhanVien");
                String matkhau = rs.getString("matkhau");
                TaiKhoan tk = new TaiKhoan(new NhanVien(nhanVien), matkhau);
                return tk;
            }
        } catch (Exception e) {
        }
        return result;
    }

    public void taoTaiKhoan(String tenTaiKhoan, String matKhau) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        con = ConnectDB.getConnection();
        String sql = "INSERT INTO TaiKhoan (nhanVien, matKhau) VALUES (?, ?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, tenTaiKhoan);
        stmt.setString(2, matKhau);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Tạo tài khoản thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Không thể tạo tài khoản.");
        }
    }
}
