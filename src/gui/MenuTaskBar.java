/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import dao.NhanVien_DAO;
import entity.NhanVien;
import entity.TaiKhoan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Nguyễn Tiến Đạt
 */
public class MenuTaskBar extends JPanel{
    TrangChu trangChu;
    String[][] getSt = {
        {"Trang Chủ", "home.svg", "trangchu"},
        {"Đồ Ăn Uống", "home.svg", "quanlydoanuong"},
        {"Khách Hàng", "customer.svg", "quanlykhachhang"},
        {"Nhân Viên", "staff.svg", "quanlynhanvien"},
        {"Quản Lý Phòng", "home.svg", "quanlyphong"},
        {"Khuyến Mãi", "home.svg", "quanlykhuyenmai"},
        {"Thống Kê", "statistical.svg", "thongke"},
        {"Đăng Xuất", "log_out.svg", "dangxuat"},
    };
    
    KhachSanAnNhien_GUI main;
    TaiKhoan user;
    String chucVu;
    public itemTaskbar[] listitem;
    JLabel lblChucVu, lblUsername;
    
    //tasbarMenu chia thành 3 phần chính là pnlCenter, pnlTop, pnlBottom
    JScrollPane scrollPane;
    JPanel pnlCenter, pnlTop, pnlBottom, bar1, bar2, bar3, bar4;
    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    Color HowerFontColor = new Color(1, 87, 155);
    Color HowerBackgroundColor = new Color(187, 222, 251);
    
    public NhanVien nhanVien;    
    public MenuTaskBar(KhachSanAnNhien_GUI main){
       this.main = main;
       initComponent();
    }
//    public MenuTaskBar(KhachSanAnNhien_GUI main, TaiKhoan tk){
//        this.main = main;
//        this.user = tk;
//        this.chucVu = "Quản lý";
//        
//        initComponent();
//    }
   
   private void initComponent(){
       listitem = new itemTaskbar[getSt.length];
       this.setOpaque(true);
       this.setBackground(DefaultColor);
       this.setLayout(new BorderLayout(0, 0));
       
       // bar1, bar là các đường kẻ mỏng giữa taskbarMenu và MainContent
        pnlTop = new JPanel();
        pnlTop.setPreferredSize(new Dimension(250, 80));
        pnlTop.setBackground(DefaultColor);
        pnlTop.setLayout(new BorderLayout(0, 0));
        this.add(pnlTop, BorderLayout.NORTH);

        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BorderLayout(0, 0));
        pnlTop.add(info, BorderLayout.CENTER);

        // Cái info này bỏ vô cho đẹp tí, mai mốt có gì xóa đi đê hiển thị thông tin tài khoản và quyền
//        in4(info);
        
        bar1 = new JPanel();
        bar1.setBackground(new Color(204, 214, 219));
        bar1.setPreferredSize(new Dimension(1, 0));
        pnlTop.add(bar1, BorderLayout.EAST);

        bar2 = new JPanel();
        bar2.setBackground(new Color(204, 214, 219));
        bar2.setPreferredSize(new Dimension(0, 1));
        pnlTop.add(bar2, BorderLayout.SOUTH);

        pnlCenter = new JPanel();
        pnlCenter.setPreferredSize(new Dimension(230, 600));
        pnlCenter.setBackground(DefaultColor);
//        pnlCenter.setBorder(new EmptyBorder(0,15,0,35));
        pnlCenter.setLayout(new FlowLayout(0, 0, 5));

        bar3 = new JPanel();
        bar3.setBackground(new Color(204, 214, 219));
        bar3.setPreferredSize(new Dimension(1, 1));
        this.add(bar3, BorderLayout.EAST);

        scrollPane = new JScrollPane(pnlCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(5, 10, 0, 10));
        this.add(scrollPane, BorderLayout.CENTER);

        pnlBottom = new JPanel();
        pnlBottom.setPreferredSize(new Dimension(250, 50));
        pnlBottom.setBackground(DefaultColor);
        pnlBottom.setLayout(new BorderLayout(0, 0));

        bar4 = new JPanel();
        bar4.setBackground(new Color(204, 214, 219));
        bar4.setPreferredSize(new Dimension(1, 1));
        pnlBottom.add(bar4, BorderLayout.EAST);

        this.add(pnlBottom, BorderLayout.SOUTH);
        
        for (int i = 0; i < getSt.length; i++) {
            if (i + 1 == getSt.length) {
                listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][0]);
                pnlBottom.add(listitem[i]);
            } else {
                listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][0]);
                pnlCenter.add(listitem[i]);
                if (i != 0) {
//                    if (!checkRole(getSt[i][2])) {
                        listitem[i].setVisible(false);
//                    }
                }
            }
        } 
        listitem[0].setBackground(HowerBackgroundColor);
        listitem[0].setForeground(HowerFontColor);
        listitem[0].isSelected = true;
        for (int i = 0; i < getSt.length; i++) {
            listitem[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    pnlMenuTaskbarMousePress(evt);
                }
            });
        }
        listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                trangChu = new TrangChu();
                main.setPanel(trangChu);
            }
        });
//        listitem[1].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent evt) {
//                quanLyDoAnUong = new QuanLyDoAnUong(main);
//                main.setPanel(quanLyDoAnUong);
//
//            }
//        });
   }
   public void pnlMenuTaskbarMousePress(MouseEvent evt) {

        for (int i = 0; i < getSt.length; i++) {
            if (evt.getSource() == listitem[i]) {
                listitem[i].isSelected = true;
                listitem[i].setBackground(HowerBackgroundColor);
                listitem[i].setForeground(HowerFontColor);
            } else {
                listitem[i].isSelected = false;
                listitem[i].setBackground(DefaultColor);
                listitem[i].setForeground(FontColor);
            }
        }
    }
//   public void in4(JPanel info) {
//        JPanel pnlIcon = new JPanel(new FlowLayout());
//        pnlIcon.setPreferredSize(new Dimension(60, 0));
//        pnlIcon.setOpaque(false);
//        info.add(pnlIcon, BorderLayout.WEST);
//        JLabel lblIcon = new JLabel();
//        lblIcon.setPreferredSize(new Dimension(50, 70));
//        if (nhanVien.isGioiTinh() == true) {
//            lblIcon.setIcon(new FlatSVGIcon("./icon/man_50px.svg"));
//        } else {
//            lblIcon.setIcon(new FlatSVGIcon("./icon/women_50px.svg"));
//        }
//        pnlIcon.add(lblIcon);
//
//        JPanel pnlInfo = new JPanel();
//        pnlInfo.setOpaque(false);
//        pnlInfo.setLayout(new FlowLayout(0, 10, 5));
//        pnlInfo.setBorder(new EmptyBorder(15, 0, 0, 0));
//        info.add(pnlInfo, BorderLayout.CENTER);
//
//        lblUsername = new JLabel(nhanVien.getHoTenNhanVien());
//        lblUsername.putClientProperty("FlatLaf.style", "font: 150% $semibold.font");
//        pnlInfo.add(lblUsername);
//        lblChucVu = new JLabel(chucVu);
//        lblChucVu.putClientProperty("FlatLaf.style", "font: 120% $light.font");
//        lblChucVu.setForeground(Color.GRAY);
//        pnlInfo.add(lblChucVu);         
//    }
}
