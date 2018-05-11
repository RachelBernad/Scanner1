package com.artemy.wifistuff;
import java.io.Serializable;
/**
 * Created by natal on 02/04/2018.
 */

public class User implements Serializable {
    String nickname;
    String password;
    String first_name;
    String last_name;
    String phone;
    long id;

    public User(String nickname, String password, String first_name, String last_name, String phone, long id) {
        this.nickname = nickname;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.id = id;
    }

    public User(String nickname, String password, String first_name, String last_name, String phone) {
        this.nickname = nickname;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone='" + phone + '\'' +
                ", id=" + id +
                '}';
    }
}
