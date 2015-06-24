<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<script type="text/javascript"	src="${ctx}/widgets/ext2/adapter/jquery/jquery.js"></script>
<html>
<head>
<title></title>
<script language="javascript">
</script>
</head>

<body  leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" scroll="yes">


<br/>
Table单选
</br>
 表名：<input type="text" name="table"   size=20  class="field" value="tbsstore">
 </br>
 对应关系：<input type="text" name="mapping"  class="field"  size=40 value="tableid,tbsstoreid|tablename,name">
 </br>
 Table单选:
 <input type="text" name="tableid"   value="">
 <input type="text" name="tablename" onkeydown="tableEnter1()" size="20" value="" class="field">
 <button   class="selectButton" type="button"  onclick="javascript:selectTable1()"/>
<img src="${ctx}/images/select.gif" width="12"/></button>
<script language='javascript'>
 function selectTable1(){
   var tablename=document.all.item("table").value;
   var mapping="tableid,tableid|tablename,name"
   selectTable(tablename,mapping,1);
 }
 function tableEnter1(){
     var tablename=document.all.item("table").value;
     var mapping=document.all.item("mapping").value;
     tableEnter(tablename,mapping,1);
 }
 
 function selectTable(tablename,mapping,limitcmpflag){
   var url ='${ctx}/select.do?forward=main&action=selectTable&table='+tablename+"&mapping="+mapping+"&limitcmpflag="+limitcmpflag;
   OpenImportWindow(url,mapping,700,500);
 }
 function tableEnter(tablename,mapping,limitcmpflag){
     var querystring="table="+tablename+"&limitcmpflag="+limitcmpflag;
     codeEnter('tableEnter',mapping,querystring,'');
 }
</script>  
</br>
Table多选:
 <input type="text" name="tableids" value="">
 <input type="text" name="tablenames"  size="20" value="" class="field">
 <button   class="selectButton" type="button"  onclick="selectTableMulti1()"/>
<img src="${ctx}/images/select.gif" width="12"/></button>
 <script language='javascript'>
 function selectTableMulti1(tablename,limitcmpflag){
    var tablename=document.all.item("table").value;
    selectTableMulti(tablename);
 }
 function selectTableMulti(tablename,limitcmpflag,ids,names){
   var url ='${ctx}/select.do?forward=main&action=selectTableMulti&table='+tablename+"&limitcmpflag="+limitcmpflag;
   var callWindow=winOpen(url,600,550,'_blank');
   callWindow.multivalue=document.all.item(ids);
   callWindow.multiname=document.all.item(names);
   callWindow.focus();
 }
</script> 

<br/>
员工单选
 <input type="text" name="tbsuserid"   value="">
 <input type="text" name="username"  size="20" value="" class="field">
 <button   class="selectButton" type="button"  onclick="javascript:selectUser()"/>
<img src="${ctx}/images/select.gif" width="12"/></button>
<script language='javascript'>
 function selectUser(){
   var url ='${ctx}/select.do?forward=main&action=selectUser';
   var map="tbsuserid,tbsuserid|username,name";
   OpenImportWindow(url,map,700,500);
 }
</script> 

</br>
员工多选:
 <input type="text" name="userids" value="">
 <input type="text" name="usernames"  size="20" value="" class="field">
 <button   class="selectButton" type="button"  onclick="selectUserMulti()"/>
<img src="${ctx}/images/select.gif" width="12"/></button>
 <script language='javascript'>
 function selectUserMulti(){
   var url ='${ctx}/select.do?forward=main&action=selectUserMulti';
   var callWindow=winOpen(url,600,550,'_blank');
   callWindow.multivalue=document.all.item("userids");
   callWindow.multiname=document.all.item("usernames");
   callWindow.focus();
 }
</script> 

</body>

</html>
