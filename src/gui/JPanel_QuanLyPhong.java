/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import connectDB.ConnectDB;
import dao.ChiTietHoaDon_DAO;
import dao.ChiTietPhieuDatPhong_DAO;
import dao.HoaDon_DAO;
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
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M S I
 */
public class JPanel_QuanLyPhong extends javax.swing.JPanel implements ActionListener, MouseListener {

    private Phong_DAO p_dao;
    private final JPopupMenu roomPopupMenu;
    private boolean isRoomSelected = false;
    private NhanVien nhanVien;
    private HoaDon hoadon;
    private HoaDon_DAO hd_dao;
    private ChiTietHoaDon_DAO cthd_dao;
    private ChiTietHoaDon cthd;
    private ChiTietPhieuDatPhong_DAO ctpdp;
    private PhieuDatPhong_DAO pdp_dao;

    /**
     * Creates new form JPanel_QuanLyNhanVien
     */
    public JPanel_QuanLyPhong(NhanVien nv) throws SQLException {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
        }
        nhanVien = nv;
        initComponents();
        loadData();

        // Tạo menu ngữ cảnh cho phòng
        roomPopupMenu = new JPopupMenu();
        JMenuItem mn_xemChiTiet = new JMenuItem("Xem thông tin phòng");
        JMenuItem mn_ThuePhong = new JMenuItem("Thuê phòng cho khách");
        JMenuItem mn_loadThongTinThue = new JMenuItem("Xem chi tiết phòng");
        JMenuItem datPhong = new JMenuItem("Đặt phòng");
        datPhong.addActionListener(this);

        mn_xemChiTiet.addActionListener(this);
        mn_ThuePhong.addActionListener(this);
        mn_loadThongTinThue.addActionListener(this);
        roomPopupMenu.add(mn_xemChiTiet);
        roomPopupMenu.add(mn_ThuePhong);
        roomPopupMenu.add(mn_loadThongTinThue);
        roomPopupMenu.add(datPhong);
        KeyStroke ctrlRKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(ctrlRKeyStroke, "Redraw");
        ActionMap actionMap = getActionMap();
        actionMap.put("Redraw", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thực hiện vẽ lại JFrame
                try {
                    loadData();
                } catch (SQLException ex) {
                    Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void loadData() throws SQLException {
        try {
            removeAllRooms();
            // Lấy danh sách các phòng từ cơ sở dữ liệu
            p_dao = new Phong_DAO();
            ArrayList<Phong> dsPhong = p_dao.getAllTablePhong(); // Lấy danh sách phòng của tầng 1

            for (Phong ph : dsPhong) {
                if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 1) {
                    JPanel roomPanel = createRoomPanel(ph);
                    pn_Tang1.add(roomPanel);
                }
                if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 2) {
                    JPanel roomPanel = createRoomPanel(ph);
                    pn_Tang2.add(roomPanel);
                }
                if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 3) {
                    JPanel roomPanel = createRoomPanel(ph);
                    pn_Tang3.add(roomPanel);
                }
                if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 4) {
                    JPanel roomPanel = createRoomPanel(ph);
                    pn_Tang4.add(roomPanel);
                }
                if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 5) {
                    JPanel roomPanel = createRoomPanel(ph);
                    pn_Tang5.add(roomPanel);
                }
            }
            for (Phong ph : dsPhong) {
                System.out.println(ph);
                if (ph.getTrangThaiPhong().getTenTrangThai() == 2) {

                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 1) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang1DT.add(roomPanel);
                    }
                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 2) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang2DT.add(roomPanel);
                    }
                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 3) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang3DT.add(roomPanel);
                    }
                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 4) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang4DT.add(roomPanel);
                    }
                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 5) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang5DT.add(roomPanel);
                    }
                }

            }
            for (Phong ph : dsPhong) {
                System.out.println(ph.getTrangThaiPhong().getTenTrangThai());
                if (ph.getTrangThaiPhong().getTenTrangThai() == 3) {

                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 1) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang1CS.add(roomPanel);
                    }
                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 2) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang2CS.add(roomPanel);
                    }
                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 3) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang3CS.add(roomPanel);
                    }
                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 4) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang4CS.add(roomPanel);
                    }
                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 5) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang5CS.add(roomPanel);
                    }
                }
                System.out.println("danh sach phong: " + ph);

            }
            for (Phong ph : dsPhong) {
                if (ph.getTrangThaiPhong().getTenTrangThai() == 1) {

                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 1) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang1DTR.add(roomPanel);
                    }
                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 2) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang2DTR.add(roomPanel);
                    }
                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 3) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang3DTR.add(roomPanel);
                    }
                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 4) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang4DTR.add(roomPanel);
                    }
                    if (Integer.parseInt(ph.getMaPhong().substring(0, 2)) == 5) {
                        JPanel roomPanel = createRoomPanel(ph);
                        pn_Tang5DTR.add(roomPanel);
                    }
                }

            }
        } catch (SQLException ex) {
            System.out.println("Loi ne!!");
            Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JPanel createRoomPanel(Phong phong) {
        JPanel roomPanel = new JPanel(new GridLayout(4, 1)); // Sử dụng GridLayout để chia panel thành 3 hàng

        // Label chứa thông tin mã phòng
        JLabel lblMaPhong = new JLabel("Tên Phòng: " + phong.getTenPhong());
        lblMaPhong.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Đặt font in đậm và kích thước là 18
        lblMaPhong.setHorizontalAlignment(SwingConstants.CENTER);
//         Label chứa thông tin trạng thái

        JLabel lblDT = new JLabel("Diện tích: " + String.valueOf(phong.getDienTich()) + " m^2");
        lblDT.setHorizontalAlignment(SwingConstants.LEFT);
        JLabel lblSG = new JLabel("Số Giường: " + phong.getSoGiuong());
        lblSG.setHorizontalAlignment(SwingConstants.LEFT);

        ///////////////////////////// Check Enum State     ///////////////////////////////
        String trangThaiString = null;
        if ("OCCUPIED".equals(phong.getTrangThaiPhong().name())) {
            trangThaiString = "Đã thuê";
        }
        if ("AVAILABLE".equals(phong.getTrangThaiPhong().name())) {
            trangThaiString = "Sẵn sàng";
        }
        if ("BOOKED".equals(phong.getTrangThaiPhong().name())) {
            trangThaiString = "Đã Đặt";
        }
        if ("UNAVAILABLE".equals(phong.getTrangThaiPhong().name())) {
            trangThaiString = "Đã khóa";
        }

        JLabel lblTrangThai = new JLabel("Trạng thái: " + trangThaiString);
        lblTrangThai.setHorizontalAlignment(SwingConstants.CENTER);

        // Thêm các label vào panel
        roomPanel.add(lblMaPhong);
        roomPanel.add(lblDT);
        roomPanel.add(lblSG);
        roomPanel.add(lblTrangThai);

        // Thiết lập border và padding cho panel
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        roomPanel.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        roomPanel.setBackground(Color.RED);
//         Thiết lập màu nền cho panel tương ứng với từng trạng thái
        switch (phong.getTrangThaiPhong().name()) {
            case "BOOKED":
                roomPanel.setBackground(Color.YELLOW); // Màu nền cho trạng thái BOOKED
                break;
            case "OCCUPIED":
                roomPanel.setBackground(Color.RED); // Màu nền cho trạng thái OCCUPIED
                break;
            case "AVAILABLE":
                roomPanel.setBackground(Color.GREEN); // Màu nền cho trạng thái AVAILABLE
                break;
            case "UNAVAILABLE":
                roomPanel.setBackground(Color.GRAY); // Màu nền cho trạng thái UNAVAILABLE
                break;
            default:
                roomPanel.setBackground(Color.WHITE); // Mặc định màu nền là trắng
                break;
        }

        // Thêm sự kiện cho panel nếu cần
        roomPanel.addMouseListener(this);
        return roomPanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        search_Engine = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pn_Tang1 = new javax.swing.JPanel();
        pn_Tang2 = new javax.swing.JPanel();
        pn_Tang3 = new javax.swing.JPanel();
        pn_Tang4 = new javax.swing.JPanel();
        pn_Tang5 = new javax.swing.JPanel();
        pn_Tang6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        pn_Tang1DTR = new javax.swing.JPanel();
        pn_Tang2DTR = new javax.swing.JPanel();
        pn_Tang3DTR = new javax.swing.JPanel();
        pn_Tang4DTR = new javax.swing.JPanel();
        pn_Tang5DTR = new javax.swing.JPanel();
        pn_Tang12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        pn_Tang1CS = new javax.swing.JPanel();
        pn_Tang2CS = new javax.swing.JPanel();
        pn_Tang3CS = new javax.swing.JPanel();
        pn_Tang4CS = new javax.swing.JPanel();
        pn_Tang5CS = new javax.swing.JPanel();
        pn_Tang18 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        pn_Tang1DT = new javax.swing.JPanel();
        pn_Tang2DT = new javax.swing.JPanel();
        pn_Tang3DT = new javax.swing.JPanel();
        pn_Tang4DT = new javax.swing.JPanel();
        pn_Tang5DT = new javax.swing.JPanel();
        pn_Tang24 = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(931, 800));
        setLayout(new java.awt.BorderLayout());

        search_Engine.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("Có sẵn");

        jLabel2.setText("Đã đặt ");

        jTextField1.setBackground(new java.awt.Color(255, 0, 0));
        jTextField1.setForeground(new java.awt.Color(255, 51, 0));

        jTextField2.setBackground(new java.awt.Color(0, 255, 51));
        jTextField2.setForeground(new java.awt.Color(0, 255, 102));

        jLabel5.setText("Đã Thuê");

        jTextField3.setBackground(new java.awt.Color(255, 255, 0));
        jTextField3.setForeground(new java.awt.Color(0, 255, 102));

        jTextField4.setBackground(new java.awt.Color(0, 0, 0));
        jTextField4.setForeground(new java.awt.Color(0, 255, 102));

        jLabel6.setText("Khóa");

        javax.swing.GroupLayout search_EngineLayout = new javax.swing.GroupLayout(search_Engine);
        search_Engine.setLayout(search_EngineLayout);
        search_EngineLayout.setHorizontalGroup(
            search_EngineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_EngineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        search_EngineLayout.setVerticalGroup(
            search_EngineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_EngineLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(search_EngineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(search_EngineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        add(search_Engine, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        pn_Tang1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel1.add(pn_Tang1);

        pn_Tang2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel1.add(pn_Tang2);

        pn_Tang3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel1.add(pn_Tang3);

        pn_Tang4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel1.add(pn_Tang4);

        pn_Tang5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 5", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel1.add(pn_Tang5);

        pn_Tang6.setLayout(new java.awt.BorderLayout());
        jPanel1.add(pn_Tang6);

        jScrollPane1.setViewportView(jPanel1);

        jTabbedPane2.addTab("Danh Sách Phòng", jScrollPane1);

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS));

        pn_Tang1DTR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang1DTR.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel5.add(pn_Tang1DTR);

        pn_Tang2DTR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang2DTR.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel5.add(pn_Tang2DTR);

        pn_Tang3DTR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang3DTR.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel5.add(pn_Tang3DTR);

        pn_Tang4DTR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang4DTR.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel5.add(pn_Tang4DTR);

        pn_Tang5DTR.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 5", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang5DTR.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel5.add(pn_Tang5DTR);

        pn_Tang12.setLayout(new java.awt.BorderLayout());
        jPanel5.add(pn_Tang12);

        jScrollPane2.setViewportView(jPanel5);

        jTabbedPane2.addTab("Danh Sách Phòng Được Đặt Trước", jScrollPane2);

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.Y_AXIS));

        pn_Tang1CS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang1CS.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel6.add(pn_Tang1CS);

        pn_Tang2CS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang2CS.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel6.add(pn_Tang2CS);

        pn_Tang3CS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang3CS.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel6.add(pn_Tang3CS);

        pn_Tang4CS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang4CS.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel6.add(pn_Tang4CS);

        pn_Tang5CS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 5", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang5CS.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel6.add(pn_Tang5CS);

        pn_Tang18.setLayout(new java.awt.BorderLayout());
        jPanel6.add(pn_Tang18);

        jScrollPane3.setViewportView(jPanel6);

        jTabbedPane2.addTab("Danh Sách Phòng Có Sẵn", jScrollPane3);

        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.Y_AXIS));

        pn_Tang1DT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang1DT.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel7.add(pn_Tang1DT);

        pn_Tang2DT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang2DT.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel7.add(pn_Tang2DT);

        pn_Tang3DT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 3", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang3DT.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel7.add(pn_Tang3DT);

        pn_Tang4DT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 4", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang4DT.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel7.add(pn_Tang4DT);

        pn_Tang5DT.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tầng 5", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        pn_Tang5DT.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jPanel7.add(pn_Tang5DT);

        pn_Tang24.setLayout(new java.awt.BorderLayout());
        jPanel7.add(pn_Tang24);

        jScrollPane4.setViewportView(jPanel7);

        jTabbedPane2.addTab("Danh Sách Phòng Đang Được Thuê", jScrollPane4);

        add(jTabbedPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JPanel pn_Tang1;
    private javax.swing.JPanel pn_Tang12;
    private javax.swing.JPanel pn_Tang18;
    private javax.swing.JPanel pn_Tang1CS;
    private javax.swing.JPanel pn_Tang1DT;
    private javax.swing.JPanel pn_Tang1DTR;
    private javax.swing.JPanel pn_Tang2;
    private javax.swing.JPanel pn_Tang24;
    private javax.swing.JPanel pn_Tang2CS;
    private javax.swing.JPanel pn_Tang2DT;
    private javax.swing.JPanel pn_Tang2DTR;
    private javax.swing.JPanel pn_Tang3;
    private javax.swing.JPanel pn_Tang3CS;
    private javax.swing.JPanel pn_Tang3DT;
    private javax.swing.JPanel pn_Tang3DTR;
    private javax.swing.JPanel pn_Tang4;
    private javax.swing.JPanel pn_Tang4CS;
    private javax.swing.JPanel pn_Tang4DT;
    private javax.swing.JPanel pn_Tang4DTR;
    private javax.swing.JPanel pn_Tang5;
    private javax.swing.JPanel pn_Tang5CS;
    private javax.swing.JPanel pn_Tang5DT;
    private javax.swing.JPanel pn_Tang5DTR;
    private javax.swing.JPanel pn_Tang6;
    private javax.swing.JPanel search_Engine;
    // End of variables declaration//GEN-END:variables
    private NhanVien_DAO nv_dao = new NhanVien_DAO();
    private DefaultTableModel modelNhanVien;
    javax.swing.ButtonGroup gr_gt = new javax.swing.ButtonGroup();
    javax.swing.ButtonGroup gr_tt = new javax.swing.ButtonGroup();
    javax.swing.ButtonGroup gr_cv = new javax.swing.ButtonGroup();

    private void removeAllRooms() {
        pn_Tang1.removeAll();
        pn_Tang2.removeAll();
        pn_Tang3.removeAll();
        pn_Tang4.removeAll();
        pn_Tang5.removeAll();

        pn_Tang1.revalidate();
        pn_Tang1.repaint();
        pn_Tang2.revalidate();
        pn_Tang2.repaint();
        pn_Tang3.revalidate();
        pn_Tang3.repaint();
        pn_Tang4.revalidate();
        pn_Tang4.repaint();
        pn_Tang5.revalidate();
        pn_Tang5.repaint();

        pn_Tang1DT.removeAll();
        pn_Tang2DT.removeAll();
        pn_Tang3DT.removeAll();
        pn_Tang4DT.removeAll();
        pn_Tang5DT.removeAll();

        pn_Tang1DT.revalidate();
        pn_Tang1DT.repaint();
        pn_Tang2DT.revalidate();
        pn_Tang2DT.repaint();
        pn_Tang3DT.revalidate();
        pn_Tang3DT.repaint();
        pn_Tang4DT.revalidate();
        pn_Tang4DT.repaint();
        pn_Tang5DT.revalidate();
        pn_Tang5DT.repaint();

        pn_Tang1DTR.removeAll();
        pn_Tang2DTR.removeAll();
        pn_Tang3DTR.removeAll();
        pn_Tang4DTR.removeAll();
        pn_Tang5DTR.removeAll();

        pn_Tang1DTR.revalidate();
        pn_Tang1DTR.repaint();
        pn_Tang2DTR.revalidate();
        pn_Tang2DTR.repaint();
        pn_Tang3DTR.revalidate();
        pn_Tang3DTR.repaint();
        pn_Tang4DTR.revalidate();
        pn_Tang4DTR.repaint();
        pn_Tang5DTR.revalidate();
        pn_Tang5DTR.repaint();

        pn_Tang1CS.removeAll();
        pn_Tang2CS.removeAll();
        pn_Tang3CS.removeAll();
        pn_Tang4CS.removeAll();
        pn_Tang5CS.removeAll();

        pn_Tang1CS.revalidate();
        pn_Tang1CS.repaint();
        pn_Tang2CS.revalidate();
        pn_Tang2CS.repaint();
        pn_Tang3CS.revalidate();
        pn_Tang3CS.repaint();
        pn_Tang4CS.revalidate();
        pn_Tang4CS.repaint();
        pn_Tang5CS.revalidate();
        pn_Tang5CS.repaint();
    }

    public void refreshData() {
        try {
            removeAllRooms();
            loadData(); // Gọi lại loadData để tải lại dữ liệu danh sách phòng
            this.revalidate();
            this.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Xử lý các sự kiện từ menu ngữ cảnh
        if (e.getActionCommand().equals("Xem thông tin phòng")) {
            // Lấy tên phòng từ JPanel chứa thông tin phòng
            JPanel roomPanelClicked = (JPanel) roomPopupMenu.getInvoker(); // JPanel chứa thông tin phòng
            JLabel roomInfoLabel = (JLabel) roomPanelClicked.getComponent(0); // Component thứ 1 là JLabel chứa tên phòng
            String tenPhong = roomInfoLabel.getText().substring(11); // Lấy tên phòng từ JLabel, bỏ đi "Tên Phòng: "

            // Tạo cửa sổ mới để hiển thị thông tin chi tiết về phòng
            JPanel_InfoPhong infoPhongPanel;
            try {
                infoPhongPanel = new JPanel_InfoPhong(tenPhong);
                JFrame infoPhongFrame = new JFrame("Thông tin phòng " + tenPhong);
                infoPhongFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                infoPhongFrame.add(infoPhongPanel);
                infoPhongFrame.pack();
                infoPhongFrame.setLocationRelativeTo(null);
                infoPhongFrame.setVisible(true);

                refreshData();

            } catch (IOException ex) {
                Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getActionCommand().equals("Thuê phòng cho khách")) {
            // Lấy tên phòng từ JPanel chứa thông tin phòng
            JPanel roomPanelClicked = (JPanel) roomPopupMenu.getInvoker(); // JPanel chứa thông tin phòng
            JLabel roomInfoLabel = (JLabel) roomPanelClicked.getComponent(0); // Component thứ 1 là JLabel chứa tên phòng
            String tenPhong = roomInfoLabel.getText().substring(11); // Lấy tên phòng từ JLabel, bỏ đi "Tên Phòng: "

            try {
                p_dao = new Phong_DAO();
                if (p_dao.getPhongTheoTenPhong(tenPhong).getFirst().getTrangThaiPhong().getTenTrangThai() == 3) {
                    // Tạo cửa sổ mới để hiển thị thông tin chi tiết về phòng
                    JPanel_thuePhong thuePhongPanel;
                    thuePhongPanel = new JPanel_thuePhong(this, tenPhong, nhanVien, null, 0, null, null);
                    JFrame thuePhongFrame = new JFrame("Cho Thuê Phòng");
                    thuePhongFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    thuePhongFrame.add(thuePhongPanel);
                    thuePhongFrame.pack();
                    thuePhongFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    thuePhongFrame.setLocationRelativeTo(null);
                    thuePhongFrame.setVisible(true);
                    refreshData();
                } else if (p_dao.getPhongTheoTenPhong(tenPhong).getFirst().getTrangThaiPhong().getTenTrangThai() == 1) {
                    // Tạo cửa sổ mới để hiển thị thông tin chi tiết về phòng
                    JPanel_thuePhong thuePhongPanel;
                    String maPhieu = null;
                    try {
                        Phong p = p_dao.getPhongTheoTenPhong(tenPhong).getFirst();
                        ctpdp = new ChiTietPhieuDatPhong_DAO();
                        ArrayList<ChiTietPhieuDatPhong> list = ctpdp.getPhongTheoPhong(p.getMaPhong());
                        for (ChiTietPhieuDatPhong chiTietPhieuDatPhong : list) {
                            if (chiTietPhieuDatPhong.isTrangThai()) {
                                maPhieu = chiTietPhieuDatPhong.getPhieuDatPhong().getMaPhieuDatPhong();
                            }
                        }
                        pdp_dao = new PhieuDatPhong_DAO();
                        PhieuDatPhong pdp = pdp_dao.getPhongTheoMaPhieuDatPhong(maPhieu).getFirst();
                        KhachHang kh = pdp.getKhachHang();
                        thuePhongPanel = new JPanel_thuePhong(this, tenPhong, nhanVien, kh, pdp.getSoLuongNguoi(), pdp.getNgayTraPhong(), pdp);
                        JFrame thuePhongFrame = new JFrame("Cho Thuê Phòng");
                        thuePhongFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        thuePhongFrame.add(thuePhongPanel);
                        thuePhongFrame.pack();
                        thuePhongFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        thuePhongFrame.setLocationRelativeTo(null);
                        thuePhongFrame.setVisible(true);
                        refreshData();
                    } catch (IOException ex) {
                        Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể thực hiện chức năng này khi phòng đang bận");
                    refreshData();
                }
            } catch (IOException ex) {
                Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getActionCommand().equals("Xem chi tiết phòng")) {
            try {
                // Lấy tên phòng từ JPanel chứa thông tin phòng
                JPanel roomPanelClicked = (JPanel) roomPopupMenu.getInvoker(); // JPanel chứa thông tin phòng
                JLabel roomInfoLabel = (JLabel) roomPanelClicked.getComponent(0); // Component thứ 1 là JLabel chứa tên phòng
                String tenPhong = roomInfoLabel.getText().substring(11); // Lấy tên phòng từ JLabel, bỏ đi "Tên Phòng: "
                Phong phong = p_dao.getPhongTheoTenPhong(tenPhong).getFirst();
                System.out.println(phong.getTrangThaiPhong());
                if (phong.getTrangThaiPhong().getTenTrangThai() == 2) {
                    cthd_dao = new ChiTietHoaDon_DAO();

                    ArrayList<ChiTietHoaDon> lcthd = cthd_dao.getChiTietHoaDontheoPhong(phong.getMaPhong());

                    System.out.println("xem chi tiet phòng: " + cthd);
                    JPanel_loadThongTinThue thongTinThue = null;
                    hd_dao = new HoaDon_DAO();
                    ArrayList<HoaDon> lhd = hd_dao.getAllTableHoaDon();
                    for (HoaDon hd : lhd) {
                        for (ChiTietHoaDon cthd : lcthd) {
                            if (hd.getTongThanhTienBanDau() == 0 && cthd.getHoaDon().getMaHoaDon().equals(hd.getMaHoaDon())) {
                                thongTinThue = new JPanel_loadThongTinThue(this, tenPhong, nhanVien, cthd, hd);
                            }
                        }
                    }
                    JFrame thongTinThueJFrame = new JFrame("Thông tin thuê phòng");
                    thongTinThueJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    thongTinThueJFrame.add(thongTinThue);
                    thongTinThueJFrame.pack();
                    thongTinThueJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    thongTinThueJFrame.setLocationRelativeTo(null);
                    thongTinThueJFrame.setVisible(true);
                    refreshData();
                } else {
                    JOptionPane.showMessageDialog(this, "Không thực hiện được khi Phòng chưa được thuê!");
                    refreshData();
                }

            } catch (IOException ex) {
                Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getActionCommand().equals("Đặt phòng")) {
            JPanel roomPanelClicked = (JPanel) roomPopupMenu.getInvoker(); // JPanel chứa thông tin phòng
            JLabel roomInfoLabel = (JLabel) roomPanelClicked.getComponent(0); // Component thứ 1 là JLabel chứa tên phòng
            String tenPhong = roomInfoLabel.getText().substring(11); // Lấy tên phòng từ JLabel, bỏ đi "Tên Phòng: "

            JPanel_DatPhong datPhongPanel;
            try {
                Phong p = p_dao.getPhongTheoTenPhong(tenPhong).getFirst();
                if (p.getTrangThaiPhong().getTenTrangThai() == 3) {
                    datPhongPanel = new JPanel_DatPhong(this, tenPhong);
                    JFrame datPhongFrame = new JFrame("Đặt Phòng");
                    datPhongFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    datPhongFrame.add(datPhongPanel);
                    datPhongFrame.pack();
                    datPhongFrame.setLocationRelativeTo(null);
                    datPhongFrame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Chỉ có thể đặt phòng khi phòng đang sẵn sàng");
                }
            } catch (IOException ex) {
                Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e
    ) {
        JPanel roomPanelClicked = (JPanel) e.getSource();

        if (SwingUtilities.isRightMouseButton(e)) {
            // Nếu click chuột phải, hiển thị menu ngữ cảnh tại vị trí chuột
            roomPopupMenu.show(roomPanelClicked, e.getX(), e.getY());
        } else {
            if (!isRoomSelected) {
                // Xử lý khi click vào một phòng
                Component[] components = roomPanelClicked.getComponents();
                for (Component component : components) {
                    if (component instanceof JLabel) {
                        JLabel roomInfoLabel = (JLabel) component;
                        isRoomSelected = true; // Đánh dấu là đã chọn phòng
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e
    ) {

    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {

    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {

    }

    @Override
    public void mouseExited(MouseEvent e
    ) {

    }
}
