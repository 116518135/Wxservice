package com.wxservice.framework.report.core.convert;

import java.util.Map;

import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;


public class ScriptConvert extends SimpleConvert{
	public void convert(Map ds, ReportRequest rr, Trpreportdtl el,
			DataElement p, int row) throws Exception {
		baseConvert(el, p, row);
//		Object ds = null;
//		ds = getDataSource(el.getDatasourcename());
		String value = this.getFieldScriptValue(ds, el.getField(), el,rr);
		p.setText(value);
	}

}
