/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import com.toedter.calendar.JDateChooser;
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
import entity.TrangThaiPhong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;

/**
 *
 * @author M S I
 */
public class JPanel_loadThongTinThue extends javax.swing.JPanel implements ActionListener {

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

    /**
     * Creates new form JPanel_thuePhong
     */
    public JPanel_loadThongTinThue(String tenPhong, NhanVien nv, ChiTietHoaDon cthd) throws IOException, SQLException {
        initComponents();
        this.setSize(WIDTH, HEIGHT);
        nhanVien = nv;
        chiTietHoaDon = cthd;
        loadThongTin(tenPhong, cthd);
    }

    public void loadThongTin(String tenPhong, ChiTietHoaDon cthd) throws IOException, SQLException {
        rd_theoNgay.setSelected(true);
        p_dao = new Phong_DAO();
        HoaDon hd;
        Phong phong = p_dao.getPhongTheoTenPhong(tenPhong).getFirst();
        txt_loaiPhong.setText(phong.getLoaiPhong().toString());
        txt_tenPhong.setText(tenPhong);
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
        hd = hd_dao.getHoaDonTheoMaHoaDon(cthd.getHoaDon().getMaHoaDon()).getFirst();
        load_DataDV(hd.getMaHoaDon());
        KhachHang kh = kh_dao.getKHTheoMaKhachHang(hd.getKhachHang().getMaKhachHang()).getFirst();
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
        txt_thue.setText(String.valueOf(hd.getThue() * 100) + " %");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
        txt_tienThuePhong.setText(currencyFormat.format(cthd.getTongTienThuePhong()));
        txt_tienDV.setText(currencyFormat.format(hd_dao.tinhTongTienDichVu(hd.getMaHoaDon())));
        txt_thanhTienBanDau.setText(currencyFormat.format(hd_dao.tinhthanhTienBanDau(hd.getMaHoaDon())));
        hd.tinhTongThanhTienBanDau();
        hd.getKhuyenMai().setGiaTri(1);
        hd.tinhTongThanhTienPhaiTra();
        txt_thanhTien.setText(currencyFormat.format(hd.getTongThanhTienPhaiTra()));

        txt_ngayTra.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    LocalDate ngayThue = txt_ngayThue.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    cthd.setNgayNhanPhong(ngayThue);
                    if (txt_ngayTra.getDate() != null) {
                        LocalDate ngayTra = txt_ngayTra.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
            }
        });

    }

    public void addEvents() {
        btn_them.addActionListener(this);
        btn_Luu.addActionListener(this);
        btn_TraPhong.addActionListener(this);
    }

    public void load_DV() throws SQLException {
        dau_dao = new DoAnUong_DAO();
        ArrayList<DoAnUong> list = dau_dao.getAllTableDoAnUong();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");

        for (DoAnUong dau : list) {
            if (!"XXX".equals(dau.getMaDoAnUong())) {
                modelDV.addRow(new Object[]{dau.getMaDoAnUong(), dau.getTenDoAnUong(), dau.getSoLuong(), currencyFormat.format(dau.getGiaBan())});
            }
        }

        for (DoAnUong dau : list) {
            if (!dau.isLoai()) {
                if (!"XXX".equals(dau.getMaDoAnUong())) {
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
                modelDichVu.addRow(new Object[]{dau.getMaDoAnUong(), dau.getTenDoAnUong(), cthd.getSoLuong(), currencyFormat.format(dau.getGiaBan()), currencyFormat.format(cthd.getTongTienDichVu())});
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
            } catch (IOException ex) {
                Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource().equals(btn_Luu)) {
            p_dao = new Phong_DAO();
            Phong p;
            HoaDon hd = null;
            ChiTietHoaDon cthd = null;
            try {
                p = p_dao.getPhongTheoTenPhong(txt_tenPhong.getText()).getFirst();
                cthd = cthd_dao.getChiTietHoaDontheoPhong(p.getMaPhong()).getFirst();
                hd = cthd.getHoaDon();
            } catch (IOException ex) {
                Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < modelDichVu.getRowCount(); i++) {
                try {
                    cthd.setHoaDon(hd);
                    DoAnUong dau = dau_dao.getDAUTheoMaDoAnUong(modelDichVu.getValueAt(i, 0).toString()).getFirst();
                    cthd.setDoAnUong(dau);
                    p = p_dao.getPhongTheoTenPhong(txt_tenPhong.getText()).getFirst();
                    cthd.setPhong(p);
                    cthd.setSoLuong(Integer.parseInt(modelDichVu.getValueAt(i, 2).toString()));
                    cthd.setSoLuongNguoiO(Integer.parseInt(txt_soLuongNguoiO.getText()));
                    dau.setSoLuong(dau.getSoLuong() - Integer.parseInt(modelDichVu.getValueAt(i, 2).toString()));
                    dau_dao.updateDoAnUong(dau);
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
                    cthd_dao = new ChiTietHoaDon_DAO();
                    if (cthd_dao.checkDoAnUongExistInHoaDon(hd.getMaHoaDon(), dau.getMaDoAnUong()) != true) {
                        cthd_dao.createChiTietHoaDon(cthd);
                        System.out.println("Đã chạy vô đây!");
                    }

                } catch (IOException ex) {
                    Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel_thuePhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Date tra = txt_ngayTra.getDate();
                // Chuyển đổi thành LocalDate
                Instant instant1 = tra.toInstant();
                ZoneId zoneId1 = ZoneId.systemDefault();
                LocalDate ngaytra = instant1.atZone(zoneId1).toLocalDate();
                cthd_dao.updateNgayTraPhongByMaHoaDon(hd.getMaHoaDon(), ngaytra);
                cthd.setNgayTraPhong(ngaytra);
                cthd.tinhTienThuePhong();
                cthd_dao.updateTongTienThuePhongByMaHoaDon(hd.getMaHoaDon(), cthd.getTongTienThuePhong());
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource().equals(btn_TraPhong)) {
            JPanel_traPhong thongTinThue;

            try {
                thongTinThue = new JPanel_traPhong(txt_tenPhong.getText().toString(), nhanVien, chiTietHoaDon);
                JFrame thongTinThueJFrame = new JFrame("Thông tin thuê phòng");
                thongTinThueJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                thongTinThueJFrame.add(thongTinThue);
                thongTinThueJFrame.pack();
                thongTinThueJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                thongTinThueJFrame.setLocationRelativeTo(null);
                thongTinThueJFrame.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_loadThongTinThue.class.getName()).log(Level.SEVERE, null, ex);
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
        jButton4 = new javax.swing.JButton();
        btn_TraPhong = new javax.swing.JButton();
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
            .addComponent(btn_them, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jButton4.setText("Hủy");

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
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_Luu, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(btn_TraPhong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Luu;
    private javax.swing.JButton btn_TraPhong;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton jButton4;
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
