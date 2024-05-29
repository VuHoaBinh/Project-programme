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
INSERT INTO Phong (maPhong, tenPhong, loaiPhong, trangThaiPhong, dienTichPhong, soGiuong, giuongPhu, view_, hutThuoc, hinhAnhPhong)
VALUES 
('0101', 'P101', N'BASIC', 'AVAILABLE', 25.5, 1, 0, 'City view', 0, null),
('0102', 'P102', N'STANDARD', 'BOOKED', 30.0, 2, 1, 'Beach view', 1, null),
('0103', 'P103', N'BUSINESS', 'AVAILABLE', 40.0, 3, 1, 'Mountain view', 0, null),
('0104', 'P104', N'VIP', 'AVAILABLE', 40.0, 3, 1, 'Mountain view', 0, null),
('0105', 'P105', N'DOUBLE', 'AVAILABLE', 35.0, 2, 1, 'Beach view', 0, null),
('0106', 'P106', N'DOUBLE', 'AVAILABLE', 35.0, 2, 1, 'Mountain view', 0, null),
('0107', 'P107', N'BASIC', 'BOOKED', 30.0, 2, 1, 'City view', 1, null),
('0108', 'P108', N'VIP', 'OCCUPIED', 45.0, 2, 0, 'Mountain view', 0, null),
('0109', 'P109', N'BUSINESS', 'UNAVAILABLE', 30.0, 2, 1, 'Beach view', 1, null),
('0201', 'P201', N'STANDARD', 'UNAVAILABLE', 25.0, 1, 0, 'Mountain view', 0, null),
('0202', 'P202', N'BASIC', 'AVAILABLE', 20.0, 1, 0, 'City view', 1, null),
('0203', 'P203', N'DOUBLE', 'BOOKED', 35.0, 2, 1, 'Park view', 1, null),
('0204', 'P204', N'VIP', 'AVAILABLE', 45.0, 3, 0, 'Mountain view', 0, null),
('0205', 'P205', N'BASIC', 'OCCUPIED', 30.0, 1, 0, 'Beach view', 1, null),
('0301', 'P301', N'VIP', 'BOOKED', 35.0, 2, 0, 'Mountain view', 0, null),
('0302', 'P302', N'DOUBLE', 'AVAILABLE', 30.0, 2, 1, 'City view', 1, null),
('0303', 'P303', N'STANDARD', 'AVAILABLE ', 30.0, 2, 1, 'Pool view', 0, null),
('0304', 'P304', N'STANDARD', 'AVAILABLE ', 35.0, 2, 1, 'Pool view', 0, null),
('0305', 'P305', N'STANDARD', 'AVAILABLE ', 30.0, 2, 0, 'Gardeb view', 1, null),
('0401', 'P401', N'BUSINESS', 'OCCUPIED', 35.0, 2, 0, 'Garden view', 0, null),
('0402', 'P402', N'STANDARD', 'AVAILABLE', 30.0, 2, 1, 'Beach view', 1, null);
CREATE TABLE KhachHang(
    maKhachHang VARCHAR(10) PRIMARY KEY,
    hoTenKhachHang NVARCHAR(30) NOT NULL,
	email VARCHAR(255) NOT NULL,
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
INSERT INTO NhanVien (maNhanVien, hoTenNhanVien, chucVu, gioiTinh, trangThaiLamViec, diaChi, soDienThoai, hinhAnh)
VALUES ('NV2411001', N'Vũ Hòa Bình', 1, 1, 1, N'123 Phạm Văn Đồng', '0987654321', null),
	   ('NV2412002', N'Nguyễn Tiến Đạt', 0, 1, 1, N'78/12 Quang Trung', '0832432268', null),
	   ('NV2412003', N'Phạm Hoàng Long', 0, 1, 1, N'2A/32 Nguyễn Tri Phương', '0724897623', null),
	   ('NV2412004', N'Trần Quang Nhân', 0, 1, 1, N'745 Quang Trung', '0767323927', null),
	   ('NV2412005', N'Vũ Nam Phương', 0, 1, 0, N'18 Dương Quảng Hàm', '0988324131', null),
	   ('NV2401001', N'Nguyễn Hà My', 1, 0, 0, N'23 Võ Văn Tần', '0244984202', null),
	   ('NV2412006', N'Huỳnh Văn Hải', 0, 1, 1, N'67 Hai Bà Trưng', '0599883339', null),
	   ('NV2402002', N'Cao Thị Mỹ Duyên', 0, 0, 1, N'12 Lê Thánh Tôn', '0287832999', null),
	   ('NV2412008', N'Ngô Anh Minh', 0, 1, 0, N'34 Trần Hưng Đạo', '0394442174', null),
	   ('NV2412009', N'Chung Hải Nam', 0, 1, 0, N'21A Hai Bà Trưng', '0124832679', null),
	   ('NV2402003', N'Phan Thị Anh Nhi', 0, 0, 1, N'143 Lê Thánh Tôn', '0345539637', null),
	   ('NV2412010', N'Đinh Văn Toàn', 0, 1, 1, N'456 Hai Bà Trưng', '0963214578', null),
	   ('NV2402004', N'Trần Thị Thu Hà', 0, 0, 1, N'789 Sư Vạn Hạnh', '0829384765', null),
	   ('NV2412011', N'Nguyễn Minh Hoàng', 0, 1, 1, N'123/456 Phan Văn Trị', '0987234567', null),
	   ('NV2402005', N'Phạm Thị Thu Trang', 0, 0, 1, N'789/1011 Âu Cơ', '0846543210', null);

CREATE TABLE TaiKhoan (
    nhanVien VARCHAR(9),
    matKhau VARCHAR(30),
    FOREIGN KEY (nhanVien) REFERENCES NhanVien(maNhanVien)
);
INSERT INTO TaiKhoan (nhanVien, matKhau)
VALUES ('NV2411001', '123'),
	   ('NV2412002', '123'),
       ('NV2412003', '123'),
	   ('NV2412004', '123'),
	   ('NV2412006', '123'),
	   ('NV2402002', '123'),
	   ('NV2402003', '123'),
	   ('NV2412010', '123'),
	   ('NV2402004', '123'),
	   ('NV2412011', '123'),
	   ('NV2402005', '123');
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
    moTa NVARCHAR(255),
    trangThaiSuDung VARCHAR(30)
);

INSERT INTO DoAnUong (maDoAnUong, tenDoAnUong, loai, giaNhap, giaBan, hoanTra, soLuong, ngaySanXuat, hanSuDung, moTa, trangThaiSuDung)
VALUES ('DV0104202401', N'Bánh mì chảo', 1, 25000, 35000, 0, 50, '2024-04-01', '2024-04-02', N'Bánh mì chảo thơm ngon', 'AVAILABLE'),
	   ('DV0104202402', N'Beefsteak', 1, 30000, 40000, 0, 50, '2024-04-01', '2024-04-02', N'Beefsteaks', 'AVAILABLE'),
	   ('DV0104202403', N'Tokbokki phô mai', 1, 15000, 25000, 0, 40, '2024-04-01', '2024-04-2', N'Tokbokki phô mai', 'AVAILABLE'),
	   ('DV0104202404', N'Cơm chiên dương châu', 1, 15000, 25000, 0, 20, '2024-04-01', '2024-04-02', N'Cơm chiên dương châu', 'AVAILABLE'),
	   ('DV0104202405', N'Mì xào thập cẩm', 1, 20000, 30000, 0, 10, '2024-04-01', '2024-04-15', N'Mì xào thập cẩm', 'AVAILABLE'),
	   ('DV0104202406', N'Bánh bao 2 trứng', 1, 10000, 20000, 0, 0, '2024-04-01', '2024-04-02', N'Bánh bao 2 trứng', 'UNAVAILABLE'),
	   ('DV0204202401', N'Sprite', 0, 8000, 18000, 1, 200, '2023-12-03', '2025-04-30', N'Sprite', 'AVAILABLE'),
	   ('DV0204202402', N'Mirinda Xa xi', 0, 15000, 25000, 1, 10, '2023-01-01', '2024-02-22', N'Mirinda Xa Xi', 'EXPIRED'),
	   ('DV0204202403', N'Mirinda Soda Kem', 0, 7000, 17000, 1, 150, '2023-11-02', '2025-09-29', N'Mirinda Soda Kem', 'AVAILABLE'),
	   ('DV0204202404', N'Mirinda Cam', 0, 7000, 17000, 1, 100, '2023-12-28', '2025-09-29', N'Mirinda Cam', 'AVAILABLE'),
	   ('DV0204202405', N'Coca Cola', 0, 8000, 18000, 1, 200, '2024-02-22', '2025-08-25', N'Coca Cola', 'AVAILABLE'),
	   ('DV0204202406', N'Pesi', 0, 8000, 18000, 1, 200, '2024-04-02', '2025-04-15', N'Pesi', 'AVAILABLE'),
	   ('DV0204202407', N'7 Up', 0, 7000, 17000, 1, 150, '2024-03-19', '2025-10-05', N'7 Up', 'AVAILABLE'),
	   ('DV0304202401', N'Pepsi Max', 0, 8000, 18000, 1, 20, '2023-07-22', '2024-01-12', N'Pepsi Max', 'EXPIRED'),
	   ('DV0304202402', N'Trà Lipton', 0, 5000, 12000, 1, 0, '2024-04-03', '2025-11-22', N'Trà Lipton', 'UNAVAILABLE'),
	   ('DV0304202403', N'Trà Ô Long', 0, 7000, 14000, 1, 150, '2023-12-01', '2026-01-11', N'Trà Ô Long', 'AVAILABLE'),
	   ('DV0304202404', N'Cà phê Trung Nguyên', 0, 10000, 20000, 1, 100, '2024-04-02', '2025-02-08', N'Cà phê Trung Nguyên', 'AVAILABLE'),
	   ('DV0304202405', N'Nước suối Lavie', 0, 5000, 10000, 1, 500, '2023-06-03', '2025-04-02', N'Nước suối Lavie', 'AVAILABLE'),
	   ('DV0304202406', N'Nước suối Aquafina', 0, 6000, 11000, 1, 300, '2023-05-24', '2025-05-01', N'Nước suối Aquafina', 'AVAILABLE'),
	   ('DV0304202407', N'Nước suối Dasani', 0, 7000, 12000, 1, 200, '2024-04-02', '2025-06-01', N'Nước suối Dasani', 'AVAILABLE');

CREATE TABLE KhuyenMai(
    maKhuyenMai VARCHAR(12) PRIMARY KEY,
    trangThaiKhuyenMai BIT,
    giaTri FLOAT,
    ngayBatDau DATE,
    ngayKetThuc DATE,
    noiDung NVARCHAR(255)
);

INSERT INTO KhuyenMai (maKhuyenMai, trangThaiKhuyenMai, giaTri, ngayBatDau, ngayKetThuc, noiDung)
VALUES ('KM1504202401', 0, 0.2, '2024-04-15', '2024-05-15', N'Giảm giá 20% cho đơn hàng trong tháng 4'),
	   ('KM0204202301', 0, 0.3, '2023-05-03', '2023-06-03', N'Giảm giá 30% cho đơn hàng trong tháng 5'),
	   ('KM0102202301', 0, 0.1, '2023-02-01', '2024-08-01', N'Giảm giá 10% cho khách hàng đi theo tour'),
	   ('KM1103202401', 0, 0.25, '2024-03-11', '2024-03-17', N'Giảm giá 25% đón tuần lễ vàng'),
	   ('KM0101202401', 0, 0.35, '2024-01-01', '2024-01-02', N'Giảm giá 35% Năm mới deal sốc'),
	   ('KM1304202401', 0, 0.2, '2024-04-30', '2024-05-02', N'Giảm giá 20% Lễ 30/04');

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
    trangThaiChiTietPhieuDatPhong BIT,
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
	soGio INT,
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



