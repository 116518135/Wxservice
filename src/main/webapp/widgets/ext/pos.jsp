<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
  <title>${webName}</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/widgets/ext/html/resources/collapser.css"></link>
	<link rel="stylesheet" type="text/css" href="${ctx}/widgets/ext/html/resources/docs.css"></link>
	
	<!-- GC -->
	<style type="text/css">
	html, body {
        margin:0;
        padding:0;
        border:0 none;
        overflow:hidden;
        height:100%;
    }
	</style>
	<script language="javascript">
	  function getPath(){
	    return "${ctx}";
	  }
	  
	  function getCmp(){
	    var cmpname="${cmpname}";
	    return cmpname;
	  }
	  
	  function getUser(){
	    var username="${username}";
	    return "用户："+username;
	    
	  }
	</script>
</head>
<body scroll="no" id="docs">
  	<div id="loading-mask" style="width:100%;height:100%;background:#c3daf9;position:absolute;z-index:20000;left:0;top:0;">&#160;</div>
  <div id="loading">
    <div class="loading-indicator"><img src="${ctx}/widgets/ext/resources/images/default/grid/loading.gif" style="width:16px;height:16px;" align="absmiddle">&#160;
     <span class="toolbar_link_text">正在加载...</span>
    </div>
  </div>
    <!-- include everything after the loading indicator -->
    <script type="text/javascript" src="${ctx}/widgets/ext/adapter/yui/yui-utilities.js"></script>
    <script type="text/javascript" src="${ctx}/widgets/ext/adapter/yui/ext-yui-adapter.js"></script>
	<script type="text/javascript" src="${ctx}/widgets/ext/ext-all.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/widgets/ext/resources/css/ext-all.css" />

    <script type="text/javascript" src="${ctx}/widgets/ext/html/resources/docs.js"></script>

  <div id="header" class="ylayout-inactive-content">
	<a href="${ctx}/login.do?method=exit" style="float:right;margin-right:10px;">
	<img src="${ctx}/images/exit.gif" border="no" alt="退出"/>
	</a>
	
	<div style="padding-top:3px;">${webName}:最专业的网络分销ERP
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	在线服务QQ:809123665<a href="http://wpa.qq.com/msgrd?V=1&amp;Uin=809123665&amp;Site=ESK;Menu=yes" target="blank"><img src="${ctx}/images/qq.gif" border="0"></a> 
	
	</div>

  </div>

  <div id="classes" class="ylayout-inactive-content">
   <a id="welcome-link">用户：${username}</a>
	  <!-- BEGIN TREE -->
	  ${menu_trees}
  </div>
  <iframe id="main" id="main" frameborder="no"></iframe>
  </body>
</html>