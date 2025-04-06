package org.example.backend.databaseapi.jpa.usuario;

import org.example.backend.databaseapi.application.port.out.usuario.EmailPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailAdapter implements EmailPort {

    @Value("${mail.smtp.username}")
    private String mailSender;
    @Value("${mail.smtp.password}")
    private String mailPassword;
    @Value("${app.frontend-url}")
    private String frontendUrl;

    @Value("${mail.smtp.host}")
    private String smtpHost;
    @Value("${mail.smtp.port}")
    private String smtpPort;
    @Value("${mail.smtp.auth}")
    private String smtpAuth;
    @Value("${mail.smtp.starttls.enable}")
    private String smtpStartTLS;

    @Override
    public void enviarCorreoRecuperacion(String destinatario, String token) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", smtpAuth);
        properties.put("mail.smtp.starttls.enable", smtpStartTLS);
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);

        // Crear una sesión con autenticación
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailSender, mailPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(mailSender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

            message.setSubject("Recuperación de contraseña");

            String resetUrl = frontendUrl + "/reset-password/" + token;
            String htmlContent = "<p>Has solicitado recuperar tu contraseña.</p>" +
                    "<p>Haz clic en el siguiente enlace para cambiar tu contraseña:</p>" +
                    "<a href=\"" + resetUrl + "\">Cambiar mi contraseña</a>" +
                    "<p>Este enlace será válido durante 1 hora.</p>" +
                    "<p>Si no solicitaste recuperar tu contraseña, puedes ignorar este correo.</p>";

            message.setContent(htmlContent, "text/html; charset=UTF-8");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar correo de recuperación", e);
        }
    }
}