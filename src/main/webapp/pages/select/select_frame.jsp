<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%
	String method = request.getParameter("action");
	String condition1 = request.getParameter("condition1");
	String condition2 = request.getParameter("condition2");
	String condition3 = request.getParameter("condition3");
	String condition4 = request.getParameter("condition4");
	String condition5 = request.getParameter("condition5");
	String condition6 = request.getParameter("condition6");
	String condition7 = request.getParameter("condition7");
	String condition8 = request.getParameter("condition8");
	String condition9 = request.getParameter("condition9");
	String condition10 = request.getParameter("condition10");
	String cmpid = request.getParameter("cmpid");
	String anonymous = request.getParameter("anonymous");
	String object = request.getParameter("object");
	String objecttype = request.getParameter("objecttype");
	String querystring = request.getParameter("querystring");
	String table = request.getParameter("table");
	String limitcmpflag = request.getParameter("limitcmpflag");
	String mapping = request.getParameter("mapping");
	if (condition1 == null) {
		condition1 = "";
	}
	if (condition2 == null) {
		condition2 = "";
	}
	if (condition3 == null) {
		condition3 = "";
	}
	if (condition4 == null) {
		condition4 = "";
	}

	if (condition5 == null) {
		condition5 = "";
	}

	if (condition6 == null) {
		condition6 = "";
	}

	if (condition7 == null) {
		condition7 = "";
	}

	if (condition8 == null) {
		condition8 = "";
	}

	if (condition9 == null) {
		condition9 = "";
	}

	if (condition10 == null) {
		condition10 = "";
	}

	if (object == null) {
		object = "";
	}

	if (objecttype == null) {
		objecttype = "";
	}

	StringBuffer curl = new StringBuffer("");
	curl.append("&anonymous=").append(anonymous);
	curl.append("&condition1=").append(condition1);
	curl.append("&condition2=").append(condition2);
	curl.append("&condition3=").append(condition3);
	curl.append("&condition4=").append(condition4);
	curl.append("&condition5=").append(condition5);
	curl.append("&condition6=").append(condition6);
	curl.append("&condition7=").append(condition7);
	curl.append("&condition8=").append(condition8);
	curl.append("&condition9=").append(condition9);
	curl.append("&condition10=").append(condition10);
	curl.append("&object=").append(object);
	curl.append("&objecttype=").append(objecttype);
	if(querystring!=null){
	  curl.append("&querystring=").append(querystring);
	}
	if (cmpid != null) {
		curl.append("&cmpid=").append(cmpid);
	}
	if (table != null) {
		curl.append("&table=").append(table);
	}
	if (limitcmpflag != null) {
		curl.append("&limitcmpflag=").append(limitcmpflag);
	}	
	if (mapping != null) {
		curl.append("&mapping=").append(mapping);
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
		src="${ctx}/select.do?method=<%=method%><%=str%>">
</frameset>
<noframes>
<body bgcolor="#FFFFFF" text="#000000">
</body>
</noframes>
</html>
