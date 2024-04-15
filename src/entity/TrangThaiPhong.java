/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author M S I
 */
public enum TrangThaiPhong {
    BOOKED(1),
    OCCUPIED(2),
    AVAILABLE(3),
    UNAVAILABLE(4);

    private final int tenTrangThai;

    private TrangThaiPhong(int tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public int getTenTrangThai() {
        return tenTrangThai;
    }
    public static TrangThaiPhong fromInt(int value) {
        for (TrangThaiPhong trangThai : TrangThaiPhong.values()) {
            if (trangThai.getTenTrangThai() == value) {
                return trangThai;
            }
        }
        throw new IllegalArgumentException("Giá trị không hợp lệ: " + value);
    }
}