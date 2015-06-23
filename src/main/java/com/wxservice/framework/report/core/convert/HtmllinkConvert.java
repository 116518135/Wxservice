package com.wxservice.framework.report.core.convert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;
import com.wxservice.framework.util.MapUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysUtil;

//0
public class HtmllinkConvert extends SimpleConvert {
	public void convert(Map ds, ReportRequest rr, Trpreportdtl el,
			DataElement p, int row) throws Exception {
		baseConvert(el, p, row);
		String template = el.getJavacontent();
		Map params = new HashMap();
		params.put("${ctx}", rr.getContext().getRequest().getContextPath());
		List<String> list = StringUtil.string2List(el.getField().toLowerCase(),
				",");
		StringBuffer value = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			String field = list.get(i);
			if (i > 0) {
				value.append(",");
			}
			String o = MapUtil.getString(ds, field, false);
			value.append(o);
			params.put("$" + field, o);
		}
		String str = SysUtil.doVarible(template, params);
		StringBuffer sb = new StringBuffer();
		sb.append("method=view&auth_string =");
		String auth_string = rr.getContext().getForm().getAuth_string();
		sb.append(auth_string);
		str = str.replace("method=view", sb.toString());
		p.setText(str);
	}
}
