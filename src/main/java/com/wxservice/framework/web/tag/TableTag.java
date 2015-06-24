package com.wxservice.framework.web.tag;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.struts.util.RequestUtils;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.JspPageContext;
import org.extremecomponents.table.core.RetrievalUtils;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.tag.TagUtils;
import org.extremecomponents.util.ExceptionUtils;

import com.wxservice.framework.util.SysFinal;
import com.wxservice.framework.web.form.BaseForm;

public class TableTag extends org.extremecomponents.table.tag.TableTag {
	public void addTableAttributes(TableModel model, Table table) {
		BaseForm form = (BaseForm) RetrievalUtils.retrieve(model.getContext(),
				SysFinal.STRUTS_FORM, TableConstants.REQUEST_SCOPE);
		List data = (List) form.getPage().getResult();
		if (data !=null && data.size() >0) {
			table.setRetrieveRowsCallback("com.wxservice.framework.web.tag.support.LimitCallback");
		}
		//table.setSortRowsCallback("com.wxservice.framework.web.tag.support.MySortCallback");
		Locale userLocale = RequestUtils.getUserLocale((HttpServletRequest)pageContext.getRequest(), null);
		String locale=userLocale.getLanguage();
		this.setLocale(locale);
		table.setLocale(locale);
	}
    public void setRowsDisplayed(String rowsDisplayed) {
    }

	long start = 0;
	
	 private String action;
	    private String autoIncludeParameters;
	    private String border;
	    private String bufferView;
	    private String cellpadding;
	    private String cellspacing;
	    private String filterable;
	    private String filterRowsCallback;
	    private String form;
	    private String imagePath;
	    private String interceptor;
	    private Object items;
	    private String locale;
	    private String method;
	    private String onInvokeAction;
	    private String retrieveRowsCallback;
	    private String rowsDisplayed;
	    private String scope;
	    private String showExports;
	    private String showPagination;
	    private String showStatusBar;
	    private String showTitle;
	    private String showTooltips;
	    private String sortRowsCallback;
	    private String sortable;
	    private String state;
	    private String stateAttr;
	    private String style;
	    private String styleClass;
	    private String tableId;
	    private String theme;
	    private String title;
	    private String var;
	    private String view;
	    private String width;

	    protected TableModel model;
	    private Iterator iterator;

	    public TableModel getModel() {
	        return model;
	    }

	    /**
	     * @jsp.attribute description="The URI that will be called
	     *                when the filter, sort and pagination is used."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setAction(String action) {
	        this.action = action;
	    }

	    /**
	     * @jsp.attribute description="Specify whether or not to automatically
	     *                include the parameters, as hidden inputs, passed into the JSP." 
	     *                required="false" rtexprvalue="true"
	     */
	    public void setAutoIncludeParameters(String autoIncludeParameters) {
	        this.autoIncludeParameters = autoIncludeParameters;
	    }

	    /**
	     * @jsp.attribute description="The table border attribute. The default is 0." 
	     *                required="false" rtexprvalue="true"
	     */
	    public void setBorder(String border) {
	        this.border = border;
	    }

	    /**
	     * @jsp.attribute description="Whether of not to buffer the view. Boolean value with the default being false." 
	     *                required="false" rtexprvalue="true"
	     */
	    public void setBufferView(String bufferView) {
	        this.bufferView = bufferView;
	    }
	    
	    /**
	     * @jsp.attribute description="The table cellpadding attribute. The default is 0."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setCellpadding(String cellpadding) {
	        this.cellpadding = cellpadding;
	    }

	    /**
	     * @jsp.attribute description="The table cellspacing attribute. The default is 0."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setCellspacing(String cellspacing) {
	        this.cellspacing = cellspacing;
	    }

	    /**
	     * @jsp.attribute description="Specify whether or not the table is
	     *                filterable. Boolean value with the default being true."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setFilterable(String filterable) {
	        this.filterable = filterable;
	    }

	    /**
	     * @jsp.attribute description="A fully qualified class name to a custom
	     *                FilterRowsCallback implementation. Could also be a named type
	     *                in the preferences. Used to
	     *                filter the Collection of Beans or Collection of Maps." 
	     *                required="false" rtexprvalue="true"
	     */
	    public void setFilterRowsCallback(String filterRowsCallback) {
	        this.filterRowsCallback = filterRowsCallback;
	    }

	    /**
	     * @jsp.attribute description="The reference to a surrounding form element."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setForm(String form) {
	        this.form = form;
	    }

	    /**
	     * @jsp.attribute description="The path to find the images. For example 
	     *                imagePath=/extremesite/images/*.png is saying look in 
	     *                the image directory for the .png images."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setImagePath(String imagePath) {
	        this.imagePath = imagePath;
	    }

	    /**
	     * @jsp.attribute description="A fully qualified class name to a custom
	     *                InterceptTable implementation. Could also be a named type
	     *                in the preferences. Used to add table attributes." 
	     *                required="false" rtexprvalue="true"
	     */
	    public void setInterceptor(String interceptor) {
	        this.interceptor = interceptor;
	    }

	    /**
	     * @jsp.attribute description="Reference the collection that will be retrieved."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setItems(Object items) {
	        this.items = items;
	    }

	    /**
	     * @jsp.attribute description="The locale for this table. 
	     *                  For example fr_FR is used for the French translation."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setLocale(String locale) {
	        this.locale = locale;
	    }

	    /**
	     * @jsp.attribute description="Used to invoke the table action using a POST or GET." 
	     *                  required="false" rtexprvalue="true"
	     */
	    public void setMethod(String method) {
	        this.method = method;
	    }

	    /**
	     * @jsp.attribute description="The javascript that will be invoked when a table action enabled."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setOnInvokeAction(String onInvokeAction) {
	        this.onInvokeAction = onInvokeAction;
	    }

	    /**
	     * @jsp.attribute description="A fully qualified class name to a custom
	     *                RetrieveRowsCallback implementation. Could also be a named type
	     *                in the preferences. Used to retrieve 
	     *                the Collection of Beans or Collection of Maps." 
	     *                required="false" rtexprvalue="true"
	     */
	    public void setRetrieveRowsCallback(String retrieveRowsCallback) {
	        this.retrieveRowsCallback = retrieveRowsCallback;
	    }


	    /**
	     * @jsp.attribute description="The scope (page, request, session, or
	     *                application) to find the Collection of beans or Collection of Maps
	     *                defined by the collection attribute." 
	     *                required="false" rtexprvalue="true"
	     */
	    public void setScope(String scope) {
	        this.scope = scope;
	    }

	    /**
	     * @jsp.attribute description="Specify whether or not the table should use
	     *                pagination. Boolean value with the default being true."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setShowPagination(String showPagination) {
	        this.showPagination = showPagination;
	    }

	    /**
	     * @jsp.attribute description="Specify whether or not the table should use
	     *                the exports. Boolean value with the default being true."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setShowExports(String showExports) {
	        this.showExports = showExports;
	    }

	    /**
	     * @jsp.attribute description="Specify whether or not the table should use
	     *                the status bar. Boolean value with the default being true."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setShowStatusBar(String showStatusBar) {
	        this.showStatusBar = showStatusBar;
	    }

	    /**
	     * @jsp.attribute description="Specify whether or not to show the title. 
	     *                Boolean value with the default being true."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setShowTitle(String showTitle) {
	        this.showTitle = showTitle;
	    }
	    
	    /**
	     * @jsp.attribute description="Specify whether or not to show the tooltips. 
	     *                Boolean value with the default being true."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setShowTooltips(String showTooltips) {
	        this.showTooltips = showTooltips;
	    }

	    /**
	     * @jsp.attribute description="A fully qualified class name to a custom
	     *                SortRowsCallback implementation. Could also be a named type
	     *                in the preferences. Used to sort the Collection 
	     *                of Beans or Collection of Maps." 
	     *                required="false" rtexprvalue="true"
	     */
	    public void setSortRowsCallback(String sortRowsCallback) {
	        this.sortRowsCallback = sortRowsCallback;
	    }

	    /**
	     * @jsp.attribute description="Specify whether or not the table is sortable.
	     *                Boolean value with the default being true." 
	     *                required="false" rtexprvalue="true"
	     */
	    public void setSortable(String sortable) {
	        this.sortable = sortable;
	    }

	    /**
	     * @jsp.attribute description="The table state to use when returning to a table. 
	     *                Acceptable values are default, notifyToDefault, persist, notifyToPersist."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setState(String state) {
	        this.state = state;
	    }

	    /**
	     * @jsp.attribute description="The table attribute used to invoke the state change of the table." 
	     *                required="false" rtexprvalue="true"
	     */
	    public void setStateAttr(String stateAttr) {
	        this.stateAttr = stateAttr;
	    }

	    /**
	     * @jsp.attribute description="The css inline style sheet." required="false"
	     *                rtexprvalue="true"
	     */
	    public void setStyle(String style) {
	        this.style = style;
	    }

	    /**
	     * @jsp.attribute description="The css class style sheet." required="false"
	     *                rtexprvalue="true"
	     */
	    public void setStyleClass(String styleClass) {
	        this.styleClass = styleClass;
	    }

	    /**
	     * @jsp.attribute description="The unique identifier for the table."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setTableId(String tableId) {
	        this.tableId = tableId;
	    }

	    /**
	     * @jsp.attribute description="The theme to style the table. The default is eXtremeTable."
	     *                required="false" rtexprvalue="true"
	     */
	    public void setTheme(String theme) {
	        this.theme = theme;
	    }

	    /**
	     * @jsp.attribute description="The title of the table. The title will
	     *                display above the table." required="false"
	     *                rtexprvalue="true"
	     */
	    public void setTitle(String title) {
	        this.title = title;
	    }

	    /**
	     * @jsp.attribute description="The name of the variable to hold the current
	     *                row bean." required="false" rtexprvalue="true"
	     */
	    public void setVar(String var) {
	        this.var = var;
	    }

	    /**
	     * @jsp.attribute description="Generates the output. The default is the
	     *                HtmlView to generate the HTML. Also used by the exports to
	     *                generate XLS-FO, POI, and CSV." required="false"
	     *                rtexprvalue="true"
	     */
	    public void setView(String view) {
	        this.view = view;
	    }

	    /**
	     * @jsp.attribute description="Width of the table." required="false"
	     *                rtexprvalue="true"
	     */
	    public void setWidth(String width) {
	        this.width = width;
	    }

	    public int doStartTag() throws JspException {
	        try {
	            // initialize the attributes
	            iterator = null;
	            pageContext.setAttribute(TableConstants.ROWCOUNT, "0");

	            // fire up the model with the context, preferences and messages
	            model = new PageTableModelImpl(new JspPageContext(pageContext), TagUtils.evaluateExpressionAsString("locale", this.locale, this, pageContext));
	            // make the table
	            Table table = new Table(model);
	            table.setAction(TagUtils.evaluateExpressionAsString("action", action, this, pageContext));
	            table.setAutoIncludeParameters(TagUtils.evaluateExpressionAsBoolean("autoIncludeParameters", this.autoIncludeParameters, this, pageContext));
	            table.setBorder(TagUtils.evaluateExpressionAsString("border", this.border, this, pageContext));
	            table.setBufferView(TagUtils.evaluateExpressionAsBoolean("bufferView", this.bufferView, this, pageContext));
	            table.setCellpadding(TagUtils.evaluateExpressionAsString("cellpadding", this.cellpadding, this, pageContext));
	            table.setCellspacing(TagUtils.evaluateExpressionAsString("cellspacing", this.cellspacing, this, pageContext));
	            table.setFilterable(TagUtils.evaluateExpressionAsBoolean("filterable", this.filterable, this, pageContext));
	            table.setFilterRowsCallback(TagUtils.evaluateExpressionAsString("filterRowsCallback", this.filterRowsCallback, this, pageContext));
	            table.setForm(TagUtils.evaluateExpressionAsString("form", this.form, this, pageContext));
	            table.setImagePath(TagUtils.evaluateExpressionAsString("imagePath", this.imagePath, this, pageContext));
	            table.setInterceptor(TagUtils.evaluateExpressionAsString("interceptor", this.interceptor, this, pageContext));
	            table.setItems(TagUtils.evaluateExpressionAsObject("items", this.items, this, pageContext));
	            table.setLocale(TagUtils.evaluateExpressionAsString("locale", this.locale, this, pageContext));
	            table.setMethod(TagUtils.evaluateExpressionAsString("method", this.method, this, pageContext));
	            table.setOnInvokeAction(TagUtils.evaluateExpressionAsString("onInvokeAction", this.onInvokeAction, this, pageContext));
	            table.setRetrieveRowsCallback(TagUtils.evaluateExpressionAsString("retrieveRowsCallback", this.retrieveRowsCallback, this, pageContext));
	            table.setRowsDisplayed(TagUtils.evaluateExpressionAsInt("rowsDisplayed", this.rowsDisplayed, this, pageContext));
	            table.setScope(TagUtils.evaluateExpressionAsString("scope", scope, this, pageContext));
	            table.setShowExports(TagUtils.evaluateExpressionAsBoolean("showExports", this.showExports, this, pageContext));
	            table.setShowPagination(TagUtils.evaluateExpressionAsBoolean("showPagination", this.showPagination, this, pageContext));
	            table.setShowStatusBar(TagUtils.evaluateExpressionAsBoolean("showStatusBar", this.showStatusBar, this, pageContext));
	            table.setShowTitle(TagUtils.evaluateExpressionAsBoolean("showTitle", this.showTitle, this, pageContext));
	            table.setShowTooltips(TagUtils.evaluateExpressionAsBoolean("showTooltips", this.showTooltips, this, pageContext));
	            table.setSortRowsCallback(TagUtils.evaluateExpressionAsString("sortRowsCallback", this.sortRowsCallback, this, pageContext));
	            table.setSortable(TagUtils.evaluateExpressionAsBoolean("sortable", this.sortable, this, pageContext));
	            table.setState(TagUtils.evaluateExpressionAsString("state", this.state, this, pageContext));
	            table.setStateAttr(TagUtils.evaluateExpressionAsString("stateAttr", this.stateAttr, this, pageContext));
	            table.setStyle(TagUtils.evaluateExpressionAsString("style", style, this, pageContext));
	            table.setStyleClass(TagUtils.evaluateExpressionAsString("styleClass", this.styleClass, this, pageContext));
	            table.setTableId(TagUtils.evaluateExpressionAsString("tableId", tableId, this, pageContext));
	            table.setTheme(TagUtils.evaluateExpressionAsString("theme", this.theme, this, pageContext));
	            table.setTitle(TagUtils.evaluateExpressionAsString("title", this.title, this, pageContext));
	            table.setVar(TagUtils.evaluateExpressionAsString("var", this.var, this, pageContext));
	            table.setView(TagUtils.evaluateExpressionAsString("view", this.view, this, pageContext));
	            table.setWidth(TagUtils.evaluateExpressionAsString("width", this.width, this, pageContext));

	            addTableAttributes(model, table);
	            model.addTable(table);
	        } catch (Exception e) {
	            throw new JspException("TableTag.doStartTag() Problem: " + ExceptionUtils.formatStackTrace(e));
	        }

	        return EVAL_BODY_INCLUDE;
	    }

	    /**
	     * Two things need to be accomplished here. First, need to iterate once over
	     * the columns to load up all the attributes. Second, need to iterate over
	     * the columns as many times as specified by the rowsDisplayed attribute so
	     * the columns row can be resolved. On each iteration over the columns the
	     * current bean in the collection is passed via the pageScope.
	     */
	    public int doAfterBody() throws JspException {
	        try {
	            if (iterator == null) {
	                iterator = model.execute().iterator();
	            }

	            if (iterator != null && iterator.hasNext()) {
	                Object bean = iterator.next();
	                model.setCurrentRowBean(bean);
	                return EVAL_BODY_AGAIN;
	            }
	        } catch (Exception e) {
	            throw new JspException("TableTag.doAfterBody() Problem: " + ExceptionUtils.formatStackTrace(e));
	        }

	        return SKIP_BODY;
	    }

	    public int doEndTag() throws JspException {
	        try {
	            pageContext.getOut().println(model.getViewData());
	        } catch (Exception e) {
	            throw new JspException("TableTag.doEndTag() Problem: " + ExceptionUtils.formatStackTrace(e));
	        }

	        return EVAL_PAGE;
	    }


	    public void doCatch(Throwable e) throws Throwable {
	        throw new JspException("TableTag Problem: " + ExceptionUtils.formatStackTrace(e));
	    }

	    public void doFinally() {
	        iterator = null;
	        model = null;
	    }

	    public void release() {
	        action = null;
	        autoIncludeParameters = null;
	        border = null;
	        cellpadding = null;
	        cellspacing = null;
	        filterable = null;
	        filterRowsCallback = null;
	        form = null;
	        imagePath = null;
	        interceptor = null;
	        items = null;
	        locale = null;
	        method = null;
	        onInvokeAction = null;
	        retrieveRowsCallback = null;
	        rowsDisplayed = null;
	        scope = null;
	        showExports = null;
	        showPagination = null;
	        showStatusBar = null;
	        sortRowsCallback = null;
	        sortable = null;
	        state = null;
	        stateAttr = null;
	        style = null;
	        styleClass = null;
	        tableId = null;
	        title = null;
	        var = null;
	        view = null;
	        width = null;
	        super.release();
	    }
}