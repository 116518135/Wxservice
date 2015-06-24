package com.wxservice.framework.report.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.database.po.report.Trpreportplugin;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.report.base.ReportConfig;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.html.HtmlProcess;
import com.wxservice.framework.report.plugin.IPlugin;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.MapUtil;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.WebUtil;

public class WhereProcessor {
	IDao dao = null;
	ReportRequest rr = null;
	Map params = new HashMap();

	public WhereProcessor(ReportRequest rr) throws Exception {
		ReportUtil.setWheres(rr, params);
		rr.getFillService().listen.beforeWhere(rr);
		this.rr = rr;
		dao = (IDao) WebUtil.getBean("dao", rr.getContext().getRequest());
		ReportConfig rc = rr.getRc();
		StringBuffer conditions = new StringBuffer(" 1=1 ");
		// 填充html
		for (int i = 0; i < rc.getCondition().size(); i = i + 1) {// 循环条件
			Trpreportcondition condition = (Trpreportcondition) rc
					.getCondition().get(i);
			if (StringUtil.isNotBlank(condition.getProcessclass())) {
				HtmlProcess hp = (HtmlProcess) WebUtil.getBean(condition
						.getProcessclass(), rr.getContext().getRequest());
				Map<String, String> map = null;
				boolean flag = rr.getFillService().listen.beforeReportCtrl(rr,
						condition, hp, map);
				if (flag) {
					map = hp.processValue(condition, rr.getContext()
							.getRequest());
					//处理条件里有根据其他条件取值情况，但前提是那个条件位置在前面：
					//如开始时间的where:vw.$v_dateflag>=to_date('$value','yyyy/mm/dd')
					Iterator iter= map.entrySet().iterator();
					while(iter.hasNext()){
						Map.Entry entry = (Map.Entry) iter.next();
						Object key = entry.getKey();
						Object val =entry.getValue();
						if(val instanceof String){
							//有变量要替换
							if(val.toString().indexOf(ReportUtil.VALUE_PREFX)>-1){
								String doval = SysUtil.doVarible(val.toString(), params);
							    map.put(key.toString(), doval);
							}
						}
					}
					
				}
				if (map != null) {
					params.putAll(map);
				}
			}
			// 处理插件
			if (!MathUtil.equal(condition.getTrpreportpluginid(), 0)) {
				Trpreportplugin plugin = ReportUtil.getReportPlugin(rr,
						condition.getTrpreportpluginid());
				if (plugin != null) {
					IPlugin p = (IPlugin) WebUtil.getBean(plugin
							.getSpringname(), rr.getContext().getRequest());
					Map pmap = p.processValue(condition, rr);
					params.putAll(pmap);
				}
			}

		}
		params.putAll(rr.getVars());
		this.setVars(params);
		rr.getFillService().listen.afterWhere(rr);
	
	}

	private void setVars(Map params2) {
		this.rr.getVars().put("$billdate", MapUtil.getString(params2, "$v_billdate", false));
		this.rr.getVars().put("$billno", MapUtil.getString(params2, "$v_billno", false));
		
	}

	public String processWhere(String sqlstr) throws Exception {
		String db = rr.getContext().getRequest().getParameter("database");
		int database = MathUtil.parsetInt(db, 0);

		Integer cmpid = WebUtil.getClientSession(rr.getContext().getRequest())
				.getCmpid();
		String src = SysUtil.doVarible(sqlstr, params);
		return src;

	}

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}

	
}
