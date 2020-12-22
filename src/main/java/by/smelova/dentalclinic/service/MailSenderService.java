package by.smelova.dentalclinic.service;

import by.smelova.dentalclinic.aspect.NeedToLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Slf4j
public class MailSenderService {
    public JavaMailSenderImpl JavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("bgsagentnatallia@gmail.com");
        mailSender.setPassword("agent_letoile73");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
    @NeedToLog
    public boolean Send(String subject, String text, String email) {
        JavaMailSenderImpl javaMailSender = JavaMailSender();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject); //The subject of the mail
        mailMessage.setText(text);
        mailMessage.setTo(email); //Who to send to
        mailMessage.setFrom("bgsagentnatallia@gmail.com"); //Who sent it
        javaMailSender.send(mailMessage);
        log.info("Mail was sent to " + email);
        return true;
    }
}
