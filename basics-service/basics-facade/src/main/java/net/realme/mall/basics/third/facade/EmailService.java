package net.realme.mall.basics.third.facade;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.third.facade
 *
 * @author 91000044
 * @date 2018/8/4 17:56
 */
public interface EmailService {

    void send(String subject, String text, String... sendTo);

    void sendAttachment(String subject, String text, String attachmentPath, String... sendTo);
}
