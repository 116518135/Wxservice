package com.wxservice.service.system;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springside.core.exception.BusinessException;

import com.wxservice.framework.components.License.LicenseService;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.BaseEngine;
import com.wxservice.framework.engine.support.SysConfig;
import com.wxservice.framework.util.AntZip;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.web.form.system.AboutForm;

/**
 * 
 * 描述： Services 创建人：Gererator
 */
public class AboutManager extends BaseEngine {
	public final static int BUFFER_SIZE = 8096;

	private String formatDetailDate() {
		String myDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		try {
			myDate = sdf.format(new Date());
		} catch (Exception e) {
		}
		return myDate;
	}

	private String getBackuppath() {
		StringBuffer path = new StringBuffer();
		path.append(serviceConfig.getStringConfig("ucard.database.backuppath"));
		path.append(serviceConfig.getStringConfig("ucard.database.name"));
		path.append(formatDetailDate());
		path.append(".sql");
		return path.toString();
	}

	/*public void backupdatabase() {
		Operators os = new Operators();
		StringBuffer sql = new StringBuffer();
		sql.append("backup database ");
		sql.append(serviceConfig.getStringConfig("ucard.database.name"));
		sql.append(" to disk='");
		sql.append(this.getBackuppath());
		sql.append("'");
		os.addScriptObject(sql.toString());
		dao.execute(os);
	}*/

	public IStrutsForward list(ActionContext context) {
		int kb = 1024 * 1024;
		BusinessException be = null;
		AboutForm form = (AboutForm) context.getForm();
		form.setTotalMemory(Runtime.getRuntime().totalMemory() / kb);
		form.setFreeMemory(Runtime.getRuntime().freeMemory() / kb);
		form.setMaxMemory(Runtime.getRuntime().maxMemory() / kb);
		form.setProcesscount(Runtime.getRuntime().availableProcessors());
		form.setSoftware_version(SysConfig.getSoftware_version());
		return this.forwardJsp("list");
	}

	public IStrutsForward cleanMemory(ActionContext context) {
		try {
			Runtime.getRuntime().gc();
			// 保存动作
		} catch (Exception e) {

			SystemLogger.error("清除内存的时候发生错误", e);
			this.processFailure(context, e, true, "清除内存");
		}
		this.processSuccess(context, true, "清除内存");
		return this.forwardMethod("list");
	}

	public IStrutsForward test(ActionContext context) {

		return this.forwardJsp("select");
	}

	public IStrutsForward backupdatabase(ActionContext context) {
		BusinessException be = null;
		AboutForm form = (AboutForm) context.getForm();
		try {
			this.backupdatabase();
			// 保存动作
		} catch (Exception e) {

			SystemLogger.error("备份数据库发生错误", e);
			this.processFailure(context, e, true, "备份数据库");
			return this.forwardMethod("list");
		}
		this.processSuccess(context, true, "备份数据库");
		return this.forwardMethod("list");
	}

	public IStrutsForward cleanCache(ActionContext context) {
		BusinessException be = null;
		AboutForm form = (AboutForm) context.getForm();
		try {
			this.clear();
			// 保存动作
		} catch (Exception e) {
			SystemLogger.error("清除缓存发生错误", e);
			this.processFailure(context, e, true, "清除缓存");
			return this.forwardMethod("list");
		}
		this.processSuccess(context, true, "清除缓存");
		return this.forwardMethod("list");
	}

	public void clear() {
		Operators os = new Operators();
		for (Class cls : SysConfig.pojolist) {
			os.addEvictObject(cls);
		}

		dao.execute(os);
	}

	// 下面是升级
	private void unZip(ActionContext context, AboutForm form)
			throws FileNotFoundException, IOException {
		try {
			InputStream input = form.getFormFile().getInputStream();
			String filename = SysUtil.getSystemUpdateFile();
			File upfile = new File(filename);
			if (upfile.exists()) {
				upfile.delete();
			}
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					upfile));
			DataInputStream in = new DataInputStream(input);
			byte[] bb = new byte[BUFFER_SIZE];
			int len = in.read(bb, 0, BUFFER_SIZE);
			while (len > 0) {
				out.write(bb, 0, len);
				len = in.read(bb, 0, BUFFER_SIZE);
			}
			out.close();
			String unzipdir = context.getRequest().getRealPath("") + File.separator;//根目录(支持window,Linux)
			System.out.println("解压文件:" + filename);
			System.out.println("解压到路径:" + unzipdir);

			AntZip zip = new AntZip();
			// 解压到根目录下.
			zip.unZip(upfile, unzipdir);

			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public IStrutsForward grade(ActionContext context) {
		AboutForm form = (AboutForm) context.getForm();
		try {
			// 保存上传文件
			if (form.getFormFile() != null) {
				// 上传解压
				unZip(context, form);
			}
			// 重启服务
			String dir = context.getRequest().getRealPath("");// 根目录
			//restartServer(dir);
		} catch (Exception e) {
			SystemLogger.error("升级发生错误", e);
			e.printStackTrace(System.out);
			this.processFailure(context, e, true, "升级发生错误");
			return this.forwardMethod("list");
		}

		return this.forwardMethod("list");
	}

	public void restartServer(String dir) throws Exception {
		String command = dir + "\\bin\\ucard.bat";
		String newcommand = "cmd /c start " + command;
		Runtime.getRuntime().exec(newcommand);

	}

	/**
	 * 备份数据库，如果指定路径的文件不存在会自动生成
	 * 
	 * @param dest
	 *            备份文件的路径
	 * @param dbname
	 *            要备份的数据库
	 * @throws IOException
	 */
	public void backupdatabase() {
		String dest = getBackuppath();
		System.out.println(serviceConfig.getStringConfig("ucard.database.backuppath"));
		File file = new File(
				serviceConfig.getStringConfig("ucard.database.backuppath"));
		if (!file.exists()) {
			file.mkdirs();
		}
		String dbname = serviceConfig.getStringConfig("ucard.database.name");
		backup(dest, dbname);
	}

	/**
	 * 备份数据库
	 * 
	 * @param output
	 *            输出流
	 * @param dbname
	 *            要备份的数据库名
	 */
	public void backup(String dest, String dbname) {
		String username = serviceConfig.getStringConfig("ucard.database.user");
		String password = serviceConfig
				.getStringConfig("ucard.database.password");
	    String binpath =LicenseService.systemPath+"/";
		String[] url =serviceConfig.getStringConfig("ucard.database.url").split(":");
		String command = "cmd /c "+binpath+"mysqldump  -h"+url[0]+" -u" + username
				+ " -p" + password + " --set-charset=utf-8 " + dbname + " > "
				+ dest;
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(command);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
}
