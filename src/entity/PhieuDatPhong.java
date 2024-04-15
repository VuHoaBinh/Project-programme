/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author LENOVO
 */
public class PhieuDatPhong {

    private String maPhieuDatPhong;
    private KhachHang khachHang;
    private NhanVien nhanvien;
    private int soLuongNguoi;
    private LocalDate ngayDatPhong;
    private LocalDate ngayNhanPhong;
    private LocalDate ngayTraPhong;
//    private double tienPhong;

    public PhieuDatPhong(String maPhieuDatPhong, KhachHang khachHang, NhanVien nhanvien, int soLuongNguoi, LocalDate ngayDatPhong, LocalDate ngayNhanPhong, LocalDate ngayTraPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
        this.khachHang = khachHang;
        this.nhanvien = nhanvien;
        this.soLuongNguoi = soLuongNguoi;
        this.ngayDatPhong = ngayDatPhong;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTraPhong = ngayTraPhong;
//        this.tienPhong = tienPhong;
    }

    public PhieuDatPhong(String maPhieuDatPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;

    }

    public String getMaPhieuDatPhong() {
        return maPhieuDatPhong;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public NhanVien getNhanvien() {
        return nhanvien;
    }

    public int getSoLuongNguoi() {
        return soLuongNguoi;
    }

    public LocalDate getNgayDatPhong() {
        return ngayDatPhong;
    }

    public LocalDate getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public LocalDate getNgayTraPhong() {
        return ngayTraPhong;
    }

//    public double getTienPhong() {
//        return tienPhong;
//    }

    public void setMaPhieuDatPhong(String maPhieuDatPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public void setNhanvien(NhanVien nhanvien) {
        this.nhanvien = nhanvien;
    }

    public void setSoLuongNguoi(int soLuongNguoi) {
        this.soLuongNguoi = soLuongNguoi;
    }

    public void setNgayDatPhong(LocalDate ngayDatPhong) {
        this.ngayDatPhong = ngayDatPhong;
    }

    public void setNgayNhanPhong(LocalDate ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public void setNgayTraPhong(LocalDate ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    @Override
    public String toString() {
        return "PhieuDatPhong{" + "maPhieuDatPhong=" + maPhieuDatPhong + ", khachHang=" + khachHang + ", nhanvien=" + nhanvien + ", soLuongNguoi=" + soLuongNguoi + ", ngayDatPhong=" + ngayDatPhong + ", ngayNhanPhong=" + ngayNhanPhong + ", ngayTraPhong=" + ngayTraPhong + ", tienPhong=" + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final PhieuDatPhong other = (PhieuDatPhong) obj;
        return Objects.equals(this.maPhieuDatPhong, other.maPhieuDatPhong);
    }
    //tinhTienPhong

}
