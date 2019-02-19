package net.realme.mall.basics.common.consts;

public final class ServiceSiteConst {
	
	public enum SiteType {

		EXCLUSIVE("1"), AUTHORIZED("2"), RECEIVE("3");

		private String value;

		private SiteType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public enum SiteStatus {

		DISABLE("0"), NORMAL("1");

		private String value;

		private SiteStatus(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public enum AssessType {

		NEW("1"), DELETE("2"), UPDATE("3");

		private String value;

		private AssessType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public enum AssessStatus {

		SAVE("0"), REVIEW("1"), PASS("2"), REJECT("3");

		private String value;

		private AssessStatus(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public enum OwnerType {

		REALME("1"), OPPO("2");

		private String value;

		private OwnerType(String value) {
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
