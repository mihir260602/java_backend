package com.Event.Event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String toEmail, String username) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("Welcome to Events.Co!");

            String htmlContent = "<html>" +
                    "<body style='font-family: Arial, sans-serif; margin: 0; padding: 20px;'>" +
                    "<div style='max-width: 600px; margin: 0 auto;'>" +
                    "<h2 style='color: #4CAF50;'>Hello " + username + "!</h2>" +
                    "<p>Thank you for registering with <strong>Events.Co</strong>. We are excited to have you onboard.</p>" +
                    "<p>Feel free to explore our events platform, where you can create, manage, and join exciting events.</p>" +
                    "<p>If you have any questions, our support team is here to assist you.</p>" +
                    "<p style='margin-top: 20px;'>Best regards,<br><strong>Events.Co Management Team</strong></p>" +
                    "<hr style='border: none; height: 1px; background-color: #f1f1f1; margin-top: 20px;'/>" +
                    "<footer style='text-align: center; font-size: 12px; color: #777;'>" +
                    "© 2024 Ease Events.Co. All rights reserved." +
                    "</footer>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true); // Use true for HTML email
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendPasswordResetEmail(String toEmail, String resetUrl) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("Password Reset Request");

            String htmlContent = "<html>" +
                    "<body style='font-family: Arial, sans-serif; margin: 0; padding: 20px;'>" +
                    "<div style='max-width: 600px; margin: 0 auto;'>" +
                    "<h2 style='color: #FF5722;'>Password Reset Request</h2>" +
                    "<p>We received a request to reset your password. If you made this request, click the link below to reset your password:</p>" +
                    "<p style='text-align: center;'><a href='" + resetUrl + "' style='padding: 10px 20px; background-color: #FF5722; color: white; text-decoration: none; border-radius: 5px;'>Reset Password</a></p>" +
                    "<p>If you did not request a password reset, please ignore this email or contact support if you have concerns.</p>" +
                    "<p style='margin-top: 20px;'>Best regards,<br><strong>Events.Co Support Team</strong></p>" +
                    "<hr style='border: none; height: 1px; background-color: #f1f1f1; margin-top: 20px;'/>" +
                    "<footer style='text-align: center; font-size: 12px; color: #777;'>" +
                    "© 2024 Ease Events All rights reserved." +
                    "</footer>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true); // Use true for HTML email
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
