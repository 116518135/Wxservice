package com.wxservice.service.system; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.wxservice.database.po.system.Tsysmenu;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.impl.CRUDOperatorImpl;
import com.wxservice.framework.util.LabelValue;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SysUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.TreeUtil;
import com.wxservice.framework.web.action.ActionContext;
import com.wxservice.framework.web.action.IStrutsForward;
import com.wxservice.framework.web.form.BaseForm;
import com.wxservice.web.form.system.TsysmenuForm;
/**
 * 
 * 
 */
public class TsysmenuManager extends CRUDOperatorImpl

{
	private static final long serialVersionUID = -1L;

	public TsysmenuManager() {

	}
	// 注册方法汉语名，用于自动写log用
	static {
		registMethodChinese("adj", "调整");
		registMethodChinese("adjSave", "调整保存");
	}

	public Class getEntityClass() {
		return Tsysmenu.class;
	}

	public String getTableKeyField() {
		return "tsysmenuid";
	}

	public void initMenuOptions(TsysmenuForm form, Integer parentid, String dft) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysmenu as a where  ifnull(accessurl,'')='' and a.parentid=").append(parentid)
				.append(" order by a.indexno");
		List list = dao.iterate(hql.toString());
		if (list != null && list.size() > 0) {
			for (int loop = 0; loop < list.size(); loop++) {
				Tsysmenu po = (Tsysmenu) list.get(loop);

				String value = po.getTsysmenuid().toString();
				form.getMenuOptions().add(
						new LabelValue(dft + po.getName(), value));
				initMenuOptions(form, po.getTsysmenuid(), dft + "----");
			}
		}
	}

	@Override
	public void initFormCombo(ActionContext context) {
		TsysmenuForm form = (TsysmenuForm) context.getForm();
		form.getMenuOptions().add(new LabelValue("ROOT", "0"));
		this.initMenuOptions(form, 0, "--");
	}

	public void bindForm(BaseForm baseform, Object object) {
		if (object == null)
			return;
		Tsysmenu po = (Tsysmenu) object;
		TsysmenuForm form = (TsysmenuForm) baseform;
		form.setTsysmenuid(po.getTsysmenuid());
		form.setParentid(po.getParentid());
		form.setName(po.getName());
		form.setAccessurl(po.getAccessurl());
		form.setIndexno(po.getIndexno());
		form.setRightvalue(po.getRightvalue());
		form.setCmprightvalue(po.getCmprightvalue());
		form.setMemo(po.getMemo());
		form.setWebserver(po.getWebserver());

	}

	public void bindEntity(BaseForm baseform, Object object) {

		Tsysmenu po = (Tsysmenu) object;
		TsysmenuForm form = (TsysmenuForm) baseform;
		po.setTsysmenuid(form.getTsysmenuid());
		po.setParentid(form.getParentid());
		po.setName(form.getName());
		po.setAccessurl(form.getAccessurl());
		po.setIndexno(form.getIndexno());
		po.setRightvalue(form.getRightvalue());
		po.setCmprightvalue(form.getCmprightvalue());
		po.setMemo(form.getMemo());
		po.setWebserver(form.getWebserver());

	}

	private boolean isHasChild(Integer id) {
		Boolean result = false;
		StringBuffer hql = new StringBuffer();
		hql.append("from Tsysmenu as a where a.parentid=").append(id);
		List l = dao.list(hql.toString());
		if (l.size() > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public void createListHqlWhere(StringBuffer hql, ActionContext context) {
		TsysmenuForm form = (TsysmenuForm) context.getForm();
		hql.append(" and entity.parentid=");
		hql.append(form.getParentid());
		hql.append(" order by entity.indexno");
	}

	@Override
	public void addEx(ActionContext context) {
		TsysmenuForm form = (TsysmenuForm) context.getForm();
		form.setIndexno(this.getMaxindexno(form.getParentid()));
	}

	public IStrutsForward main(ActionContext context) {
		return forwardJsp(MAIN_PAGE);
	}

	public IStrutsForward tree(ActionContext context) {
		return forwardJsp(TREE_PAGE);
	}

	public IStrutsForward json_old(ActionContext context) {
		try {
			Map map = new HashMap();
			TsysmenuForm form = (TsysmenuForm) context.getForm();
			List nodes = new ArrayList();
			if (form.getId() == null) {
				Map node = new HashMap();
				node.put("id", "0");
				node.put("text", "功能菜单");
				node.put("parentId", "0");
				node.put("isfolder", true);
				Map attrs = new HashMap();
				attrs.put("url", getUrl("0", "list", context.getRequest()));
				attrs.put("target", "rightFrame");
				node.put("attrs", attrs);
				nodes.add(node);
			} else {

				String hql = " from Tsysmenu as a where a.parentid="
						+ form.getId() + " order by a.indexno";
				List list = dao.iterate(hql.toString());
				for (Object o : list) {
					Tsysmenu po = (Tsysmenu) o;
					
					
					Map node = new HashMap();
					node.put("id", po.getTsysmenuid());
					node.put("text", po.getName());
					node.put("parentId", po.getParentid().toString());
					if (isHasChild(po.getTsysmenuid())) {
						node.put("isfolder", true);
					} else {
						node.put("isfolder", false);
					}

					Map attrs = new HashMap();
					attrs.put("url", getUrl(po.getTsysmenuid().toString(),
							"list", context.getRequest()));
					attrs.put("target", "rightFrame");
					node.put("attrs", attrs);
					nodes.add(node);
				}

			}
			map.put("nodes", nodes);
			processSuccess(context, false);
			return this.forwardJson(map);
		} catch (Exception e) {
			processFailure(context, e, false);
			return this.forwardJsp(MAIN_PAGE);
		}

	}

	public String getUrl(String id, String method, HttpServletRequest request) {
		StringBuffer url = new StringBuffer();
		url.append("/tsysmenu.do?method=");
		url.append(method);
		url.append("&parentid=");
		url.append(id);
		String absurl = SysUtil.getAbsUrl(url.toString(), request);
		return absurl;
	}

	public IStrutsForward adj(ActionContext context) {
		TsysmenuForm form = (TsysmenuForm) context.getForm();
		try {
			context.getForm().setMethod("adjSave");
			StringBuffer hql = new StringBuffer();
			hql.append(" from Tsysmenu as a where  a.parentid=").append(
					form.getParentid()).append(" order by a.indexno");
			List list = dao.iterate(hql.toString());
			if (list != null && list.size() > 0) {
				for (int loop = 0; loop < list.size(); loop++) {
					Tsysmenu po = (Tsysmenu) list.get(loop);
					String value = po.getTsysmenuid().toString();
					form.getAdjOptions().add(
							new LabelValue(po.getName(), value));
				}
			}
			processSuccess(context, false);
			return this.forwardJsp("adj");
		} catch (Exception e) {
			processFailure(context, e, false);
			return this.forwardJsp("adj");
		}

	}

	private Tsysmenu getMenu(String menuid) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from Tsysmenu as a where a.tsysmenuid=");
		hql.append(menuid);
		Tsysmenu menu = (Tsysmenu) dao.get(hql.toString());
		return menu;
	}

	/**
	 * 调整菜单顺序
	 * 
	 * @param context
	 * @return
	 */
	public IStrutsForward adjSave(ActionContext context) {
		TsysmenuForm form = (TsysmenuForm) context.getForm();
		try {
			context.getForm().setMethod("adjSave");
			if (StringUtils.isNotBlank(form.getMenuorder())) {
				List menulist = StringUtil
						.string2List(form.getMenuorder(), ",");
				if (menulist != null && menulist.size() > 0) {
					Operators os = new Operators();
					for (int i = 0; i < menulist.size(); i++) {
						String menuid = (String) menulist.get(i);
						Tsysmenu po = (Tsysmenu) this.getMenu(menuid);
						if (po != null) {
							po.setIndexno(i + 1);
							os.addUpdateObject(po);
						}
					}
					dao.execute(os);
				}
			}
			processSuccess(context, false);
			return this.forwardMethod("adj");
		} catch (Exception e) {
			processFailure(context, e, false);
			return this.forwardMethod("adj");
		}

	}

	public int getMaxindexno(Integer id) {
		Boolean result = false;
		StringBuffer hql = new StringBuffer();
		hql.append("from Tsysmenu where parentid=").append(id);
		List list = dao.list(hql.toString());
		if (list != null && list.size() > 0) {
			return list.size() + 1;
		}
		return 1;
	}
	
	//
	public IStrutsForward json(ActionContext context) {
		return jsondata(context, "list");
	}
	private IStrutsForward jsondata(ActionContext context, String method) {
		try {
			StringBuffer json = new StringBuffer("");
			HttpServletRequest request = context.getRequest();
			TsysmenuForm form = (TsysmenuForm) context.getForm();
			String id=this.getTreeId(form.getId());
			json.append("[");
			String str = this.doJsondata(context, id, request,method);
			json.append(str);
			json.append("]");

			return this.forwardJson(json.toString());
		} catch (Exception e) {
			SystemLogger.error("加载树发生错误", e);
		}
		return forwardJsp(MAIN_PAGE);
	}

	private String getTreeSql(String parentid, String where) {
		StringBuffer hsql = new StringBuffer();
		hsql.append("from Tsysmenu  a where a.parentid=");
		hsql.append(parentid);
		if (StringUtil.isNotBlank(where)) {
			hsql.append(" and ");
			hsql.append(where);
		}
		hsql.append(" order by a.indexno asc");
		return hsql.toString();
	}
	public String getTreeId(Tsysmenu po) {
		StringBuffer id = new StringBuffer();
		//id.append("tree");
		id.append(po.getTsysmenuid());
		return id.toString();
	}
	
	public String getTreeId(String idstr) {
	
		//String id=idstr.substring(4,idstr.length());
		String id=idstr;
		
		return id.toString();
	}
	public boolean isEnd(Integer parentid) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Tsysmenu  a where a.parentid=").append(parentid);
		Object obj = dao.get(hsql.toString());
		return obj == null;
	}
	public String getUrl(Integer parentid, String method,
			HttpServletRequest request) {
		StringBuffer url = new StringBuffer();
		url.append("/tsysmenu.do?method=");
		url.append(method);
		url.append("&parentid=");
		url.append(parentid);
		String absurl = SysUtil.getAbsUrl(url.toString(), request);
		return absurl;
	}
	public String createLink2(ActionContext context, Tsysmenu menu,String method) {
		String url=this.getUrl(menu.getTsysmenuid(), method, context.getRequest());
		return url;
	}
	private void doLeaf(ActionContext context, HttpServletRequest request,
			StringBuffer json, Tsysmenu po,String method) {
		json.append("{");
		TreeUtil.doStringJson("href", createLink2(context, po,method), json);
		json.append(",");
		TreeUtil.doStringJson("hrefTarget", "rightFrame", json);
		json.append(",");
		TreeUtil.doStringJson("text", po.getName(), json);
		json.append(",");
		TreeUtil.doStringJson("id", this.getTreeId(po), json);
		json.append(",");
		TreeUtil.doBooleanJson("leaf", "true", json);
		json.append(",");
		/*
		TreeUtil.doStringJson("cls", "cls", json);
		json.append(",");
		*/
		TreeUtil.doStringJson("iconCls", "icon-cls", json);
		json.append("}");
	}
	private String doJsondata(ActionContext context, String parentid,
			HttpServletRequest request,String method) throws Exception {
		StringBuffer json = new StringBuffer();
		String hsql = getTreeSql(parentid, "");
		List list = dao.iterate(hsql);
		if (list != null && list.size() > 0) {
			int loopflag = 0;
			for (int loop = 0; loop < list.size(); loop++) {
				Object o = list.get(loop);
                Tsysmenu po=(Tsysmenu)o;
                if (isEnd(po.getTsysmenuid())) {
					if (loopflag > 0) {
						json.append(",");
					}
					loopflag++;
					doLeaf(context, request, json, po,method);

				} else {
					if (loopflag > 0) {
						json.append(",");
					}
					loopflag++;
					json.append("{");
					TreeUtil.doStringJson("href", createLink2(context, po,method), json);
					json.append(",");
					TreeUtil.doStringJson("hrefTarget", "rightFrame", json);
					json.append(",");
					TreeUtil.doBooleanJson("singleClickExpand", "true", json);
					json.append(",");
					TreeUtil.doStringJson("id", this.getTreeId(po), json);
					json.append(",");
					/*
					TreeUtil.doStringJson("iconCls", "icon-pkg", json);
					json.append(",");
					TreeUtil.doStringJson("cls", "package", json);
					json.append(",");
					*/
					TreeUtil.doStringJson("text", po.getName(), json);
					json.append("}");
				}
			}
    	}
		return json.toString();
	}

	
	
	

}
