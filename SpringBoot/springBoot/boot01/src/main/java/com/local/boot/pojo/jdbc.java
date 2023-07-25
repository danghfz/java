package com.local.boot.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 党
 * @version 1.0
 * 2022/5/27   0:05
 */
//@Component //只有在容器中的组件，才会拥有SpringBoot提供的功能
@ConfigurationProperties(prefix = "jdbc")
public class jdbc {
    private String driver;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    private String url;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public String toString() {
        return "jdbc{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
