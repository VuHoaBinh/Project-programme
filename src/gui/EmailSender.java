package gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailSender {
    public static void sendEmailWithAttachment(String toEmail, String subject, String body, String filePath) {
        // Thông tin tài khoản email của bạn
        String fromEmail = "phamhoanglong041003@gmail.com";
        String password = "lgol wsbn izgh zzqw"; // Sử dụng mật khẩu tài khoản Google của bạn

        // Cấu hình thông tin máy chủ SMTP của Gmail
        String host = "smtp.gmail.com";
        int port = 587; // hoặc 465 nếu sử dụng SSL

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Tạo phiên làm việc để gửi email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Tạo một đối tượng MimeMessage
            MimeMessage message = new MimeMessage(session);

            // Thiết lập người gửi, người nhận và chủ đề
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);

            // Tạo phần thân của email
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Tạo phần đính kèm tệp PDF
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File(filePath));

            // Kết hợp các phần lại với nhau
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            // Thiết lập nội dung email
            message.setContent(multipart);

            // Gửi email
            Transport.send(message);
            System.out.println("Email sent successfully with attachment!");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String toEmail = "recipient@example.com";
        String subject = "Test email with attachment";
        String body = "This is a test email with attachment.";
        String filePath = "data/phieuDatPhongBanPDF/PhieuDatPhong.pdf";

        sendEmailWithAttachment(toEmail, subject, body, filePath);
    }
}
