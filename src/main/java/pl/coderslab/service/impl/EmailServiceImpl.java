package pl.coderslab.service.impl;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.coderslab.service.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("u1326546@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        try {
            emailSender.send(message);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String body, String attachment) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("u1326546@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);
            FileSystemResource file = new FileSystemResource(new File(attachment));
            helper.addAttachment(String.format("%s", file.getFilename()), file);
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
