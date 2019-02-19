package net.realme.mall.product.domain.response;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/8/30 14:10
 */
public class ProductAttributeResponse implements Serializable {

    private static final long serialVersionUID = 5012501703354826431L;

    /**
     * 属性ID
     */
    private Integer id;

    /**
     * 属性名
     */
    private String name;

    /**
     * 属性描述
     */
    private String description;

    /**
     * 属性排列序号
     */
    private Integer sequence;

    /**
     * 获取属性名
     *
     * @return name - 属性名
     */
    public String getName() {
        return name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 设置属性名
     *
     * @param name 属性名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取属性描述
     *
     * @return description - 属性描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置属性描述
     *
     * @param description 属性描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取属性排列序号
     *
     * @return sequence - 属性排列序号
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置属性排列序号
     *
     * @param sequence 属性排列序号
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
