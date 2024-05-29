/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.ChiTietHoaDon_DAO;
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
import entity.Phong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M S I
 */
public class JPanel_loadThongTinThue extends javax.swing.JPanel implements ActionListener {

    private LocalDate previousNgayTra = null;
    ArrayList<DoAnUong> listadd = new ArrayList<>();
    ArrayList<DoAnUong> listdel = new ArrayList<>();
    JPanel_QuanLyPhong Jqlp;
    private HoaDon_DAO hd_dao;
    private Phong_DAO p_dao;
    private DefaultTableModel modelDV;
    private Object jScrollPane1;
    private DoAnUong_DAO dau_dao;
    private DefaultTableModel modelDV1;
    private DefaultTableModel modelDichVu;
    private ChiTietHoaDon cthd;
    javax.swing.ButtonGroup gr_ttheo = new javax.swing.ButtonGroup();
    javax.swing.ButtonGroup gr_dt = new javax.swing.ButtonGroup();
    private NhanVien nhanVien;
    private ChiTietHoaDon_DAO cthd_dao;
    private KhuyenMai_DAO km_dao;
    private final ChiTietHoaDon chiTietHoaDon;
    private KhachHang_DAO kh_dao;
    private final HoaDon hoaDon;
    private int fisrt_time;
    private HoaDon mahd;

    /**
     * Creates new form JPanel_thuePhong
     */
    public JPanel_loadThongTinThue(JPanel_QuanLyPhong quanLyPhong, String tenPhong, NhanVien nv, ChiTietHoaDon cthd, HoaDon hd) throws IOException, SQLException {
        initComponents();
        this.mahd = hd;
        rd_nam.setEnabled(false);
        rd_nu.setEnabled(false);
        rd_theoNgay.setEnabled(false);
        rd_theoGio.setEnabled(false);
        txt_ngayThue.setEnabled(false);
        this.setSize(WIDTH, HEIGHT);
        nhanVien = nv;
        hoaDon = hd;
        chiTietHoaDon = cthd;
        this.Jqlp = quanLyPhong;
        loadThongTin(tenPhong, cthd);
        this.previousNgayTra = txt_ngayTra.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
        String keyword = txt_maDIchVu.getText().toLowerCase();
        try {
            ArrayList<DoAnUong> list = dau_dao.getAllTableDoAnUong();
            modelDV.setRowCount(0); // Xóa toàn bộ dữ liệu trong bảng dịch vụ

            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
            ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");

            for (DoAnUong dau : list) {
                String ten = dau.getTenDoAnUong().toLowerCase();
                String ma = dau.getMaDoAnUong().toLowerCase();
                if (ten.contains(keyword) || ma.contains(keyword)) {
                    if (!dau.getMaDoAnUong().equals("XXX") && dau.getTrangThaiSuDung().getTentrangThaiSuDung() != 3 && dau.getSoLuong() != 0) {
                        modelDV.addRow(new Object[]{dau.getMaDoAnUong(), dau.getTenDoAnUong(), dau.getSoLuong(), currencyFormat.format(dau.getGiaBan())});
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadThongTin(String tenPhong, ChiTietHoaDon cthd) throws IOException, SQLException {
        rd_theoNgay.setSelected(true);
        p_dao = new Phong_DAO();
        Phong phong = p_dao.getPhongTheoTenPhong(tenPhong).getFirst();
        txt_loaiPhong.setText(phong.getLoaiPhong().toString());
        txt_tenPhong.setText(tenPhong);
        ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO();
        ChiTietHoaDon cthdd = cthd_dao.getChiTietHoaDontheoMa(cthd.getHoaDon().getMaHoaDon()).get(0);
        System.out.println("gui.JPanel_loadThongTinThue.loadThongTin()" + cthdd.getSoGio());
        if (cthdd.getSoGio() != 0) {
            rd_theoGio.setSelected(true);
            sp_time.setValue(cthdd.getSoGio());
            txt_ngayTra.setEnabled(false);
            fisrt_time = (int) sp_time.getValue();
        } else {
            sp_time.setValue(0);
            sp_time.setEnabled(false);

        }
        load_DV();
        addEvents();
        // Khởi tạo JDateChooser và đặt giá trị là ngày hiện tại
        LocalDate currentDate = cthd.getNgayNhanPhong();
        Date thue = java.sql.Date.valueOf(currentDate);
        txt_ngayThue.setDate(thue);

        LocalDate tra1 = cthd.getNgayTraPhong();
        Date tra = java.sql.Date.valueOf(tra1);
        txt_ngayTra.setDate(tra);
        hd_dao = new HoaDon_DAO();
        kh_dao = new KhachHang_DAO();
        load_DataDV(hoaDon.getMaHoaDon());
        KhachHang kh = kh_dao.getKHTheoMaKhachHang(hoaDon.getKhachHang().getMaKhachHang()).getFirst();
        txt_SDT.setText(kh.getMaKhachHang());
        txt_HvT.setText(kh.getHoTenKhachHang());
        if (kh.isGioiTinh()) {
            rd_nam.setSelected(true);
        } else {
            rd_nu.setSelected(true);
        }
        txt_CCCD.setText(kh.getCCCD());
        txt_ngaySinh.setText(kh.getNgaySinh().toString());
        txt_soLuongNguoiO.setText(String.valueOf(cthd.getSoLuongNguoiO()));
        txt_thue.setText(String.valueOf(hoaDon.getThue() * 100) + " %");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
        txt_tienDV.setText(currencyFormat.format(hd_dao.tinhTongTienDichVu(hoaDon.getMaHoaDon())));
        txt_thanhTienBanDau.setText(currencyFormat.format(hd_dao.tinhthanhTienBanDau(hoaDon.getMaHoaDon())));
        hoaDon.tinhTongThanhTienBanDau();
        hoaDon.getKhuyenMai().setGiaTri(0);
        hoaDon.tinhTongThanhTienPhaiTra();
        txt_thanhTien.setText(currencyFormat.format(hoaDon.getTongThanhTienPhaiTra()));
        ChiTietHoaDon ct = cthd_dao.getChiTietHoaDontheoMa(hoaDon.getMaHoaDon()).getFirst();
        double tienThueDefault = ct.getTongTienThuePhong() - ct.getPhuPhi();
        txt_tienThuePhong.setText(currencyFormat.format(tienThueDefault));
        System.out.println(ct);
        txt_phuPhi.setText(currencyFormat.format(ct.getPhuPhi()));

        if (rd_theoGio.isSelected()) {
            sp_time.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    if ((int) sp_time.getValue() >= fisrt_time) {
                        Date ngayThue_pre = txt_ngayThue.getDate();
                        if (ngayThue_pre != null) {
                            LocalDate ngayThue_next = ngayThue_pre.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            LocalDateTime ngayThue = LocalDateTime.of(ngayThue_next, LocalTime.of(14, 0));
                            int soGioThue = (Integer) sp_time.getValue();
                            LocalDateTime ngayTra = ngayThue.plusHours(soGioThue);
                            txt_ngayTra.setDate(Date.from(ngayTra.atZone(ZoneId.systemDefault()).toInstant()));
//                        System.out.println("Ngay Thue: " + ngayThue);
//                        System.out.println("Ngay Tra: " + ngayTra);

                            try {
                                LocalDate ngayTra_date = txt_ngayTra.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                Phong p = null;
                                p = p_dao.getPhongTheoTenPhong(txt_tenPhong.getText()).get(0);
                                cthd.setNgayNhanPhong(ngayThue_next);
                                cthd.setNgayTraPhong(ngayTra_date);
                                cthd.setPhong(phong);
                                if (rd_theoGio.isSelected()) {
                                    cthd.setSoGio((int) sp_time.getValue());
                                    cthd.tinhTienThuePhong(p, false);
                                } else {
                                    cthd.setSoGio(0);
                                    cthd.tinhTienThuePhong(p, true);
                                }
                                double tienThuePhong = cthd.getTongTienThuePhong();
                                // Định dạng số tiền thành chuỗi tiền tệ
                                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                                ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
                                String tienThuePhongFormatted = currencyFormat.format(tienThuePhong);
                                txt_tienThuePhong.setText(tienThuePhongFormatted);
                            } catch (IOException | SQLException ex) {
                                Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                capNhatGia();
                            } catch (IOException ex) {
                                Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày thuê!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Giờ gian hạn phải lớn hơn giờ hiện tại");
                        sp_time.setValue(fisrt_time);
                    }
                }
            });
        }

        if (rd_theoNgay.isSelected()) {
            txt_ngayTra.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("date".equals(evt.getPropertyName())) {
                        LocalDate ngayThue = txt_ngayThue.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        cthd.setNgayNhanPhong(ngayThue);
                        Phong p = null;
                        try {
                            p = p_dao.getPhongTheoTenPhong(txt_tenPhong.getText()).get(0);
                        } catch (IOException ex) {
                            Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (txt_ngayTra.getDate() != null) {
                            LocalDate ngayTra = txt_ngayTra.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                            // Check if ngayTra is after the previous ngayTra if it exists
                            if (ngayTra.isBefore(previousNgayTra)) {
                                JOptionPane.showMessageDialog(null, "Ngày trả mới phải sau ngày trả trước đó!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                Date pre = Date.from(previousNgayTra.atStartOfDay(ZoneId.systemDefault()).toInstant());
                                txt_ngayTra.setDate(pre); // Reset the date field to null
                                return; // Exit the method early
                            }

                            cthd.setNgayTraPhong(ngayTra);
                            cthd.setPhong(p);
                            if (rd_theoGio.isSelected()) {
                                cthd.setSoGio((int) sp_time.getValue());
                                cthd.tinhTienThuePhong(p, false);
                            } else {
                                cthd.setSoGio(0);
                                cthd.tinhTienThuePhong(p, true);
                            }
                            double tienThuePhong = cthd.getTongTienThuePhong();

                            // Định dạng số tiền thành chuỗi tiền tệ
                            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                            ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
                            String tienThuePhongFormatted = currencyFormat.format(tienThuePhong);
                            txt_tienThuePhong.setText(tienThuePhongFormatted);
                        }
                    }
                }
            });
        }

    }

    public void addEvents() {
        btn_them.addActionListener(this);
        btn_Luu.addActionListener(this);
        btn_TraPhong.addActionListener(this);
        btn_Huy.addActionListener(this);
        btn_xoa.addActionListener(this);
    }

    public void load_DV() throws SQLException {
        dau_dao = new DoAnUong_DAO();
        ArrayList<DoAnUong> list = dau_dao.getAllTableDoAnUong();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");

        for (DoAnUong dau : list) {
            if (!"XXX".equals(dau.getMaDoAnUong()) && dau.getSoLuong() != 0) {
                modelDV.addRow(new Object[]{dau.getMaDoAnUong(), dau.getTenDoAnUong(), dau.getSoLuong(), currencyFormat.format(dau.getGiaBan())});
            }
        }

        for (DoAnUong dau : list) {
            if (!dau.isLoai()) {
                if (!"XXX".equals(dau.getMaDoAnUong()) && dau.getSoLuong() != 0) {
                    modelDV1.addRow(new Object[]{dau.getMaDoAnUong(), dau.getTenDoAnUong(), dau.getSoLuong(), currencyFormat.format(dau.getGiaBan())});
                }
            }
        }

    }

    public void load_DataDV(String maHD) throws SQLException, IOException {
        cthd_dao = new ChiTietHoaDon_DAO();
        ArrayList<ChiTietHoaDon> list_temp = cthd_dao.getChiTietHoaDontheoMa(maHD);
        dau_dao = new DoAnUong_DAO();

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");

        for (ChiTietHoaDon cthd : list_temp) {
            DoAnUong dau = dau_dao.getDAUTheoMaDoAnUong(cthd.getDoAnUong().getMaDoAnUong()).getFirst();
            if (!"XXX".equals(dau.getMaDoAnUong())) {
                dau.setGiaBan();
                modelDichVu.addRow(new Object[]{dau.getMaDoAnUong(), dau.getTenDoAnUong(), cthd.getSoLuong(), currencyFormat.format(dau.getGiaBan()), currencyFormat.format(cthd.getTongTienDichVu())});
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btn_Huy)) {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                parentFrame.dispose(); // Tắt JFrame chứa JPanel
            }
        }

        if (e.getSource().equals(btn_them)) {
            int row = tbl_DV.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ cần thêm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                ArrayList<DoAnUong> list = dau_dao.getAllTableDoAnUong();
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
                DoAnUong dau = dau_dao.getDAUTheoMaDoAnUong(modelDV.getValueAt(row, 0).toString()).getFirst();
                listadd.add(dau);
                System.out.println(listadd);
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
                            dau.setGiaBan();
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
                        dau.setGiaBan();
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
                try {
                    DoAnUong dau = dau_dao.getPhongTheoTenDoAnUong(modelDichVu.getValueAt(selectedRow, 0).toString()).getFirst();

                    if (dau.isHoanTra()) {
                        listdel.add(dau);
                        int soLuong = (int) modelDichVu.getValueAt(selectedRow, 2);
                        // Giảm số lượng đi 1 nếu lớn hơn 1
                        modelDichVu.setValueAt(soLuong - 1, selectedRow, 2);
                        dau.setGiaBan();
                        double giaBan = dau.getGiaBan();
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

                        int soLuong = (int) modelDichVu.getValueAt(selectedRow, 2);
                        // Giảm số lượng đi 1 nếu lớn hơn 1

                        if (cthd_dao.checkDoAnUongExistInHoaDon(mahd.getMaHoaDon(), dau.getMaDoAnUong())) {
                            ChiTietHoaDon cthd2 = cthd_dao.getChiTietHoaDontheoMaHoaDonAndDoAnUong(mahd.getMaHoaDon(), dau.getMaDoAnUong()).get(0);
                            System.out.println(cthd2.getSoLuong() + "<" + (int) modelDichVu.getValueAt(selectedRow, 2));
                            if (cthd2.getSoLuong() > (int) modelDichVu.getValueAt(selectedRow, 2) - 1) {
                                JOptionPane.showMessageDialog(null, "Không thể hoàn trả");
                                return;
                            }
                        }
                        listdel.add(dau);
                        modelDichVu.setValueAt(soLuong - 1, selectedRow, 2);
                        dau.setGiaBan();
                        double giaBan = dau.getGiaBan();
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
                    }
                } catch (IOException ex) {
                    Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Hiển thị thông báo khi không có dòng nào được chọn
                JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ cần xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource().equals(btn_Luu)) {
            // Show a confirmation dialog
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn lưu các thay đổi?",
                    "Xác nhận lưu",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            // Check the user's response
            if (response == JOptionPane.YES_OPTION) {
                p_dao = new Phong_DAO();
                Phong p = null;
                HoaDon hd = null;
                ChiTietHoaDon cthd = null;

                try {
                    p = p_dao.getPhongTheoTenPhong(txt_tenPhong.getText()).getFirst();
                    ArrayList<ChiTietHoaDon> lcthd = cthd_dao.getChiTietHoaDontheoPhong(p.getMaPhong());
                    JPanel_loadThongTinThue thongTinThue = null;
                    hd_dao = new HoaDon_DAO();

                    ArrayList<HoaDon> lhd = hd_dao.getAllTableHoaDon();
                    for (HoaDon hd1 : lhd) {
                        for (ChiTietHoaDon cthd1 : lcthd) {
                            if (hd1.getTongThanhTienBanDau() == 0 && cthd1.getHoaDon().getMaHoaDon().equals(hd1.getMaHoaDon())) {
                                cthd = cthd1;
                            }
                        }
                    }
                    hd = cthd.getHoaDon();
                    Date tra = txt_ngayTra.getDate();
                    Instant instant1 = tra.toInstant();
                    ZoneId zoneId1 = ZoneId.systemDefault();
                    LocalDate ngaytra = instant1.atZone(zoneId1).toLocalDate();
                    cthd_dao.updateNgayTraPhongByMaHoaDon(hd.getMaHoaDon(), ngaytra);
                    if (rd_theoGio.isSelected()) {
                        cthd.setSoGio((int) sp_time.getValue());
                        cthd.tinhTienThuePhong(p, false);
                    } else {
                        cthd.setSoGio(0);
                        cthd.tinhTienThuePhong(p, true);
                    }
                    cthd_dao.updatesoGioByMaHoaDon(hd.getMaHoaDon(), (int) sp_time.getValue());
                    cthd_dao.updateTongTienThuePhongByMaHoaDon(hd.getMaHoaDon(), cthd.getTongTienThuePhong());
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
                }

                for (int i = 0; i < listadd.size(); ++i) {
                    try {
                        DoAnUong dau = dau_dao.getDAUTheoMaDoAnUong(listadd.get(i).getMaDoAnUong()).get(0);
                        dau.setSoLuong(dau.getSoLuong() - 1);
                        dau_dao.updateDoAnUong(dau);
                    } catch (IOException | SQLException ex) {
                        Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        if (!cthd_dao.checkDoAnUongExistInHoaDon(hd.getMaHoaDon(), listadd.get(i).getMaDoAnUong())) {
                            DoAnUong dau = listadd.get(i);
                            cthd.setDoAnUong(dau);

                            dau.setGiaBan();

                            cthd.setHoaDon(hd);
                            cthd.setPhong(p);
                            cthd.setSoLuong(1);

                            Date thue = txt_ngayThue.getDate();
                            Instant instant = thue.toInstant();
                            ZoneId zoneId = ZoneId.systemDefault();
                            LocalDate ngayThue = instant.atZone(zoneId).toLocalDate();
                            cthd.setNgayNhanPhong(ngayThue);

                            cthd.setSoLuongNguoiO(Integer.parseInt(txt_soLuongNguoiO.getText()));
                            cthd.setPhuPhi(Double.parseDouble(txt_phuPhi.getText().replace(" VNĐ", "").replace(",", "")));
                            if (rd_theoGio.isSelected()) {
                                cthd.setSoGio((int) sp_time.getValue());
                                cthd.tinhTienThuePhong(p, false);
                            } else {
                                cthd.setSoGio(0);
                                cthd.tinhTienThuePhong(p, true);
                            }
                            cthd.tinhTongTienDichVu();

                            cthd_dao.createChiTietHoaDon(cthd);
                        } else {
                            ChiTietHoaDon cthd_new = cthd_dao.getChiTietHoaDontheoMaHoaDonAndDoAnUong(hd.getMaHoaDon(), listadd.get(i).getMaDoAnUong()).get(0);
                            int soLuongcu = cthd_new.getSoLuong();
                            cthd_dao.updateSoLuongByMaHoaDonAndMaDoAnUong(cthd_new.getHoaDon().getMaHoaDon(), cthd_new.getDoAnUong().getMaDoAnUong(), soLuongcu + 1);
                            cthd_new.setDoAnUong(listadd.get(i));
                            cthd_new.setSoLuong(soLuongcu + 1);
                            cthd_new.tinhTongTienDichVu();
                            cthd_dao.updateSoGioByMaHoaDonAndMaDoAnUong(cthd_new.getHoaDon().getMaHoaDon(), cthd_new.getDoAnUong().getMaDoAnUong(), (int) sp_time.getValue());
                            cthd_dao.updateTongTienDichVuByMaHoaDonAndMaDoAnUong(cthd_new.getHoaDon().getMaHoaDon(), cthd_new.getDoAnUong().getMaDoAnUong(), cthd_new.getTongTienDichVu());
                        }
                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                for (int j = 0; j < listdel.size(); ++j) {
                    try {
                        DoAnUong dau = dau_dao.getDAUTheoMaDoAnUong(listdel.get(j).getMaDoAnUong()).get(0);
                        dau.setSoLuong(dau.getSoLuong() + 1);
                        dau_dao.updateDoAnUong(dau);
                    } catch (IOException | SQLException ex) {
                        Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        ChiTietHoaDon cthd_new = cthd_dao.getChiTietHoaDontheoMaHoaDonAndDoAnUong(hd.getMaHoaDon(), listdel.get(j).getMaDoAnUong()).get(0);
                        int soLuongcu = cthd_new.getSoLuong();
                        cthd_dao.updateSoLuongByMaHoaDonAndMaDoAnUong(cthd_new.getHoaDon().getMaHoaDon(), cthd_new.getDoAnUong().getMaDoAnUong(), soLuongcu - 1);
                        cthd_new.setDoAnUong(listdel.get(j));
                        cthd_new.setSoLuong(soLuongcu - 1);
                        cthd_new.tinhTongTienDichVu();
                        cthd_dao.updateSoLuongDoUongTraVeByMaHoaDonAndMaDoAnUong(cthd_new.getHoaDon().getMaHoaDon(), cthd_new.getDoAnUong().getMaDoAnUong(), soLuongcu - 1);
                        if (cthd_new.getSoLuong() != 0) {
                            cthd_dao.updateTongTienDichVuByMaHoaDonAndMaDoAnUong(cthd_new.getHoaDon().getMaHoaDon(), cthd_new.getDoAnUong().getMaDoAnUong(), cthd_new.getTongTienDichVu());
                        } else {
                            cthd_dao.deleteChiTietHoaDonByMaHoaDonAndMaDoAnUong(cthd_new.getHoaDon().getMaHoaDon(), cthd_new.getDoAnUong().getMaDoAnUong());
                        }
                    } catch (SQLException | IOException ex) {
                        Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                JOptionPane.showMessageDialog(this, "Đã lưu thành công");
            } else {
                // User chose NO, do nothing and return
                return;
            }
        }

        if (e.getSource().equals(btn_TraPhong)) {
            // Show a confirmation dialog
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn trả phòng?",
                    "Xác nhận trả phòng",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            // Check the user's response
            if (response == JOptionPane.YES_OPTION) {
                try {
                    // Create the JPanel_traPhong instance
                    JPanel_traPhong thongTinThue = new JPanel_traPhong(this.Jqlp, txt_tenPhong.getText(), nhanVien, chiTietHoaDon);

                    // Create a new JFrame to display the rental information
                    JFrame thongTinThueJFrame = new JFrame("Thông tin thuê phòng");
                    thongTinThueJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    thongTinThueJFrame.add(thongTinThue);
                    thongTinThueJFrame.pack();
                    thongTinThueJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    thongTinThueJFrame.setLocationRelativeTo(null);
                    thongTinThueJFrame.setVisible(true);

                } catch (IOException | SQLException ex) {
                    Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Close the parent JFrame containing this JPanel
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                if (parentFrame != null) {
                    parentFrame.dispose();
                }
            } else {
                // User chose NO, do nothing and return
                return;
            }
        }

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
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 0, 12, 1);
        sp_time = new javax.swing.JSpinner(spinnerModel);
        jLabel20 = new javax.swing.JLabel();
        txt_soLuongNguoiO = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_SDT = new javax.swing.JTextField();
        txt_HvT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
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
        btn_TraPhong = new javax.swing.JButton();
        btn_Luu = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 800));
        setLayout(new java.awt.BorderLayout());

        pn_dichVu.setPreferredSize(new java.awt.Dimension(350, 300));
        pn_dichVu.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(122, 178, 178));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cập nhật dịch vụ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
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

        jPanel1.setBackground(new java.awt.Color(122, 178, 178));
        jPanel1.setPreferredSize(new java.awt.Dimension(150, 60));

        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

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
                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pn_dichVu.add(jPanel1, java.awt.BorderLayout.SOUTH);

        add(pn_dichVu, java.awt.BorderLayout.WEST);

        pn_thuePhong.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(644, 180));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setBackground(new java.awt.Color(205, 232, 229));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 12), new java.awt.Color(77, 134, 156))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(727, 170));

        jLabel1.setText("Tên Phòng:");

        jLabel4.setText("Loại Phòng:");

        txt_tenPhong.setEditable(false);
        txt_tenPhong.setBackground(new java.awt.Color(255, 255, 255));
        txt_tenPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tenPhongActionPerformed(evt);
            }
        });

        txt_loaiPhong.setEditable(false);
        txt_loaiPhong.setBackground(new java.awt.Color(255, 255, 255));
        txt_loaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_loaiPhongActionPerformed(evt);
            }
        });

        jLabel7.setText("Ngày Thuê:");

        txt_ngayThue.setBackground(new java.awt.Color(205, 232, 229));

        jLabel15.setText("Ngày Trả:");

        txt_ngayTra.setBackground(new java.awt.Color(205, 232, 229));

        jLabel16.setText("Thuê Theo:");

        rd_theoNgay.setText("Theo Ngày");

        rd_theoGio.setText("Theo giờ");

        sp_time.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel20.setText("Số người:");

        txt_soLuongNguoiO.setEditable(false);
        txt_soLuongNguoiO.setBackground(new java.awt.Color(255, 255, 255));

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
                        .addComponent(txt_soLuongNguoiO))
                    .addComponent(txt_loaiPhong)
                    .addComponent(txt_ngayTra, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(txt_ngayThue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_tenPhong))
                .addContainerGap(124, Short.MAX_VALUE))
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
                                .addComponent(txt_soLuongNguoiO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jPanel8.setBackground(new java.awt.Color(205, 232, 229));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 12), new java.awt.Color(77, 134, 156))); // NOI18N
        jPanel8.setPreferredSize(new java.awt.Dimension(728, 130));

        jLabel6.setText("Họ và Tên:");

        txt_SDT.setEditable(false);
        txt_SDT.setBackground(new java.awt.Color(255, 255, 255));
        txt_SDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SDTActionPerformed(evt);
            }
        });

        txt_HvT.setEditable(false);
        txt_HvT.setBackground(new java.awt.Color(255, 255, 255));
        txt_HvT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_HvTActionPerformed(evt);
            }
        });

        jLabel8.setText("Giới tính:");

        jLabel5.setText("Số Điên Thoại:");

        jLabel17.setText("CCCD:");

        txt_CCCD.setEditable(false);
        txt_CCCD.setBackground(new java.awt.Color(255, 255, 255));
        txt_CCCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CCCDActionPerformed(evt);
            }
        });

        jLabel18.setText("Ngày Sinh:");

        txt_ngaySinh.setEditable(false);
        txt_ngaySinh.setBackground(new java.awt.Color(255, 255, 255));
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
                    .addComponent(txt_HvT)
                    .addComponent(txt_CCCD)
                    .addComponent(txt_ngaySinh)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(rd_nam)
                        .addGap(18, 18, 18)
                        .addComponent(rd_nu)
                        .addGap(0, 325, Short.MAX_VALUE))
                    .addComponent(txt_SDT))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap(17, Short.MAX_VALUE))
        );

        gr_dt.add(rd_nam);gr_dt.add(rd_nu);

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

        jPanel11.setBackground(new java.awt.Color(205, 232, 229));
        jPanel11.setPreferredSize(new java.awt.Dimension(944, 150));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Tiền Dịch Vụ:");

        txt_tienDV.setEditable(false);
        txt_tienDV.setBackground(new java.awt.Color(255, 255, 255));
        txt_tienDV.setText("0 VNĐ");
        txt_tienDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienDVActionPerformed(evt);
            }
        });

        txt_tienThuePhong.setEditable(false);
        txt_tienThuePhong.setBackground(new java.awt.Color(255, 255, 255));
        txt_tienThuePhong.setText("0 VNĐ");
        txt_tienThuePhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienThuePhongActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Thành Tiền:");

        txt_thanhTien.setEditable(false);
        txt_thanhTien.setBackground(new java.awt.Color(255, 255, 255));
        txt_thanhTien.setText("0 VNĐ");
        txt_thanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_thanhTienActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Phụ Phí:");

        txt_phuPhi.setEditable(false);
        txt_phuPhi.setBackground(new java.awt.Color(255, 255, 255));
        txt_phuPhi.setText("0 VNĐ");
        txt_phuPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_phuPhiActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Thuế(VAT):");

        txt_thue.setEditable(false);
        txt_thue.setBackground(new java.awt.Color(255, 255, 255));
        txt_thue.setText("0 VNĐ");
        txt_thue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_thueActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Thành Tiền Ban Đầu:");

        txt_thanhTienBanDau.setEditable(false);
        txt_thanhTienBanDau.setBackground(new java.awt.Color(255, 255, 255));
        txt_thanhTienBanDau.setText("0 VNĐ");
        txt_thanhTienBanDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_thanhTienBanDauActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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
                    .addComponent(txt_tienDV, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
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

        jPanel7.setBackground(new java.awt.Color(122, 178, 178));
        jPanel7.setPreferredSize(new java.awt.Dimension(728, 60));

        btn_Huy.setText("Hủy");

        btn_TraPhong.setText("Trả Phòng");

        btn_Luu.setText("Lưu");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(701, Short.MAX_VALUE)
                .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_TraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_Luu, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(btn_TraPhong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_themActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Huy;
    private javax.swing.JButton btn_Luu;
    private javax.swing.JButton btn_TraPhong;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
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
    private javax.swing.JTextField txt_soLuongNguoiO;
    private javax.swing.JTextField txt_tenPhong;
    private javax.swing.JTextField txt_thanhTien;
    private javax.swing.JTextField txt_thanhTienBanDau;
    private javax.swing.JTextField txt_thue;
    private javax.swing.JTextField txt_tienDV;
    private javax.swing.JTextField txt_tienThuePhong;
    // End of variables declaration//GEN-END:variables
}
