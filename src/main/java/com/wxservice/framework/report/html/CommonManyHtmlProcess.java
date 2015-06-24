package com.wxservice.framework.report.html;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.framework.util.HtmlBuilder;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysUtil;

public class CommonManyHtmlProcess extends CommonHtmlProcess {
	@Override
	public Map<String, String> processValue(Trpreportcondition condition,
			HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		String value = this.catchValue(condition.getJsname(), request);
		doValue(result, condition,
				request);
		return result;
	}

	protected void doValue(Map<String, String> result,
			Trpreportcondition condition, HttpServletRequest request) {
		List<String> jsArr = StringUtil.string2List(condition.getJsname(), "|");
		List<String> sqlArr = StringUtil
				.string2List(condition.getWheres(), "|");
		for (int i = 0; i < jsArr.size(); i++) {
			String jsname = jsArr.get(i);
			String value = this.catchValue(jsname, request);
			String valuekey = this.getValueKey(jsname);
			result.put(valuekey.toString(), value);
			if (StringUtil.isNotBlank(condition.getWheres())) {
				String sqlkey = this.getSqlKey(jsname);
				String whereTemplate = null;
				if (sqlArr.size() > i) {
					whereTemplate = sqlArr.get(i);
				}
				if (StringUtils.isNotBlank(value)) {
					if (StringUtils.isNotBlank(whereTemplate)) {
						Map<String, String> params = new HashMap<String, String>();
						params.put(VAR_KEY_VALUE, value);
						String sql = SysUtil.doVarible(whereTemplate, params);
						result.put(sqlkey.toString(), sql);
					} else {
						result.put(sqlkey.toString(), value);
					}
				} else {
					result.put(sqlkey.toString(), "1=1");
				}
			}

		}

	}

}
