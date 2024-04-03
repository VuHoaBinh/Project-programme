/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.LoaiPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import connectDB.ConnectDB;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class Phong_DAO {

    /**
     *
     * @param getAllTablePhong
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<Phong> getAllTablePhong() throws java.sql.SQLException {
        ArrayList<Phong> dsPhong = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM Phong";
        java.sql.Statement statement = con.createStatement();
        java.sql.ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maPhong = rs.getString("maPhong");
            String tenPhong = rs.getString("tenPhong");
            String loaiPhong = rs.getString("loaiPhong");
            String trangThaiPhong = rs.getString("trangThaiPhong");
            double dienTichPhong = rs.getDouble("dienTichPhong");
            int soGiuong = rs.getInt("soGiuong");
            boolean giuongPhu = rs.getBoolean("giuongPhu");
            String view_ = rs.getString("view_");
            boolean hutThuoc = rs.getBoolean("hutThuoc");
            String hinhAnhPhong = rs.getString("hinhAnhPhong");

            // Assuming LoaiPhong and TrangThaiPhong are enums
            LoaiPhong loai = LoaiPhong.valueOf(loaiPhong);
            TrangThaiPhong trangThai = TrangThaiPhong.valueOf(trangThaiPhong);

            Phong phong = new Phong(maPhong, tenPhong, dienTichPhong, soGiuong, giuongPhu, view_, hutThuoc, hinhAnhPhong, loai, trangThai);
            dsPhong.add(phong);
        }
        return dsPhong;
    }

    /**
     *
     * @param getPhongTheoMaPhong
     * @return
     * @throws IOException
     * @throws java.sql.SQLException
     */
    public ArrayList<Phong> getPhongTheoMaPhong(String maPhong) throws IOException, java.sql.SQLException {
        ArrayList<Phong> dsPhong = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM Phong WHERE maPhong = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, maPhong);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String tenPhong = rs.getString("tenPhong");
                String loaiPhong = rs.getString("loaiPhong");
                String trangThaiPhong = rs.getString("trangThaiPhong");
                double dienTichPhong = rs.getDouble("dienTichPhong");
                int soGiuong = rs.getInt("soGiuong");
                boolean giuongPhu = rs.getBoolean("giuongPhu");
                String view_ = rs.getString("view_");
                boolean hutThuoc = rs.getBoolean("hutThuoc");
                String hinhAnhPhong = rs.getString("hinhAnhPhong");

                // Assuming LoaiPhong and TrangThaiPhong are enums
                LoaiPhong loai = LoaiPhong.valueOf(loaiPhong);
                TrangThaiPhong trangThai = TrangThaiPhong.valueOf(trangThaiPhong);

                Phong phong = new Phong(maPhong, tenPhong, dienTichPhong, soGiuong, giuongPhu, view_, hutThuoc, hinhAnhPhong, loai, trangThai);
                dsPhong.add(phong);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dsPhong;
    }
    
    

    public boolean createPhong(Phong p) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            statement = con.prepareStatement("INSERT INTO Phong VALUES (?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, p.getMaPhong());
            statement.setString(2, p.getTenPhong());
            statement.setString(3, p.getLoaiPhong().name());
            statement.setString(4, p.getTrangThaiPhong().name());
            statement.setDouble(5, p.getDienTich());
            statement.setInt(6, p.getSoGiuong());
            statement.setBoolean(7, p.isGiuongPhu());
            statement.setString(8, p.getView());
            statement.setBoolean(9, p.isHutThuoc());
            statement.setString(10, p.getHinhAnhPhong());

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

    public void updatePhong(Phong p) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE Phong SET maPhong = ?, tenPhong = ?, loaiPhong=?, trangThaiPhong=?,"
                + " dienTichPhong = ?, soGiuong = ?, giuongPhu=?, view_=? "
                + "hutThuoc = ?, hinhAnhPhong = ? WHERE maPhong=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, p.getMaPhong());
            statement.setString(2, p.getTenPhong());
            statement.setString(3, p.getLoaiPhong().name());
            statement.setString(4, p.getTrangThaiPhong().name());
            statement.setDouble(5, p.getDienTich());
            statement.setInt(6, p.getSoGiuong());
            statement.setBoolean(7, p.isGiuongPhu());
            statement.setString(8, p.getView());
            statement.setBoolean(9, p.isHutThuoc());
            statement.setString(10, p.getHinhAnhPhong());
            statement.setString(11, p.getMaPhong());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePhong (Phong p) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from Phong where maPhong = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getMaPhong());
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