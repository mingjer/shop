package net.realme.mall.oms.cms.ftl;

import freemarker.core.Environment;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import net.realme.mall.basics.dto.SiteEnum;
import net.realme.mall.basics.dto.TranslationDto;
import net.realme.mall.basics.facade.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: ftl
 *
 * @author 91000044
 * @date 2018/7/31 20:51
 */
@Component
public class CmsTranslationMethod implements TemplateMethodModelEx {

    @Autowired
    private TranslationService translationService;

    @Override
    public Object exec(List args) throws TemplateModelException {
        if(args.size() != 1){
            throw new TemplateModelException("Wrong arguments");
        }
        String name = String.valueOf(args.get(0));
        String siteCode = Environment.getCurrentEnvironment().getDataModel().get("siteCode").toString();
        List<TranslationDto> translationDtos = translationService.listValues(name, siteCode).getData();
        String val = name;
        if (translationDtos != null && !translationDtos.isEmpty()) {
            for (TranslationDto translationDto: translationDtos) {
                if (!SiteEnum.DEFAULT_SITE.getValue().equals(translationDto.getSiteCode())) {
                    return translationDto.getT9nValue();
                } else {
                    val = translationDto.getT9nValue();
                }
            }
        }
        return val;
    }
}
