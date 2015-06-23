package com.wxservice.framework.web.tag.support;

import java.util.Collection;

import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.callback.FilterRowsCallback;
import org.extremecomponents.table.callback.RetrieveRowsCallback;
import org.extremecomponents.table.callback.SortRowsCallback;
import org.extremecomponents.table.core.RetrievalUtils;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;

import com.wxservice.framework.web.form.BaseForm;

public final class LimitCallback implements RetrieveRowsCallback,
		FilterRowsCallback, SortRowsCallback {
	public final static String TOTAL_ROWS = "totalRows";

	public Collection retrieveRows(TableModel model) throws Exception {

		Table table = model.getTableHandler().getTable();

		Collection rows = RetrievalUtils.retrieveCollection(model.getContext(),
				table.getItems(), table.getScope());
		BaseForm form = (BaseForm) RetrievalUtils.retrieve(model.getContext(),
				"struts_form", TableConstants.REQUEST_SCOPE);
		int t = (int) form.getPage().getTotalCount();
		Integer totalRows = t;
		if (totalRows == null) {
			totalRows = (Integer) RetrievalUtils.retrieve(model.getContext(),
					model.getTableHandler().prefixWithTableId()
							+ TableConstants.TOTAL_ROWS);
		}
		// if (table.isShowPagination()) {
		model.getTableHandler().setTotalRows((Integer) totalRows);
		// } else {
		// model.getTableHandler().setTotalRows(rows.size());
		// }
		return rows;
	}

	public Collection filterRows(TableModel model, Collection rows)
			throws Exception {
		return rows;
	}

	public Collection sortRows(TableModel model, Collection rows)
			throws Exception {
		return rows;
	}
}
