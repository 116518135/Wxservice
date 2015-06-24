package com.wxservice.framework.report.plugin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.DataElement;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.service.NameManager;
public class SimplePlugin implements IPlugin{
	public IDao dao=null;
	public NameManager nameManager=null;
	public String getPluginSqlKey(String jsname){
	   return ReportUtil.getPluginSqlKey(jsname);
	}

    @Override
	public List getTitleList(int beginCol, int beginRow, ReportRequest rr,
			Trpreportdtl el) throws Exception {

		return null;
	}
    @Override
	public List getValueList(int beginCol, int beginRow, Map model, ReportRequest rr,
			Trpreportdtl el) throws Exception {
		return null;
	}
	public String formatValue(Object obj, String dft) {
		String value = (String) obj;
		if (StringUtil.notNull(value).equals("")) {
			return dft;
		} else {
			return value;
		}
	}

    @Override
	public int getCol(ReportRequest rr,
			Trpreportdtl el) {
		return 1;
	}
    @Override
	public int getRow(ReportRequest rr,
			Trpreportdtl el) {
		return 1;
	}
	@Override
	public String toHeaderHtml(Map ds,DataElement data,ReportRequest rr, Trpreportdtl el) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toValueHtml(Map ds,DataElement data,ReportRequest rr, Trpreportdtl el) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isTable() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getWidthPer() {
		return "20%";
	}
	public IDao getDao() {
		return dao;
	}
	public void setDao(IDao dao) {
		this.dao = dao;
	}
	@Override
	public Map<String, String> processValue(Trpreportcondition condition,
			ReportRequest rr) {
		return null;
	}
	public NameManager getNameManager() {
		return nameManager;
	}

	public void setNameManager(NameManager nameManager) {
		this.nameManager = nameManager;
	}
	
	public Map getPriceMap(ReportRequest rr){
		Map priceMap=new HashMap();
		String pricetype=rr.getContext().getRequest().getParameter("price");
		if(StringUtil.isNotBlank(pricetype)){
			priceMap.put("$v_price", pricetype);
		}
		return priceMap;
	}
}
