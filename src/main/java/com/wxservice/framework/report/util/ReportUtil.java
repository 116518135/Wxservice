package com.wxservice.framework.report.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.jxpath.CompiledExpression;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.struts.util.RequestUtils;
import org.springframework.util.ReflectionUtils;

import com.wxservice.database.po.report.Trpreport;
import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.database.po.report.Trpreportplugin;
import com.wxservice.database.po.system.Tsysmenu;
import com.wxservice.framework.components.License.LicenseService;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.report.base.ReportConfig;
import com.wxservice.framework.report.base.ReportConfigImpl;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.html.HtmlProcess;
import com.wxservice.framework.util.FileUtil;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.service.system.TsysmenuManager;

public class ReportUtil {
	public static String VALUE_PREFX = "$v_";
	public static String SQL_PREFX = "$s_";
	public static String PLUGIN_PREFX = "$p_";
	public static final String DATASOURCE_DEFAULT = "main";
	public static final String DATASOURCE_wheres = "wheres";
	public static final String DATASOURCE_data = "DATASOURCE_data";
	public static int left = 1;
	public static int center = 2;
	public static int right = 3;

	public static void setWheres(ReportRequest rr, Map wheres) {
		rr.getContext().getRequest().setAttribute(DATASOURCE_wheres, wheres);
	}

	public static Map getWheres(ReportRequest rr) {
		return (Map) rr.getContext().getRequest().getAttribute(
				DATASOURCE_wheres);
	}

	public static void setDatasource(ReportRequest rr, Map ds) {
		rr.getContext().getRequest().setAttribute(DATASOURCE_data, ds);
	}

	public static Map getDatasource(HttpServletRequest request) {
		return (Map) request.getAttribute(DATASOURCE_data);
	}

	public static Trpreport getReport(IDao dao, String code) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trpreport as a where a.code='");
		hql.append(code);
		hql.append("'");
		Trpreport o = (Trpreport) dao.get(hql.toString());
		return o;
	}

	public static Trpreport getReport(IDao dao, Integer reportid) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Trpreport as a where a.trpreportid=");
		hql.append(reportid);
		Trpreport o = (Trpreport) dao.get(hql.toString());
		return o;
	}

	public static List<LabelValue> getReportAlignOption() {
		List<LabelValue> options = new ArrayList<LabelValue>();
		LabelValue t = new LabelValue("左对齐", left);
		options.add(t);
		t = new LabelValue("中间对齐", center);
		options.add(t);
		t = new LabelValue("右对齐", right);
		options.add(t);
		return options;
	}

	public static String getReportAlignName(int prop) {
		List<LabelValue> options = getReportAlignOption();
		for (LabelValue o : options) {
			if (o.getIvalue() == prop) {
				return o.getLabel();
			}
		}
		return "";
	}

	// 字段类型
	public static int constant = 0;
	public static int field = 1;
	public static int variable = 2;
	public static int plugin = 3;
	public static int javascript = 4;
	public static int expression = 5;
	public static int checkbox = 6;
	public static int htmlLink = 7;

	public static List<LabelValue> getReportFieldTypeOption() {

		List<LabelValue> options = new ArrayList<LabelValue>();
		LabelValue t = new LabelValue("常量", constant);
		options.add(t);
		t = new LabelValue("字段", field);
		options.add(t);
		t = new LabelValue("变量", variable);
		options.add(t);
		t = new LabelValue("插件", plugin);
		options.add(t);
		t = new LabelValue("Java脚本", javascript);
		options.add(t);
		t = new LabelValue("表达式", expression);
		options.add(t);
		t = new LabelValue("Html处理器", checkbox);
		options.add(t);
		t = new LabelValue("Html Link处理器", htmlLink);
		options.add(t);
		return options;
	}

	public static String getReportFieldTypeName(int prop) {
		List<LabelValue> options = getReportFieldTypeOption();
		for (LabelValue o : options) {
			if (o.getIvalue() == prop) {
				return o.getLabel();
			}
		}
		return "";
	}

	//

	public static String display_table = "";
	public static String display_dcube = "dcube";
	public static String display_dhskudcube = "dhskudcube";

	public static List<LabelValue> getReportDispProcessOptions() {
		List<LabelValue> options = new ArrayList<LabelValue>();
		LabelValue t = new LabelValue("表格显示处理类", display_table);
		options.add(t);
		return options;
	}

	// 数据源类型
	public static int datasoucetype_sql = 0;
	public static int datasoucetype_xml = 1;
	public static int datasoucetype_txt = 2;
	public static int datasoucetype_java = 3;
	public static int datasoucetype_proc = 4;
	public static int datasoucetype_olap = 5;

	public static List<LabelValue> getDatasourcetypeOption() {
		List<LabelValue> options = new ArrayList<LabelValue>();
		LabelValue t = new LabelValue("SQL", datasoucetype_sql);
		options.add(t);
		t = new LabelValue("XML", datasoucetype_xml);
		options.add(t);
		t = new LabelValue("文本", datasoucetype_txt);
		options.add(t);
		t = new LabelValue("JAVA", datasoucetype_java);
		options.add(t);
		t = new LabelValue("存储过程", datasoucetype_proc);
		options.add(t);
		t = new LabelValue("数据仓库", datasoucetype_olap);
		options.add(t);
		return options;
	}

	public static String getReportDatasourcetypeName(int prop) {
		List<LabelValue> options = getDatasourcetypeOption();
		for (LabelValue o : options) {
			if (o.getIvalue() == prop) {
				return o.getLabel();
			}
		}
		return "";
	}

	public static List<LabelValue> getReportProcessclassOptions() {
		List<LabelValue> options = new ArrayList<LabelValue>();
		LabelValue t = new LabelValue("常用报表处理类", "reportManager");
		options.add(t);
		return options;
	}

	public static String getReportProcessclassName(String v) {
		List<LabelValue> options = getReportProcessclassOptions();
		for (LabelValue o : options) {
			if (o.getValue().equals(v)) {
				return o.getLabel();
			}
		}
		return "";
	}

	public static List<LabelValue> getProcessclassOptions() {
		List<LabelValue> options = new ArrayList<LabelValue>();
		LabelValue t = new LabelValue("普通处理类", "commonHtmlProcess");
		options.add(t);
		t = new LabelValue("普通多字段处理类", "commonManyHtmlProcess");
		options.add(t);			
		t = new LabelValue("汇总处理类", "collectHtmlProcess");
		options.add(t);
		t = new LabelValue("开始日期", "beginDateHtmlProcess");
		options.add(t);
		t = new LabelValue("结束日期", "enddateHtmlProcess");
		options.add(t);
		t = new LabelValue("赛场单选处理类", "competionHtmlProcess");
		options.add(t);

		return options;
	}

	// 得到插件类
	public static Trpreportplugin getReportPlugin(ReportRequest rr,
			Integer trpreportpluginid) {
		for (Object o : rr.getRc().getPlugin()) {
			Trpreportplugin plugin = (Trpreportplugin) o;
			if (MathUtil
					.equal(plugin.getTrpreportpluginid(), trpreportpluginid)) {
				return plugin;
			}
		}
		return null;
	}

	public static String getValueKey(String jsname) {
		StringBuffer value_var_key = new StringBuffer();
		value_var_key.append(VALUE_PREFX);
		value_var_key.append(jsname);
		return value_var_key.toString();
	}

	public static String getSqlKey(String jsname) {
		StringBuffer sql_var_key = new StringBuffer();
		sql_var_key.append(SQL_PREFX);
		sql_var_key.append(jsname);
		return sql_var_key.toString();
	}

	public static String getPluginSqlKey(String jsname) {
		StringBuffer sql_var_key = new StringBuffer();
		sql_var_key.append(PLUGIN_PREFX);
		sql_var_key.append(jsname);
		return sql_var_key.toString();
	}

	public static final String _reportPath = "/WEB-INF/report/config";
	public static final String _tempPath = "/WEB-INF/tmp";
	public static final String _printExtName = ".print";
	public static final String _reportExtName = ".rpt";
	public static final String _reportExtName1 = "rpt";
	protected static final HashMap converts = new HashMap();

	public static Number proccessExpr(String expr, Map ds) {
		try {
			String path = StringUtil.replace(expr.toLowerCase(), "/", " div ");
			JXPathContext context = JXPathContext.newContext(ds);
			CompiledExpression expression = JXPathContext.compile(path);
			Number number = (Number) expression.getValue(context);
			if (number.equals(Double.NaN)) {
				number = new Double(0);
			}
			return number;
		} catch (Throwable e) {
			SystemLogger.error(expr + "表达式错误或者计算错误");
		}
		return new Double(0);
	}

	public static boolean proccessExprBoolean(String expr, Map ds) {
		try {
			String path = StringUtil.replace(expr.toLowerCase(), "/", " div ");
			JXPathContext context = JXPathContext.newContext(ds);
			CompiledExpression expression = JXPathContext.compile(path);
			Boolean result = (Boolean) expression.getValue(context);
			return result;
		} catch (Throwable e) {
			SystemLogger.error(expr + "表达式错误或者计算错误");
		}
		return false;
	}

	/**
	 * 得到报表存放的路径
	 * 
	 * @param form
	 * @return
	 */
	public static String getConfigPath(BaseForm form) {
		StringBuffer path = new StringBuffer("");
		path.append(form.getServlet().getServletContext().getRealPath(""))
				.append("/").append(_reportPath).append("/");
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

	public static boolean existsReportConfig(String reportcode, BaseForm form) {
		StringBuffer filepath = new StringBuffer("");
		filepath.append(getConfigPath(form));
		filepath.append(reportcode);
		filepath.append(_reportExtName);
		return existsFile(filepath.toString());
	}

	public static boolean existsFile(String filepath) {
		boolean result = false;

		File file = new File(filepath);
		if (file.exists()) {
			result = true;
		}
		return result;
	}

	/**
	 * 保存报表
	 * 
	 * @param config
	 * @param form
	 * @return
	 */
	public static boolean saveReportConfig(ReportConfig config, BaseForm form) {
		boolean result = false;

		try {
			String file = getReportFilePath(config, form);
			FileUtil.saveObject(config, file.toString());
			// 写菜单

			result = true;
		} catch (Exception e) {
			SystemLogger.error("保存报计设计到文件发生错误", e);
		}
		return result;
	}

	public static String getReportFilePath(ReportConfig config, BaseForm form) {
		StringBuffer file = new StringBuffer();
		file.append(getConfigPath(form));
		file.append(config.getReport().getCode());
		file.append(_reportExtName);
		return file.toString();
	}

	/**
	 * 重新载入报表
	 * 
	 * @param reportid
	 * @param form
	 * @return
	 */
	public static ReportConfig loader(String reportid, BaseForm form) {
		Object obj = null;
		StringBuffer file = new StringBuffer("");
		try {
			file.append(getConfigPath(form));
			file.append(reportid);
			file.append(_reportExtName);
			obj = FileUtil.loadObject(file.toString());
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return (ReportConfig) obj;
	}

	/**
	 * 重新载入报表
	 * 
	 * @param reportid
	 * @param form
	 * @return
	 */
	public static ReportConfig loader(InputStream is) {
		Object obj = null;

		try {
			obj = FileUtil.loadObject(is);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return (ReportConfig) obj;
	}

	public static Collection getFileCollection(BaseForm form) throws Exception {
		String path = getConfigPath(form);
		File directory = new File(path);
		String[] extensions = new String[1];
		extensions[0] = _reportExtName1;
		Collection col = FileUtils.listFiles(directory, extensions, true);
		return col;
	}

	public static String readFileToString(File file, String encoding)
			throws IOException {
		InputStream in = new java.io.FileInputStream(file);
		try {
			return IOUtils.toString(in, encoding);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static byte[] readFileToByte(String file) throws IOException {
		InputStream in = new java.io.FileInputStream(file);
		try {
			return IOUtils.toByteArray(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static byte[] readFileToByte(File file) throws IOException {
		InputStream in = new java.io.FileInputStream(file);
		try {
			return IOUtils.toByteArray(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	//
	public static String getSettingDir(BaseForm form) {
		StringBuffer path = new StringBuffer("");
		path.append(form.getServlet().getServletContext().getRealPath(""))
				.append("/").append(_tempPath).append("/");
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

	public static void deleteTmpDir() {
		try {
			StringBuffer path = new StringBuffer("");
			path.append(LicenseService.systemPath).append("/")
					.append(_tempPath).append("/");
			File directory = new File(path.toString());
			FileUtils.deleteDirectory(directory);

		} catch (Exception e) {
			SystemLogger.error("删除报表临时目录失败", e);
		}
	}

	public static String getSettingPath(String filename, BaseForm form) {
		StringBuffer file = new StringBuffer();
		file.append(getSettingDir(form));
		file.append(filename);
		return file.toString();
	}

	public static boolean saveObjectSetting(String filename, Object obj,
			BaseForm form) {
		boolean result = false;

		try {
			String file = getSettingPath(filename, form);
			FileUtil.saveObject(obj, file.toString());
			result = true;
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return result;
	}

	public static String getAlign(int align) {
		if (align == 1) {
			return "center";
		} else if (align == 2) {
			return "right";
		} else {
			return "left";
		}
	}

	public static Object getFieldClassname(Trpreportdtl el, ReportRequest rr)
			throws Exception {
		String classname = "";
		Object o = null;
		if (constant == el.getFieldtype()) {
			classname = "labelConvert";
		} else if (field == el.getFieldtype()) {
			classname = "fieldConvert";
		} else if (variable == el.getFieldtype()) {
			classname = "variableConvert";
		} else if (plugin == el.getFieldtype()) {
			Trpreportplugin plugin = getReportPlugin(rr, el
					.getTrpreportpluginid());
			classname = plugin.getSpringname();
		} else if (javascript == el.getFieldtype()) {
			classname = "scriptConvert";
		} else if (expression == el.getFieldtype()) {
			classname = "expressionConvert";
		} else if (checkbox == el.getFieldtype()) {
			classname = "checkboxConvert";
		} else if (htmlLink == el.getFieldtype()) {
			classname = "htmllinkConvert";
		} else {
			classname = "labelConvert";
		}
		o = WebUtil.getBean(classname, rr.getContext().getForm());
		return o;
	}

	public static HtmlProcess getHtmlProcess(BaseForm form, String classname)
			throws Exception {
		HtmlProcess o = (HtmlProcess) WebUtil.getBean(classname, form);
		return o;
	}

	public static Map static_class_cache = new HashMap();

	public static Class applicationClass(String className)
			throws ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader == null) {
			classLoader = RequestUtils.class.getClassLoader();
		}
		return (classLoader.loadClass(className));
	}

	public static Object createInstance(String className) throws Exception {
		Object obj = static_class_cache.get(className);
		if (obj == null) {
			Class c = applicationClass(className);
			obj = c.newInstance();
			// static_class_cache.put(className, obj);
		}
		return obj;
	}

	public static ReportConfig createReportConfig(IDao dao, Trpreport rp) {
		ReportConfigImpl rc = new ReportConfigImpl();
		rc.setReport(rp);
		StringBuffer buf = new StringBuffer();
		buf.append(" from Trpreportcondition  ");
		buf.append(" as a where a.trpreportid=");
		buf.append(rp.getTrpreportid());
		buf.append(" order by a.rowindex,a.colindex");
		List tempList = dao.iterate(buf.toString());
		rc.getCondition().addAll(tempList);

		buf.setLength(0);
		buf.append(" from Trpreportplugin  ");
		buf.append(" as a where a.trpreportid=");
		buf.append(rp.getTrpreportid());
		tempList = dao.iterate(buf.toString());
		rc.getPlugin().addAll(tempList);

		buf.setLength(0);
		buf.append(" from Trpreportdatasource  ");
		buf.append(" as a where a.trpreportid=");
		buf.append(rp.getTrpreportid());
		tempList = dao.iterate(buf.toString());
		rc.getDatasource().addAll(tempList);

		buf.setLength(0);
		buf.append(" from Trpreportdtl  ");
		buf.append(" as a where reportarea=0 and  a.trpreportid=");
		buf.append(rp.getTrpreportid());
		buf.append(" order by a.rowindex,a.colindex");
		tempList = dao.iterate(buf.toString());
		rc.getHeader().addAll(tempList);

		buf.setLength(0);
		buf.append(" from Trpreportdtl  ");
		buf.append(" as a where reportarea=1 and  a.trpreportid=");
		buf.append(rp.getTrpreportid());
		buf.append(" order by a.rowindex,a.colindex");
		tempList = dao.iterate(buf.toString());
		rc.getDetail().addAll(tempList);

		buf.setLength(0);
		buf.append(" from Trpreportdtl  ");
		buf.append(" as a where reportarea=2 and  a.trpreportid=");
		buf.append(rp.getTrpreportid());
		buf.append(" order by a.rowindex,a.colindex");
		tempList = dao.iterate(buf.toString());
		rc.getFooter().addAll(tempList);
		return rc;
	}

	public static void createMenu(IDao dao, Trpreport rc, Operators os,
			BaseForm form) {
		if (MathUtil.equal(rc.getParentid(), 0))
			return;
		String url = getMenuUrl(rc.getCode());
		StringBuffer hql = new StringBuffer();
		hql.append("  from Tsysmenu where accessurl='");
		hql.append(url.toString());
		hql.append("'");
		SystemLogger.debug(hql.toString());
		Object o = dao.get(hql.toString());
		if (o != null) {
			Tsysmenu po = (Tsysmenu) o;
			po.setParentid(rc.getParentid());
			po.setName(rc.getName());
			os.addUpdateObject(po);
		} else {
			String zh_cn = rc.getName();
			String authstring = "11222222222221";
			String cmprightvalue = "22000";
			Tsysmenu po = (Tsysmenu) new Tsysmenu();
			po.setParentid(rc.getParentid());
			po.setName(rc.getName());
			po.setAccessurl(url.toString());
			po.setRightvalue(authstring);
			po.setMemo("");
			po.setCmprightvalue(cmprightvalue);
			TsysmenuManager menuManager = (TsysmenuManager) WebUtil.getBean(
					"tsysmenuManager", form);
			int max = menuManager.getMaxindexno(po.getParentid());
			po.setIndexno(max);
			os.addAddObject(po);
		}
	}

	public static String getMenuUrl(String code) {
		StringBuffer url = new StringBuffer(
				"/report.do?method=condition&reportid=");
		url.append(code);
		return url.toString();
	}
}
