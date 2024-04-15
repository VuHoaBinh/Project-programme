/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.itextpdf.text.log.Logger;
import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import java.io.IOException;
import java.lang.System.Logger.Level;
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
    public ArrayList<HoaDon> getHoaDonTheoMaHoaDon(String maHoaDon) throws IOException, java.sql.SQLException {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

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
        return dsHoaDon;
    }

    public boolean createHoaDon(HoaDon hd) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

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
        return n > 0;
    }

    public boolean createHoaDonTam(HoaDon hd) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            statement = con.prepareStatement("INSERT INTO HoaDonTam VALUES (?,?,?,?,?,?,?,?)");
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

    public int demSoLuongHoaDonTheoMaMau(String time) {
        int soLuong = 0;
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String mau = "MHD" + time + "%";
        System.out.println(time);
        String sql = "SELECT COUNT(*) AS soLuong FROM HoaDon WHERE maHoaDon LIKE ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, mau);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("soLuong");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return soLuong;
    }

    public double tinhTongTienDichVu(String maHoaDon) throws SQLException {
        double tongTienDichVu = 0;

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql = "SELECT SUM(tongTienDichVu) AS tong FROM ChiTietHoaDon WHERE hoaDon = ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, maHoaDon);
            rs = statement.executeQuery();
            if (rs.next()) {
                tongTienDichVu = rs.getDouble("tong");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tongTienDichVu;
    }

    public double tinhthanhTienBanDau(String maHoaDon) throws SQLException {
        double tongTienDichVu = 0;
        double tongTienThuePhong = 0;

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql = "SELECT SUM(tongTienDichVu) AS tongTienDichVu, AVG(tongTienThuePhong) AS tongTienThuePhong "
                + "FROM ChiTietHoaDon WHERE hoaDon = ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, maHoaDon);
            rs = statement.executeQuery();
            if (rs.next()) {
                tongTienDichVu = rs.getDouble("tongTienDichVu");
                tongTienThuePhong = rs.getDouble("tongTienThuePhong");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Tính tổng tổngTienDichVu và tongTienThuePhong
        double tongTien = tongTienDichVu + tongTienThuePhong;
        return tongTien;
    }
}
