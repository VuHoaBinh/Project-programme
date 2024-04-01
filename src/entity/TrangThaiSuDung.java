/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author M S I
 */
public enum TrangThaiSuDung {
    AVAILABLE(1),
    UNAVAILABLE(2),
    EXPIRED(3);

    private final int tenTrangThaiSuDung;

    private TrangThaiSuDung(int tenTrangThaiSuDung) {
        this.tenTrangThaiSuDung = tenTrangThaiSuDung;
    }

    public int getTentrangThaiSuDung() {
        return tenTrangThaiSuDung;
    }
}
