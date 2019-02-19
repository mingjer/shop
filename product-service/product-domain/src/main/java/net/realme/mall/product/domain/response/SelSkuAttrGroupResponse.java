package net.realme.mall.product.domain.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 91000156 on 2018/8/29 21:21
 * SKU属性组
 */
public class SelSkuAttrGroupResponse implements Serializable {

    private static final long serialVersionUID = -6039451344769713173L;

    // 整体供选择的属性组
    private List<ProductAttributeResponse> attrList;

    // 属性组集合
    private List<ProductAttributeValueResponse> attrValList;

    // 已选择完属性组
    private List<ProductSkuAttributeResponse> selOption;

    public List<ProductAttributeResponse> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<ProductAttributeResponse> attrList) {
        this.attrList = attrList;
    }

    public List<ProductAttributeValueResponse> getAttrValList() {
        return attrValList;
    }

    public void setAttrValList(List<ProductAttributeValueResponse> attrValList) {
        this.attrValList = attrValList;
    }

    public List<ProductSkuAttributeResponse> getSelOption() {
        return selOption;
    }

    public void setSelOption(List<ProductSkuAttributeResponse> selOption) {
        this.selOption = selOption;
    }
}
