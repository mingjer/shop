package net.realme.mall.product.beantool;

import net.realme.mall.product.domain.SkuDetailUrisViewDto;
import net.realme.mall.product.model.SkuDetailUrisView;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by 91000156 on 2018/9/8 16:58
 */
@Mapper(componentModel = "spring")
public interface DetailPageJumpUriConvert {

    List<SkuDetailUrisViewDto> toDetailPageJumpUriDto(List<SkuDetailUrisView> detailPageJumpUriList);

}
