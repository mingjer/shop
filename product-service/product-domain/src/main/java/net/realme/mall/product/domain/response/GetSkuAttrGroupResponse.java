package net.realme.mall.product.domain.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by 91000156 on 2018/8/30 14:47
 */
public class GetSkuAttrGroupResponse implements Serializable {

    private static final long serialVersionUID = 3008159250530288089L;

    private List<ProductAttributeResponse> attrList;

    private List<ProductAttributeValueResponse> attrValList;

    private Map<String, List<DefaultOptionRetModel>> selOptions;

    public Map<String, List<DefaultOptionRetModel>> getSelOptions() {
        return selOptions;
    }

    public void setSelOptions(Map<String, List<DefaultOptionRetModel>> selOptions) {
        this.selOptions = selOptions;
    }

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
}
