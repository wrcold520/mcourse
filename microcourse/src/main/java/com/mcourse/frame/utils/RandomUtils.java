package com.mcourse.frame.utils;

import java.util.UUID;

public class RandomUtils {
	/**
	 * 获取32位随机字符串
	 * 
	 * @return
	 */
	public static String generateString() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	/**
	 * 获取固定位数的随机字符
	 * 
	 * @param length
	 * @return
	 */
	public static String generateString(int length) {
		Assert.greatThan(length, 0);
		return generateString().substring(0, length);
	}

	public static void main(String[] args) {
		System.out.println(generateString(16));
		System.out.println(generateString(16));
	}
}
