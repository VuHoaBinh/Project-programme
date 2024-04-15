package entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author M S I
 */
public class DoAnUong {

    private String maDoAnUong;
    private String tenDoAnUong;
    private boolean loai;
    private double giaNhap;
    private double giaBan;
    private boolean hoanTra;
    private int soLuong;
    private LocalDate ngaySanXuat;
    private LocalDate HanSuDung;
    private String moTa;
    private TrangThaiSuDung trangThaiSuDung;

    public DoAnUong() {
    }

    public DoAnUong(String maDoAnUong) {
        this.maDoAnUong = maDoAnUong;
    }

    public DoAnUong(String maDoAnUong, String tenDoAnUong, boolean loai, double giaNhap, double giaBan, boolean hoanTra, int soLuong, LocalDate ngaySanXuat, LocalDate HanSuDung, String moTa, TrangThaiSuDung trangThaiSuDung) {
        this.maDoAnUong = maDoAnUong;
        this.tenDoAnUong = tenDoAnUong;
        this.loai = loai;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.hoanTra = hoanTra;
        this.soLuong = soLuong;
        this.ngaySanXuat = ngaySanXuat;
        this.HanSuDung = HanSuDung;
        this.moTa = moTa;
        this.trangThaiSuDung = trangThaiSuDung;
    }

    public void setGiaBan() {
        this.giaBan = this.giaNhap * 1.5;
    }

    public void setMaDoAnUong(String maDoAnUong) {
        this.maDoAnUong = maDoAnUong;
    }

    public void setTenDoAnUong(String tenDoAnUong) {
        this.tenDoAnUong = tenDoAnUong;
    }

    public void setLoai(boolean loai) {
        this.loai = loai;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public void setHoanTra(boolean hoanTra) {
        this.hoanTra = hoanTra;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public void setHanSuDung(LocalDate HanSuDung) {
        this.HanSuDung = HanSuDung;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setTrangThaiSuDung(TrangThaiSuDung trangThaiSuDung) {
        this.trangThaiSuDung = trangThaiSuDung;
    }

    public String getMaDoAnUong() {
        return maDoAnUong;
    }

    public String getTenDoAnUong() {
        return tenDoAnUong;
    }

    public boolean isLoai() {
        return loai;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public boolean isHoanTra() {
        return hoanTra;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public LocalDate getHanSuDung() {
        return HanSuDung;
    }

    public String getMoTa() {
        return moTa;
    }

    public TrangThaiSuDung getTrangThaiSuDung() {
        return trangThaiSuDung;
    }

    @Override
    public String toString() {
        return "DoAnUong{" + "maDoAnUong=" + maDoAnUong + ", tenDoAnUong=" + tenDoAnUong + ", loai=" + loai + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", hoanTra=" + hoanTra + ", soLuong=" + soLuong + ", ngaySanXuat=" + ngaySanXuat + ", HanSuDung=" + HanSuDung + ", moTa=" + moTa + ", trangThaiSuDung=" + trangThaiSuDung + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.maDoAnUong);
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
        final DoAnUong other = (DoAnUong) obj;
        return Objects.equals(this.maDoAnUong, other.maDoAnUong);
    }

}
