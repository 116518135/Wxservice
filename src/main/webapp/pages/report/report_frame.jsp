<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%
	String method = request.getParameter("action");
	String code = request.getParameter("code");
	String trpdcubetplid = request.getParameter("trpdcubetplid");


	StringBuffer curl = new StringBuffer("");
	if(code!=null){
	  curl.append("&code=").append(code);
	}
	if (trpdcubetplid != null) {
		curl.append("&trpdcubetplid=").append(trpdcubetplid);
	}
	String str = curl.toString();
%>
<html>
<head>
<title>${webName}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<frameset rows="*" cols="*" border="0" framespacing="0" frameborder="NO">
	<frame name="topFrame" scrolling="yes" noresize
		src="${ctx}/trpreport.do?method=<%=method%><%=str%>">
</frameset>
<noframes>
<body bgcolor="#FFFFFF" text="#000000">
</body>
</noframes>
</html>
