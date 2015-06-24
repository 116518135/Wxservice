<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.wxservice.service.ServiceConfig"%>
<%@ include file="/include/header.jsp"%>
<%@ include file="/include/ext2js.jsp"%>
<%
	String str = (String) ServiceConfig.getProps().get(
			"ucard.software.name");
	String softwarename = new String(str.getBytes("ISO-8859-1"),
			"UTF-8");
%>
<html>
<head>
<title><%=softwarename%></title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
.stit {
	font-size: 15px;
	font-weight: bold;
	color: #FF9352;
	padding-bottom: 5px;
	border-bottom-width: 1px;
	border-bottom-style: dashed;
	border-bottom-color: #FF9352;
	width: 90%;
	margin: 0px auto 10px;
}

.maintxt {
	font-size: 14px;
	line-height: 200%;
	color: #333333;
	width: 85%;
	margin: 0 auto 20px;
}
</style>
</head>
<body>

	<div class="stit"><br>博饼规则</div>
	<div class="maintxt">

		<p>
			&nbsp;&nbsp;每位玩家通过手机进行注册，系统会发出验证码，验证后，该玩家注册成功。<br>
		</p>
		&nbsp;&nbsp;注册玩家可随时进入“个人中心”完善个人资料，为便于奖品发放收取，玩家注册完成后即可参与主赛场和专场的网络博饼。<br>
		</p>
	</div>
	<div class="maintxt">
		<p>
			&nbsp;&nbsp;&nbsp;&nbsp;1、每个用户每天在每个赛场可以博20次，如出现最好成绩相同者，则先博出该分数者胜出，游戏时间以系统时间为准。<br>
			&nbsp;&nbsp;&nbsp;&nbsp;2、如恶意注册、通过不正当手段中奖，我们将取消其中奖资格。<br>
			&nbsp;&nbsp;&nbsp;&nbsp;3、如出现当日未满20次游戏停止情况，重新登录即可继续游戏。<br>
			&nbsp;&nbsp;&nbsp;&nbsp;4、由于网络原因造成的游戏过程不正常、结果未被统计等情况，及对博饼成绩如有异议的，我们只以服务器端成绩为准。<br>
			&nbsp;&nbsp;&nbsp;&nbsp;5、玩家须仔细阅读游戏规则，同意后再注册参加。<br>
		</p>
	</div>
	<div class="stit">特别声明</div>
	<div class="maintxt">
		&nbsp;&nbsp;我们将竭力对活动进行技术及服务保障，如遇不可抗力，保留活动暂停及终止的权利，同时保留对此次活动规则的完全解释权。<br>
	</div>
	<div class="stit">状元等级说明（本游戏依据鼓浪屿申遗办的博饼大赛规则）</div>
	<div class="maintxt">
		<img src="${ctx}/images/shuoming.gif" width="270" height="280">
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="${ctx}/scripts/login/jquery.js"></script>
	<script src="${ctx}/scripts/login/bootstrap.min.js"></script>
</body>
</html>