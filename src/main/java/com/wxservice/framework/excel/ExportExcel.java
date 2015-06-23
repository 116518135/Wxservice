package com.wxservice.framework.excel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.wxservice.database.po.system.Tsysexcel;
import com.wxservice.database.po.system.Tsysexceldtl;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.excel.base.ExcelBean;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.service.NameManager;

public class ExportExcel extends SimpleExcelExecutor {
	public Object execute(ExcelContext context) throws Exception {
		Tsysexcel master = this.getMaster(context.getForm().getCode());
		List<Tsysexceldtl> dtllist = this.getDtllist(master.getTsysexcelid());
		context.setMaster(master);
		context.setDtllist(dtllist);
		ExcelBean bean = null;
		List<ExcelBean> headDatas = new ArrayList();
		bean = new ExcelBean();
		bean.setRow(1);
		bean.setCol(1);
		bean.setText("配置编号");
		bean.setDatatype(ExcelUtil.datatype_string);
		headDatas.add(bean);
		bean = new ExcelBean();
		bean.setRow(1);
		bean.setCol(2);
		bean.setText(master.getCode());
		bean.setDatatype(ExcelUtil.datatype_string);
		headDatas.add(bean);

		bean = new ExcelBean();
		bean.setRow(2);
		bean.setCol(1);
		bean.setText("配置名称");
		bean.setDatatype(ExcelUtil.datatype_string);
		headDatas.add(bean);
		bean = new ExcelBean();
		bean.setRow(2);
		bean.setCol(2);
		bean.setText(master.getName());
		bean.setDatatype(ExcelUtil.datatype_string);
		headDatas.add(bean);
		int plugRow = 0;
		int plugCol = 0;
		List<ExcelBean> pageHeadsDatas = new ArrayList();
		for (Tsysexceldtl dtl : dtllist) {
			if (dtl.getColindex() == 0)
				continue;
			IExcelField processor = (IExcelField) WebUtil.getBean(dtl
					.getFieldprocess(), context.getRequest());

			List list = processor.exportTitle(context, dtl, this.getBeingRow());
			pageHeadsDatas.addAll(list);
			for (int j = 0; j < list.size(); j++) {
				ExcelBean p = (ExcelBean) list.get(j);
				p.setCol(p.getCol() + plugCol);
				if (plugRow > 1) {
					p.setMergeheights(plugRow - 1);
				}
			}

		}
		// 打印，导出
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("zh", "CN"));
		WritableWorkbook workbook = Workbook.createWorkbook(bo, ws);
		WritableSheet s = workbook.createSheet(master.getName(), 0);
		for (int i = 0; i < headDatas.size(); i++) {
			ExcelBean el = (ExcelBean) headDatas.get(i);
			this.printPageHeaderCell(s, el, getGreyBackground());
		}
		for (int i = 0; i < pageHeadsDatas.size(); i++) {
			ExcelBean el = (ExcelBean) pageHeadsDatas.get(i);
			this.printPageHeaderCell(s, el, getGreyBackground());
		}
		workbook.write();
		workbook.close();
		return bo.toByteArray();

	}

	private void resetHeight(List<ExcelBean> pageHeadsDatas, int maxRow) {
		for (int j = 0; j < pageHeadsDatas.size(); j++) {
			ExcelBean p = pageHeadsDatas.get(j);
			if (!p.isPlugdata()) {
				p.setMergeheights(maxRow);
			}

		}
	}

	private void printPageHeaderCell(WritableSheet s, ExcelBean el,
			WritableCellFormat format) throws Exception {

		Label label = new Label(el.getCol() - 1, el.getRow() - 1, el.getText(),
				format);
		s.setColumnView(el.getCol() - 1, el.getWidth());
		merge(s, el);
		s.addCell(label);
	}

	private void merge(WritableSheet s, ExcelBean el) throws WriteException,
			RowsExceededException {
		if (el.getMergeheights() != 0 || el.getMergewidths() != 0) {
			int r = el.getMergeheights() + el.getRow() - 1; // 通过设置的width,height合并单元格，注意在title和detail中不能设置height.
			int c = el.getMergewidths() + el.getCol() - 1;
			s.mergeCells(el.getCol() - 1, el.getRow() - 1, c, r);
		}
	}

	private WritableCellFormat getGreyBackground() throws Exception {
		WritableCellFormat greyBackground = new WritableCellFormat(); // 灰色的格式化
		greyBackground.setWrap(true);
		greyBackground.setBackground(Colour.GRAY_25);
		greyBackground.setBorder(Border.ALL, BorderLineStyle.THIN);
		greyBackground.setAlignment(Alignment.CENTRE);
		return greyBackground;
	}

	public IDao getDao() {
		return dao;
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}

	public NameManager getNameManager() {
		return nameManager;
	}

	public void setNameManager(NameManager nameManager) {
		this.nameManager = nameManager;
	}

}
