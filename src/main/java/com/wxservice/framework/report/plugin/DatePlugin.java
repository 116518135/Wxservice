package com.wxservice.framework.report.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.extremecomponents.util.HtmlBuilder;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.database.po.report.Trpreportplugin;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;
import com.wxservice.framework.report.html.HtmlProcess;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.FormatUtil;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.session.ClientSession;

public class DatePlugin extends SimplePlugin {
	public static String DATELIST = "DATELIST";
	public static String DATEQTY = "DATEQTY";
	public static String DATEAMT = "DATEAMT";
	public static String str_blank = "";

	public List getValueList(int beginCol, int beginRow, Map model,
			ReportRequest rr, Trpreportdtl el) {
		HttpServletRequest request = rr.getContext().getRequest();
		Trpreportplugin plugin = ReportUtil.getReportPlugin(rr, el
				.getTrpreportpluginid());
		List datelist = this.getDateList(rr, plugin, "");
		int size = 2;
		List results = new ArrayList();
		for (int i = 0; i < datelist.size(); i++) {
			LabelValue temp = (LabelValue) datelist.get(i);
			for (int j = 0; j < size; j++) {
				String field = "";
				if (j == 0) {
					field = DATEQTY;
				} else {
					field = DATEAMT;
				}
				String asname = new StringBuffer(field).append(i).toString();
				Number v = (Number) model.get(asname.toString());
				String str = FormatUtil.format(asname, v);
				DataElement p = new DataElement();
				p.setRow(beginRow);
				p.setDatatype(2);
				p.setCol(beginCol + j + i * size);
				p.setText(str);
				p.setAlign(el.getAlign());
				p.setTitle("");// 加入title 需要
				p.setField(asname.toString());
				p.setXslField(true);
				p.setHtmlField(false);
				p.setPlugData(true);
				p.setOldfield(field);
				results.add(p);
			}
		}
		return results;
	}

	public List getTitleList(int beginCol, int beginRow, ReportRequest rr,
			Trpreportdtl el) {
		Trpreportplugin plugin = ReportUtil.getReportPlugin(rr, el
				.getTrpreportpluginid());
		List datelist = this.getDateList(rr, plugin, "");
		List<String> titlelist = StringUtil.string2List(el.getJavacontent(),
				";");
		int size = titlelist.size();
		List results = new ArrayList();

		for (int i = 0; i < datelist.size(); i++) {
			LabelValue temp = (LabelValue) datelist.get(i);
			DataElement p = new DataElement();
			p.setRow(beginRow);
			p.setCol(beginCol + i * size);
			p.setWidth(12);
			p.setHeight(0);
			p.setXslField(true);
			p.setHtmlField(false);
			p.setPlugData(true);
			p.setText(temp.getLabel());
			p.setTitle(temp.getLabel());
			p.setField("");
			p.setMergeWidths(size - 1);
			results.add(p);
			for (int j = 0; j < size; j++) {
				String title = (String) titlelist.get(j);
				String field = "";
				if (j == 0) {
					field = DATEQTY;
				} else {
					field = DATEAMT;
				}
				String asname = new StringBuffer(field).append(j).toString();
				p = new DataElement();
				p.setRow(beginRow + 1);
				p.setCol(beginCol + j + i * size);
				p.setWidth(12);
				p.setHeight(0);
				p.setXslField(true);
				p.setHtmlField(false);
				p.setPlugData(true);
				p.setText(title);
				p.setTitle(title);
				p.setField(asname.toString());
				results.add(p);

			}
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getCol(ReportRequest rr, Trpreportdtl el) {
		Trpreportplugin plugin = ReportUtil.getReportPlugin(rr, el
				.getTrpreportpluginid());
		List datelist = this.getDateList(rr, plugin, "");
		List<String> aliaslist = StringUtil.string2List(plugin.getFieldalias(),
				";");
		int c = datelist.size() * aliaslist.size();
		return c;

	}

	@Override
	public int getRow(ReportRequest rr, Trpreportdtl el) {
		return 2;
	}

	protected String buildHeaderHtml(ReportRequest rr, Trpreportdtl el) {
		HttpServletRequest request = rr.getContext().getRequest();
		Trpreportplugin plugin = ReportUtil.getReportPlugin(rr, el
				.getTrpreportpluginid());
		List datelist = this.getDateList(rr, plugin, "");
		List<String> titlelist = StringUtil.string2List(el.getJavacontent(),
				";");
		int size = titlelist.size();
		String headerClass = "tableHeader";
		HtmlBuilder html = new HtmlBuilder();
		html.table(2);
		html.width("100%");

		html.style("TABLE-LAYOUT:fixed");
		html.border("1");
		html.cellSpacing("1");
		html.cellPadding("0");
		html.close();
		HtmlBuilder html1 = new HtmlBuilder();
		HtmlBuilder html2 = new HtmlBuilder();
		html1.tr(2);
		html1.close();
		html2.tr(2);
		html2.close();
		for (int i = 0; i < datelist.size(); i++) {
			LabelValue temp = (LabelValue) datelist.get(i);
			DataElement p = new DataElement();
			html1.td(2);
			html1.styleClass(headerClass);
			html1.colSpan(String.valueOf(size));
			html1.close();
			html1.append(temp.getLabel());
			html1.tdEnd();
			for (int j = 0; j < size; j++) {
				String title = (String) titlelist.get(j);
				html2.td(2);
				html2.width("10");
				html2.styleClass(headerClass);
				html2.close();
				html2.append(title);
				html2.tdEnd();

			}
		}
		html1.trEnd(2);
		html2.trEnd(2);
		html.append(html1);
		html.append(html2);
		html.tableEnd(2);
		return html.toString();
	}

	protected String toValueHtml(Map ds, ReportRequest rr, Trpreportdtl el) {
		HttpServletRequest request = rr.getContext().getRequest();
		Trpreportplugin plugin = ReportUtil.getReportPlugin(rr, el
				.getTrpreportpluginid());
		List datelist = this.getDateList(rr, plugin, "");
		int size = 2;
		String headerClass = "tableHeader";
		HtmlBuilder html = new HtmlBuilder();
		html.table(2);
		html.width("100%");

		html.style("TABLE-LAYOUT:fixed");
		html.border("1");
		html.cellSpacing("1");
		html.cellPadding("0");
		html.close();
		html.tr(2);
		html.close();

		for (int i = 0; i < datelist.size(); i++) {
			DataElement p = new DataElement();
			for (int j = 0; j < size; j++) {
				String field = "";
				if (j == 0) {
					field = "dateqty";
				} else {
					field = "dateamt";
				}
				String asname = new StringBuffer(field).append(i).toString();
				Number v = (Number) ds.get(asname.toString());
				String str = FormatUtil.format(asname, v);
				html.td(2);
				html.width("10");
				html.close();
				if (StringUtil.isBlank(str)) {
					html.append("&nbsp;");
				} else {
					html.append(str);
				}
				html.tdEnd();
			}
		}
		html.trEnd(2);
		html.tableEnd(2);
		return html.toString();
	}

	@Override
	public String toHeaderHtml(Map ds, DataElement data, ReportRequest rr,
			Trpreportdtl el) {

		return this.buildHeaderHtml(rr, el);
	}

	@Override
	public String toValueHtml(Map ds, DataElement data, ReportRequest rr,
			Trpreportdtl el) {
		// TODO Auto-generated method stub
		return this.toValueHtml(ds, rr, el);
	}

	@Override
	public boolean isTable() {
		// TODO Auto-generated method stub
		return true;
	}

	// 下面是处理sql
	// sum(cash where tbsstoreid=$id then qty1 else 0 end ) as $alias$id
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> processValue(Trpreportcondition condition,
			ReportRequest rr) {
		Map<String, String> result = new HashMap<String, String>();
		Trpreportplugin plugin = ReportUtil.getReportPlugin(rr, condition
				.getTrpreportpluginid());
		if (StringUtil.isBlank(plugin.getTemplate()))
			return result;
		if (StringUtil.isBlank(plugin.getFieldalias()))
			return result;

		List list = getDateList(rr, plugin, condition.getJsname());
		StringBuffer casewhen = new StringBuffer();
		Map params = new HashMap();
		Map priceMap = this.getPriceMap(rr);
		params.putAll(priceMap);
		for (int j = 0; j < list.size(); j++) {
			LabelValue temp = (LabelValue) list.get(j);
			if (j > 0) {
				casewhen.append(",");
			}
			casewhen.append(temp.getValue());
		}

		String key = this.getPluginSqlKey(plugin.getFieldalias());
		result.put(key, casewhen.toString());
		return result;
	}

	@SuppressWarnings("unchecked")
	private List getDateList(ReportRequest rr, Trpreportplugin plugin,
			String jsname) {
		List datelist = (List) rr.getContext().getRequest().getAttribute(
				DATELIST);
		if (datelist == null) {
			datelist = new ArrayList();
			String[] arr = plugin.getTemplate().split(",");
			int firstTime = Integer.valueOf(arr[0]);
			int endTime = Integer.valueOf(arr[1]);
			int gap = Integer.valueOf(arr[2]);
			int length = 0;
			int time = endTime - firstTime;
			if (time % gap == 0) {
				length = time / gap;
			} else {
				length = time / gap + 1;
			}
			for (int i = 0; i <= length; i++) {
				int first = firstTime + i * gap;
				int next = first + gap - 1;
				StringBuffer str = new StringBuffer();
				str.append("(");
				str.append(first);
				str.append(":00-");
				str.append(next);
				str.append(":59)");
				// datelist.add(str);
				StringBuffer sql = new StringBuffer();
				sql.append(" sum(case when to_char(vw.billdate,'hh24')");
				sql.append(" between '");
				sql.append(StringUtil.leftPad(String.valueOf(first), 2, "0"));
				sql.append("' and '");
				sql.append(StringUtil.leftPad(String.valueOf(next), 2, "0"));
				sql.append("' then qty1 else 0 end ) as ");
				sql.append(DATEQTY);
				sql.append(i);
				sql.append(", sum(case when to_char(vw.billdate,'hh24')");
				sql.append(" between '");
				sql.append(StringUtil.leftPad(String.valueOf(first), 2, "0"));
				sql.append("' and '");
				sql.append(StringUtil.leftPad(String.valueOf(next), 2, "0"));
				sql.append("' then amt1 else 0 end ) as ");
				sql.append(DATEAMT);
				sql.append(i);
				datelist.add(new LabelValue(str.toString(), sql.toString()));// 用于表头和select语句
			}
			rr.getContext().getRequest().setAttribute(DATELIST, datelist);
		}
		return datelist;
	}
}
