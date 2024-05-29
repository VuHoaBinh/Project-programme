package gui;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class WebcamFrame extends JFrame implements Runnable, ThreadFactory {

    private WebcamPanel webcamPanel;
    private Webcam webcam;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private Callback callback;

    public interface Callback {

        void onQRCodeScanned(String qrCode);
    }

    public WebcamFrame(Callback callback) {
        this.callback = callback;
        initComponents();
    }

    private void closeCamera() {
        if (webcam.isOpen()) {
            webcam.close();
        }
    }

    private void initComponents() {
        setTitle("Webcam");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension size = WebcamResolution.VGA.getSize(); // Độ phân giải VGA (640x480)
        webcam = Webcam.getDefault(); // Lấy webcam mặc định
        webcam.setViewSize(size);

        webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setFPSDisplayed(true);

        add(webcamPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Đặt vị trí cửa sổ ở giữa màn hình

        executor.execute(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                closeCamera();
            }
        });
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            BufferedImage image = null;

            if (webcam.isOpen()) {
                image = webcam.getImage();
            }

            if (image == null) {
                continue;
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                Result result = new MultiFormatReader().decode(bitmap);
                if (result != null) {
                    String qrCode = result.getText();
                    if (callback != null) {
                        callback.onQRCodeScanned(qrCode);
                        webcam.close();
                        dispose();
                        break;
                    }
                }
            } catch (NotFoundException e) {
                // No QR code found
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "QR Thread");
        t.setDaemon(true);
        return t;
    }
}
