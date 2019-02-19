package net.realme.mall.basics.facade;

import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.dto.IndiaPinCodeDto;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.basics.facade
 *
 * @author 91000044
 * @date 2018/8/27 17:24
 */
public interface IndiaPinCodeService {

	ResultT<ResultList<IndiaPinCodeDto>> list(int page, int limit);

	ResultT<ResultList<IndiaPinCodeDto>> listAll();

	ResultT<IndiaPinCodeDto> getByPinCode(String pinCode);

	ResultT<Integer> addPinCode(IndiaPinCodeDto indiaPinCodeDto);

	ResultT<Integer> updatePinCode(IndiaPinCodeDto indiaPinCodeDto);

}
