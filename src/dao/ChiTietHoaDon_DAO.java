/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.DoAnUong;
import entity.HoaDon;
import entity.Phong;
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

    private Phong_DAO p_dao;

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

            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(
                    new HoaDon(maHoaDon),
                    new DoAnUong(maDoAnUong),
                    soLuong,
                    new Phong(maPhong),
                    ngayNhanPhong,
                    ngayTraPhong,
                    soLuongNguoiO,
                    soLuongDoUongTraVe,
                    tongTienThuePhong,
                    tongTienDichVu,
                    phuPhi
            );

            dsChiTietHoaDon.add(chiTietHoaDon);

        }
        return dsChiTietHoaDon;
    }

    public ArrayList<ChiTietHoaDon> getChiTietHoaDontheoPhong(String maPhong) throws java.sql.SQLException, IOException {
        ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM ChiTietHoaDon WHERE phong = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maPhong);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            p_dao = new Phong_DAO();
            Phong phong = p_dao.getPhongTheoMaPhong(maPhong).getFirst();
            if (phong.getTrangThaiPhong().toString() == "OCCUPIED") {
                String maHoaDon = rs.getString("hoaDon");
                String maDoAnUong = rs.getString("doAnUong");
                int soLuong = rs.getInt("soLuong");
                LocalDate ngayNhanPhong = rs.getDate("ngayNhanPhong").toLocalDate();
                LocalDate ngayTraPhong = rs.getDate("ngayTraPhong").toLocalDate();
                int soLuongNguoiO = rs.getInt("soLuongNguoiO");
                int soLuongDoUongTraVe = rs.getInt("soLuongDoUongTraVe");
                double tongTienThuePhong = rs.getDouble("tongTienThuePhong");
                double tongTienDichVu = rs.getDouble("tongTienDichVu");
                double tongThanhTien = rs.getDouble("tongThanhTien");
                double phuPhi = rs.getDouble("phuPhi");

                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(
                        new HoaDon(maHoaDon),
                        new DoAnUong(maDoAnUong),
                        soLuong,
                        new Phong(maPhong),
                        ngayNhanPhong,
                        ngayTraPhong,
                        soLuongNguoiO,
                        soLuongDoUongTraVe,
                        tongTienThuePhong,
                        tongTienDichVu,
                        phuPhi
                );

                dsChiTietHoaDon.add(chiTietHoaDon);
            }
        }
        return dsChiTietHoaDon;
    }

    public ArrayList<DoAnUong> getDoAnUongtheoMa(String ma) throws java.sql.SQLException, IOException {
        ArrayList<DoAnUong> ds = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM ChiTietHoaDon WHERE hoaDon = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, ma);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String maDoAnUong = rs.getString("doAnUong");
            ds.add(new DoAnUong(maDoAnUong));
        }
        return ds;
    }

    public boolean createChiTietHoaDon(ChiTietHoaDon cthd) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            statement = con.prepareStatement("INSERT INTO ChiTietHoaDon VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, cthd.getHoaDon().getMaHoaDon());
            statement.setString(2, cthd.getDoAnUong().getMaDoAnUong());
            statement.setString(3, cthd.getPhong().getMaPhong());
            statement.setInt(4, cthd.getSoLuong());
            statement.setDate(5, java.sql.Date.valueOf(cthd.getNgayNhanPhong()));
            statement.setDate(6, java.sql.Date.valueOf(cthd.getNgayTraPhong()));
            statement.setInt(7, cthd.getSoGio());
            statement.setInt(8, cthd.getSoLuongNguoiO());
            statement.setDouble(9, cthd.getSoLuongDoUongTraVe());
            statement.setDouble(10, cthd.getTongTienThuePhong());
            statement.setDouble(11, cthd.getTongTienDichVu());
            statement.setDouble(12, 0);
            statement.setDouble(13, cthd.getPhuPhi());

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public ArrayList<ChiTietHoaDon> getChiTietHoaDontheoMa(String ma) throws java.sql.SQLException, IOException {
        ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM ChiTietHoaDon WHERE hoaDon = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, ma);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {

            String maHoaDon = rs.getString("hoaDon");
            String maDoAnUong = rs.getString("doAnUong");
            int soLuong = rs.getInt("soLuong");
            LocalDate ngayNhanPhong = rs.getDate("ngayNhanPhong").toLocalDate();
            LocalDate ngayTraPhong = rs.getDate("ngayTraPhong").toLocalDate();
            int soLuongNguoiO = rs.getInt("soLuongNguoiO");
            int soGio = rs.getInt("soGio");
            int soLuongDoUongTraVe = rs.getInt("soLuongDoUongTraVe");
            double tongTienThuePhong = rs.getDouble("tongTienThuePhong");
            double tongTienDichVu = rs.getDouble("tongTienDichVu");
            double tongThanhTien = rs.getDouble("tongThanhTien");
            double phuPhi = rs.getDouble("phuPhi");

            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(
                    new HoaDon(maHoaDon),
                    new DoAnUong(maDoAnUong),
                    soLuong,
                    new Phong(ma),
                    soGio,
                    ngayNhanPhong,
                    ngayTraPhong,
                    soLuongNguoiO,
                    soLuongDoUongTraVe,
                    tongTienThuePhong,
                    tongTienDichVu,
                    phuPhi
            );

            dsChiTietHoaDon.add(chiTietHoaDon);
        }
        return dsChiTietHoaDon;
    }

    public boolean checkDoAnUongExistInHoaDon(String maHoaDon, String maDoAnUong) throws SQLException {
        boolean exists = false;

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM ChiTietHoaDon WHERE hoaDon = ? AND doAnUong = ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, maHoaDon);
            statement.setString(2, maDoAnUong);
            rs = statement.executeQuery();
            exists = rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return exists;
    }

    public boolean updateNgayTraPhongByMaHoaDon(String maHoaDon, LocalDate ngayTraPhongMoi) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            String sql = "UPDATE ChiTietHoaDon SET ngayTraPhong = ? WHERE hoaDon = ?";
            statement = con.prepareStatement(sql);
            statement.setDate(1, java.sql.Date.valueOf(ngayTraPhongMoi));
            statement.setString(2, maHoaDon);

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    public boolean updateTongTienThuePhongByMaHoaDon(String maHoaDon, double tongTienThuePhongMoi) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            String sql = "UPDATE ChiTietHoaDon SET tongTienThuePhong = ? WHERE hoaDon = ?";
            statement = con.prepareStatement(sql);
            statement.setDouble(1, tongTienThuePhongMoi);
            statement.setString(2, maHoaDon);

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    public boolean updateSoLuongByMaHoaDonAndMaDoAnUong(String maHoaDon, String maDoAnUong, int soLuongMoi) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            String sql = "UPDATE ChiTietHoaDon SET soLuong = ? WHERE hoaDon = ? AND doAnUong = ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, soLuongMoi);
            statement.setString(2, maHoaDon);
            statement.setString(3, maDoAnUong);

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    public ArrayList<ChiTietHoaDon> getChiTietHoaDontheoMaHoaDonAndDoAnUong(String maHoaDon, String maDoAnUong) throws SQLException, IOException {
        ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM ChiTietHoaDon WHERE hoaDon = ? AND doAnUong = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maHoaDon);
        statement.setString(2, maDoAnUong);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            String maDoAnUongDb = rs.getString("doAnUong");
            int soLuong = rs.getInt("soLuong");
            String maPhong = rs.getString("phong");
            LocalDate ngayNhanPhong = rs.getDate("ngayNhanPhong").toLocalDate();
            LocalDate ngayTraPhong = rs.getDate("ngayTraPhong").toLocalDate();
            int soLuongNguoiO = rs.getInt("soLuongNguoiO");
            int soLuongDoUongTraVe = rs.getInt("soLuongDoUongTraVe");
            double tongTienThuePhong = rs.getDouble("tongTienThuePhong");
            double tongTienDichVu = rs.getDouble("tongTienDichVu");
            double phuPhi = rs.getDouble("phuPhi");

            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(
                    new HoaDon(maHoaDon),
                    new DoAnUong(maDoAnUongDb),
                    soLuong,
                    new Phong(maPhong),
                    ngayNhanPhong,
                    ngayTraPhong,
                    soLuongNguoiO,
                    soLuongDoUongTraVe,
                    tongTienThuePhong,
                    tongTienDichVu,
                    phuPhi
            );

            dsChiTietHoaDon.add(chiTietHoaDon);
        }
        return dsChiTietHoaDon;
    }

    public boolean updateTongTienDichVuByMaHoaDonAndMaDoAnUong(String maHoaDon, String maDoAnUong, double tongTienDichVuMoi) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        String sql = "UPDATE ChiTietHoaDon SET tongTienDichVu = ? WHERE hoaDon = ? AND doAnUong = ?";
        statement = con.prepareStatement(sql);
        statement.setDouble(1, tongTienDichVuMoi);
        statement.setString(2, maHoaDon);
        statement.setString(3, maDoAnUong);

        n = statement.executeUpdate();

        return n > 0;
    }

    public boolean deleteChiTietHoaDonByMaHoaDonAndMaDoAnUong(String maHoaDon, String maDoAnUong) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        String sql = "DELETE FROM ChiTietHoaDon WHERE hoaDon = ? AND doAnUong = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maHoaDon);
        statement.setString(2, maDoAnUong);

        n = statement.executeUpdate();

        return n > 0;
    }

    public boolean updateSoLuongDoUongTraVeByMaHoaDonAndMaDoAnUong(String maHoaDon, String maDoAnUong, int soLuongDoUongTraVeMoi) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        String sql = "UPDATE ChiTietHoaDon SET soLuongDoUongTraVe = ? WHERE hoaDon = ? AND doAnUong = ?";
        statement = con.prepareStatement(sql);
        statement.setInt(1, soLuongDoUongTraVeMoi);
        statement.setString(2, maHoaDon);
        statement.setString(3, maDoAnUong);

        n = statement.executeUpdate();

        return n > 0;
    }

    public boolean updateTongThanhTienByMaHoaDonAndMaDoAnUong(String maHoaDon, double tongThanhTienMoi) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            String sql = "UPDATE ChiTietHoaDon SET tongThanhTien = ? WHERE hoaDon = ? ";
            statement = con.prepareStatement(sql);
            statement.setDouble(1, tongThanhTienMoi);
            statement.setString(2, maHoaDon);

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    public boolean updatesoGioByMaHoaDon(String maHoaDon, int soGio) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            String sql = "UPDATE ChiTietHoaDon SET soGio = ? WHERE hoaDon = ? ";
            statement = con.prepareStatement(sql);
            statement.setDouble(1, soGio);
            statement.setString(2, maHoaDon);

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean updateSoGioByMaHoaDonAndMaDoAnUong(String maHoaDon, String maDoAnUong, int soGio) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        String sql = "UPDATE ChiTietHoaDon SET soGio = ? WHERE hoaDon = ? AND doAnUong = ?";
        statement = con.prepareStatement(sql);
        statement.setInt(1, soGio);
        statement.setString(2, maHoaDon);
        statement.setString(3, maDoAnUong);

        n = statement.executeUpdate();

        return n > 0;
    }
}
