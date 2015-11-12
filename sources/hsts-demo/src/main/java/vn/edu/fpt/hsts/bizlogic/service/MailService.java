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
import vn.edu.fpt.hsts.bizlogic.service.processor.MailProcessor;
import vn.edu.fpt.hsts.common.IConsts;
import vn.edu.fpt.hsts.persistence.entity.Account;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailService extends JavaMailSenderImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    public static final String SUBJECT_MAIL = "[HSTS]Your Account Information";

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

    @Value("${hsts.mail.number.mail}")
    private int maxMailPerCron;

    @Value("${hsts.mail.sleepy.time}")
    private int sleepyTime;

    /**
     * The {@link MailProcessor}.
     */
    @Autowired
    private MailProcessor mailProcessor;

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
    public boolean sendMail(final String recipientMail, final String subject,
                         final String content){
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
            LOGGER.info("Send mail successfully!");
            return true;
        } catch (Exception e) {
            return false;
        }
        finally {
            LOGGER.info(IConsts.END_METHOD);
        }

    }


    /**
     * Read an synchroize queue of mail need to sending
     * Try to send.
     * If do not success -> re push to queue
     */
    public void mailingScheduler() {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            boolean canContinue = true;
            int count = 0;
            while (canContinue && (count < maxMailPerCron)) {
                if (mailProcessor.isEmpty()) {
                    canContinue = false;
                    continue;
                }
                /**
                 * 2 ways:
                 *      | Peek element first, If success: remove
                 *      | Poll element, If fail: re-add  -> should use for avoiding redundant mail account
                 */
                final Account account = mailProcessor.poll();
                boolean result = this.sendMail(account.getEmail(), MailService.SUBJECT_MAIL,
                        "Username : " + account.getUsername() + "<br/>" +
                        "Password : " + account.getPassword());
                if (!result) {
                    mailProcessor.offer(account);
                }
                count++;
                // Sleepy time
                Thread.sleep(sleepyTime);
            }
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException at: {}", e.getMessage());
            return;
        } catch (Exception e) {
            LOGGER.error("Exception at: {}", e.getMessage());
            return;
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }

    public void pushMail(final Account account) {
        LOGGER.info(IConsts.BEGIN_METHOD);
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("account[{}]", account.getUsername());
            }
            mailProcessor.offer(account);
        } finally {
            LOGGER.info(IConsts.END_METHOD);
        }
    }
}
