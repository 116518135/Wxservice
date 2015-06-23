package com.wxservice.framework.generator.config;

import java.beans.PropertyDescriptor;
import java.io.File;

import org.apache.commons.beanutils.PropertyUtils;

import com.wxservice.framework.util.StringUtil;
/** 
 * xml config配置文件
 * @author mzhanker
 *
 */
public class Config {
	String workpath;//系统默认为当前路径   
	String module; //模块名，需要配置进来
    String action;  //当前进行的操作。
	boolean overwrite; //是否overwrite
	String template;
	String pdmFile;    //pdm文件路径，默认在 .\pdm目录下
	String table;      //表名
	String title;      //模块的中文名称
	String columns;// 写入格式为：字段名,回车事件,保存前是否需要检查为空|字段名,回车事件,保存前是否需要检查为空
	String dtltable;
	String dtlcolumns;
    //初始化
	public void init() throws Exception {
		File directory = new File("."); 
		this.workpath=directory.getCanonicalPath();
	}
	public static void main(String[] args) throws Exception{

	}
	public String getModule() {
		return module;
	}




	public void setModule(String module) {
		this.module = module;
	}




	public String getAction() {
		
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}




	public boolean isOverwrite() {
		return overwrite;
	}




	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}




	public String getTemplate() {
		return template;
	}




	public void setTemplate(String template) {
		this.template = template;
	}




	public String getPdmFile() {
		return pdmFile;
	}




	public void setPdmFile(String pdmFile) {
		this.pdmFile = pdmFile;
	}




	public String getTable() {
		return table;
	}




	public void setTable(String table) {
		this.table = table;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getColumns() {
		return columns;
	}




	public void setColumns(String columns) {
		this.columns = columns;
	}
	public String getWorkpath() {
		return workpath;
	}
	public void setWorkpath(String workpath) {
		this.workpath = workpath;
	}
	public String getDtltable() {
		return dtltable;
	}
	public void setDtltable(String dtltable) {
		this.dtltable = dtltable;
	}
	public String getDtlcolumns() {
		return dtlcolumns;
	}
	public void setDtlcolumns(String dtlcolumns) {
		this.dtlcolumns = dtlcolumns;
	}




	



}
