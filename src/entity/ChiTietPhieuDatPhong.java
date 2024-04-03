/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author LENOVO
 */
public class ChiTietPhieuDatPhong {

    private PhieuDatPhong phieuDatPhong;
    private Phong phong;

    public ChiTietPhieuDatPhong() {
    }

    public ChiTietPhieuDatPhong(PhieuDatPhong phieuDatPhong, Phong phong) {
        this.phieuDatPhong = phieuDatPhong;
        this.phong = phong;
    }

    public PhieuDatPhong getPhieuDatPhong() {
        return phieuDatPhong;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
        this.phieuDatPhong = phieuDatPhong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuDatPhong{" + "phieuDatPhong=" + phieuDatPhong + ", phong=" + phong + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.phieuDatPhong);
        hash = 83 * hash + Objects.hashCode(this.phong);
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

        final ChiTietPhieuDatPhong other = (ChiTietPhieuDatPhong) obj;
        if (!Objects.equals(this.phieuDatPhong, other.phieuDatPhong)) {
            return false;
        }
        return Objects.equals(this.phong, other.phong);
    }

}
