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
public class ChiTietHoaDon {
    private HoaDon hoaDon;
    private DoAnUong doAnUong;
    private int soLuong;
    private Phong phong;
    private LocalDate ngayNhanPhong;
    private LocalDate ngayTraPhong;
    private int soLuongNguoiO;
    private int soLuongDoUongTraVe;
    private double tongTienThuePhong;
    private double tongTienDichVu;
    private double tongThanhTien;
    private double phuPhi;

    public ChiTietHoaDon() {

    }

    public ChiTietHoaDon(HoaDon hoaDon, DoAnUong doAnUong, int soLuong, Phong phong, LocalDate ngayNhanPhong, LocalDate ngayTraPhong, int soLuongNguoiO, int soLuongDoUongTraVe, double tongTienThuePhong, double tongTienDichVu, double tongThanhTien, double phuPhi) {
        this.hoaDon = hoaDon;
        this.doAnUong = doAnUong;
        this.soLuong = soLuong;
        this.phong = phong;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTraPhong = ngayTraPhong;
        this.soLuongNguoiO = soLuongNguoiO;
        this.soLuongDoUongTraVe = soLuongDoUongTraVe;
        this.tongTienThuePhong = tongTienThuePhong;
        this.tongTienDichVu = tongTienDichVu;
        this.tongThanhTien = tongThanhTien;
        this.phuPhi = phuPhi;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public DoAnUong getDoAnUong() {
        return doAnUong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public Phong getPhong() {
        return phong;
    }

    public LocalDate getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public LocalDate getNgayTraPhong() {
        return ngayTraPhong;
    }

    public int getSoLuongNguoiO() {
        return soLuongNguoiO;
    }

    public int getSoLuongDoUongTraVe() {
        return soLuongDoUongTraVe;
    }

    public double getTongTienThuePhong() {
        return tongTienThuePhong;
    }

    public double getTongTienDichVu() {
        return tongTienDichVu;
    }

    public double getTongThanhTien() {
        return tongThanhTien;
    }

    public double getPhuPhi() {
        return phuPhi;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public void setDoAnUong(DoAnUong doAnUong) {
        this.doAnUong = doAnUong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public void setNgayNhanPhong(LocalDate ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public void setNgayTraPhong(LocalDate ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public void setSoLuongNguoiO(int soLuongNguoiO) {
        this.soLuongNguoiO = soLuongNguoiO;
    }
    public double tinhTongTienDichVu(){
        return soLuong * doAnUong.getGiaNhap();
    }
//    public double tinhTienThuePhong(){
//        
//    }
//    public double tinhTongThanhTien(){
//        return 
//    }
//    
    //tinhTongPhuPhi (tl4 thiếu cái này)
    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "hoaDon=" + hoaDon + ", doAnUong=" + doAnUong + ", soLuong=" + soLuong + ", phong=" + phong + ", ngayNhanPhong=" + ngayNhanPhong + ", ngayTraPhong=" + ngayTraPhong + ", soLuongNguoiO=" + soLuongNguoiO + ", soLuongDoUongTraVe=" + soLuongDoUongTraVe + ", tongTienThuePhong=" + tongTienThuePhong + ", tongTienDichVu=" + tongTienDichVu + ", tongThanhTien=" + tongThanhTien + ", phuPhi=" + phuPhi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.hoaDon);
        hash = 53 * hash + Objects.hashCode(this.doAnUong);
        hash = 53 * hash + Objects.hashCode(this.phong);
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
        final ChiTietHoaDon other = (ChiTietHoaDon) obj;
        if (!Objects.equals(this.hoaDon, other.hoaDon)) {
            return false;
        }
        if (!Objects.equals(this.doAnUong, other.doAnUong)) {
            return false;
        }
        return Objects.equals(this.phong, other.phong);
    }
}

