package net.realme.mall.oms.core.model;

import javax.persistence.*;

@Table(name = "file_upload")
public class FileUpload {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 保存路径
     */
    private String path;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 格式
     */
    private String format;

    /**
     * 原始文件名
     */
    @Column(name = "original_name")
    private String originalName;

    /**
     * 文件描述
     */
    private String description;

    /**
     * 上传时间
     */
    @Column(name = "uploaded_at")
    private Long uploadedAt;

    /**
     * 操作人
     */
    @Column(name = "uploaded_by")
    private String uploadedBy;

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
     * 获取保存路径
     *
     * @return path - 保存路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置保存路径
     *
     * @param path 保存路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取文件大小
     *
     * @return size - 文件大小
     */
    public Long getSize() {
        return size;
    }

    /**
     * 设置文件大小
     *
     * @param size 文件大小
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * 获取格式
     *
     * @return format - 格式
     */
    public String getFormat() {
        return format;
    }

    /**
     * 设置格式
     *
     * @param format 格式
     */
    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    /**
     * 获取原始文件名
     *
     * @return original_name - 原始文件名
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * 设置原始文件名
     *
     * @param originalName 原始文件名
     */
    public void setOriginalName(String originalName) {
        this.originalName = originalName == null ? null : originalName.trim();
    }

    /**
     * 获取文件描述
     *
     * @return description - 文件描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置文件描述
     *
     * @param description 文件描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取上传时间
     *
     * @return uploaded_at - 上传时间
     */
    public Long getUploadedAt() {
        return uploadedAt;
    }

    /**
     * 设置上传时间
     *
     * @param uploadedAt 上传时间
     */
    public void setUploadedAt(Long uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    /**
     * 获取操作人
     *
     * @return uploaded_by - 操作人
     */
    public String getUploadedBy() {
        return uploadedBy;
    }

    /**
     * 设置操作人
     *
     * @param uploadedBy 操作人
     */
    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy == null ? null : uploadedBy.trim();
    }
}