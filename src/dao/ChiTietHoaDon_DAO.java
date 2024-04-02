/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author ASUS
 */
public class ChiTietHoaDon_DAO {

    /**
     *
     * @param getAllTablePhong
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<ChiTietHoaDon> getAllTableChiTietHoaDon() throws java.sql.SQLException {
        ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM ChiTietHoaDon";
        java.sql.Statement statement = con.createStatement();
        java.sql.ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maHoaDon = rs.getString("hoaDon");
            String maDoAnUong = rs.getString("doAnUong");
            int soLuong = rs.getInt("soLuong");
            String maPhong = rs.getString("phong");
            LocalDate ngayNhanPhong = rs.getDate("ngayNhanPhong").toLocalDate();
            LocalDate ngayTraPhong = rs.getDate("ngayTraPhong").toLocalDate();
            int soLuongNguoiO = rs.getInt("soLuongNguoiO");
            int soLuongDoUongTraVe = rs.getInt("soLuongDoUongTraVe");
            double tongTienThuePhong = rs.getDouble("tongTienThuePhong");
            double tongTienDichVu = rs.getDouble("tongTienDichVu");
            double tongThanhTien = rs.getDouble("tongThanhTien");
            double phuPhi = rs.getDouble("phuPhi");

            // Assuming you have constructors for ChiTietHoaDon that accept necessary parameters
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maHoaDon, maDoAnUong, soLuong, maPhong,
                    ngayNhanPhong, ngayTraPhong, soLuongNguoiO,
                    soLuongDoUongTraVe, tongTienThuePhong,
                    tongTienDichVu, tongThanhTien, phuPhi);
            dsChiTietHoaDon.add(chiTietHoaDon);
        }
        return dsChiTietHoaDon;
    }

    
}
