package org.miage.sid.mailer.service;

import org.miage.sid.mailer.misc.Logger;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
        @Autowired
        private Logger logger;

        @Value("${smtp.host}")
        private String host;

        @Value("${smtp.username}")
        private String username;

        @Value("${smtp.password}")
        private String password;

        @Value("${smtp.from}")
        private String from;


        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @PreAuthorize("hasAnyRole('ROLE_USER')")
        public void sendMail(String recipient, String subject, String message) {
            Email email = new EmailBuilder()
                    .from(from, from)
                    .to(recipient)
                    .subject(subject)
                    .text(message)
                    .build();

            new Mailer(new ServerConfig("smtp.laposte.net", 25, "mail.adresse.sid", "MailSid2017")).sendMail(email);
        }

        public Logger getLogger() {
            return logger;
        }

        public void setLogger(Logger logger) {
            this.logger = logger;
        }

}
