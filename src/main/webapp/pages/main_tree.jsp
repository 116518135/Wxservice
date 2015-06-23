<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/ext2js.jsp" %>
<%@ page import= "com.wxservice.service.ServiceConfig"%>
<%@ page import= "com.wxservice.framework.util.StringUtil"%>
<%
  int  looptime=(int)ServiceConfig.getIntConfig("esk.sms.looptime");
  if(looptime==0){
	  looptime=1000*60*60*24;
  }
  String   image=(String)ServiceConfig.getProps().get("esk.software.main.logo");
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
<head>
  <title>ESK</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/widgets/ext2/login/docs.css?v=4"></link>
	<link rel="stylesheet" type="text/css" href="${ctx}/widgets/ext2/login/style.css"></link>
	<link rel="shortcut icon" href="${ctx}/images/favicon.ico" type="image/x-icon" />
	<style type="text/css">
		</style>
	<script language="javascript">
      function getCmp(){
	        var cmpname="${SYSTEM_ClIENTSESSION.cmpname}";
	    return cmpname;
	  }
	  
	  function getUser(){
	    var username="${username}";
	    return "操作员："+username;
	    
	  }
	  function getPeriod(){
	    var period='${period}';
	    return period;
	  }
	  
	  function getWorkspace(){
	    return '${ctx}/login.do?forward=workspace';
	  }
	  function exit(){
	    top.location="${ctx}/login.do?method=exit";
	  }
	  //短消息下次检查的时间
	  function getSmsLoopTime(){
	    var period=<%=looptime%>;
	    return period;
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
	  

	  
	  var usermenuStore = new Ext.data.Store({
		     proxy: new Ext.data.HttpProxy({
		          url: getPath()+"/login.do?method=selectUserMenu"
		     }),
		     reader: new Ext.data.JsonReader({
		          root: 'rows',
		          totalProperty: 'totalCount',
		          id: 'id'
		     }, [
                   {name: 'id', mapping: 'id'},
                   {    name: 'text', mapping: 'text'},
                   {    name: 'href', mapping: 'href'}
		         ])
		     }
		 );      
		 
      usermenuStore.load();
      var usermenucombo=new Ext.form.ComboBox({
               store: usermenuStore,
               displayField:'text',
               triggerAction: 'all',
               editable:false,
               selectOnFocus:true,
               emptyText:'请选择常用功能...',
               mode: 'local',
               onSelect:selectMenu,
               typeAhead: false,
               listClass: 'x-combo-list-small'
            })
    
	  var homenode=new Ext.tree.TreeNode();
	  
	  homenode.id='homenode';
	  homenode.attributes.text='工作台';
	  homenode.attributes.href=getWorkspace();
  	  var psdnode=new Ext.tree.TreeNode();
	  psdnode.id='psdnode';
	  psdnode.attributes.text='修改密码';
	  psdnode.attributes.href='${ctx}/user.do?method=updatePassword';
	  
  	  var downnode=new Ext.tree.TreeNode();
	  downnode.id='downnode';
	  downnode.attributes.text='下载';
	  downnode.attributes.href='${ctx}/login.do?forward=download';	  
	  var toolbar=
        new Ext.Toolbar({
            cls:'top-toolbar',
            items:['-',usermenucombo,'-'
            ,{
                iconCls: 'icon-expand-all',
				text: '展开菜单',
                handler: function(){ 
                  api.root.expand(true); 
                }
            }, '-', {
                iconCls: 'icon-collapse-all',
                text: '收起菜单',
                handler: function(){ 
                  api.root.collapse(true); 
                }
            },'-',getPeriod()
            ,'-',getUser()
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
                text:'下载',
                iconCls: 'icon-download',
                enableToggle: true,
                toggleHandler : function(){
                    mainPanel.loadClass(downnode);
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
    <script type="text/javascript" src="${ctx}/widgets/ext2/login/TabCloseMenu.js?v=7"></script>

    <script type="text/javascript" src="${ctx}/widgets/ext2/login/docs1.js?v=25"></script>
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
  <script src="<c:url value="/scripts/showsms.js?v=17"/>" type="text/javascript"></script>
  </body>
</html>