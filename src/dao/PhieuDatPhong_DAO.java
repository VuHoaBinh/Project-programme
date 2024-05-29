/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class PhieuDatPhong_DAO {

    /**
     *
     * @param getAllTablePhieuDatPhong
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<PhieuDatPhong> getAllTablePhieuDatPhong() throws java.sql.SQLException {
        ArrayList<PhieuDatPhong> dsPhieuDatPhong = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM PhieuDatPhong";
        java.sql.Statement statement = con.createStatement();
        java.sql.ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maPhieuDatPhong = rs.getString("maPhieuDatPhong");
            // Assuming you have methods to retrieve KhachHang and NhanVien objects
            KhachHang khachHang = new KhachHang(rs.getString("khachHang"));
            NhanVien nhanVien = new NhanVien(rs.getString("nhanVien"));
            int soLuongNguoi = rs.getInt("soLuongNguoi");
            LocalDate ngayDatPhong = rs.getDate("ngayDatPhong").toLocalDate();
            LocalDate ngayNhanPhong = rs.getDate("ngayNhanPhong").toLocalDate();
            LocalDate ngayTraPhong = rs.getDate("ngayTraPhong").toLocalDate();

            PhieuDatPhong phieuDatPhong = new PhieuDatPhong(maPhieuDatPhong, khachHang, nhanVien,
                    soLuongNguoi, ngayDatPhong, ngayNhanPhong, ngayTraPhong);
            dsPhieuDatPhong.add(phieuDatPhong);
        }
        return dsPhieuDatPhong;
    }

    /**
     *
     * @param getPhongTheoMaPhieuDatPhong
     * @return
     * @throws IOException
     * @throws java.sql.SQLException
     */
    public ArrayList<PhieuDatPhong> getPhongTheoMaPhieuDatPhong(String maPhieuDatPhong) throws IOException, java.sql.SQLException {
        ArrayList<PhieuDatPhong> dsPhieuDatPhong = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM PhieuDatPhong WHERE maPhieuDatPhong = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maPhieuDatPhong);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            // Assuming you have methods to retrieve KhachHang and NhanVien objects
            KhachHang khachHang = new KhachHang(rs.getString("khachHang"));
            NhanVien nhanVien = new NhanVien(rs.getString("nhanVien"));
            int soLuongNguoi = rs.getInt("soLuongNguoi");
            LocalDate ngayDatPhong = rs.getDate("ngayDatPhong").toLocalDate();
            LocalDate ngayNhanPhong = rs.getDate("ngayNhanPhong").toLocalDate();
            LocalDate ngayTraPhong = rs.getDate("ngayTraPhong").toLocalDate();

            PhieuDatPhong phieuDatPhong = new PhieuDatPhong(maPhieuDatPhong, khachHang, nhanVien,
                    soLuongNguoi, ngayDatPhong, ngayNhanPhong, ngayTraPhong);
            dsPhieuDatPhong.add(phieuDatPhong);
        }

        return dsPhieuDatPhong;
    }

    public boolean createPhieuDatPhong(PhieuDatPhong pdp) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        statement = con.prepareStatement("INSERT INTO PhieuDatPhong VALUES (?,?,?,?,?,?,?,?)");
        statement.setString(1, pdp.getMaPhieuDatPhong());
        statement.setString(2, pdp.getKhachHang().getMaKhachHang());
        statement.setString(3, pdp.getNhanvien().getMaNhanVien());
        statement.setInt(4, pdp.getSoLuongNguoi());
        statement.setDate(5, java.sql.Date.valueOf(pdp.getNgayDatPhong()));
        statement.setDate(6, java.sql.Date.valueOf(pdp.getNgayNhanPhong()));
        statement.setDate(7, java.sql.Date.valueOf(pdp.getNgayTraPhong()));
        statement.setDouble(8, 0);

        n = statement.executeUpdate();

        return n > 0;
    }

    public void updatePhieuDatPhong(PhieuDatPhong pdp) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE PhieuDatPhong SET maPhieuDatPhong = ?, khachHang = ?, nhanVien=?, soLuongNguoi=?,"
                + " ngayDatPhong = ?, ngayNhanPhong = ?, ngayTraPhong=?  WHERE maPhieuDatPhong=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, pdp.getMaPhieuDatPhong());
            statement.setString(2, pdp.getKhachHang().getMaKhachHang());
            statement.setString(3, pdp.getNhanvien().getMaNhanVien());
            statement.setInt(4, pdp.getSoLuongNguoi());
            statement.setDate(5, java.sql.Date.valueOf(pdp.getNgayDatPhong()));
            statement.setDate(6, java.sql.Date.valueOf(pdp.getNgayNhanPhong()));
            statement.setDate(7, java.sql.Date.valueOf(pdp.getNgayTraPhong()));
            statement.setString(8, pdp.getMaPhieuDatPhong());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePhieuDatPhong(PhieuDatPhong p) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from PhieuDatPhong where maPhieuDatPhong = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getMaPhieuDatPhong());
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

    public PhieuDatPhong getPhieuDatPhongMoiNhat() {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PhieuDatPhong pdp = null;

        try {
            Statement statement = con.createStatement();
            String sql = "Select top 1 * from phieudatphong ORDER BY maPhieuDatPhong DESC ";
            // Thực hiện câu lệnh sql trả về đối tượng ResultSet
            ResultSet rs = statement.executeQuery(sql);
            // Duyệt kết quả trả về
            while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
                String maPDP = rs.getString(1);
                KhachHang kh = new KhachHang(rs.getString(2));
                NhanVien nv = new NhanVien(rs.getString(3));
                int soLuongKhach = rs.getInt(4);
                LocalDate ngayDatPhong = rs.getDate("ngayDatPhong").toLocalDate();
                LocalDate ngayNhanPhong = rs.getDate("ngayNhanPhong").toLocalDate();
                LocalDate ngayTraPhong = rs.getDate("ngayTraPhong").toLocalDate();
                pdp = new PhieuDatPhong(maPDP, kh, nv, soLuongKhach, ngayDatPhong, ngayNhanPhong, ngayTraPhong);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return pdp;
    }

    public int demSoLuongPDPTheoMaMau(int nam) {
        int soLuong = 0;
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String year = String.format("%02d", nam % 100);
        String mau = "PDP" + year + "%";
        System.out.println(year);
        String sql = "SELECT COUNT(*) AS soLuong FROM PhieuDatPhong WHERE maPhieuDatPhong LIKE ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, mau);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("soLuong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuDatPhong_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soLuong;
    }
}
