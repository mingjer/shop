package net.realme.scm.wms.model;

import javax.persistence.*;

@Table(name = "wms_warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "site_code")
    private String siteCode;

    /**
     * 物流服务商
     */
    private String provider;

    @Column(name = "warehouse_code")
    private String warehouseCode;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "warehouse_type")
    private Byte warehouseType;

    private String gstin;

    private Byte status;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return site_code
     */
    public String getSiteCode() {
        return siteCode;
    }

    /**
     * @param siteCode
     */
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode == null ? null : siteCode.trim();
    }

    /**
     * 获取物流服务商
     *
     * @return provider - 物流服务商
     */
    public String getProvider() {
        return provider;
    }

    /**
     * 设置物流服务商
     *
     * @param provider 物流服务商
     */
    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    /**
     * @return warehouse_code
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * @param warehouseCode
     */
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode == null ? null : warehouseCode.trim();
    }

    /**
     * @return warehouse_name
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * @param warehouseName
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    /**
     * @return warehouse_type
     */
    public Byte getWarehouseType() {
        return warehouseType;
    }

    /**
     * @param warehouseType
     */
    public void setWarehouseType(Byte warehouseType) {
        this.warehouseType = warehouseType;
    }

    /**
     * @return gstin
     */
    public String getGstin() {
        return gstin;
    }

    /**
     * @param gstin
     */
    public void setGstin(String gstin) {
        this.gstin = gstin == null ? null : gstin.trim();
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return created_at
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return updated_at
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}