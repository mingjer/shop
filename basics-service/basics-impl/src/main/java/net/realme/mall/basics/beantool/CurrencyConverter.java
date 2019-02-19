package net.realme.mall.basics.beantool;

import net.realme.mall.basics.dto.CurrencyDto;
import net.realme.mall.basics.model.Currency;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.basics.beantool
 *
 * @author 91000044
 * @date 2018/7/25 10:22
 */
@Mapper(componentModel = "spring")
public interface CurrencyConverter {

    Currency fromCurrencyDto(CurrencyDto currencyDto);

    CurrencyDto toCurrencyDto(Currency currency);

    List<CurrencyDto> toCurrencyDtoList(List<Currency> currencyList);
}
