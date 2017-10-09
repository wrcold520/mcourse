package com.mcourse.frame.utils;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

	public static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * 判断文件是否存在
	 * 
	 * @param filepath
	 * @return
	 */
	public static boolean isExist(String filepath) {
		Assert.hasText(filepath);
		return isExist(new File(filepath));
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isExist(File file) {
		return file.exists();
	}

	/**
	 * 是否是文件，文件存在且是文件返回true，其他返回false
	 * 
	 * @param filepath
	 * @return
	 */
	public static boolean isFile(String filepath) {
		boolean isFile = false;
		if (!isExist(filepath)) {
			logger.warn("File: " + filepath + " is not exist!");
		} else {
			isFile = isFile(new File(filepath));
		}
		return isFile;
	}

	/**
	 * 是否是文件，文件存在且是文件返回true，其他返回false
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isFile(File file) {
		boolean isFile = false;
		if (!isExist(file)) {
			logger.warn("File: " + file + " is not exist!");
		} else {
			if (file.isFile()) {
				isFile = true;
			}
		}
		return isFile;
	}

	/**
	 * 是否是文件目录，文件存在且是文件目录返回true，其他返回false
	 * 
	 * @param filepath
	 * @return
	 */
	public static boolean isDirectory(String filepath) {
		boolean isFile = false;
		if (!isExist(filepath)) {
			logger.warn("File: " + filepath + " is not exist!");
		} else {
			if (new File(filepath).isDirectory()) {
				isFile = true;
			}
		}
		return isFile;
	}

	/**
	 * 是否是文件目录，文件存在且是文件目录返回true，其他返回false
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isDirectory(File file) {
		boolean isFile = false;
		if (!isExist(file)) {
			logger.warn("File: " + file + " is not exist!");
		} else {
			if (file.isDirectory()) {
				isFile = true;
			}
		}
		return isFile;
	}

	/**
	 * 根据文件路径，如果文件不存在，创建一个新的文件
	 * 
	 * @param filepath
	 * @throws IOException
	 */
	public static void createNewFile(String filepath) throws IOException {
		File file = new File(filepath);
		createNewFile(file);
	}

	/**
	 * 根据文件路径，如果文件不存在，创建一个新的文件
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void createNewFile(File file) throws IOException {
		if (!file.exists() || !file.isFile()) {
			mkdirs(file.getParentFile());
			logger.info("准备创建文件: " + file.getAbsolutePath());
			file.createNewFile();
		}
	}

	/**
	 * 如果不存在对应的目录，创建目录
	 * 
	 * @param dirpath
	 */
	public static void mkdirs(String dirpath) {
		Assert.hasText(dirpath);
		mkdirs(new File(dirpath));
	}

	/**
	 * 如果不存在对应的目录，创建目录
	 * 
	 * @param file
	 */
	private static void mkdirs(File file) {
		if (!file.exists() || !file.isDirectory()) {
			logger.info("准备创建目录: " + file.getAbsolutePath());
			file.mkdirs();
		}
	}
}
