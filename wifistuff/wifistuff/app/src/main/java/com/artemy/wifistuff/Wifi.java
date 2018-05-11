package com.artemy.wifistuff;

import java.io.Serializable;

/**
 * Created by natal on 11/03/2018.
 */

public class Wifi  implements Serializable
{
    long id;
    String wifi_name,wifi_pass,wifi_pwtype,bssid,mongo_id;

    public Wifi(long id, String wifi_name, String wifi_pass, String wifi_pwtype, String bssid, String mongo_id) {
        this.id = id;
        this.wifi_name = wifi_name;
        this.wifi_pass = wifi_pass;
        this.wifi_pwtype = wifi_pwtype;
        this.bssid = bssid;
        this.mongo_id = mongo_id;
    }

    public Wifi(String wifi_name, String wifi_pass, String wifi_pwtype, String bssid, String mongo_id) {
        this.wifi_name = wifi_name;
        this.wifi_pass = wifi_pass;
        this.wifi_pwtype = wifi_pwtype;
        this.bssid = bssid;
        this.mongo_id = mongo_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWifi_name() {
        return wifi_name;
    }

    public void setWifi_name(String wifi_name) {
        this.wifi_name = wifi_name;
    }

    public String getWifi_pass() {
        return wifi_pass;
    }

    public void setWifi_pass(String wifi_pass) {
        this.wifi_pass = wifi_pass;
    }

    public String getWifi_pwtype() {
        return wifi_pwtype;
    }

    public void setWifi_pwtype(String wifi_pwtype) {
        this.wifi_pwtype = wifi_pwtype;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getMongo_id() {
        return mongo_id;
    }

    public void setMongo_id(String mongo_id) {
        this.mongo_id = mongo_id;
    }

    @Override
    public String toString() {
        return "Wifi{" +
                "id=" + id +
                ", nickname='" + wifi_name + '\'' +
                ", username='" + wifi_pass + '\'' +
                ", password='" + wifi_pwtype + '\'' +
                ", bssid='" + bssid + '\'' +
                ", mongo_id='" + mongo_id + '\'' +
                '}';
    }
}
