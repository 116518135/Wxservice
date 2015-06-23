package com.wxservice.framework.report.base;

import java.io.Serializable;
import java.util.List;

import com.wxservice.database.po.report.Trpreport;



public interface ReportConfig extends Serializable {
   public Trpreport getReport();
   public List   getCondition();
   public List   getDatasource();
   public List   getPlugin();
   public List   getHeader();
   public List   getFooter();
   public List   getDetail();
}
