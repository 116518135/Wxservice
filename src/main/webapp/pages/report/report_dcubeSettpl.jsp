<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<%@ include file="/include/ext2js.jsp"%>
<script language="javascript">
	//元素间的移动
	function selectMove(source, end) {
		var obj = document.all.item(source);
		var index = obj.selectedIndex;
		var selectValue = obj.options[index].value; //显示value 
		var selectValue2 = obj.options[index].innerText; //显示显示的值 具体内容
		var obj2 = document.all.item(end);
		obj2.options.add(new Option(selectValue2, selectValue));
		obj.options.remove(index);
	}
	function selectMoveAll(source, end) {
		var obj = document.all.item(source);
		var obj2 = document.all.item(end);
		for(var i = 0;i<obj.options.length;i++){
			var selectValue = obj.options[i].value; //显示value 
			var selectValue2 = obj.options[i].innerText; //显示显示的值 具体内容
			obj2.options.add(new Option(selectValue2, selectValue));
		}
		obj.options.length=0;
	}
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
	//按钮
	function save(index) {
		if (document.all.item('name').value == '') {
			alert('名称不能为空');
		} else {
			var form = document.forms[0];
			var obj1 = document.all.item('rowband');
			var obj2 = document.all.item('colband');
			var obj3 = document.all.item('pageband');
			var obj4 = document.all.item('databand');
			var obj5 = document.all.item('hiddenband');
			var obj6 = document.all.item('subband');
			form.rowbands.value = getValues(obj1);
			form.colbands.value = getValues(obj2);
			form.pagebands.value = getValues(obj3);
			form.databands.value = getValues(obj4);
			form.hiddenbands.value = getValues(obj5);
			form.subbands.value = getValues(obj6);
			if (index == 2 || document.all.item('trpdcubetplid').value == '0') {
				form.method.value = 'addsaveTemplate';
			}
			form.submit();
		}
	}
	function getValues(obj) {
		var rowband = '';
		for ( var i = 0; i < obj.options.length; i++) {
			rowband += obj.options[i].value;
			if (i != obj.options.length - 1) {
				rowband += ',';
			}
		}
		return rowband;
	}
	function reFresh() {
		var obj1 = document.all.item('rowband');
		var obj2 = document.all.item('colband');
		var obj3 = document.all.item('pageband');
		var obj5 = document.all.item('hiddenband');
		var obj6 = document.all.item('subband');
		var showsubflag1 = document.all.item('showsubflag');
		var colbands = top.colbands;
		var rowbands = top.rowbands;
		var hiddenbands = top.hiddenbands;
		var subbands = top.subbands;
		var pagebands = top.pagebands;
		var allbands = top.allbands;
		var showsubflag = top.showsubflag;
		var trpdcubetplid = top.trpdcubetplid;
		colbands.value = getValues(obj2);
		rowbands.value = getValues(obj1);
		hiddenbands.value = getValues(obj5);
		pagebands.value = getValues(obj3);
		subbands.value = getValues(obj6);
		trpdcubetplid.value = document.all.item('trpdcubetplid').value;
		showsubflag.value = showsubflag1.checked;
		allbands.value = Math.floor(Math.random() * 999999 + 1);
		top.close();
	}
	window.onload = function() {
		//var header = top.headerdata;
		var val = top.headerdata;
		var alldata = '';
		var totaldata='';
		for ( var i = 0; i < val.length; i++) {
			var dd = val[i];
			if (dd.dimvalueflag == 3) {
				alldata += dd.field;
				alldata += ',';
			}else{
				totaldata += dd.field;
				totaldata += ',';
			}
		}
		alldata = alldata.substring(0, alldata.length - 1);
		totaldata = totaldata.substring(0, totaldata.length - 1);
		setOptions(totaldata,document.all.item('totalband'), val);
		
		var obj1 = document.all.item('rowband');
		var val1 = top.rowbands.value;
		setOptions(val1, obj1, val);
		var obj2 = document.all.item('colband');
		var val2 = top.colbands.value;
		setOptions(val2, obj2, val);
		var obj3 = document.all.item('pageband');
		var val3 = top.pagebands.value;
		setOptions(val3, obj3, val);
		var obj4 = document.all.item('hiddenband');
		var val4 = top.hiddenbands.value;
		setOptions(val4, obj4, val);
		var obj5 = document.all.item('databand');
		var val5 = top.databands.value;
		setOptions(val5, obj5, val);
		var obj6 = document.all.item('subband');
		var val6 = top.subbands.value;
		setOptions(val6, obj6, val);
		if(top.showsubflag.value == 'true'){
			document.all.item('showsubflag').checked = true;
		}else{
			document.all.item('showsubflag').checked = false;
		}
	};
	function setOptions(val1, obj1, val) {
		if (val1 != '') {
			var arr1 = val1.split(',');
			for ( var j = 0; j < arr1.length; j++) {
				for ( var i = 0; i < val.length; i++) {
					var dd = val[i];
					if (dd.field == arr1[j]) {
						obj1.options.add(new Option(dd.title, dd.field));
					}
				}
			}
		}
	}
</script>
<body style="margin: 10px; background-color: #BCD2EE">
	<html:form action="/trpreport.do" token="TOKEN_LINE" method="post">
		<html:hidden property="method" />
		<html:hidden property="moduleTitle" />
		<html:hidden property="trpreportid" />
		<html:hidden property="rowbands" />
		<html:hidden property="colbands" />
		<html:hidden property="pagebands" />
		<html:hidden property="databands" />
		<html:hidden property="hiddenbands" />
		<html:hidden property="subbands" />
		<html:hidden property="trpdcubetplid" />
		<html:hidden property="code" />
		<html:hidden property="activeTab" />
		<table border="0" cellspacing="0" cellpadding="0" class="tableRegion"
			width="100%">
			<tr class="titleRow">
				<td align="right"><ucard:Button value="增加" icon="icon-save"
						onclick="javascript:save(2)" /> <ucard:Button value="保存"
						icon="icon-save" hotkey='F10,121' onclick="javascript:save(1)" />
					<ucard:Button value=" 关闭 " icon="icon-edit" hotkey='ESC,27'
						onclick="javascript:top.close()" /> <ucard:Button invoke=""
						onclick="javascript:reFresh()" icon="icon-edit" value="刷新" />&nbsp;</td>
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
					<tr>
						<td width="45%" class="label"><font color='red'>*</font>名称</td>
						<td width="55%" class="input" colspan="2"><html:text
								property="name" maxlength="20" size="20" styleClass="field" />
							是否显示合计<html:checkbox property="showsubflag" value="1" /></td>
					</tr>
				</thead>
			</table>
		</div>
		<div id="tabs1">
				<div id="tab1" class="x-hide-display">
					<%@ include file="dcubetab1.jsp"%>
				</div>
				<div id="tab2" class="x-hide-display">
					<%@ include file="dcubetab2.jsp"%>
				</div>
				<div id="tab3" class="x-hide-display">
					<%@ include file="dcubetab3.jsp"%>
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
		}, {
			contentEl : 'tab2',
			title : '隐藏数据区'
		}, {
			contentEl : 'tab3',
			title : '合计数据区'
		}
		 ]
					 
	});
});
</script>
</html>