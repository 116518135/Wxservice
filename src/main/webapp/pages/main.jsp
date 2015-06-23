<!DOCTYPE html>
<html lang="en">
<head>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/ext2js.jsp" %>
<%@ page import= "com.wxservice.service.ServiceConfig"%>
<%@ page import= "com.wxservice.framework.util.StringUtil"%>
<%
	String   str=(String)ServiceConfig.getProps().get("wxservice.software.name");
	String   softwarename=new String(str.getBytes("ISO-8859-1"),"UTF-8"); 
  String   image=(String)ServiceConfig.getProps().get("wxservice.software.main.logo");
  //image="esk_logo_white.jpg";
  if(StringUtil.isBlank(image)){
	  image="";
  }else{
	  StringBuffer buf=new StringBuffer();
	  buf.append("<img src='");
	  buf.append(pageContext.getAttribute("ctx"));
	  buf.append("/images/");
	  buf.append(image);
	  buf.append("' width='60'/>");
	  image=buf.toString();
  }
%>


 <title><%=softwarename%></title>
    <link rel="stylesheet" type="text/css" href="${ctx}/widgets/ext2/login/docs.css?v=10"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/widgets/ext2/login/style.css?v=1"/>
	<link rel="shortcut icon" href="${ctx}/images/favicon.ico" type="image/x-icon" />
	<style type="text/css">

		</style>
	<script language="javascript">
      function getCmp(){
	      var cmpname="${SYSTEM_ClIENTSESSION.cmpname}";
	      return "商家："+cmpname;
	  }
	  function getTitle(){
	      return "系统导航";
	  }
	  function getUser(){
	    var username="${username}";
	    return " 操作员："+username;
	    
	  }
	 
	  function getWorkspace(){
	    return '${ctx}/login.do?method=workspace';
	  }
	  function exit(){
	    top.location="${ctx}/login.do?method=exit";
	  }
	  
	  function addSmsTab(id,text,href){
	    var node=new Ext.tree.TreeNode();
	  	node.id=id;
	    node.attributes.text=text;
	    node.attributes.href=href;
	    mainPanel.loadClass(node);
	  }	 
	  var selectMenu=function(record, index){
	    var id="tree"+record.get("id");
	    var text=record.get("text");
	    var href=record.get("href");
	    var node=new Ext.tree.TreeNode();
	    node.id=id;
	    node.attributes.text=text;
	    node.attributes.href=href;
	    usermenucombo.clearValue();
	    usermenucombo.collapse();
	    mainPanel.loadClass(node);
	  }
	  

	  
	 
	  var homenode=new Ext.tree.TreeNode();
	  
	  homenode.id='homenode';
	  homenode.attributes.text='工作台';
	  homenode.attributes.href=getWorkspace();
  	  var psdnode=new Ext.tree.TreeNode();
	  psdnode.id='psdnode';
	  psdnode.attributes.text='修改密码';
	  psdnode.attributes.href='${ctx}/tbsuser.do?method=updatePassword';
	  
	  var toolbar=
        new Ext.Toolbar({
            cls:'top-toolbar',
            style: 'background-color:#55859f;', 
            items:[
            '',getUser()
            ,'->',{
                text:'工作台',
                iconCls: 'icon-home',
                enableToggle: true,
                toggleHandler : function(){
                     mainPanel.loadClass(homenode);
                }
              }
            , '-',{
                text:'修改密码',
                iconCls: 'icon-modifypasswd',
                enableToggle: true,
                toggleHandler : function(){
                    mainPanel.loadClass(psdnode);
                }
              }
             , '-',{
                text:'退出',
                iconCls: 'icon-exit',
                enableToggle: true,
                toggleHandler : function(){
                    exit();
                }
              }                         
			]
        });
        
        
     
	</script>	
</head>
<body scroll="no" id="docs">
  <div id="loading-mask" style=""></div>
  <div id="loading">
    <div class="loading-indicator"><img src="${ctx}/widgets/ext2/login/extanim64.gif" width="64" height="64" style="margin-right:8px;" align="absmiddle"/>正在加载......</div>
  </div>
    <!-- include everything after the loading indicator -->
    <script type="text/javascript" src="${ctx}/widgets/ext2/login/TabCloseMenu.js?v=10"></script>

    <script type="text/javascript" src="${ctx}/widgets/ext2/login/docs.js?v=28"></script>
	<script type="text/javascript">
           Docs.menuData=${menu_trees};
      </script>

  <div id="header">
    <div class="api-title"><%=image%><strong>${webName}</strong></div>
  </div>

  <div id="classes"></div>

  <div id="main"></div>
  <div id="div_sound">
  </div>
  <script src="<c:url value="/scripts/base/exit.js"/>" type="text/javascript"></script>
  </body>
</html>