<%@ page   isELIgnored="false"%>
<%@ include file="/commons/taglibs.jsp" %>
<%@ include file="/commons/vars.jsp" %>
<%@ include file="/commons/meta.jsp" %>
<%@ include file="/include/button.jsp" %>
<%@ include file="/widgets/calendar/calendar.jsp" %>
<%@ include file="/commons/messages.jsp" %>
<%@ include file="/include/dialog.jsp" %>
<link href="${ctx}/widgets/extremecomponents/extremecomponents.css?v=18" type="text/css" rel="stylesheet">
<link href="${ctx}/styles/esk.css?v=4" type="text/css" rel="stylesheet">
<script src="<c:url value="/scripts/base/code2name.js?v=16"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/base/find.js?v=2"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/base/select.js?v=12"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/base/keydown.js?v=5"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/base/function.js?v=4"/>" type="text/javascript"></script>
<script src="<c:url value="/scripts/base/form.js?v=1"/>" type="text/javascript"></script>
<script language="javascript">
   function getPath(){
     return "${ctx}";
   }
   function getSpringname(){
     return "${struts_form.service}";
   }
   function getAmtround(){
     return "${SYSTEM_ClIENTSESSION.amtRound}";
   }
   function getPriceround(){
     return "${SYSTEM_ClIENTSESSION.priceRound}";
   }
   function getDiscountround(){
     return "${SYSTEM_ClIENTSESSION.discountRound}";
   }
   
   
</script>


