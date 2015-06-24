package com.wxservice.framework.generator.pdm;
  
import java.util.List;

public class Table extends PdmObject {
	public List columns = null;

	public PrimaryKey pk = new PrimaryKey();

	public List getColumns() {
		return columns;
	}

	public void setColumns(List columns) {
		this.columns = columns;
		for (int i = 0; i < columns.size(); i++) {
			Column c = (Column) columns.get(i);
			c.setTable(this);
		}
	}

	public PrimaryKey getPk() {
		return pk;
	}

	public void setPk(Column column) {
		pk.setRef(column);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("table:");
		sb.append(name);
		sb.append("|");
		sb.append(code);
		sb.append("\n");
		sb.append("columns:");
		for (int i = 0; i < columns.size(); i++) {
			sb.append("\n");
			sb.append(columns.get(i).toString());
		}
		sb.append("PrimaryKey:");
		sb.append("\n");
		sb.append(pk.toString());
		return sb.toString();
	}

}
