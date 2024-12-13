package com.awstraining.backend.business.notifyme.adapter;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.awstraining.backend.business.notifyme.MessageSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageSnsAWSSender implements MessageSender {

    private static final Logger LOGGER = LogManager.getLogger(MessageSnsAWSSender.class);

    // TODO: lab1
    //  1. Inject AWS AmazonsSNS from configuration SNSConfig.
    //  2. Make sure that you created new value in parameter store with arn of sns topic.
    //  3. Inject parameter with @Value annotation through constructor.
    private AmazonSNS amazonSNS;

    private String topicSns;

    @Autowired
    public MessageSnsAWSSender(AmazonSNS amazonSNS, @Value("${/config/backend_EMEA-TEST/notification.topicarn}") String topicSns) {
        this.amazonSNS = amazonSNS;
        this.topicSns = topicSns;
    }

    @Override
    public void send(String text) {
        // TODO: lab1
        //  1. Create publish request.
        //  2. Publish request with sns.
        //  3. Log information about successful sent message to topic with topic arn and message id.
        PublishRequest publishRequest = new PublishRequest(topicSns, text);
        PublishResult result = amazonSNS.publish(publishRequest);
        LOGGER.info(
            "Send message to topic: " + topicSns + ", with text: " + text + ", messageId: " + result.getMessageId());
    }
}
