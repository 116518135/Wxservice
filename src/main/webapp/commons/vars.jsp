<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
 String  webName=(String)pageContext.getServletContext().getAttribute("systemTitle");
 request.setAttribute("webName",webName);
%>

