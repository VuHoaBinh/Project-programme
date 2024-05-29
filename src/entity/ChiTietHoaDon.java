/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    private int soGio;
    private LocalDate ngayNhanPhong;
    private LocalDate ngayTraPhong;
    private int soLuongNguoiO;
    private int soLuongDoUongTraVe;
    private double tongTienThuePhong;
    private double tongTienDichVu;
    private double phuPhi;

    public ChiTietHoaDon() {

    }

    public ChiTietHoaDon(HoaDon hoaDon, DoAnUong doAnUong, int soLuong, Phong phong, LocalDate ngayNhanPhong, LocalDate ngayTraPhong, int soLuongNguoiO, int soLuongDoUongTraVe, double tongTienThuePhong, double tongTienDichVu, double phuPhi) {

        this.hoaDon = hoaDon;
        this.doAnUong = doAnUong;
        this.soLuong = soLuong;
        this.phong = phong;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTraPhong = ngayTraPhong;
        this.soGio = 0;
        this.soLuongNguoiO = soLuongNguoiO;
        this.soLuongDoUongTraVe = soLuongDoUongTraVe;
        this.tongTienThuePhong = tongTienThuePhong;
        this.tongTienDichVu = tongTienDichVu;
        this.phuPhi = phuPhi;
    }

    public ChiTietHoaDon(HoaDon hoaDon, DoAnUong doAnUong, int soLuong, Phong phong, int soGio, LocalDate ngayNhanPhong, LocalDate ngayTraPhong, int soLuongNguoiO, int soLuongDoUongTraVe, double tongTienThuePhong, double tongTienDichVu, double phuPhi) {
        this.hoaDon = hoaDon;
        this.doAnUong = doAnUong;
        this.soLuong = soLuong;
        this.phong = phong;
        this.soGio = soGio;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTraPhong = ngayTraPhong;
        this.soLuongNguoiO = soLuongNguoiO;
        this.soLuongDoUongTraVe = soLuongDoUongTraVe;
        this.tongTienThuePhong = tongTienThuePhong;
        this.tongTienDichVu = tongTienDichVu;
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

    public void setPhuPhi(double phuPhi) {
        this.phuPhi = phuPhi;
    }

    public void tinhTongTienDichVu() {
        if (doAnUong != null) {
            this.tongTienDichVu = this.soLuong * doAnUong.getGiaBan();
        } else {
            this.tongTienDichVu = 0;
        }

    }

    public int getSoGio() {
        return soGio;
    }

    public void setSoGio(int soGio) {
        this.soGio = soGio;
    }

    
    
    
    public void tinhTienThuePhong(Phong phong, boolean isTheoNgay) {
        if (isTheoNgay) {
            int soTien1n = 0;
            if (phong.getLoaiPhong().getTenLoai() == 1) {
                soTien1n = 500000;
            } else if (phong.getLoaiPhong().getTenLoai() == 2) {
                soTien1n = 600000;
            } else if (phong.getLoaiPhong().getTenLoai() == 3) {
                soTien1n = 750000;
            } else if (phong.getLoaiPhong().getTenLoai() == 4) {
                soTien1n = 900000;
            } else if (phong.getLoaiPhong().getTenLoai() == 5) {
                soTien1n = 420000;
            }

            // Kiểm tra nếu ngày nhận và trả không null và ngày trả phải sau ngày nhận
            if (ngayNhanPhong != null && ngayTraPhong != null && ngayTraPhong.isAfter(ngayNhanPhong)) {
                // Tính số ngày giữa ngày nhận và ngày trả phòng
                long soNgayThue = ChronoUnit.DAYS.between(ngayNhanPhong, ngayTraPhong);

                // Tính tổng tiền thuê phòng dựa trên số ngày và giá thuê mỗi ngày
                double tongTienThue = soTien1n*soNgayThue*24*0.5;
                this.tongTienThuePhong = tongTienThue + this.phuPhi;
                System.out.println("entity.ChiTietHoaDon.tinhTienThuePhong()" + phuPhi);
            } else {
                // Trả về 0 hoặc giá trị mặc định khi thông tin không hợp lệ
                this.tongTienThuePhong = 0;
            }
        } else {
            if (phong.getLoaiPhong().getTenLoai() == 1) {
                if(soGio == 1) this.tongTienThuePhong = 500000;
                if(soGio == 2) this.tongTienThuePhong = 800000;
                if(soGio == 3) this.tongTienThuePhong = 1050000;
                if(soGio >= 4) this.tongTienThuePhong = 3600000;
            } else if (phong.getLoaiPhong().getTenLoai() == 2) {
                if(soGio == 1) this.tongTienThuePhong = 600000;
                if(soGio == 2) this.tongTienThuePhong = 960000;
                if(soGio == 3) this.tongTienThuePhong = 1260000;
                if(soGio >= 4) this.tongTienThuePhong = 4320000;
            } else if (phong.getLoaiPhong().getTenLoai() == 3) {
                if(soGio == 1) this.tongTienThuePhong = 750000;
                if(soGio == 2) this.tongTienThuePhong = 1200000;
                if(soGio == 3) this.tongTienThuePhong = 1575000;
                if(soGio >= 4) this.tongTienThuePhong = 5400000;
            } else if (phong.getLoaiPhong().getTenLoai() == 4) {
                if(soGio == 1) this.tongTienThuePhong = 900000;
                if(soGio == 2) this.tongTienThuePhong = 1440000;
                if(soGio == 3) this.tongTienThuePhong = 1890000;
                if(soGio >= 4) this.tongTienThuePhong = 6480000;
            } else if (phong.getLoaiPhong().getTenLoai() == 5) {
                if(soGio == 1) this.tongTienThuePhong = 420000;
                if(soGio == 2) this.tongTienThuePhong = 672000;
                if(soGio == 3) this.tongTienThuePhong = 882000;
                if(soGio >= 4) this.tongTienThuePhong = 3024000;
            }
        }
    }

    //tinhTongPhuPhi (tl4 thiếu cái này)

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "hoaDon=" + hoaDon + ", doAnUong=" + doAnUong + ", soLuong=" + soLuong + ", phong=" + phong + ", soGio=" + soGio + ", ngayNhanPhong=" + ngayNhanPhong + ", ngayTraPhong=" + ngayTraPhong + ", soLuongNguoiO=" + soLuongNguoiO + ", soLuongDoUongTraVe=" + soLuongDoUongTraVe + ", tongTienThuePhong=" + tongTienThuePhong + ", tongTienDichVu=" + tongTienDichVu + ", phuPhi=" + phuPhi + '}';
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
