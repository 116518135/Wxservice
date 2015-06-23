package com.wxservice.framework.report.core.convert;

import java.util.Map;

import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;


//0
public class VariableConvert extends SimpleConvert{
	public void convert(Map ds, ReportRequest rr, Trpreportdtl el,
			DataElement p, int row) throws Exception {
		baseConvert(el, p, row);
		p.setText(getVariableValue(rr,el.getJavacontent()));
	}
}
