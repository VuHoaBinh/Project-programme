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
    };
    public static TaiKhoan_DAO getInstance(){
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

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                new JFrame_TrangChu(nv_dao.getNhanVienTheoMa(txtTaiKhoan.getText()).getFirst()).setVisible(true);
                new JFrame_Login().setVisible(false);
                tenNhanVien = nv_dao.getHoTenNhanVienTheoMa(txtTaiKhoan.getText());
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

    public void dMatKhau(JTextField txtTaiKhoan, JPasswordField txtMatKhau, JPasswordField txtmatKhauMoi) throws Exception {
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
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
//                TaiKhoan curUser = new TaiKhoan(new NhanVien(txtTaiKhoan.getText()));
                String sqlUpdate = "UPDATE TaiKhoan SET matkhau = ? WHERE nhanVien = ?";
                stmt = con.prepareStatement(sqlUpdate);
                stmt.setString(1, new String(txtmatKhauMoi.getPassword()));
                stmt.setString(2, user);
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
    public static String getTenNhanVien(){
        return tenNhanVien;
    }
    
//    public TaiKhoan selectById(String t) {
//        TaiKhoan result = null;
//        try {
//            Connection con = (Connection) ConnectDB.getConnection();
//            String sql = "SELECT * FROM TaiKhoan WHERE nhanVien=?";
//            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
//            pst.setString(1, t);
//            ResultSet rs = (ResultSet) pst.executeQuery();
//            while(rs.next()){
//                String nhanVien = rs.getString("nhanVien");
//                String matkhau = rs.getString("matkhau");
//                TaiKhoan tk = new TaiKhoan(nhanVien, matkhau);
//                return result;
//            }
//            ConnectDB.disconnect();
//        } catch (Exception e) {
//        }
//        return result;
//    }
}
