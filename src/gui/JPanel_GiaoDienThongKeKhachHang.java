package gui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;

import java.awt.Label;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import javax.swing.table.TableRowSorter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import entity.KhachHang;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;


public class JPanel_GiaoDienThongKeKhachHang extends JPanel implements ActionListener {
	private final int fontSize = 12;

	private Font fontPlain, fontBold;
	private JTable tblTK, tblTKChiTiet;
	private DefaultTableModel modeltbTK, modelTKChiTiet;
	private JDatePickerImpl datePickerTu, datePickerDen;
	private UtilDateModel modelDenNgay, modelTuNgay;
	private JPanel pnlInfor, pnlDetail, pnlTable, pnlGraph, pnlSelect, pnlPie;
	private KhachHang_DAO kh_dao;
	private HoaDon_DAO hd_dao;
	private JButton btnThongKe, btnXuatHoaDon;
	private JLabel lblBot, lblTop, lblLoai, lblTieuChi;
	private JComboBox<String> cmbLoai, cmbThangTu, cmbThangDen, cmbNamTu, cmbNamDen;
	private JComboBox<Object> cmbTieuChi;
	private ArrayList<KhachHang_DAO.KhachHangThongKe>[] dsTKKH;
	private Date resultsTKDate[];
	private String resultsTKMonth[], resultsTKYear[];
	private Map<String, Integer> mapYear;
	private Map<String, ThangNam> mapMonth;
	private final String listTieuChi[][] = {
			{ "Hóa đơn", "Tất cả hóa đơn", "Khách hàng cũ", "Khách hàng mới", "Khách hàng quen",
					"TOP 10 nhiều hóa đơn nhất", "TOP 10 ít hóa đơn nhất" }, };
	private final String listTitle[][] = { { "Tổng số hóa đơn", "Số hóa đơn" },
			{ "Tổng thời gian sử dụng", "Thời gian sử dụng" }, { "Tổng số lượt sử dụng", "Số lượt sử dụng" } };
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
	public JPanel_GiaoDienThongKeKhachHang() {
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
		LineChartTest lineChart = new LineChartTest();
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

		lblBot = new JLabel("Đến ngày");
		lblBot.setBounds(27, 48, 65, 22);
		pnlTable.add(lblBot);
		lblBot.setFont(fontBold);
		modelDenNgay = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(modelDenNgay, p);
		// Don't know about the formatter, but there it is...
		datePickerDen = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePickerDen.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
		datePickerDen.getJFormattedTextField().setFont(fontPlain);
		SpringLayout springLayout = (SpringLayout) datePickerDen.getLayout();
		springLayout.putConstraint(SpringLayout.WEST, datePickerDen.getJFormattedTextField(), 0, SpringLayout.WEST,
				datePickerDen);
		datePickerDen.setBounds(100, 48, 119, 22);
//		modelDenNgay.addChangeListener(myChangeListenerDatePicker);
		pnlTable.add(datePickerDen);

		modelTuNgay = new UtilDateModel();
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		JDatePanelImpl datePanel1 = new JDatePanelImpl(modelTuNgay, p1);
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
		btnThongKe.setBounds(445, 47, 112, 23);
		btnThongKe.addActionListener(this);
		pnlTable.add(btnThongKe);

		btnXuatHoaDon = new JButton("In báo cáo");
		btnXuatHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXuatHoaDon.setFont(fontBold);
		btnXuatHoaDon.setBounds(445, 20, 112, 23);
		pnlTable.add(btnXuatHoaDon);

		pnlPie = new JPanel();
		pnlPie.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlPie.setBounds(27, 85, 530, 151);
		pnlTable.add(pnlPie);
		pnlPie.setLayout(null);
		lblTop = new JLabel("Từ ngày");
		lblTop.setFont(fontBold);
		lblTop.setBounds(27, 20, 65, 22);
		pnlTable.add(lblTop);

		lblLoai = new JLabel("Loại");
		lblLoai.setFont(fontBold);
		lblLoai.setBounds(234, 20, 51, 22);
		pnlTable.add(lblLoai);

		cmbLoai = new JComboBox<String>();
		cmbLoai.addItem(listLoai[0][1]);
		cmbLoai.addItem(listLoai[0][2]);
		cmbLoai.addItem(listLoai[0][3]);
		cmbLoai.setBounds(298, 21, 137, 21);
		pnlTable.add(cmbLoai);

		hd_dao = new HoaDon_DAO();

		cmbThangTu = new JComboBox<String>();
		cmbThangDen = new JComboBox<String>();
		cmbNamTu = new JComboBox<String>();
		cmbNamDen = new JComboBox<String>();
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
					cmbThangTu.addItem(thangTitle + thangNam);
				}
				cmbThangDen.addItem(thangTitle + thangNam);
			}
		}
		cmbThangDen.setBounds(100, 48, 119, 22);
		cmbThangTu.setBounds(100, 20, 119, 22);

		for (int nam : hd_dao.getAllNamLapHD()) {
			String namTitle = "Năm ";
			if (nam != 0) {
				mapYear.put(namTitle + nam, nam);

				cmbNamTu.addItem(namTitle + nam);

				cmbNamDen.addItem(namTitle + nam);
			}
		}
		cmbNamTu.setBounds(100, 20, 119, 22);
		cmbNamDen.setBounds(100, 48, 119, 22);

		cmbLoai.setFont(fontPlain);
		cmbThangTu.setFont(fontPlain);
		cmbThangDen.setFont(fontPlain);
		cmbNamTu.setFont(fontPlain);
		cmbNamDen.setFont(fontPlain);

		pnlTable.add(cmbThangTu);
		pnlTable.add(cmbThangDen);
		pnlTable.add(cmbNamTu);
		pnlTable.add(cmbNamDen);

		lblTieuChi = new JLabel("Tiêu chí");
		lblTieuChi.setFont(fontBold);
		lblTieuChi.setBounds(233, 48, 57, 22);
		pnlTable.add(lblTieuChi);
		taoBang();
		taoBangChiTiet();
		createAndShowTieuChi();
		comboLoaiCheck();
		addEventDepend();

	}

	private void addEventDepend() {
		modelTuNgay.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// Duyệt qua các phần tử ở dòng thứ 2 và 3
				
                                for (int j = 0; j < listTieuChi[0].length; j++) {
                                        String item = listTieuChi[0][j];
                                        if (cmbTieuChi.getSelectedItem().toString().equals(item)) {
                                                if ("value".equals(evt.getPropertyName())) {
                                                        // Ngày đã chọn trên datePicker1
                                                        Date selectedDate = modelTuNgay.getValue();
                                                        System.out.println("Selected date on datePicker1: " + selectedDate);

                                                        // Cập nhật ngày đã chọn lên datePicker2 và enable datePicker2
                                                        modelDenNgay.setValue(selectedDate);
                                                        break;
                                                }
                                        }
                                }
				

			}
		});

		cmbThangTu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Duyệt qua các phần tử ở dòng thứ 2 và 3
				
					for (int j = 0; j < listTieuChi[0].length; j++) {
						String item = listTieuChi[0][j];
						if (cmbTieuChi.getSelectedItem().toString().equals(item)) {
							cmbThangDen.setSelectedItem(cmbThangTu.getSelectedItem());
							break;
						}
					}
				
				cmbThangDen.repaint();

			}
		});

		cmbNamTu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Duyệt qua các phần tử ở dòng thứ 2 và 3
				
					for (int j = 0; j < listTieuChi[0].length; j++) {
						String item = listTieuChi[0][j];
						if (cmbTieuChi.getSelectedItem().toString().equals(item)) {
							cmbNamDen.setSelectedItem(cmbNamTu.getSelectedItem());
							break;
						}
					}
				
				cmbNamDen.repaint();

			}
		});
	}

	private void createAndShowTieuChi() {
		String tieuChi[] = { listTieuChi[0][0] };
		cmbTieuChi = new JComboBox<>(tieuChi);
		cmbTieuChi.setFont(fontPlain);
		cmbTieuChi.setBounds(298, 49, 137, 21);
		JPopupMenu popupMenu = new JPopupMenu();

		// Đặt invoker cho popupMenu
		popupMenu.setInvoker(cmbTieuChi);
		cmbTieuChi.setComponentPopupMenu(popupMenu);

		ComboBoxModel<Object> model = cmbTieuChi.getModel();
		model.setSelectedItem(listTieuChi[0][1]);
		Map<String, List<String>> tieuChiConList = new HashMap<>();
		for (int i = 0; i < this.listTieuChi.length; i++) {
			String temp[] = new String[this.listTieuChi[i].length - 1];
			for (int j = 1; j < this.listTieuChi[i].length; j++) {
				temp[j - 1] = this.listTieuChi[i][j];
			}
			tieuChiConList.put(listTieuChi[i][0], Arrays.asList(temp));
		}

		cmbTieuChi.addActionListener(e -> {
			TableColumn column = tblTK.getColumnModel().getColumn(2);
			TableColumn columnChangeTitle = tblTK.getColumnModel().getColumn(0);
			TableColumn columnChangeTitleDetail = tblTKChiTiet.getColumnModel().getColumn(0);
			String loaiDaChon = cmbLoai.getSelectedItem().toString();
			modelTKChiTiet.setRowCount(0);
			modeltbTK.setRowCount(0);
			if (cmbTieuChi.getSelectedItem().toString().equals(listTieuChi[0][0])) {
				column.setHeaderValue(listTitle[0][0]);
				columnChangeTitleDetail.setHeaderValue(listTitle[0][1]);
				columnChangeTitle.setHeaderValue(listLoai[0][0]);
				if (loaiDaChon.equals(listLoai[0][1])) {
					datePickerDen.getComponent(1).setEnabled(true);
//					modelDenNgay.setValue(null);
				} else if (loaiDaChon.equals(listLoai[0][2])) {
					cmbThangDen.setEnabled(true);
				} else if (loaiDaChon.equals(listLoai[0][3])) {
					cmbNamDen.setEnabled(true);
				}
			} 
			tblTKChiTiet.getTableHeader().repaint();
			tblTK.getTableHeader().repaint();
			showPopupMenu(cmbTieuChi, popupMenu, tieuChiConList, pnlTable);

		});
		cmbTieuChi.setFont(fontPlain);
		pnlTable.add(cmbTieuChi);
	}

	private static boolean allComponentsDisabled(Container container) {
		for (Component component : container.getComponents()) {
			if (component.isEnabled()) {
				return false;
			}
			if (component instanceof Container) {
				if (!allComponentsDisabled((Container) component)) {
					return false;
				}
			}
		}
		return true;
	}

	private static void showPopupMenu(JComboBox comboTieuChi, JPopupMenu popupMenu,
			Map<String, List<String>> subItemsMap, JPanel panel) {

		String selectedMainItem = (String) comboTieuChi.getSelectedItem();

		popupMenu.removeAll();
		List<String> subItems = subItemsMap.get(selectedMainItem);
		if (subItems != null) {
			for (String subItem : subItems) {
				JMenuItem menuItem = new JMenuItem(subItem);
				menuItem.addActionListener(e -> {
					DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboTieuChi.getModel();
					model.setSelectedItem(subItem);
				});
				popupMenu.add(menuItem);
			}

			// Hiển thị PopupMenu ở đúng vị trí
			popupMenu.show(comboTieuChi, 0, comboTieuChi.getHeight());
		}

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
		kh_dao = new KhachHang_DAO();
		modeltbTK = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modeltbTK.addColumn(listLoai[0][0]);
		modeltbTK.addColumn("Số khách");
		modeltbTK.addColumn(listTitle[0][0]);
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
					// Canh giữa nội dung trong ô
					label.setHorizontalAlignment(SwingConstants.CENTER);

					// Đặt font cho nội dung trong ô
					label.setFont(fontPlain);
					return label;
				}
			});
		}

		// Đặt font cho dòng header
		tblTK.getTableHeader().setFont(this.fontBold);

		JScrollPane sp = new JScrollPane(tblTK, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(4, 3, 522, 145);
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
				modelTKChiTiet.setRowCount(0);
				int row = tblTK.getSelectedRow();
				docDuLieuVaoBangChiTiet(row);
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
				cmbThangTu.setVisible(false);
				cmbThangDen.setVisible(false);

				// Xóa năm
//				panelTable.remove(comboNamTu);
				cmbNamTu.setVisible(false);
				cmbNamDen.setVisible(false);

				// Thêm ngày
				datePickerTu.setVisible(true);
				datePickerDen.setVisible(true);
				lblTop.setText("Từ ngày");
				lblBot.setText("Đến ngày");

				datePickerDen.getComponent(1).setEnabled(true);
				
                                for (int j = 0; j < listTieuChi[0].length; j++) {
                                        String item = listTieuChi[0][j];
                                        if (cmbTieuChi.getSelectedItem().toString().equals(item)) {
                                                datePickerDen.getComponent(1).setEnabled(false);
                                                break;
                                        }
                                }
				

				// Render lại
				repaint();
			}


//			
			if (cmbLoai.getSelectedItem().toString().equals(listLoai[0][2])) {
				// Xóa ngày
				datePickerTu.setVisible(false);
				datePickerDen.setVisible(false);

				// Xóa năm
				cmbNamTu.setVisible(false);
				cmbNamDen.setVisible(false);

				// Thêm tháng
				cmbThangTu.setVisible(true);
				cmbThangDen.setVisible(true);
				lblTop.setText("Từ tháng");
				lblBot.setText("Đến tháng");

				cmbThangDen.setEnabled(true);
				
                                for (int j = 0; j < listTieuChi[0].length; j++) {
                                        String item = listTieuChi[0][j];
                                        if (cmbTieuChi.getSelectedItem().toString().equals(item)) {
                                                cmbThangDen.setEnabled(false);
                                                break;
                                        }
                                }
				

				// Render lại
				repaint();
			}

			if (cmbLoai.getSelectedItem().toString().equals(listLoai[0][3])) {
				// Xóa ngày
				datePickerTu.setVisible(false);
				datePickerDen.setVisible(false);

				// Xóa tháng
				cmbThangTu.setVisible(false);
				cmbThangDen.setVisible(false);

				// Thêm năm
				cmbNamTu.setVisible(true);
				cmbNamDen.setVisible(true);
				lblTop.setText("Từ năm");
				lblBot.setText("Đến năm");

				cmbNamDen.setEnabled(true);
				
					for (int j = 0; j < listTieuChi[0].length; j++) {
						String item = listTieuChi[0][j];
						if (cmbTieuChi.getSelectedItem().toString().equals(item)) {
							cmbNamDen.setEnabled(false);
							break;
						}
					
				}

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
		modelTKChiTiet.addColumn("Số hóa đơn"); // 0
		modelTKChiTiet.addColumn("Mã KH"); // 1
		modelTKChiTiet.addColumn("Họ và tên"); // 2
		modelTKChiTiet.addColumn("Giới tính"); // 3
		modelTKChiTiet.addColumn("Ngày sinh"); // 4
		modelTKChiTiet.addColumn("CCCD"); // 5

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

		tblTKChiTiet.getColumnModel().getColumn(0).setPreferredWidth(120); // Số hóa đơn
		tblTKChiTiet.getColumnModel().getColumn(1).setPreferredWidth(60); // Mã
		tblTKChiTiet.getColumnModel().getColumn(2).setPreferredWidth(150); // tên
		tblTKChiTiet.getColumnModel().getColumn(3).setPreferredWidth(80); // Giới tính
		tblTKChiTiet.getColumnModel().getColumn(4).setPreferredWidth(100); // Ngày sinh
		tblTKChiTiet.getColumnModel().getColumn(5).setPreferredWidth(100); // CCCD

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

	private void thongKeTongKH(String loai) {
		if (loai.equals(listLoai[0][1])) {
			int countSize = 0;
			Date tuNgayDate = (Date) datePickerTu.getModel().getValue();
			Date denNgayDate = (Date) datePickerDen.getModel().getValue();
			Instant instantTuNgay = tuNgayDate.toInstant();
			LocalDate localDateTuNgay = instantTuNgay.atZone(ZoneId.systemDefault()).toLocalDate();
			Instant instantDenNgay = denNgayDate.toInstant();
			Date date;
			LocalDate localDateDenNgay = instantDenNgay.atZone(ZoneId.systemDefault()).toLocalDate();

			for (LocalDate lc = localDateTuNgay; !lc.isAfter(localDateDenNgay); lc = lc.plusDays(1))
				countSize++;
			dsTKKH = new ArrayList[countSize];
			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			this.resultsTKDate = new Date[countSize];

			int q = 0;
			for (LocalDate lc = localDateTuNgay; !lc.isAfter(localDateDenNgay); lc = lc.plusDays(1)) {
				date = Date.from(lc.atStartOfDay(ZoneId.systemDefault()).toInstant());
				resultsTKDate[q] = date;
				this.dsTKKH[q] = kh_dao.thongKeKhachTheoNgay(date);
				q++;
			}
			docDuLieuVaoTableTongQuat();
		} else if (loai.equals(listLoai[0][2])) {
			int countSize = 0;
			String thangNamTu = cmbThangTu.getSelectedItem().toString();
			String thangNamDen = cmbThangDen.getSelectedItem().toString();
			for (int i = mapMonth.get(thangNamTu).nam; i <= mapMonth.get(thangNamDen).nam; i++)
				for (int j = mapMonth.get(thangNamTu).thang; j <= mapMonth.get(thangNamDen).thang; j++)
					countSize++;

			dsTKKH = new ArrayList[countSize];
			resultsTKMonth = new String[countSize];

			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			int q = 0;
			for (int i = mapMonth.get(thangNamTu).nam; i <= mapMonth.get(thangNamDen).nam; i++) {
				for (int j = mapMonth.get(thangNamTu).thang; j <= mapMonth.get(thangNamDen).thang; j++) {
					this.dsTKKH[q] = kh_dao.thongKeKhachTheoThang(j, i);
					this.resultsTKMonth[q] = getKeyThangNam(mapMonth, new ThangNam(j, i));
					q++;
				}
			}
			docDuLieuVaoTableTongQuat();
		} else if (loai.equals(listLoai[0][3])) {
			int countSize = 0;
			String namTu = cmbNamTu.getSelectedItem().toString();
			String namDen = cmbNamDen.getSelectedItem().toString();
			for (int i = mapYear.get(namTu); i <= mapYear.get(namDen); i++)
				countSize++;

			dsTKKH = new ArrayList[countSize];
			resultsTKYear = new String[countSize];

			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			int q = 0; // bị lỗi
			for (int i = mapYear.get(namTu); i <= mapYear.get(namDen); i++) {

				this.dsTKKH[q] = kh_dao.thongKeKhachTheoNam(i);
				this.resultsTKYear[q] = getKeyNam(mapYear, i);
				q++;
			}
			docDuLieuVaoTableTongQuat();
		}
	}

	private void thongKeKHMoi(String loai) {
		if (loai.equals(listLoai[0][1])) {
			int countSize = 0;
			Date tuNgayDate = (Date) datePickerTu.getModel().getValue();
			Date denNgayDate = (Date) datePickerDen.getModel().getValue();
			Instant instantTuNgay = tuNgayDate.toInstant();
			LocalDate localDateTuNgay = instantTuNgay.atZone(ZoneId.systemDefault()).toLocalDate();
			Instant instantDenNgay = denNgayDate.toInstant();
			Date date;
			LocalDate localDateDenNgay = instantDenNgay.atZone(ZoneId.systemDefault()).toLocalDate();

			for (LocalDate lc = localDateTuNgay; !lc.isAfter(localDateDenNgay); lc = lc.plusDays(1))
				countSize++;
			dsTKKH = new ArrayList[countSize];
			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			this.resultsTKDate = new Date[countSize];
			int q = 0;
			for (LocalDate lc = localDateTuNgay; !lc.isAfter(localDateDenNgay); lc = lc.plusDays(1)) {
				date = Date.from(lc.atStartOfDay(ZoneId.systemDefault()).toInstant());
				resultsTKDate[q] = date;
				this.dsTKKH[q] = kh_dao.thongKeKHMoiTheoNgay(date);
				q++;
			}
			docDuLieuVaoTableTongQuat();
		} else if (loai.equals(listLoai[0][2])) {
			int countSize = 0;
			String thangNamTu = cmbThangTu.getSelectedItem().toString();
			String thangNamDen = cmbThangDen.getSelectedItem().toString();
			for (int i = mapMonth.get(thangNamTu).nam; i <= mapMonth.get(thangNamDen).nam; i++)
				for (int j = mapMonth.get(thangNamTu).thang; j <= mapMonth.get(thangNamDen).thang; j++)
					countSize++;

			dsTKKH = new ArrayList[countSize];
			resultsTKMonth = new String[countSize];

			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			int q = 0;
			for (int i = mapMonth.get(thangNamTu).nam; i <= mapMonth.get(thangNamDen).nam; i++) {
				for (int j = mapMonth.get(thangNamTu).thang; j <= mapMonth.get(thangNamDen).thang; j++) {
					this.dsTKKH[q] = kh_dao.thongKeKHMoiTheoThang(j, i);
					this.resultsTKMonth[q] = getKeyThangNam(mapMonth, new ThangNam(j, i));
					q++;
				}
			}
			docDuLieuVaoTableTongQuat();
		} else if (loai.equals(listLoai[0][3])) {
			int countSize = 0;
			String namTu = cmbNamTu.getSelectedItem().toString();
			String namDen = cmbNamDen.getSelectedItem().toString();
			for (int i = mapYear.get(namTu); i <= mapYear.get(namDen); i++)
				countSize++;

			dsTKKH = new ArrayList[countSize];
			resultsTKYear = new String[countSize];

			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			int q = 0; // bị lỗi
			for (int i = mapYear.get(namTu); i <= mapYear.get(namDen); i++) {

				this.dsTKKH[q] = kh_dao.thongKeKHMoiTheoNam(i);
				this.resultsTKYear[q] = getKeyNam(mapYear, i);
				q++;
			}
			docDuLieuVaoTableTongQuat();
		}
	}

	private void thongKeKHCu(String loai) {
		if (loai.equals(listLoai[0][1])) {
			int countSize = 0;
			Date tuNgayDate = (Date) datePickerTu.getModel().getValue();
			Date denNgayDate = (Date) datePickerDen.getModel().getValue();
			Instant instantTuNgay = tuNgayDate.toInstant();
			LocalDate localDateTuNgay = instantTuNgay.atZone(ZoneId.systemDefault()).toLocalDate();
			Instant instantDenNgay = denNgayDate.toInstant();
			Date date;
			LocalDate localDateDenNgay = instantDenNgay.atZone(ZoneId.systemDefault()).toLocalDate();

			for (LocalDate lc = localDateTuNgay; !lc.isAfter(localDateDenNgay); lc = lc.plusDays(1))
				countSize++;
			dsTKKH = new ArrayList[countSize];
			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			this.resultsTKDate = new Date[countSize];

			int q = 0;
			for (LocalDate lc = localDateTuNgay; !lc.isAfter(localDateDenNgay); lc = lc.plusDays(1)) {
				date = Date.from(lc.atStartOfDay(ZoneId.systemDefault()).toInstant());
				resultsTKDate[q] = date;
				this.dsTKKH[q] = kh_dao.thongKeKHCuTheoNgay(date);
				q++;
			}
			docDuLieuVaoTableTongQuat();
		} else if (loai.equals(listLoai[0][2])) {
			int countSize = 0;
			String thangNamTu = cmbThangTu.getSelectedItem().toString();
			String thangNamDen = cmbThangDen.getSelectedItem().toString();
			for (int i = mapMonth.get(thangNamTu).nam; i <= mapMonth.get(thangNamDen).nam; i++)
				for (int j = mapMonth.get(thangNamTu).thang; j <= mapMonth.get(thangNamDen).thang; j++)
					countSize++;

			dsTKKH = new ArrayList[countSize];
			resultsTKMonth = new String[countSize];

			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			int q = 0;
			for (int i = mapMonth.get(thangNamTu).nam; i <= mapMonth.get(thangNamDen).nam; i++) {
				for (int j = mapMonth.get(thangNamTu).thang; j <= mapMonth.get(thangNamDen).thang; j++) {
					this.dsTKKH[q] = kh_dao.thongKeKHCuTheoThang(j, i);
					this.resultsTKMonth[q] = getKeyThangNam(mapMonth, new ThangNam(j, i));
					q++;
				}
			}
			docDuLieuVaoTableTongQuat();
		} else if (loai.equals(listLoai[0][3])) {
			int countSize = 0;
			String namTu = cmbNamTu.getSelectedItem().toString();
			String namDen = cmbNamDen.getSelectedItem().toString();
			for (int i = mapYear.get(namTu); i <= mapYear.get(namDen); i++)
				countSize++;

			dsTKKH = new ArrayList[countSize];
			resultsTKYear = new String[countSize];

			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			int q = 0; // bị lỗi
			for (int i = mapYear.get(namTu); i <= mapYear.get(namDen); i++) {

				this.dsTKKH[q] = kh_dao.thongKeKHCuTheoNam(i);
				this.resultsTKYear[q] = getKeyNam(mapYear, i);
				q++;
			}
			docDuLieuVaoTableTongQuat();
		}
	}

	private void thongKeKHQuen(String loai) {
		if (loai.equals(listLoai[0][1])) {
			int countSize = 0;
			Date tuNgayDate = (Date) datePickerTu.getModel().getValue();
			Date denNgayDate = (Date) datePickerDen.getModel().getValue();
			Instant instantTuNgay = tuNgayDate.toInstant();
			LocalDate localDateTuNgay = instantTuNgay.atZone(ZoneId.systemDefault()).toLocalDate();
			Instant instantDenNgay = denNgayDate.toInstant();
			Date date;
			LocalDate localDateDenNgay = instantDenNgay.atZone(ZoneId.systemDefault()).toLocalDate();

			for (LocalDate lc = localDateTuNgay; !lc.isAfter(localDateDenNgay); lc = lc.plusDays(1))
				countSize++;
			dsTKKH = new ArrayList[countSize];
			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			this.resultsTKDate = new Date[countSize];

			int q = 0;
			for (LocalDate lc = localDateTuNgay; !lc.isAfter(localDateDenNgay); lc = lc.plusDays(1)) {
				date = Date.from(lc.atStartOfDay(ZoneId.systemDefault()).toInstant());
				resultsTKDate[q] = date;
				this.dsTKKH[q] = kh_dao.thongKeKHQuenTheoNgay(date);
				q++;
			}
			docDuLieuVaoTableTongQuat();
		} else if (loai.equals(listLoai[0][2])) {
			int countSize = 0;
			String thangNamTu = cmbThangTu.getSelectedItem().toString();
			String thangNamDen = cmbThangDen.getSelectedItem().toString();
			for (int i = mapMonth.get(thangNamTu).nam; i <= mapMonth.get(thangNamDen).nam; i++)
				for (int j = mapMonth.get(thangNamTu).thang; j <= mapMonth.get(thangNamDen).thang; j++)
					countSize++;

			dsTKKH = new ArrayList[countSize];
			resultsTKMonth = new String[countSize];

			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			int q = 0;
			for (int i = mapMonth.get(thangNamTu).nam; i <= mapMonth.get(thangNamDen).nam; i++) {
				for (int j = mapMonth.get(thangNamTu).thang; j <= mapMonth.get(thangNamDen).thang; j++) {
					this.dsTKKH[q] = kh_dao.thongKeKHQuenTheoThang(j, i);
					this.resultsTKMonth[q] = getKeyThangNam(mapMonth, new ThangNam(j, i));
					q++;
				}
			}
			docDuLieuVaoTableTongQuat();
		} else if (loai.equals(listLoai[0][3])) {
			int countSize = 0;
			String namTu = cmbNamTu.getSelectedItem().toString();
			String namDen = cmbNamDen.getSelectedItem().toString();
			for (int i = mapYear.get(namTu); i <= mapYear.get(namDen); i++)
				countSize++;

			dsTKKH = new ArrayList[countSize];
			resultsTKYear = new String[countSize];

			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			int q = 0; // bị lỗi
			for (int i = mapYear.get(namTu); i <= mapYear.get(namDen); i++) {

				this.dsTKKH[q] = kh_dao.thongKeKHQuenTheoNam(i);
				this.resultsTKYear[q] = getKeyNam(mapYear, i);
				q++;
			}
			docDuLieuVaoTableTongQuat();
		}
	}

	private void thongKeKHCoHDNhieuNhat(String loai) {
		if (loai.equals(listLoai[0][1])) {
			int countSize = 0;
			Date tuNgayDate = (Date) datePickerTu.getModel().getValue();
			Date denNgayDate = (Date) datePickerDen.getModel().getValue();
			Instant instantTuNgay = tuNgayDate.toInstant();
			LocalDate localDateTuNgay = instantTuNgay.atZone(ZoneId.systemDefault()).toLocalDate();
			Instant instantDenNgay = denNgayDate.toInstant();
			Date date;
			LocalDate localDateDenNgay = instantDenNgay.atZone(ZoneId.systemDefault()).toLocalDate();

			for (LocalDate lc = localDateTuNgay; !lc.isAfter(localDateDenNgay); lc = lc.plusDays(1))
				countSize++;
			dsTKKH = new ArrayList[countSize];
			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			this.resultsTKDate = new Date[countSize];

			int q = 0;
			for (LocalDate lc = localDateTuNgay; !lc.isAfter(localDateDenNgay); lc = lc.plusDays(1)) {
				date = Date.from(lc.atStartOfDay(ZoneId.systemDefault()).toInstant());
				resultsTKDate[q] = date;
				this.dsTKKH[q] = kh_dao.thongKeKHCoHDNhieuNhatTheoNgay(date);
				q++;
			}
			docDuLieuVaoTableTongQuat();
		} else if (loai.equals(listLoai[0][2])) {
			int countSize = 0;
			String thangNamTu = cmbThangTu.getSelectedItem().toString();
			String thangNamDen = cmbThangDen.getSelectedItem().toString();
			for (int i = mapMonth.get(thangNamTu).nam; i <= mapMonth.get(thangNamDen).nam; i++)
				for (int j = mapMonth.get(thangNamTu).thang; j <= mapMonth.get(thangNamDen).thang; j++)
					countSize++;

			dsTKKH = new ArrayList[countSize];
			resultsTKMonth = new String[countSize];

			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			int q = 0;
			for (int i = mapMonth.get(thangNamTu).nam; i <= mapMonth.get(thangNamDen).nam; i++) {
				for (int j = mapMonth.get(thangNamTu).thang; j <= mapMonth.get(thangNamDen).thang; j++) {
					this.dsTKKH[q] = kh_dao.thongKeKHCoHDNhieuNhatTheoThang(j, i);
					this.resultsTKMonth[q] = getKeyThangNam(mapMonth, new ThangNam(j, i));
					q++;
				}
			}
			docDuLieuVaoTableTongQuat();
		} else if (loai.equals(listLoai[0][3])) {
			int countSize = 0;
			String namTu = cmbNamTu.getSelectedItem().toString();
			String namDen = cmbNamDen.getSelectedItem().toString();
			for (int i = mapYear.get(namTu); i <= mapYear.get(namDen); i++)
				countSize++;

			dsTKKH = new ArrayList[countSize];
			resultsTKYear = new String[countSize];

			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			int q = 0; // bị lỗi
			for (int i = mapYear.get(namTu); i <= mapYear.get(namDen); i++) {

				this.dsTKKH[q] = kh_dao.thongKeKHCoHDNhieuNhatTheoNam(i);
				this.resultsTKYear[q] = getKeyNam(mapYear, i);
				q++;
			}
			docDuLieuVaoTableTongQuat();
		}
	}

	private void thongKeKHCoHDItNhat(String loai) {
		if (loai.equals(listLoai[0][1])) {
			int countSize = 0;
			Date tuNgayDate = (Date) datePickerTu.getModel().getValue();
			Date denNgayDate = (Date) datePickerDen.getModel().getValue();
			Instant instantTuNgay = tuNgayDate.toInstant();
			LocalDate localDateTuNgay = instantTuNgay.atZone(ZoneId.systemDefault()).toLocalDate();
			Instant instantDenNgay = denNgayDate.toInstant();
			Date date;
			LocalDate localDateDenNgay = instantDenNgay.atZone(ZoneId.systemDefault()).toLocalDate();

			for (LocalDate lc = localDateTuNgay; !lc.isAfter(localDateDenNgay); lc = lc.plusDays(1))
				countSize++;
			dsTKKH = new ArrayList[countSize];
			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			this.resultsTKDate = new Date[countSize];

			int q = 0;
			for (LocalDate lc = localDateTuNgay; !lc.isAfter(localDateDenNgay); lc = lc.plusDays(1)) {
				date = Date.from(lc.atStartOfDay(ZoneId.systemDefault()).toInstant());
				resultsTKDate[q] = date;
				this.dsTKKH[q] = kh_dao.thongKeKHCoHDItNhatTheoNgay(date);
				q++;
			}
			docDuLieuVaoTableTongQuat();
		} else if (loai.equals(listLoai[0][2])) {
			int countSize = 0;
			String thangNamTu = cmbThangTu.getSelectedItem().toString();
			String thangNamDen = cmbThangDen.getSelectedItem().toString();
			for (int i = mapMonth.get(thangNamTu).nam; i <= mapMonth.get(thangNamDen).nam; i++)
				for (int j = mapMonth.get(thangNamTu).thang; j <= mapMonth.get(thangNamDen).thang; j++)
					countSize++;

			dsTKKH = new ArrayList[countSize];
			resultsTKMonth = new String[countSize];

			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			int q = 0;
			for (int i = mapMonth.get(thangNamTu).nam; i <= mapMonth.get(thangNamDen).nam; i++) {
				for (int j = mapMonth.get(thangNamTu).thang; j <= mapMonth.get(thangNamDen).thang; j++) {
					this.dsTKKH[q] = kh_dao.thongKeKHCoHDItNhatTheoThang(j, i);
					this.resultsTKMonth[q] = getKeyThangNam(mapMonth, new ThangNam(j, i));
					q++;
				}
			}
			docDuLieuVaoTableTongQuat();
		} else if (loai.equals(listLoai[0][3])) {
			int countSize = 0;
			String namTu = cmbNamTu.getSelectedItem().toString();
			String namDen = cmbNamDen.getSelectedItem().toString();
			for (int i = mapYear.get(namTu); i <= mapYear.get(namDen); i++)
				countSize++;

			dsTKKH = new ArrayList[countSize];
			resultsTKYear = new String[countSize];

			// Khởi tạo từng phần tử của mảng là một ArrayList
			for (int i = 0; i < dsTKKH.length; i++) {
				dsTKKH[i] = new ArrayList<>();
			}

			int q = 0; // bị lỗi
			for (int i = mapYear.get(namTu); i <= mapYear.get(namDen); i++) {

				this.dsTKKH[q] = kh_dao.thongKeKHCoHDItNhatTheoNam(i);
				this.resultsTKYear[q] = getKeyNam(mapYear, i);
				q++;
			}
			docDuLieuVaoTableTongQuat();
		}
	}


	public String getKeyThangNam(Map<String, ThangNam> map, ThangNam value) {
		for (Map.Entry<String, ThangNam> entry : map.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}

		return null;
	}

	public String getKeyNam(Map<String, Integer> map, int value) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue().equals(value)) {
				return entry.getKey();
			}
		}

		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnThongKe)) {
			if (validData()) {
				String loaiDaChon = cmbLoai.getSelectedItem().toString();
				String tieuChiDaChon = cmbTieuChi.getSelectedItem().toString();
				modeltbTK.setRowCount(0);
				// Hóa đơn
				if (tieuChiDaChon.equals(listTieuChi[0][1])) {
					thongKeTongKH(loaiDaChon);
				} else if (tieuChiDaChon.equals(listTieuChi[0][2])) {
					thongKeKHCu(loaiDaChon);
				} else if (tieuChiDaChon.equals(listTieuChi[0][3])) {
					thongKeKHMoi(loaiDaChon);
				} else if (tieuChiDaChon.equals(listTieuChi[0][4])) {
					thongKeKHQuen(loaiDaChon);
				} else if (tieuChiDaChon.equals(listTieuChi[0][5])) {
					thongKeKHCoHDNhieuNhat(loaiDaChon);
				} else if (tieuChiDaChon.equals(listTieuChi[0][6])) {
					thongKeKHCoHDItNhat(loaiDaChon);
				} 
			}

		}

	}

	private boolean validData() {
		String loai = cmbLoai.getSelectedItem().toString();
		String tieuChi = cmbTieuChi.getSelectedItem().toString();
		if (loai.equals(listLoai[0][1])) {
			String tuNgayBangChu = datePickerTu.getJFormattedTextField().getText();
			String denNgayBangChu = datePickerDen.getJFormattedTextField().getText();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			if (tuNgayBangChu.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn từ ngày");
				return false;
			} else if (LocalDate.parse(tuNgayBangChu, formatter).isAfter(LocalDate.now())) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn từ ngày bé hơn hoặc bằng ngày hiện hành");
				return false;
			}

			if (!allComponentsDisabled(datePickerDen)) {
				if (denNgayBangChu.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn đến ngày");
					return false;
				} else if (LocalDate.parse(denNgayBangChu, formatter).isAfter(LocalDate.now())) {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn đến ngày bé hơn hoặc bằng ngày hiện hành");
					return false;
				} else if (LocalDate.parse(denNgayBangChu, formatter)
						.isBefore(LocalDate.parse(tuNgayBangChu, formatter))) {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn đến ngày lớn hơn " + tuNgayBangChu);
					return false;
				}
			}

		} else if (loai.equals(listLoai[0][2])) {
			String thangTu = cmbThangTu.getSelectedItem().toString();
			String thangDen = cmbThangDen.getSelectedItem().toString();
			ThangNam thangTuObj = mapMonth.get(thangTu);
			ThangNam thangDenObj = mapMonth.get(thangDen);

			if (cmbThangDen.isEnabled()) {
				if (thangDenObj.nam < thangTuObj.nam) {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn tháng/năm lớn hơn hoặc bằng " + thangTu);
					return false;
				} else if (thangDenObj.thang < thangTuObj.thang) {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn tháng/năm lớn hơn hoặc bằng " + thangTu);
					return false;
				}
			}

		} else if (loai.equals(listLoai[0][3])) {
			String namTu = cmbNamTu.getSelectedItem().toString();
			String namDen = cmbNamDen.getSelectedItem().toString();
			int namTuNum = mapYear.get(namTu);
			int namDenNum = mapYear.get(namDen);

			if (cmbNamDen.isEnabled()) {
				if (namDenNum < namTuNum) {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn tháng/năm lớn hơn hoặc bằng " + namTuNum);
					return false;
				}
			}

		}

		if (tieuChi.equals("Hóa đơn") || tieuChi.equals("Loại phòng") || tieuChi.equals("Dịch vụ")) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn chi tiết tiêu chí " + tieuChi);
			return false;
		}

		return true;

	}

	private void docDuLieuVaoTableTongQuat() {
		String loai = cmbLoai.getSelectedItem().toString();
		String tieuChi = cmbTieuChi.getSelectedItem().toString();
		int tongTieuChi[] = new int[this.dsTKKH.length];
		for (int i = 0; i < this.dsTKKH.length; i++) {
			for (int j = 0; j < this.dsTKKH[i].size(); j++) {
				tongTieuChi[i] += this.dsTKKH[i].get(j).getTongTheoTieuChi();
			}
		}
		if (loai.equals(listLoai[0][1])) {
			// Hóa đơn
			for (int j = 1; j < listTieuChi[0].length; j++) {
				if (tieuChi == listTieuChi[0][j]) {
					for (int i = 0; i < this.dsTKKH.length; i++) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
						modeltbTK.addRow(new Object[] { dateFormat.format(this.resultsTKDate[i]), this.dsTKKH[i].size(),
								tongTieuChi[i] });
					}
					return;
				}
			}



		} else if (loai.equals(listLoai[0][2])) {
			// Hóa đơn
			for (int j = 1; j < listTieuChi[0].length; j++) {
				if (tieuChi == listTieuChi[0][j]) {
					for (int i = 0; i < this.dsTKKH.length; i++) {
						modeltbTK.addRow(new Object[] { resultsTKMonth[i], this.dsTKKH[i].size(), tongTieuChi[i] });
					}
					return;
				}
			}


		} else if (loai.equals(listLoai[0][3])) {
			// Hóa đơn
			for (int j = 1; j < listTieuChi[0].length; j++) {
				if (tieuChi == listTieuChi[0][j]) {
					for (int i = 0; i < this.dsTKKH.length; i++) {
						modeltbTK.addRow(new Object[] { resultsTKYear[i], this.dsTKKH[i].size(), tongTieuChi[i] });
					}
					return;
				}
			}
			
		}

	}

	private void docDuLieuVaoBangChiTiet(int selectedRow) {
		String tieuChi = cmbTieuChi.getSelectedItem().toString();
		KhachHang kh;

		// Hóa đơn
		for (int j = 1; j < listTieuChi[0].length; j++) {
			if (tieuChi == listTieuChi[0][j]) {
				for (int i = 0; i < this.dsTKKH[selectedRow].size(); i++) {
					kh = this.dsTKKH[selectedRow].get(i).getKhachHang();
//					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
//                                        dateFormat.format(kh.getNgaySinh())
					modelTKChiTiet.addRow(new Object[] { this.dsTKKH[selectedRow].get(i).getTongTheoTieuChi(),
							kh.getMaKhachHang(), kh.getHoTenKhachHang(), kh.isGioiTinh() ? "Nam" : "Nữ",
							kh.getNgaySinh(), kh.getCCCD() });
				}
			}
		}
	}
}
