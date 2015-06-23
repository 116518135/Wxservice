<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head>
</head>
<frameset cols="200,*"  border="0" framespacing="1" rows="*" frameborder="0">
  <frame name="leftFrame"  scrolling="yes"  src="${ctx}/tsysmenu.do?method=tree">
  <frame name="rightFrame" src="${ctx}/tsysmenu.do?method=list&parentid=0">
</frameset>
<noframes><body bgcolor="#FFFFFF" text="#000000">
</body></noframes>
</html>
