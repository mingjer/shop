package net.realme.mall.oms.basics.resp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TranslationL10NResp implements Serializable {

    private String t9nKey;
    private String t9nValue;

    private List<String> siteCodesArr;

    public TranslationL10NResp(String t9nKey, String t9nValue) {
        this.t9nKey = t9nKey;
        this.t9nValue = t9nValue;
        this.siteCodesArr = new ArrayList<>(50);
    }

    public String getT9nValue() {
        return t9nValue;
    }

    public void setT9nValue(String t9nValue) {
        this.t9nValue = t9nValue;
    }

    public List<String> getSiteCodesArr() {
        return siteCodesArr;
    }

    public void setSiteCodesArr(List<String> siteCodesArr) {
        this.siteCodesArr = siteCodesArr;
    }

    public String getT9nKey() {
        return t9nKey;
    }

    public void setT9nKey(String t9nKey) {
        this.t9nKey = t9nKey;
    }
}
