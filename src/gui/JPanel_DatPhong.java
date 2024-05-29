/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import dao.ChiTietPhieuDatPhong_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatPhong;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import static javax.swing.UIManager.getIcon;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.log4j.chainsaw.Main;

/**
 *
 * @author M S I
 */
public class JPanel_DatPhong extends javax.swing.JPanel {

    private Phong_DAO p_dao;
    private KhachHang_DAO kh_dao;
    private PhieuDatPhong_DAO pdp_dao;
    private NhanVien_DAO nv_dao;
    private ChiTietPhieuDatPhong_DAO ctpdp_dao;
    private JPanel_QuanLyPhong pnl_qlp;
    private NhanVien nv;
    private ChiTietHoaDon cthd;
    JPanel_QuanLyPhong Jqlp;

    /**
     * Creates new form InfoPhong
     */
    public JPanel_DatPhong(JPanel_QuanLyPhong quanLyPhong, String maPhong) throws IOException, SQLException {
        initComponents();
        btn_datNgay.setIcon(new FlatSVGIcon(getClass().getResource("/icon/add.svg")));
        btn_timSDT.setIcon(new FlatSVGIcon(getClass().getResource("/icon/search_1.svg")));
        this.Jqlp = quanLyPhong;
        loadData(maPhong);
//        FlatIntelliJLaf.registerCustomDefaultsSource("style");
//        FlatIntelliJLaf.setup();
    }

    public void loadData(String maPhong) throws IOException, SQLException {
        HoaDon hd = new HoaDon();
        KhachHang_DAO kh_dao = new KhachHang_DAO();
        p_dao = new Phong_DAO();
        Phong phong = p_dao.getPhongTheoTenPhong(maPhong).getFirst();
        int soLuongKhach = phong.getSoGiuong() * 2;
        if (phong.isGiuongPhu() == true) {
            soLuongKhach += 1;
        }
        String[] str = new String[soLuongKhach];
        String stt;
        for (int i = 1; i <= soLuongKhach; i++) {
            stt = Integer.toString(i);
            str[i - 1] = stt;
        }

        cb_soLuongKhach.setModel(new javax.swing.DefaultComboBoxModel<>(str));
        nv_dao = new NhanVien_DAO();
        nv = nv_dao.getNhanVienTheoTen(nv_dao.getNhanVienDangNhap()).get(0);
        pnl_qlp = new JPanel_QuanLyPhong(nv);
        pdp_dao = new PhieuDatPhong_DAO();
        ctpdp_dao = new ChiTietPhieuDatPhong_DAO();
        txt_tenNV.setText(nv_dao.getNhanVienDangNhap());
        txt_maPhieu.setText(genMaPDP());
        txt_phong.setText(phong.getTenPhong());
        LocalDate ngayHienTai = LocalDate.now();
        txt_ngayDatPhong.setText(ngayHienTai.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        cthd = new ChiTietHoaDon();
        txt_thue.setText(String.valueOf(hd.getThue() * 100) + " %");
        cb_soLuongKhach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btn_ngayTraPhong.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày trả trước khi chọn số người.");
                    cb_soLuongKhach.setSelectedIndex(-1); // Reset selection
                    return;
                }
                if (btn_ngayTraPhong != null) {
                    LocalDate ngayTra = btn_ngayTraPhong.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    Date date1 = java.sql.Date.valueOf(ngayTra);
                    btn_ngayTraPhong.setDate(date1);
                    if (!btn_ngayTraPhong.getDate().toString().equals("")) {
                        LocalDate ngayThue = btn_ngayNhanPhong.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                        cthd.setNgayNhanPhong(ngayThue);
                        cthd.setNgayTraPhong(ngayTra);
                        cthd.setPhong(phong);
                        cthd.tinhTienThuePhong(phong, true);
                        double tienThuePhong = cthd.getTongTienThuePhong();
                        // Định dạng số tiền thành chuỗi tiền tệ
                        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                        ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
                        String tienThuePhongFormatted = currencyFormat.format(tienThuePhong);
                        txt_tienThuePhong.setText(tienThuePhongFormatted);
                    }
                }
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedItem = (String) comboBox.getSelectedItem();
                int soNguoi_Choosed = Integer.parseInt(selectedItem);
                Phong phong;
                try {
                    p_dao = new Phong_DAO();
                    phong = p_dao.getPhongTheoTenPhong(txt_phong.getText()).getFirst();
                    int max = 0, max_st = 0;
                    max = phong.getSoGiuong() * 2;
                    if (phong.isGiuongPhu() && phong.getLoaiPhong().getTenLoai() != 5) {
                        max_st = max + 1;
                    }
                    double phuPhi = 0;
                    System.out.println(soNguoi_Choosed + "CCCC" + max_st);
                    if (phong.getLoaiPhong().getTenLoai() == 1 || phong.getLoaiPhong().getTenLoai() == 2) {
                        if (soNguoi_Choosed == max_st) {
                            LocalDate ngayThue = btn_ngayNhanPhong.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            LocalDate ngayTra = btn_ngayTraPhong.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
                            LocalDate ngayThue = btn_ngayNhanPhong.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            LocalDate ngayTra = btn_ngayTraPhong.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
    }

    public PhieuDatPhong taoPhieu() throws SQLException, IOException {
        String ma = txt_maPhieu.getText().toString();
        String tenKH = txt_tenKhachHang.getText().toString();
        KhachHang kh = kh_dao.getKhachHangTheoTen(tenKH);
        String tenNV = txt_tenNV.getText().toString();
        NhanVien nv = nv_dao.getNhanVienTheoTen(tenNV).get(0);
        int soLuongKhach = Integer.parseInt((String) cb_soLuongKhach.getSelectedItem());
        LocalDate ngayDatPhong = LocalDate.now();
        LocalDate ngayNhanPhong = convertToLocalDate(btn_ngayNhanPhong.getDate());
        LocalDate ngayTraPhong = convertToLocalDate(btn_ngayTraPhong.getDate());

        return new PhieuDatPhong(ma, kh, nv, soLuongKhach, ngayDatPhong, ngayNhanPhong, ngayTraPhong);
    }

    public ChiTietPhieuDatPhong taoChiTietPDP() throws IOException, SQLException {
        String ma = txt_maPhieu.getText().toString();
        PhieuDatPhong pdp = pdp_dao.getPhieuDatPhongMoiNhat();
        String maPhong = txt_phong.getText().toString();
        Phong phong = p_dao.getPhongTheoTenPhong(maPhong).getFirst();

        return new ChiTietPhieuDatPhong(new PhieuDatPhong(ma), phong, true);
    }

    public void capNhatGia() throws IOException, SQLException {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        ((DecimalFormat) currencyFormat).applyPattern("###,### VNĐ");
        double tongTienDV = 0;
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_maPhieu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_tenNV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_phong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_sdtKH = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_ngayDatPhong = new javax.swing.JTextField();
        txt_tenKhachHang = new javax.swing.JTextField();
        btn_timSDT = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btn_datNgay = new javax.swing.JButton();
        cb_soLuongKhach = new javax.swing.JComboBox<>();
        btn_ngayNhanPhong = new com.toedter.calendar.JDateChooser();
        btn_ngayTraPhong = new com.toedter.calendar.JDateChooser();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txt_tienDV = new javax.swing.JTextField();
        txt_tienThuePhong = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_thanhTien = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_phuPhi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_thue = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_thanhTienBanDau = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 153, 153));
        setPreferredSize(new java.awt.Dimension(900, 650));
        setVerifyInputWhenFocusTarget(false);
        setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Đặt Phòng");
        add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel1.setPreferredSize(new java.awt.Dimension(931, 200));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jLabel2.setText("Mã phiếu đặt phòng:");

        txt_maPhieu.setEditable(false);
        txt_maPhieu.setEnabled(false);
        txt_maPhieu.setFocusable(false);
        txt_maPhieu.setRequestFocusEnabled(false);
        txt_maPhieu.setVerifyInputWhenFocusTarget(false);

        jLabel3.setText("Nhân viên:");

        txt_tenNV.setEditable(false);
        txt_tenNV.setEnabled(false);
        txt_tenNV.setFocusable(false);
        txt_tenNV.setRequestFocusEnabled(false);

        jLabel4.setText("Số lượng khách:");

        txt_phong.setEditable(false);
        txt_phong.setEnabled(false);
        txt_phong.setFocusable(false);
        txt_phong.setRequestFocusEnabled(false);

        jLabel5.setText("Số điện thoại KH:");

        jLabel6.setText("Phòng:");

        jLabel7.setText("Khách hàng:");

        jLabel8.setText("Ngày nhận phòng:");

        jLabel9.setText("Ngày đặt phòng:");

        txt_ngayDatPhong.setEditable(false);
        txt_ngayDatPhong.setEnabled(false);
        txt_ngayDatPhong.setFocusable(false);
        txt_ngayDatPhong.setRequestFocusEnabled(false);

        txt_tenKhachHang.setEditable(false);
        txt_tenKhachHang.setEnabled(false);
        txt_tenKhachHang.setFocusable(false);
        txt_tenKhachHang.setRequestFocusEnabled(false);

        btn_timSDT.setIcon(new FlatSVGIcon("./icon/search_1.svg"));
        btn_timSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_timSDTActionPerformed(evt);
            }
        });

        jLabel10.setText("Ngày trả phòng:");

        btn_datNgay.setIcon(new FlatSVGIcon("./icon/add.svg"));
        btn_datNgay.setText("Đặt Ngay");
        btn_datNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_datNgayActionPerformed(evt);
            }
        });

        cb_soLuongKhach.setPreferredSize(new java.awt.Dimension(50, 22));

        btn_ngayNhanPhong.setDateFormatString("dd-MM-yyyy");
        btn_ngayNhanPhong.setPreferredSize(new java.awt.Dimension(64, 22));
        btn_ngayNhanPhong.setRequestFocusEnabled(false);

        btn_ngayTraPhong.setDateFormatString("dd-MM-yyyy");

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

        jLabel11.setText("Phụ Phí:");

        txt_phuPhi.setText("0 VNĐ");
        txt_phuPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_phuPhiActionPerformed(evt);
            }
        });

        jLabel13.setText("Thuế(VAT):");

        txt_thue.setText("0 VNĐ");
        txt_thue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_thueActionPerformed(evt);
            }
        });

        jLabel15.setText("Thành Tiền Ban Đầu:");

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
                    .addComponent(jLabel13)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_thue, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(txt_thanhTienBanDau))
                .addGap(33, 33, 33)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_phuPhi, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(txt_tienThuePhong))
                .addGap(32, 32, 32)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_tienDV, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(txt_thanhTien))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_thue, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tienThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tienDV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_thanhTienBanDau, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_phuPhi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_thanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );

        jPanel10.add(jPanel11);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_datNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txt_maPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txt_phong, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cb_soLuongKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btn_ngayNhanPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_ngayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_sdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_timSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngayDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_maPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_timSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_phong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_sdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_soLuongKhach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_ngayDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_ngayNhanPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ngayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(285, 285, 285)
                .addComponent(btn_datNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(315, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(130, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel2);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_timSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_timSDTActionPerformed
        try {
            // TODO add your handling code here:
            kh_dao = new KhachHang_DAO();
            String sdt = txt_sdtKH.getText().trim();
            if (sdt.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Bạn chưa nhập số điện thoại");
                txt_sdtKH.requestFocus();
            } else if (!txt_sdtKH.getText().matches("^0\\d{9}$")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại chỉ có 10 kí tự số và bắt đầu bằng số 0");
                txt_sdtKH.requestFocus();
            } else if (kh_dao.getKhachHangTheoMa(sdt) == null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm khách hàng không?", "Chú ý không có khách hàng này", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    txt_sdtKH.requestFocus();
                    JPanel_ThemKHNhanh themKHNhanhPanel;
                    themKHNhanhPanel = new JPanel_ThemKHNhanh();
                    JFrame themKHFrame = new JFrame("Thêm Khách Hàng Nhanh");
                    themKHFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    themKHFrame.add(themKHNhanhPanel);
                    themKHFrame.pack();
                    themKHFrame.setLocationRelativeTo(null);
                    themKHFrame.setVisible(true);
                } else {
                    txt_sdtKH.requestFocus();
                }
            } else {
                txt_tenKhachHang.setText(kh_dao.getKhachHangTheoMa(sdt).getHoTenKhachHang());
                txt_sdtKH.requestFocus();
            }
        } catch (IOException ex) {
            Logger.getLogger(JPanel_DatPhong.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JPanel_DatPhong.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_timSDTActionPerformed

    private void btn_datNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datNgayActionPerformed
        boolean kt = true;

        try {
            String maPhieu = txt_maPhieu.getText();
            String qrCodeFilePath = "data_qr/" + maPhieu + ".png"; // Đường dẫn tương đối cho QR Code

// Tạo và lưu file QR Code
            FileOutputStream fout = null;
            try {
                ByteArrayOutputStream out = QRCode.from(maPhieu).to(ImageType.PNG).stream();
                File qrFile = new File(qrCodeFilePath);
                qrFile.getParentFile().mkdirs(); // Tạo thư mục nếu nó chưa tồn tại
                fout = new FileOutputStream(qrFile);
                fout.write(out.toByteArray());
                fout.flush();
                System.out.println("QR Code đã được tạo và lưu tại: " + qrCodeFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fout != null) {
                    try {
                        fout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            LocalDate now = LocalDate.now();
            LocalDate ngayNhanPhong = convertToLocalDate(btn_ngayNhanPhong.getDate());
            LocalDate ngayTraPhong = convertToLocalDate(btn_ngayTraPhong.getDate());

            if (!ngayNhanPhong.isAfter(now)) {
                JOptionPane.showMessageDialog(null, "Ngày nhận phòng phải sau ngày hiện tại");
                kt = false;
            }

            if (!ngayTraPhong.isAfter(ngayNhanPhong)) {
                JOptionPane.showMessageDialog(null, "Ngày trả phòng dự kiến phải sau ngày nhận phòng");
                kt = false;
            }

            if (txt_tenKhachHang.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Chưa có khách hàng");
                txt_sdtKH.requestFocus();
                kt = false;
            }

            if (kt) {
                try {
                    PhieuDatPhong pdp = taoPhieu();
                    pdp_dao.createPhieuDatPhong(pdp);
                    String maPhong = txt_phong.getText();
                    Phong phong = p_dao.getPhongTheoTenPhong(maPhong).getFirst();
                    ChiTietPhieuDatPhong ctpdp = taoChiTietPDP();
                    System.out.println("sâdasdasdasd" + ctpdp);
                    ctpdp_dao.create(ctpdp);
                    phong.setTrangThaiPhong(TrangThaiPhong.BOOKED);
                    p_dao.updatePhong(phong);
                    JOptionPane.showMessageDialog(null, "Đặt thành công");
                    int comfirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in phiếu đặt phòng?", "Chú ý", JOptionPane.YES_NO_OPTION);

                    if (comfirm == JOptionPane.YES_OPTION) {
                        String toEmail = pdp.getKhachHang().getEmai(); // Địa chỉ email của người nhận
                        String subject = "Đặt phòng khách sạn AN NHIÊN - QRCode"; // Chủ đề của email
                        String body = "Thông đặt phòng khách sạn An Nhiên - Đây là phiếu đặt phòng của bạn: " + txt_tenKhachHang.getText() + "( Thời gian nhận phòng: " + txt_ngayDatPhong.getText() + " ) ";

                        // Tạo tệp PDF tạm thời
                        String pdfFileName = "PhieuDatPhong.pdf";
                        String pdfFilePath = "data_qr/PhieuDatPhong.pdf"; // Đường dẫn tương đối cho tệp PDF

                        Document document = new Document();
                        FileOutputStream fos = new FileOutputStream(pdfFilePath);
                        PdfWriter.getInstance(document, fos);;

                        // Load font from resources
                        InputStream fontStream = Main.class.getClassLoader().getResourceAsStream("data/font/font-times-new-roman.ttf");
                        if (fontStream == null) {
                            throw new FileNotFoundException("Font not found");
                        }
                        BaseFont unicodeFont = BaseFont.createFont("font-times-new-roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, true, fontStream.readAllBytes(), null);
                        com.itextpdf.text.Font font = new com.itextpdf.text.Font(unicodeFont, 14);

                        document.open();

                        // Add hotel details
                        Paragraph hotelName = new Paragraph("Khách sạn: An Nhiên", font);
                        document.add(hotelName);

                        Chunk address = new Chunk("Address: Số 102-103-104-105-106 Nguyễn Huệ, phường Bến Nghé, Quận 1, Tp.HCM", font);
                        Chunk phone = new Chunk("Phone: 0988-123-789", font);
                        Chunk email = new Chunk("Email: annhien.hotel@annhien.com", font);

                        Paragraph hotelDetails = new Paragraph();
                        hotelDetails.add(address);
                        hotelDetails.add(Chunk.TABBING);
                        hotelDetails.add(phone);
                        hotelDetails.add(Chunk.TABBING);
                        hotelDetails.add(email);

                        document.add(hotelDetails);

                        // Add date and time
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        LocalDateTime currentDateTime = LocalDateTime.now();
                        String formattedDateTime = currentDateTime.format(formatter);
                        Paragraph date = new Paragraph("Ngày lập phiếu: " + formattedDateTime, font);
                        document.add(date);

                        // Generate and add QR code
                        Image image = Image.getInstance(qrCodeFilePath);
                        image.scaleToFit(200, 200);
                        image.setAlignment(Element.ALIGN_CENTER);
                        document.add(image);

                        document.close();
                        fos.close();

                        // Gửi email với tệp PDF đính kèm
                        EmailSender.sendEmailWithAttachment(toEmail, subject, body, pdfFilePath);
                        JOptionPane.showMessageDialog(null, "Email đã được gửi thành công");

                        // Mở tệp PDF sau khi đã tạo và gửi qua email
                        try {
                            if (Desktop.isDesktopSupported()) {
                                File pdfFile = new File(pdfFilePath);
                                Desktop.getDesktop().open(pdfFile);
                            } else {
                                System.out.println("Môi trường không hỗ trợ mở tệp PDF.");
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(JPanel_DatPhong.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Load lại dữ liệu
                try {
                    this.Jqlp.loadData();
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel_DatPhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_datNgayActionPerformed

    private void txt_tienDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienDVActionPerformed

    private void txt_tienThuePhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tienThuePhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienThuePhongActionPerformed

    private void txt_thanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_thanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_thanhTienActionPerformed

    private void txt_phuPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_phuPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_phuPhiActionPerformed

    private void txt_thueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_thueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_thueActionPerformed

    private void txt_thanhTienBanDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_thanhTienBanDauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_thanhTienBanDauActionPerformed
    public String genMaPDP() {
//    Bao gồm 19 ký tự có dạng MPDPhhppddmmyyyyxxx.
//    Trong đó:
//    - hh là giờ lập phiếu
//    - pp: là phút lập phiếu
//    - dd: là ngày lập phiếu
//    - mm: là tháng lập phiếu
//    - yyyy: là năm lập phiếu
//    - xxx: là ba số nguyên dương được hệ thống phát sinh tăng dần.
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String hh = String.format("%02d", hour);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        String pp = String.format("%02d", minute);
        if (minute < 10) {
            pp = String.format("0%01d", minute);
        }
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String dd = String.format("%02d", day);
        if (day < 10) {
            dd = String.format("0%01d", day);
        }
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        String mm = String.format("%02d", month);
        if (month < 10) {
            mm = String.format("0%01d", month);
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String yyyy = String.format("%04d", year);
        int soLuong = pdp_dao.demSoLuongPDPTheoMaMau(year);
        String formattedNumber = String.format("%03d", soLuong);
        System.out.println(soLuong);
        return String.format("MPDP" + hh + pp + dd + mm + yyyy + formattedNumber);
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_datNgay;
    private com.toedter.calendar.JDateChooser btn_ngayNhanPhong;
    private com.toedter.calendar.JDateChooser btn_ngayTraPhong;
    private javax.swing.JButton btn_timSDT;
    private javax.swing.JComboBox<String> cb_soLuongKhach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JTextField txt_maPhieu;
    private javax.swing.JTextField txt_ngayDatPhong;
    private javax.swing.JTextField txt_phong;
    private javax.swing.JTextField txt_phuPhi;
    private javax.swing.JTextField txt_sdtKH;
    private javax.swing.JTextField txt_tenKhachHang;
    private javax.swing.JTextField txt_tenNV;
    private javax.swing.JTextField txt_thanhTien;
    private javax.swing.JTextField txt_thanhTienBanDau;
    private javax.swing.JTextField txt_thue;
    private javax.swing.JTextField txt_tienDV;
    private javax.swing.JTextField txt_tienThuePhong;
    // End of variables declaration//GEN-END:variables
}
