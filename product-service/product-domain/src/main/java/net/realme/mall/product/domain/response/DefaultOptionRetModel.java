package net.realme.mall.product.domain.response;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/9/3 16:25
 */
public class DefaultOptionRetModel implements Serializable {

    private static final long serialVersionUID = 8615388694623651646L;

    private String attrId;
    private String attrValId;

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getAttrValId() {
        return attrValId;
    }

    public void setAttrValId(String attrValId) {
        this.attrValId = attrValId;
    }
}
