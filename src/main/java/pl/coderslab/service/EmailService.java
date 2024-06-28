package pl.coderslab.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendEmail(String to, String subject, String body);

    void sendEmailWithAttachment(String to, String subject, String body, String attachment);
}
