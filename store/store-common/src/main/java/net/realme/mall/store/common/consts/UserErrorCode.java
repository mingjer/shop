package net.realme.mall.store.common.consts;

public enum UserErrorCode {

	PINCODE_CHECK_FAIL(30001, "address.pincode.check.fail"), 
	POSTCODE_NOT_FOUND(30002, "address.postcode.not.found"), 
	ADDRESS_ADD_FAIL(30003, "address.add.fail"), 
	ADDRESS_UPDATE_FAIL(30004, "address.update.fail"), 
	ADDRESS_DELETE_FAIL(30005, "address.delete.fail"),
	ADDRESS_DEFAULT_FAIL(30006, "address.default.set.fail");

	private int code;
	
	private String msg;

	private UserErrorCode(int code, String msg) {
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
