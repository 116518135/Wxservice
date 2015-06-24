<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/ext2js.jsp"%>
<html>
<head>
  <title>${webName}</title>
 <link rel="shortcut icon" href="${ctx}/images/favicon.ico" type="image/x-icon" />
<script>
  function xslquery(){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     var action=form.action;
     var querystring='?method=cacheXsl&reportid='+form.reportid.value;
     var url=action+querystring;
     winOpen(url);
  }
</script>
</head>
<body style="margin:10px;background-color: #BCD2EE" id="docs"  >

<html:form  action="/report.do"  method="post">	
<input type="hidden" name="method" value="cacheHtml"/>
<html:hidden property="reportid"/>
<html:hidden property="moduleTitle"/>
<html:hidden property="auth_string"/>
<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
</table>	

 	<div id="tabs1">
	<div id="htmltable" class="x-hide-display">
	    ${struts_form.htmlContent}
	</div>
	
	
	</div>
	        

</html:form>
</body>
<script language="javascript">
 Ext.onReady(function(){
       var tabs = new Ext.TabPanel({
             renderTo: 'tabs1',
             activeTab: 0,
             autoWidth:true,
             autoHeight:true,
             defaults:{autoScroll: false},
             items:[
               {contentEl:'htmltable', title:'${struts_form.moduleTitle}'}
             ]
    });
  })  
 </script>   
</html>	
