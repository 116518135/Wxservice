package com.wxservice.framework.report.core;
import java.util.ArrayList;
import java.util.List;

import com.wxservice.database.po.report.Trpreportcondition;
import com.wxservice.framework.dao.IDao;
import com.wxservice.framework.report.base.ReportConfig;
import com.wxservice.framework.report.html.HtmlCell;
import com.wxservice.framework.report.html.HtmlProcess;
import com.wxservice.framework.util.HtmlBuilder;
import com.wxservice.framework.util.WebUtil;
import com.wxservice.framework.web.action.ActionContext;
public class ConditionProcess {
	IDao dao=null;
	public ConditionProcess(){
		
	}
	public String toHtml(ReportConfig rc,ActionContext context){
		HtmlBuilder writer = new HtmlBuilder();
//		加入title
		appendTitle(rc, writer);
		//加入table
		writer.newline();
		writer.append("<table width='100%' class='tableRegion' border='0' cellspacing='0' cellpadding='0'>");
		writer.newline();
		//得到最大行
		int rows=getMaxRow(rc);
		//加入一个空的table
	    List htmlRows = new ArrayList();
        for (int row = 1; row <=rows; row++) {
            List cols = new ArrayList();
            for (int col = 1; col <=2; col++) {
                HtmlCell cell = new HtmlCell(row,col);
                cols.add(cell);
            }
            htmlRows.add(cols);
        }
        //填充html
		for(int i=0;i<rc.getCondition().size();i=i+1){//循环条件
			Trpreportcondition condition=(Trpreportcondition)rc.getCondition().get(i);
			if(condition.getRowindex()==0 && condition.getColindex()==0) continue;
			HtmlProcess hp=(HtmlProcess)WebUtil.getBean(condition.getProcessclass(), context.getRequest());
			String htmlvalue=hp.processHtml(condition, context.getRequest());
			//找到对应的单元格
			HtmlCell cell=this.getCell(condition.getRowindex(), condition.getColindex(), htmlRows);
			if(cell==null) continue;
			cell.setHtml(htmlvalue);
		}
		
		//在write的中画html
        for (int i = 0; i < htmlRows.size(); i++) {
            List cols = (List) htmlRows.get(i);
            writer.newline();
            writer.tabs(2);
            writer.append("<tr>");
            for (int j = 0; j < cols.size(); j++) {
            	writer.tabs(2);
            	HtmlCell cell = (HtmlCell) cols.get(j);
            	writer.newline(); 
            	writer.tabs(3);
            	writer.append(cell.getHtml());
            }
            writer.tabs(2);
            writer.append("</tr>");
        }		
		writer.append("</table>");
		return writer.toString();
		
	}


	private int getMaxRow(ReportConfig rc) {
		int max=1;
		for(Object o:rc.getCondition()){
			Trpreportcondition condition=(Trpreportcondition)o;
			if(max<condition.getRowindex()){
				max=condition.getRowindex();
			}
		}
		return max;
	}
	
    private HtmlCell getCell(int row, int col, List htmlRows) {
    	HtmlCell cell = null;
            List cols = (List) htmlRows.get(row-1);
            cell = (HtmlCell) cols.get(col-1);

        return cell;
    }

	private void appendTitle(ReportConfig rc, HtmlBuilder writer) {
		//加入title
		writer.append("<div class='eXtremeTable' >");
		writer.newline().tab();
		writer.append("<table id='ec_table'  border='0'  cellspacing='0'  cellpadding='0'  class='tableRegion'  width='100%' >");
		writer.newline().tab().tab();
		writer.append("<thead>");
		writer.newline().tab().tab();		
		writer.append("<tr class='titleRow' >");
		writer.newline().tab().tab();	
		writer.append("<td colspan='0' ><span>");
		writer.append(rc.getReport().getName());
		writer.append("</span></td>");
		writer.newline().tab().tab();		
		writer.append("</tr>");
		writer.newline().tab();		
		writer.append("</table>");
		writer.newline();		
		writer.append("</div>");
	}


	public IDao getDao() {
		return dao;
	}


	public void setDao(IDao dao) {
		this.dao = dao;
	}



}
