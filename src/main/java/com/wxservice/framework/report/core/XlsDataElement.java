package com.wxservice.framework.report.core;

import java.io.Serializable;

public class XlsDataElement implements Serializable{
    private int row;
    private int col;
    private String text;
    private int width=10;
    private int height=400;
    private int align;  //0: 左边 left  1:中间  center  2：右边 right
    private int datatype=0;//0:字符串型  1:多列头的数字型  2:其它的数字型
    private int celltype=0;//单元格格式
    private String field;
    private String oldfield;
    private String title;
    private boolean isPlugData=false;
    private String widthper="20%";
    
    private String color;
    
    private boolean isXslField=true;
    
    private boolean isHtmlField=true;
    
    boolean sortable=true;

    private int mergeWidths=0;//需要合并的列，主要体现在pageHeader,其它区暂时不作考虑.
    private int mergeHeights=0;//需要合并的行..
    
    private int rowid=0;
    private int dimvalueflag=0;
    
    private boolean top =false; //上边框实线
    private boolean bottom=false;
    private boolean left=false;
    private boolean right=false;
    private boolean NoCell=false;
    private boolean topCell=false;
    
	public boolean getTopCell() {
		return topCell;
	}

	public void setTopCell(boolean topCell) {
		this.topCell = topCell;
	}

	public boolean getNoCell() {
		return NoCell;
	}

	public void setNoCell(boolean noCell) {
		NoCell = noCell;
	}

	public boolean getTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public boolean getBottom() {
		return bottom;
	}

	public void setBottom(boolean bottom) {
		this.bottom = bottom;
	}

	public boolean getLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean getRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public int getDimvalueflag() {
		return dimvalueflag;
	}

	public void setDimvalueflag(int dimvalueflag) {
		this.dimvalueflag = dimvalueflag;
	}

	public int getRowid() {
		return rowid;
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}

	public int getMergeHeights() {
		return mergeHeights;
	}

	public void setMergeHeights(int mergeHeights) {
		this.mergeHeights = mergeHeights;
	}

	public int getCelltype() {
		return celltype;
	}

	public void setCelltype(int celltype) {
		this.celltype = celltype;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getDatatype() {
		return datatype;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMergeWidths() {
		return mergeWidths;
	}

	public void setMergeWidths(int mergeWidths) {
		this.mergeWidths = mergeWidths;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}



	public int getAlign() {
		return align;
	}

	public void setAlign(int align) {
		this.align = align;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public String getWidthper() {
		return widthper;
	}

	public void setWidthper(String widthper) {
		this.widthper = widthper;
	}

	public boolean getIsXslField() {
		return isXslField;
	}

	public void setXslField(boolean isXslField) {
		this.isXslField = isXslField;
	}

	public boolean getIsHtmlField() {
		return isHtmlField;
	}

	public void setHtmlField(boolean isHtmlField) {
		this.isHtmlField = isHtmlField;
	}

	public boolean isPlugData() {
		return isPlugData;
	}

	public void setPlugData(boolean isPlugData) {
		this.isPlugData = isPlugData;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOldfield() {
		return oldfield;
	}

	public void setOldfield(String oldfield) {
		this.oldfield = oldfield;
	}
    
}
