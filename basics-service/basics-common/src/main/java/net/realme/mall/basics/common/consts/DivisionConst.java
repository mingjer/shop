package net.realme.mall.basics.common.consts;

public final class DivisionConst {

	public enum DivisionType {

		COUNTRY("country"), PROVINCE("province"), CITY("city");

		private String value;

		private DivisionType(String value) {
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
