package com.artemy.wifistuff;

/**
 * Created by Win10 User on 17.04.2018.
 */

public class WifiNetwork {
    String SSID;
    String BSSID;
    String capabilities;
    String password;

    public WifiNetwork(String SSID, String BSSID, String capabilities, String password) {
        this.SSID = SSID;
        this.BSSID = BSSID;
        this.capabilities = capabilities;
        this.password = password;
    }

    public String getSSID() {
        return SSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public String getPassword() {
        return password;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public void setBSSID(String BSSID) {
        this.BSSID = BSSID;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "WifiNetwork{" +
                "SSID='" + SSID + '\'' +
                ", BSSID='" + BSSID + '\'' +
                ", capabilities='" + capabilities + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
