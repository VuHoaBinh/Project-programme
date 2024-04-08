CREATE DATABASE QuanLyKhachSan
USE QuanLyKhachSan
GO

CREATE TABLE Phong (
    maPhong VARCHAR(4) PRIMARY KEY,
    tenPhong VARCHAR(5),
    loaiPhong VARCHAR(30),
    trangThaiPhong VARCHAR(30),
    dienTichPhong FLOAT,
    soGiuong INT,
    giuongPhu BIT,
    view_ VARCHAR(255),
    hutThuoc BIT,
    hinhAnhPhong VARCHAR(255)
);

CREATE TABLE KhachHang(
    maKhachHang VARCHAR(10) PRIMARY KEY,
    hoTenKhachHang NVARCHAR(30) NOT NULL,
    gioiTinh BIT,
    CCCD VARCHAR(12),
    ngaySinh DATE,
    trangThaiKhachHang BIT
);

CREATE TABLE NhanVien (
    maNhanVien VARCHAR(9) PRIMARY KEY,
    hoTenNhanVien NVARCHAR(50) NOT NULL,
    chucVu BIT,
    gioiTinh BIT,
    trangThaiLamViec BIT,
    diaChi NVARCHAR(50) NOT NULL,
    soDienThoai VARCHAR(10) NOT NULL,
    hinhAnh VARCHAR(255)
);

CREATE TABLE TaiKhoan (
    nhanVien VARCHAR(9),
    matKhau VARCHAR(30),
    FOREIGN KEY (nhanVien) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE DoAnUong (
    maDoAnUong VARCHAR(12) PRIMARY KEY,
    tenDoAnUong NVARCHAR(50) NOT NULL,
    loai BIT,
    giaNhap FLOAT,
    giaBan FLOAT,
    hoanTra BIT,
    soLuong INT,
    ngaySanXuat DATE,
    hanSuDung DATE,
    moTa VARCHAR(255),
    trangThaiSuDung VARCHAR(30)
);

CREATE TABLE KhuyenMai(
    maKhuyenMai VARCHAR(12) PRIMARY KEY,
    trangThaiKhuyenMai BIT,
    giaTri FLOAT,
    ngayBatDau DATE,
    ngayKetThuc DATE,
    noiDung NVARCHAR(255)
);

CREATE TABLE HoaDon(
    maHoaDon VARCHAR(18) PRIMARY KEY,
    khachHang VARCHAR(10),
    khuyenMai VARCHAR(12),
    nhanVien VARCHAR(9),
    ngayLapHoaDon DATE,
    thue FLOAT,
    tongThanhTienBanDau FLOAT,
    tongThanhTienPhaiTra FLOAT,
    FOREIGN KEY (khachHang) REFERENCES KhachHang(maKhachHang),
    FOREIGN KEY (nhanVien) REFERENCES NhanVien(maNhanVien),
    FOREIGN KEY (khuyenMai) REFERENCES KhuyenMai(maKhuyenMai)
);

CREATE TABLE PhieuDatPhong (
    maPhieuDatPhong VARCHAR(19) PRIMARY KEY,
    khachHang VARCHAR(10),
    nhanVien VARCHAR(9),
    soLuongNguoi INT,
    ngayDatPhong DATE,
    ngayNhanPhong DATE,
    ngayTraPhong DATE,
    tienPhong FLOAT,
    FOREIGN KEY (khachHang) REFERENCES KhachHang(maKhachHang),
    FOREIGN KEY (nhanVien) REFERENCES NhanVien(maNhanVien)
);

CREATE TABLE ChiTietPhieuDatPhong (
    phieuDatPhong VARCHAR(19),
    phong VARCHAR(4),
    FOREIGN KEY (phieuDatPhong) REFERENCES PhieuDatPhong(maPhieuDatPhong),
    FOREIGN KEY (phong) REFERENCES Phong(maPhong)
);

CREATE TABLE ChiTietHoaDon(
    hoaDon VARCHAR(18),
    doAnUong VARCHAR(12),
    phong VARCHAR(4),
    soLuong INT,
    ngayNhanPhong DATE,
    ngayTraPhong DATE,
    soLuongNguoiO INT,
    soLuongDoUongTraVe INT,
    tongTienThuePhong FLOAT,
    tongTienDichVu FLOAT,
    tongThanhTien FLOAT,
    phuPhi FLOAT,
    FOREIGN KEY (hoaDon) REFERENCES HoaDon(maHoaDon),
    FOREIGN KEY (doAnUong) REFERENCES DoAnUong(maDoAnUong),
    FOREIGN KEY (phong) REFERENCES Phong(maPhong)
);
INSERT INTO NhanVien (maNhanVien, hoTenNhanVien, chucVu, gioiTinh, trangThaiLamViec, diaChi, soDienThoai, hinhAnh)
VALUES ('NV001', N'Nguyễn Văn A', 1, 1, 1, N'Hà Nội', '0123456789', 'anh_nv1.jpg');

INSERT INTO NhanVien (maNhanVien, hoTenNhanVien, chucVu, gioiTinh, trangThaiLamViec, diaChi, soDienThoai, hinhAnh)
VALUES ('NV002', N'Trần Thị B', 0, 0, 1, N'Hồ Chí Minh', '0987654321', 'anh_nv2.jpg');

INSERT INTO NhanVien (maNhanVien, hoTenNhanVien, chucVu, gioiTinh, trangThaiLamViec, diaChi, soDienThoai, hinhAnh)
VALUES ('NV003', N'Lê Văn C', 0, 1, 0, N'Đà Nẵng', '0369852147', 'anh_nv3.jpg');

-- Thêm 5 phòng ngẫu nhiên vào bảng Phong
INSERT INTO Phong (maPhong, tenPhong, loaiPhong, trangThaiPhong, dienTichPhong, soGiuong, giuongPhu, view_, hutThuoc, hinhAnhPhong)
VALUES ('P001', '101', 'Phòng Đơn', 'Trống', 25.5, 1, 0, 'City View', 0, 'phong101.jpg');

INSERT INTO Phong (maPhong, tenPhong, loaiPhong, trangThaiPhong, dienTichPhong, soGiuong, giuongPhu, view_, hutThuoc, hinhAnhPhong)
VALUES ('P002', '102', 'Phòng Đôi', 'Đã Đặt', 35.0, 1, 1, 'Ocean View', 1, 'phong102.jpg');

INSERT INTO Phong (maPhong, tenPhong, loaiPhong, trangThaiPhong, dienTichPhong, soGiuong, giuongPhu, view_, hutThuoc, hinhAnhPhong)
VALUES ('P003', '103', 'Phòng Suite', 'Trống', 45.5, 2, 1, 'Mountain View', 0, 'phong103.jpg');

INSERT INTO Phong (maPhong, tenPhong, loaiPhong, trangThaiPhong, dienTichPhong, soGiuong, giuongPhu, view_, hutThuoc, hinhAnhPhong)
VALUES ('P004', '104', 'Phòng Đôi', 'Đã Đặt', 35.0, 1, 1, 'City View', 1, 'phong104.jpg');

INSERT INTO Phong (maPhong, tenPhong, loaiPhong, trangThaiPhong, dienTichPhong, soGiuong, giuongPhu, view_, hutThuoc, hinhAnhPhong)
VALUES ('P005', '105', 'Phòng Đơn', 'Trống', 25.5, 1, 0, 'City View', 0, 'phong105.jpg');

