   
function OpenFindWindow(url,sWidth, sHeight) {
  if (null == sWidth)  sWidth  = 640;  
  if (null == sHeight) sHeight = 480;
  var callWindow = winOpen(url, sWidth, sHeight, "_blank");
  callWindow.callerOnReturn = eval("onFindReturn");
  callWindow.focus();
}

function onFindReturn(findwhere,rtDialog,method) { 
  
  rtDialog.close();
  if(method==undefined){
      method="list";
  }
  form=document.forms[0];
  form.findwhere.value=findwhere;
  form.method.value=method;
  form.submit();
}
    
    

    
