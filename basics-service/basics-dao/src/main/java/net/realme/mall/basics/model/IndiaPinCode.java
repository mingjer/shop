package net.realme.mall.basics.model;

import javax.persistence.*;

@Table(name = "india_pincode")
public class IndiaPinCode {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * PIN code
     */
    @Column(name = "pin_code")
    private String pinCode;

    /**
     * 国家代码
     */
    @Column(name = "country_id")
    private String countryId;

    /**
     * 国家名称
     */
    @Column(name = "country_name")
    private String countryName;

    /**
     * 省/邦/区域代码
     */
    @Column(name = "province_id")
    private String provinceId;

    /**
     * 省/邦/区域名称
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 城市代码
     */
    @Column(name = "city_id")
    private String cityId;

    /**
     * 城市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 在线支付，1:支持  0:不支持
     */
    private Byte prepaid;

    /**
     * 货到付款，1:支持  0:不支持
     */
    private Byte cod;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Long createdAt;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * 更新人
     */
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
     * 获取PIN code
     *
     * @return pin_code - PIN code
     */
    public String getPinCode() {
        return pinCode;
    }

    /**
     * 设置PIN code
     *
     * @param pinCode PIN code
     */
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode == null ? null : pinCode.trim();
    }

    /**
     * 获取国家代码
     *
     * @return country_id - 国家代码
     */
    public String getCountryId() {
        return countryId;
    }

    /**
     * 设置国家代码
     *
     * @param countryId 国家代码
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId == null ? null : countryId.trim();
    }

    /**
     * 获取国家名称
     *
     * @return country_name - 国家名称
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 设置国家名称
     *
     * @param countryName 国家名称
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    /**
     * 获取省/邦/区域代码
     *
     * @return province_id - 省/邦/区域代码
     */
    public String getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省/邦/区域代码
     *
     * @param provinceId 省/邦/区域代码
     */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    /**
     * 获取省/邦/区域名称
     *
     * @return province_name - 省/邦/区域名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置省/邦/区域名称
     *
     * @param provinceName 省/邦/区域名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * 获取城市代码
     *
     * @return city_id - 城市代码
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 设置城市代码
     *
     * @param cityId 城市代码
     */
    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    /**
     * 获取城市名称
     *
     * @return city_name - 城市名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置城市名称
     *
     * @param cityName 城市名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * 获取在线支付，1:支持  0:不支持
     *
     * @return prepaid - 在线支付，1:支持  0:不支持
     */
    public Byte getPrepaid() {
        return prepaid;
    }

    /**
     * 设置在线支付，1:支持  0:不支持
     *
     * @param prepaid 在线支付，1:支持  0:不支持
     */
    public void setPrepaid(Byte prepaid) {
        this.prepaid = prepaid;
    }

    /**
     * 获取货到付款，1:支持  0:不支持
     *
     * @return cod - 货到付款，1:支持  0:不支持
     */
    public Byte getCod() {
        return cod;
    }

    /**
     * 设置货到付款，1:支持  0:不支持
     *
     * @param cod 货到付款，1:支持  0:不支持
     */
    public void setCod(Byte cod) {
        this.cod = cod;
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取更新时间
     *
     * @return updated_at - 更新时间
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置更新时间
     *
     * @param updatedAt 更新时间
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 获取更新人
     *
     * @return updated_by - 更新人
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新人
     *
     * @param updatedBy 更新人
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
}