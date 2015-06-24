<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/ext2js.jsp" %>
<head>
    <link rel="stylesheet" type="text/css" href="${ctx}/widgets/ext/html/resources/welcome.css"/>
    <script language="javascript">


	    function upload(method){
    		var form=document.forms[0];
    		if(form.formFile.value==''){
    		  alert('上传文件不能为空!');
    		  return;
    		}
		    form.method.value=method;
            Ext.MessageBox.show({
              msg: "正在执行，请稍候......",
              progressText: '',
              width:300,
              wait:true, 
              waitConfig: {interval:200},
              animEl: 'mb6'
             });
		    form.submit();
	    }
    </script>
</head>
<body>
<html:form  action="/excel.do"  method="post"  enctype="multipart/form-data">	
  <html:hidden property="method"/>
  <html:hidden property="action"/>
  <html:hidden property="moduleTitle"/>
    <div class="col">
        <div class="block">
           <h3 class="block-title">文件</h3>
           <div class="block-body">
                <html:file property="formFile" size="30" styleClass="field-r"/>
           </div>
           <div class="block-body">
                  <ucard:Button  value="上传" icon="icon-save" hotkey='F2,113' onclick="javascript:upload('uploadSave')"/>
           </div>
        </div>
         
    </div>
    <logic:present name="errorlist"> 
     <ec:table items="errorlist"    width="100%" sortable="false" title="错误信息"  showPagination="false"
            filterable="false"    autoIncludeParameters="false"  var="item">
	  <ec:row>
		  		  <ec:column  property="type" title="错误类型" filterable="false" />
          		  <ec:column  property="rows" title="行号" filterable="false" />
          		  <ec:column  property="keyvalue" title="关键字" filterable="false" />
          		  <ec:column  property="message" title="描述" filterable="false" />
         	  </ec:row>
  </ec:table>
 </logic:present>
  
</html:form>	    
</body>
</html>