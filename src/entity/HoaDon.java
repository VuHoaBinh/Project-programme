/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.HoaDon_DAO;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.Objects;

/**
 *
 * @author LENOVO
 */
public class HoaDon {

    private String maHoaDon;
    private KhachHang khachHang;
    private KhuyenMai khuyenMai;
    private NhanVien nhanVien;
    private LocalDate ngayLapHoaDon;

    private double thue = 0.08;

    private double tongThanhTienBanDau;
    private double tongThanhTienPhaiTra;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, KhachHang khachHang, KhuyenMai khuyenMai, NhanVien nhanVien, LocalDate ngayLapHoaDon, double thue, double tongThanhTienBanDau, double tongThanhTienPhaiTra) {
        this.maHoaDon = maHoaDon;
        this.khachHang = khachHang;
        this.khuyenMai = khuyenMai;
        this.nhanVien = nhanVien;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.thue = thue;
        this.tongThanhTienBanDau = tongThanhTienBanDau;
        this.tongThanhTienPhaiTra = tongThanhTienPhaiTra;
    }
    
    public HoaDon(String maHoaDon, KhachHang khachHang, NhanVien nhanVien, LocalDate ngayLapHoaDon, double tongThanhTienPhaiTra) {
        this.maHoaDon = maHoaDon;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.tongThanhTienPhaiTra = tongThanhTienPhaiTra;
    }
    
    public HoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;

    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public LocalDate getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    public double getThue() {
        return thue;
    }

    public double getTongThanhTienBanDau() {
        return tongThanhTienBanDau;
    }

        public double getTongThanhTienPhaiTra() {
        return tongThanhTienPhaiTra;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public void setNgayLapHoaDon(LocalDate ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    public void setThue(double thue) {
        this.thue = thue;
    }

    public void tinhTongThanhTienBanDau() throws SQLException {
        HoaDon_DAO hd_dao = new HoaDon_DAO();
        tongThanhTienBanDau = hd_dao.tinhthanhTienBanDau(maHoaDon);
    }

    public void tinhTongThanhTienPhaiTra() {
        double tongThanhTienCoThue = (tongThanhTienBanDau * (1.0 + thue));
        tongThanhTienPhaiTra = tongThanhTienCoThue * (1 - this.khuyenMai.getGiaTri());
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", khachHang=" + khachHang + ", khuyenMai=" + khuyenMai + ", nhanVien=" + nhanVien + ", ngayLapHoaDon=" + ngayLapHoaDon + ", thue=" + thue + ", tongThanhTienBanDau=" + tongThanhTienBanDau + ", tongThanhTienPhaiTra=" + tongThanhTienPhaiTra + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.maHoaDon);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final HoaDon other = (HoaDon) obj;
        return Objects.equals(this.maHoaDon, other.maHoaDon);
    }

}
