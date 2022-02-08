package com.xzp.springboot.rocketmq.listener;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * rocketmq消息监听器
 * @author xuzhipeng
 * @date 2022/2/8
 */
@Slf4j
@Component
public class DemoMessageListener implements MessageListener {

    @Override
    public Action consume(Message message, ConsumeContext consumeContext) {
        String messageBody = new String(message.getBody());
        log.info("消息消费,topic:[{}],tag:[{}],key:[{}],msgId:[{}],body:[{}]", message.getTopic(), message.getTag(), message.getKey(), message.getMsgID(), messageBody);
        try {
            // TODO: 2022/2/8 实现消息消费逻辑 
            return Action.CommitMessage;
        } catch (Exception e) {
            log.error("消息消费异常,消息体:[{}],异常信息:[{}]", messageBody, e.getMessage(), e);
            return Action.ReconsumeLater;
        }
    }
}
