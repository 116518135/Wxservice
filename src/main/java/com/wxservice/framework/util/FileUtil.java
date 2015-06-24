package com.wxservice.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ReflectionUtils;

import com.wxservice.framework.web.form.BaseForm;

public class FileUtil {
	public static final String _tempPath = "/WEB-INF/tmp";
	public static final String _download = "/downloads";
	public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator"); // 取换行符

	/**
	 * 将文件的内容转换成String
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static String file2String(InputStream input) throws IOException {
		StringWriter sw = new StringWriter();
		copyFile(input, sw);
		return sw.toString();
	}

	
	/**
	 * 文件copy
	 * 
	 * @param input
	 * @param output
	 * @throws IOException
	 */
	public static void copyFile(InputStream input, Writer output)
			throws IOException {
		InputStreamReader in = new InputStreamReader(input);
		copyFile(in, output);
	}

	/**
	 * 文件copy
	 * 
	 * @param input
	 * @param output
	 * @return
	 * @throws IOException
	 */
	public static int copyFile(Reader input, Writer output) throws IOException {
		char[] buffer = new char[DEFAULT_BUFFER_SIZE];
		int count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	/**
	 * 得到一个文件folder的路径
	 * 
	 * @param form
	 * @param folder
	 * @return
	 */
	public static String getPath(BaseForm form, String folder) {
		StringBuffer path = new StringBuffer("");
		path.append(form.getServlet().getServletContext().getRealPath(""))
				.append("/").append(folder).append("/");
		File directory = new File(path.toString());
		if (!directory.exists()) {// 目录不存在,必须强制建立
			try {
				FileUtils.forceMkdir(directory);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
		return path.toString();
	}

	/**
	 * 得到系统临时文件夹的路径
	 * 
	 * @param form
	 * @return
	 */
	public static String getTmpPath(BaseForm form) {
		return getPath(form, _tempPath);
	}

	/**
	 * 得到下载文件夹的路径
	 * 
	 * @param form
	 * @return
	 */
	public static String getDownloadPath(BaseForm form) {
		return getPath(form, _download);
	}

	/**
	 * 得到一个临时文件名
	 * 
	 * @param request
	 * @param form
	 * @param cate
	 * @return
	 */
	public static String getTmpPath(HttpServletRequest request, BaseForm form,
			String extendName) {
		StringBuffer file = new StringBuffer();
		file.append(getTmpPath(form));
		file.append(extendName);
		file.append("_");
		file.append(request.getSession().getId());
		file.append(".tmp");
		return file.toString();
	}

	/**
	 * 从文件当中加载一个对象
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static Object loadObject(String fileName) throws Exception {
		return loadObject(new File(fileName));
	}

	/**
	 * 当文件当中加载一个对象
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static Object loadObject(File file) throws Exception {
		if (!file.exists() || !file.isFile()) {
			throw new Exception(new FileNotFoundException(String.valueOf(file)));
		}

		Object obj = null;

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			obj = ois.readObject();
		} catch (IOException e) {
			throw new Exception("Error loading object from file : " + file, e);
		} catch (ClassNotFoundException e) {
			throw new Exception(
					"Class not found when loading object from file : " + file,
					e);
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
				}
			}

			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
		}
		return obj;
	}

	/**
	 * 加载一个对象
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static Object loadObject(InputStream is) throws Exception {
		Object obj = null;

		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(is);
			obj = ois.readObject();
		} catch (IOException e) {
			throw new Exception("Error loading object from InputStream", e);
		} catch (ClassNotFoundException e) {
			throw new Exception(
					"Class not found when loading object from InputStream", e);
		} finally {

			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
				}
			}
		}

		return obj;
	}

	/**
	 * 保存一个对象到文件当中
	 * 
	 * @param obj
	 * @param fileName
	 * @throws Exception
	 */
	public static void saveObject(Object obj, String fileName) throws Exception {
		saveObject(obj, new File(fileName));
	}
	public static void saveObject1(Object obj, File file) throws Exception {
	   	
	}	
	/**
	 * 保存一个对象到文件当中
	 */
	public static void saveObject(Object obj, File file) throws Exception {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.flush();
			fos.flush();
		} catch (IOException e) {
			throw new Exception("Error saving file : " + file, e);
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
				}
			}

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 保存一个Ojbect对象
	 */
	public static void saveObject(Object obj, OutputStream os) throws Exception {
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(os);
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException e) {
			throw new Exception("Error saving object to OutputStream", e);
		} finally {

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
