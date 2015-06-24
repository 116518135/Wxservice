package com.wxservice.framework.util;

public class RowColCell {
    public RowColCell() {};
    public Integer row = new Integer(0);
    public Integer col = new Integer(0);
    public String label = "";
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Integer getCol() {
		return col;
	}
	public void setCol(Integer col) {
		this.col = col;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
    
}
