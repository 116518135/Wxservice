package com.wxservice.framework.report.core.convert;

import java.util.Map;

import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;

public class FieldConvert extends SimpleConvert {
	public void convert(Map ds, ReportRequest rr, Trpreportdtl el,
			DataElement p, int row) throws Exception {
		// TODO Auto-generated method stub
		baseConvert(el, p, row);
		if (ds == null) {
			return;
		}
		Object o = this.getFieldValue(ds, el.getField());
		if (o != null) {
			this.setFieldValue(p, o, el);
		} else {
			p.setText("");
		}

	}
}
