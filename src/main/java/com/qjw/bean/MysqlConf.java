package com.qjw.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author : qjw
 * @data : 2019/6/13
 */
public class MysqlConf {

    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.username}")
    private String username;
    private String password;
    private String driveClass = "com.mysql.jdbc.Driver";

    @Override
    public String toString() {
        return "MysqlConf{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driveClass='" + driveClass + '\'' +
                '}';
    }
}
