package com.wxservice.framework.report.core.convert;

import java.util.Map;

import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;


public class LabelConvert extends SimpleConvert{
	public void convert(Map ds, ReportRequest rr, Trpreportdtl el,
			DataElement p, int row) throws Exception {
		// TODO Auto-generated method stub
		baseConvert(el, p, row);
		p.setText(el.getTitle());
	}
	
}
