package net.realme.framework.util.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


public class ResultList<T> implements Serializable {

    /**
     * 要返回的某一页的记录列表
     */
    private List<T> records;
    /**
     * 总记录数
     */
    private long recordTotal;
    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 每页的记录数
     */
    private int pageSize;

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(long recordTotal) {
        this.recordTotal = recordTotal;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}