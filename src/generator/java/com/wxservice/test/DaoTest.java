package com.wxservice.test;

import java.util.List;

import com.wxservice.framework.util.StringUtil;

public class DaoTest {
	
	public static void main(String[] args){
//		DaoTest t=new DaoTest();
//		String str=t.getClass().getName();
//	    List list=StringUtil.string2List(str,".");
//	    list.get(list.size()-1);
//	    System.out.println(list.get(list.size()-1));
	    substring();
	}
	
	public static void substring(){
		String str="/tbscolor.do?method=list";
		List list = StringUtil.string2List(str,"?");
		System.out.println(list.get(0));
		System.out.println(list.get(1));
	}
	
}
