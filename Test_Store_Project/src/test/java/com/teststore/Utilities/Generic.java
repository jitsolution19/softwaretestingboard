package com.teststore.Utilities;

public class Generic {
	public boolean CompareText(String expectedText, String textfromUi) {
		boolean flag = false;
		if (expectedText.matches(textfromUi)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;

	}
}
