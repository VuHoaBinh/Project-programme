/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author M S I
 */
public class NhanVien {
    private String maNhanVien;
    private String hoTenNhanVien;
    private boolean gioiTinh;
    private String diaChi;
    private boolean trangThaiLamViec;
    private String soDIenThoai;
    private String hinhAnh;
    private boolean ChucVu;

    public NhanVien(String maNhanVien, String hoTenNhanVien, boolean gioiTinh, String diaChi, boolean trangThaiLamViec, String soDIenThoai, String hinhAnh, boolean ChucVu) {
        this.maNhanVien = maNhanVien;
        this.hoTenNhanVien = hoTenNhanVien;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.trangThaiLamViec = trangThaiLamViec;
        this.soDIenThoai = soDIenThoai;
        this.hinhAnh = hinhAnh;
        this.ChucVu = ChucVu;
    }
    

    public NhanVien() {
    }

    public NhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getHoTenNhanVien() {
        return hoTenNhanVien;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public boolean isTrangThaiLamViec() {
        return trangThaiLamViec;
    }

    public String getSoDIenThoai() {
        return soDIenThoai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public boolean isChucVu() {
        return ChucVu;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setHoTenNhanVien(String hoTenNhanVien) {
        this.hoTenNhanVien = hoTenNhanVien;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setTrangThaiLamViec(boolean trangThaiLamViec) {
        this.trangThaiLamViec = trangThaiLamViec;
    }

    public void setSoDIenThoai(String soDIenThoai) {
        this.soDIenThoai = soDIenThoai;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public void setChucVu(boolean ChucVu) {
        this.ChucVu = ChucVu;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNhanVien=" + maNhanVien + ", hoTenNhanVien=" + hoTenNhanVien + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", trangThaiLamViec=" + trangThaiLamViec + ", soDIenThoai=" + soDIenThoai + ", hinhAnh=" + hinhAnh + ", ChucVu=" + ChucVu + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.maNhanVien);
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
        final NhanVien other = (NhanVien) obj;
        return Objects.equals(this.maNhanVien, other.maNhanVien);
    }
    
}