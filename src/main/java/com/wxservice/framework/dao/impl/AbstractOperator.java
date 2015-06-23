package com.wxservice.framework.dao.impl;
import com.wxservice.framework.dao.IOperator;

public abstract class AbstractOperator implements IOperator {
	Object pojo=null;// 处理对象列表
	/**
	 * 构造函数
	 */
	public AbstractOperator() {

	}

	/**
	 * 构造函数，传入一个处理对象
	 * 
	 * @param obj
	 */
	public AbstractOperator(Object obj) {
		if (obj != null) {
			this.pojo=obj;
		}
	}



	public void release() {
		this.pojo = null;

	}

	/**  
	 * 返回 pojo 的值   
	 * @return pojo  
	 */
	
	public Object getPojo() {
		return pojo;
	}

	/**  
	 * 设置 pojo 的值  
	 * @param pojo
	 */
	
	public void setPojo(Object pojo) {
		this.pojo = pojo;
	}
	
	

}
