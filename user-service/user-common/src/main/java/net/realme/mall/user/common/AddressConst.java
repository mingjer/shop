package net.realme.mall.user.common;

public final class AddressConst {

	public enum Status {

		EFFECTIVE("1"), INVALID("0");

		private String value;

		private Status(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public enum DefaultStatus {

		DEFAULT("1"), NORMAL("0");

		private String value;

		private DefaultStatus(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
