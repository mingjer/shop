package net.realme.mall.product.domain.request;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/9/13 21:29
 * 更新spu属性时所使用的model
 */
public class SpuAttrUpdate implements Serializable {

    private static final long serialVersionUID = -4099395525410722852L;
    /**
     * 属性id
     */
    private Integer id;
    /**
     * 属性值
     */
    private String attrVal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttrVal() {
        return attrVal;
    }

    public void setAttrVal(String attrVal) {
        this.attrVal = attrVal;
    }
}
