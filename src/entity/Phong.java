package entity;

import java.util.Objects;

/**
 * Lớp đại diện cho một phòng trong khách sạn.
 * 
 * @author M S I
 */
public class Phong {
    private String maPhong;
    private String tenPhong;
    private double dienTich;
    private int soGiuong;
    private boolean giuongPhu;
    private String view;
    private boolean hutThuoc;
    private String hinhAnhPhong;
    private LoaiPhong loaiPhong;
    private TrangThaiPhong trangThaiPhong;

    // Constructor
    public Phong(String maPhong, String tenPhong, double dienTich, int soGiuong, 
                 boolean giuongPhu, String view, boolean hutThuoc, String hinhAnhPhong, 
                 LoaiPhong loaiPhong, TrangThaiPhong trangThaiPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.dienTich = dienTich;
        this.soGiuong = soGiuong;
        this.giuongPhu = giuongPhu;
        this.view = view;
        this.hutThuoc = hutThuoc;
        this.hinhAnhPhong = hinhAnhPhong;
        this.loaiPhong = loaiPhong;
        this.trangThaiPhong = trangThaiPhong;
    }

    public Phong(String maPhong) {
        this.maPhong = maPhong;
    }

    // Getters and Setters
    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

    public int getSoGiuong() {
        return soGiuong;
    }

    public void setSoGiuong(int soGiuong) {
        this.soGiuong = soGiuong;
    }

    public boolean isGiuongPhu() {
        return giuongPhu;
    }

    public void setGiuongPhu(boolean giuongPhu) {
        this.giuongPhu = giuongPhu;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean isHutThuoc() {
        return hutThuoc;
    }

    public void setHutThuoc(boolean hutThuoc) {
        this.hutThuoc = hutThuoc;
    }

    public String getHinhAnhPhong() {
        return hinhAnhPhong;
    }

    public void setHinhAnhPhong(String hinhAnhPhong) {
        this.hinhAnhPhong = hinhAnhPhong;
    }

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public TrangThaiPhong getTrangThaiPhong() {
        return trangThaiPhong;
    }

    public void setTrangThaiPhong(TrangThaiPhong trangThaiPhong) {
        this.trangThaiPhong = trangThaiPhong;
    }

    // toString method
    @Override
    public String toString() {
        return "Phong{" +
                "maPhong='" + maPhong + '\'' +
                ", tenPhong='" + tenPhong + '\'' +
                ", dienTich=" + dienTich +
                ", soGiuong=" + soGiuong +
                ", giuongPhu=" + giuongPhu +
                ", view='" + view + '\'' +
                ", hutThuoc=" + hutThuoc +
                ", hinhAnhPhong='" + hinhAnhPhong + '\'' +
                ", loaiPhong=" + loaiPhong +
                ", trangThaiPhong=" + trangThaiPhong +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.maPhong);
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
        final Phong other = (Phong) obj;
        return Objects.equals(this.maPhong, other.maPhong);
    }
    
    
}