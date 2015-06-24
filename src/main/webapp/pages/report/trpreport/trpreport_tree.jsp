<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/ext2js.jsp" %>
<html>
<head>
<title></title>
<script language="javascript">
Ext.onReady(function(){
var loadurl=getPath()+"/trpreport.do?method=json";
var rootNode = new Ext.tree.AsyncTreeNode({id:'0',href:getPath()+"/trpreport.do?method=list&parentid=0",hrefTarget:"rightFrame",text: '菜单',draggable:false });
var treePanel = new Ext.tree.TreePanel({   
            columnWidth:.33,   
            columnHeight:.100,   
            el:'tree-div',   
            collapsible: true,   
            margins:'5 0 5 5',   
            cmargins:'5 5 5 5',   
            rootVisible:true,   
            lines:true,                
            autoScroll:true,   
            collapseFirst:false,       
            loader:new Ext.tree.TreeLoader({   
                dataUrl:loadurl  
            })   
});  

treePanel.on('beforeload',function(node){           
            treePanel.loader.dataUrl = loadurl+"&id="+node.id;
});  
treePanel.setRootNode(rootNode);
rootNode.expand(false, true);
treePanel.render();
});
</script>

</head>
<body> 
<div id="tree-div" style="overflow:auto; height:100%;width:100%;border:0px solid #c3daf9;"></div>
</body>
</html>