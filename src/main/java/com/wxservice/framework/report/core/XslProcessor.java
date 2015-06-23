package com.wxservice.framework.report.core;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import com.wxservice.framework.report.base.ReportPrint;
import com.wxservice.framework.report.base.ReportRequest;
import com.wxservice.framework.util.MathUtil;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.ZipCompressorByAnt;
import com.wxservice.service.ServiceConfig;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



public class XslProcessor extends SimpleExport {
	public String isNumberCell = "true";
	public ServiceConfig serviceConfig = null;

	public String getIsNumberCell() {
		return isNumberCell;
	}

	public void setIsNumberCell(String isNumberCell) {
		this.isNumberCell = isNumberCell;
	}

	public XslProcessor() {

	}
	
	public int[] getRGB(String color){
		int[]rgb=new int[3];
		try{
		int pixel=Integer.parseInt(color.substring(1), 16);
		rgb[0]=(pixel&0xff0000)>>16;
		rgb[1]=(pixel&0xff00)>>8;
		rgb[2]=(pixel&0xff);
		return rgb;
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
		rgb[0]=0;
		rgb[1]=0;
		rgb[2]=0;
		return rgb;
	}

	public byte[] zip(ReportRequest ctx) throws FileNotFoundException,
			IOException {
		String dir = this.getExcelDir(ctx.getContext().getForm(), ctx
				.getContext().getRequest());
		File file = new File(dir);   
		StringBuffer downloadfile = new StringBuffer();
		if(file.listFiles().length==0){   
			return null;
		}
		if(file.listFiles().length>1){
			ctx.setRarFlag(1);
			StringBuffer rarname = new StringBuffer("_");
			rarname.append(ctx.getRc().getReport().getName());
			rarname.append(".rar");
			
			downloadfile.append(dir);
			downloadfile.append(rarname);
			ZipCompressorByAnt zca = new ZipCompressorByAnt(downloadfile.toString());
			zca.compress(dir);
        }else{
        	downloadfile.append(dir);
        	downloadfile.append(file.listFiles()[0].getName());
        }
		InputStream fis = new BufferedInputStream(new FileInputStream(
				downloadfile.toString()));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		this.deleteExcelDir(ctx.getContext().getForm(), ctx.getContext().getRequest());
		return buffer;
	}

	/**
	 * 打印页面的头
	 * 
	 * @param s
	 * @param el
	 * @param format
	 * @throws Exception
	 */
	private void printPageHeaderCell(WritableSheet s, DataElement el,
			WritableCellFormat format) throws Exception {

		Label label = new Label(el.getCol() - 1, el.getRow() - 1, el.getText(),
				format);
		// System.out.println(el.getText()+",row="+el.getRow()+",col="+el.getCol()+",mergeHeight="+el.getMergeHeights()+",mergeWidth="+el.getMergeWidths());
		s.setColumnView(el.getCol() - 1, el.getWidth());
		merge(s, el);
		s.addCell(label);
	}
	
	public int getMaxRow(){
		return serviceConfig.getIntConfig("ucard.report.excel.maxrow");
	}
	public int getMaxCol(){
		return serviceConfig.getIntConfig("ucard.report.excel.maxcol");
	}

	private void printPageHeaderCell(DataElement el, WritableCellFormat format,
			ReportRequest ctx) throws Exception {
		WritableWorkbook workbook=ctx.getWorkbook();
		int overRow = (el.getRow() - 1) / getMaxRow();// 得到是否超行
		int overCol = (el.getCol() - 1) /getMaxCol();// 得到是否超列
		int index =overCol;
		WritableSheet s = null;
		if (overRow >=ctx.getIndex()) {
			workbook.write();// 保存文件
			workbook.close();
			workbook = this.getWorkbook(ctx);
			ctx.setWorkbook(workbook);
			s = workbook.createSheet(String.valueOf(ctx.getIndex()-1), 0);
		} else {
			if(workbook.getSheets().length==overCol){
				s=workbook.createSheet(String.valueOf(index), index);
			}else{
				s=workbook.getSheet(index);
			}
		}
		

		int r = el.getRow();
		int c = el.getCol();
		if (overRow > 0) {
			r = el.getRow() -  (ctx.getIndex()-1)  * getMaxRow();
		}
		if (overCol > 0) {
			c = el.getCol() - index * getMaxCol();
		}
		el.setRow(r);
		el.setCol(c);

		Label label = new Label(el.getCol() - 1, el.getRow() - 1, el.getText(),
				format);
		s.setColumnView(el.getCol() - 1, el.getWidth());
		merge(s, el);
		s.addCell(label);
	}

	private void merge(WritableSheet s, DataElement el) throws WriteException,
			RowsExceededException {
		if (el.getMergeHeights() != 0 || el.getMergeWidths() != 0) {
			int r = el.getMergeHeights() + el.getRow() - 1; // 通过设置的width,height合并单元格，注意在title和detail中不能设置height.
			int c = el.getMergeWidths() + el.getCol() - 1;
			s.mergeCells(el.getCol() - 1, el.getRow() - 1, c, r);
		}
	}

	/**
	 * 打印cell
	 * 
	 * @param s
	 * @param el
	 * @param format
	 * @throws Exception
	 */
	private void printCell(WritableSheet s, DataElement el,
			WritableCellFormat format) throws Exception {
		Label label = new Label(el.getCol() - 1, el.getRow() - 1, el.getText(),
				format);
		merge(s, el);
		s.addCell(label);

		
	}

	/**
	 * 打印格式
	 * 
	 * @param el
	 * @return
	 * @throws Exception
	 */
	private WritableCellFormat newWritableCellFormat(DataElement el)
			throws Exception {
		WritableCellFormat fmt = null;
		/*
		 * if (1 == el.getDatatype()) {// 数字型 JxlNumberFormat pounddp2 = new
		 * JxlNumberFormat("G/通用格式;G/通用格式;;"); fmt = new
		 * WritableCellFormat(pounddp2); } else { fmt = new
		 * WritableCellFormat(); }
		 */
		fmt = new WritableCellFormat();
		if (2 == el.getAlign()) {
			fmt.setAlignment(Alignment.CENTRE); //
		} else if (3 == el.getAlign()) {
			fmt.setAlignment(Alignment.RIGHT); //
		} else {
			fmt.setAlignment(Alignment.LEFT); //
		}
		fmt.setVerticalAlignment(VerticalAlignment.CENTRE);
		fmt.setBorder(Border.ALL, BorderLineStyle.THIN);
		return fmt;
	}


	/**
	 * 打印cell
	 * 
	 * @param s
	 * @param el
	 * @throws Exception
	 */
	private void printCell(WritableSheet s, DataElement el) throws Exception {
		WritableCellFormat fmt = newWritableCellFormat(el);
		int col = el.getCol() - 1;
		WritableCell label = null;
		if (1 == el.getDatatype() && "true".equals(this.isNumberCell)) {// 数字型
			double val = MathUtil.parseDouble(el.getText(), 0);
			label = new Number(col, el.getRow() - 1, val, fmt);
		} else if (2 == el.getDatatype()) {
			double val = MathUtil.parseDouble(el.getText(), 0);
			label = new Number(col, el.getRow() - 1, val, fmt);
		} else if (3 == el.getDatatype()) {
			if(StringUtil.isNotBlank(el.getText())){
				double val = MathUtil.parseDouble(el.getText(), 0);
				label = new Number(col, el.getRow() - 1, val, fmt);
			}else{
				label = new Label(col, el.getRow() - 1, el.getText(), fmt);
			}
		} else {// 字符型
			label = new Label(col, el.getRow() - 1, el.getText(), fmt);
		}
		merge(s, el);
		s.addCell(label);
	}

	/**
	 * 得到title的格式
	 * 
	 * @return
	 * @throws Exception
	 */
	private WritableCellFormat getTitleFormat() throws Exception {
		WritableFont titleFont = new WritableFont(WritableFont.TAHOMA, 18,
				WritableFont.BOLD, true, UnderlineStyle.SINGLE, Colour.BLUE2);
		WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
		titleFormat.setAlignment(Alignment.CENTRE); // 列标题格式化
		return titleFormat;
	}

	/**
	 * 获得背景
	 * 
	 * @return
	 * @throws Exception
	 */
	private WritableCellFormat getGreyBackground() throws Exception {
		WritableCellFormat greyBackground = new WritableCellFormat(); // 灰色的格式化
		greyBackground.setWrap(true);
		greyBackground.setBackground(Colour.GRAY_25);
		greyBackground.setBorder(Border.ALL, BorderLineStyle.THIN);
		greyBackground.setAlignment(Alignment.CENTRE);
		return greyBackground;
	}

	/**
	 * 得到边界格式
	 * 
	 * @return
	 * @throws Exception
	 */
	private WritableCellFormat getNoBroderFormat() throws Exception {
		WritableCellFormat noBroderFormat = new WritableCellFormat();
		noBroderFormat.setAlignment(Alignment.CENTRE); //
		noBroderFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		noBroderFormat.setBorder(Border.NONE, BorderLineStyle.THIN);
		return noBroderFormat;
	}

	/**
	 * 打印header
	 * 
	 * @param s
	 * @param pr
	 * @throws Exception
	 */
	public void printHeader(WritableSheet s, ReportPrint pr) throws Exception {
		List children = pr.getHeader().getChildren();
		if (children != null && children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				DataElement el = (DataElement) children.get(i);
				if (el.getIsXslField()) {
					this.printCell(s, el, getNoBroderFormat());
				}
			}
		}
	}

	/**
	 * 打印pageheader
	 * 
	 * @param s
	 * @param pr
	 * @throws Exception
	 */
	public void printPageHeader(ReportPrint pr, ReportRequest ctx)
			throws Exception {
		WritableWorkbook workbook = ctx.getWorkbook();
		List children = pr.getPageHeader().getChildren();
		if (children != null && children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				DataElement el = (DataElement) children.get(i);
				if (el.getIsXslField()) {
					this.printPageHeaderCell(el, getGreyBackground(), ctx);
					// this.printPageHeaderCell(s, el, getGreyBackground());
				}
			}
		}
	}

	private int printCell(DataElement el, ReportRequest ctx) throws Exception {
		WritableWorkbook workbook = ctx.getWorkbook();
		int overRow = (el.getRow() - 1) / getMaxRow();// 得到是否超行
		int overCol = (el.getCol() - 1) / getMaxCol();// 得到是否超列
		int index =overCol;
		WritableSheet s = null;
		if (overRow >= ctx.getIndex()) {
			workbook.write();// 保存文件
			workbook.close();
			workbook = this.getWorkbook(ctx);
			ctx.setWorkbook(workbook);
			s = workbook.createSheet("报表数据", 0);
		} else {
			if(workbook.getSheets().length==overCol){
				s=workbook.createSheet(String.valueOf(index), index);
			}else{
				s=workbook.getSheet(index);
			}
		}
		int r = el.getRow();
		int c = el.getCol();
		if (overRow > 0) {
			r = el.getRow() - (ctx.getIndex()-1) *getMaxRow();
		}
		if (overCol > 0) {
			c = el.getCol() - index * getMaxCol();
		}
		el.setRow(r);
		el.setCol(c);

		WritableCellFormat fmt = newWritableCellFormat(el);
		//设置颜色

		int col = el.getCol() - 1;
		WritableCell label = null;
		if (1 == el.getDatatype() && "true".equals(this.isNumberCell)) {// 数字型
			double val = MathUtil.parseDouble(el.getText(), 0);
			label = new Number(col, el.getRow() - 1, val, fmt);
		} else if (2 == el.getDatatype()) {
			double val = MathUtil.parseDouble(el.getText(), 0);
			label = new Number(col, el.getRow() - 1, val, fmt);
		} else if (3 == el.getDatatype()) {
			if(StringUtil.isNotBlank(el.getText())){
				double val = MathUtil.parseDouble(el.getText(), 0);
				label = new Number(col, el.getRow() - 1, val, fmt);
			}else{
				label = new Label(col, el.getRow() - 1, el.getText(), fmt);
			}
		}else {// 字符型
			label = new Label(col, el.getRow() - 1, el.getText(), fmt);
		}
		merge(s, el);
		s.addCell(label);
		return index;
	}

	/**
	 * 打印明细
	 * 
	 * @param s
	 * @param pr
	 * @throws Exception
	 */
	public void printDetail(ReportPrint pr, ReportRequest ctx) throws Exception {
		WritableWorkbook workbook = ctx.getWorkbook();
		List children = pr.getDetail().getChildren();
		if (children != null && children.size() > 0) {
			int index = 0;
			for (int i = 0; i < children.size(); i++) {
				DataElement el = (DataElement) children.get(i);
				if (el.getIsXslField()) {
					this.printCell(el, ctx);
				}

			}
		}

	}

	public int getSheetIndex(int row, int col) {
		int overRow = row / getMaxRow();// 得到是否超行
		int overCol = col / getMaxCol();// 得到是否超列
		return overRow + overCol;

	}

	/**
	 * 打印footer
	 * 
	 * @param s
	 * @param pr
	 * @throws Exception
	 */
	public int printFooter(ReportPrint pr, ReportRequest ctx) throws Exception {
		WritableWorkbook workbook = ctx.getWorkbook();
		List children = pr.getFooter().getChildren();
		int index = 0;
		if (children != null && children.size() > 0) {
			for (int i = 0; i < children.size(); i++) {
				DataElement el = (DataElement) children.get(i);
				if (el.getIsXslField()) {

					index = this.printCell(el, ctx);
				}
			}
		}
		return index;
	}

	public WritableWorkbook getWorkbook(ReportRequest ctx) throws Exception {
		String path = this.getExcelName(ctx);
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("zh", "CN"));
		WritableWorkbook w = Workbook.createWorkbook(new File(path), ws);
		return w;
	}

	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	public void setServiceConfig(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}


}
