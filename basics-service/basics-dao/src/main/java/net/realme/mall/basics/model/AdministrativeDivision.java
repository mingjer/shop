package net.realme.mall.basics.model;

import javax.persistence.*;

@Table(name = "administrative_division")
public class AdministrativeDivision {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 划分ID
     */
    @Column(name = "division_id")
    private String divisionId;

    /**
     * 划分编码
     */
    @Column(name = "division_code")
    private String divisionCode;

    /**
     * 划分类型
     */
    @Column(name = "division_type")
    private String divisionType;

    /**
     * 划分名称
     */
    @Column(name = "division_name")
    private String divisionName;

    /**
     * 父级ID
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 父级名称
     */
    @Column(name = "parent_name")
    private String parentName;

    /**
     * ID全路径，冒号分隔
     */
    @Column(name = "id_path")
    private String idPath;

    /**
     * 名称全路径，冒号分隔
     */
    @Column(name = "name_path")
    private String namePath;

    /**
     * 是否有子划分
     */
    @Column(name = "has_child")
    private Byte hasChild;

    /**
     * 国家编码(两位)
     */
    @Column(name = "country_code")
    private String countryCode;

    private Byte status;

    /**
     * 显示顺序，越小排越前
     */
    private Integer sequence;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

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
     * 获取划分ID
     *
     * @return division_id - 划分ID
     */
    public String getDivisionId() {
        return divisionId;
    }

    /**
     * 设置划分ID
     *
     * @param divisionId 划分ID
     */
    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId == null ? null : divisionId.trim();
    }

    /**
     * 获取划分编码
     *
     * @return division_code - 划分编码
     */
    public String getDivisionCode() {
        return divisionCode;
    }

    /**
     * 设置划分编码
     *
     * @param divisionCode 划分编码
     */
    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode == null ? null : divisionCode.trim();
    }

    /**
     * 获取划分类型
     *
     * @return division_type - 划分类型
     */
    public String getDivisionType() {
        return divisionType;
    }

    /**
     * 设置划分类型
     *
     * @param divisionType 划分类型
     */
    public void setDivisionType(String divisionType) {
        this.divisionType = divisionType == null ? null : divisionType.trim();
    }

    /**
     * 获取划分名称
     *
     * @return division_name - 划分名称
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * 设置划分名称
     *
     * @param divisionName 划分名称
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName == null ? null : divisionName.trim();
    }

    /**
     * 获取父级ID
     *
     * @return parent_id - 父级ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父级ID
     *
     * @param parentId 父级ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 获取父级名称
     *
     * @return parent_name - 父级名称
     */
    public String getParentName() {
        return parentName;
    }

    /**
     * 设置父级名称
     *
     * @param parentName 父级名称
     */
    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    /**
     * 获取ID全路径，冒号分隔
     *
     * @return id_path - ID全路径，冒号分隔
     */
    public String getIdPath() {
        return idPath;
    }

    /**
     * 设置ID全路径，冒号分隔
     *
     * @param idPath ID全路径，冒号分隔
     */
    public void setIdPath(String idPath) {
        this.idPath = idPath == null ? null : idPath.trim();
    }

    /**
     * 获取名称全路径，冒号分隔
     *
     * @return name_path - 名称全路径，冒号分隔
     */
    public String getNamePath() {
        return namePath;
    }

    /**
     * 设置名称全路径，冒号分隔
     *
     * @param namePath 名称全路径，冒号分隔
     */
    public void setNamePath(String namePath) {
        this.namePath = namePath == null ? null : namePath.trim();
    }

    /**
     * 获取是否有子划分
     *
     * @return has_child - 是否有子划分
     */
    public Byte getHasChild() {
        return hasChild;
    }

    /**
     * 设置是否有子划分
     *
     * @param hasChild 是否有子划分
     */
    public void setHasChild(Byte hasChild) {
        this.hasChild = hasChild;
    }

    /**
     * 获取国家编码(两位)
     *
     * @return country_code - 国家编码(两位)
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 设置国家编码(两位)
     *
     * @param countryCode 国家编码(两位)
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
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
     * 获取显示顺序，越小排越前
     *
     * @return sequence - 显示顺序，越小排越前
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置显示顺序，越小排越前
     *
     * @param sequence 显示顺序，越小排越前
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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
     * @return created_by
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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

    /**
     * @return updated_by
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
}