package net.realme.mall.product.domain;

/**
 * Created by 91000156 on 2018/9/18 10:51
 */
public class ProductFittingDto {

    /**
     * 自增id
     */
    private Integer id;

    /**
     * 主件sku_id
     */
    private Integer mainSkuId;

    /**
     * 配件sku_id
     */
    private Integer partSkuId;

    /**
     * 配件序号
     */
    private Integer sequence;

    /**
     * 创建时间
     */
    private Long createdTime;

    /**
     * 更新时间
     */
    private Long updatedTime;

    /**
     * 创建者
     */
    private Integer createdBy;

    /**
     * 更新者
     */
    private Integer updatedBy;

    /**
     * 状态：0 正常，-1 已删除
     */
    private Byte status;

    /**
     * 获取自增id
     *
     * @return id - 自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增id
     *
     * @param id 自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取主件sku_id
     *
     * @return main_sku_id - 主件sku_id
     */
    public Integer getMainSkuId() {
        return mainSkuId;
    }

    /**
     * 设置主件sku_id
     *
     * @param mainSkuId 主件sku_id
     */
    public void setMainSkuId(Integer mainSkuId) {
        this.mainSkuId = mainSkuId;
    }

    /**
     * 获取配件sku_id
     *
     * @return part_sku_id - 配件sku_id
     */
    public Integer getPartSkuId() {
        return partSkuId;
    }

    /**
     * 设置配件sku_id
     *
     * @param partSkuId 配件sku_id
     */
    public void setPartSkuId(Integer partSkuId) {
        this.partSkuId = partSkuId;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Long getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新时间
     *
     * @return updated_time - 更新时间
     */
    public Long getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * 获取创建者
     *
     * @return created_by - 创建者
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建者
     *
     * @param createdBy 创建者
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取更新者
     *
     * @return updated_by - 更新者
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新者
     *
     * @param updatedBy 更新者
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取状态：0 正常，-1 已删除
     *
     * @return status - 状态：0 正常，-1 已删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态：0 正常，-1 已删除
     *
     * @param status 状态：0 正常，-1 已删除
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
