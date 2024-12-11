package com.api.ticketsplaza.utility;

public class StringUtility {

	public static boolean isStringEmpty(String value) {
		if (value == null || value.trim().isEmpty()) {
			return true;
		} else
			return false;
	}
	
	public static boolean isStringNotEmpty(String value) {
		return !isStringEmpty(value);
	}

}
