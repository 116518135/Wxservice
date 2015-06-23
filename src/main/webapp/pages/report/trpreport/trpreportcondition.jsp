<%@ page contentType="text/html;charset=UTF-8"%>
<ec:table items="trpreportcondition"  action="${ctx}/trpreport.do"   width="100%" form="TrpreportForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item" showPagination="false" showStatusBar="true">
	  <ec:row>
		  		  <ec:column  property="name" title="名称" filterable="true" pkid="trpreportconditionid" 
		  		  javascriptname="conditionview" />
          		  <ec:column  property="rowindex" title="行" filterable="true" />
          		  <ec:column  property="colindex" title="列" filterable="true" />
          		  <ec:column  property="width" title="宽度" filterable="true" />
          	  </ec:row>
  </ec:table>
  <script language="javascript">
  function conditionview(pkid){
	     var action="trpreportcondition.do";
	     var querystring='?method=view&trpreportconditionid='+pkid;
	     var url=action+querystring;
	     var title="条件";
	     openSubWindow600(url,title);
	  }
  </script>