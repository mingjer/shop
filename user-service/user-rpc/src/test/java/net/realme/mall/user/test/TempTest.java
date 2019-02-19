package net.realme.mall.user.test;

import java.text.DecimalFormat;

import org.junit.Test;

public class TempTest {

	@Test
	public void testParse() {
		String phoneNumberStr = "+916742598395";
		String[] mobile = phoneNumberStr.split(" ");
		if (mobile.length > 1) {
			System.out.println("sho");
		}
		System.out.println(mobile[0]);
		System.out.println(mobile[1]);
	}

	@Test
	public void testParseDecimalFormat() {
		double data = 77.5839;
		DecimalFormat df = new DecimalFormat("#.00000");
		String dataStr = df.format(data);
		System.out.println(dataStr);
	}

	public static int getDigestIndex(String str) {
		int digestIndex = 0;
		for (int i = 0, len = str.length(); i < len; i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				digestIndex = i;
				break;
			}
		}
		return digestIndex;
	}
}
