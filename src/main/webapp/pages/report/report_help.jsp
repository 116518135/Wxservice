<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head>

<script>
   function htmlquery(){
     var form=document.forms[0];
     form.method.value="html";
     form.submit();
  }
  
  function hotkey(){ 
     var key=window.event.keyCode; 
     if(key==113){
         htmlquery();
     }
     if(key==13){
         htmlquery();
     }
  }    
  document.onkeydown = hotkey; 

</script>
</head>
<body style="margin:10px;">
<font class="font7">

</font>


</body>
</html>	
