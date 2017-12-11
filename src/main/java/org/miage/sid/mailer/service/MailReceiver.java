package org.miage.sid.mailer.service;

import org.miage.sid.mailer.misc.Logger;
import org.miage.sid.mailer.misc.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.util.*;

@Service
public class MailReceiver {
    @Autowired
    private Logger logger;

    @Value("${pop.host}")
    private String host = "";
    @Value("${pop.username}")
    private String username;
    @Value("${pop.password}")
    private String password;

    public MailReceiver() {

    }

    private String getText(Part p) throws
            MessagingException, IOException {

        if (p.isMimeType("text/*")) {
            String s = (String)p.getContent();
            Boolean textIsHtml = p.isMimeType("text/html");
            return s;
        }

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart)p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }

        return null;
    }
    public Map<String,String> getMail(Message message) throws IOException, MessagingException {
        Map<String, String> map = new HashMap<>();
        Address[] froms = message.getFrom();
        String email = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
        map.put("from", email);
        String subject = message.getSubject();
        map.put("subject", subject);
        logger.log(String.format("Message de %s (sujet %s)",
                email,
                subject));

        String contenu = "";
        Object content = message.getContent();
        if (content instanceof Multipart) {
            Multipart multipart = (Multipart) content;
            for (int x = 0; x < multipart.getCount(); x++) {
                contenu = contenu + "\n" + getText(multipart.getBodyPart(x));
            }
        } else {
            contenu = content.toString();
        }
        map.put("content", contenu);
        logger.log("Contenu " + contenu);

        return map;
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<Mail> getMails(Integer quantity) throws MessagingException, IOException {
        logger.log("Récupération messages");
        List<Mail> listMessage = new ArrayList<>();
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("pop3");
        store.connect(getHost(), getUsername(), getPassword());

        Folder[] f = store.getDefaultFolder().list();
        for(Folder fd:f)
            System.out.println(">> "+fd.getName());

        Folder inbox = f[0];
        inbox.open(Folder.READ_ONLY);
        Message[] messages = inbox.getMessages();
        Integer maxQty = quantity;
        if (messages.length < maxQty) {
            maxQty = messages.length;
        }
        for (Integer i = 0; i< maxQty; i++) {
            Map<String, String> message = getMail(messages[i]);
            listMessage.add(new Mail(message));
            System.out.println(message);
        }
        return listMessage;
    }



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

}
