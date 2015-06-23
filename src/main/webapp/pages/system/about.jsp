<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@ include file="/include/ext2js.jsp"%>
<head>
<title></title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/widgets/ext/html/resources/welcome.css" />
<script language="javascript">
       
	    function backup(){
    		var form=document.forms[0];
		    form.method.value="backupdatabase";
		    form.submit();
	    }
	   
	    function clean(){
    		var form=document.forms[0];
		    form.method.value="cleanCache";
		    form.submit();
	    }
	    
	    function cleanMemory(){
    		var form=document.forms[0];
		    
		    form.submit();
	    }
	   function test(){
    		var form=document.forms[0];
		    var href=getPath()+"/about.do?method=test";
            parent.addSmsTab("testid","选择框测试",href);
             
	    }
	   

	  

    </script>
</head>
<body>
<html:form action="/about.do" method="post"
	enctype="multipart/form-data">
	<html:hidden property="method" />
	<html:hidden property="action" />
	<html:hidden property="moduleTitle" />
	<div class="col">


	
	
	<div class="block">
	<h2 class="block-title">系统信息</h2>
	<div class="block-body">最大内存：${struts_form.maxMemory}M</div>
	<div class="block-body">已分配内存：${struts_form.totalMemory}M</div>
	<div class="block-body">空闲内存：${struts_form.freeMemory}M</div>

	<div class="block-body">强制垃圾收集: <mytag:Button value="执行"
		icon="icon-apply" onclick="javascript:cleanMemory()" /></div>
	</div>
	<div class="block">
	<h2 class="block-title">清除缓存</h2>
	<div class="block-body"></div>
	<div class="block-body"><mytag:Button value="执行" icon="icon-apply"
		onclick="javascript:clean()" /></div>
	</div>

	<div class="block">
	<h2 class="block-title">备份数据库</h2>
	<div class="block-body"></div>
	<div class="block-body"><mytag:Button value="执行" icon="icon-apply"
		onclick="javascript:backup()" /></div>
	</div>
	</div>


</html:form>
</body>
</html>