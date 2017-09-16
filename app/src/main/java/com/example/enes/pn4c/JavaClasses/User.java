package com.example.enes.pn4c.JavaClasses;

/**
 * Created by erama on 9/16/17.
 */

public class User {

    private String NickName;
    private String Email;
    private String Gender;
    private String Age;
    private String Password;
    private String RegisterDate;

    public User(String Email, String NickName, String Gender, String Age, String Password, String RegisterDate){
        this.NickName = NickName;
        this.Email = Email;
        this.Gender = Gender;
        this.Age = Age;
        this.Password = Password;
        this.RegisterDate = RegisterDate;
    }




    //Getter and Setters
    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(String registerDate) {
        RegisterDate = registerDate;
    }
}
