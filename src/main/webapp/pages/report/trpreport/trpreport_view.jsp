<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/ext2js.jsp"%>
<html>
<head></head>
<script language="javascript">
  function update(){
     var form=document.forms[0];
     form.method.value='update';
     form.submit();
  }
  function del(){
     if(confirm('确认删除资料？')){
        var form=document.forms[0];
        form.method.value='delete';
        form.submit();
     }
  }
  function ret(){
     var form=document.forms[0];
     form.method.value='list';
     form.submit();
  }
  
  function addLine(){
     var form=document.forms[0];
     var action=form.action;
     var title=form.moduleTitle.value;
     var querystring='?method=addLine&trpreportid='+document.all.item("trpreportid").value+'&reportarea=1';
     var url=action+querystring;
     openSubWindow600(url,title);
  }
  function addTrpreporthead(){
	  var form=document.forms[0];
	     var action=form.action;
	     var title=form.moduleTitle.value;
	     var querystring='?method=addLine&trpreportid='+document.all.item("trpreportid").value+'&reportarea=0';
	     var url=action+querystring;
	     openSubWindow600(url,title);
	  }
  function addTrpreporttail(){
	  var form=document.forms[0];
	     var action=form.action;
	     var title=form.moduleTitle.value;
	     var querystring='?method=addLine&trpreportid='+document.all.item("trpreportid").value+'&reportarea=2';
	     var url=action+querystring;
	     openSubWindow600(url,title);
	  }
  function view(pkid){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     var action=form.action;
     var querystring='?method=viewLine&trpreportid='+document.all.item("trpreportid").value+'&trpreportdtlid='+pkid;
     var url=action+querystring;
     openSubWindow600(url,title);
  }
  function addDatesource(){
	     var form=document.forms[0];
	     var action=form.action;
	     var trpreportid=document.all.item("trpreportid").value;
	     var url='trpreportdatasource.do?method=add&trpreportid='+trpreportid;
	     var title="增加数据源";
	     openSubWindow600(url,title);
	  }
  function addPlugin(){
	     var form=document.forms[0];
	     var action=form.action;
	     var trpreportid=document.all.item("trpreportid").value;
	     var url='trpreportplugin.do?method=add&trpreportid='+trpreportid;
	     var title="增加插件";
	     openSubWindow600(url,title);
	  }
  function addTrpreportcondition(){
	     var form=document.forms[0];
	     var action=form.action;
	     var trpreportid=document.all.item("trpreportid").value;
	     var url='trpreportcondition.do?method=add&trpreportid='+trpreportid;
	     var title="增加条件";
	     openSubWindow600(url,title);
	  }
  function improveConditio(){
	  var trpreportid=document.all.item("trpreportid").value;
	     var url='trpreportcondition.do?method=improve&trpreportid='+trpreportid;
	     var title="调整条件布局";
	     openSubWindow600(url,title);
	  }
  function improveHead(){
	  var trpreportid=document.all.item("trpreportid").value;
	     var url='trpreport.do?method=improve'+'&reportarea=0&trpreportid='+trpreportid;
	     var title="调整表头布局";
	     openSubWindow600(url,title);
	  }
  function improveLine(){
	  var trpreportid=document.all.item("trpreportid").value;
	     var url='trpreport.do?method=improve'+'&reportarea=1&trpreportid='+trpreportid;
	     var title="调整明细布局";
	     openSubWindow600(url,title);
	  }
  function improveTail(){
	  var trpreportid=document.all.item("trpreportid").value;
	     var url='trpreport.do?method=improve'+'&reportarea=2&trpreportid='+trpreportid;
	     var title="调整表尾布局";
	     openSubWindow600(url,title);
	  }
  function copyFromothertrpreport(){
	     var trpreportid=document.all.item("trpreportid").value;
	     var url='trpreport.do?method=copyFromothertrpreport&trpreportid='+trpreportid;
	     var title="从其他表复制";
	     openSubWindow600(url,title);
	  }
  function copyDetailToTail(){
	    
	     var form=document.forms[0];
	     form.method.value='copyDetailToTail';
	     form.submit();
	  }
function testReport(){
     var form=document.forms[0];
     var action=getPath()+"/report.do";
     var querystring='?method=condition&reportid='+form.code.value;
     var url=action+querystring;
     winOpen(url,800,600, "_blank");
  }   
function debugReport(){
    var form=document.forms[0];
    var action=getPath()+"/report.do";
    var querystring='?method=debugcondition&reportid='+form.code.value;
    var url=action+querystring;
    winOpen(url,800,600, "_blank");
 } 
</script>
<body style="margin:10px;">
<html:form  action="/trpreport.do"  method="post">	
<html:hidden property="method"/>
<html:hidden property="trpreportid"/>
<html:hidden property="ec_p"/>
<html:hidden property="ec_crd"/>
<html:hidden property="findwhere"/>
<html:hidden property="moduleTitle"/>
<html:hidden property="parentid"/>
<html:hidden property="activeTab"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">

		    <mytag:Button invoke="isEdit" onclick="javascript:addDatesource()" icon="icon-add"  value="增加数据源"/>	    
		    <mytag:Button invoke="isEdit" onclick="javascript:addPlugin()" icon="icon-add"  value="增加插件"/>     
            <mytag:Button invoke="isEdit" onclick="javascript:addTrpreportcondition()" icon="icon-add"   value="增加条件"/>
            <mytag:Button invoke="isEdit" onclick="javascript:addTrpreporthead()" icon="icon-add"  value="增加报表头"/>		    
		    <mytag:Button invoke="isEdit" onclick="javascript:addLine()" icon="icon-add"  value="增加明细"/>     
            <mytag:Button invoke="isEdit" onclick="javascript:addTrpreporttail()" icon="icon-add"   value="增加报表尾"/>
               
            <mytag:Button invoke="isEdit" onclick="javascript:improveConditio()" icon="icon-edit"  value="调整条件布局"/>
            <mytag:Button invoke="isEdit" onclick="javascript:improveHead()" icon="icon-edit"  value="调整表头布局"/> 
            <mytag:Button invoke="isEdit" onclick="javascript:improveLine()" icon="icon-edit"  value="调整明细布局"/>
            <mytag:Button invoke="isEdit" onclick="javascript:improveTail()" icon="icon-edit"  value="调整表尾布局"/>
            </br>
            </br>     
            <mytag:Button invoke="isEdit" onclick="javascript:copyDetailToTail()" icon="icon-copy"  value="COPY明细到表尾"/>
            <mytag:Button invoke="isEdit" onclick="javascript:copyFromothertrpreport()" icon="icon-copy"  value="从其它报表复制"/>
            <mytag:Button  onclick="javascript:debugReport()" icon="icon-apply" value="debug测试"/>
            <mytag:Button  onclick="javascript:testReport()" icon="icon-apply" value="报表测试"/>
		    <mytag:Button  onclick="javascript:submitForm('menu')" icon="icon-apply" value="生成菜单"/>
		    <mytag:Button  onclick="javascript:submitForm('deploy')" icon="icon-apply" value="报表编译"/>
		    <mytag:Button  onclick="javascript:submitForm('download')" icon="icon-apply" value="报表下载"/>
            <mytag:Button invoke="isEdit" onclick="javascript:update()" icon="icon-edit"   value="修改"/>
            <mytag:Button invoke="isEdit" onclick="javascript:del()" icon="icon-delete"   value="删除"/>
		    <mytag:Button  value="返回"   icon="icon-cancel" hotkey="ESC,27" onclick="javascript:ret()"/>
		</td>
	</tr>

</table>	

<div class="eXtremeTable" >
<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<thead>
	<tr class="titleRow" >
		<td colspan="4" ><span><bean:write name="org.apache.struts.taglib.html.BEAN" property="moduleTitle"/></span></td>
	</tr>
	                 <tr>
              <td width="20%" class="label">
                 报表编码
              </td>
              <td width="30%" class="input">
		      <html:text property="code" disabled="true" size="20"   styleClass="field"/>
             </td>
                 <td width="20%" class="label">
            报表名称
        </td>
        <td width="30%" class="input">
			<html:text property="name" disabled="true" size="20"   styleClass="field"/>
        </td>            
 
          
    </tr>		  
                <tr>
              <td width="20%" class="label">
                 处理类
              </td>
              <td width="30%" class="input">
		  
		      <html:select property="processclass" style= "width:150 " disabled="true">
								<html:option value="">&nbsp;</html:option>
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="processclasslist" value="value"/>
							</html:select>
             </td>
                 <td width="20%" class="label">
            显示处理类
        </td>
        <td width="30%" class="input">
		        <html:select property="dispprocessor" disabled="true" style= "width:150 ">
								
								<html:optionsCollection
									name="org.apache.struts.taglib.html.BEAN" property="dispprocessoroptions"/>
							</html:select> 
        </td>            
 
          
    </tr>		  
                <tr>
              <td width="20%" class="label">
                 server地址
              </td>
              <td width="30%" class="input">
		      <html:text property="serverurl" disabled="true" size="20"   styleClass="field"/>
             </td>
              <td width="20%" class="label">
            监听类
        </td>
        <td width="30%" class="input">
			<html:text property="listenclass" disabled="true" size="20"   styleClass="field"/>
        </td>            
            
          
    </tr>		  
     </table>
</div>	

			<div id="tabs1">
				<div id="atttab" class="x-hide-display">
					<%@ include file="trpreportdatasource.jsp"%>
				</div>
				<div id="othertab" class="x-hide-display">
					<%@ include file="trpreportplugin.jsp"%>
				</div>
				<div id="tab3" class="x-hide-display">
					<%@ include file="trpreportcondition.jsp"%>
				</div>
				<div id="tab4" class="x-hide-display">
					<%@ include file="trpreporthead.jsp"%>
				</div>
				<div id="tab5" class="x-hide-display">
					<%@ include file="trpreportviewline.jsp"%>
				</div>
				<div id="tab6" class="x-hide-display">
					<%@ include file="trpreporttail.jsp"%>
				</div>
				
			</div>
  
  

</html:form>
</body>
<script language="javascript">
Ext.onReady(function() {
	var tabs = new Ext.TabPanel( {
		id : "tabPanel",
		renderTo : 'tabs1',
		activeTab : ${struts_form.activeTab},
		autoWidth : true,
		autoheight : true,
		defaults : {
			autoScroll : true
		},
		items : [ {
			contentEl : 'atttab',
			title : '数据源'
		}, {
			contentEl : 'othertab',
			title : '插件'
		}, {
			contentEl : 'tab3',
			title : '条件'

		}, {
			contentEl : 'tab4',
			title : '报表头'

		}, {
			contentEl : 'tab5',
			title : '明细'

		},{
			contentEl : 'tab6',
			title : '报表尾'

		}
		 ],
		 listeners:{
			'tabchange':function(){
				var title = Ext.getCmp('tabPanel').getActiveTab().title;
				var tab = document.all.item("activeTab");
				if(title =='数据源')
				{
					tab.value = 0;
				}
				if(title =='插件')
				{
					tab.value = 1;
				}
				if(title =='条件')
				{
					tab.value = 2;
				}
				if(title =='报表头')
				{
					tab.value = 3;
				}
				if(title =='明细')
				{
					tab.value = 4;
				}
				if(title =='报表尾')
				{
					tab.value = 5;
				}
			;}
		
		}
					 
	});
	
})
</script>

</html>	
