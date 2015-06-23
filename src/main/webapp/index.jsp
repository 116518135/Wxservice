<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import= "com.wxservice.service.ServiceConfig"%>
<%@ include file="/include/header.jsp"%>
<%@ include file="/include/ext2js.jsp"%>
<%
String   str=(String)ServiceConfig.getProps().get("wxservice.software.name");
String   softwarename=new String(str.getBytes("ISO-8859-1"),"UTF-8"); 
%>
<html>
<head>
  <title><%=softwarename%></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${ctx}/scripts/login/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${ctx}/scripts/login/css/thin-admin.css" rel="stylesheet" media="screen">
<link href="${ctx}/scripts/login/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${ctx}/scripts/login/css/style.css" rel="stylesheet">
	<script type="text/javascript">
	function onpost() {
    if(document.all.item("usercode").value==""){
       alert("帐号不能为空!");
		document.all.item("usercode").focus();
		return false;
    }    
     form.action="${ctx}/login.do?method=login";
     form.submit();
}
</script>
</head>
<body>

<div class="login-logo">
	<img src="${ctx}/scripts/login/logo.png" width="147" height="40"> 
    </div>

<div class="widget" >
<div class="login-content">
  <div class="widget-content" style="padding-bottom:0;">
  <form method="post" action="${ctx}/login.do?method=login" class="no-margin" onsubmit="return onpost()">
                
                
                <fieldset>
                    <div class="form-group no-margin">
                       <h3 class="form-title" style="font-size: 18px">登录帐号</h3>
						<label for="text">Account</label>
                        <div class="input-group input-group-lg">
                                <span class="input-group-addon">
                                    <i class="icon-user"></i>
                                </span>
                            <input type="text" placeholder="请输入帐号" class="form-control input-lg" id="usercode" name="usercode">
                        </div>

                    </div>

                    <div class="form-group">
                       <label for="password">Password</label>
                   
                        <div class="input-group input-group-lg">
                                <span class="input-group-addon">
                                    <i class="icon-lock"></i>
                                </span>
                            <input type="password" placeholder="请输入密码" class="form-control input-lg" id="password" name="passwd">
                        </div>

                    </div>
                </fieldset>
               <div class="form-actions">
				<label class="checkbox">						
				</label>
					<button class="btn btn-warning pull-right" type="submit">
				登录 <i class="m-icon-swapright m-icon-white"></i>
				</button> 
                         
			</div>
            
            
            </form>
  
  
  </div>
   </div>
</div>








<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="${ctx}/scripts/login/jquery.js"></script> 
<script src="${ctx}/scripts/login/bootstrap.min.js"></script> 



 

<!--switcher html start-->
<div class="demo_changer active" style="right: 0px;">
                <div class="demo-icon"></div>
                <div class="form_holder">
                        

                    <div class="predefined_styles">
                        <a class="styleswitch" rel="a" href=""><img alt="" src="${ctx}/scripts/login/a.jpg"></a>	
                        <a class="styleswitch" rel="b" href=""><img alt="" src="${ctx}/scripts/login/b.jpg"></a>
                        <a class="styleswitch" rel="c" href=""><img alt="" src="${ctx}/scripts/login/c.jpg"></a>
                        <a class="styleswitch" rel="d" href=""><img alt="" src="${ctx}/scripts/login/d.jpg"></a>
                        <a class="styleswitch" rel="e" href=""><img alt="" src="${ctx}/scripts/login/e.jpg"></a>
                        <a class="styleswitch" rel="f" href=""><img alt="" src="${ctx}/scripts/login/f.jpg"></a>
                        <a class="styleswitch" rel="g" href=""><img alt="" src="${ctx}/scripts/login/g.jpg"></a>
                        <a class="styleswitch" rel="h" href=""><img alt="" src="${ctx}/scripts/login/h.jpg"></a>
                        <a class="styleswitch" rel="i" href=""><img alt="" src="${ctx}/scripts/login/i.jpg"></a>
                        <a class="styleswitch" rel="j" href=""><img alt="" src="${ctx}/scripts/login/j.jpg"></a>
                    </div>
					                    
                </div>
            </div>
            
            
    <!--switcher html end-->
<script src="${ctx}/scripts/login/switcher.js"></script> 
<script src="${ctx}/scripts/login/moderziner.custom.js"></script> 
<link href="${ctx}/scripts/login/css/switcher.css" rel="stylesheet">
<link href="${ctx}/scripts/login/css/switcher-defult.css" rel="stylesheet">
<link rel="alternate stylesheet" type="text/css" href="${ctx}/scripts/login/a.css" title="a" media="all" disabled="">
<link rel="alternate stylesheet" type="text/css" href="${ctx}/scripts/login/b.css" title="b" media="all" disabled="">
<link rel="alternate stylesheet" type="text/css" href="${ctx}/scripts/login/c.css" title="c" media="all" disabled="">
<link rel="alternate stylesheet" type="text/css" href="${ctx}/scripts/login/d.css" title="d" media="all" disabled="">
<link rel="alternate stylesheet" type="text/css" href="${ctx}/scripts/login/e.css" title="e" media="all" disabled="">
<link rel="alternate stylesheet" type="text/css" href="${ctx}/scripts/login/f.css" title="f" media="all" disabled="">
<link rel="alternate stylesheet" type="text/css" href="${ctx}/scripts/login/g.css" title="g" media="all" disabled="">
<link rel="alternate stylesheet" type="text/css" href="${ctx}/scripts/login/h.css" title="h" media="all" disabled="">
<link rel="alternate stylesheet" type="text/css" href="${ctx}/scripts/login/i.css" title="i" media="all" disabled="">
<link rel="alternate stylesheet" type="text/css" href="${ctx}/scripts/login/j.css" title="j" media="all">

</body>
</html>