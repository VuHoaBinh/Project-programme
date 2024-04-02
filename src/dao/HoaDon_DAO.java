/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
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
public class HoaDon_DAO {

    /**
     *
     * @param getAllTableHoaDon
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<HoaDon> getAllTableHoaDon() throws java.sql.SQLException {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM HoaDon";
        java.sql.Statement statement = con.createStatement();
        java.sql.ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maHoaDon = rs.getString("maHoaDon");
            String maKhachHang = rs.getString("khachHang");
            String maKhuyenMai = rs.getString("khuyenMai");
            String maNhanVien = rs.getString("nhanVien");
            LocalDate ngayLapHoaDon = rs.getDate("ngayLapHoaDon").toLocalDate();
            double thue = rs.getDouble("thue");
            double tongThanhTienBanDau = rs.getDouble("tongThanhTienBanDau");
            double tongThanhTienPhaiTra = rs.getDouble("tongThanhTienPhaiTra");

            // Assuming you have appropriate constructors for KhachHang, KhuyenMai, and NhanVien
            KhachHang khachHang = new KhachHang(maKhachHang); // Replace with correct instantiation
            KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai); // Replace with correct instantiation
            NhanVien nhanVien = new NhanVien(maNhanVien); // Replace with correct instantiation

            HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, khuyenMai, nhanVien, ngayLapHoaDon, thue, tongThanhTienBanDau, tongThanhTienPhaiTra);
            dsHoaDon.add(hoaDon);
        }
        return dsHoaDon;
    }

    /**
     *
     * @param maHoaDon
     * @param getPhongTheoMaHoaDon
     * @return
     * @throws IOException
     * @throws java.sql.SQLException
     */
    public ArrayList<HoaDon> getPhongTheoMaHoaDon(String maHoaDon) throws IOException, java.sql.SQLException {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM HoaDon WHERE maHoaDon = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, maHoaDon);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maKhachHang = rs.getString("khachHang");
                String maKhuyenMai = rs.getString("khuyenMai");
                String maNhanVien = rs.getString("nhanVien");
                LocalDate ngayLapHoaDon = rs.getDate("ngayLapHoaDon").toLocalDate();
                double thue = rs.getDouble("thue");
                double tongThanhTienBanDau = rs.getDouble("tongThanhTienBanDau");
                double tongThanhTienPhaiTra = rs.getDouble("tongThanhTienPhaiTra");

                // Assuming you have appropriate constructors for KhachHang, KhuyenMai, and NhanVien
                KhachHang khachHang = new KhachHang(maKhachHang); // Replace with correct instantiation
                KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai); // Replace with correct instantiation
                NhanVien nhanVien = new NhanVien(maNhanVien); // Replace with correct instantiation

                HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, khuyenMai, nhanVien, ngayLapHoaDon, thue, tongThanhTienBanDau, tongThanhTienPhaiTra);
                dsHoaDon.add(hoaDon);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dsHoaDon;
    }

    public boolean createHoaDon(HoaDon hd) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            statement = con.prepareStatement("INSERT INTO HoaDon VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1, hd.getMaHoaDon());
            statement.setString(2, hd.getKhachHang().getMaKhachHang());
            statement.setString(3, hd.getKhuyenMai().getMaKhuyenMai());
            statement.setString(4, hd.getNhanVien().getMaNhanVien());
            statement.setDate(5, java.sql.Date.valueOf(hd.getNgayLapHoaDon()));
            statement.setDouble(6, hd.getThue());
            statement.setDouble(7, hd.getTongThanhTienBanDau());
            statement.setDouble(8, hd.getTongThanhTienPhaiTra());

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public void updateHoaDon(HoaDon hd) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE HoaDon SET maHoaDon = ?, khachHang = ?, khuyenMai=?, nhanVien=?,"
                + " ngayLapHoaDon = ?, thue = ?, tongThanhTienBanDau=?, tongThanhTienPhaiTra=? WHERE maHoaDon=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, hd.getMaHoaDon());
            statement.setString(2, hd.getKhachHang().getMaKhachHang());
            statement.setString(3, hd.getKhuyenMai().getMaKhuyenMai());
            statement.setString(4, hd.getNhanVien().getMaNhanVien());
            statement.setDate(5, java.sql.Date.valueOf(hd.getNgayLapHoaDon()));
            statement.setDouble(6, hd.getThue());
            statement.setDouble(7, hd.getTongThanhTienBanDau());
            statement.setDouble(8, hd.getTongThanhTienPhaiTra());
            statement.setString(9, hd.getMaHoaDon());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHoaDon(HoaDon p) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from HoaDon where maHoaDon = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getMaHoaDon());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
