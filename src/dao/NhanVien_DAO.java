/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.NhanVien;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class NhanVien_DAO {

    /**
     *
     * @param getAllTableNhanVien
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<NhanVien> getAllTableNhanVien() throws SQLException {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM NhanVien";
        java.sql.Statement statement = con.createStatement();
        java.sql.ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            String maNhanVien = rs.getString("maNhanVien");
            String hoTenNhanVien = rs.getString("hoTenNhanVien");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String diaChi = rs.getString("diaChi");
            boolean trangThaiLamViec = rs.getBoolean("trangThaiLamViec");
            String soDienThoai = rs.getString("soDienThoai");
            String hinhAnh = rs.getString("hinhAnh");
            boolean chucVu = rs.getBoolean("chucVu");

            NhanVien nhanVien = new NhanVien(maNhanVien, hoTenNhanVien, gioiTinh, diaChi, trangThaiLamViec, soDienThoai, hinhAnh, chucVu);
            dsNhanVien.add(nhanVien);
        }

        return dsNhanVien;
    }

    /**
     *
     * @param getNhanVienTheoMa
     * @return
     * @throws IOException
     * @throws java.sql.SQLException
     */
    public ArrayList<NhanVien> getNhanVienTheoMa(String maNhanVien) throws IOException, SQLException {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, maNhanVien);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String hoTenNhanVien = rs.getString("hoTenNhanVien");
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                String diaChi = rs.getString("diaChi");
                boolean trangThaiLamViec = rs.getBoolean("trangThaiLamViec");
                String soDienThoai = rs.getString("soDienThoai");
                String hinhAnh = rs.getString("hinhAnh");
                boolean chucVu = rs.getBoolean("chucVu");

                NhanVien nv = new NhanVien(maNhanVien, hoTenNhanVien, gioiTinh, diaChi, trangThaiLamViec, soDienThoai, hinhAnh, chucVu);
                dsNhanVien.add(nv);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dsNhanVien;
    }

    public boolean createNhanVien(NhanVien nv) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            statement = con.prepareStatement("INSERT INTO NhanVien VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1, nv.getMaNhanVien());
            statement.setString(2, nv.getHoTenNhanVien());
            statement.setBoolean(3, nv.isChucVu());
            statement.setBoolean(4, nv.isGioiTinh());
            statement.setBoolean(5, nv.isTrangThaiLamViec());
            statement.setString(6, nv.getDiaChi());
            statement.setString(7, nv.getSoDIenThoai());
            statement.setString(8, nv.getHinhAnh());

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public void updateNhanVien(NhanVien nv) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE NhanVien SET maNhanVien = ?, hoTenNhanVien = ?, chucVu=?, gioiTinh=?,"
                + " trangThaiLamViec = ?, diaChi = ?, soDienThoai=?, hinhAnh=? WHERE maNhanVien=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, nv.getMaNhanVien());
            statement.setString(2, nv.getHoTenNhanVien());
            statement.setBoolean(3, nv.isChucVu());
            statement.setBoolean(4, nv.isGioiTinh());
            statement.setBoolean(5, nv.isTrangThaiLamViec());
            statement.setString(6, nv.getDiaChi());
            statement.setString(7, nv.getSoDIenThoai());
            statement.setString(8, nv.getHinhAnh());
            statement.setString(9, nv.getMaNhanVien());
            
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNhanVien(NhanVien p) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from NhanVien where maNhanVien = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getMaNhanVien());
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
