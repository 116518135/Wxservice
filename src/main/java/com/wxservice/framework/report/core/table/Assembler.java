package com.wxservice.framework.report.core.table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.core.TableModel;

import com.wxservice.framework.report.base.ReportPrint;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;
import com.wxservice.framework.util.SysUtil;

public class Assembler {

	private TableModel model;

	final public static String COLUMN_PROPERTY = "property";

	final public static String COLUMN_TITLE = "title";

	final public static String COLUMN_ALIAS = "alias";

	final public static String COLUMN_SORTABLE = "sortable";

	public List initList(List children, ReportRequest ctx) {
		List result = new ArrayList();
		Map all = new LinkedMap();
		if (children != null && children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				Object o = children.get(i);
				if (o instanceof DataElement) {
					DataElement el = (DataElement) o;
					if (el.getIsHtmlField()) {
						Map map = (Map) all.get(el.getRow());
						if (map == null) {
							map = new HashMap();
							all.put(el.getRow(), map);
						}
						map.put(el.getField(),el.getText());
					}
				}

			}
		}
		// 转换成list
		result.addAll(all.values());
		return result;
	}

	public String build(ReportRequest ctx, ReportPrint rp) throws Exception {
		List footerList = initList(rp.getFooter().getChildren(), ctx);
		initModel(ctx.getContext().getRequest(), footerList);
		List detailList = initList(rp.getDetail().getChildren(), ctx);
		initTable(ctx, detailList);
		initRow();
		initCloumns(ctx, rp.getPageHeader().getChildren());
		return model.assemble().toString();
	}

	public void initCloumns(ReportRequest ctx, List children) {
		// 加入title
		if (children != null && children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				Object o = children.get(i);
				if (o instanceof DataElement) {
					DataElement el = (DataElement) o;
					if (el.getIsHtmlField()) {
						Column column = this.addColumn(el.getField(), el
								.getTitle());
						//column.setWidth(String.valueOf(el.getWidth()));
						if (!el.isSortable()) {
							//column.setWidth(String.valueOf(el.getWidth()));
							column.setWidth(el.getWidthper());
						}

						column.setSortable(el.isSortable());
						// 设置Style

						
					}

					// column.setWidth(String.valueOf(el.getWidth()));
				}

			}
		}

	}

	

	// --------------- init model mothods ------------------------------
	protected void initModel(HttpServletRequest request, List footerList) {
		Context context = null;
		context = new HttpServletRequestContext(request);
		model = new MyTableModelImpl(context, footerList);
	}

	protected void initModel(TableModel model) {
		this.model = model;
	}

	protected TableModel getModel() {
		return model;
	}

	// --------------- init row mothods ------------------------------
	protected void initTable(ReportRequest ctx, Collection list) {
		Table table = model.getTableInstance();
		table.setWidth(getTableWidth());
		table.setTableId(getTableId());
		table.setItems(list);
		table.setStyle("word-break:keep-all");
		table.setBorder("solid 1px");
		StringBuffer action=new StringBuffer();
		action.append(model.getContext().getContextPath());
		action.append(ctx.getContext().getForm().getFormpath());
		action.append(".do");
		table.setAction(action.toString());
		//table.setTitle(ctx.getRc().getReport().getName());
		table.setAutoIncludeParameters(false);
		table.setFilterable(false);
		table.setForm(SysUtil.getClassname(ctx.getContext().getForm()));
		model.addTable(table);
	}

	private String tableId;

	private String tableWidth;

	protected String getTableId() {
		return tableId == null ? "reportForm" : tableId;
	}

	protected void setTableId(String tableId) {
		this.tableId = tableId;
	}

	protected String getTableWidth() {
		return tableWidth == null ? "100%" : tableWidth;
	}

	protected void setTableWidth(String tableWidth) {
		this.tableWidth = tableWidth;
	}

	// --------------- init row mothods ------------------------------
	protected void initRow() {
		Row row = model.getRowInstance();
		setRowHighlight(row);
		model.addRow(row);
	}

	protected void setRowHighlight(Row row) {
		row.setHighlightRow(Boolean.TRUE);
	}

	// --------------- add column mothods ------------------------------
	protected Column addColumn(String property, String title, String alias,
			boolean sortable) {
		Column column = model.getColumnInstance();

		if (property != null)
			column.setProperty(property);

		if (alias != null)
			column.setAlias(alias);

		if (title != null)
			column.setTitle(title);
		else
			column.setTitle(StringUtils.capitalize(property));

		column.setSortable(Boolean.valueOf(sortable));

		model.addColumn(column);
		return column;
	}

	protected Column addColumn(String property) {
		return addColumn(property, null, null, true);
	}

	protected Column addColumn(String property, String title) {
		return addColumn(property, title, null, true);
	}

	protected Column addColumn(Map paramMap) {
		String property = (String) paramMap.get(COLUMN_PROPERTY);
		String title = (String) paramMap.get(COLUMN_TITLE);
		String alias = (String) paramMap.get(COLUMN_ALIAS);
		Boolean sortable = (Boolean) paramMap.get(COLUMN_SORTABLE);
		Column column = addColumn(property, title, alias, sortable);
		return column;
	}
}
