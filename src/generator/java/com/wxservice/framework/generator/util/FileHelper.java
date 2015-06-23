package com.wxservice.framework.generator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileHelper {
	public static String readFile(String filepath) {
		File file = new File(filepath);
		if (!file.exists()) {
			return null;
		}

		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			throw new RuntimeException("没找到文件"+ filepath);
		}

		StringBuffer sb = new StringBuffer(1000);
		try {
			char[] buf;
			int count;

			buf = new char[2048];

			while (true) {
				count = fr.read(buf);
				if (count < 0)
					break;
				for (int i = 0; i < count; i++) {
					sb.append(buf[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读文件错误：" + filepath);
		}
		try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
			// quiet
		}
		return sb.toString();
	}

	public static void saveToFile(String filepath, String content,
			boolean overWrite) throws Exception {
		File dir = new File(getFileDir(filepath));
		if (dir.exists()) {
			if (!overWrite) {
				throw new Exception("文件已经存在,不能够重新产生代码,如果必须,请修改config属性!");
			}
		}
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}

		}

		FileOutputStream out = null;

		out = new FileOutputStream(filepath);

		String encoding = "utf-8";
		Writer fh = new OutputStreamWriter(out, encoding);
		fh.append(content);
		fh.close();

		//System.out.println("save to " + filepath + "!");
	}

	public static String getFileDir(String filepath) {
		int end = filepath.lastIndexOf('/');

		String dirpath = filepath.substring(0, end);
		return dirpath;
	}

}
