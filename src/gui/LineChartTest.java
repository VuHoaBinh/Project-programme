package gui;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.time.Year;
import java.time.YearMonth;
import javax.swing.JPanel;

import com.itextpdf.text.Font;

import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import gui.ModelChart;

/**
 *
 * @author RAVEN
 */
public class LineChartTest extends JPanel {

	/**
	 * Creates new form Test
	 */
	public LineChartTest() {
		setVisible(true);
		initComponents();
		chart.setTitle("Tổng Quan");
		chart.addLegend("Tất cả KH", Color.decode("#7b4397"), Color.decode("#dc2430"));
		chart.addLegend("KH Cũ", Color.decode("#e65c00"), Color.decode("#F9D423"));
		chart.addLegend("KH Mới", Color.decode("#0099F7"), Color.decode("#F11712"));
		chart.addLegend("KH Quen", Color.decode("#0099F7"), Color.decode("#F11712"));
		setDataKhachHang();
//		test();
	}

	public LineChartTest(String thongKeDoanhThu) {
		setVisible(true);
		initComponents();
		chart.setTitle("Tổng Quan");
		chart.setSize(400, 600);
		chart.addLegend("Tổng doanh thu", Color.decode("#7b4397"), Color.decode("#dc2430"));
		chart.addLegend("DT / hóa đơn", Color.decode("#e65c00"), Color.decode("#F9D423"));
		chart.addLegend("DT cao nhất", Color.decode("#0099F7"), Color.decode("#F11712"));
		chart.addLegend("DT thấp nhất", Color.decode("#0099F7"), Color.decode("#F11712"));
		setDataDoanhThu();
	}

//	private void test() {
//		chart.clear();
//		chart.addData(new ModelChart("T1", new double[] { 500, 50, 100 }));
//		chart.addData(new ModelChart("T2", new double[] { 600, 300, 150 }));
//		chart.addData(new ModelChart("T3", new double[] { 200, 50, 2000 }));
//		chart.addData(new ModelChart("T4", new double[] { 480, 700, 100 }));
//		chart.addData(new ModelChart("T5", new double[] { 350, 540, 500 }));
//		chart.addData(new ModelChart("T6", new double[] { 450, 800, 100 }));
//		chart.addData(new ModelChart("T7", new double[] { 450, 800, 100 }));
//		chart.addData(new ModelChart("T8", new double[] { 450, 800, 100 }));
//		chart.addData(new ModelChart("T9", new double[] { 450, 800, 100 }));
//		chart.addData(new ModelChart("T10", new double[] { 450, 800, 100 }));
//		chart.addData(new ModelChart("T11", new double[] { 450, 800, 100 }));
//		chart.addData(new ModelChart("T12", new double[] { 450, 800, 100 }));
//		chart.start();
//	}
	


	private void setDataDoanhThu() {
		HoaDon_DAO hd_dao = new HoaDon_DAO();
		Double thongKeTongDoanhThu[] = hd_dao.thongKeBieuDoLineTongDoanhThu(Year.now().getValue());
		int thongKeTongHoaDon[] = hd_dao.thongKeBieuDoLineTongHoaDon(Year.now().getValue());
		Double doanhThuCaoNhatMoThang[] = hd_dao.thongKeBieuDoLineDoanhThuCaoNhatMoiThang(Year.now().getValue());
		Double doanhThuThapNhatMoThang[] = hd_dao.thongKeBieuDoLineDoanhThuThapNhatMoiThang(Year.now().getValue());
		
		chart.clear();
		chart.addData(new ModelChart("T1", new double[] { thongKeTongDoanhThu[0], thongKeTongHoaDon[0], doanhThuCaoNhatMoThang[0], doanhThuThapNhatMoThang[0] }));
		chart.addData(new ModelChart("T2", new double[] { thongKeTongDoanhThu[1], thongKeTongHoaDon[1], doanhThuCaoNhatMoThang[1], doanhThuThapNhatMoThang[1] }));
		chart.addData(new ModelChart("T3", new double[] { thongKeTongDoanhThu[2], thongKeTongHoaDon[2], doanhThuCaoNhatMoThang[2], doanhThuThapNhatMoThang[2] }));
		chart.addData(new ModelChart("T4", new double[] { thongKeTongDoanhThu[3], thongKeTongHoaDon[3], doanhThuCaoNhatMoThang[3], doanhThuThapNhatMoThang[3] }));
		chart.addData(new ModelChart("T5", new double[] { thongKeTongDoanhThu[4], thongKeTongHoaDon[4], doanhThuCaoNhatMoThang[4], doanhThuThapNhatMoThang[4] }));
		chart.addData(new ModelChart("T6", new double[] { thongKeTongDoanhThu[5], thongKeTongHoaDon[5], doanhThuCaoNhatMoThang[5], doanhThuThapNhatMoThang[5] }));
		chart.addData(new ModelChart("T7", new double[] { thongKeTongDoanhThu[6], thongKeTongHoaDon[6], doanhThuCaoNhatMoThang[6], doanhThuThapNhatMoThang[6] }));
		chart.addData(new ModelChart("T8", new double[] { thongKeTongDoanhThu[7], thongKeTongHoaDon[7], doanhThuCaoNhatMoThang[7], doanhThuThapNhatMoThang[7] }));
		chart.addData(new ModelChart("T9", new double[] { thongKeTongDoanhThu[8], thongKeTongHoaDon[8], doanhThuCaoNhatMoThang[8], doanhThuThapNhatMoThang[8] }));
		chart.addData(new ModelChart("T10", new double[] { thongKeTongDoanhThu[9], thongKeTongHoaDon[9], doanhThuCaoNhatMoThang[9], doanhThuThapNhatMoThang[9] }));
		chart.addData(new ModelChart("T11", new double[] { thongKeTongDoanhThu[10], thongKeTongHoaDon[10], doanhThuCaoNhatMoThang[10], doanhThuThapNhatMoThang[10] }));
		chart.addData(new ModelChart("T12", new double[] { thongKeTongDoanhThu[11], thongKeTongHoaDon[11], doanhThuCaoNhatMoThang[11], doanhThuThapNhatMoThang[11] }));
		chart.start();
	}
	
	private void setDataKhachHang () {
		KhachHang_DAO kh_dao = new KhachHang_DAO();
		int tongKHList[] = kh_dao.thongKeBieuDoLineTongKH(Year.now().getValue());
		int tongKHCu[] = kh_dao.thongKeBieuDoLineTongKHCu(Year.now().getValue());
		int tongKHMoi[] = kh_dao.thongKeBieuDoLineTongKHMoi(Year.now().getValue());
		int tongKHQuen[] = kh_dao.thongKeBieuDoLineTongKHQuen(Year.now().getValue());
		
		chart.clear();
		chart.addData(new ModelChart("T1", new double[] { tongKHList[0], tongKHCu[0], tongKHMoi[0], tongKHQuen[0]}));
		chart.addData(new ModelChart("T2", new double[] { tongKHList[1], tongKHCu[1], tongKHMoi[1], tongKHQuen[1] }));
		chart.addData(new ModelChart("T3", new double[] { tongKHList[2], tongKHCu[2], tongKHMoi[2], tongKHQuen[2] }));
		chart.addData(new ModelChart("T4", new double[] { tongKHList[3], tongKHCu[3], tongKHMoi[3], tongKHQuen[3] }));
		chart.addData(new ModelChart("T5", new double[] { tongKHList[4], tongKHCu[4], tongKHMoi[4], tongKHQuen[4] }));
		chart.addData(new ModelChart("T6", new double[] { tongKHList[5], tongKHCu[5], tongKHMoi[5], tongKHQuen[5] }));
		chart.addData(new ModelChart("T7", new double[] { tongKHList[6], tongKHCu[6], tongKHMoi[6], tongKHQuen[6] }));
		chart.addData(new ModelChart("T8", new double[] { tongKHList[7], tongKHCu[7], tongKHMoi[7], tongKHQuen[7] }));
		chart.addData(new ModelChart("T9", new double[] { tongKHList[8], tongKHCu[8], tongKHMoi[8], tongKHQuen[8] }));
		chart.addData(new ModelChart("T10", new double[] { tongKHList[9], tongKHCu[9], tongKHMoi[9], tongKHQuen[9] }));
		chart.addData(new ModelChart("T11", new double[] { tongKHList[10], tongKHCu[10], tongKHMoi[10], tongKHQuen[10]}));
		chart.addData(new ModelChart("T12", new double[] { tongKHList[11], tongKHCu[11], tongKHMoi[11], tongKHQuen[11]}));
		chart.start();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		panelShadow1 = new gui.PanelShadow();
		chart = new gui.CurveLineChart();

//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		panelShadow1.setBackground(new java.awt.Color(34, 59, 69));
		panelShadow1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelShadow1.setColorGradient(new java.awt.Color(17, 38, 47));

		chart.setForeground(new java.awt.Color(237, 237, 237));
		chart.setFillColor(true);

		javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
		panelShadow1.setLayout(panelShadow1Layout);
		panelShadow1Layout
				.setHorizontalGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								panelShadow1Layout.createSequentialGroup().addContainerGap()
										.addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
										.addContainerGap()));
		panelShadow1Layout
				.setVerticalGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(panelShadow1Layout.createSequentialGroup().addContainerGap()
								.addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
								.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(panelShadow1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(panelShadow1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

//        pack();
//        setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents


	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(LineChartTest.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(LineChartTest.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(LineChartTest.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LineChartTest.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new LineChartTest().setVisible(true);
//            }
//        });
		// </editor-fold>

		/* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new LineChartTest().setVisible(true);
//            }
//        });
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private gui.CurveLineChart chart;
	private gui.PanelShadow panelShadow1;
	// End of variables declaration//GEN-END:variables
}
