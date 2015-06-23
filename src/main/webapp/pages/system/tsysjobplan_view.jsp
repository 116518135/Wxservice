<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<html>
<head></head>
<script language="javascript">
  function update(){
     var form=document.forms[0];
     form.method.value='update';
     form.submit();
  }
  function del(){
     if(confirm('确认删除资料？')){
        var form=document.forms[0];
        form.method.value='delete';
        form.submit();
     }
  }
  function ret(){
     var form=document.forms[0];
     form.method.value='list';
     form.submit();
  }
</script>
<body style="margin:10px;">
<html:form  action="/tsysjobplan.do"  method="post">	
<html:hidden property="method"/>
<html:hidden property="tsysjobplanid"/>
<html:hidden property="ec_p"/>
<html:hidden property="ec_crd"/>
<html:hidden property="findwhere"/>
<table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
		    <mytag:Button invoke="isEdit" onclick="javascript:update()" icon="icon-edit"  hotkey="F4,115" value="修改"/>&nbsp;
               
            <mytag:Button invoke="isEdit" onclick="javascript:del()" icon="icon-delete"  hotkey="F5,116" value="删除"/>&nbsp;
                  
		    <mytag:Button  value="返回"   icon="icon-cancel" hotkey="ESC,27" onclick="javascript:ret()"/>
		</td>
	</tr>

</table>	

<div class="eXtremeTable" >
<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<tr class="titleRow" >
		<td colspan="4" ><span><bean:write name="org.apache.struts.taglib.html.BEAN" property="moduleTitle"/></span></td>
	</tr>
	      <tr>
        <td width="20%" class="label">
         计划编号
        </td>
        <td width="30%" class="input">
            <html:text property="code" maxlength="20" disabled="true" onblur=""  onkeydown="nextFocus('name')"  size="20"   styleClass="field"/>        
        </td>
                 <td width="20%" class="label">
            计划名称
        </td>
        <td width="30%" class="input">
			<html:text property="name"  disabled="true" maxlength="50" onblur=""   onkeydown="nextFocus('jobname')" size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
          <tr>
        <td width="20%" class="label">
              调度对象
        </td>
        <td width="30%" class="input">
            <html:hidden property="tsysjobid"/>
            <html:text property="jobname" maxlength="20" disabled="true"  onkeydown="nextFocus('indexno')"  size="20"   styleClass="field"/>  
           
                  
        </td>
                 <td width="20%" class="label">
            优先级
        </td>
        <td width="30%" class="input">
			<html:text property="indexno"  maxlength="20" onblur=""  disabled="true" onkeydown="nextFocus('memo')" size="20"   styleClass="field"/>
        </td>     
        
              </tr>		  
          <tr>
                           <td  width="20%" class="label" >停止使用</td>
         <td  width="30%"  class="input"><html:checkbox property="stopflag" disabled="true" value="1"/></td>  
         
        <td width="20%" class="label">
            调度频率
        </td>
        <td width="30%" class="input">
            <html:radio property="fnflag" disabled="true" onclick="showDiv()" value="0"/>每天
            <html:radio property="fnflag" disabled="true" onclick="showDiv()" value="1"/>每周
            <html:radio property="fnflag" disabled="true" onclick="showDiv()" value="2"/>每月        
        </td>
        
              </tr>		 
	  
          <tr>
        <td width="20%" class="label">
            自定义属性1
        </td>
        <td width="30%" class="input">
            <html:text property="add1" maxlength="50"     disabled="true"   size="20"   styleClass="field"/>        
        </td>
                <td width="20%" class="label">
            自定义属性2
        </td>
        <td width="30%" class="input">
            <html:text property="add2" maxlength="50"   disabled="true"     size="20"   styleClass="field"/>        
        </td>
              </tr>	
       
                                         
          <tr>
        <td width="20%" class="label">
            备注
        </td>
        <td width="30%" class="input" colspan="3">
            <html:text property="memo" maxlength="50"  disabled="true"    size="20"   styleClass="field"/>        
        </td>
        
              </tr>		               
     </table>
    
     

	


 <div id="daydiv">
    <table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	   <tr class="titleRow" >
		  <td><span>每日</span></td>
	  </tr>
   	  <tr>
        <td width="100%" class="input">
              <html:radio property="dayfnflag" value="0"/>一次发生于
             <html:text property="onlyonehour" size="5" disabled="true" onblur="isInt()"   styleClass="field"/>小时
             <html:text property="onlyonemin" size="5" disabled="true" onblur="isInt()"  styleClass="field"/>分
        </td>
      </tr>	
   	  <tr>
        <td width="100%" class="input" >
         <html:radio property="dayfnflag" value="1"/>发生频率从
             <html:text property="manybeginhour" size="5" onblur="isInt()" disabled="true"  styleClass="field"/>小时
             <html:text property="manybeginmin" size="5" onblur="isInt()"  disabled="true" styleClass="field"/>分开始,到
             <html:text property="manyendhour" size="5" onblur="isInt()"  disabled="true" styleClass="field"/>小时
             <html:text property="manyendmin" size="5" onblur="isInt()" disabled="true"  styleClass="field"/>分结束,每
             <html:text property="hourminindex" size="5" onblur="isInt()"  disabled="true"  styleClass="field"/>
             <html:select disabled="true" property="hourminflag">
                 <html:option value="0">分钟</html:option>
                 <html:option value="1">小时</html:option>
             </html:select>
             执行一次
        </td>
      </tr>	      
	</table>
	</div>
	
	
     <div id="weekdiv">
    <table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<tr class="titleRow" >
		<td ><span>每周</span></td>
	</tr>
	<tr>
        
        <td width="100%" class="input">
              <html:checkbox property="sunday"  disabled="true"  value="1"/>星期日
              <html:checkbox property="monday"    value="2"/>星期一
              <html:checkbox property="tuesday"   value="3"/>星期二
              <html:checkbox property="wednesday" value="4"/>星期三
              <html:checkbox property="thursday"  value="5"/>星期四
              <html:checkbox property="friday"    value="6"/>星期五
              <html:checkbox property="saturday"  value="7"/>星期六  
        </td>
    </tr>	
	</table>
	</div>
	
	<div id="monthdiv">
	    <table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<tr class="titleRow" >
		<td><span>每月</span></td>
	</tr>
	 <tr>
        <td width="100%" class="input">
          <html:radio property="monthflag" disabled="true" value="0"/>
           第<html:text property="dayindex" size="5" onblur="isInt()"  disabled="true" styleClass="field"/>天
        </td>
      </tr>	   
	 <tr>
        <td width="100%" class="input">
          <html:radio property="monthflag" disabled="true" value="1"/>
           第<html:text property="weekindex" size="5" onblur="isInt()" disabled="true"  styleClass="field"/>周
             <html:select disabled="true" property="weekweekindex">
                 <html:option value="1">星期日</html:option>
                 <html:option value="2">星期一</html:option>
                 <html:option value="3">星期二</html:option>
                 <html:option value="4">星期三</html:option>
                 <html:option value="5">星期四</html:option>
                 <html:option value="6">星期五</html:option>
                 <html:option value="7">星期六</html:option>
             </html:select>
        </td>
      </tr>	          
	</table>
	</div>
	
	
</div>


</html:form>
<script language="javascript">
function showDiv(){
  var src=document.all.item("fnflag");
  if(src[0].checked){
    var monthdiv = document.all.item("monthdiv");
     monthdiv.style.display = "none";
     var weekdiv = document.all.item("weekdiv");
     weekdiv.style.display = "none";
  
  }
  if(src[2].checked){
     var monthdiv = document.all.item("monthdiv");
     monthdiv.style.display = "";
     var weekdiv = document.all.item("weekdiv");
     weekdiv.style.display = "none";
  }
  if(src[1].checked){
     var monthdiv = document.all.item("monthdiv");
     monthdiv.style.display = "none";
     var weekdiv = document.all.item("weekdiv");
     weekdiv.style.display = "";
  }
} 
showDiv();
</script>
</body>
</html>	
