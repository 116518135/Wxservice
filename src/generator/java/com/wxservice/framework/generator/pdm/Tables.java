package com.wxservice.framework.generator.pdm;
  
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class Tables {

	private Map tables = new HashMap();

	public Map getTables() {
		return tables;
	}

	public int size() {
		return tables.size();
	}

	public boolean containsKey(String tablename) {
		return tables.containsKey(tablename);
	}

	public boolean isEmpty() {
		return tables.isEmpty();
	}

	public Table put(String tablename, Table table) {
		return (Table) tables.put(tablename, table);
	}

	public Table get(String tablename) {
		return (Table) tables.get(tablename);
	}

	public Table put(List list) {
		Table ret = null;
		for (int i = 0; i < list.size(); i++) {
			Table table = (Table) list.get(i);
			ret = put(table.getCode(), table);
		}
		return ret;
	}

	public Set keySet() {
		return tables.keySet();
	}
	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		Iterator lt=tables.keySet().iterator();
		sb.append("\n");
		while(lt.hasNext()){
			String key=(String)lt.next();
			Table table=(Table)tables.get(key);
			sb.append(table.toString());
			sb.append("\n");
			
		}
        return sb.toString();		 
	}

}
