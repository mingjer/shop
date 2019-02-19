package net.realme.mall.oms.cms.ftl;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import net.realme.mall.oms.domain.cms.CmsComponentTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.common
 *
 * @author 91000044
 * @date 2018/7/31 20:35
 */
@Component
public class FreemarkerConfiguration {

    @Autowired
    private Configuration configuration;

    @Autowired
    private CmsSectionDirective cmsSectionDirective;

    @Autowired
    private CmsVariableDirective cmsVariableDirective;

    @Autowired
    private CmsTranslationMethod cmsTranslationMethod;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        configuration.setWrapUncheckedExceptions(true);
        configuration.setSharedVariable(CmsComponentTypeEnum.TYPE_SECTION.getValue(), cmsSectionDirective);
        configuration.setSharedVariable(CmsComponentTypeEnum.TYPE_VARIABLE.getValue(), cmsVariableDirective);
        configuration.setSharedVariable("t9n", cmsTranslationMethod);
    }
}
