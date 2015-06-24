package com.wxservice.framework.components.quartz;

import java.util.Calendar;

public class JobTest {  
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		System.out.println(year);
		System.out.println(month);
	}
}
