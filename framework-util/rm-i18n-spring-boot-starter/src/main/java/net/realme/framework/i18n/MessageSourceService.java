package net.realme.framework.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.i18n
 *
 * @author 91000044
 * @date 2018/8/6 10:31
 */
@Component
public class MessageSourceService {

    @Autowired
    private MessageSource messageSource;

    public String getMsgByCode(String code) {
        return messageSource.getMessage(code, null,  code,null);
    }

//    public String getMsgByCode(String code,Object... args){
//        return messageSource.getMessage(code, args, Locale.CHINA);
//    }
}
