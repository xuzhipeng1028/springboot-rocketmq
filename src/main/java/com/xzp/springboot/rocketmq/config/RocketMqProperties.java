package com.xzp.springboot.rocketmq.config;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * RocketMQ属性配置类
 * @author xuzhipeng
 * @date 2022/2/8
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMqProperties {

    /**
     * AccessKey, 用于标识、校验用户身份
     */
    private String accessKey;

    /**
     * SecretKey, 用于标识、校验用户身份
     */
    private String secretKey;

    /**
     * Name Server地址
     */
    private String nameSrvAddr;

    /**
     * topic
     */
    private String topic;

    /**
     * 消费者组
     */
    private String groupId;

    /**
     * 消息tag
     */
    private String tag;

    /**
     * 获取rocketMQ基本配置信息
     * @return Properties
     */
    public Properties getMqProperties() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.AccessKey, this.accessKey);
        properties.setProperty(PropertyKeyConst.SecretKey, this.secretKey);
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, this.nameSrvAddr);
        return properties;
    }
}
