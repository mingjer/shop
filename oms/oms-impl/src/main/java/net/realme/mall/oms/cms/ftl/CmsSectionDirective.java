package net.realme.mall.oms.cms.ftl;

import freemarker.core.Environment;
import freemarker.template.*;
import net.realme.mall.oms.cms.facade.CmsComponentService;
import net.realme.mall.oms.domain.cms.CmsComponentDto;
import net.realme.mall.oms.domain.cms.CmsComponentTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

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
public class CmsSectionDirective implements TemplateDirectiveModel {

    @Autowired
    private CmsComponentService cmsComponentService;

    @Override
    public void execute(Environment env,
                        Map map, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {

        if (!map.containsKey("name") || map.get("name") == null) {
            throw new TemplateException("parameter [name] cannot be empty", env);
        }
        String name = map.get("name").toString();
        Writer out = env.getOut();
        String siteCode = env.getDataModel().get("siteCode").toString();
        CmsComponentDto cmsComponentDto = cmsComponentService.get(CmsComponentTypeEnum.TYPE_SECTION.getValue(), name, siteCode);
        if (cmsComponentDto != null) {
            out.write(cmsComponentDto.getContent());
        }
    }
}
