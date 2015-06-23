package com.wxservice.framework.report.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wxservice.database.po.report.Trpreportdatasource;
import com.wxservice.database.po.report.Trpreportdtl;
import com.wxservice.database.po.report.Trpreportplugin;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.dao.jdbc.JdbcManager;
import com.wxservice.framework.report.base.Band;
import com.wxservice.framework.report.base.ReportPrint;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.report.core.convert.IConvert;
import com.wxservice.framework.report.datasource.IDatasource;
import com.wxservice.framework.report.exception.FillException;
import com.wxservice.framework.report.listen.IReportListen;
import com.wxservice.framework.report.plugin.IPlugin;
import com.wxservice.framework.report.util.ReportUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.util.WebUtil;

public class FillService {
	public Map rsMap = null;
	public Map plugs = null;
	public ReportRequest rr = null;
	public ReportPrint rp = null;
	IReportListen listen = null;
	// RenderTool renderTool = null;
	public IDao dao = null;
	public JdbcManager jdbcManager = null;

	public void release() {
		this.rr = null;
		this.rp = null;

	}

	public FillService(ReportRequest rr) {
		rsMap = new HashMap();
		rp = new ReportPrint();
		this.rr = rr;
		rr.setFillService(this);
		jdbcManager = rr.getDao().getJdbcManager();
		dao = rr.getDao();
		String listenname = rr.getRc().getReport().getListenclass();
		if (StringUtils.isNotBlank(listenname)) {
			listen = (IReportListen) WebUtil.getBean(listenname, rr
					.getContext().getRequest());
		}
		// defaultReportListen
		if (listen == null) {
			listen = (IReportListen) WebUtil.getBean("simpleListen", rr
					.getContext().getRequest());
		}
		// renderTool = new RenderTool(this);
	}

	public Object getDataSource(String key) throws Exception {
		Object ds = null;
		if ("".equals(key)) {
			ds = rsMap.get(ReportUtil.DATASOURCE_DEFAULT);
		} else {
			ds = rsMap.get(key);
		}
		return ds;
	}

	public ReportPrint fillReport() throws FillException {
		try {
			listen.beforeDatasource(rr);
			WhereProcessor whereprocessor = this.processDatasource();
			listen.afterDatasource(rr);
			listen.beforeHeader(rr);
			this.createBand(rp.getHeader(), rr.getRc().getHeader(), 0);
			listen.afterHeader(rr);
			int maxrow = this.getMaxRow(rp.getHeader()) + 1;

			listen.beforePageHeader(rr);
			this.doPageHeaderBand(rp.getPageHeader(), rr.getRc().getDetail(),
					maxrow);
			listen.afterPageHeader(rr);

			maxrow = this.getMaxRow(rp.getPageHeader()) + 1;
			listen.beforePageDetail(rr);
			this.doDetailBand(rp.getDetail(), rr.getRc().getDetail(), maxrow,
					whereprocessor);
			listen.afterPageDetail(rr);

			maxrow = this.getFooterMaxRow(rp.getDetail(), rp.getPageHeader()) + 1;
			listen.beforePageFooter(rr);
			this.doFooterBand(rp.getFooter(), rr.getRc().getFooter(), maxrow,
					whereprocessor);
			listen.afterPageFooter(rr);
		} catch (Exception e) {
			rr.getErrormap().put("sqlerror", e);
			SystemLogger.error("填充报表发生错误", e);
		}

		return rp;
	}

	/**
	 * // 0:sql // 1:hibernate // 2:xml // 3:txt
	 * 
	 * @throws Exception
	 */
	private WhereProcessor processDatasource() throws Exception {
		WhereProcessor whereProcessor = new WhereProcessor(rr);
		for (Object o : rr.getRc().getDatasource()) {
			Trpreportdatasource ds = (Trpreportdatasource) o;
			Object rs = null;
			boolean isGoQuery = listen.beforeQuery(rr, ds, whereProcessor, rs);
			if (!isGoQuery) {// 如果返回的false，表示不须要再进行查询了。
				continue;
			}
			if (StringUtils.isBlank(ds.getContent())) {
				continue;
			}
			if (ReportUtil.datasoucetype_proc == ds.getDatasourcetype()) {// 存储过程
				String sql = whereProcessor.processWhere(ds.getContent());
				SystemLogger.debug(sql);
				long begin = System.currentTimeMillis();
				rs = jdbcManager.queryForProcdure(sql);
				long end = System.currentTimeMillis();
				StringBuffer msg = new StringBuffer();
				msg.append("存储过程的执行时间:");
				msg.append(end - begin);
				SystemLogger.info(msg.toString());

			} else if (ReportUtil.datasoucetype_java == ds.getDatasourcetype()) {// java对象
				if (ds.getName().equals(ds.getContent())) {
					IDatasource query = (IDatasource) WebUtil.getBean(ds
							.getName());
					query.query(rr, ds, whereProcessor, rsMap);
				}
			} else if (ReportUtil.datasoucetype_xml == ds.getDatasourcetype()) {// 存储过程

			} else if (ReportUtil.datasoucetype_txt == ds.getDatasourcetype()) {// 存储过程
				List rss = new ArrayList();
				List fieldList = StringUtil.string2List(ds.getContent(), ";");
				if (fieldList != null && fieldList.size() > 1) {
					String title = (String) fieldList.get(0);
					List titleList = StringUtil.string2List(title, ",");
					for (int i = 1; i < fieldList.size(); i++) {
						String record = (String) fieldList.get(i);
						List recordList = StringUtil.string2List(record, ",");
						Map row = new HashMap();
						for (int j = 0; j < recordList.size(); j++) {
							String value = (String) recordList.get(j);
							String key = (String) titleList.get(j);
							row.put(key, value);
						}
						rss.add(row);
					}
					rs = rss;
				}
			} else {
				String sql = whereProcessor.processWhere(ds.getContent());
				SystemLogger.debug(sql);
				long begin = System.currentTimeMillis();
				rs = jdbcManager.queryForList(sql);
				long end = System.currentTimeMillis();
				StringBuffer msg = new StringBuffer();
				msg.append("sql执行时间:");
				msg.append(end - begin);
				SystemLogger.info(msg.toString());
			}

			Object rs1 = listen.afterQuery(rr, ds, rs);
			if (rs != null) {
				rsMap.put(ds.getName().toLowerCase(), rs1);
			}
		}
		ReportUtil.setDatasource(rr, this.rsMap);
		return whereProcessor;

	}

	// 0:常量
	// 1:字段
	// 2:变量
	// 3:插件
	// 4:字段变量
	private void createBand(Band newband, List children, int row)
			throws Exception {
		if (children != null && children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				Trpreportdtl el = (Trpreportdtl) children.get(i);
				Object o = ReportUtil.getFieldClassname(el, rr);
				DataElement p = new DataElement();

				if (o instanceof IConvert) {
					IConvert convert = (IConvert) o;
					convert.convert(new HashMap(), rr, el, p, row);
					p.setRow(el.getRowindex());
					p.setCol(el.getColindex());
					p.setMergeWidths(el.getWidth());
					newband.addChild(p);
				}
				if (o instanceof IPlugin) {

				}
			}
		}

	}

	private String getPluginField(Trpreportdtl el) {
		Trpreportplugin plugin = ReportUtil.getReportPlugin(rr, el
				.getTrpreportpluginid());
		String prefx = plugin.getFieldalias();
		StringBuffer key = new StringBuffer();
		key.append(prefx);
		key.append("_html");
		return key.toString();
	}

	// pageHeader
	private void doPageHeaderBand(Band newband, List children, int row)
			throws Exception {
		int plugRow = 0;
		int plugCol = 0;

		if (children != null && children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				Trpreportdtl el = (Trpreportdtl) children.get(i);
				DataElement p = new DataElement();
				Object o = ReportUtil.getFieldClassname(el, rr);
				if (o instanceof IConvert) {
					IConvert convert = (IConvert) o;
					convert.convert(new HashMap(), rr, el, p, row);
					p.setCol(p.getCol() + plugCol);
					p.setText(p.getTitle());
					if (plugRow > 1) { // 如果多列头的高度大于1,其它的列需要设置成一样的高度
						p.setMergeHeights(plugRow - 1);
					}
					if (el.getColindex() > 0) {
						newband.addChild(p);
					}
				} else if (o instanceof IPlugin) {
					IPlugin plugin = (IPlugin) o;

					plugRow = plugin.getRow(rr, el);
					if (plugRow > 1) { // 如果多列头的高度大于1,其它的列需要设置成一样的高度
						this.resetHeight(newband, plugRow - 1);
					}
					List datas = plugin.getTitleList(el.getColindex()
							.intValue()
							+ plugCol, row, rr, el);
					if (plugin.isTable()) {
						// 计算valuehtml 放入PluginData
						p.setCol(el.getColindex());
						p.setHeight(1);
						p.setRow(row);
						p.setAlign(el.getAlign());
						p.setWidth(el.getWidth());
						p.setField(el.getField());
						p.setTitle(el.getTitle());
						p.setField(getPluginField(el));
						String valuehtml = plugin.toHeaderHtml(null, p, rr, el);
						p.setTitle(valuehtml);
						p.setSortable(false);
						p.setWidthper(el.getWidth().toString() + "%");
						p.setXslField(false);
						p.setHtmlField(true);
						p.setPlugData(true);
						newband.addChild(p);
					}
					
					newband.getChildren().addAll(datas);
					plugCol = plugin.getCol(rr, el) + plugCol - 1;

				}
			}

		}

	}

	private void resetHeight(Band band, int height) {
		List bd = band.getChildren();
		if (bd != null && bd.size() > 0) {
			for (int j = 0; j < bd.size(); j++) {
				Object o = bd.get(j);
				DataElement p = (DataElement) o;
				if (!p.isPlugData()) {
					p.setMergeHeights(height);
				}
			}
		}

	}

	private void doFooterBand(Band newband, List children, int row,
			WhereProcessor whereprocessor) throws Exception {

		Object obj = getDataSource("sum");

		List list = (List) obj;

		int plugCol = 0;
		if (list != null && list.size() > 0) {
			for (int j = 0; j < list.size(); j++) {
				int rowid = j + 1;
				Map map = (Map) list.get(j);
				map.put("rowid", rowid);
				plugCol = 0;
				if (children != null && children.size() > 0) {
					for (int i = 0; i < children.size(); i++) {
						Trpreportdtl el = (Trpreportdtl) children.get(i);
						DataElement p = new DataElement();
						p.setRowid(rowid);
						Object o = ReportUtil.getFieldClassname(el, rr);
						if (o instanceof IConvert) {
							IConvert convert = (IConvert) o;
							convert.convert(map, rr, el, p, row + j);
							p.setCol(p.getCol() + plugCol);
							if (el.getColindex() > 0) {
								newband.addChild(p);
							}
						} else if (o instanceof IPlugin) {
							IPlugin plugin = (IPlugin) o;

							List datas = plugin.getValueList(el.getColindex()
									.intValue()
									+ plugCol, row + j, map, rr, el);
							if (plugin.isTable()) {
								// 计算valuehtml 放入PluginData
								p.setCol(el.getColindex());
								p.setHeight(1);
								p.setRow(row + j);
								p.setAlign(el.getAlign());
								p.setWidth(el.getWidth());
								p.setField(el.getField());
								p.setTitle(el.getTitle());
								String valuehtml = plugin.toValueHtml(map, p,
										rr, el);
								p.setField(getPluginField(el));
								p.setText(valuehtml);
								p.setXslField(false);
								p.setHtmlField(true);
								p.setPlugData(true);
								newband.addChild(p);
							}
				
							newband.getChildren().addAll(datas);
							plugCol = plugin.getCol(rr, el) + plugCol - 1;

						}
					}
				}

			}
		}

	}

	private void doDetailBand(Band newband, List children, int row,
			WhereProcessor whereprocessor) throws Exception {

		Object obj = getDataSource("main");

		List list = (List) obj;

		int plugCol = 0;
		if (list != null && list.size() > 0) {
			for (int j = 0; j < list.size(); j++) {
				Map map = (Map) list.get(j);
				listen.beforeRow(rr, whereprocessor, map);
				int rowid = j + 1;
				map.put("rowid", rowid);
				plugCol = 0;
				if (children != null && children.size() > 0) {
					for (int i = 0; i < children.size(); i++) {
						Trpreportdtl el = (Trpreportdtl) children.get(i);
						DataElement p = new DataElement();
						p.setRowid(rowid);
						Object o = ReportUtil.getFieldClassname(el, rr);
						if (o instanceof IConvert) {
							IConvert convert = (IConvert) o;
							// p.setRow(row+j);
							convert.convert(map, rr, el, p, row + j);
							p.setCol(p.getCol() + plugCol);
							if (el.getColindex() > 0) {
								newband.addChild(p);
							}
						} else if (o instanceof IPlugin) {
							IPlugin plugin = (IPlugin) o;
							List datas = plugin.getValueList(el.getColindex()
									.intValue()
									+ plugCol, row + j, map, rr, el);
							if (plugin.isTable()) {
								// 计算valuehtml 放入PluginData
								p.setRow(row + j);
								p.setCol(el.getColindex());
								p.setHeight(1);
								p.setAlign(el.getAlign());
								p.setWidth(el.getWidth());
								p.setField(el.getField());
								p.setTitle(el.getTitle());
								String valuehtml = plugin.toValueHtml(map, p,
										rr, el);
								p.setField(getPluginField(el));
								p.setText(valuehtml);
								p.setXslField(false);
								p.setHtmlField(true);
								p.setPlugData(true);
								newband.addChild(p);
							}
							
							newband.getChildren().addAll(datas);
							plugCol = plugin.getCol(rr, el) + plugCol - 1;

						}
					}
				}
				listen.afterRow(rr, whereprocessor, map);

			}
		}

	}

	public JdbcManager getJdbcManager() {
		return jdbcManager;
	}

	public void setJdbcManager(JdbcManager jdbcManager) {
		this.jdbcManager = jdbcManager;
	}

	public ReportRequest getRr() {
		return rr;
	}

	public void setRr(ReportRequest rr) {
		this.rr = rr;
	}

	public Map getPlugs() {
		return plugs;
	}

	public void setPlugs(Map plugs) {
		this.plugs = plugs;
	}

	public Map getRsMap() {
		return rsMap;
	}

	public void setRsMap(Map rsMap) {
		this.rsMap = rsMap;
	}

	public ReportPrint getRp() {
		return rp;
	}

	public void setRp(ReportPrint rp) {
		this.rp = rp;
	}

	private int getMaxCol(Band band) {
		int maxcol = 0;
		List bd = band.getChildren();
		if (bd != null && bd.size() > 0) {
			for (int j = 0; j < bd.size(); j++) {
				DataElement p = (DataElement) bd.get(j);
				if (p.getRow() > maxcol) {
					maxcol = p.getCol();
				}
			}
		}
		return maxcol;
	}

	private int getMaxRow(Band band) {
		int maxrow = 0;
		List bd = band.getChildren();
		if (bd != null && bd.size() > 0) {
			for (int j = 0; j < bd.size(); j++) {
				Object o = bd.get(j);
				if (o instanceof DataElement) {
					DataElement p = (DataElement) o;
					if (p.getRow() > maxrow) {
						maxrow = p.getRow();
					}
				}
			}
		}
		return maxrow;
	}

	private int getFooterMaxRow(Band band, Band pageHeader) {
		int maxrow = 0;
		List bd = band.getChildren();
		if (bd != null && bd.size() > 0) {
			for (int j = 0; j < bd.size(); j++) {
				Object o = bd.get(j);
				if (o instanceof DataElement) {
					DataElement p = (DataElement) o;
					if (p.getRow() > maxrow) {
						maxrow = p.getRow();
					}
				}
			}
		} else {
			return this.getMaxRow(pageHeader);
		}
		return maxrow;
	}
}
