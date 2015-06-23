package com.wxservice.framework.report.core.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.callback.FilterRowsCallback;
import org.extremecomponents.table.callback.RetrieveRowsCallback;
import org.extremecomponents.table.callback.SortRowsCallback;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.Messages;
import org.extremecomponents.table.core.Preferences;
import org.extremecomponents.table.core.Registry;
import org.extremecomponents.table.core.TableCache;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableModelUtils;
import org.extremecomponents.table.core.TableProperties;
import org.extremecomponents.table.core.TableRegistry;
import org.extremecomponents.table.handler.ColumnHandler;
import org.extremecomponents.table.handler.ExportHandler;
import org.extremecomponents.table.handler.RowHandler;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.handler.ViewHandler;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.ModelLimitFactory;
import org.extremecomponents.table.limit.TableLimit;

/**
 * @author Jeff Johnston
 */
public final class MyTableModelImpl implements TableModel {
	private static Log logger = LogFactory.getLog(TableModel.class);

	// model interfaces model接口
	private Context context; // 包装了pageContext.目的是取值方便

	private Preferences preferences; // 一些引用

	private Messages messages; // 信息

	private Registry registry; // 注册器

	// model handlers model处理
	private TableHandler tableHandler = new TableHandler(this);

	private RowHandler rowHandler = new RowHandler(this);

	private ColumnHandler columnHandler = new ColumnHandler(this);

	private ViewHandler viewHandler = new ViewHandler(this);

	private ExportHandler exportHandler = new ExportHandler(this);

	// model objects model 对象
	private Object currentRowBean;

	private Collection collectionOfBeans;

	private Collection collectionOfFilteredBeans;

	private Collection collectionOfPageBeans;

	private Limit limit;

	private Locale locale;

	private List footerList = null;

	public MyTableModelImpl(Context context, List footerList) {
		this.footerList = footerList;
		this.context = context;

		Preferences preferences = new TableProperties();
		preferences.init(context, TableModelUtils
				.getPreferencesLocation(context));
		this.preferences = preferences;

		this.locale = Locale.CHINA;
		// 国际化的初始化
		Messages messages = TableModelUtils.getMessages(this);
		messages.init(context, this.locale);
		this.messages = messages;

	}

	public Context getContext() {
		return context;
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public Messages getMessages() {
		return messages;
	}

	public Registry getRegistry() {
		return registry;
	}

	public Table getTableInstance() {
		return new Table(this);
	}

	public Export getExportInstance() {
		return new Export(this);
	}

	public Row getRowInstance() {
		return new Row(this);
	}

	public Column getColumnInstance() {
		return new Column(this);
	}

	public void addTable(Table table) {
		// 加入一个table对象
		tableHandler.addTable(table);

		// now set the registry 现在开始注册
		this.registry = new TableRegistry(this);

		// then set the limit 设置限制
		LimitFactory limitFactory = new ModelLimitFactory(this);
		this.limit = new TableLimit(limitFactory);
	}

	public void addExport(Export export) {
		exportHandler.addExport(export);
	}

	public void addRow(Row row) {
		rowHandler.addRow(row);
	}

	public void addColumn(Column column) {
		columnHandler.addAutoGenerateColumn(column);
	}

	public void addColumns(String autoGenerateColumns) {
		autoGenerateColumns = TableModelUtils.getAutoGenerateColumnsPreference(
				this, autoGenerateColumns);
		TableCache.getInstance().getAutoGenerateColumns(autoGenerateColumns)
				.addColumns(this);
	}

	/**
	 * The parameter value can be null, String, String[], or a List. The
	 * parameter value will be converted to a String[] internally.
	 * 
	 * @param name
	 *            The parameter name
	 * @param value
	 *            The parameter value
	 */
	public void addParameter(String name, Object value) {
		registry.addParameter(name, value);
	}

	public TableHandler getTableHandler() {
		return tableHandler;
	}

	public RowHandler getRowHandler() {
		return rowHandler;
	}

	public ColumnHandler getColumnHandler() {
		return columnHandler;
	}

	public ViewHandler getViewHandler() {
		return viewHandler;
	}

	public ExportHandler getExportHandler() {
		return exportHandler;
	}

	public Object getCurrentRowBean() {
		return currentRowBean;
	}

	public void setCurrentRowBean(Object bean) {
		int rowcount = rowHandler.increaseRowCount();
		this.currentRowBean = bean;
		context.setPageAttribute(TableConstants.ROWCOUNT, String
				.valueOf(rowcount));
		context.setPageAttribute(tableHandler.getTable().getVar(), bean);
	}

	public Collection getCollectionOfBeans() {
		return collectionOfBeans;
	}

	public void setCollectionOfBeans(Collection collectionOfBeans) {
		this.collectionOfBeans = collectionOfBeans;
	}

	public Collection getCollectionOfFilteredBeans() {
		return collectionOfFilteredBeans;
	}

	public void setCollectionOfFilteredBeans(
			Collection collectionOfFilteredBeans) {
		this.collectionOfFilteredBeans = collectionOfFilteredBeans;
	}

	public Collection getCollectionOfPageBeans() {
		return collectionOfPageBeans;
	}

	public void setCollectionOfPageBeans(Collection collectionOfPageBeans) {
		this.collectionOfPageBeans = collectionOfPageBeans;
	}

	public Limit getLimit() {
		return limit;
	}

	public void setLimit(Limit limit) {
		this.limit = limit;
	}

	public Locale getLocale() {
		return locale;
	}

	public Collection retrieveRows(TableModel model) throws Exception {
		RetrieveRowsCallback retrieveRowsCallback = TableCache.getInstance()
				.getRetrieveRowsCallback(model);

		return retrieveRowsCallback.retrieveRows(model);
	}

	public Collection filterRows(TableModel model, Collection rows)
			throws Exception {
		FilterRowsCallback filterRowsCallback = TableCache.getInstance()
				.getFilterRowsCallback(model);

		return filterRowsCallback.filterRows(model, rows);
	}

	public Collection sortRows(TableModel model, Collection rows)
			throws Exception {
		SortRowsCallback sortRowsCallback = TableCache.getInstance()
				.getSortRowsCallback(model);

		return sortRowsCallback.sortRows(model, rows);
	}

	public Collection getCurrentRows(TableModel model, Collection rows) {
		Limit limit = model.getLimit();

		int rowStart = limit.getRowStart();
		int rowEnd = limit.getRowEnd();

		// Normal case. Using Limit and paginating for a specific set of rows.
		if (rowStart >= rows.size()) {
			if (logger.isDebugEnabled()) {
				logger
						.debug("The Limit row start is >= items.size(). Return the items available.");
			}

			return rows;
		}

		if (rowEnd > rows.size()) {
			if (logger.isWarnEnabled()) {
				logger
						.warn("The Limit row end is > items.size(). Return as many items as possible.");
			}

			rowEnd = rows.size();
		}

		Collection results = new ArrayList();
		if (rows instanceof List) {
			List tempRows = (List) rows;
			for (int i = rowStart; i < rowEnd; i++) {

				Object bean = tempRows.get(i);
				results.add(bean);
			}
		}

		// 再加入求和部分
		if (footerList != null && footerList.size() > 0) {
			for (Object bean : footerList) {
				results.add(bean);
			}
		}

		return results;
	}

	public Collection execute() throws Exception {
		// 取到当前所有的集合
		Collection rows = retrieveRows(this);

		rows = new ArrayList(rows); // copy for thread safety

		this.collectionOfBeans = rows;

		rows = filterRows(this, rows);// 对rows进行过滤

		rows = sortRows(this, rows); // 对页面所选的内容进行排序

		this.collectionOfFilteredBeans = rows;

		Integer totalRows = getTableHandler().getTotalRows();
		int defaultRowsDisplayed = getTableHandler().getTable()
				.getRowsDisplayed();
		if (totalRows != null) {
			limit.setRowAttributes(totalRows.intValue(), defaultRowsDisplayed);
		} else {
			limit.setRowAttributes(rows.size(), defaultRowsDisplayed);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(limit.toString());
		}

		rows = getCurrentRows(this, rows);

		this.collectionOfPageBeans = rows;
		// 画一些table的开始部份
		viewHandler.setView();

		return rows;
	}

	public void setColumnValues() throws Exception {
		List columns = columnHandler.getColumns();
		Iterator iter = columns.iterator();
		while (iter.hasNext()) {
			Column column = (Column) iter.next();
			if ("true".equals(column
					.getAttribute(TableConstants.IS_AUTO_GENERATE_COLUMN))) {
				String property = column.getProperty();
				Object propertyValue = TableModelUtils.getColumnPropertyValue(
						currentRowBean, property);
				column.setValue(propertyValue);
				column.setPropertyValue(propertyValue);
				columnHandler.modifyColumnAttributes(column);
				viewHandler.addColumnValueToView(column);
			}
		}
	}

	public Object getViewData() throws Exception {
		Object viewData = viewHandler.getView().afterBody(this);

		if (limit.isExported()) {
			context.setRequestAttribute(TableConstants.VIEW_DATA, viewData);
			context.setRequestAttribute(TableConstants.VIEW_RESOLVER,
					exportHandler.getCurrentExport().getViewResolver());
			context.setRequestAttribute(TableConstants.EXPORT_FILE_NAME,
					exportHandler.getCurrentExport().getFileName());
			return "";
		}

		return viewData;
	}

	/**
	 * Will execute the model and interate over the rows. Very useful for
	 * assembling a table using java code.
	 */
	public Object assemble() throws Exception {
		Iterator iterator = execute().iterator();
		for (Iterator iter = iterator; iter.hasNext();) {
			Object bean = iterator.next();
			setCurrentRowBean(bean);

			// call to modify attributes
			getRowHandler().modifyRowAttributes();

			// call columns to set values
			setColumnValues();
		}

		return getViewData();
	}
}
