package com.example.vetclinic.service;

import com.example.vetclinic.dto.Query;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService  {
    static String SEND_TO="parubak96@gmail.com";
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendMail(Query query) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariable("query",query);
        String emailContent = templateEngine.process("email/templates", context);

        mimeMessageHelper.setTo(SEND_TO);
        mimeMessageHelper.setFrom(SEND_TO);
        mimeMessageHelper.setSubject("Clinic site");
        mimeMessageHelper.setText(emailContent, true);
        emailSender.send(message);
    }

    @Override
    public void sendSimpleEmail(String toAddress, String name, String message) throws MessagingException {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setTo(SEND_TO);
        helper.setFrom(toAddress);
        helper.setSubject("Звернення");
        String sendMassage=String.format("Від: %S <br>Зворотня адреса: %s <br>Текст звернення: %s" ,name,toAddress,message);
        helper.setText(sendMassage,true);
        emailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment) throws MessagingException, FileNotFoundException {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(toAddress);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
        messageHelper.addAttachment("Purchase Order", file);
        emailSender.send(mimeMessage);
    }
}
