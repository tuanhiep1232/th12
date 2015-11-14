package net.th1232.utils;

public class StringUtil {
	public static boolean isEmpty(String s, boolean isTrim) {
		if (isTrim) return s == null || s.trim().isEmpty();
		return s == null || s.isEmpty();
	}
}
