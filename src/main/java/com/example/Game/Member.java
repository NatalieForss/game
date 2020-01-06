package com.example.Game;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class Member {
    private int id;
    @Size(min=5, max=30)
    private String anvandarNamn;
    @Size(min=5, max=30)
    private String losenord;

    @Email
    private String emailAdress;


    public Member() {
    }

    public Member(String anvandarNamn, String losenord, String emailAdress) {
        this.anvandarNamn = anvandarNamn;
        this.losenord = losenord;
        this.emailAdress = emailAdress;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getAnvandarNamn() {
        return anvandarNamn;
    }

    public void setAnvandarNamn(String anvandarNamn) {
        this.anvandarNamn = anvandarNamn;
    }

    public String getLosenord() {
        return losenord;
    }

    public void setLosenord(String losenord) {
        this.losenord = losenord;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }
}

