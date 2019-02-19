package net.realme.mall.oms.consts;

public enum ServiceSiteErrorCode {

	/**服务网点错误码*/
	SERVICE_SITE_NOT_FOUND(60001, "service.site.not.found"), 
	SERVICE_SITE_SAVE_FAIL(60002, "service.site.save.fail"), 
	SERVICE_SITE_UPLOAD_FAIL(60003, "service.site.upload.fail"),
	SERVICE_SITE_EXIST_APPLIY(60004, "service.site.has.apply.record"), 
	SERVICE_SITE_ADD_APPLY_EXIST(60005, "service.site.add.apply.has.found"),
	SERVICE_SITE_APPLY_FAIL(60006, "service.site.apply.fail"), 
	SERVICE_SITE_NOT_APPLY(60007, "service.site.not.apply"),
	SERVICE_SITE_REVIEW_FAIL(60008, "service.site.review.fail"),
	SERVICE_SITE_HAS_CANCEL(60009,"service.site.has.cancel");

	private int code;
	
	private String msg;

	private ServiceSiteErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
