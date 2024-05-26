/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import dao.ChiTietHoaDon_DAO;
import dao.ChiTietPhieuDatPhong_DAO;
import dao.DoAnUong_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.KhuyenMai_DAO;
import dao.Phong_DAO;
import entity.ChiTietHoaDon;
import entity.DoAnUong;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M S I
 */
public class JPanel_thuePhong extends javax.swing.JPanel implements ActionListener {

    JPanel_QuanLyPhong Jqlp;
    boolean isSettingDate = false;
    private HoaDon_DAO hd_dao;
    private Phong_DAO p_dao;
    private DefaultTableModel modelDV;
    private DoAnUong_DAO dau_dao;
    private DefaultTableModel modelDV1;
    private DefaultTableModel modelDichVu;
    private ChiTietHoaDon cthd;
    javax.swing.ButtonGroup gr_ttheo = new javax.swing.ButtonGroup();
    javax.swing.ButtonGroup gr_dt = new javax.swing.ButtonGroup();
    private NhanVien nhanVien;
    private ChiTietHoaDon_DAO cthd_dao;
    private PhieuDatPhong khm;

    /**
     * Creates new form JPanel_thuePhong
     *
     * @param quanLyPhong
     * @param tenPhong
     * @param nv
     * @param kh
     * @param soNguoi
     * @param ngayTra
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public JPanel_thuePhong(JPanel_QuanLyPhong quanLyPhong, String tenPhong, NhanVien nv, KhachHang kh, int soNguoi, LocalDate ngayTra, PhieuDatPhong pdt) throws IOException, SQLException {
        initComponents();
        this.setSize(WIDTH, HEIGHT);
        nhanVien = nv;
        this.khm = pdt;
        this.Jqlp = quanLyPhong;
        loadThongTin(tenPhong, kh, soNguoi, ngayTra);
        txt_maDIchVu.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    timKiemDichVu();
                } catch (IOException ex) {
                    Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    timKiemDichVu();
                } catch (IOException ex) {
                    Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    timKiemDichVu();
                } catch (IOException ex) {
                    Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void timKiemDichVu() throws IOException {
        String keyword = txt_maDIchVu.getText();
        try {
            ArrayList<DoAnUong> list = dau_dao.getAllTableDoAnUong();
            modelDV.setRowCount(0); // Xóa toàn bộ dữ liệu trong bảng dịch vụ

            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
            ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");

            for (DoAnUong dau : list) {
                if (dau.getMaDoAnUong().contains(keyword) || dau.getTenDoAnUong().contains(keyword)) {
                    if (!dau.getMaDoAnUong().equals("XXX") && dau.getTrangThaiSuDung().getTentrangThaiSuDung() != 3 && dau.getSoLuong() != 0) {
                        modelDV.addRow(new Object[]{dau.getMaDoAnUong(), dau.getTenDoAnUong(), dau.getSoLuong(), currencyFormat.format(dau.getGiaBan())});
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadThongTin(String tenPhong, KhachHang kh, int soNguoi, LocalDate ngayTra) throws IOException, SQLException {
        HoaDon hd = new HoaDon();
        KhachHang_DAO kh_dao = new KhachHang_DAO();
        if (kh != null) {
            ArrayList<KhachHang> khachHangList = kh_dao.getKHTheoMaKhachHang(kh.getMaKhachHang());
            KhachHang kh0 = khachHangList.get(0);
            txt_SDT.setText(kh.getMaKhachHang());
            txt_SDT.setEditable(false);
            txt_HvT.setText(kh0.getHoTenKhachHang());
            txt_HvT.setEditable(false);
            txt_CCCD.setText(kh0.getCCCD());
            txt_CCCD.setEditable(false);
            if (kh0.isGioiTinh()) {
                rd_nam.setSelected(true);
            } else {
                rd_nu.setSelected(true);
            }
            txt_ngaySinh.setText(kh0.getNgaySinh().toString());
            txt_ngaySinh.setEditable(false);
        }
        p_dao = new Phong_DAO();
        Phong phong = p_dao.getPhongTheoTenPhong(tenPhong).getFirst();
        txt_loaiPhong.setText(phong.getLoaiPhong().toString());
        txt_tenPhong.setText(tenPhong);
        load_DV();
        addEvents();
        // Khởi tạo JDateChooser và đặt giá trị là ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(currentDate);
        txt_ngayThue.setDate(date);

        cthd = new ChiTietHoaDon();
        txt_thue.setText(String.valueOf(hd.getThue() * 100) + " %");
        if (ngayTra != null) {
            Date date1 = java.sql.Date.valueOf(ngayTra);
            txt_ngayTra.setDate(date1);
            if (!txt_ngayTra.getDate().toString().equals("")) {
                rd_theoNgay.setSelected(true);
                LocalDate ngayThue = txt_ngayThue.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                cthd.setNgayNhanPhong(ngayThue);
                cthd.setNgayTraPhong(ngayTra);
                cthd.setPhong(phong);
                cthd.tinhTienThuePhong();
                double tienThuePhong = cthd.getTongTienThuePhong();
                // Định dạng số tiền thành chuỗi tiền tệ
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
                String tienThuePhongFormatted = currencyFormat.format(tienThuePhong);
                txt_tienThuePhong.setText(tienThuePhongFormatted);
            }
        }
        int max = 0;
        max = phong.getSoGiuong() * 2;
        if (phong.isGiuongPhu() && phong.getLoaiPhong().getTenLoai() != 5) {
            max = max + 1;
        }

        for (int i = 1; i <= max; i++) {
            cbSoNguoi.addItem(String.valueOf(i));
        }

        if (soNguoi != 0) {
            cbSoNguoi.setSelectedIndex(soNguoi - 1);
        }
        
        cbSoNguoi.setSelectedIndex(-1);
        
        cbSoNguoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txt_ngayTra.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày trả trước khi chọn số người.");
                    cbSoNguoi.setSelectedIndex(-1); // Reset selection
                    return;
                }
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedItem = (String) comboBox.getSelectedItem();
                int soNguoi_Choosed = Integer.parseInt(selectedItem);
                Phong phong;
                try {
                    p_dao = new Phong_DAO();
                    phong = p_dao.getPhongTheoTenPhong(txt_tenPhong.getText()).getFirst();
                    int max = 0, max_st = 0;
                    max = phong.getSoGiuong() * 2;
                    if (phong.isGiuongPhu() && phong.getLoaiPhong().getTenLoai() != 5) {
                        max_st = max + 1;
                    }
                    double phuPhi = 0;
                    System.out.println(soNguoi_Choosed+"CCCC" + max_st);
                    if (phong.getLoaiPhong().getTenLoai() == 1 || phong.getLoaiPhong().getTenLoai() == 2) {
                        if (soNguoi_Choosed == max_st) {
                            LocalDate ngayThue = txt_ngayThue.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            LocalDate ngayTra = txt_ngayTra.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            long soNgayThue = ChronoUnit.DAYS.between(ngayThue, ngayTra);
                            phuPhi = 7000 * soNgayThue * 24;
                            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                            ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
                            String phuPhiFormatted = currencyFormat.format(phuPhi);
                            txt_phuPhi.setText(phuPhiFormatted);
                        } else {
                            txt_phuPhi.setText("0 VNĐ");
                        }
                    } else if (phong.getLoaiPhong().getTenLoai() == 3 || phong.getLoaiPhong().getTenLoai() == 4) {
                        System.out.println(soNguoi_Choosed);
                        if (soNguoi_Choosed == max_st) {
                            LocalDate ngayThue = txt_ngayThue.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            LocalDate ngayTra = txt_ngayTra.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            long soNgayThue = ChronoUnit.DAYS.between(ngayThue, ngayTra);
                            phuPhi = 10000 * soNgayThue * 24;
                            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                            ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
                            String phuPhiFormatted = currencyFormat.format(phuPhi);
                            txt_phuPhi.setText(phuPhiFormatted);
                        } else {
                            txt_phuPhi.setText("0 VNĐ");
                        }
                    } else {
                        txt_phuPhi.setText("0 VNĐ");
                    }
                    capNhatGia();
                } catch (IOException ex) {
                    Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        rd_theoNgay.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txt_tienThuePhong.setText("0 VNĐ");
                    txt_ngayTra.addPropertyChangeListener(new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if ("date".equals(evt.getPropertyName()) && !isSettingDate) {
                                LocalDate ngayThue = txt_ngayThue.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                cthd.setNgayNhanPhong(ngayThue);
                                if (txt_ngayTra.getDate() != null) {
                                    LocalDate ngayTra = txt_ngayTra.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                    if (ngayTra.isBefore(ngayThue)) {
                                        JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày trả sau ngày thuê phòng.");
                                        isSettingDate = true; // Đặt cờ để ngăn chặn việc lặp lại
                                        txt_ngayTra.setDate(null);
                                        isSettingDate = false; // Đặt lại cờ sau khi đặt giá trị
                                    }
                                    cthd.setNgayTraPhong(ngayTra);
                                    cthd.setPhong(phong);
                                    cthd.tinhTienThuePhong();
                                    double tienThuePhong = cthd.getTongTienThuePhong();
                                    // Định dạng số tiền thành chuỗi tiền tệ
                                    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                                    ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
                                    String tienThuePhongFormatted = currencyFormat.format(tienThuePhong);
                                    txt_tienThuePhong.setText(tienThuePhongFormatted);
                                }
                            }
                            try {
                                capNhatGia();
                            } catch (IOException ex) {
                                Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                }
            }
        });

        rd_theoGio.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txt_tienThuePhong.setText("0 VNĐ");
                }
            }
        });
        txt_ngayTra.addPropertyChangeListener(new PropertyChangeListener() {
            @Override

            public void propertyChange(PropertyChangeEvent evt) {

                if ("date".equals(evt.getPropertyName()) && !isSettingDate) {
                    if (!rd_theoNgay.isSelected() && !rd_theoGio.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn 'Theo ngày' hoặc 'Theo giờ' trước khi chọn ngày trả.");

                        isSettingDate = true; // Đặt cờ để ngăn chặn việc lặp lại
                        txt_ngayTra.setDate(null);
                        isSettingDate = false; // Đặt lại cờ sau khi đặt giá trị
                    }
                }
            }
        });

        txt_SDT.addKeyListener(new KeyAdapter() {
            private KhachHang_DAO kh_dao = new KhachHang_DAO();

            @Override
            public void keyReleased(KeyEvent e) {
                // Gọi hàm tìm kiếm theo thời gian thực khi có sự thay đổi trên txt_SDT
                try {
                    String maKhachHang = txt_SDT.getText().toString();
                    if (!maKhachHang.isEmpty()) {
                        ArrayList<KhachHang> khachHangList = kh_dao.getKHTheoMaKhachHang(maKhachHang);
                        if (!khachHangList.isEmpty()) {
                            KhachHang kh = khachHangList.get(0);
                            txt_HvT.setText(kh.getHoTenKhachHang());
                            txt_CCCD.setText(kh.getCCCD());
                            if (kh.isGioiTinh()) {
                                rd_nam.setSelected(true);
                            } else {
                                rd_nu.setSelected(true);
                            }
                            txt_ngaySinh.setText(kh.getNgaySinh().toString());
                        } else {
                            // Clear text fields if no matching customer found
                            txt_HvT.setText("");
                            txt_CCCD.setText("");
                            rd_nam.setSelected(false);
                            rd_nu.setSelected(false);
                            txt_ngaySinh.setText("");
                        }
                    } else {
                        // Clear text fields if txt_SDT is empty
                        txt_HvT.setText("");
                        txt_CCCD.setText("");
                    }
                } catch (IOException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public void genID_HD(HoaDon hd) {
        hd_dao = new HoaDon_DAO();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmddMMyyyy");
        int soLuong = hd_dao.demSoLuongHoaDonTheoMaMau(sdf.format(cal.getTime()));
        String sl = String.format("%03d", soLuong);
        String maHD = "HD" + sdf.format(cal.getTime()) + sl;
        hd.setMaHoaDon(maHD);
    }

    public void addEvents() {
        btn_them.addActionListener(this);
        btn_xoa.addActionListener(this);
        btn_Luu.addActionListener(this);
        btn_Huy.addActionListener(this);
        btn_themKH.addActionListener(this);
    }

    public void load_DV() throws SQLException {
        dau_dao = new DoAnUong_DAO();
        ArrayList<DoAnUong> list = dau_dao.getAllTableDoAnUong();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");

        for (DoAnUong dau : list) {
            if (!dau.getMaDoAnUong().equals("XXX") && dau.getTrangThaiSuDung().getTentrangThaiSuDung() != 3) {
                if (dau.getSoLuong() != 0) {
                    modelDV.addRow(new Object[]{dau.getMaDoAnUong(), dau.getTenDoAnUong(), dau.getSoLuong(), currencyFormat.format(dau.getGiaBan())});
                }
            }
        }

        for (DoAnUong dau : list) {
            if (!dau.isLoai()) {
                if (!dau.getMaDoAnUong().equals("XXX") && dau.getTrangThaiSuDung().getTentrangThaiSuDung() != 3) {
                    if (dau.getSoLuong() != 0) {
                        modelDV1.addRow(new Object[]{dau.getMaDoAnUong(), dau.getTenDoAnUong(), dau.getSoLuong(), currencyFormat.format(dau.getGiaBan())});
                    }
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btn_themKH)) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm khách hàng không?", "Chú ý không có khách hàng này", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                txt_SDT.requestFocus();
                JPanel_ThemKHNhanh themKHNhanhPanel;
                themKHNhanhPanel = new JPanel_ThemKHNhanh();
                JFrame themKHFrame = new JFrame("Thêm Khách Hàng Nhanh");
                themKHFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                themKHFrame.add(themKHNhanhPanel);
                themKHFrame.pack();
                themKHFrame.setLocationRelativeTo(null);
                themKHFrame.setVisible(true);
            } else {
                txt_SDT.requestFocus();
            }
        }

        if (e.getSource().equals(btn_them)) {
            int row = tbl_DV.getSelectedRow();
            try {
                ArrayList<DoAnUong> list = dau_dao.getAllTableDoAnUong();
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
                DoAnUong dau = dau_dao.getDAUTheoMaDoAnUong(modelDV.getValueAt(row, 0).toString()).getFirst();

                // Kiểm tra xem mã đã tồn tại trong modelDichVu chưa
                boolean found = false;
                int soLuong = 1;
                for (int i = 0; i < modelDichVu.getRowCount(); i++) {
                    if (modelDichVu.getValueAt(i, 0).equals(dau.getMaDoAnUong())) {
                        // Mã đã tồn tại, tăng số lượng lên 1
                        soLuong = (int) modelDichVu.getValueAt(i, 2) + 1;
                        dau.setSoLuong(Integer.parseInt(modelDV.getValueAt(row, 2).toString()) - 1); // Giảm số lượng trong kho
                        if (dau.getSoLuong() < 0) {
                            JOptionPane.showMessageDialog(this, "Không Đủ Số Lượng!");
                            return;
                        } else {
                            modelDichVu.setValueAt(soLuong, i, 2);
                            // Cập nhật tổng tiền
                            double giaBan = dau.getGiaBan();
                            double tongTien = soLuong * giaBan;
                            modelDichVu.setValueAt(currencyFormat.format(tongTien), i, 4);
                            found = true;
                            break;
                        }

                    }
                }

                if (!found) {
                    // Mã chưa tồn tại, thêm dòng mới vào modelDichVu
                    soLuong = 1;
                    dau.setSoLuong(Integer.parseInt(modelDV.getValueAt(row, 2).toString()) - 1); // Giảm số lượng trong kho
                    if (dau.getSoLuong() < 0) {
                        JOptionPane.showMessageDialog(this, "Không Đủ Số Lượng!");
                        return;
                    } else {
                        double tongTien = soLuong * dau.getGiaBan();
                        modelDichVu.addRow(new Object[]{dau.getMaDoAnUong(), dau.getTenDoAnUong(), soLuong, currencyFormat.format(dau.getGiaBan()), currencyFormat.format(tongTien)});
                    }
                }

                // Cập nhật số lượng và giá trị
                modelDV.setValueAt(dau.getSoLuong(), row, 2);
                capNhatGia();
            } catch (IOException ex) {
                Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource().equals(btn_xoa)) {
            int selectedRow = tbl_gioDichVu.getSelectedRow();
            if (selectedRow != -1) {
                int soLuong = (int) modelDichVu.getValueAt(selectedRow, 2);
                // Giảm số lượng đi 1 nếu lớn hơn 1
                modelDichVu.setValueAt(soLuong - 1, selectedRow, 2);
                double giaBan = Double.parseDouble(modelDichVu.getValueAt(selectedRow, 3).toString().replace(" VNĐ", "").replace(",", ""));
                double tongTien = (soLuong - 1) * giaBan;
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
                modelDichVu.setValueAt(currencyFormat.format(tongTien), selectedRow, 4);

                // Cập nhật lại số lượng trong modelDV
                if (modelDV.getRowCount() > 0 && modelDichVu.getRowCount() > 0) {
                    for (int i = 0; i < modelDV.getRowCount(); i++) {
                        if (modelDV.getValueAt(i, 0).toString().equals(modelDichVu.getValueAt(selectedRow, 0))) {
                            int soLuongDV = Integer.parseInt(modelDV.getValueAt(i, 2).toString());
                            modelDV.setValueAt(soLuongDV + 1, i, 2);
                            break;
                        }
                    }
                }

                // Xóa dòng khi số lượng giảm về 0
                if (soLuong == 1) {
                    modelDichVu.removeRow(selectedRow);
                }

                try {
                    capNhatGia();
                } catch (IOException ex) {
                    Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Hiển thị thông báo khi không có dòng nào được chọn
                JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ cần xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource().equals(btn_Luu)) {
            if (!txt_ngayTra.getDate().before(txt_ngayThue.getDate()) && !txt_SDT.getText().equals("") && !txt_HvT.getText().equals("")) {
                Phong p = null;
                HoaDon hd = new HoaDon();
                genID_HD(hd);
                hd.setKhachHang(new KhachHang(txt_SDT.getText()));
                hd.setNhanVien(nhanVien);
                hd.setNgayLapHoaDon(LocalDate.now());
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                try {
                    new KhuyenMai_DAO();
                    hd.setKhuyenMai(null);
                    hd_dao.createHoaDon(hd);
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Kiểm tra bảng dịch vụ có dữ liệu hay không
                if (modelDichVu.getRowCount() == 0) {
                    // Nếu bảng rỗng, gán đồ ăn uống là null hoặc xử lý tùy ý

                    try {
                        cthd.setDoAnUong(new DoAnUong("XXX"));
                        cthd.setHoaDon(hd);
                        p = p_dao.getPhongTheoTenPhong(txt_tenPhong.getText()).getFirst();
                        cthd.setPhong(p);
                        cthd.setSoLuong(0);
                        cthd.setSoLuongNguoiO(Integer.parseInt(cbSoNguoi.getSelectedItem().toString()));

                        // Lấy ngày từ JDateChooser
                        Date thue = txt_ngayThue.getDate();

                        // Chuyển đổi thành LocalDate
                        Instant instant = thue.toInstant();
                        ZoneId zoneId = ZoneId.systemDefault();
                        LocalDate ngayThue = instant.atZone(zoneId).toLocalDate();
                        cthd.setNgayNhanPhong(ngayThue);

                        // Lấy ngày từ JDateChooser
                        Date tra = txt_ngayTra.getDate();

                        // Chuyển đổi thành LocalDate
                        Instant instant1 = tra.toInstant();
                        ZoneId zoneId1 = ZoneId.systemDefault();
                        LocalDate ngaytra = instant1.atZone(zoneId1).toLocalDate();
                        cthd.setNgayTraPhong(ngaytra);
                        cthd.setPhuPhi(Double.parseDouble(txt_phuPhi.getText().replace(" VNĐ", "").replace(",", "")));
                        cthd.tinhTienThuePhong();

                        cthd.tinhTongTienDichVu();
                        cthd_dao = new ChiTietHoaDon_DAO();
                        cthd_dao.createChiTietHoaDon(cthd);
                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                for (int i = 0; i < modelDichVu.getRowCount(); i++) {
                    try {
                        cthd.setHoaDon(hd);
                        DoAnUong dau = dau_dao.getDAUTheoMaDoAnUong(modelDichVu.getValueAt(i, 0).toString()).getFirst();
                        cthd.setDoAnUong(dau);
                        p = p_dao.getPhongTheoTenPhong(txt_tenPhong.getText()).getFirst();
                        cthd.setPhong(p);
                        cthd.setSoLuong(Integer.parseInt(modelDichVu.getValueAt(i, 2).toString()));
                        dau.setSoLuong(dau.getSoLuong() - Integer.parseInt(modelDichVu.getValueAt(i, 2).toString()));
                        dau_dao.updateDoAnUong(dau);
                        cthd.setSoLuongNguoiO(Integer.parseInt(cbSoNguoi.getSelectedItem().toString()));

                        // Lấy ngày từ JDateChooser
                        Date thue = txt_ngayThue.getDate();

                        // Chuyển đổi thành LocalDate
                        Instant instant = thue.toInstant();
                        ZoneId zoneId = ZoneId.systemDefault();
                        LocalDate ngayThue = instant.atZone(zoneId).toLocalDate();
                        cthd.setNgayNhanPhong(ngayThue);

                        // Lấy ngày từ JDateChooser
                        Date tra = txt_ngayTra.getDate();

                        // Chuyển đổi thành LocalDate
                        Instant instant1 = tra.toInstant();
                        ZoneId zoneId1 = ZoneId.systemDefault();
                        LocalDate ngaytra = instant1.atZone(zoneId1).toLocalDate();
                        cthd.setNgayTraPhong(ngaytra);
                        cthd.tinhTienThuePhong();
                        cthd.tinhTongTienDichVu();
                        cthd.getDoAnUong().setGiaBan();
                        cthd.tinhTongTienDichVu();
                        cthd.setPhuPhi(Double.parseDouble(txt_phuPhi.getText().replace(" VNĐ", "").replace(",", "")));
                        cthd_dao = new ChiTietHoaDon_DAO();
                        cthd_dao.createChiTietHoaDon(cthd);
                        System.out.println(hd.getMaHoaDon() + "+" + cthd.getHoaDon().getMaHoaDon());

                    } catch (IOException ex) {
                        Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (p != null) {
                    p.setTrangThaiPhong(TrangThaiPhong.OCCUPIED);
                    p_dao.updatePhong(p);
                }
                JOptionPane.showMessageDialog(this, "Thuê Phòng Thành Công!");
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                if (parentFrame != null) {
                    parentFrame.dispose(); // Tắt JFrame chứa JPanel
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sai cú pháp số điện thoại khách hàng hoặc ngày trả, thuê sai quy định");
            }
            if (khm != null) {
                try {
                    ChiTietPhieuDatPhong_DAO ctpdt = new ChiTietPhieuDatPhong_DAO();
                    ctpdt.setTrangThai(khm.getMaPhieuDatPhong(), false);
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                this.Jqlp.loadData();
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource().equals(btn_Huy)) {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                parentFrame.dispose(); // Tắt JFrame chứa JPanel
            }
        }
    }

    public double tinhTongTienDV() {
        double tongTienDV = 0;
        for (int i = 0; i < modelDichVu.getRowCount(); i++) {
            int soLuong = (int) modelDichVu.getValueAt(i, 2);
            double giaBan = Double.parseDouble(modelDichVu.getValueAt(i, 3).toString().replace(" VNĐ", "").replace(",", ""));
            double tongTien = soLuong * giaBan;
            tongTienDV += tongTien;
        }
        return tongTienDV;
    }

    public void capNhatGia() throws IOException, SQLException {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");

        // Tính tổng tiền từ cột 4 của modelDichVu
        double tongTienDV = tinhTongTienDV();

        // Gán giá trị tổng tiền vào txt_tienDV
        txt_tienDV.setText(currencyFormat.format(tongTienDV));

        // Tính tổng tiền thuê phòng từ txt_tienThuePhong
        double tienThuePhong = Double.parseDouble(txt_tienThuePhong.getText().replace(" VNĐ", "").replace(",", ""));

        //tính tiền phụ phí
        double phuPhi = Double.parseDouble(txt_phuPhi.getText().replace(" VNĐ", "").replace(",", ""));

        // Tính tổng tiền ban đầu
        double thanhTienBanDau = tienThuePhong + tongTienDV + phuPhi;
        double thanhTien = thanhTienBanDau * (1 + new HoaDon().getThue());

        // Gán giá trị tổng tiền ban đầu vào txt_thanhTienBanDau
        txt_thanhTienBanDau.setText(currencyFormat.format(thanhTienBanDau));
        txt_thanhTien.setText(currencyFormat.format(thanhTien));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_dichVu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txt_maDIchVu = new javax.swing.JTextField();
        pn_detailDichVu = new javax.swing.JTabbedPane();
        pn_tatCa = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_DV = new javax.swing.JTable();
        pn_DoUong = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_DV1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btn_xoa = new javax.swing.JButton();
        btn_them = new javax.swing.JButton();
        pn_thuePhong = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_tenPhong = new javax.swing.JTextField();
        txt_loaiPhong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_ngayThue = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        txt_ngayTra = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        rd_theoNgay = new javax.swing.JRadioButton();
        rd_theoGio = new javax.swing.JRadioButton();
        sp_time = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        cbSoNguoi = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_SDT = new javax.swing.JTextField();
        txt_HvT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_themKH = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txt_CCCD = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_ngaySinh = new javax.swing.JTextField();
        rd_nu = new javax.swing.JRadioButton();
        rd_nam = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_gioDichVu = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txt_tienDV = new javax.swing.JTextField();
        txt_tienThuePhong = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_thanhTien = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_phuPhi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_thue = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_thanhTienBanDau = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btn_Huy = new javax.swing.JButton();
        btn_Luu = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 800));
        setLayout(new java.awt.BorderLayout());

        pn_dichVu.setPreferredSize(new java.awt.Dimension(350, 300));
        pn_dichVu.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cập nhật dịch vụ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 80));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(txt_maDIchVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 330, 40));

        pn_dichVu.add(jPanel2, java.awt.BorderLayout.NORTH);

        pn_tatCa.setLayout(new java.awt.BorderLayout());

        String[] colHeader = {"Mã DV", "Tên DV", "Số Lượng", "Giá Bán"};
        modelDV = new DefaultTableModel(colHeader, 0);
        tbl_DV.setModel(modelDV);
        jScrollPane2.setViewportView(tbl_DV);

        pn_tatCa.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pn_detailDichVu.addTab("Tất cả", pn_tatCa);

        pn_DoUong.setLayout(new java.awt.BorderLayout());

        String[] colHeader1 = {"Mã DV", "Tên DV", "Số Lượng", "Giá Bán"};
        modelDV1 = new DefaultTableModel(colHeader1, 0);
        tbl_DV1.setModel(modelDV1);
        jScrollPane3.setViewportView(tbl_DV1);

        pn_DoUong.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        pn_detailDichVu.addTab("Đồ uống", pn_DoUong);

        pn_dichVu.add(pn_detailDichVu, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(150, 60));

        btn_xoa.setText("Xóa");

        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_them, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(btn_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pn_dichVu.add(jPanel1, java.awt.BorderLayout.SOUTH);

        add(pn_dichVu, java.awt.BorderLayout.WEST);

        pn_thuePhong.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(644, 180));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Chi Tiết"));
        jPanel3.setPreferredSize(new java.awt.Dimension(727, 170));

        jLabel1.setText("Tên Phòng:");

        jLabel4.setText("Loại Phòng:");

        txt_tenPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenPhongActionPerformed(evt);
            }
        });

        txt_loaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_loaiPhongActionPerformed(evt);
            }
        });

        jLabel7.setText("Ngày Thuê:");

        jLabel15.setText("Ngày Trả:");

        jLabel16.setText("Thuê Theo:");

        rd_theoNgay.setText("Theo Ngày");

        rd_theoGio.setText("Theo giờ");

        sp_time.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel20.setText("Số người:");

        cbSoNguoi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rd_theoNgay)
                        .addGap(18, 18, 18)
                        .addComponent(rd_theoGio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sp_time, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSoNguoi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txt_loaiPhong)
                    .addComponent(txt_ngayTra, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(txt_ngayThue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_tenPhong))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_loaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rd_theoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rd_theoGio)
                                .addComponent(jLabel16)
                                .addComponent(sp_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)
                                .addComponent(cbSoNguoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_ngayThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(txt_ngayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        gr_ttheo.add(rd_theoNgay);gr_ttheo.add(rd_theoGio);

        jPanel5.add(jPanel3);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Khách Hàng"));
        jPanel8.setPreferredSize(new java.awt.Dimension(728, 130));

        jLabel6.setText("Họ và Tên:");

        txt_SDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SDTActionPerformed(evt);
            }
        });

        txt_HvT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_HvTActionPerformed(evt);
            }
        });

        jLabel8.setText("Giới tính:");

        jLabel5.setText("Số Điên Thoại:");

        btn_themKH.setIcon(new FlatSVGIcon("./icon/search_1.svg"));
        btn_themKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themKHActionPerformed(evt);
            }
        });

        jLabel17.setText("CCCD:");

        txt_CCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CCCDActionPerformed(evt);
            }
        });

        jLabel18.setText("Ngày Sinh:");

        txt_ngaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngaySinhActionPerformed(evt);
            }
        });

        rd_nu.setText("Nữ");

        rd_nam.setText("Nam");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txt_SDT, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_themKH, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_HvT)
                    .addComponent(txt_CCCD)
                    .addComponent(txt_ngaySinh)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(rd_nam)
                        .addGap(18, 18, 18)
                        .addComponent(rd_nu)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_themKH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_HvT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rd_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rd_nu)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txt_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        gr_dt.add(rd_nu);
        gr_dt.add(rd_nam);

        jPanel5.add(jPanel8);

        pn_thuePhong.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.BorderLayout());

        String[] colHeader2 = {"Mã DV", "Tên DV", "Số Lượng", "Giá Bán","Thành tiền"};
        modelDichVu = new DefaultTableModel(colHeader2, 0);
        tbl_gioDichVu.setModel(modelDichVu);
        jScrollPane4.setViewportView(tbl_gioDichVu);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        tbl_gioDichVu.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        tbl_gioDichVu.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        DefaultTableCellRenderer cenRenderer = new DefaultTableCellRenderer();
        cenRenderer.setHorizontalAlignment(JLabel.CENTER);

        tbl_gioDichVu.getColumnModel().getColumn(0).setCellRenderer(cenRenderer);
        tbl_gioDichVu.getColumnModel().getColumn(2).setCellRenderer(cenRenderer);

        jPanel9.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Hóa Đơn"));
        jPanel10.setPreferredSize(new java.awt.Dimension(728, 200));
        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

        jPanel11.setPreferredSize(new java.awt.Dimension(944, 150));

        jLabel12.setText("Tiền Dịch Vụ:");

        txt_tienDV.setText("0 VNĐ");
        txt_tienDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienDVActionPerformed(evt);
            }
        });

        txt_tienThuePhong.setText("0 VNĐ");
        txt_tienThuePhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienThuePhongActionPerformed(evt);
            }
        });

        jLabel14.setText("Thành Tiền:");

        txt_thanhTien.setText("0 VNĐ");
        txt_thanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_thanhTienActionPerformed(evt);
            }
        });

        jLabel9.setText("Phụ Phí:");

        txt_phuPhi.setText("0 VNĐ");
        txt_phuPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_phuPhiActionPerformed(evt);
            }
        });

        jLabel10.setText("Thuế(VAT):");

        txt_thue.setText("0 VNĐ");
        txt_thue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_thueActionPerformed(evt);
            }
        });

        jLabel13.setText("Thành Tiền Ban Đầu:");

        txt_thanhTienBanDau.setText("0 VNĐ");
        txt_thanhTienBanDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_thanhTienBanDauActionPerformed(evt);
            }
        });

        jLabel19.setText("Tiền Phòng:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_thue, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(txt_thanhTienBanDau))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txt_tienThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txt_phuPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tienDV, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(txt_thanhTien))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_thue, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tienThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tienDV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_thanhTienBanDau, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_phuPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_thanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );

        jPanel10.add(jPanel11);

        jPanel6.add(jPanel10, java.awt.BorderLayout.SOUTH);

        pn_thuePhong.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel7.setPreferredSize(new java.awt.Dimension(728, 60));

        btn_Huy.setText("Hủy");

        btn_Luu.setText("Lưu");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(867, Short.MAX_VALUE)
                .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(btn_Luu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pn_thuePhong.add(jPanel7, java.awt.BorderLayout.SOUTH);

        add(pn_thuePhong, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_HvTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_HvTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_HvTActionPerformed

    private void txt_SDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SDTActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_themActionPerformed

    private void txt_thanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_thanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_thanhTienActionPerformed

    private void txt_tienThuePhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienThuePhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienThuePhongActionPerformed

    private void txt_tienDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienDVActionPerformed

    private void txt_thanhTienBanDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_thanhTienBanDauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_thanhTienBanDauActionPerformed

    private void txt_thueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_thueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_thueActionPerformed

    private void txt_phuPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_phuPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_phuPhiActionPerformed

    private void txt_CCCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CCCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CCCDActionPerformed

    private void txt_ngaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngaySinhActionPerformed

    private void txt_loaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_loaiPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_loaiPhongActionPerformed

    private void txt_tenPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tenPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenPhongActionPerformed

    private void btn_themKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_themKHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Huy;
    private javax.swing.JButton btn_Luu;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_themKH;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> cbSoNguoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pn_DoUong;
    private javax.swing.JTabbedPane pn_detailDichVu;
    private javax.swing.JPanel pn_dichVu;
    private javax.swing.JPanel pn_tatCa;
    private javax.swing.JPanel pn_thuePhong;
    private javax.swing.JRadioButton rd_nam;
    private javax.swing.JRadioButton rd_nu;
    private javax.swing.JRadioButton rd_theoGio;
    private javax.swing.JRadioButton rd_theoNgay;
    private javax.swing.JSpinner sp_time;
    private javax.swing.JTable tbl_DV;
    private javax.swing.JTable tbl_DV1;
    private javax.swing.JTable tbl_gioDichVu;
    private javax.swing.JTextField txt_CCCD;
    private javax.swing.JTextField txt_HvT;
    private javax.swing.JTextField txt_SDT;
    private javax.swing.JTextField txt_loaiPhong;
    private javax.swing.JTextField txt_maDIchVu;
    private javax.swing.JTextField txt_ngaySinh;
    private com.toedter.calendar.JDateChooser txt_ngayThue;
    private com.toedter.calendar.JDateChooser txt_ngayTra;
    private javax.swing.JTextField txt_phuPhi;
    private javax.swing.JTextField txt_tenPhong;
    private javax.swing.JTextField txt_thanhTien;
    private javax.swing.JTextField txt_thanhTienBanDau;
    private javax.swing.JTextField txt_thue;
    private javax.swing.JTextField txt_tienDV;
    private javax.swing.JTextField txt_tienThuePhong;
    // End of variables declaration//GEN-END:variables
}
