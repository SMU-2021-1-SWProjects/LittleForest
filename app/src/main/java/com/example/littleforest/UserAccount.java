package com.example.littleforest;

public class UserAccount {
    public String ac_name;
    public String ac_email;
    public String ac_psw;


    public UserAccount()
    {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


    public UserAccount(String ac_name, String ac_email, String ac_psw) {
        this.ac_name = ac_name;
        this.ac_email = ac_email;
        this.ac_psw = ac_psw;
    }

    public String getUserAccount_Name() {
        return ac_name;
    }
    public void setUserAccount_Name(String ac_name) {
        this.ac_name = ac_name;
    }

    public String getUserAccount_email() {return ac_email; }
    public void setUserAccount_email(String ac_email) {
        this.ac_email = ac_email;
    }

    public String getUserAccount_Psw() {
        return ac_psw;
    }
    public void setUserAccount_Psw(String ac_psw) { this.ac_psw = ac_psw; }


    @Override
    public String toString() {
        return "User{" +
                "Account_Name='" + ac_name + '\'' +
                ", Account_email='" + ac_email + '\'' +
                ", Account_psw='" + ac_psw + '\'' +
                '}';
    }




}
