package com.wxservice.service;
import java.util.Date;

import com.wxservice.database.po.system.Tsysbillnorule;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.session.ClientSession;
public class BillnoGenerator extends AbstractBillnoGenerator{
	public synchronized String generatorBillno(Integer cmpid, String mark,
			String billcode, boolean isUpdateCount,Date date) {
		Tsysbillnorule rule = this.getBillnorule(cmpid, billcode,true);
		int count = rule.getCurcount();
        return generatorBillno(mark,isUpdateCount,date,rule,count);
	}

	public synchronized String generatorBillno2(String billcode,Date date) {
		Tsysbillnorule rule = this.getBillnorule(0,billcode,true);
		int count = rule.getCurcount();
        return generatorBillno("",true,date,rule,count);
	}
	
	public String generatorBillno(ActionContext context,String billcode) {
		ClientSession client = WebUtil.getClientSession(context.getRequest());
		return generatorBillno(client, billcode);
	}
	
	
	public String generatorBillno(ClientSession client,String billcode) {
		return generatorBillno(client, billcode, true);
	}

	public String generatorBillno(ActionContext context,String billcode,
			boolean isUpdateCounter) {
		ClientSession client = WebUtil.getClientSession(context.getRequest());
		return generatorBillno(client, billcode, isUpdateCounter);
	}

	public String generatorBillno(ClientSession client,String billcode,
			boolean isUpdateCounter) {
		String mark = client.getCmpmark();
		Date today=new Date();
		return this.generatorBillno(client.getCmpid(), mark, billcode, isUpdateCounter,today);
	}
	
	public String generatorBillno1(String billcode) {
		Date today=new Date();
		return this.generatorBillno2(billcode,today);
	}

	/*public String generatorBillno(Integer cmpid, String billcode,
			boolean isUpdateCount) {
		Tbscmp cmp = (Tbscmp) dao.get(Tbscmp.class, cmpid);
		String mark = cmp.getMark();
		Date today=new Date();
		return this.generatorBillno(cmpid, mark, billcode, isUpdateCount,today);
	}*/

	
}