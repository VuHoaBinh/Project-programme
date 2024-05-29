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
import entity.KhuyenMai;
import entity.NhanVien;
import entity.Phong;
import entity.TrangThaiPhong;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M S I
 */
public class JPanel_traPhong extends javax.swing.JPanel implements ActionListener {

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
    private KhuyenMai km;

    /**
     * Creates new form JPanel_thuePhong
     */
    public JPanel_traPhong(JPanel_QuanLyPhong quanLyPhong, String tenPhong, NhanVien nv, ChiTietHoaDon cthd) throws IOException, SQLException {
        initComponents();
        rd_nam.setEnabled(false);
        rd_nu.setEnabled(false);
        txt_ngayThue.setEnabled(false);
        txt_ngayThue.setBackground(Color.WHITE);
        txt_ngayTra.setEnabled(false);
        txt_ngayTra.setBackground(Color.WHITE);
        this.setSize(WIDTH, HEIGHT);
        nhanVien = nv;
        chiTietHoaDon = cthd;
        this.Jqlp = quanLyPhong;
        loadThongTin(tenPhong, cthd);
    }

    public void loadThongTin(String tenPhong, ChiTietHoaDon cthd) throws IOException, SQLException {
        km_dao = new KhuyenMai_DAO();
        ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO();
        ChiTietHoaDon cthdd = cthd_dao.getChiTietHoaDontheoMa(cthd.getHoaDon().getMaHoaDon()).get(0);
        System.out.println("gui.JPanel_loadThongTinThue.loadThongTin()" + cthdd.getSoGio());
        if (cthdd.getSoGio() != 0) {
            rd_theoNgay.setSelected(false);
            rd_theoGio.setSelected(true);
            sp_time.setValue(cthdd.getSoGio());
            txt_ngayTra.setEnabled(false);
            sp_time.setEnabled(false);
            rd_theoNgay.setEnabled(false);
        } else {
            rd_theoNgay.setSelected(true);
            rd_theoGio.setSelected(false);
            sp_time.setValue(0);
            sp_time.setEnabled(false);

        }
        sp_time.setEnabled(false);
        rd_theoNgay.setEnabled(false);
        ArrayList<KhuyenMai> list = km_dao.getKhuyenMaiByDate(LocalDate.now());
        txt_maHD.setText("Mã Hóa Đơn:" + cthd.getHoaDon().getMaHoaDon());
        double max = 0;
        int index = -1;
        System.out.println(list);
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (max < list.get(i).getGiaTri() && list.get(i).isTrangThaiKhuyenMai() == true) {
                    max = list.get(i).getGiaTri();
                    index = i;
                }
            }

            txt_maKM.setText(list.get(index).getMaKhuyenMai());
            km = km_dao.getPhongTheoMaKhuyenMai(txt_maKM.getText().toString()).getFirst();
            txta_ND.setText(km.getNoiDung());
            txt_ngayBD.setText(km.getNgayBatDau().toString());
            txt_ngayKetThuc.setText(km.getNgayKetThuc().toString());
        } else {
            km = new KhuyenMai();
            km.setGiaTri(0);
        }
        p_dao = new Phong_DAO();
        HoaDon hd;
        Phong phong = p_dao.getPhongTheoTenPhong(tenPhong).getFirst();
        txt_loaiPhong.setText(phong.getLoaiPhong().toString());
        txt_tenPhong.setText(tenPhong);
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
        txt_tienThuePhong.setText(currencyFormat.format(cthd.getTongTienThuePhong() - cthd.getPhuPhi()));
        txt_tienDV.setText(currencyFormat.format(hd_dao.tinhTongTienDichVu(hd.getMaHoaDon())));
        txt_thanhTienBanDau.setText(currencyFormat.format(hd_dao.tinhthanhTienBanDau(hd.getMaHoaDon())));
        hd.tinhTongThanhTienBanDau();
        hd.getKhuyenMai().setGiaTri(km.getGiaTri());
        hd.tinhTongThanhTienPhaiTra();
        txt_thanhTien.setText(currencyFormat.format(hd.getTongThanhTienPhaiTra()));
        txt_phuPhi.setText(currencyFormat.format(cthd.getPhuPhi()));
        System.out.println(cthd);
    }

    public void addEvents() {
        btn_XacNhan.addActionListener(this);
        btn_Huy.addActionListener(this);
    }

    public void load_DataDV(String maHD) throws SQLException, IOException {
        cthd_dao = new ChiTietHoaDon_DAO();
        ArrayList<ChiTietHoaDon> list_temp = cthd_dao.getChiTietHoaDontheoMa(maHD);
        dau_dao = new DoAnUong_DAO();

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");

        for (ChiTietHoaDon cthd : list_temp) {
            DoAnUong dau = dau_dao.getDAUTheoMaDoAnUong(cthd.getDoAnUong().getMaDoAnUong()).getFirst();
            dau.setGiaBan();
            if (!"XXX".equals(dau.getMaDoAnUong())) {
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
        if (e.getSource().equals(btn_XacNhan)) {
            try {
                HoaDon hd = null;
                hd = hd_dao.getHoaDonTheoMaHoaDon(chiTietHoaDon.getHoaDon().getMaHoaDon()).getFirst();
                ArrayList<ChiTietHoaDon> lcthd = cthd_dao.getChiTietHoaDontheoMa(chiTietHoaDon.getHoaDon().getMaHoaDon());

                for (ChiTietHoaDon ct : lcthd) {
                    cthd_dao.updateTongThanhTienByMaHoaDonAndMaDoAnUong(hd.getMaHoaDon(), 1.0);
                }
                hd.tinhTongThanhTienBanDau();
                hd.setKhuyenMai(km);
                hd.tinhTongThanhTienPhaiTra();
                hd.setNgayLapHoaDon(LocalDate.now());
                hd_dao.updateHoaDon(hd);
                p_dao = new Phong_DAO();
                Phong p = p_dao.getPhongTheoMaPhong(chiTietHoaDon.getPhong().getMaPhong()).getFirst();
                p.setTrangThaiPhong(TrangThaiPhong.AVAILABLE);
                p_dao.updatePhong(p);
            } catch (IOException ex) {
                Logger.getLogger(JPanel_traPhong.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_traPhong.class.getName()).log(Level.SEVERE, null, ex);
            }
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                parentFrame.dispose(); // Tắt JFrame chứa JPanel
            }
            try {
                this.Jqlp.loadData();
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_traPhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    public void capNhatGia() {
//        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
//        ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
//
//        // Tính tổng tiền từ cột 4 của modelDichVu
//        double tongTienDV = tinhTongTienDV();
//
//        // Gán giá trị tổng tiền vào txt_tienDV
//        txt_tienDV.setText(currencyFormat.format(tongTienDV));
//
//        // Tính tổng tiền thuê phòng từ txt_tienThuePhong
//        double tienThuePhong = Double.parseDouble(txt_tienThuePhong.getText().replace(" VNĐ", "").replace(",", ""));
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        txt_tienDV = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_maKM = new javax.swing.JTextField();
        txt_ngayBD = new javax.swing.JTextField();
        txt_ngayKetThuc = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txta_ND = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        btn_Huy = new javax.swing.JButton();
        btn_XacNhan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_maHD = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 800));
        setLayout(new java.awt.BorderLayout());

        pn_thuePhong.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(644, 180));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Chi Tiết"));
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

        txt_ngayThue.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setText("Ngày Trả:");

        txt_ngayTra.setBackground(new java.awt.Color(255, 255, 255));

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
                .addGap(138, 138, 138)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(txt_ngayTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_ngayThue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_tenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
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
                .addGap(129, 129, 129)
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
                        .addGap(0, 302, Short.MAX_VALUE))
                    .addComponent(txt_SDT))
                .addGap(138, 138, 138))
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

        jPanel10.setPreferredSize(new java.awt.Dimension(728, 200));
        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin hóa đơn"));
        jPanel11.setPreferredSize(new java.awt.Dimension(844, 150));

        jLabel12.setText("Tiền Dịch Vụ:");

        txt_tienThuePhong.setEditable(false);
        txt_tienThuePhong.setBackground(new java.awt.Color(255, 255, 255));
        txt_tienThuePhong.setText("0 VNĐ");
        txt_tienThuePhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienThuePhongActionPerformed(evt);
            }
        });

        jLabel14.setText("Thành Tiền:");

        txt_thanhTien.setEditable(false);
        txt_thanhTien.setBackground(new java.awt.Color(255, 255, 255));
        txt_thanhTien.setText("0 VNĐ");
        txt_thanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_thanhTienActionPerformed(evt);
            }
        });

        jLabel9.setText("Phụ Phí:");

        txt_phuPhi.setEditable(false);
        txt_phuPhi.setBackground(new java.awt.Color(255, 255, 255));
        txt_phuPhi.setText("0 VNĐ");
        txt_phuPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_phuPhiActionPerformed(evt);
            }
        });

        jLabel10.setText("Thuế(VAT):");

        txt_thue.setEditable(false);
        txt_thue.setBackground(new java.awt.Color(255, 255, 255));
        txt_thue.setText("0 VNĐ");
        txt_thue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_thueActionPerformed(evt);
            }
        });

        jLabel13.setText("Thành Tiền Ban Đầu:");

        txt_thanhTienBanDau.setEditable(false);
        txt_thanhTienBanDau.setBackground(new java.awt.Color(255, 255, 255));
        txt_thanhTienBanDau.setText("0 VNĐ");
        txt_thanhTienBanDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_thanhTienBanDauActionPerformed(evt);
            }
        });

        jLabel19.setText("Tiền Phòng:");

        txt_tienDV.setEditable(false);
        txt_tienDV.setBackground(new java.awt.Color(255, 255, 255));
        txt_tienDV.setText("0 VNĐ");
        txt_tienDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tienDVActionPerformed(evt);
            }
        });

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
                    .addComponent(txt_thue, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(txt_thanhTienBanDau))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_tienThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_phuPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_thanhTien)
                    .addComponent(txt_tienDV, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_thue, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tienThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tienDV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_thanhTienBanDau, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_phuPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_thanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );

        jPanel10.add(jPanel11);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khuyến mãi"));

        jLabel21.setText("Ngày Kết Thúc:");

        jLabel22.setText("Mã Khuyến Mãi:");

        jLabel23.setText("Ngày Bắt Đầu:");

        txt_maKM.setEditable(false);
        txt_maKM.setBackground(new java.awt.Color(255, 255, 255));
        txt_maKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maKMActionPerformed(evt);
            }
        });

        txt_ngayBD.setEditable(false);
        txt_ngayBD.setBackground(new java.awt.Color(255, 255, 255));
        txt_ngayBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngayBDActionPerformed(evt);
            }
        });

        txt_ngayKetThuc.setEditable(false);
        txt_ngayKetThuc.setBackground(new java.awt.Color(255, 255, 255));
        txt_ngayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngayKetThucActionPerformed(evt);
            }
        });

        jLabel24.setText("Nội Dung Khuyến Mãi:");

        txta_ND.setEditable(false);
        txta_ND.setBackground(new java.awt.Color(255, 255, 255));
        txta_ND.setColumns(20);
        txta_ND.setRows(5);
        jScrollPane2.setViewportView(txta_ND);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_maKM, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ngayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_maKM, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ngayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel10.add(jPanel1);

        jPanel6.add(jPanel10, java.awt.BorderLayout.SOUTH);

        pn_thuePhong.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel7.setPreferredSize(new java.awt.Dimension(728, 60));

        btn_Huy.setText("Hủy");

        btn_XacNhan.setText("Xác Nhận");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(1217, Short.MAX_VALUE)
                .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_XacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_thuePhong.add(jPanel7, java.awt.BorderLayout.SOUTH);

        add(pn_thuePhong, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Hóa Đơn ");
        jPanel2.add(jLabel2, java.awt.BorderLayout.CENTER);

        txt_maHD.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        txt_maHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_maHD.setText("Mã Hóa Đơn:");
        jPanel2.add(txt_maHD, java.awt.BorderLayout.SOUTH);

        add(jPanel2, java.awt.BorderLayout.NORTH);
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

    private void txt_maKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maKMActionPerformed

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

    private void txt_tienDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienDVActionPerformed

    private void txt_ngayBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngayBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngayBDActionPerformed

    private void txt_ngayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngayKetThucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngayKetThucActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Huy;
    private javax.swing.JButton btn_XacNhan;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel pn_thuePhong;
    private javax.swing.JRadioButton rd_nam;
    private javax.swing.JRadioButton rd_nu;
    private javax.swing.JRadioButton rd_theoGio;
    private javax.swing.JRadioButton rd_theoNgay;
    private javax.swing.JSpinner sp_time;
    private javax.swing.JTable tbl_gioDichVu;
    private javax.swing.JTextField txt_CCCD;
    private javax.swing.JTextField txt_HvT;
    private javax.swing.JTextField txt_SDT;
    private javax.swing.JTextField txt_loaiPhong;
    private javax.swing.JLabel txt_maHD;
    private javax.swing.JTextField txt_maKM;
    private javax.swing.JTextField txt_ngayBD;
    private javax.swing.JTextField txt_ngayKetThuc;
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
    private javax.swing.JTextArea txta_ND;
    // End of variables declaration//GEN-END:variables
}
