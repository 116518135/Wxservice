package com.wxservice.framework.web.tag.support;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

import com.wxservice.framework.util.StringUtil;

public class ViewCell extends AbstractCell {
	
	@Override
	protected String getCellValue(TableModel table, Column column) {
		Object id = column.getAttribute("pkid");
		String javascript=(String)column.getAttribute("javascript");
		if(StringUtil.isBlank(javascript)){
			javascript="view";
		}
		StringBuffer cellVal = new StringBuffer();
		cellVal.append("<a href=\"javascript:");
		cellVal.append(javascript);
		cellVal.append("('");
		cellVal.append(id);
		cellVal.append("')\">");
		cellVal.append(column.getPropertyValue());
		cellVal.append("</a>");
		return cellVal.toString();
	}

}

