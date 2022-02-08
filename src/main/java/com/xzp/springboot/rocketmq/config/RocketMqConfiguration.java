package com.xzp.springboot.rocketmq.config;

import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.xzp.springboot.rocketmq.listener.DemoMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * RocketMQ配置类
 * @author xuzhipeng
 * @date 2022/2/8
 */
@Configuration
public class RocketMqConfiguration {

    @Resource
    private RocketMqProperties rocketMqProperties;

    @Resource
    private DemoMessageListener demoMessageListener;

    /**
     * 生产者
     * @return producerBean
     */
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean producerBean() {
        ProducerBean producer = new ProducerBean();
        Properties properties = rocketMqProperties.getMqProperties();
        producer.setProperties(properties);
        return producer;
    }

    /**
     * 消费者
     * @return demoConsumer
     */
    @Bean(name = "demoConsumer", initMethod = "start", destroyMethod = "shutdown")
    public ConsumerBean demoConsumer() {
        ConsumerBean consumerBean = new ConsumerBean();

        //配置文件
        Properties properties = rocketMqProperties.getMqProperties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, rocketMqProperties.getGroupId());
        //将消费者线程数固定为20个 20为默认值
        properties.setProperty(PropertyKeyConst.ConsumeThreadNums, "20");
        consumerBean.setProperties(properties);

        //订阅关系
        Map<Subscription, MessageListener> subscriptionTable = new HashMap<>(1);
        Subscription subscription = new Subscription();
        subscription.setTopic(rocketMqProperties.getTopic());
        subscription.setExpression(rocketMqProperties.getTag());
        subscriptionTable.put(subscription, demoMessageListener);

        consumerBean.setSubscriptionTable(subscriptionTable);
        return consumerBean;
    }

}
