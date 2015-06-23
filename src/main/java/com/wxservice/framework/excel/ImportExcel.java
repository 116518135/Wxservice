package com.wxservice.framework.excel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;

import com.wxservice.database.po.system.Tsysexcel;
import com.wxservice.database.po.system.Tsysexceldtl;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;

public class ImportExcel extends SimpleExcelExecutor {

	public Object execute(ExcelContext context) throws Exception {
		List errorList1 = this.read(context);
		List errorList2 = this.check(context);
		errorList2.addAll(errorList1);
		if (errorList2 != null && errorList2.size() > 0) {
			return errorList2;
		}
		IExcelProcess process = context.getExcelProcess();
		List errorList3 = process.execute(context);
		return errorList3;
	}

	public List<Map> check(ExcelContext context) {
		List<Map> errorList = new ArrayList<Map>();
		try {
			if (MathUtil.equal(context.getMaster().getCheckflag(), 0)) {
				return errorList;
			}
			if (StringUtil.isBlank(context.getMaster().getTablename())) {
				return errorList;
			}
			if (StringUtil.isBlank(context.getMaster().getTablekey())) {
				return errorList;
			}
			String tablename = context.getMaster().getTablename();
			String tablekey = context.getMaster().getTablekey().toLowerCase();
			StringBuffer sql_prefix = new StringBuffer(
					" select 1 as count from ");
			sql_prefix.append(tablename);
			sql_prefix.append(" where ");
			sql_prefix.append(tablekey);
			sql_prefix.append("='");
			for (int i = 0; i < context.getResults().size(); i++) {
				Map map = (Map) context.getResults().get(i);
				String keyvalue = (String) map.get(tablekey);
				StringBuffer sql = new StringBuffer(sql_prefix);
				sql.append(keyvalue);
				sql.append("'");
				SystemLogger.debug(sql.toString());
				List t = dao.getJdbcManager().queryForList(sql.toString());// 判断数据库里有没有重复的
				if (t != null && t.size() > 0) {
					Map errorMap = new HashMap();
					errorMap.put("type", "数据错误");
					errorMap.put("rows", map.get("rowid"));
					errorMap.put("keyvalue", keyvalue);
					errorMap.put("message", "数据库中存在当前主键");
					errorList.add(errorMap);
					continue;
				}
				for (int j = i + 1; j < context.getResults().size(); j++) {
					Map othermap = (Map) context.getResults().get(j);
					String otherkeyvalue = (String) othermap.get(tablekey);
					if (keyvalue.equals(otherkeyvalue)) {
						Map errorMap = new HashMap();
						errorMap.put("type", "数据错误");
						errorMap.put("rows", map.get("rowid"));
						errorMap.put("keyvalue", keyvalue);
						errorMap.put("message", "当前Excel文件中存在两个相同的主键");
						errorList.add(errorMap);
					}
				}
			}
		} catch (Exception e) {
			Map errorMap = new HashMap();
			errorMap.put("type", "系统错误");
			errorMap.put("message", "进行主键检查时，系统发生错误");
			errorList.add(errorMap);
			SystemLogger.error(e.getMessage(), e);
		}
		return errorList;
	}

	public List read(ExcelContext context) throws Exception {
		List errorList = new ArrayList();
		InputStream stream = context.getForm().getFormFile().getInputStream();
		if (stream != null) {
			Workbook workbook = null;
			try {
				workbook = Workbook.getWorkbook(stream);
			} catch (Exception e) {
				Map errorMap = new HashMap();
				errorMap.put("type", "系统错误");
				errorMap.put("message", "读取Excel文件发生错误");
				errorList.add(errorMap);
				SystemLogger.error(e.getMessage(), e);
				return errorList;
			}
			Sheet sheet = workbook.getSheet(0);
			if (sheet != null) {
				String code = ExcelUtil.getContent(sheet, 0, 1);
				Tsysexcel master = getMaster(code);
				if (master == null) {
					Map errorMap = new HashMap();
					errorMap.put("type", "系统错误");
					errorMap.put("message", "Excel配置不存在");
					errorList.add(errorMap);
				}
				if (errorList.size() > 0) {
					return errorList;
				}
				String processstr = master.getProcessclass();
				IExcelProcess process = (IExcelProcess) WebUtil.getBean(
						processstr, context.getRequest());
				if (StringUtil.isNotBlank(master.getSpringname())) {
					if (master.getSpringname().indexOf(".") < 0) {
						Object bean = WebUtil.getBean(master.getSpringname(),
								context.getRequest());
						if (bean != null) {
							CRUDOperatorImpl springbean = null;
							springbean = (CRUDOperatorImpl) bean;
							context.setSpringBean(springbean);
						}
					}
				}   

				context.setMaster(master);
				context.setExcelProcess(process);
				List dtllist = getDtllist(master.getTsysexcelid());
				context.setDtllist(dtllist);
				readEx(sheet, context);
				List results = new ArrayList();
				int beginRow = getBeingRow();
				for (int row = beginRow; row < sheet.getRows(); row++) {
					Map rowMap = new HashMap();
					rowMap.put("rowid", String.valueOf(row + 1));
					boolean notBlankRow = StringUtils.isNotBlank(ExcelUtil
							.getContent(sheet, row, 0));
					int plugCol = 0;

					for (int i = 0; notBlankRow && i < dtllist.size(); i++) {

						Tsysexceldtl detail = (Tsysexceldtl) dtllist.get(i);
						IExcelField processor = (IExcelField) WebUtil.getBean(
								detail.getFieldprocess(), context.getRequest());
						//Map valueMap = new HashMap();
						Map errorMap = null;
						try {
							errorMap = processor.importValue(context, detail,
									sheet, row, rowMap, plugCol);

							if (errorMap != null && errorMap.size() > 0) {
								errorList.add(errorMap);
							}
						} catch (Exception pe) {
							SystemLogger.error(pe.getMessage(), pe);
							errorMap = new HashMap();
							errorMap.put("type", "系统错误");
							errorMap.put("message", "读取Excel 字段发生错误:"
									+ detail.getFieldtitle());
							errorList.add(errorMap);
						}
						// 处理.
						//rowMap.putAll(valueMap);
					}
					if (notBlankRow) {
						results.add(rowMap);
					}
				}

				context.setResults(results);

			}

		}
		return errorList;
	}

	public void readEx(Sheet sheet, ExcelContext context) throws Exception {

	}

	public List executeEx(ExcelContext context) throws Exception {
		return new ArrayList();
	}
}
