package com.example.AudioBook.Util.Authentication;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, String otp) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify OTP");
//          <a href="http://localhost:8080/verify-account?email=%s&otp=%s" target="_blank">click link to verify</a>
        mimeMessageHelper.setText("""
        <div>
            <h2>Your confirm code is: </h2>
            <h1>%s</h1>
            <h3>Please don't share this OTP to anyone !</h3>
        </div>
        """.formatted(otp), true);

        javaMailSender.send(mimeMessage);
    }
    public void sendPasswordEmail(String email, String password) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Your password");
        mimeMessageHelper.setText("""
        <div>
            <h2>Your password is: </h2>
            <h1>%s</h1>
            <h3>Please don't share this password to anyone !</h3>
        </div>
        """.formatted(password), true);

        javaMailSender.send(mimeMessage);
    }
}