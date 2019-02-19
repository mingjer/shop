package net.realme.mall.store.common.consts;

public final class AddressConst {

	public enum Status {

		DISABLE("0"), NORMAL("1");

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

	public enum Prepaid {

		SUPPORT("1"), NOT_SUPPORT("0");

		private String value;

		private Prepaid(String value) {
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
