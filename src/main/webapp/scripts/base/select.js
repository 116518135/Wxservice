 //下面是单项选择的js
function OpenImportWindow(url,map, sWidth, sHeight) {//打开选择的窗口

  if (null == sWidth)  sWidth  = 640;  
  if (null == sHeight) sHeight = 480;
  var callWindow = winOpen(url, sWidth, sHeight, "_blank");
  callWindow.TOPMAP = map;
  callWindow.callerOnReturn = eval("onDialogReturn");
  callWindow.focus();
}
function onDialogReturn(rtObject, rtDialog) {  //关闭选择
  rtDialog.close();
  ImportData(rtObject);
}

function ImportData(rtObject) {               //导入数据
 
  var arGroup   = rtObject.substring(0,rtObject.length).split("|");
  var arMap;
  var strName = '';
  for (i = 0; i < arGroup.length; i++) {
    arMap = arGroup[i].split(",");
    for (j = 0; j < arMap.length; j=j+2) {
			var srcnodeName = arMap[j];
			document.all.item(srcnodeName).value=arMap[j+1];
			strName = srcnodeName;
			break;
     }
  }


}  

function returnSelect(chk){
  var map='';
  var msg ='';
  var nameTxt = '';
  var arValue='';
 
  var transMap = top.TOPMAP;
	 var obj=document.getElementsByName(chk);
  
	if(obj==null) 
	{
	    top.close();
		return;
    }
    
	var len = obj.length;
		for(k = 0;k<len;k++){
		  if(obj[k].checked){
            var arGroup   = transMap.substring(0,transMap.length).split("|");
            var arMap;
            for (i = 0; i < arGroup.length; i++) {
              arMap = arGroup[i].split(",");
              for(j=0;j<arMap.length;j=j+2){
                var idnodeName = arMap[j];
                var srcnodeName = arMap[j+1];
                nameTxt = srcnodeName;
                arValue =document.getElementsByName(nameTxt)[k].value;
                if(i == 0){
                  map = idnodeName + ',' + arValue;
                } else {
                  map = map + '|' + idnodeName + ',' + arValue;
                }
                 
                break;
              }
            }
	      }
		}
	if(map==''){
		alert('请选择要取回的数据!');
		return;
	} else {
	   returnSubmit(map);
	}
}


function returnSubmit(uecdata)
{ 
  top.callerOnReturn(uecdata, top);  
}





  
  
