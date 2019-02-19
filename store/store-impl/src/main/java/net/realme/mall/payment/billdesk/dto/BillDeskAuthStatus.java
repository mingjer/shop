package net.realme.mall.payment.billdesk.dto;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.impl.payment.dto
 *
 * @author 91000044
 * @date 2018/9/3 19:21
 */
public class BillDeskAuthStatus {

    public static final String SUCCESS = "0300";
    public static final String FAILURE = "0399";
    /**
     * Error Condition: Txn not found/ Invalid checksum/ Invalid Request IP etc
     */
    public static final String BAD_REQUEST = "NA";
    /**
     * Pending/Abandoned
     */
    public static final String PENDING_ABANDONED = "0002";
    public static final String BILLDESK_ERR = "0001";
}
