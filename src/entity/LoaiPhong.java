package entity;

/**
 *
 * @author M S I
 */
public enum LoaiPhong {
    BASIC(1),
    STANDARD(2),
    BUSINESS(3),
    VIP(4),DOUBLE(5);

    private final int tenLoai;

    private LoaiPhong(int tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getTenLoai() {
        return tenLoai;
    }
    
    
}