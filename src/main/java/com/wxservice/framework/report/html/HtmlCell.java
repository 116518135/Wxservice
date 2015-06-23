package com.wxservice.framework.report.html;

public class HtmlCell {
	int row;

	int col;

	String html="";

	public HtmlCell(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

}
