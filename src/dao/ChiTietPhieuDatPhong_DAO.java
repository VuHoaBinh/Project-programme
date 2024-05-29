/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.jdbc.Connection;
import connectDB.ConnectDB;
import entity.ChiTietPhieuDatPhong;

import entity.PhieuDatPhong;
import entity.Phong;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class ChiTietPhieuDatPhong_DAO {

    /**
     *
     * @param getAllTableChiTietPhieuDatPhong
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<ChiTietPhieuDatPhong> getAllTableChiTietPhieuDatPhong() throws java.sql.SQLException {
        ArrayList<ChiTietPhieuDatPhong> dsChiTietPhieuDatPhong = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM ChiTietPhieuDatPhong";
        java.sql.Statement statement = con.createStatement();
        java.sql.ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maPhieuDatPhong = rs.getString("phieuDatPhong");
            String maPhong = rs.getString("phong");
            boolean trangThai = rs.getBoolean("trangThaiChiTietPhieuDatPhong");
            // Retrieve PhieuDatPhong object
            PhieuDatPhong phieuDatPhong = new PhieuDatPhong(maPhieuDatPhong); // Implement this method to fetch PhieuDatPhong object by ID

            // Retrieve Phong object
            Phong phong = new Phong(maPhong); // Implement this method to fetch Phong object by ID

            // Assuming you have constructors for ChiTietPhieuDatPhong that accept PhieuDatPhong and Phong objects
            ChiTietPhieuDatPhong chiTiet = new ChiTietPhieuDatPhong(phieuDatPhong, phong, trangThai);
            dsChiTietPhieuDatPhong.add(chiTiet);
        }
        return dsChiTietPhieuDatPhong;
    }

    /**
     *
     * @param getPhongTheoMaChiTietPhieuDatPhong
     * @return
     * @throws IOException
     * @throws java.sql.SQLException
     */
    public ArrayList<ChiTietPhieuDatPhong> getPhongTheoMaChiTietPhieuDatPhong(String maChiTietPhieuDatPhong) throws IOException, java.sql.SQLException {
        ArrayList<ChiTietPhieuDatPhong> dsChiTietPhieuDatPhong = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM ChiTietPhieuDatPhong WHERE phieuDatPhong = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maChiTietPhieuDatPhong);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            if (rs.getBoolean("trangThaiChiTietPhieuDatPhong") == true) {
                String maPhieuDatPhong = rs.getString("phieuDatPhong");
                String maPhong = rs.getString("phong");
                boolean trangThai = rs.getBoolean("trangThaiChiTietPhieuDatPhong");
                // Assuming you have constructors for ChiTietPhieuDatPhong that accept PhieuDatPhong and Phong objects
                ChiTietPhieuDatPhong chiTiet = new ChiTietPhieuDatPhong(new PhieuDatPhong(maPhieuDatPhong), new Phong(maPhong), trangThai);
                dsChiTietPhieuDatPhong.add(chiTiet);
            }

        }

        return dsChiTietPhieuDatPhong;
    }

    public ArrayList<ChiTietPhieuDatPhong> getPhongTheoPhong(String maPhong1) throws IOException, java.sql.SQLException {
        ArrayList<ChiTietPhieuDatPhong> dsChiTietPhieuDatPhong = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM ChiTietPhieuDatPhong WHERE phong = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maPhong1);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String maPhieuDatPhong = rs.getString("phieuDatPhong");
            String maPhong = maPhong1;
            boolean trangThai = rs.getBoolean("trangThaiChiTietPhieuDatPhong");

            // Assuming you have constructors for ChiTietPhieuDatPhong that accept PhieuDatPhong and Phong objects
            ChiTietPhieuDatPhong chiTiet = new ChiTietPhieuDatPhong(new PhieuDatPhong(maPhieuDatPhong), new Phong(maPhong), trangThai);
            dsChiTietPhieuDatPhong.add(chiTiet);
        }

        return dsChiTietPhieuDatPhong;
    }

    public boolean create(ChiTietPhieuDatPhong ctpdp) throws SQLException {

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;

        stmt = con.prepareStatement("insert into ChiTietPhieuDatPhong values(?,?,?)");
        stmt.setString(1, ctpdp.getPhieuDatPhong().getMaPhieuDatPhong());
        stmt.setString(2, ctpdp.getPhong().getMaPhong());
        stmt.setBoolean(3, ctpdp.isTrangThai());
        n += stmt.executeUpdate();

        return n > 0;
    }

    public void setTrangThai(String maChiTietPhieuDatPhong, boolean trangThai) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE ChiTietPhieuDatPhong SET trangThaiChiTietPhieuDatPhong = ? WHERE phieuDatPhong = ? ";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setBoolean(1, trangThai);
            statement.setString(2, maChiTietPhieuDatPhong);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ChiTietPhieuDatPhong> getPhongTheoPhongTrangThai(String phong) throws IOException, java.sql.SQLException {
        ArrayList<ChiTietPhieuDatPhong> dsChiTietPhieuDatPhong = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM ChiTietPhieuDatPhong WHERE phong = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, phong);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            if (rs.getBoolean("trangThaiChiTietPhieuDatPhong") == true) {
                String maPhieuDatPhong = rs.getString("phieuDatPhong");
                String maPhong = rs.getString("phong");
                boolean trangThai = rs.getBoolean("trangThaiChiTietPhieuDatPhong");
                // Assuming you have constructors for ChiTietPhieuDatPhong that accept PhieuDatPhong and Phong objects
                ChiTietPhieuDatPhong chiTiet = new ChiTietPhieuDatPhong(new PhieuDatPhong(maPhieuDatPhong), new Phong(maPhong), trangThai);
                dsChiTietPhieuDatPhong.add(chiTiet);
            }

        }

        return dsChiTietPhieuDatPhong;
    }
}
