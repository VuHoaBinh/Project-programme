/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.itextpdf.text.log.Logger;
import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import java.io.IOException;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.lang.System.Logger.Level;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class HoaDon_DAO {

    /**
     *
     * @param getAllTableHoaDon
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<HoaDon> getAllTableHoaDon() throws java.sql.SQLException {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM HoaDon";
        java.sql.Statement statement = con.createStatement();
        java.sql.ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maHoaDon = rs.getString("maHoaDon");
            String maKhachHang = rs.getString("khachHang");
            String maKhuyenMai = rs.getString("khuyenMai");
            String maNhanVien = rs.getString("nhanVien");
            LocalDate ngayLapHoaDon = rs.getDate("ngayLapHoaDon").toLocalDate();
            double thue = rs.getDouble("thue");
            double tongThanhTienBanDau = rs.getDouble("tongThanhTienBanDau");
            double tongThanhTienPhaiTra = rs.getDouble("tongThanhTienPhaiTra");

            // Assuming you have appropriate constructors for KhachHang, KhuyenMai, and NhanVien
            KhachHang khachHang = new KhachHang(maKhachHang); // Replace with correct instantiation
            KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai); // Replace with correct instantiation
            NhanVien nhanVien = new NhanVien(maNhanVien); // Replace with correct instantiation

            HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, khuyenMai, nhanVien, ngayLapHoaDon, thue, tongThanhTienBanDau, tongThanhTienPhaiTra);
            dsHoaDon.add(hoaDon);
        }
        return dsHoaDon;
    }

    /**
     *
     * @param maHoaDon
     * @param getPhongTheoMaHoaDon
     * @return
     * @throws IOException
     * @throws java.sql.SQLException
     */
    public ArrayList<HoaDon> getHoaDonTheoMaHoaDon(String maHoaDon) throws IOException, java.sql.SQLException {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM HoaDon WHERE maHoaDon = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maHoaDon);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String maKhachHang = rs.getString("khachHang");
            String maKhuyenMai = rs.getString("khuyenMai");
            String maNhanVien = rs.getString("nhanVien");
            LocalDate ngayLapHoaDon = rs.getDate("ngayLapHoaDon").toLocalDate();
            double thue = rs.getDouble("thue");
            double tongThanhTienBanDau = rs.getDouble("tongThanhTienBanDau");
            double tongThanhTienPhaiTra = rs.getDouble("tongThanhTienPhaiTra");

            // Assuming you have appropriate constructors for KhachHang, KhuyenMai, and NhanVien
            KhachHang khachHang = new KhachHang(maKhachHang); // Replace with correct instantiation
            KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai); // Replace with correct instantiation
            NhanVien nhanVien = new NhanVien(maNhanVien); // Replace with correct instantiation

            HoaDon hoaDon = new HoaDon(maHoaDon, khachHang, khuyenMai, nhanVien, ngayLapHoaDon, thue, tongThanhTienBanDau, tongThanhTienPhaiTra);
            dsHoaDon.add(hoaDon);

        }

        return dsHoaDon;
    }

    public boolean createHoaDon(HoaDon hd) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        statement = con.prepareStatement("INSERT INTO HoaDon VALUES (?,?,?,?,?,?,?,?)");
        statement.setString(1, hd.getMaHoaDon());
        statement.setString(2, hd.getKhachHang().getMaKhachHang());
        if(hd.getKhuyenMai()!=null){
            statement.setString(3, hd.getKhuyenMai().getMaKhuyenMai());
        }
        else{
            statement.setString(3, null);
        }
        statement.setString(4, hd.getNhanVien().getMaNhanVien());
        statement.setDate(5, java.sql.Date.valueOf(hd.getNgayLapHoaDon()));
        statement.setDouble(6, hd.getThue());
        statement.setDouble(7, hd.getTongThanhTienBanDau());
        statement.setDouble(8, hd.getTongThanhTienPhaiTra());

        n = statement.executeUpdate();
        return n > 0;
    }

    public boolean createHoaDonTam(HoaDon hd) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            statement = con.prepareStatement("INSERT INTO HoaDonTam VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1, hd.getMaHoaDon());
            statement.setString(2, hd.getKhachHang().getMaKhachHang());
            statement.setString(3, hd.getKhuyenMai().getMaKhuyenMai());
            statement.setString(4, hd.getNhanVien().getMaNhanVien());
            statement.setDate(5, java.sql.Date.valueOf(hd.getNgayLapHoaDon()));
            statement.setDouble(6, hd.getThue());
            statement.setDouble(7, hd.getTongThanhTienBanDau());
            statement.setDouble(8, hd.getTongThanhTienPhaiTra());

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public void updateHoaDon(HoaDon hd) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE HoaDon SET maHoaDon = ?, khachHang = ?, khuyenMai=?, nhanVien=?,"
                + " ngayLapHoaDon = ?, thue = ?, tongThanhTienBanDau=?, tongThanhTienPhaiTra=? WHERE maHoaDon=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, hd.getMaHoaDon());
            statement.setString(2, hd.getKhachHang().getMaKhachHang());
            statement.setString(3, hd.getKhuyenMai().getMaKhuyenMai());
            statement.setString(4, hd.getNhanVien().getMaNhanVien());
            statement.setDate(5, java.sql.Date.valueOf(hd.getNgayLapHoaDon()));
            statement.setDouble(6, hd.getThue());
            statement.setDouble(7, hd.getTongThanhTienBanDau());
            statement.setDouble(8, hd.getTongThanhTienPhaiTra());
            statement.setString(9, hd.getMaHoaDon());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHoaDon(HoaDon p) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from HoaDon where maHoaDon = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getMaHoaDon());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int demSoLuongHoaDonTheoMaMau(String time) {
        int soLuong = 0;
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String mau = time;
        System.out.println(time);
        String sql = "SELECT COUNT(*) AS soLuong FROM HoaDon WHERE maHoaDon LIKE ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, "%"+mau+"%");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("soLuong");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return soLuong;
    }

    public double tinhTongTienDichVu(String maHoaDon) throws SQLException {
        double tongTienDichVu = 0;

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql = "SELECT SUM(tongTienDichVu) AS tong FROM ChiTietHoaDon WHERE hoaDon = ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, maHoaDon);
            rs = statement.executeQuery();
            if (rs.next()) {
                tongTienDichVu = rs.getDouble("tong");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tongTienDichVu;
    }

    public double tinhthanhTienBanDau(String maHoaDon) throws SQLException {
        double tongTienDichVu = 0;
        double tongTienThuePhong = 0;

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        String sql = "SELECT SUM(tongTienDichVu) AS tongTienDichVu, AVG(tongTienThuePhong) AS tongTienThuePhong "
                + "FROM ChiTietHoaDon WHERE hoaDon = ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, maHoaDon);
            rs = statement.executeQuery();
            if (rs.next()) {
                tongTienDichVu = rs.getDouble("tongTienDichVu");
                tongTienThuePhong = rs.getDouble("tongTienThuePhong");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Tính tổng tổngTienDichVu và tongTienThuePhong
        double tongTien = tongTienDichVu + tongTienThuePhong;
        return tongTien;
    }
    
    public String[] getAllThangLapHD() {
		String[] list = new String[12 * getAllNamLapHD().length];
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;

		try {
			String sql = "SELECT DISTINCT \r\n"
					+ "CONCAT(MONTH(ngayLapHoaDon), '/', YEAR(ngayLapHoaDon)) AS ThangNam , MONTH(ngayLapHoaDon), YEAR(ngayLapHoaDon)\r\n"
					+ "FROM HoaDon \r\n" + "ORDER BY YEAR(ngayLapHoaDon) DESC, MONTH(ngayLapHoaDon) ASC";
			statement = con.prepareStatement(sql);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery();
			// Duyệt kết quả trả về
			int i = 0;
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				list[i] = rs.getString("ThangNam");
				i++;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
    
    public int[] getAllNamLapHD() {
		int[] list = new int[5]; // Khoảng 5 năm
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;

		try {
			String sql = "SELECT DISTINCT\r\n" + "    YEAR(ngayLapHoaDon) AS Thang\r\n" + "FROM\r\n" + "    HoaDon\r\n"
					+ "ORDER BY\r\n" + "    Thang;";
			statement = con.prepareStatement(sql);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery();
			// Duyệt kết quả trả về
			int i = 0;
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				list[i] = rs.getInt(1);
				i++;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
    public class HoaDonThongKe {
		private HoaDon hoaDon;
		private double tongTienPhong;
		private double tongTienDV;

		public double getTongTienPhong() {
			return tongTienPhong;
		}

		public double getTongTienDV() {
			return tongTienDV;
		}

		public HoaDon getHoaDon() {
			return hoaDon;
		}

		public HoaDonThongKe(HoaDon hoaDon, double tongTienPhong, double tongTienDV) {
			super();
			this.hoaDon = hoaDon;
			this.tongTienPhong = tongTienPhong;
			this.tongTienDV = tongTienDV;
		}
	}

	public ArrayList<HoaDonThongKe> thongKeDoanhThuTheoNgay(Date ngay) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<HoaDonThongKe> dsHDTK = new ArrayList<HoaDonThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;

		try {
			String sql = "SELECT \r\n"
					+ "    HD.maHoaDon, HD.khachHang, HD.nhanVien, HD.NgayLapHoaDon, HD.tongThanhTienPhaiTra,\r\n"
					+ "    SUM(CTHD.tongTienDichVu) AS TongTienDV,\r\n"
					+ "    SUM(CTHD.tongTienThuePhong) AS TongTienSDPhong\r\n"
					+ "FROM \r\n" + "    HoaDon HD\r\n" + "LEFT JOIN \r\n"
					+ "    ChiTietHoaDon CTHD ON HD.maHoaDon = CTHD.hoaDon\r\n" + "WHERE\r\n"
					+ "    ngayLapHoaDon = ?\r\n" + "GROUP BY \r\n"
					+ "    HD.maHoaDon, HD.khachHang, HD.nhanVien, HD.ngayLapHoaDon, HD.tongThanhTienPhaiTra;";
			statement = con.prepareStatement(sql);
			statement.setDate(1, new java.sql.Date(ngay.getTime()));
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				String maHD = rs.getString(1);
                                KhachHang kh = new KhachHang(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
                                LocalDate ngayLapHoaDon = rs.getDate(4).toLocalDate();
                                double tongThanhTienPhaiTra = rs.getDouble(5);
				double tongTienDV = rs.getDouble(6);
				double tongTienPhong = rs.getDouble(7);

				HoaDon hoaDon = new HoaDon(maHD, kh, nv, ngayLapHoaDon, tongThanhTienPhaiTra);
				HoaDonThongKe hdtk = new HoaDonThongKe(hoaDon, tongTienDV, tongTienPhong);
				dsHDTK.add(hdtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dsHDTK;
	}

	public ArrayList<HoaDonThongKe> thongKeDoanhThuTheoThang(int thang, int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<HoaDonThongKe> dsHDTK = new ArrayList<HoaDonThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;

		try {
			String sql = "SELECT \r\n"
					+ "    HD.maHoaDon, HD.khachHang, HD.nhanVien, HD.NgayLapHoaDon, HD.tongThanhTienPhaiTra,\r\n"
					+ "    SUM(CTHD.tongTienDichVu) AS TongTienDV,\r\n"
					+ "    SUM(CTHD.tongTienThuePhong) AS TongTienSDPhong\r\n"
					+ "FROM \r\n" + "    HoaDon HD\r\n" + "LEFT JOIN \r\n"
					+ "    ChiTietHoaDon CTHD ON HD.maHoaDon = CTHD.hoaDon\r\n" + "WHERE\r\n"
					+ "    MONTH(ngayLapHoaDon) = ? AND YEAR(ngayLapHoaDon) = ?\r\n" + "GROUP BY \r\n"
					+ "    HD.maHoaDon, HD.khachHang, HD.nhanVien, HD.ngayLapHoaDon, HD.tongThanhTienPhaiTra;";
                        
			statement = con.prepareStatement(sql);
			statement.setInt(1, thang);
			statement.setInt(2, nam);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				String maHD = rs.getString(1);
                                KhachHang kh = new KhachHang(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
                                LocalDate ngayLapHoaDon = rs.getDate(4).toLocalDate();
                                double tongThanhTienPhaiTra = rs.getDouble(5);
				double tongTienDV = rs.getDouble(6);
				double tongTienPhong = rs.getDouble(7);

				HoaDon hoaDon = new HoaDon(maHD, kh, nv, ngayLapHoaDon, tongThanhTienPhaiTra);
				HoaDonThongKe hdtk = new HoaDonThongKe(hoaDon, tongTienDV, tongTienPhong);
				dsHDTK.add(hdtk);

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dsHDTK;
	}

	public ArrayList<HoaDonThongKe> thongKeDoanhThuTheoNam(int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<HoaDonThongKe> dsHDTK = new ArrayList<HoaDonThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;

		try {
                        String sql = "SELECT \r\n"
					+ "    HD.maHoaDon, HD.khachHang, HD.nhanVien, HD.ngayLapHoaDon, HD.tongThanhTienPhaiTra,\r\n"
					+ "    SUM(CTHD.tongTienDichVu) AS TongTienDV,\r\n"
					+ "    SUM(CTHD.tongTienThuePhong) AS TongTienSDPhong\r\n"
					+ "FROM \r\n" + "    HoaDon HD\r\n" + "LEFT JOIN \r\n"
					+ "    ChiTietHoaDon CTHD ON HD.maHoaDon = CTHD.hoaDon\r\n" + "WHERE\r\n"
					+ "    YEAR(ngayLapHoaDon) = ?\r\n" + "GROUP BY \r\n"
					+ "    HD.maHoaDon, HD.khachHang, HD.nhanVien, HD.ngayLapHoaDon, HD.tongThanhTienPhaiTra;";
                        
			statement = con.prepareStatement(sql);
			statement.setInt(1, nam);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				String maHD = rs.getString(1);
                                KhachHang kh = new KhachHang(rs.getString(2));
				NhanVien nv = new NhanVien(rs.getString(3));
                                LocalDate ngayLapHoaDon = rs.getDate(4).toLocalDate();
                                double tongThanhTienPhaiTra = rs.getDouble(5);
				double tongTienDV = rs.getDouble(6);
				double tongTienPhong = rs.getDouble(7);

				HoaDon hoaDon = new HoaDon(maHD, kh, nv, ngayLapHoaDon, tongThanhTienPhaiTra);
				HoaDonThongKe hdtk = new HoaDonThongKe(hoaDon, tongTienDV, tongTienPhong);
				dsHDTK.add(hdtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dsHDTK;
	}

	public Double[] thongKeBieuDoLineTongDoanhThu(int namTK) {
		Double[] thongKeList = new Double[12];
		for (int i = 0; i < thongKeList.length; i++) {
			thongKeList[i] = (double) 0;
		}
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
//			String sql = "SELECT \r\n" + "	MONTH(ngayLapHoaDon) as Thang, YEAR(ngayLapHoaDon) as Nam,\r\n"
//					+ "    SUM(CTHD.tongTienDichVu) AS TongTienDV,\r\n"
//					+ "    SUM(CTHD.tongTienThuePhong) AS TongTienSDPhong\r\n"
//					+ "FROM \r\n" + "    HoaDon HD\r\n" + "LEFT JOIN \r\n"
//					+ "    ChiTietHoaDon CTHD ON HD.maHoaDon = CTHD.hoaDon\r\n" + "WHERE\r\n"
//					+ "   YEAR(ngayLapHoaDon) = ?\r\n" + "Group by\r\n" + "	MONTH(ngayLapHoaDon), YEAR(ngayLapHoaDon)\r\n"
//					+ "Order by\r\n" + "	YEAR(ngayLapHoaDon) DESC, MONTH(ngayLapHoaDon) DESC";
                        String sql = "SELECT \r\n" + "	MONTH(ngayLapHoaDon) as Thang, YEAR(ngayLapHoaDon) as Nam,\r\n"
					+ "    SUM(HD.tongThanhTienPhaiTra) AS TongTienDV,\r\n"
					+ "    SUM(HD.tongThanhTienPhaiTra) AS TongTienSDPhong\r\n"
					+ "FROM \r\n" + "    HoaDon HD\r\n" + "LEFT JOIN \r\n"
					+ "    ChiTietHoaDon CTHD ON HD.maHoaDon = CTHD.hoaDon\r\n" + "WHERE\r\n"
					+ "   YEAR(ngayLapHoaDon) = ?\r\n" + "Group by\r\n" + "	MONTH(ngayLapHoaDon), YEAR(ngayLapHoaDon)\r\n"
					+ "Order by\r\n" + "	YEAR(ngayLapHoaDon) DESC, MONTH(ngayLapHoaDon) DESC";
                        
                        
			statement = con.prepareStatement(sql);
			statement.setInt(1, namTK);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				thongKeList[rs.getInt(1) - 1] = rs.getDouble(3) + rs.getDouble(4);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return thongKeList;
	}

	public int[] thongKeBieuDoLineTongHoaDon(int namTK) {
		int[] thongKeList = new int[12];
		for (int i = 0; i < thongKeList.length; i++) {
			thongKeList[i] = 0;
		}
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n" + "    MONTH(ngayLapHoaDon) as Thang, \r\n" + "    YEAR(ngayLapHoaDon) as Nam,\r\n"
					+ "    SUM(CTHD.tongTienDichVu) AS TongTienDV,\r\n"
					+ "    SUM(CTHD.tongTienThuePhong) AS TongTienSDPhong,\r\n"
					+ "    COUNT(HD.maHoaDon) as SoHoaDon\r\n" + "FROM \r\n" + "    HoaDon HD\r\n" + "LEFT JOIN \r\n"
					+ "    ChiTietHoaDon CTHD ON HD.maHoaDon = CTHD.hoaDon\r\n" + "WHERE\r\n"
					+ "    YEAR(ngayLapHoaDon) = ?\r\n" + "GROUP BY\r\n" + "    MONTH(ngayLapHoaDon), YEAR(ngayLapHoaDon)\r\n"
					+ "ORDER BY\r\n" + "    YEAR(ngayLapHoaDon) DESC, MONTH(ngayLapHoaDon) DESC;";
                        
                        
			statement = con.prepareStatement(sql);
			statement.setInt(1, namTK);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				thongKeList[rs.getInt(1) - 1] = rs.getInt(5);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return thongKeList;
	}

	public Double[] thongKeBieuDoLineDoanhThuCaoNhatMoiThang(int namTK) {
		Double[] thongKeList = new Double[12];
		for (int i = 0; i < thongKeList.length; i++) {
			thongKeList[i] = (double) 0;
		}
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "WITH RankedRevenue AS (\r\n" + "    SELECT \r\n" + "        HD.maHoaDon,\r\n"
					+ "        MONTH(HD.ngayLapHoaDon) as Thang, \r\n" + "        YEAR(HD.ngayLapHoaDon) as Nam,\r\n"
					+ "        COALESCE(SUM(CTHD.tongTienDichVu), 0) AS TongTienDV,\r\n"
					+ "        COALESCE(SUM(CTHD.tongTienThuePhong), 0) AS TongTienSDPhong,\r\n"
					+ "        COALESCE(SUM(CTHD.tongTienDichVu), 0) + COALESCE(SUM(CTHD.tongTienThuePhong), 0) AS TongDoanhThu\r\n"
					+ "    FROM \r\n" + "        HoaDon HD\r\n" + "    LEFT JOIN \r\n"
					+ "    ChiTietHoaDon CTHD ON HD.maHoaDon = CTHD.hoaDon\r\n" + "WHERE\r\n"
					+ "        YEAR(HD.ngayLapHoaDon) = ? -- @Nam là biến bạn truyền vào\r\n" + "    GROUP BY\r\n"
					+ "        HD.maHoaDon, MONTH(HD.ngayLapHoaDon), YEAR(HD.ngayLapHoaDon)\r\n" + ")\r\n" + "SELECT \r\n"
					+ "    Thang, \r\n" + "    Nam,\r\n" + "    MAX(TongDoanhThu) AS DoanhThuCaoNhatTrongThang\r\n"
					+ "FROM \r\n" + "    RankedRevenue\r\n" + "GROUP BY\r\n" + "    Thang, Nam;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, namTK);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				thongKeList[rs.getInt(1) - 1] = rs.getDouble(3);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return thongKeList;
	}

	public Double[] thongKeBieuDoLineDoanhThuThapNhatMoiThang(int namTK) {
		Double[] thongKeList = new Double[12];
		for (int i = 0; i < thongKeList.length; i++) {
			thongKeList[i] = (double) 0;
		}
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "WITH RankedRevenue AS (\r\n" + "    SELECT \r\n" + "        HD.maHoaDon,\r\n"
					+ "        MONTH(HD.ngayLapHoaDon) as Thang, \r\n" + "        YEAR(HD.ngayLapHoaDon) as Nam,\r\n"
					+ "        COALESCE(SUM(CTHD.tongTienDichVu),0) AS TongTienDV,\r\n"
					+ "        COALESCE(SUM(CTHD.tongTienThuePhong),0) AS TongTienSDPhong,\r\n"
					+ "        COALESCE(SUM(CTHD.tongTienDichVu), 0) + COALESCE(SUM(CTHD.tongTienThuePhong), 0) AS TongDoanhThu,\r\n"
					+ "        ROW_NUMBER() OVER (PARTITION BY MONTH(HD.ngayLapHoaDon), YEAR(HD.ngayLapHoaDon) ORDER BY (COALESCE(SUM(CTHD.tongTienDichVu), 0) + COALESCE(SUM(CTHD.tongTienThuePhong), 0)) ASC) as RowNum\r\n"
					+ "    FROM \r\n" + "        HoaDon HD\r\n" + "    LEFT JOIN \r\n"
					+ "    ChiTietHoaDon CTHD ON HD.maHoaDon = CTHD.hoaDon\r\n" + "WHERE\r\n"
					+ "        YEAR(HD.ngayLapHoaDon) = ? -- @Nam là biến bạn truyền vào\r\n" + "    GROUP BY\r\n"
					+ "        HD.maHoaDon, MONTH(HD.ngayLapHoaDon), YEAR(HD.ngayLapHoaDon)\r\n" + ")\r\n" + "SELECT \r\n"
					+ "    Thang, \r\n" + "    Nam,\r\n" + "    MIN(TongDoanhThu) AS DoanhThuThapNhatTrongThang\r\n"
					+ "FROM \r\n" + "    RankedRevenue\r\n" + "WHERE \r\n" + "    RowNum = 1\r\n" + "GROUP BY\r\n"
					+ "    Thang, Nam;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, namTK);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				thongKeList[rs.getInt(1) - 1] = rs.getDouble(3);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return thongKeList;
	}
    
    
    
    
}
