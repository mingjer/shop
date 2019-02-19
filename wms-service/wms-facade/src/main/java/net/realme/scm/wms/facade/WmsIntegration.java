package net.realme.scm.wms.facade;

import net.realme.framework.util.dto.ResultT;
import net.realme.scm.wms.domain.NotificationResponse;
import net.realme.scm.wms.domain.PushOrderPayload;
import net.realme.scm.wms.domain.PushProductPayload;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.scm.wms.facade
 *
 * @author 91000044
 * @date 2018/9/19 14:42
 */
public interface WmsIntegration {

    ResultT<Boolean> pushProduct(PushProductPayload payload);

    ResultT<Boolean> pushOrder(List<? extends PushOrderPayload> payload);

    ResultT<Boolean> notifyOrder(NotificationResponse notificationResponse);

    ResultT<Boolean> notifyInBound(NotificationResponse notificationResponse);

    ResultT<Boolean> notifyInventory(NotificationResponse notificationResponse);
}
