//检查有没有消息
var checkSms=function(){
   var url=getPath()+"/ajax.do?method=checkSms";
   Ext.Ajax.request({
             url: url, 
             params:"",  
             method: 'post',   
             callback: checkSms_callback 
     });
}
////检查有没有消息 的回调函数
var checkSms_callback =function(options, success, response){
     if(success){
         ctroltime=window.setTimeout("checkSms()",getSmsLoopTime());
         var jsonContent = response.responseText;
         var results = eval("(" + jsonContent + ")");
         if(results.result=='true'){
           document.getElementById("div_sound").innerHTML="<embed hidden='true' src='"+getPath()+"/images/sound.wav'/>";
           showInfomsg()
         }else{
           document.getElementById("div_sound").innerHTML="";
         }
     }
  }

 
 function showInfomsg()
{
   mytop=screen.availHeight-200;
   myleft=0;
   var url=getPath()+"/sendsms.do?method=showSms";
   window.open(url,"","height=160,width=370,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,top="+mytop+",left="+myleft+",resizable=yes");
}


//点我知道了调用的函数
function updateSms(dialog,sysid,taburl1){
   var url=getPath()+"/sendsms.do?method=showSms&tsyssmsid="+sysid;
   dialog.close();
   addSmsTab('smsid','消息详情',taburl1);
   Ext.Ajax.request({
             url: url, 
             params:"",  
             method: 'post',   
             callback: updateSms_callback 
     });

}

function updateSms_callback(){
     // addSmsTab('smsid','消息详情'，taburl);  
}
Ext.onReady(checkSms);
 
 