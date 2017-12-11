package org.miage.sid.mailer.misc;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Mail {
    private String from;

    @NotEmpty
    private List<String> to;
    private List<String> cc;
    private List<String> cci;

    @NotEmpty
    private String subject;

    private String content;
    private Date date;

    public Mail() {
        to = new ArrayList<>();
        cc = new ArrayList<>();
        cci= new ArrayList<>();
    }

    public Mail(Map<String, String> map) {
        from = map.get("from");
        subject = map.get("subject");
        content = map.get("content");

    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getCci() {
        return cci;
    }

    public void setCci(List<String> cci) {
        this.cci = cci;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTo(String mail) {
        to.clear();
        to.add(mail);
    }
}
