//输入编号返回名称
function codeEnter(method,mapping,querystring,nextjsname){
    var key=window.event.keyCode;
    if(key==13){
       var code = trim(window.event.srcElement.value);
       if(code=='') return;
       var jsname=window.event.srcElement.name;
       var url=getPath()+"/ajax.do?method="+method;
       url=url+'&code='+code;
       url=url+'&jsname='+jsname;
       url=url+'&mapping='+mapping;
       url=url+'&'+querystring;
       url=url+'&nextjsname='+nextjsname;
        $.get(url,{},codeEnter_callback);
    }
}

 function codeEnter_callback(data, textStatus){
    var form=document.forms[0];
    var results = eval("(" + data + ")");
    if(results.result=="1"){
        var transMap=results.mapping;
        var arGroup   = transMap.substring(0,transMap.length).split("|");
        var arMap;
        for (i = 0; i < arGroup.length; i++) {
              arMap = arGroup[i].split(",");
              var idnodeName = arMap[0];
              var srcnodeName = arMap[1];
              var v=eval('results.'+srcnodeName);
              var el=document.all.item(idnodeName);
              if(el!=null && el!='undefined'){
                 el.value=v;
              }
              var nextel=document.all.item(results.nextjsname);
              if(nextel!=null && nextel!='undefined'){
                 nextel.focus();
              }
        }       
      
    }else{
       alert(results.message);
       cleanValue(results);
       var input=document.all.item(results.jsname);
       if(input!=null && input!='undefined'){
          input.value='';
          input.focus();
       }
    }
  }
  
  function codeEnter1(method,mapping,querystring,nextjsname){
    var key=window.event.keyCode;
    if(key==13){
       var code = trim(window.event.srcElement.value);
       if(code=='') return;
       var jsname=window.event.srcElement.name;
       var url=getPath()+"/ajax.do?method="+method;
       url=url+'&code='+code;
       url=url+'&jsname='+jsname;
       url=url+'&mapping='+mapping;
       url=url+'&'+querystring;
       url=url+'&nextjsname='+nextjsname;
       new Ajax.Request(url,{method: 'post',onComplete: codeEnter_callback1});
    }
}

 function codeEnter_callback1(originalRequest){
    var form=document.forms[0];
    var jsonContent = originalRequest.responseText;
    var results = eval("(" + jsonContent + ")");
    if(results.result=="1"){
        var transMap=results.mapping;
        var arGroup   = transMap.substring(0,transMap.length).split("|");
        var arMap;
        for (i = 0; i < arGroup.length; i++) {
              arMap = arGroup[i].split(",");
              var idnodeName = arMap[0];
              var srcnodeName = arMap[1];
              var v=eval('results.'+srcnodeName);
              var el=document.all.item(idnodeName);
              if(el!=null && el!='undefined'){
                 el.value=v;
              }
              var nextel=document.all.item(results.nextjsname);
              if(nextel!=null && nextel!='undefined'){
                 nextel.focus();
              }
        }        
   
    }else{
       alert(results.message);
       cleanValue(results);
       var input=document.all.item(results.jsname);
       if(input!=null && input!='undefined'){
          input.value='';
          input.focus();
       }
    }
  }
  
  function cleanValue(results){
        var transMap=results.mapping;
        var arGroup   = transMap.substring(0,transMap.length).split("|");
        var arMap;
        for (i = 0; i < arGroup.length; i++) {
              arMap = arGroup[i].split(",");
              var idnodeName = arMap[0];
              var el=document.all.item(idnodeName);
              if(el!=null && el!='undefined'){
                 el.value="";
              }
        }      
  }