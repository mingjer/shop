package net.realme.mall.oms.cms.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.cms.req
 *
 * @author 91000044
 * @date 2018/7/28 19:57
 */
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComponentsReq {

    @ApiModelProperty(value = "组件类型，[sec|var]")
    @NotBlank
    private String type;
    @ApiModelProperty(value = "组件名称")
    @NotBlank
    private String name;
    @ApiModelProperty(value = "本地化配置", dataType = "form")
    @NotEmpty
    private List<ComponentL10N> localization;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ComponentL10N> getLocalization() {
        return localization;
    }

    public void setLocalization(List<ComponentL10N> localization) {
        this.localization = localization;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ComponentL10N {

        private Integer id;
        private String siteCodes;
        private String content;

        public ComponentL10N() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSiteCodes() {
            return siteCodes;
        }

        public void setSiteCodes(String siteCodes) {
            this.siteCodes = siteCodes;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
