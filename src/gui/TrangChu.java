/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
/**
 *
 * @author Nguyễn Tiến Đạt
 */
public class TrangChu extends JPanel{
    JPanel pnlMain;

    JLabel lblTitle;


    private void initComponent() {
        this.setBackground(new Color(24, 24, 24));
        this.setBounds(0, 200, 300, 1200);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);
        
        pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());

        // Tạo panel header luôn xuất hiện trong mọi giao diện
        JPanel pnlHeader = new JPanel() {
//                @Override
//                protected void paintComponent(Graphics g) {
//                        // TODO Auto-generated method stub
//                        super.paintComponent(g);
//                        Paint p = new GradientPaint(0.0f, 0.0f, new Color(255, 255, 255), getWidth(), getHeight(),
//                                        new Color(0xffe53b), true);
//                        Graphics2D g2d = (Graphics2D) g;
//                        g2d.setPaint(p);
//                        g2d.fillRect(0, 0, getWidth(), getHeight());
//
//                }
        };
        pnlHeader.setLayout(new BorderLayout());
        pnlHeader.setBackground(Color.WHITE);
        lblTitle = new JLabel("HỆ THỐNG QUẢN LÍ KHÁCH SẠN AN NHIÊN", SwingConstants.CENTER);
        lblTitle.setForeground(new Color(89, 59, 23));
        lblTitle.setFont(new Font("Time New Roman", Font.BOLD, 40));
        JLabel lbLogo = new JLabel(getIcon("data/images/logo.png", 150, 100));
        lbLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logoHotel.png")));
        JLabel lbltime = new JLabel("", SwingConstants.CENTER);
        lbltime.setFont(new Font("Calibri", Font.BOLD, 30));

        int delay = 100;
        Timer timer = new Timer(delay, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
                        String formattedDateTime = now.format(formatter);
                        lbltime.setText(formattedDateTime);
                }
        });

        timer.start();
        lbltime.setForeground(new Color(89, 59, 23));
        JPanel pnlSubEast = new JPanel();
        pnlSubEast.setOpaque(false);
        pnlSubEast.setLayout(new BoxLayout(pnlSubEast, BoxLayout.Y_AXIS));
        Box b = Box.createVerticalBox();
        Box b1 = Box.createHorizontalBox();
        b1.add(Box.createHorizontalStrut(20));
        b1.add(lbltime);
        b1.add(Box.createHorizontalStrut(20));
        b.add(b1);
        pnlSubEast.add(b);

        pnlHeader.add(lblTitle, BorderLayout.CENTER);
        pnlHeader.add(lbLogo, BorderLayout.WEST);
        pnlHeader.add(pnlSubEast, BorderLayout.EAST);
        
//        BackgroundPanel pnlBottom = new BackgroundPanel("/image/hotel.png");
        JPanel pnlBottom = new JPanel();
        JLabel hotel = new JLabel();
        hotel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hotel.png")));
        pnlBottom.add(hotel);
        pnlMain.add(pnlHeader, BorderLayout.NORTH);
        pnlMain.add(pnlBottom, BorderLayout.CENTER);
        this.add(pnlMain);
    }

    public TrangChu() {
        initComponent();
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
    }
    private ImageIcon getIcon(String path, int width, int height) {
		// TODO Auto-generated method stub
		ImageIcon iconEmployee = new ImageIcon(path);
		Image scaledImage = iconEmployee.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		return scaledIcon;
    }
    
//    class BackgroundPanel extends JPanel {
//        private Image backgroundImage;
//
//        // Constructor nhận đường dẫn tới hình ảnh
//        public BackgroundPanel(String imagePath) {
//            // Tải hình ảnh từ đường dẫn
//            this.backgroundImage = new ImageIcon(imagePath).getImage();
//        }
//
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            // Vẽ hình ảnh nền
//            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//        }
//    }
}
