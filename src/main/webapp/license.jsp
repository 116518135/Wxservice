<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<script type="text/javascript" src="${ctx}/scripts/base/md5.js"></script>
<HTML>   
<HEAD><TITLE>client</TITLE>   
    <META http-equiv=Content-Type content="text/html; charset=gb2312">   
    <SCRIPT language=JScript event="OnCompleted(hResult,pErrorObject, pAsyncContext)" for=foo>   
        document.forms[0].txtCientNo.value = hex_md5(unescape(MACAddr));   
    </SCRIPT>   
  
    <SCRIPT language=JScript event=OnObjectReady(objObject,objAsyncContext) for=foo>   
        if (objObject.IPEnabled != null && objObject.IPEnabled != "undefined" && objObject.IPEnabled == true)   
        {   
            if (objObject.MACAddress != null && objObject.MACAddress != "undefined")   
                MACAddr = objObject.MACAddress;   
        }   
    </SCRIPT>   

</HEAD>   
<BODY>   
<OBJECT id=locator classid=CLSID:76A64158-CB41-11D1-8B02-00600806D9B6 VIEWASTEXT>   
</OBJECT>   
<OBJECT id=foo classid=CLSID:75718C9A-F029-11d1-A1AC-00C04FB6C223></OBJECT>   
<SCRIPT language=JScript>   
    var service = locator.ConnectServer();   
    var MACAddr ;   
    var IPAddr ;   
    var DomainAddr;   
    var sDNSName;   
    service.Security_.ImpersonationLevel = 3;   
    service.InstancesOfAsync(foo, 'Win32_NetworkAdapterConfiguration');   
</SCRIPT>   
<form>
<div class="eXtremeTable" >
<table id="ec_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<tr class="titleRow" >
		<td colspan="4" ><span>客户端序列号</span></td>
	</tr>
          <tr class="titleRow">
        
        <td width="80%" class="input" colspan="4">
             <INPUT name="txtCientNo" size="50" onfocus="select()" class="field"> 
        </td>
              </tr>	
       
     </table>
</div>
</form>
<FORM>   
</BODY>   
</HTML>  