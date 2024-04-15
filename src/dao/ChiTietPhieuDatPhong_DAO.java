/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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

            // Retrieve PhieuDatPhong object
            PhieuDatPhong phieuDatPhong = new PhieuDatPhong(maPhieuDatPhong); // Implement this method to fetch PhieuDatPhong object by ID

            // Retrieve Phong object
            Phong phong = new Phong(maPhong); // Implement this method to fetch Phong object by ID

            // Assuming you have constructors for ChiTietPhieuDatPhong that accept PhieuDatPhong and Phong objects
            ChiTietPhieuDatPhong chiTiet = new ChiTietPhieuDatPhong(phieuDatPhong, phong);
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

        try {
            String sql = "SELECT * FROM ChiTietPhieuDatPhong WHERE maChiTietPhieuDatPhong = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, maChiTietPhieuDatPhong);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String maPhieuDatPhong = rs.getString("maPhieuDatPhong");
                String maPhong = rs.getString("maPhong");

                // Assuming you have constructors for ChiTietPhieuDatPhong that accept PhieuDatPhong and Phong objects
                ChiTietPhieuDatPhong chiTiet = new ChiTietPhieuDatPhong(new PhieuDatPhong(maPhieuDatPhong), new Phong(maPhong));
                dsChiTietPhieuDatPhong.add(chiTiet);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dsChiTietPhieuDatPhong;
    }

    public boolean create(ChiTietPhieuDatPhong ctpdp) {

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("insert into ChiTietPhieuDatPhong values(?,?)");
            stmt.setString(1, ctpdp.getPhieuDatPhong().getMaPhieuDatPhong());
            stmt.setString(2, ctpdp.getPhong().getMaPhong());
            n += stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return n > 0;
    }

}
