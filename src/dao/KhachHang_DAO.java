/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
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
public class KhachHang_DAO {

    /**
     *
     * @param getAllTableKhachHang
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<KhachHang> getAllTableKhachHang() throws java.sql.SQLException {
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM KhachHang";
        java.sql.Statement statement = con.createStatement();
        java.sql.ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maKhachHang = rs.getString("maKhachHang");
            String hoTenKhachHang = rs.getString("hoTenKhachHang");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String CCCD = rs.getString("CCCD");
            LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
            boolean trangThaiKhachHang = rs.getInt("trangThaiKhachHang") == 1;

            KhachHang kh = new KhachHang(maKhachHang, hoTenKhachHang, gioiTinh,
                    CCCD, ngaySinh, trangThaiKhachHang);
            dsKhachHang.add(kh);
        }
        return dsKhachHang;
    }

    /**
     *
     * @param getPhongTheoMaKhachHang
     * @return
     * @throws IOException
     * @throws java.sql.SQLException
     */
    public ArrayList<KhachHang> getKHTheoMaKhachHang(String maKH) throws IOException, java.sql.SQLException {
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maKH);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String maKhachHang = rs.getString("maKhachHang");
            String hoTenKhachHang = rs.getString("hoTenKhachHang");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String CCCD = rs.getString("CCCD");
            LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
            boolean trangThaiKhachHang = rs.getBoolean("trangThaiKhachHang");
            KhachHang kh = new KhachHang(maKhachHang, hoTenKhachHang, gioiTinh,
                    CCCD, ngaySinh, trangThaiKhachHang);
            dsKhachHang.add(kh);
        }
        return dsKhachHang;
    }

    public ArrayList<KhachHang> getDsKhachHangTheoMa(String maKhachHang) throws IOException, SQLException {
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maKhachHang);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String hoTenKhachHang = rs.getString("hoTenKhachHang");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String CCCD = rs.getString("CCCD");
            java.sql.Date ngaySinh = rs.getDate("ngaySinh");
            LocalDate ngaySinhLocalDate = ngaySinh.toLocalDate();
            boolean trangThaiKhachHang = rs.getBoolean("trangThaiKhachHang");
            KhachHang kh = new KhachHang(maKhachHang, hoTenKhachHang, gioiTinh, CCCD, ngaySinhLocalDate, trangThaiKhachHang);
            dsKhachHang.add(kh);
        }
        return dsKhachHang;
    }

    public KhachHang getKhachHangTheoMa(String maKhachHang) throws IOException, SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        KhachHang kh = null;
        PreparedStatement statement = null;

        String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maKhachHang);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String hoTenKhachHang = rs.getString("hoTenKhachHang");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String CCCD = rs.getString("CCCD");
            java.sql.Date ngaySinh = rs.getDate("ngaySinh");
            LocalDate ngaySinhLocalDate = ngaySinh.toLocalDate();
            boolean trangThaiKhachHang = rs.getBoolean("trangThaiKhachHang");
            kh = new KhachHang(maKhachHang, hoTenKhachHang, gioiTinh, CCCD, ngaySinhLocalDate, trangThaiKhachHang);
        }
        return kh;
    }

    public KhachHang getKhachHangTheoTen(String ten) throws IOException, SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        KhachHang kh = null;
        PreparedStatement statement = null;

        String sql = "SELECT * FROM KhachHang WHERE hoTenKhachHang = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, ten);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String maKhachHang = rs.getString("maKhachHang");
            String hoTenKhachHang = rs.getString("hoTenKhachHang");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String CCCD = rs.getString("CCCD");
            java.sql.Date ngaySinh = rs.getDate("ngaySinh");
            LocalDate ngaySinhLocalDate = ngaySinh.toLocalDate();
            boolean trangThaiKhachHang = rs.getBoolean("trangThaiKhachHang");

            kh = new KhachHang(maKhachHang, hoTenKhachHang, gioiTinh, CCCD, ngaySinhLocalDate, trangThaiKhachHang);
        }
        return kh;
    }

    public boolean createKhachHang(KhachHang kh) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            statement = con.prepareStatement("INSERT INTO KhachHang VALUES (?,?,?,?,?,?)");
            statement.setString(1, kh.getMaKhachHang());
            statement.setString(2, kh.getHoTenKhachHang());
            statement.setBoolean(3, kh.isGioiTinh());
            statement.setString(4, kh.getCCCD());
            statement.setDate(5, java.sql.Date.valueOf(kh.getNgaySinh()));
            statement.setBoolean(6, kh.isTrangThaiKhachHang());

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

    public void updateKhachHang(KhachHang kh) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE KhachHang SET maKhachHang = ?, hoTenKhachHang = ?, gioiTinh=?, CCCD=?,"
                + " ngaySinh = ?, trangThaiKhachHang = ? WHERE maKhachHang=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, kh.getMaKhachHang());
            statement.setString(2, kh.getHoTenKhachHang());
            statement.setBoolean(3, kh.isGioiTinh());
            statement.setString(4, kh.getCCCD());
            statement.setDate(5, java.sql.Date.valueOf(kh.getNgaySinh()));
            statement.setBoolean(6, kh.isTrangThaiKhachHang());
            statement.setString(7, kh.getMaKhachHang());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTrangThaiKhachHang(String ma) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE KhachHang SET trangThaiKhachHang = ? WHERE maKhachHang=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setBoolean(1, false);
            statement.setString(2, ma);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteKhachHang(KhachHang p) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from KhachHang where maKhachHang = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getMaKhachHang());
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
