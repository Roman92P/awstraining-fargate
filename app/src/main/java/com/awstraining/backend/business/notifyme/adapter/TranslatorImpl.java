package com.awstraining.backend.business.notifyme.adapter;

import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import com.awstraining.backend.business.notifyme.NotifyMeDO;
import com.awstraining.backend.business.notifyme.Translator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TranslatorImpl implements Translator {

    private static final Logger LOGGER = LogManager.getLogger(TranslatorImpl.class);
    

    
    // TODO: lab2
    //  1. Inject AWS AmazonTranslate from configuration TranslatorConfig.
    private final AmazonTranslate amazonTranslate;
    @Autowired
    public TranslatorImpl(AmazonTranslate amazonTranslate) {

        this.amazonTranslate = amazonTranslate;
    }
    
    @Override
    public String translate(NotifyMeDO notifyMeDO) {
        // TODO: lab2
        //  1. Create translate text request.
        //  2. Call translate.
        TranslateTextResult result = amazonTranslate
            .translateText(new TranslateTextRequest().withText(notifyMeDO.text()))
            .withSourceLanguageCode(notifyMeDO.sourceLc())
            .withTargetLanguageCode(notifyMeDO.targetLc());
        //  3. Log information about successful translated message.
        LOGGER.info("Successfully translated text: " + notifyMeDO.text() + " to: " + result.getTargetLanguageCode());
        //  4. Return translated message
        return result.getTranslatedText();
    }
}
