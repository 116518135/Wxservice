package com.wxservice.framework.report.core.convert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.jxpath.CompiledExpression;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.lang.StringUtils;

import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SystemLogger;


//0
public class ExpressionConvert extends SimpleConvert {
	public void convert(Map ds, ReportRequest rr, Trpreportdtl el,
			DataElement p, int row) throws Exception {
		baseConvert(el, p, row);
		String path = el.getJavacontent();
		if (ds == null) {
			ds = new HashMap();
		}
		if (StringUtils.isNotBlank(path)) {

			Number number = null;
			try {
				path = StringUtil.replace(path.toLowerCase(), "/", " div ");
				if (path.indexOf("sum") >=0 || path.indexOf("avg") >= 0) {
					List mainDs=null;
					mainDs=(List)rr.getFillService().getDataSource("main");
					JXPathContext context = JXPathContext.newContext(mainDs);
					CompiledExpression expression = JXPathContext.compile(path);
					number = (Number) expression.getValue(context);
					this.setFieldValue(p, number, el);
					ds.put(el.getField(), number);
				}else{
					JXPathContext context = JXPathContext.newContext(ds);
					CompiledExpression expression = JXPathContext.compile(path);
					number = (Number) expression.getValue(context);
					this.setFieldValue(p, number, el);
					ds.put(el.getField(), number);
				}
				

			} catch (Exception e) {
				this.setFieldValue(p, new Integer(0), el);
				ds.put(el.getField(), 0);
				SystemLogger.error("表达式错误或者计算错误");

			}
		}
	}


}
