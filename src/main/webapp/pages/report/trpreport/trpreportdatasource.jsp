<%@ page contentType="text/html;charset=UTF-8"%>
 <script language="javascript">
  function datasourceview(pkid){
	    

	     var form="TrpreportdatasourceForm";
	     var title="数据源";
	     var action="trpreportdatasource.do";
	     var querystring='?method=view&trpreportdatasourceid='+pkid;
	     var url=action+querystring;
	     openSubWindow600(url,title);
	  }
  </script>
<ec:table items="trpreportdatasource"  action="${ctx}/trpreport.do"   width="100%" form="TrpreportForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item" showPagination="false" >
	  <ec:row >
		  		  <ec:column  property="name" title="名称" filterable="true" pkid="trpreportdatasourceid" javascriptname="datasourceview"/>
          		  <ec:column  property="datasourcetype" title="类型" filterable="true" />
          	  </ec:row>
  </ec:table>
 