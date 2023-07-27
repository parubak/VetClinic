package com.example.vetclinic.service;

import com.example.vetclinic.dto.Query;
import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;

public interface EmailService {

    void sendSimpleEmail(final String toAddress, final String subject, final String message);
    void sendEmailWithAttachment(final String toAddress, final String subject, final String message, final String attachment) throws MessagingException, FileNotFoundException;
    public void sendMail(Query query) throws MessagingException;
}