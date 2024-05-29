package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import dao.HoaDon_DAO;
import entity.HoaDon;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;


public class JPanel_GiaoDienThongKeDoanhThu extends JPanel implements ActionListener {
	private final int fontSize = 12;

	private Font fontPlain, fontBold;
	private JTable tblTK, tblTKChiTiet;
	private DefaultTableModel modeltbTK, modelTKChiTiet;
	private JDatePickerImpl datePickerTu;
	private UtilDateModel modelThoiGian;
	private JPanel pnlInfor, pnlDetail, pnlTable, pnlGraph, pnlSelect, pnlPie;
	private HoaDon_DAO hd_dao;
	private JButton btnThongKe;
	private JLabel lblThoiGian, lblLoai;
	private JComboBox<String> cmbLoai, cmbThang, cmbNam;
	private ArrayList<HoaDon_DAO.HoaDonThongKe> dsTKHD;
	private Map<String, Integer> mapYear;
	private Map<String, ThangNam> mapMonth;
	private final String listLoai[][] = { { "Thời gian", "Ngày", "Tháng", "Năm" } };
	private class ThangNam {
		int thang;
		int nam;

		ThangNam(int thang, int nam) {
			this.thang = thang;
			this.nam = nam;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof ThangNam))
				return false;

			if (obj == this)
				return true;

			ThangNam tn = (ThangNam) obj;
			return this.thang == tn.thang && this.nam == tn.nam;
		}
	}

	/**
	 * @wbp.nonvisual location=583,-36
	 */
	public JPanel_GiaoDienThongKeDoanhThu() {
		this.fontPlain = new Font("Dialog", Font.PLAIN, fontSize);
		this.fontBold = new Font("Dialog", Font.BOLD, fontSize);
		setLayout(null);

		pnlSelect = new JPanel();
		pnlSelect.setBackground(new Color(255, 255, 255));
		pnlSelect.setBounds(22, 20, 590, 556);
		add(pnlSelect);
		pnlSelect.setLayout(null);

		pnlInfor = new JPanel();
		pnlInfor.setLayout(new BorderLayout());
		pnlInfor.setForeground(Color.BLACK);
		pnlInfor.setBorder(null);
		pnlInfor.setBackground(Color.WHITE);
		pnlInfor.setBounds(0, 0, 580, 257);
		pnlSelect.add(pnlInfor);

		pnlDetail = new JPanel();
		pnlDetail.setForeground(Color.BLACK);
		pnlDetail.setBorder(new TitledBorder(null, "Chi ti\u1EBFt th\u1ED1ng k\u00EA", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 128, 255)));
		pnlDetail.setBackground(Color.WHITE);
		pnlDetail.setBounds(0, 266, 580, 280);
		pnlSelect.add(pnlDetail);

		pnlGraph = new JPanel();
		pnlGraph.setBackground(new Color(255, 255, 255));
		pnlGraph.setBounds(622, 11, 639, 565);
		pnlGraph.setLayout(null);
		LineChartTest lineChart = new LineChartTest("");
		lineChart.setBounds(0, 0, 639, 554);
		pnlGraph.add(lineChart);
		add(pnlGraph);

		pnlTable = new JPanel();
		pnlTable.setBackground(new Color(255, 255, 255));
		pnlTable.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"B\u1EA3ng th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 255)));
		pnlTable.setLayout(null);
		pnlInfor.add(Box.createVerticalStrut(80));
		pnlInfor.add(pnlTable, BorderLayout.CENTER);


		modelThoiGian = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel1 = new JDatePanelImpl(modelThoiGian, p);
		datePickerTu = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		datePickerTu.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
		datePickerTu.getJFormattedTextField().setFont(fontPlain);
		SpringLayout springLayout2 = (SpringLayout) datePickerTu.getLayout();
		springLayout2.putConstraint(SpringLayout.SOUTH, datePickerTu.getJFormattedTextField(), 0, SpringLayout.SOUTH,
				datePickerTu);
		datePickerTu.setBounds(100, 20, 119, 22);
		pnlTable.add(datePickerTu);

		btnThongKe = new JButton("Thống kê");
		btnThongKe.setFont(fontBold);
		btnThongKe.setBounds(445, 20, 112, 23);
		btnThongKe.addActionListener(this);
		pnlTable.add(btnThongKe);

		pnlPie = new JPanel();
		pnlPie.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlPie.setBounds(27, 53, 530, 183);
		pnlTable.add(pnlPie);
		pnlPie.setLayout(null);
		lblThoiGian = new JLabel("Từ ngày");
		lblThoiGian.setFont(fontBold);
		lblThoiGian.setBounds(27, 20, 65, 22);
		pnlTable.add(lblThoiGian);

		lblLoai = new JLabel("Loại");
		lblLoai.setFont(fontBold);
		lblLoai.setBounds(234, 20, 51, 22);
		pnlTable.add(lblLoai);

		cmbLoai = new JComboBox<String>();
		cmbLoai.addItem(listLoai[0][1]);
		cmbLoai.addItem(listLoai[0][2]);
		cmbLoai.addItem(listLoai[0][3]);
		cmbLoai.setBounds(283, 20, 137, 21);
		pnlTable.add(cmbLoai);

		hd_dao = new HoaDon_DAO();

		cmbThang = new JComboBox<String>();
		cmbNam = new JComboBox<String>();
		mapMonth = new HashMap<>();
		mapYear = new HashMap<>();
		for (String thangNam : hd_dao.getAllThangLapHD()) {
			String thangTitle = "Tháng ";
			String parts[] = new String[2];
			ThangNam thangNamObj;
			if (thangNam != null) {
				parts = thangNam.split("/");
				if (parts != null) {
					int thang = Integer.parseInt(parts[0]);
					int nam = Integer.parseInt(parts[1]);
					thangNamObj = new ThangNam(thang, nam);
					mapMonth.put(thangTitle + thangNam, thangNamObj);
					cmbThang.addItem(thangTitle + thangNam);
				}
			}
		}
		cmbThang.setBounds(100, 20, 119, 22);

		for (int nam : hd_dao.getAllNamLapHD()) {
			String namTitle = "Năm ";
			if (nam != 0) {
				mapYear.put(namTitle + nam, nam);

				cmbNam.addItem(namTitle + nam);
			}
		}
		cmbNam.setBounds(100, 20, 119, 22);

		cmbLoai.setFont(fontPlain);
		cmbThang.setFont(fontPlain);
		cmbNam.setFont(fontPlain);

		pnlTable.add(cmbThang);
		pnlTable.add(cmbNam);
		taoBang();
		taoBangChiTiet();
		comboLoaiCheck();
	}
	// Renderer để thiết lập kiểu font
	private class CustomTableCellRenderer implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component cellComponent = table.getDefaultRenderer(Object.class).getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);

			// Kiểm tra nếu đang xử lý dòng header
			if (row == -1) {
				cellComponent.setFont(fontBold);
			} else {
				// Kiểm tra nếu đang xử lý cột header
				if (table.getColumnModel().getColumn(column).getModelIndex() == column) {
					cellComponent.setFont(fontBold);
				} else {
					cellComponent.setFont(fontPlain);
				}
			}

			return cellComponent;
		}
	}

	private void taoBang() {
		modeltbTK = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modeltbTK.addColumn("Tiêu chí");
		modeltbTK.addColumn("Kết quả");
		tblTK = new JTable(modeltbTK);
		TableColumn column;
		for (int i = 0; i < tblTK.getColumnCount(); i++) {
			column = tblTK.getColumnModel().getColumn(i);
			column.setCellRenderer(new TableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					JLabel label = (JLabel) table.getDefaultRenderer(table.getColumnClass(column))
							.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					// Đặt font cho nội dung trong ô
					label.setFont(fontPlain);
					return label;
				}
			});
		}
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        tblTK.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tblTK.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        

		// Đặt font cho dòng header
		tblTK.getTableHeader().setFont(this.fontBold);
		
		
		JScrollPane sp = new JScrollPane(tblTK, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(4, 5, 522, 174);
		pnlPie.add(sp);
		tblTK.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
//				modelTKChiTiet.setRowCount(0);
//				int row = tblTK.getSelectedRow();
//				docDuLieuVaoBangChiTiet(row);
			}
		});
	}
	
    private void handleHeaderClick(int columnIndex, JTable tbl, DefaultTableModel model) {
        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tbl.setRowSorter(sorter);

        List<? extends SortKey> sortKeys = sorter.getSortKeys();
        if (sortKeys.size() > 0 && sortKeys.get(0).getColumn() == columnIndex
                && sortKeys.get(0).getSortOrder() == SortOrder.DESCENDING) {
            // Đã sắp xếp giảm dần, chuyển sang sắp xếp tăng dần
            sorter.setSortKeys(null);
        } else {
            // Chưa sắp xếp hoặc sắp xếp tăng dần, chuyển sang sắp xếp giảm dần
            sorter.setSortKeys(List.of(new SortKey(columnIndex, SortOrder.DESCENDING)));
        }
    }

	/**
	 * 
	 */
	private void comboLoaiCheck() {
		cmbLoai.addActionListener(e -> {
			if (cmbLoai.getSelectedItem().toString().equals(listLoai[0][1])) {
				// Xóa tháng
				cmbThang.setVisible(false);

				// Xóa năm
				cmbNam.setVisible(false);

				// Thêm ngày
				datePickerTu.setVisible(true);
				lblThoiGian.setText("Từ ngày");


				// Render lại
				repaint();
			}

			if (cmbLoai.getSelectedItem().toString().equals(listLoai[0][2])) {
				// Xóa ngày
				datePickerTu.setVisible(false);

				// Xóa năm
				cmbNam.setVisible(false);

				// Thêm tháng
				cmbThang.setVisible(true);
				lblThoiGian.setText("Từ tháng");

				// Render lại
				repaint();
			}

			if (cmbLoai.getSelectedItem().toString().equals(listLoai[0][3])) {
				// Xóa ngày
				datePickerTu.setVisible(false);

				// Xóa tháng
				cmbThang.setVisible(false);


				// Thêm năm
				cmbNam.setVisible(true);
				lblThoiGian.setText("Từ năm");
				
				// Render lại
				repaint();
				}
			});
	}



	private void taoBangChiTiet() {
		modelTKChiTiet = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelTKChiTiet.addColumn("Thành tiền"); // 0
		modelTKChiTiet.addColumn("Mã HD"); // 1
		modelTKChiTiet.addColumn("Mã KH"); // 2
		modelTKChiTiet.addColumn("Mã NV"); // 4
		modelTKChiTiet.addColumn("Ngày lập hóa đơn"); // 5
		
		tblTKChiTiet = new JTable(modelTKChiTiet);
		TableColumn column;
		for (int i = 0; i < tblTKChiTiet.getColumnCount(); i++) {
			column = tblTKChiTiet.getColumnModel().getColumn(i);
			column.setCellRenderer(new TableCellRenderer() {
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					Component c = table.getDefaultRenderer(table.getColumnClass(column))
							.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					c.setFont(fontPlain);
					return c;
				}
			});
		}

		// Đặt font cho dòng header
		tblTKChiTiet.getTableHeader().setFont(this.fontBold);
		tblTKChiTiet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		pnlDetail.setLayout(null);

		tblTKChiTiet.getColumnModel().getColumn(0).setPreferredWidth(120); // Thành tiền
		tblTKChiTiet.getColumnModel().getColumn(1).setPreferredWidth(80); // Mã hóa đơn
                tblTKChiTiet.getColumnModel().getColumn(2).setPreferredWidth(80); // Mã khách hàng
		tblTKChiTiet.getColumnModel().getColumn(3).setPreferredWidth(80); // Mã nhân viên
		tblTKChiTiet.getColumnModel().getColumn(4).setPreferredWidth(150); // Ngày lập hóa đơn
		
		// sort
		tblTKChiTiet.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = tblTKChiTiet.columnAtPoint(e.getPoint());
                if (columnIndex != -1) {
                    handleHeaderClick(columnIndex, tblTKChiTiet, modelTKChiTiet);
                }
            }
        });

		JScrollPane sp1 = new JScrollPane(tblTKChiTiet, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp1.setBounds(27, 21, 530, 241);
		pnlDetail.add(sp1);
	}
	
	private void thongKeDoanhThu(String loai) {
		if (loai.equals(listLoai[0][1])) {
			Date ngay = (Date) datePickerTu.getModel().getValue();
			dsTKHD = new ArrayList<>();
			this.dsTKHD = hd_dao.thongKeDoanhThuTheoNgay(ngay);
			docDuLieuVaoTableTongQuat();
			docDuLieuVaoBangChiTiet();
		} else if (loai.equals(listLoai[0][2])) {
			String thangNamTu = cmbThang.getSelectedItem().toString();
			dsTKHD = new ArrayList<>();
			this.dsTKHD = hd_dao.thongKeDoanhThuTheoThang(mapMonth.get(thangNamTu).thang,mapMonth.get(thangNamTu).nam);
			docDuLieuVaoTableTongQuat();
			docDuLieuVaoBangChiTiet();
		} else if (loai.equals(listLoai[0][3])) {
			String namTu = cmbNam.getSelectedItem().toString();
			dsTKHD = new ArrayList<>();
			this.dsTKHD = hd_dao.thongKeDoanhThuTheoNam(mapYear.get(namTu));
			docDuLieuVaoTableTongQuat();
			docDuLieuVaoBangChiTiet();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnThongKe)) {
			if (validData()) {
				String loaiDaChon = cmbLoai.getSelectedItem().toString();
				modeltbTK.setRowCount(0);
				thongKeDoanhThu(loaiDaChon);
			}

		}

	}

	private boolean validData() {
		String loai = cmbLoai.getSelectedItem().toString();
		if (loai.equals(listLoai[0][1])) {
			String tuNgayBangChu = datePickerTu.getJFormattedTextField().getText();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			if (tuNgayBangChu.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn từ ngày");
				return false;
			} else if (LocalDate.parse(tuNgayBangChu, formatter).isAfter(LocalDate.now())) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn từ ngày bé hơn hoặc bằng ngày hiện hành");
				return false;
			}

		} 
		return true;

	}
    private static String formatCurrency(double amount) {
        // Tạo một đối tượng NumberFormat cho tiền tệ của Việt Nam
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);

        // Định dạng số và thêm kí hiệu tiền tệ
        return currencyFormatter.format(amount);
    }

	private void docDuLieuVaoTableTongQuat() {
		// Thêm tiêu
		modeltbTK.setRowCount(0);
		double doanhThuPhong = 0;
		double doanhThuDV = 0;
		double tongDoanhThu = 0;
		
		for (int i = 0; i < this.dsTKHD.size(); i++) {
			doanhThuDV += this.dsTKHD.get(i).getTongTienDV();
			doanhThuPhong += this.dsTKHD.get(i).getTongTienPhong();
		}
		tongDoanhThu = doanhThuDV + doanhThuPhong;
		modeltbTK.addRow(new Object[] {"Tổng tiền phòng", formatCurrency(doanhThuPhong)});
		modeltbTK.addRow(new Object[] {"Tổng tiền dịch vụ", formatCurrency(doanhThuDV)});
		modeltbTK.addRow(new Object[] {"Tổng doanh thu", formatCurrency(tongDoanhThu)});
		if (cmbLoai.getSelectedItem().toString().equals(listLoai[0][1])) {
			modeltbTK.addRow(new Object[] {"Doanh thu / giờ", formatCurrency(tongDoanhThu/24)});
			modeltbTK.addRow(new Object[] {"Tổng số hóa đơn", this.dsTKHD.size()});
			double hoaDonTheoGio = ((double) this.dsTKHD.size() / 24) * 0.1d;
			double roundedResult = Math.round(hoaDonTheoGio * 1000.0) / 1000.0;
			if (roundedResult * 10000 % 10 >= 5) {
			    roundedResult = Math.round(roundedResult * 1000.0 + 1.0) / 1000.0;
			}
			modeltbTK.addRow(new Object[] {"Số hóa đơn / giờ", roundedResult});
			modeltbTK.addRow(new Object[] {"Doanh thu / hóa đơn", formatCurrency(tongDoanhThu/this.dsTKHD.size())});
		} else if (cmbLoai.getSelectedItem().toString().equals(listLoai[0][2])) {
			String thangNamTu = cmbThang.getSelectedItem().toString();
			int soNgayCuaThangNam = YearMonth.of(mapMonth.get(thangNamTu).nam,mapMonth.get(thangNamTu).thang).lengthOfMonth();
			modeltbTK.addRow(new Object[] {"Doanh thu / giờ", formatCurrency(tongDoanhThu/(24*soNgayCuaThangNam))});
			modeltbTK.addRow(new Object[] {"Doanh thu / ngày", formatCurrency(tongDoanhThu/soNgayCuaThangNam)});
			modeltbTK.addRow(new Object[] {"Tổng số hóa đơn", this.dsTKHD.size()});
			double hoaDonTheoGio = ((double) this.dsTKHD.size() / (24*soNgayCuaThangNam)) * 0.1d;
			double roundedResultGio = Math.round(hoaDonTheoGio * 1000.0) / 1000.0;
			if (roundedResultGio * 10000 % 10 >= 5) {
				roundedResultGio = Math.round(roundedResultGio * 1000.0 + 1.0) / 1000.0;
			}
			
			double hoaDonTheoNgay = ((double) this.dsTKHD.size() / (24*soNgayCuaThangNam)) * 0.1d;
			double roundedResultNgay = Math.round(hoaDonTheoNgay * 1000.0) / 1000.0;
			if (roundedResultNgay * 10000 % 10 >= 5) {
				roundedResultNgay = Math.round(roundedResultNgay * 1000.0 + 1.0) / 1000.0;
			}
			modeltbTK.addRow(new Object[] {"Số hóa đơn / giờ", roundedResultGio});
			modeltbTK.addRow(new Object[] {"Số hóa đơn / ngày", roundedResultNgay});
			modeltbTK.addRow(new Object[] {"Doanh thu / hóa đơn", formatCurrency(tongDoanhThu/this.dsTKHD.size())});
			
		} else if (cmbLoai.getSelectedItem().toString().equals(listLoai[0][3])) {
			String namTu = cmbNam.getSelectedItem().toString();
			int soNgayCuaNam = YearMonth.of(mapYear.get(namTu), 1).lengthOfYear();
			modeltbTK.addRow(new Object[] {"Doanh thu / giờ", formatCurrency(tongDoanhThu/(24*soNgayCuaNam))});
			modeltbTK.addRow(new Object[] {"Doanh thu / ngày", formatCurrency(tongDoanhThu/soNgayCuaNam)});
			modeltbTK.addRow(new Object[] {"Doanh thu / tháng", formatCurrency(tongDoanhThu/(soNgayCuaNam/12))});
			modeltbTK.addRow(new Object[] {"Tổng số hóa đơn", this.dsTKHD.size()});
			double hoaDonTheoGio = ((double) this.dsTKHD.size() / (24*soNgayCuaNam)) * 0.1d;
			double roundedResultGio = Math.round(hoaDonTheoGio * 1000.0) / 1000.0;
			if (roundedResultGio * 10000 % 10 >= 5) {
				roundedResultGio = Math.round(roundedResultGio * 1000.0 + 1.0) / 1000.0;
			}
			
			double hoaDonTheoNgay = ((double) this.dsTKHD.size() / soNgayCuaNam) * 0.1d;
			double roundedResultNgay = Math.round(hoaDonTheoNgay * 1000.0) / 1000.0;
			if (roundedResultNgay * 10000 % 10 >= 5) {
				roundedResultNgay = Math.round(roundedResultNgay * 1000.0 + 1.0) / 1000.0;
			}
			
			double hoaDonTheoThang = ((double) this.dsTKHD.size() / (soNgayCuaNam/12)) * 0.1d;
			double roundedResultThang = Math.round(hoaDonTheoThang * 1000.0) / 1000.0;
			if (roundedResultThang * 10000 % 10 >= 5) {
				roundedResultThang = Math.round(roundedResultThang * 1000.0 + 1.0) / 1000.0;
			}
			modeltbTK.addRow(new Object[] {"Số hóa đơn / giờ", roundedResultGio});
			modeltbTK.addRow(new Object[] {"Số hóa đơn / ngày", roundedResultNgay});
			modeltbTK.addRow(new Object[] {"Số hóa đơn / tháng", roundedResultThang});
			modeltbTK.addRow(new Object[] {"Doanh thu / hóa đơn", formatCurrency(tongDoanhThu/this.dsTKHD.size())});
			
		}
	}

	private void docDuLieuVaoBangChiTiet() {
		modelTKChiTiet.setRowCount(0);
		HoaDon hd;
		for (int i = 0; i < this.dsTKHD.size(); i++) {
			hd = this.dsTKHD.get(i).getHoaDon();
			modelTKChiTiet.addRow(new Object[] {formatCurrency(hd.getTongThanhTienPhaiTra()),hd.getMaHoaDon(), hd.getKhachHang().getMaKhachHang(), hd.getNhanVien().getMaNhanVien(), hd.getNgayLapHoaDon()});
		}
	}
        
}
