package jp.mtn.moviechat.model;

import java.io.Serializable;

public class Login implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mail;
    private String pass;

    public Login() {}
    public Login(String mail, String pass) {
        this.mail = mail;
        this.pass = pass;
    }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }
}
