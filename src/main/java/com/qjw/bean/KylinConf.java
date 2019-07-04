package com.qjw.bean;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : qjw
 * @data : 2019/6/13
 */
@Component
@ConfigurationProperties(prefix = "kylin")
public class KylinConf {
    private int port;

    @Override
    public String toString() {
        return "KylinConf{" +
                "port='" + port + '\'' +
                '}';
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
