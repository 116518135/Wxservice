<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@ include file="/include/ext2js.jsp"%>
<script language="javascript">
	//元素间的移动
	function selectMovesub(source) {
		var obj = document.all.item(source);
		var index = obj.selectedIndex;
		
		var selectValue = obj.options[index].value; //显示value 
		var selectValue2 = obj.options[index].innerText; //显示显示的值 具体内容
		var obj2 = document.all.item('subband');
		//如果有，则不加
		var flag = false;
		for ( var i = 0; i < obj2.length; i++) {
			if (obj2.options[i].value == selectValue) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			obj2.options.add(new Option(selectValue2, selectValue));
		}
	}
	function selectMovesubAll(source){
		var obj = document.all.item(source);
		var obj2 = document.all.item('subband');
		for(var index = 0;index<obj.options.length;index++){
			var selectValue = obj.options[index].value; //显示value 
			var selectValue2 = obj.options[index].innerText; //显示显示的值 具体内容
			//如果有，则不加
			var flag = false;
			for ( var i = 0; i < obj2.length; i++) {
				if (obj2.options[i].value == selectValue) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				obj2.options.add(new Option(selectValue2, selectValue));
			}
		}
	}
	function subRemove() {
		var obj = document.all.item('subband');
		var index = obj.selectedIndex;
		obj.options.remove(index);
	}
	function subRemoveAll(){
		var obj = document.all.item('subband');
		obj.length = 0;
	}
	//元素间的移动结束
	function getValues(obj) {
		var rowband = '';
		for ( var i = 0; i < obj.options.length; i++) {
			rowband += obj.options[i].value;
			if (i != obj.options.length - 1) {
				rowband += '+';
			}
		}
		return rowband;
	}
	function getValues2(obj) {
		var rowband = '';
		for ( var i = 0; i < obj.options.length; i++) {
			rowband += obj.options[i].innerText;
			if (i != obj.options.length - 1) {
				rowband += '+';
			}
		}
		return rowband;
	}
	function reFresh() {
		var obj1 = document.all.item('subband');
		var collectnames = top.multiname;
		var collect = top.multivalue;
		collectnames.value = getValues2(obj1);
		collect.value = getValues(obj1);
		if(collectnames.value==''||collect.value==''){
			alert("请至少选择一项");
			return;
		}
		top.close();
	}
</script>
<body style="margin: 10px; background-color: #BCD2EE">
	<html:form action="/trpreport.do" token="TOKEN_LINE" method="post">
		<html:hidden property="method" />
		<html:hidden property="moduleTitle" />
		<html:hidden property="trpreportid" />
		<html:hidden property="databands" />
		<html:hidden property="subbands" />
		<table border="0" cellspacing="0" cellpadding="0" class="tableRegion"
			width="100%">
			<tr class="titleRow">
				<td align="right"> 
					 <ucard:Button invoke=""
						onclick="javascript:reFresh()" icon="icon-edit" value="选择返回" />&nbsp;</td>
			</tr>
		</table>
		<div class="eXtremeTable">
			<table id="ec_table" border="0" cellspacing="0" cellpadding="0"
				class="tableRegion" width="100%">
				<thead>
					<tr class="titleRow">
						<td colspan="3"><span>设置模板 </span>
						</td>
					</tr>
				</thead>
			</table>
		</div>
		<div id="tabs1">
				<div id="tab1" class="x-hide-display">
					<%@ include file="collecttab.jsp"%>
				</div>
			</div>
	</html:form>
</body>
<script language="javascript">
Ext.onReady(function() {
	var tabs = new Ext.TabPanel( {
		id : "tabPanel",
		renderTo : 'tabs1',
		activeTab : 0,
		autoWidth : true,
		autoheight : true,
		defaults : {
			autoScroll : true
		},
		items : [ {
			contentEl : 'tab1',
			title : '基本数据区'
		}
		 ]
					 
	});
});
</script>
</html>