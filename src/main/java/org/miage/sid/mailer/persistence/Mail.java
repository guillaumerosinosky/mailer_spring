package org.miage.sid.mailer.persistence;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(indexes = {
        @Index(name="mail_date", columnList="receiveDate")
})
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String sender;

    @Column
    private String to;

    @Column
    private String cc;

    @Column
    private String cci;

    @Column
    private String subject;

    @Column
    private Date receiveDate;

    @Column
    private String content;

    @ManyToOne(fetch=FetchType.LAZY)
    Account account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCci() {
        return cci;
    }

    public void setCci(String cci) {
        this.cci = cci;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
