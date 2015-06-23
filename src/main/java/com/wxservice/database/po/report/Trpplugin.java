package com.wxservice.database.po.report;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
@Entity
@Table(name = "trpplugin")
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Trpplugin {
	private static final long serialVersionUID = 1L;
	protected Integer trppluginid;//系统ID
	protected String name;//编号
	protected String code;//名称
	protected String springname;// 对象名
	protected String fieldalias;// 字段别名
	protected String template;// sql模板
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Trpplugin")
	@TableGenerator(
	   name = "Trpplugin",
	   table = "tsyscounter",
	   pkColumnName = "tablekey",
	   valueColumnName = "countvalue",
	   pkColumnValue = "Trpplugin",
	   allocationSize = 1
	)
	public Integer getTrppluginid() {
		return trppluginid;
	}
	public void setTrppluginid(Integer trppluginid) {
		this.trppluginid = trppluginid;
	}
	@Column(length=30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=20)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(length=30)
	public String getSpringname() {
		return springname;
	}
	public void setSpringname(String springname) {
		this.springname = springname;
	}
	@Column(length=50)
	public String getFieldalias() {
		return fieldalias;
	}
	public void setFieldalias(String fieldalias) {
		this.fieldalias = fieldalias;
	}
	@Column(length=1024)
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((trppluginid == null) ? 0 : trppluginid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trpplugin other = (Trpplugin) obj;
		if (trppluginid == null) {
			if (other.trppluginid != null)
				return false;
		} else if (!trppluginid.equals(other.trppluginid))
			return false;
		return true;
	}
	
}
