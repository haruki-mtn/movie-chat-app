package jp.mtn.moviechat.model;

import java.io.Serializable;

public class Register implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String mail;
    private String pass;

    public Register() {}
    public Register(String name, String mail, String pass) {
        this.name = name;
        this.mail = mail;
        this.pass = pass;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }
}
