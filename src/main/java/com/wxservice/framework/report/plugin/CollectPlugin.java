package com.wxservice.framework.report.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.database.po.report.Trpreportplugin;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;
import com.wxservice.framework.report.html.CollectHtmlProcess;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.StringUtil;

public class CollectPlugin extends SimplePlugin {

	@SuppressWarnings("unchecked")
	@Override
	public List getValueList(int beginCol, int beginRow, Map model,
			ReportRequest rr, Trpreportdtl el) {
		Trpreportplugin plugin = ReportUtil.getReportPlugin(rr, el
				.getTrpreportpluginid());
		String[] templateArr = plugin.getTemplate().split(";");
		List list = (List) rr.getContext().getRequest().getAttribute(
				CollectHtmlProcess.collectCateList);
		List valueList = new ArrayList();
		int index = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LabelValue catetemp = (LabelValue) list.get(i);
				if (CollectHtmlProcess.AMT.indexOf(catetemp.getLabel()) > -1
						|| catetemp.getLabel().indexOf(
								CollectHtmlProcess.PRICETYPE) > -1
						|| catetemp.getLabel().indexOf(CollectHtmlProcess.SIZE) > -1) {
					continue;
				}
				StringBuffer key = new StringBuffer();
				key.append(catetemp.getValue());
				String value = formatValue(model.get(key.toString()), "");
				String title = formatValue(catetemp.getLabel(), "");
				DataElement p = new DataElement();
				p.setRow(beginRow);
				if (el.getDimvalueflag() != null) {
					p.setDimvalueflag(el.getDimvalueflag());
				}
				p.setCol(index + beginCol);
				index++;
				p.setText(value);
				p.setAlign(0);
				// p.setTitle(title);// 加入title 需要
				if (templateArr != null) {
					for (String template : templateArr) {
						if (template.indexOf(value + "=") > -1) {
							String title2 = template.substring(template
									.indexOf("=") + 1);
							p.setTitle(title2);
						}
					}
				}
				p.setField(key.toString());
				valueList.add(p);
			}
		}

		return valueList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List getTitleList(int beginCol, int beginRow, ReportRequest rr,
			Trpreportdtl el) {
		Trpreportplugin plugin = ReportUtil.getReportPlugin(rr, el
				.getTrpreportpluginid());
		String[] templateArr = plugin.getTemplate().split(";");
		List list = (List) rr.getContext().getRequest().getAttribute(
				CollectHtmlProcess.collectCateList);
		List titleList = new ArrayList();
		int index = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LabelValue catetemp = (LabelValue) list.get(i);
				if (CollectHtmlProcess.AMT.indexOf(catetemp.getLabel()) > -1
						|| catetemp.getLabel().indexOf(
								CollectHtmlProcess.PRICETYPE) > -1
						|| catetemp.getLabel().indexOf(CollectHtmlProcess.SIZE) > -1) {
					continue;
				}
				String value = this.formatValue(catetemp.getLabel(), "");
				DataElement p = new DataElement();
				p.setRow(beginRow);
				p.setCol(beginCol + index);
				index++;
				p.setWidth(12);
				p.setHeight(0);
				if (el.getDimvalueflag() != null) {
					p.setDimvalueflag(el.getDimvalueflag());
				}
				if (templateArr != null) {
					for (String template : templateArr) {
						if (template.indexOf(value + "=") > -1) {
							String title = template.substring(template
									.indexOf("=") + 1);
							p.setTitle(title);
							p.setText(title);
							p.setField(catetemp.getValue());
						}
					}
				}
				titleList.add(p);
			}
		}
		return titleList;
	}

	public int getCol(ReportRequest rr, Trpreportdtl el) {
		HttpServletRequest request = rr.getContext().getRequest();
		List list = (List) request
				.getAttribute(CollectHtmlProcess.collectCateList);
		int index = 0;
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				LabelValue catetemp = (LabelValue) list.get(i);
				String value = catetemp.getLabel();
				if (CollectHtmlProcess.AMT.indexOf(value) > -1
						|| catetemp.getLabel().indexOf(
								CollectHtmlProcess.PRICETYPE) > -1
						|| value.indexOf(CollectHtmlProcess.SIZE) > -1) {
					index++;
				}
			}
			return list.size() - index;
		}
		return 0;
	}

	@Override
	public String formatValue(Object obj, String dft) {
		if (obj instanceof Number) {
			return obj.toString();
		}
		String value = (String) obj;
		if (StringUtil.notNull(value).equals("")) {
			return dft;
		} else {
			return value;
		}
	}
}
