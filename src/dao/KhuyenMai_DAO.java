/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.KhuyenMai;
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
public class KhuyenMai_DAO {

    /**
     *
     * @param getAllTableKhuyenMai
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<KhuyenMai> getAllTableKhuyenMai() throws java.sql.SQLException {
        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM KhuyenMai";
        java.sql.Statement statement = con.createStatement();
        java.sql.ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maKhuyenMai = rs.getString("maKhuyenMai");
            boolean trangThaiKhuyenMai = rs.getBoolean("trangThaiKhuyenMai");
            double giaTri = rs.getDouble("giaTri");
            LocalDate ngayBatDau = rs.getDate("ngayBatDau").toLocalDate();
            LocalDate ngayKetThuc = rs.getDate("ngayKetThuc").toLocalDate();
            String noiDung = rs.getString("noiDung");

            KhuyenMai km = new KhuyenMai(maKhuyenMai, trangThaiKhuyenMai,
                    giaTri, ngayBatDau, ngayKetThuc, noiDung);
            dsKhuyenMai.add(km);
        }
        return dsKhuyenMai;
    }

    /**
     *
     * @param getPhongTheoMaKhuyenMai
     * @return
     * @throws IOException
     * @throws java.sql.SQLException
     */
    public ArrayList<KhuyenMai> getPhongTheoMaKhuyenMai(String maKhuyenMai) throws IOException, java.sql.SQLException {
        ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, maKhuyenMai);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                boolean trangThaiKhuyenMai = rs.getBoolean("trangThaiKhuyenMai");
                double giaTri = rs.getDouble("giaTri");
                LocalDate ngayBatDau = rs.getDate("ngayBatDau").toLocalDate();
                LocalDate ngayKetThuc = rs.getDate("ngayKetThuc").toLocalDate();
                String noiDung = rs.getString("noiDung");

                KhuyenMai km = new KhuyenMai(maKhuyenMai, trangThaiKhuyenMai,
                        giaTri, ngayBatDau, ngayKetThuc, noiDung);
                dsKhuyenMai.add(km);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dsKhuyenMai;
    }

    public boolean createKhuyenMai(KhuyenMai km) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            statement = con.prepareStatement("INSERT INTO KhuyenMai VALUES (?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, km.getMaKhuyenMai());
            statement.setBoolean(2, km.isTrangThaiKhuyenMai());
            statement.setDouble(3, km.getGiaTri());
            statement.setDate(4, java.sql.Date.valueOf(km.getNgayBatDau()));
            statement.setDate(5, java.sql.Date.valueOf(km.getNgayKetThuc()));
            statement.setString(6, km.getNoiDung());

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

    public void updateKhuyenMai(KhuyenMai km) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE KhuyenMai SET maKhuyenMai = ?, trangThaiKhuyenMai = ?, giaTri=?, ngayBatDau=?,"
                + " ngayKetThuc = ?, noiDung = ? WHERE maKhuyenMai=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, km.getMaKhuyenMai());
            statement.setBoolean(2, km.isTrangThaiKhuyenMai());
            statement.setDouble(3, km.getGiaTri());
            statement.setDate(4, java.sql.Date.valueOf(km.getNgayBatDau()));
            statement.setDate(5, java.sql.Date.valueOf(km.getNgayKetThuc()));
            statement.setString(6, km.getNoiDung());
            statement.setString(7, km.getMaKhuyenMai());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteKhuyenMai(KhuyenMai p) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from KhuyenMai where maKhuyenMai = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getMaKhuyenMai());
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
