<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@ include file="/include/ext2js.jsp" %>
<head>
<title>Ext - Welcome</title>
  <link rel="stylesheet" type="text/css" href="${ctx}/widgets/ext/html/resources/welcome.css"/>
<script language="javascript">
       	function upload(){
		var form=document.forms[0];
		
		if(form.report.value=='') {
		  alert('上传文件不能为空');
		  return;
		}
		form.method.value="upload";
		form.submit();
	    }
      function deploy(){
		var form=document.forms[0];
		
		form.method.value="deploy";
		form.submit();
	 }     
 
      function load(){
		var form=document.forms[0];
		
		form.method.value="load";
		form.submit();
	 } 	 
	function updatereport(){
		var form=document.forms[0];
		
		form.method.value="updatereport";
		form.submit();
	 } 	
	  function remoteDeploy(){
	        var form=document.forms[0];
    		form.method.value="remoteDeploy";
            Ext.MessageBox.show({
              msg: "正在执行......",
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
<html:form action="/reporttool.do" method="post" enctype="multipart/form-data">
	<html:hidden property="method" />
	<html:hidden property="moduleTitle" />
	<html:hidden property="reportServerUrl"/>
    <input type="hidden" name="anonymous" value="1">
	<div class="col">
	<div class="block">
	<h3 class="block-title">上传</h3>
	<div class="block-body">上传已经编译后的报表文件，进行发布.</div>
	<div class="block-body"><html:file property="report" size="20"
		styleClass="field" /> <html:checkbox property="overDeploy" value="1" />如果存在,覆盖原来的旧报表
	</div>
	<div class="block-body"><input type="button" value=" 上传 "
		icon="icon-apply" onclick="javascript:upload()" /></div>
	</div>
	<div class="block">
	<h3 class="block-title">编译</h3>
	<div class="block-body">将数据库的报表设计进行编译，生成格式文件，存放在Web服务器中.</div>
	<div class="block-body"><html:radio property="overBuild"
		value="1" />重新全部编译 <html:radio property="overBuild" value="0" />已经存在的不再编译
	</div>
	<div class="block-body"><input type="button" value=" 编译 "
		icon="icon-apply" onclick="javascript:deploy()" /></div>
	</div>
	
        <div class="block">
           <h3 class="block-title">远程发布</h3>
           <div class="block-body">
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;远程发布地址:<html:text property="deployServer" size="40" styleClass="field"/>
           </div>
           <div class="block-body">
                 待发布的报表编号:<html:text property="deployReportcode" size="40" styleClass="field"/>
           </div>           
           <div class="block-body">
                  <ucard:Button  onclick="javascript:remoteDeploy()"  icon="icon-apply"  value="执行"/>
           </div>
        </div>    
	
	
	
	<div class="block">
	<h3 class="block-title">加载</h3>
	<div class="block-body">
	首先将报表文件存放在Web服务器指定的目录中,然后通过此功能，将报表文件的数据重新加载到数据库中.它是编译的逆向工程.</div>
	<div class="block-body"><html:radio property="overLoad" value="1" />如果已经加载过，先删除，再加载.
	<html:radio property="overLoad" value="0" />已经存在的不再加载</div>
	<div class="block-body"><input type="button" value=" 加载 "
		icon="icon-apply" onclick="javascript:load()" disabled="disabled"/></div>
	</div>

	<div class="block">
	<h3 class="block-title">批量处理报表条件</h3>
			报表多选:
		 <input type="hidden" name="reportids" value="">
		 <input type="text" name="reportnames"  size="20" value="" class="field">
		 <button  class="selectButton" onclick="selectReportMulti()"/>
		<img src="${ctx}/images/select.gif" width="12"/></button>
		 <script language='javascript'>
		 function selectReportMulti(){
		  
		  var url ='${ctx}/select.do?forward=main&action=selectTableMulti&table=trpreport';
		   var callWindow=winOpen(url,600,550,'_blank');
		   callWindow.multivalue=document.all.item("reportids");
		   callWindow.multiname=document.all.item("reportnames");
		   callWindow.focus();
		 }
		</script> 
 <br>
		 原条件JS名称：<input type="text" name="oldjsname"  size="20" value="" class="field">
		
		 新条件名称和JS名称(,分割)<input type="text" name="newjsname"  size="20" value="" class="field">
	<div class="block-body"><input type="button" value="执行"
		icon="icon-apply" onclick="javascript:updatereport()" /></div>
	</div>
	</div>
</html:form>
<script language="javascript">

</script>
</body>
</html>