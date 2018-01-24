package com.abcft.common.util.gen;

import java.io.File;
import java.io.IOException;

/**
 * 
 * 文件工具类
 * 
 * @author mike <br>
 *         2015年12月31日
 * @version 1.0
 */
public final class FileUtil {

	static class Tester {

		public static void main(String[] args) {
			String directory = "d:\\test\\show";
			makeFolder(directory);
			makeFile(directory + "\\1.txt");
		}

	}

	private FileUtil() {
	}

	/**
	 * 创建文件夹
	 * 
	 * @param directory
	 * @return File
	 */
	public static File makeFolder(String directory) {
		try {
			File folder = new File(directory);
			if (!folder.exists()) {
				makeDirectory(folder);
			}
			return folder;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 创建文件夹
	 * 
	 * @param directory
	 * @return File
	 * @throws IOException
	 */
	private static void makeDirectory(File directory) throws IOException {
		if (!directory.exists()) {
			if (!directory.isDirectory()) {
				throw new IOException("file exists and is not a directory.");
			}

		} else {
			if (directory.mkdirs()) {
				if (!directory.isDirectory()) {
					throw new IOException("unable to create directory" + directory);
				}
			}
		}
	}

	/**
	 * 创建文件
	 * 
	 * @param directory
	 * @return File
	 */
	public static File makeFile(String directory) {
		try {
			File file = new File(directory);
			File parentFolder = file.getParentFile();
			if (!parentFolder.exists()) {
				makeDirectory(parentFolder);
			}
			return file;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除目录
	 * 
	 * @param directory
	 * @throws IOException
	 */
	public static void delFolder(String directory) throws IOException {
		File folder = new File(directory);
		if (!folder.exists()) {
			return;
		}

		if (!isSymlink(folder)) {

			File[] files = folder.listFiles();
			if (files == null) {
				throw new IOException("Failed to list contents of " + directory);
			}

			IOException exception = null;
			for (File file : files) {
				try {
					delFile(file);
				} catch (IOException ioe) {
					exception = ioe;
				}
			}

			if (null != exception) {
				throw exception;
			}
			delFolder(folder);
		}
		if (!folder.delete()) {
			throw new RuntimeException("don't delete the directory:" + directory);
		}
	}

	public static void delFolder(File directory) throws IOException {
		if (!directory.exists()) {
			return;
		}

		if (!isSymlink(directory)) {

			File[] files = directory.listFiles();
			if (files == null) {
				throw new IOException("Failed to list contents of " + directory);
			}

			IOException exception = null;
			for (File file : files) {
				try {
					delFile(file);
				} catch (IOException ioe) {
					exception = ioe;
				}
			}

			if (null != exception) {
				throw exception;
			}
			delFolder(directory);
		}
		if (!directory.delete()) {
			throw new RuntimeException("don't delete the directory:" + directory);
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 */
	public static void delFile(String directory) {
		try {
			File file = new File(directory);
			if (file.exists() && file.isFile()) {
				delFolder(directory);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void delFile(File directory) throws IOException {
		if (directory.exists() && directory.isFile()) {
			delFolder(directory);
		}
	}

	public static boolean isSymlink(File file) throws IOException {
		if (file == null) {
			throw new NullPointerException("File must not be null");
		}
		if (File.separatorChar == '\\') {
			return false;
		}
		File fileInCanonicalDir = null;
		if (file.getParent() == null) {
			fileInCanonicalDir = file;
		} else {
			File canonicalDir = file.getParentFile().getCanonicalFile();
			fileInCanonicalDir = new File(canonicalDir, file.getName());
		}

		if (fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile())) {
			return false;
		} else {
			return true;
		}
	}

}
