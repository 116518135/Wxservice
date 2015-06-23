<%@ page contentType="text/html;charset=UTF-8"%>
<ec:table items="trpreporthead"  action="${ctx}/trpreport.do"   width="100%" form="TrpreportForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item" showPagination="false" 
             >
	  <ec:row>
		  		  <ec:column  property="title" title="标题" filterable="true" pkid="trpreportdtlid" javascriptname="headview"/>
          		  <ec:column  property="field" title="字段" filterable="true" />
          		  <ec:column  property="width" title="宽度" filterable="true" />
          		  <ec:column  property="fieldtype" title="字段类型" filterable="true" />       		  
          		  <ec:column  property="rowindex" title="行" filterable="true" />
          		  <ec:column  property="colindex" title="列" filterable="true" />
          	  </ec:row>
  </ec:table>
   <script language="javascript">
  function headview(pkid){
	     var action="trpreport.do";
	     var querystring='?method=viewLine&trpreportdtlid='+pkid;
	     var url=action+querystring;
	     var title="表头";
	     openSubWindow600(url,title);
	  }
  </script>