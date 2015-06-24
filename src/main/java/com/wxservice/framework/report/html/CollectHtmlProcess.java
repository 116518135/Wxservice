package com.wxservice.framework.report.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.StringUtil;

public class CollectHtmlProcess extends CommonHtmlProcess {
	final public static String $collectGroup = "$collectGroup";
	final public static String $collectSelect = "$collectSelect";
	final public static String COLLECT = "collect";
	final public static String ALIAS = "alias";// 别名
	final public static String collectCateList = "collectCateList";
	final public static String AMT = "vw.stdprice,vw.discount,vw.unitprice";
	final public static String SIZE = "$p_sizeqty";
	final public static String PRICETYPE = "pricetype";

	@Override
	public Map<String, String> processValue(Trpreportcondition condition,
			HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		System.out.println(request.getParameter(COLLECT));
		String[] valueArray = request.getParameter(COLLECT).split("[+]");
		StringBuffer select = new StringBuffer("");
		StringBuffer group = new StringBuffer("");
		List list = (List) request.getAttribute(collectCateList);
		boolean isExit = false;
		if (list != null) {
			isExit = true;
		} else {
			list = new ArrayList();
		}
		if (valueArray != null) {
			int index = 0;
			for (int i = 0; i < valueArray.length; i++) {
				if (i != 0) {
					select.append(",");
					group.append(",");
				}
				String value = valueArray[i];
				select.append(value);
				if (value.indexOf(SIZE) > -1) {// 尺码的情况下
					String groupStr = group.toString();
					if (StringUtil.isNotBlank(groupStr)) {
						groupStr = groupStr.substring(0, groupStr
								.lastIndexOf(","));
						group = new StringBuffer(groupStr);
					} else {
						group = new StringBuffer("1");
					}
					list.add(new LabelValue(value, ""));
					continue;
				}
				if (value.indexOf("sum") > -1) {// 没有group by的情况下
					String groupStr = group.toString();
					groupStr = groupStr.substring(0, groupStr.lastIndexOf(","));
					group = new StringBuffer(groupStr);
					list.add(new LabelValue(value, ALIAS + "sum" + index));
					select.append(" as ");
					select.append(ALIAS);
					select.append("sum");
					select.append(index);
					index++;
					continue;
				}
				select.append(" as ");
				select.append(ALIAS);
				select.append(i);
				// 下面是用于group by
				group.append(value);
				if (!isExit) {
					// list.add(value);
					list.add(new LabelValue(value, ALIAS + i));
				}
			}
		}
		request.setAttribute(collectCateList, list);
		result.put($collectSelect, select.toString());
		result.put($collectGroup, group.toString());
		String sqlkey = this.getSqlKey(condition.getJsname());
		String where = "";
		if (StringUtil.isNotBlank(where)) {
			result.put(sqlkey, where);
		} else {
			result.put(sqlkey, "1=1");
		}
		return result;
	}

}
