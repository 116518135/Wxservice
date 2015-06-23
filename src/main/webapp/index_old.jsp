<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import= "com.wxservice.service.ServiceConfig"%>
<%@ include file="/include/header.jsp"%>
<%@ include file="/include/ext2js.jsp"%>
<%
String   str=(String)ServiceConfig.getProps().get("ucard.software.name");
String   softwarename=new String(str.getBytes("ISO-8859-1"),"UTF-8"); 
%>
<html>
<head>
  <title><%=softwarename%></title>
	<script type="text/javascript">
 function submitForm(){
     var form=document.forms[0];
      
     if(document.all.item("usercode").value==""){
        alert("用户不能为空!");
		document.all.item("usercode").focus();
		return;
     }
     
    if(document.all.item("randomcode").value==""){
        alert("验证码不能为空!");
		document.all.item("randomcode").focus();
		return;
     }
     form.action="${ctx}/login.do?method=login";
     form.submit();
  }
  

  function nextSubmit(){
    var key=window.event.keyCode;
	if(key==13){
		submitForm();
    }
  }

  function submitReset(){
     var form=document.forms[0];
	 form.reset();
  }
  
  function keyfn(){
	  var name  = document.activeElement.name;
	  if(name=="usercode"){
		  nextFocus('passwd')
	  }else if(name=="passwd"){
		  nextFocus('randomcode')
	  }else if(name=="randomcode"){
		  nextSubmit();
	  }
   }
  

Ext.onReady(function(){
	    
        Ext.form.Field.prototype.msgTarget = 'side';
        Ext.QuickTips.init();
        setTimeout(function() {
        Ext.get('loading-mask').fadeOut( {
        remove : true
        });
        }, 300);//3秒后移除加载动画
        var LoginForm = new Ext.FormPanel({
                el:'hello-tabs',
                autoTabs:true,
                activeTab:0,
                deferredRender:false,
                border:false,
              
                keys:[{ //处理键盘回车事件   
                    key:Ext.EventObject.ENTER,   
                    fn: keyfn, 
                    scope:this  
                }]  ,
                items:{
                  xtype:'tabpanel',
                  activeTab: 0,
                  defaults:{autoHeight:true, bodyStyle:'padding:6px'}, 
            items:[{
                    title:'登录',
                    contentEl: 'loginInfo',
                    layout:'form',                                
                    defaults: {width: 160},
                    defaultType: 'textfield',
                    items: [{
                                        cls : 'user',
                                        //width:100,
                        fieldLabel: '用  户',
                    name: 'usercode',
                    style: 'font-size: 15px',
                    blankText : '操作员不能为空'
                },{
                    cls : 'key',
                                        //width:100,
                    fieldLabel: '密 码',
                    name: 'passwd',
                    style: 'font-size: 15px',
                    inputType: 'password'
                },{
                    fieldLabel: '验证码',
                    autoCreate:'{tag:"input",type:"text",maxlength:"5",autocomplete:"off""}',
                    name: 'randomcode',
                    maxLength: 4,
                    blankText : '验证码不能为空',
                    style: 'font-size:14px;font-weight:bold;letter-spacing: 1px;background:url(${ctx}/login.do?method=image);background-repeat: no-repeat;background-position: 100px 0px;center right no-repeat;'
                }]
            }]
        }
        });
        var win = new Ext.Window({
                el:'hello-win',
                layout:'fit',
                width:350,
                height:260,
                closeAction:'hide',
                plain: true,
                modal:true,
                collapsible: false,
                draggable: true,
                closable: false,
                items: 
                LoginForm,
                buttonAlign:'center',        
                buttons: [{
                    text:'确定',
                    handler: submitForm
                },{
                    text: '重置',
                    handler: submitReset
                }
                
                ]
            }
          
            );  
            
        win.on('show', function() {
             var userid = Ext.get('usercode'); 
             var passwd = Ext.get('passwd');
             var randcode = Ext.get('randomcode'); 
        });            
                win.show();
});
	</script>
</head>
<body background="${ctx}/images/index_background.png">

<table  border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td>
    
    
<div id="loading-mask" style="">

<div id="loading">

  <div style="text-align:center;padding-top:26%">系统加载中...</div>

</div>

</div>

<div id="hello-win" class="x-hidden">

    <div class="x-window-header"><%=softwarename%>
       
    </div>

    <div id="hello-tabs">

                <img src="${ctx}/images/login-bar.jpg" border="0" width='350' height='60'/>

    </div>

<div id='loginInfo' style='color:red;padding-left:25px;'>
</div>

</div>
</td>
  </tr>
</table>

</body>
</html>