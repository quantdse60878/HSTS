/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/5/2015.
 */
package vn.edu.fpt.hsts.bizlogic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import vn.edu.fpt.hsts.common.IConsts;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailService extends JavaMailSenderImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    /**
     *
     */
    @Value("${hsts.mail.server.host}")
    private String host;

    @Value("${hsts.mail.server.port}")
    private int port;

    @Value("${hsts.mail.server.username}")
    private String username;

    @Value("${hsts.mail.server.password}")
    private String password;

    @Value("${hsts.mail.server.fromEmail}")
    private String fromEmail;

    @Value("${hsts.mail.server.fromAlias}")
    private String fromAlias;

    @Value("${hsts.mail.smtp.auth}")
    private String enableAuthen;

    @Value("${hsts.mail.smtp.starttls.enable}")
    private String enableStartTls;

    /**
     * <p>
     * Initialize properties.
     * </p>
     */
    @Autowired
    public void init() {
        this.setHost(this.host);
        this.setPort(this.port);
        this.setUsername(this.username);
        this.setPassword(this.password);
        this.getJavaMailProperties().setProperty("mail.smtp.auth",
                enableAuthen);
        this.getJavaMailProperties().setProperty("mail.smtp.starttls.enable",
                enableStartTls);
    }

    /**
     * <p>
     * Validate email address.
     * </p>
     * @param email {@link String}
     * @throws AddressException ae
     */
    public void validateEmail(final String email) throws AddressException {
        new InternetAddress(email).validate();
    }

    /**
     * <p>
     * Send an email.
     * </p>
     * @param recipientMail {@link String}
     * @param subject {@link String}
     * @param content {@link String}
     * @throws MessagingException me
     * @throws UnsupportedEncodingException ue
     */
    public void sendMail(final String recipientMail, final String subject,
                         final String content) throws MessagingException,
            UnsupportedEncodingException {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            MimeMessage mimeMessage = this.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage, true, "UTF-8");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(this.fromEmail, this.fromAlias);
            mimeMessageHelper.setTo(recipientMail);
            mimeMessageHelper.setText(content, true);

            this.send(mimeMessage);
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("Send mail successfully!");
            }
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }

    }
}
