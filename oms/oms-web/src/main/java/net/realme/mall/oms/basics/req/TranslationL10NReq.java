package net.realme.mall.oms.basics.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.io.Serializable;

import java.util.List;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class TranslationL10NReq implements Serializable {
    @ApiModelProperty(value = "翻译的值")
    private String t9nKey;

    @ApiModelProperty(value = "翻译的value值封装", dataType = "form")
    private List<TranslationL10N> translationL10N;

    public String getT9nKey() {
        return t9nKey;
    }

    public void setT9nKey(String t9nKey) {
        this.t9nKey = t9nKey;
    }

    public List<TranslationL10N> getTranslationL10N() {
        return translationL10N;
    }

    public void setTranslationL10N(List<TranslationL10N> translationL10N) {
        this.translationL10N = translationL10N;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TranslationL10N{
        private String t9nValue;
        private String siteCodes;

        public String getT9nValue() {
            return t9nValue;
        }

        public void setT9nValue(String t9nValue) {
            this.t9nValue = t9nValue;
        }

        public String getSiteCodes() {
            return siteCodes;
        }

        public void setSiteCodes(String siteCodes) {
            this.siteCodes = siteCodes;
        }
    }

}
