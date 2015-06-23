<%@ page contentType="text/html;charset=UTF-8"%>
<ec:table items="trpreportplugin"  action="${ctx}/trpreport.do"   width="100%" form="TrpreportForm"  rowsDisplayed="15"
            filterable="false"    autoIncludeParameters="false"  var="item" showPagination="false" >
	  <ec:row>
		  		  <ec:column  property="name" title="名称" filterable="true" pkid="trpreportpluginid" javascriptname="pluginview"/>
          		  <ec:column  property="springname" title="对象名" filterable="true" />
          	  </ec:row>
  </ec:table>
  <script language="javascript">
  function pluginview(pkid){
	    

	     var form="TrpreportpluginForm";
	     var title="插件";
	     var action="trpreportplugin.do";
	     var querystring='?method=view&trpreportpluginid='+pkid;
	     var url=action+querystring;
	     openSubWindow600(url,title);
	  }
  </script>