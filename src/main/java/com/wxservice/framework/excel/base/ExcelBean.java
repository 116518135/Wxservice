package com.wxservice.framework.excel.base;

public class ExcelBean {
	int row;
	int col;
	int mergeheights=0;
	int mergewidths=0;
	int width=10;
	String text;
	int datatype;// 数据类型
	boolean isPlugdata = false;

	public boolean isPlugdata() {
		return isPlugdata;
	}

	public void setPlugdata(boolean isPlugdata) {
		this.isPlugdata = isPlugdata;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getMergeheights() {
		return mergeheights;
	}

	public void setMergeheights(int mergeheights) {
		this.mergeheights = mergeheights;
	}

	public int getMergewidths() {
		return mergewidths;
	}

	public void setMergewidths(int mergewidths) {
		this.mergewidths = mergewidths;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getDatatype() {
		return datatype;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
