/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import dao.Phong_DAO;
import entity.NhanVien;
import entity.Phong;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M S I
 */
public class JPanel_QuanLyPhong extends javax.swing.JPanel implements ActionListener, MouseListener {

    private Phong_DAO p_dao;
    private DefaultTableModel modelTang1;
    private final JPopupMenu roomPopupMenu;
    private boolean isRoomSelected = false;

    /**
     * Creates new form JPanel_QuanLyNhanVien
     */
    public JPanel_QuanLyPhong() throws SQLException {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
        }
        initComponents();
        loadData();

        // Tạo menu ngữ cảnh cho phòng
        roomPopupMenu = new JPopupMenu();
        JMenuItem viewDetailsItem = new JMenuItem("Xem chi tiết");
        JMenuItem datPhong = new JMenuItem("Đặt phòng");
        
        
        datPhong.addActionListener(this);
        viewDetailsItem.addActionListener(this);
        
        
        roomPopupMenu.add(viewDetailsItem);
        roomPopupMenu.add(datPhong);
    }

    public void loadData() throws SQLException {
        try {
            // Xóa các phần tử hiện có trên pn_Tang1 trước khi load dữ liệu mới
            pn_Tang1.removeAll();
            pn_Tang1.revalidate();
            pn_Tang1.repaint();

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
            }

            // Cập nhật lại hiển thị của pn_Tang1
            pn_Tang1.revalidate();
            pn_Tang1.repaint();

        } catch (SQLException ex) {
            Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JPanel createRoomPanel(Phong phong) {
        JPanel roomPanel = new JPanel(new BorderLayout());
        // Tạo JLabel chứa hình đại diện cho phòng
        JLabel roomImageLabel = new JLabel(new ImageIcon("src/trangThaiPhong/AVAILABLE.png"));
        roomImageLabel.setPreferredSize(new Dimension(100, 100)); // Đặt kích thước cho hình đại diện

        // Tạo JLabel chứa thông tin phòng
        JLabel roomInfoLabel = new JLabel(phong.getTenPhong());
        Font boldItalicFont = new Font("Segoe UI", Font.BOLD + Font.ITALIC, 15);
        roomInfoLabel.setFont(boldItalicFont);

        roomInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Đặt hình đại diện ở trên, thông tin phòng ở dưới
        roomPanel.add(roomImageLabel, BorderLayout.CENTER);
        roomPanel.add(roomInfoLabel, BorderLayout.SOUTH);

        // Thêm sự kiện cho roomPanel nếu cần
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
        jPanel6 = new javax.swing.JPanel();
        txt_maCanTim = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        cb_gt = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cb_cv = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cb_tt = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btn_Loc = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pn_Tang1 = new javax.swing.JPanel();
        pn_Tang2 = new javax.swing.JPanel();
        pn_Tang3 = new javax.swing.JPanel();
        pn_Tang4 = new javax.swing.JPanel();
        pn_Tang5 = new javax.swing.JPanel();
        pn_Tang6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(931, 800));
        setLayout(new java.awt.BorderLayout());

        search_Engine.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        search_Engine.setLayout(new javax.swing.BoxLayout(search_Engine, javax.swing.BoxLayout.X_AXIS));

        jPanel6.setPreferredSize(new java.awt.Dimension(386, 50));

        txt_maCanTim.setText("");
        txt_maCanTim.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã nhân viên...");
        txt_maCanTim.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("./icon/search_1.svg"));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(txt_maCanTim, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(txt_maCanTim, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        search_Engine.add(jPanel6);

        cb_gt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam", "Nữ"}));
        cb_gt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_gtActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Trạng Thái:");

        cb_cv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả","Lễ Tân","Quản Lý"}));
        cb_cv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_cvActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Chức vụ");

        cb_tt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang làm", "Đã nghỉ"}));
        cb_tt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ttActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Giới tính:");
        jLabel3.setPreferredSize(new java.awt.Dimension(20, 16));

        btn_Loc.setText("Lọc");

        btn_refresh.setIcon(new FlatSVGIcon("./icon/reload.svg"));
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_gt, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(cb_tt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(cb_cv, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Loc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cb_gt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cb_tt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_cv, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        search_Engine.add(jPanel7);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1543, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Phòng được đặt trước", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1543, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Phòng đang được thuê", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Phòng có sẵn", jPanel4);

        add(jTabbedPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cb_cvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_cvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_cvActionPerformed

    private void cb_gtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_gtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_gtActionPerformed

    private void cb_ttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_ttActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_refreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_Loc;
    public javax.swing.JButton btn_refresh;
    private javax.swing.JComboBox<String> cb_cv;
    private javax.swing.JComboBox<String> cb_gt;
    private javax.swing.JComboBox<String> cb_tt;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel pn_Tang1;
    private javax.swing.JPanel pn_Tang2;
    private javax.swing.JPanel pn_Tang3;
    private javax.swing.JPanel pn_Tang4;
    private javax.swing.JPanel pn_Tang5;
    private javax.swing.JPanel pn_Tang6;
    private javax.swing.JPanel search_Engine;
    private javax.swing.JTextField txt_maCanTim;
    // End of variables declaration//GEN-END:variables
    private NhanVien_DAO nv_dao = new NhanVien_DAO();
    private DefaultTableModel modelNhanVien;
    javax.swing.ButtonGroup gr_gt = new javax.swing.ButtonGroup();
    javax.swing.ButtonGroup gr_tt = new javax.swing.ButtonGroup();
    javax.swing.ButtonGroup gr_cv = new javax.swing.ButtonGroup();

    @Override
    public void actionPerformed(ActionEvent e) {
        // Xử lý các sự kiện từ menu ngữ cảnh
        if (e.getActionCommand().equals("Xem chi tiết")) {
            // Lấy tên phòng từ JPanel chứa thông tin phòng
            JPanel roomPanelClicked = (JPanel) roomPopupMenu.getInvoker(); // JPanel chứa thông tin phòng
            JLabel roomInfoLabel = (JLabel) roomPanelClicked.getComponent(1); // Component thứ 2 là JLabel chứa tên phòng
            String tenPhong = roomInfoLabel.getText(); // Lấy tên phòng từ JLabel

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

            } catch (IOException ex) {
                Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(JPanel_QuanLyPhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
                        // Lấy thông tin từ label và hiển thị trong một cửa sổ thông báo

                        isRoomSelected = true; // Đánh dấu là đã chọn phòng
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
