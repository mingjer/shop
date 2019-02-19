package net.realme.mall.payment.beantool;

import net.realme.mall.payment.domain.PaymentResponse;
import net.realme.mall.payment.domain.PaymentTxnLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.payment.beantool
 *
 * @author 91000044
 * @date 2018/9/5 23:56
 */
@Mapper(componentModel = "spring")
public interface PaymentTxnConverter {

    @Mappings({
            @Mapping(target="response", source="paymentResponse.rawText"),
    })
    PaymentTxnLogDto toPaymentTxnLogDto(PaymentResponse paymentResponse);
}
