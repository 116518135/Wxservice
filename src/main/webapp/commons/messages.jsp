<%
  String message=(String)request.getAttribute("SYSTEM_MESSAGE");
  
  request.removeAttribute("SYSTEM_MESSAGE");
  if(message!=null) {
%>
<script language="javascript">
  alert("<%=message%>");
</script>
<%}%>

