package com.wxservice.web.form.report;

import java.util.List;

import com.wxservice.framework.web.form.BaseForm;

/**
 * 
 * 描述： Struts Form 创建人：Gererator
 */

public class TrpreportForm extends BaseForm

{
	private static final long serialVersionUID = -1L;

	public TrpreportForm() {
		this.setService("trpreportManager");
		this.setModuleTitle("报表定义");
		this.setAnonymous("0");
	}

	Integer trpreportid;
	String code;
	String name;
	String processclass;
	String listenclass;
	String serverurl;
	Integer parentid = 0;

	Integer trpreportdtlid;
	String title;
	String field;
	Integer fieldtype;
	Integer rowindex;
	Integer colindex;
	Integer width;
	Integer align;
	Integer linkflag;
	String linkurl;
	String javacontent;
	java.math.BigDecimal valuemin;
	java.math.BigDecimal valuemax;
	String color;
	Integer trpreportpluginid;
	Integer reportarea;
	// xj
	// 菜单需要
	String id;
	List fieldtypelist;
	List alignlist;
	List trpreportpluginidlist;
	// 复制需要
	String check;
	String codeflag;

	String activeTab = "0";
	List parentlist;
	String rowband;
	String colband;
	String pageband;
	String hiddenband;
	String databand;
	String subband;
	String totalband;
	Integer showsubflag = 0;
	String header[];
	List rowbandlist;
	List colbandlist;
	List pagebandlist;
	List hiddenbandlist;
	List databandlist;
	List subbandlist;
	List totalbandlist;
	String rowbands;
	String colbands;
	String pagebands;
	String hiddenbands;
	String databands;
	String subbands;
	Integer trpdcubetplid = 0;

	public String getTotalband() {
		return totalband;
	}

	public void setTotalband(String totalband) {
		this.totalband = totalband;
	}

	public List getTotalbandlist() {
		return totalbandlist;
	}

	public void setTotalbandlist(List totalbandlist) {
		this.totalbandlist = totalbandlist;
	}

	public String getSubband() {
		return subband;
	}

	public void setSubband(String subband) {
		this.subband = subband;
	}

	public String getSubbands() {
		return subbands;
	}

	public void setSubbands(String subbands) {
		this.subbands = subbands;
	}

	public List getSubbandlist() {
		return subbandlist;
	}

	public void setSubbandlist(List subbandlist) {
		this.subbandlist = subbandlist;
	}

	public Integer getTrpdcubetplid() {
		return trpdcubetplid;
	}

	public void setTrpdcubetplid(Integer trpdcubetplid) {
		this.trpdcubetplid = trpdcubetplid;
	}

	public String getRowbands() {
		return rowbands;
	}

	public void setRowbands(String rowbands) {
		this.rowbands = rowbands;
	}

	public String getColbands() {
		return colbands;
	}

	public void setColbands(String colbands) {
		this.colbands = colbands;
	}

	public String getPagebands() {
		return pagebands;
	}

	public void setPagebands(String pagebands) {
		this.pagebands = pagebands;
	}

	public String getHiddenbands() {
		return hiddenbands;
	}

	public void setHiddenbands(String hiddenbands) {
		this.hiddenbands = hiddenbands;
	}

	public String getDatabands() {
		return databands;
	}

	public void setDatabands(String databands) {
		this.databands = databands;
	}

	public List getDatabandlist() {
		return databandlist;
	}

	public void setDatabandlist(List databandlist) {
		this.databandlist = databandlist;
	}

	public List getColbandlist() {
		return colbandlist;
	}

	public void setColbandlist(List colbandlist) {
		this.colbandlist = colbandlist;
	}

	public List getPagebandlist() {
		return pagebandlist;
	}

	public void setPagebandlist(List pagebandlist) {
		this.pagebandlist = pagebandlist;
	}

	public List getHiddenbandlist() {
		return hiddenbandlist;
	}

	public void setHiddenbandlist(List hiddenbandlist) {
		this.hiddenbandlist = hiddenbandlist;
	}

	public List getRowbandlist() {
		return rowbandlist;
	}

	public void setRowbandlist(List rowbandlist) {
		this.rowbandlist = rowbandlist;
	}

	public String getRowband() {
		return rowband;
	}

	public void setRowband(String rowband) {
		this.rowband = rowband;
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public Integer getShowsubflag() {
		return showsubflag;
	}

	public void setShowsubflag(Integer showsubflag) {
		this.showsubflag = showsubflag;
	}

	public String getDataband() {
		return databand;
	}

	public void setDataband(String databand) {
		this.databand = databand;
	}

	public String getColband() {
		return colband;
	}

	public void setColband(String colband) {
		this.colband = colband;
	}

	public String getPageband() {
		return pageband;
	}

	public void setPageband(String pageband) {
		this.pageband = pageband;
	}

	public String getHiddenband() {
		return hiddenband;
	}

	public void setHiddenband(String hiddenband) {
		this.hiddenband = hiddenband;
	}

	public List getParentlist() {
		return parentlist;
	}

	public void setParentlist(List parentlist) {
		this.parentlist = parentlist;
	}

	protected String dispprocessor;// 显示处理类
	protected List dispprocessoroptions;
	protected Integer dimvalueflag = 0;// 维度值

	public String getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}

	public String getCodeflag() {
		return codeflag;
	}

	public void setCodeflag(String codeflag) {
		this.codeflag = codeflag;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public List getTrpreportpluginidlist() {
		return trpreportpluginidlist;
	}

	public void setTrpreportpluginidlist(List trpreportpluginidlist) {
		this.trpreportpluginidlist = trpreportpluginidlist;
	}

	public List getAlignlist() {
		return alignlist;
	}

	public void setAlignlist(List alignlist) {
		this.alignlist = alignlist;
	}

	public List getFieldtypelist() {
		return fieldtypelist;
	}

	public void setFieldtypelist(List fieldtypelist) {
		this.fieldtypelist = fieldtypelist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTrpreportdtlid() {
		return trpreportdtlid;
	}

	public void setTrpreportdtlid(Integer trpreportdtlid) {
		this.trpreportdtlid = trpreportdtlid;
	}

	/**
	 * @return 返回 报表编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            要设置的 报表编码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return 返回 报表名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 报表名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回 处理类
	 */
	public String getProcessclass() {
		return processclass;
	}

	/**
	 * @param processclass
	 *            要设置的 处理类
	 */
	public void setProcessclass(String processclass) {
		this.processclass = processclass;
	}

	/**
	 * @return 返回 监听类
	 */
	public String getListenclass() {
		return listenclass;
	}

	/**
	 * @param listenclass
	 *            要设置的 监听类
	 */
	public void setListenclass(String listenclass) {
		this.listenclass = listenclass;
	}

	/**
	 * @return 返回 server地址
	 */
	public String getServerurl() {
		return serverurl;
	}

	/**
	 * @param serverurl
	 *            要设置的 server地址
	 */
	public void setServerurl(String serverurl) {
		this.serverurl = serverurl;
	}

	/**
	 * @return 返回 父菜单
	 */
	public Integer getParentid() {
		return parentid;
	}

	/**
	 * @param parentid
	 *            要设置的 父菜单
	 */
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	/**
	 * @return 返回 报表ID
	 */
	public Integer getTrpreportid() {
		return trpreportid;
	}

	/**
	 * @param trpreportid
	 *            要设置的 报表ID
	 */
	public void setTrpreportid(Integer trpreportid) {
		this.trpreportid = trpreportid;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return 返回 字段
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            要设置的 字段
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return 返回 字段类型
	 */
	public Integer getFieldtype() {
		return fieldtype;
	}

	/**
	 * @param fieldtype
	 *            要设置的 字段类型
	 */
	public void setFieldtype(Integer fieldtype) {
		this.fieldtype = fieldtype;
	}

	/**
	 * @return 返回 行
	 */
	public Integer getRowindex() {
		return rowindex;
	}

	/**
	 * @param rowindex
	 *            要设置的 行
	 */
	public void setRowindex(Integer rowindex) {
		this.rowindex = rowindex;
	}

	/**
	 * @return 返回 列
	 */
	public Integer getColindex() {
		return colindex;
	}

	/**
	 * @param colindex
	 *            要设置的 列
	 */
	public void setColindex(Integer colindex) {
		this.colindex = colindex;
	}

	/**
	 * @return 返回 宽度
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            要设置的 宽度
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return 返回 对齐方式
	 */
	public Integer getAlign() {
		return align;
	}

	/**
	 * @param align
	 *            要设置的 对齐方式
	 */
	public void setAlign(Integer align) {
		this.align = align;
	}

	/**
	 * @return 返回 链接标志
	 */
	public Integer getLinkflag() {
		return linkflag;
	}

	/**
	 * @param linkflag
	 *            要设置的 链接标志
	 */
	public void setLinkflag(Integer linkflag) {
		this.linkflag = linkflag;
	}

	/**
	 * @return 返回 链接url
	 */
	public String getLinkurl() {
		return linkurl;
	}

	/**
	 * @param linkurl
	 *            要设置的 链接url
	 */
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	/**
	 * @return 返回 脚本内容
	 */
	public String getJavacontent() {
		return javacontent;
	}

	/**
	 * @param javacontent
	 *            要设置的 脚本内容
	 */
	public void setJavacontent(String javacontent) {
		this.javacontent = javacontent;
	}

	public java.math.BigDecimal getValuemin() {
		return valuemin;
	}

	public void setValuemin(java.math.BigDecimal valuemin) {
		this.valuemin = valuemin;
	}

	public java.math.BigDecimal getValuemax() {
		return valuemax;
	}

	public void setValuemax(java.math.BigDecimal valuemax) {
		this.valuemax = valuemax;
	}

	/**
	 * @return 返回 颜色
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            要设置的 颜色
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return 返回 插件
	 */
	public Integer getTrpreportpluginid() {
		return trpreportpluginid;
	}

	/**
	 * @param trpreportpluginid
	 *            要设置的 插件
	 */
	public void setTrpreportpluginid(Integer trpreportpluginid) {
		this.trpreportpluginid = trpreportpluginid;
	}

	/**
	 * @return 返回 区域
	 */
	public Integer getReportarea() {
		return reportarea;
	}

	/**
	 * @param reportarea
	 *            要设置的 区域
	 */
	public void setReportarea(Integer reportarea) {
		this.reportarea = reportarea;
	}

	List processclasslist;

	public List getProcessclasslist() {
		return processclasslist;
	}

	public void setProcessclasslist(List processclasslist) {
		this.processclasslist = processclasslist;
	}

	public String getDispprocessor() {
		return dispprocessor;
	}

	public void setDispprocessor(String dispprocessor) {
		this.dispprocessor = dispprocessor;
	}

	public List getDispprocessoroptions() {
		return dispprocessoroptions;
	}

	public void setDispprocessoroptions(List dispprocessoroptions) {
		this.dispprocessoroptions = dispprocessoroptions;
	}

	public Integer getDimvalueflag() {
		return dimvalueflag;
	}

	public void setDimvalueflag(Integer dimvalueflag) {
		this.dimvalueflag = dimvalueflag;
	}

}
