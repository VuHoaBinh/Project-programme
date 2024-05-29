/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class KhachHang_DAO {

    /**
     *
     * @param getAllTableKhachHang
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList<KhachHang> getAllTableKhachHang() throws java.sql.SQLException {
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM KhachHang";
        java.sql.Statement statement = con.createStatement();
        java.sql.ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maKhachHang = rs.getString("maKhachHang");
            String hoTenKhachHang = rs.getString("hoTenKhachHang");
            String email = rs.getString("email");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String CCCD = rs.getString("CCCD");
            LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
            boolean trangThaiKhachHang = rs.getInt("trangThaiKhachHang") == 1;

            KhachHang kh = new KhachHang(maKhachHang, hoTenKhachHang, email, gioiTinh,
                    CCCD, ngaySinh, trangThaiKhachHang);
            dsKhachHang.add(kh);
        }
        return dsKhachHang;
    }

    /**
     *
     * @param getPhongTheoMaKhachHang
     * @return
     * @throws IOException
     * @throws java.sql.SQLException
     */
    public ArrayList<KhachHang> getKHTheoMaKhachHang(String maKH) throws IOException, java.sql.SQLException {
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();

        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maKH);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String maKhachHang = rs.getString("maKhachHang");
            String hoTenKhachHang = rs.getString("hoTenKhachHang");
            String email = rs.getString("email");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String CCCD = rs.getString("CCCD");
            LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
            boolean trangThaiKhachHang = rs.getBoolean("trangThaiKhachHang");
            KhachHang kh = new KhachHang(maKhachHang, hoTenKhachHang, email, gioiTinh,
                    CCCD, ngaySinh, trangThaiKhachHang);
            dsKhachHang.add(kh);
        }
        return dsKhachHang;
    }

    public ArrayList<KhachHang> getDsKhachHangTheoMa(String maKhachHang) throws IOException, SQLException {
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;

        String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maKhachHang);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String hoTenKhachHang = rs.getString("hoTenKhachHang");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String CCCD = rs.getString("CCCD");
            String email = rs.getString("email");
            java.sql.Date ngaySinh = rs.getDate("ngaySinh");
            LocalDate ngaySinhLocalDate = ngaySinh.toLocalDate();
            boolean trangThaiKhachHang = rs.getBoolean("trangThaiKhachHang");
            KhachHang kh = new KhachHang(maKhachHang, hoTenKhachHang, email, gioiTinh, CCCD, ngaySinhLocalDate, trangThaiKhachHang);
            dsKhachHang.add(kh);
        }
        return dsKhachHang;
    }

    public KhachHang getKhachHangTheoMa(String maKhachHang) throws IOException, SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        KhachHang kh = null;
        PreparedStatement statement = null;

        String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, maKhachHang);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String hoTenKhachHang = rs.getString("hoTenKhachHang");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String email = rs.getString("email");
            String CCCD = rs.getString("CCCD");
            java.sql.Date ngaySinh = rs.getDate("ngaySinh");
            LocalDate ngaySinhLocalDate = ngaySinh.toLocalDate();
            boolean trangThaiKhachHang = rs.getBoolean("trangThaiKhachHang");
            kh = new KhachHang(maKhachHang, hoTenKhachHang, email, gioiTinh, CCCD, ngaySinhLocalDate, trangThaiKhachHang);
        }
        return kh;
    }

    public KhachHang getKhachHangTheoTen(String ten) throws IOException, SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        KhachHang kh = null;
        PreparedStatement statement = null;

        String sql = "SELECT * FROM KhachHang WHERE hoTenKhachHang = ?";
        statement = con.prepareStatement(sql);
        statement.setString(1, ten);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String maKhachHang = rs.getString("maKhachHang");
            String hoTenKhachHang = rs.getString("hoTenKhachHang");
            boolean gioiTinh = rs.getBoolean("gioiTinh");
            String email = rs.getString("email");
            String CCCD = rs.getString("CCCD");
            java.sql.Date ngaySinh = rs.getDate("ngaySinh");
            LocalDate ngaySinhLocalDate = ngaySinh.toLocalDate();
            boolean trangThaiKhachHang = rs.getBoolean("trangThaiKhachHang");

            kh = new KhachHang(maKhachHang, hoTenKhachHang, email, gioiTinh, CCCD, ngaySinhLocalDate, trangThaiKhachHang);
        }
        return kh;
    }

    public boolean createKhachHang(KhachHang kh) throws SQLException {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement statement = null;
        int n = 0;

        try {
            statement = con.prepareStatement("INSERT INTO KhachHang VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, kh.getMaKhachHang());
            statement.setString(2, kh.getHoTenKhachHang());
            statement.setString(3, kh.getEmai());
            statement.setBoolean(4, kh.isGioiTinh());
            statement.setString(5, kh.getCCCD());
            statement.setDate(6, java.sql.Date.valueOf(kh.getNgaySinh()));
            statement.setBoolean(7, kh.isTrangThaiKhachHang());

            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    public void updateKhachHang(KhachHang kh) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE KhachHang SET maKhachHang = ?, hoTenKhachHang = ?, email= ? ,gioiTinh=?, CCCD=?,"
                + " ngaySinh = ?, trangThaiKhachHang = ? WHERE maKhachHang=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, kh.getMaKhachHang());
            statement.setString(2, kh.getHoTenKhachHang());
            statement.setString(3, kh.getEmai());
            statement.setBoolean(4, kh.isGioiTinh());
            statement.setString(5, kh.getCCCD());
            statement.setDate(6, java.sql.Date.valueOf(kh.getNgaySinh()));
            statement.setBoolean(7, kh.isTrangThaiKhachHang());
            statement.setString(8, kh.getMaKhachHang());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTrangThaiKhachHang(String ma) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        String sql = "UPDATE KhachHang SET trangThaiKhachHang = ? WHERE maKhachHang=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setBoolean(1, false);
            statement.setString(2, ma);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteKhachHang(KhachHang p) {
        ConnectDB.getInstance();
        java.sql.Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE from KhachHang where maKhachHang = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getMaKhachHang());
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
    
    public class KhachHangThongKe {
        private KhachHang khachHang;
        private int tongTheoTieuChi;
        public KhachHangThongKe(KhachHang khachHang, int tongTheoTieuChi){
            this.khachHang = khachHang;
            this.tongTheoTieuChi = tongTheoTieuChi;
        }
        public KhachHang getKhachHang(){
            return khachHang;
        }
        public int getTongTheoTieuChi(){
            return tongTheoTieuChi;
        }
    }
    
    
    public ArrayList<KhachHangThongKe> thongKeKhachTheoNgay(Date ngay) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;

		try {
			String sql = "SELECT KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh, COUNT(*) AS SoLuongHoaDon\r\n"
					+ "FROM KhachHang\r\n" + "JOIN HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n"
					+ "WHERE HoaDon.maHoaDon IS NOT NULL AND ngayLapHoaDon = ?\r\n"
					+ "GROUP BY KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh;";
			statement = con.prepareStatement(sql);
			statement.setDate(1, new java.sql.Date(ngay.getTime()));
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int tongHoaDon = rs.getInt(6);
				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, tongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;
	}

	public ArrayList<KhachHangThongKe> thongKeKhachTheoThang(int thang, int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;

		try {
			String sql = "SELECT KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh, COUNT(*) AS SoLuongHoaDon\r\n"
					+ "FROM KhachHang\r\n" + "JOIN HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n"
					+ "WHERE HoaDon.maHoaDon IS NOT NULL AND MONTH(ngayLapHoaDon) = ? AND YEAR(ngayLapHoaDon) = ?\r\n"
					+ "GROUP BY KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, thang);
			statement.setInt(2, nam);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;
	}

	public ArrayList<KhachHangThongKe> thongKeKhachTheoNam(int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;

		try {
			String sql = "SELECT KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh, COUNT(*) AS SoLuongHoaDon\r\n"
					+ "FROM KhachHang\r\n" + "JOIN HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n"
					+ "WHERE HoaDon.maHoaDon IS NOT NULL AND YEAR(ngayLapHoaDon) = ?\r\n"
					+ "GROUP BY KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, nam);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;
	}

	public ArrayList<KhachHangThongKe> thongKeKHMoiTheoNgay(Date ngay) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh,\r\n"
					+ "    COUNT(HoaDon.maHoaDon) AS SoLuongHoaDon\r\n" + "FROM \r\n" + "    KhachHang\r\n" + "JOIN \r\n"
					+ "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n" + "    HoaDon.ngayLapHoaDon = ?\r\n"
					+ "    AND NOT EXISTS (\r\n" + "        SELECT 1\r\n" + "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND ngayLapHoaDon < ?\r\n" + "    )\r\n" + "GROUP BY \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh;";
			statement = con.prepareStatement(sql);
			statement.setDate(1, new java.sql.Date(ngay.getTime()));
			statement.setDate(2, new java.sql.Date(ngay.getTime()));
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;

	}

	public ArrayList<KhachHangThongKe> thongKeKHMoiTheoThang(int thang, int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh,\r\n"
					+ "    COUNT(HoaDon.maHoaDon) AS SoLuongHoaDon\r\n" + "FROM \r\n" + "    KhachHang\r\n" + "JOIN \r\n"
					+ "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n"
					+ "    YEAR(HoaDon.ngayLapHoaDon) = ?  AND MONTH(HoaDon.ngayLapHoaDon) = ?\r\n" + "    AND NOT EXISTS (\r\n"
					+ "        SELECT 1\r\n" + "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND YEAR(HoaDon.ngayLapHoaDon) = ?  AND MONTH(HoaDon.ngayLapHoaDon) < ?\r\n"
					+ "    )\r\n" + "GROUP BY \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, nam);
			statement.setInt(2, thang);
			statement.setInt(3, nam);
			statement.setInt(4, thang);

			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;

	}

	public ArrayList<KhachHangThongKe> thongKeKHMoiTheoNam(int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh,\r\n"
					+ "    COUNT(HoaDon.maHoaDon) AS SoLuongHoaDon\r\n" + "FROM \r\n" + "    KhachHang\r\n" + "JOIN \r\n"
					+ "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n"
					+ "    YEAR(HoaDon.ngayLapHoaDon) = ?\r\n" + "    AND NOT EXISTS (\r\n" + "        SELECT 1\r\n"
					+ "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND YEAR(HoaDon.ngayLapHoaDon) < ?\r\n" + "    )\r\n"
					+ "GROUP BY \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, nam);
			statement.setInt(2, nam);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;

	}

	public ArrayList<KhachHangThongKe> thongKeKHCuTheoNgay(Date ngay) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh,\r\n"
					+ "    COUNT(HoaDon.maHoaDon) AS SoLuongHoaDon\r\n" + "FROM \r\n" + "    KhachHang\r\n" + "JOIN \r\n"
					+ "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n"
					+ "    HoaDon.ngayLapHoaDon = ? -- Ngày hôm nay\r\n" + "    AND (\r\n" + "        SELECT COUNT(*)\r\n"
					+ "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND ngayLapHoaDon < ? -- Từ hôm qua trở về trước\r\n"
					+ "    ) >= 1\r\n" + "    AND (\r\n" + "        SELECT COUNT(*)\r\n" + "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND ngayLapHoaDon < ? -- Từ hôm qua trở về trước\r\n"
					+ "    ) < 10\r\n" + "GROUP BY \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh;";
			statement = con.prepareStatement(sql);
			statement.setDate(1, new java.sql.Date(ngay.getTime()));
			statement.setDate(2, new java.sql.Date(ngay.getTime()));
			statement.setDate(3, new java.sql.Date(ngay.getTime()));
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;

	}

	public ArrayList<KhachHangThongKe> thongKeKHCuTheoThang(int thang, int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh,\r\n"
					+ "    COUNT(HoaDon.maHoaDon) AS SoLuongHoaDon\r\n" + "FROM \r\n" + "    KhachHang\r\n" + "JOIN \r\n"
					+ "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n"
					+ "    YEAR(HoaDon.ngayLapHoaDon) = ? AND MONTH(HoaDon.ngayLapHoaDon) = ?-- Ngày hôm nay\r\n"
					+ "    AND (\r\n" + "        SELECT COUNT(*)\r\n" + "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND YEAR(HoaDon.ngayLapHoaDon) = ? AND MONTH(HoaDon.ngayLapHoaDon) < ? -- Từ hôm qua trở về trước\r\n"
					+ "    ) >= 1\r\n" + "    AND (\r\n" + "        SELECT COUNT(*)\r\n" + "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND YEAR(HoaDon.ngayLapHoaDon) = ? AND MONTH(HoaDon.ngayLapHoaDon) < ? -- Từ hôm qua trở về trước\r\n"
					+ "    ) < 10\r\n" + "GROUP BY \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, nam);
			statement.setInt(2, thang);
			statement.setInt(3, nam);
			statement.setInt(4, thang);
			statement.setInt(5, nam);
			statement.setInt(6, thang);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;

	}

	public ArrayList<KhachHangThongKe> thongKeKHCuTheoNam(int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh,\r\n"
					+ "    COUNT(HoaDon.maHoaDon) AS SoLuongHoaDon\r\n" + "FROM \r\n" + "    KhachHang\r\n" + "JOIN \r\n"
					+ "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n"
					+ "    YEAR(HoaDon.ngayLapHoaDon) = ?-- Ngày hôm nay\r\n" + "    AND (\r\n"
					+ "        SELECT COUNT(*)\r\n" + "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND YEAR(HoaDon.ngayLapHoaDon) < ?  -- Từ hôm qua trở về trước\r\n"
					+ "    ) >= 1\r\n" + "    AND (\r\n" + "        SELECT COUNT(*)\r\n" + "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND YEAR(HoaDon.ngayLapHoaDon) < ?  -- Từ hôm qua trở về trước\r\n"
					+ "    ) < 10\r\n" + "GROUP BY \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, nam);
			statement.setInt(2, nam);
			statement.setInt(3, nam);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;

	}

	public ArrayList<KhachHangThongKe> thongKeKHQuenTheoNgay(Date ngay) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh,\r\n"
					+ "    COUNT(HoaDon.maHoaDon) AS SoLuongHoaDon\r\n" + "FROM \r\n" + "    KhachHang\r\n" + "JOIN \r\n"
					+ "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n"
					+ "    HoaDon.ngayLapHoaDon = ? -- Ngày hôm nay\r\n" + "    AND (\r\n" + "        SELECT COUNT(*)\r\n"
					+ "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND ngayLapHoaDon < ? -- Từ hôm qua trở về trước\r\n"
					+ "    ) >= 10\r\n" + "GROUP BY \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh;";
			statement = con.prepareStatement(sql);
			statement.setDate(1, new java.sql.Date(ngay.getTime()));
			statement.setDate(2, new java.sql.Date(ngay.getTime()));
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;
	}

	public ArrayList<KhachHangThongKe> thongKeKHQuenTheoThang(int thang, int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh,\r\n"
					+ "    COUNT(HoaDon.maHoaDon) AS SoLuongHoaDon\r\n" + "FROM \r\n" + "    KhachHang\r\n" + "JOIN \r\n"
					+ "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n"
					+ "    YEAR(HoaDon.ngayLapHoaDon) = ? AND MONTH(HoaDon.ngayLapHoaDon) = ? -- Ngày hôm nay\r\n"
					+ "    AND (\r\n" + "        SELECT COUNT(*)\r\n" + "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND YEAR(HoaDon.ngayLapHoaDon) = ? AND MONTH(HoaDon.ngayLapHoaDon) < ? -- Từ hôm qua trở về trước\r\n"
					+ "    ) >= 10\r\n" + "GROUP BY \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, nam);
			statement.setInt(2, thang);
			statement.setInt(3, nam);
			statement.setInt(4, thang);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;
	}

	public ArrayList<KhachHangThongKe> thongKeKHQuenTheoNam(int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh,\r\n"
					+ "    COUNT(HoaDon.maHoaDon) AS SoLuongHoaDon\r\n" + "FROM \r\n" + "    KhachHang\r\n" + "JOIN \r\n"
					+ "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n"
					+ "    YEAR(HoaDon.ngayLapHoaDon) = ?\r\n" + "    AND (\r\n" + "        SELECT COUNT(*)\r\n"
					+ "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND YEAR(HoaDon.ngayLapHoaDon) < ?\r\n" + "    ) >= 10\r\n"
					+ "GROUP BY \r\n"
					+ "    KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, nam);
			statement.setInt(2, nam);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;
	}

	public ArrayList<KhachHangThongKe> thongKeKHCoHDNhieuNhatTheoNgay(Date ngay) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT TOP 10 KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh, COUNT(*) AS SoLuongHoaDon\r\n"
					+ "FROM KhachHang\r\n" + "JOIN HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n"
					+ "WHERE HoaDon.maHoaDon IS NOT NULL AND ngayLapHoaDon = ?\r\n"
					+ "GROUP BY KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh";
			statement = con.prepareStatement(sql);
			statement.setDate(1, new java.sql.Date(ngay.getTime()));
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;
	}

	public ArrayList<KhachHangThongKe> thongKeKHCoHDNhieuNhatTheoThang(int thang, int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT TOP 10 KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh, COUNT(*) AS SoLuongHoaDon\r\n"
					+ "FROM KhachHang\r\n" + "JOIN HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n"
					+ "WHERE HoaDon.maHoaDon IS NOT NULL AND YEAR(HoaDon.ngayLapHoaDon) = ? AND MONTH(HoaDon.ngayLapHoaDon) = ?\r\n"
					+ "GROUP BY KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh\r\n"
					+ "Order by COUNT(*) desc;\r\n" + "";
			statement = con.prepareStatement(sql);
			statement.setInt(1, nam);
			statement.setInt(2, thang);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;
	}

	public ArrayList<KhachHangThongKe> thongKeKHCoHDNhieuNhatTheoNam(int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT TOP 10 KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh, COUNT(*) AS SoLuongHoaDon\r\n"
					+ "FROM KhachHang\r\n" + "JOIN HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n"
					+ "WHERE HoaDon.maHoaDon IS NOT NULL AND YEAR(HoaDon.ngayLapHoaDon) = ? \r\n"
					+ "GROUP BY KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh\r\n"
					+ "Order by COUNT(*) desc";
			statement = con.prepareStatement(sql);
			statement.setInt(1, nam);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;
	}

	public ArrayList<KhachHangThongKe> thongKeKHCoHDItNhatTheoNgay(Date ngay) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT TOP 10 KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh, COUNT(*) AS SoLuongHoaDon\r\n"
					+ "FROM KhachHang\r\n" + "JOIN HoaDon ON KhachHang.maKhachHang = HoaDon.maKhachHang\r\n"
					+ "WHERE HoaDon.maHoaDon IS NOT NULL AND HoaDon.ngayLapHoaDon = ? \r\n"
					+ "GROUP BY KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh\r\n"
					+ "Order by COUNT(*) asc";
			statement = con.prepareStatement(sql);
			statement.setDate(1, new java.sql.Date(ngay.getTime()));
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;
	}

	public ArrayList<KhachHangThongKe> thongKeKHCoHDItNhatTheoThang(int thang, int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT TOP 10 KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh, COUNT(*) AS SoLuongHoaDon\r\n"
					+ "FROM KhachHang\r\n" + "JOIN HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n"
					+ "WHERE HoaDon.maHoaDon IS NOT NULL AND YEAR(HoaDon.ngayLapHoaDon) = ? AND MONTH(HoaDon.ngayLapHoaDon) = ?\r\n"
					+ "GROUP BY KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh\r\n"
					+ "Order by COUNT(*) asc";
			statement = con.prepareStatement(sql);
			statement.setInt(1, nam);
			statement.setInt(2, thang);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;
	}

	public ArrayList<KhachHangThongKe> thongKeKHCoHDItNhatTheoNam(int nam) {
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		ArrayList<KhachHangThongKe> dskh = new ArrayList<KhachHangThongKe>();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT TOP 10 KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh, COUNT(*) AS SoLuongHoaDon\r\n"
					+ "FROM KhachHang\r\n" + "JOIN HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n"
					+ "WHERE HoaDon.maHoaDon IS NOT NULL AND YEAR(HoaDon.ngayLapHoaDon) = ?\r\n"
					+ "GROUP BY KhachHang.maKhachHang, KhachHang.hoTenKhachHang, KhachHang.gioiTinh, KhachHang.CCCD, KhachHang.ngaySinh\r\n"
					+ "Order by COUNT(*) asc";
			statement = con.prepareStatement(sql);
			statement.setInt(1, nam);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				// Lấy thông tin từ ResultSet và tạo đối tượng KhachHang
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				boolean gioiTinhKH = rs.getBoolean(3);
				String cccd = rs.getString(4);
				LocalDate ngaySinhKH = rs.getDate(5).toLocalDate();
				int soLuongHoaDon = rs.getInt(6);

				KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinhKH, cccd, ngaySinhKH);
				KhachHangThongKe khtk = new KhachHangThongKe(khachHang, soLuongHoaDon);
				dskh.add(khtk);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Đóng connection, statement và resultSet ở đây
		}
		return dskh;
	}

	public int[] thongKeBieuDoLineTongKH(int namTK) {
		int[] intArray = new int[12];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = 0;
		}
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n" + "    MONTH(ngayLapHoaDon) AS Thang,\r\n" + "    YEAR(ngayLapHoaDon) AS Nam,\r\n"
					+ "    COUNT(DISTINCT KhachHang.maKhachHang) AS TongSoKH\r\n" + "FROM \r\n" + "    KhachHang\r\n"
					+ "LEFT JOIN \r\n" + "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n"
					+ "    YEAR(ngayLapHoaDon) = ?\r\n" + "GROUP BY \r\n" + "    MONTH(ngayLapHoaDon), YEAR(ngayLapHoaDon)\r\n"
					+ "ORDER BY \r\n" + "    YEAR(ngayLapHoaDon) DESC, MONTH(ngayLapHoaDon) DESC;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, namTK);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				intArray[rs.getInt(1) - 1] = rs.getInt(3);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return intArray;
	}

	public int[] thongKeBieuDoLineTongKHMoi(int namTK) {
		int[] intArray = new int[12];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = 0;
		}
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n" + "    MONTH(ngayLapHoaDon) AS Thang,\r\n" + "    YEAR(ngayLapHoaDon) AS Nam,\r\n"
					+ "    COUNT(DISTINCT KhachHang.maKhachHang) AS SoKhachHangMoi\r\n" + "FROM \r\n" + "    KhachHang\r\n"
					+ "JOIN \r\n" + "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n"
					+ "    YEAR(HoaDon.ngayLapHoaDon) = ?\r\n" + "    AND NOT EXISTS (\r\n" + "        SELECT 1\r\n"
					+ "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND YEAR(HoaDon.ngayLapHoaDon) < ?\r\n" + "    )\r\n"
					+ "group by MONTH(ngayLapHoaDon), YEAR(ngayLapHoaDon)\r\n"
					+ "order by  YEAR(ngayLapHoaDon) DESC, MONTH(ngayLapHoaDon) DESC;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, namTK);
			statement.setInt(2, namTK);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				intArray[rs.getInt(1) - 1] = rs.getInt(3);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return intArray;
	}

	public int[] thongKeBieuDoLineTongKHCu(int namTK) {
		int[] intArray = new int[12];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = 0;
		}
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n" + "    MONTH(ngayLapHoaDon) AS Thang,\r\n" + "    YEAR(ngayLapHoaDon) AS Nam,\r\n"
					+ "    COUNT(DISTINCT KhachHang.maKhachHang) AS SoKhachHangMoi\r\n" + "FROM \r\n" + "    KhachHang\r\n"
					+ "JOIN \r\n" + "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n"
					+ "    YEAR(HoaDon.ngayLapHoaDon) = ?\r\n" + "    AND (\r\n" + "        SELECT COUNT(*)\r\n"
					+ "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND YEAR(HoaDon.ngayLapHoaDon) < ?\r\n" + "    ) >= 1\r\n"
					+ "    AND (\r\n" + "        SELECT COUNT(*)\r\n" + "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND YEAR(HoaDon.ngayLapHoaDon) < ?\r\n" + "    ) < 10\r\n"
					+ "GROUP BY \r\n" + "    MONTH(ngayLapHoaDon), YEAR(ngayLapHoaDon)\r\n" + "ORDER BY \r\n"
					+ "    YEAR(ngayLapHoaDon) DESC, MONTH(ngayLapHoaDon) DESC;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, namTK);
			statement.setInt(2, namTK);
			statement.setInt(3, namTK);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				intArray[rs.getInt(1) - 1] = rs.getInt(3);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return intArray;
	}

	public int[] thongKeBieuDoLineTongKHQuen(int namTK) {
		int[] intArray = new int[12];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = 0;
		}
		ConnectDB.getInstance();
		java.sql.Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		ResultSet rs;
		try {
			String sql = "SELECT \r\n" + "    MONTH(ngayLapHoaDon) AS Thang,\r\n" + "    YEAR(ngayLapHoaDon) AS Nam,\r\n"
					+ "    COUNT(DISTINCT KhachHang.maKhachHang) AS SoKhachHangMoi\r\n" + "FROM \r\n" + "    KhachHang\r\n"
					+ "JOIN \r\n" + "    HoaDon ON KhachHang.maKhachHang = HoaDon.khachHang\r\n" + "WHERE \r\n"
					+ "    YEAR(HoaDon.ngayLapHoaDon) = ?\r\n" + "    AND (\r\n" + "        SELECT COUNT(*)\r\n"
					+ "        FROM HoaDon\r\n"
					+ "        WHERE maKhachHang = KhachHang.maKhachHang AND YEAR(HoaDon.ngayLapHoaDon) < ?\r\n" + "    ) >= 10\r\n"
					+ "GROUP BY \r\n" + "    MONTH(ngayLapHoaDon), YEAR(ngayLapHoaDon)\r\n" + "ORDER BY \r\n"
					+ "    YEAR(ngayLapHoaDon) DESC, MONTH(ngayLapHoaDon) DESC;";
			statement = con.prepareStatement(sql);
			statement.setInt(1, namTK);
			statement.setInt(2, namTK);
			// Thực hiện câu lệnh sql trả về đối tượng ResultSet
			rs = statement.executeQuery();
			// Duyệt kết quả trả về
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp
				intArray[rs.getInt(1) - 1] = rs.getInt(3);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return intArray;
	}
    
}
