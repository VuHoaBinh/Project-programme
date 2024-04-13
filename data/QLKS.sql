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
    gioiTinh BIT,
    CCCD VARCHAR(12),
    ngaySinh DATE,
    trangThaiKhachHang BIT
);

INSERT INTO KhachHang (maKhachHang, hoTenKhachHang, gioiTinh, CCCD, ngaySinh, trangThaiKhachHang)
VALUES ('0123456789', N'Nguyễn Văn An', 1, '034290567890', '1990-05-15', 0),
	   ('0349249242', N'Nguyễn Quốc Đạt', 1, '079203789012', '2003-04-19', 0),
	   ('0745303329', N'Hà Thị Thu', 0, '079398292933', '1998-12-13', 0),
	   ('0692493912', N'Trần Thanh Văn', 1, '034200789910', '2000-04-20', 0),
	   ('0523923923', N'Huỳnh Hoàng Trinh', 0, '012397428922', '1997-05-02', 1),
	   ('0789889112', N'Nguyễn Dũng', 1, '079202443267', '2002-07-08', 0),
	   ('0332567242', N'Thân Thụy Thương', 0, '079391003241', '1991-05-13', 0),
	   ('0824571334', N'Lâm Trường Quân', 1, '083290780744', '1990-11-01', 0),
	   ('0755221354', N'Lâm Duy Luân', 1, '054287667535', '1987-12-22', 0),
	   ('0934024006', N'Nguyễn Viết Xuân', 0, '034388667391', '1988-01-27', 0),
	   ('0456620041', N'Phạm Vũ Quyên', 0, '079385777583', '1985-08-29', 1),
	   ('0904126785', N'Dương Minh Huy', 1, '056295260539', '1995-09-04', 1),
	   ('0456620041', N'Trần Ngọc Uyên Linh', 0, '079394543367', '1994-06-07', 0),
	   ('0886424139', N'Vũ Thị Nương', 0, '079301769383', '2001-02-11', 0),
	   ('0924566203', N'Trương Quỳnh Như', 0, '079303660481', '2003-11-23', 0),
	   ('0772831339', N'Lê Thị Lan', 0, '032300432121', '2000-01-01', 0),
	   ('0933818313', N'Trần Văn Minh', 1, '0122956789029', '1995-03-05', 0),
       ('0123849232', N'Phạm Thị Hoa', 0, '034302567801', '2002-07-12', 0);

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
VALUES ('NV2411001', N'Vũ Hòa Bình', 1, 1, 1, '123 Phạm Văn Đồng', '0987654321', null),
	   ('NV2412002', N'Nguyễn Tiến Đạt', 0, 1, 1, '78/12 Quang Trung', '0832432268', null),
	   ('NV2412003', N'Phạm Hoàng Long', 0, 1, 1, '2A/32 Nguyễn Tri Phương', '0724897623', null),
	   ('NV2412004', N'Trần Quang Nhân', 0, 1, 1, '745 Quang Trung', '0767323927', null),
	   ('NV2412005', N'Vũ Nam Phương', 0, 1, 0, '18 Dương Quảng Hàm', '0988324131', null),
	   ('NV2401001', N'Nguyễn Hà My', 1, 0, 0, '23 Võ Văn Tần', '0244984202', null),
	   ('NV2412006', N'Huỳnh Văn Hải', 0, 1, 1, '67 Hai Bà Trưng', '0599883339', null),
	   ('NV2402002', N'Cao Thị Mỹ Duyên', 0, 0, 1, '12 Lê Thánh Tôn', '0287832999', null),
	   ('NV2412008', N'Ngô Anh Minh', 0, 1, 0, '34 Trần Hưng Đạo', '0394442174', null),
	   ('NV2412009', N'Chung Hải Nam', 0, 1, 0, '21A Hai Bà Trưng', '0124832679', null),
	   ('NV2402003', N'Phan Thị Anh Nhi', 0, 0, 1, '143 Lê Thánh Tôn', '0345539637', null),
	   ('NV2412010', N'Đinh Văn Toàn', 0, 1, 1, '456 Hai Bà Trưng', '0963214578', null),
	   ('NV2402004', N'Trần Thị Thu Hà', 0, 0, 1, '789 Sư Vạn Hạnh', '0829384765', null),
	   ('NV2412011', N'Nguyễn Minh Hoàng', 0, 1, 1, '123/456 Phan Văn Trị', '0987234567', null),
	   ('NV2402005', N'Phạm Thị Thu Trang', 0, 0, 1, '789/1011 Âu Cơ', '0846543210', null);

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
    moTa VARCHAR(255),
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

INSERT INTO HoaDon (maHoaDon, khachHang, khuyenMai, nhanVien, ngayLapHoaDon, thue, tongThanhTienBanDau, tongThanhTienPhaiTra)
VALUES ('MHD203002042024001', '0123456789', 'KM1504202401', 'NV2412002', '2024-04-02', 0.1, 18000000.0, 16500000.0),
	   ('MHD152210022024001', '0824571334', 'KM0102202301', 'NV2412006', '2024-02-10', 0.1, 2000000.0, 1900000.0),
	   ('MHD063401042024001', '0924566203', 'KM0102202301', 'NV2412003', '2024-04-01', 0.1, 9000000.0, 9000000.0),
	   ('MHD105023022024001', '0692493912', 'KM0102202301', 'NV2412004', '2024-02-23', 0.1, 10080000.0, 1080000.0),
	   ('MHD130201042024002', '0789889112', 'KM1504202401', 'NV2402003', '2024-04-01', 0.1, 2700000.0, 2700000.0),
	   ('MHD193701042024003', '0933818313', 'KM1504202401', 'NV2402002', '2024-04-01', 0.1, 3800000.0, 3800000.0),
	   ('MHD194501042024004', '0824571334', 'KM1504202401', 'NV2402002', '2024-04-01', 0.1, 1400000.0, 1400000.0),
	   ('MHD150202042024002', '0772831339', 'KM1504202401', 'NV2411001', '2024-04-02', 0.1, 1800000.0, 1800000.0),
	   ('MHD181302042024003', '0349249242', 'KM1504202401', 'NV2411001', '2024-04-02', 0.1, 2000000.0, 2000000.0),
	   ('MHD195623012024001', '0523923923', 'null', 'NV2412009', '2024-01-23', 0.1, 2100000.0, 2120000.0),
	   ('MHD084301012024001', '0755221354', 'KM0101202401', 'NV2401001', '2024-01-01', 0.1, 2200000.0, 1500000.0),
	   ('MHD100901012024002', '0934024006', 'KM0101202401', 'NV2401001', '2024-01-01', 0.1, 7200000.0, 7000000.0),
	   ('MHD092514032024001', '0904126785', 'KM1103202401', 'NV2412010', '2024-03-14', 0.1, 144000000.0, 14000000.0),
	   ('MHD100314032024002', '0886424139', 'KM1103202401', 'NV2412010', '2024-03-14', 0.1, 1500000.0, 1140000.0),
	   ('MHD112014032024003', '0456620041', 'KM1103202401', 'NV2412010', '2024-03-14', 0.1, 1400000.0, 1040000.0),
	   ('MHD133123022024001', '0692493912', 'KM0102202301', 'NV2412004', '2024-02-23', 0.1, 5500000.0, 5500000.0),
	   ('MHD120415032024001', '0123849232', 'KM1103202401', 'NV2412008', '2024-03-15', 0.1, 1500000.0, 1140000.0),
	   ('MHD213302042024004', '0332567242', 'KM1504202401', 'NV2412002', '2024-04-02', 0.1, 2100000.0, 2100000.0);
	   
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
 
INSERT INTO PhieuDatPhong (maPhieuDatPhong, khachHang, nhanVien, soLuongNguoi, ngayDatPhong, ngayNhanPhong, ngayTraPhong, tienPhong)
VALUES ('MPDP073227032024001', '0123456789', 'NV2411000', 2, '2024-03-27', '2024-03-30', '2024-04-02', 18000000.0),
	   ('MPDP061429032024001', '0924566203', 'NV2411000', 2, '2024-03-29', '2024-03-31', '2024-04-01', 9000000.0),
	   ('MPDP202430122023001', '0934024006', 'NV2411000', 2, '2023-12-30', '2023-12-31', '2024-01-01', 7200000.0),
	   ('MPDP195020022024001', '0692493912', 'NV2411000', 4, '2024-02-20', '2024-02-22', '2024-02-23', 1080000.0),
	   ('MPDP183511032024001', '0904126785', 'NV2411000', 2, '2024-03-11', '2024-03-12', '2024-03-14', 14400000.0);

CREATE TABLE ChiTietPhieuDatPhong (
    phieuDatPhong VARCHAR(19),
    phong VARCHAR(4),
    FOREIGN KEY (phieuDatPhong) REFERENCES PhieuDatPhong(maPhieuDatPhong),
    FOREIGN KEY (phong) REFERENCES Phong(maPhong)
);

INSERT INTO ChiTietPhieuDatPhong (phieuDatPhong, phong)
VALUES ('MPDP073227032024001', '0202'),
	   ('MPDP061429032024001', '0103'),
	   ('MPDP202430122023001', '0303'),
	   ('MPDP195020022024001', '0104'),
	   ('MPDP183511032024001', '0305');

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
<<<<<<< HEAD

INSERT INTO ChiTietHoaDon (hoaDon, doAnUong, phong, soLuong, ngayNhanPhong, ngayTraPhong, soLuongNguoiO, soLuongDoUongTraVe, tongTienThuePhong, tongTienDichVu, tongThanhTien, phuPhi)
VALUES ('MHD203022042024001', 'DV0104202401', '0102', 2, '2024-04-10', '2024-04-14', 2, 0, 600000.0, 50000.0, 650000.0, 20000.0);
=======
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
>>>>>>> origin/TrangChu_ver1

