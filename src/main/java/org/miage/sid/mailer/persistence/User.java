package org.miage.sid.mailer.persistence;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlIDREF;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    @OneToMany(mappedBy = "user")
    private List<UserRole> roles;

    public User(){
        this.accounts = new ArrayList<Account>();
    }

    public User(User user) {
        this.username = user.username;
        this.password = user.password;
        this.accounts = new ArrayList<>(user.getAccounts());
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

}
