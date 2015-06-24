function getCookie(name)
{
     var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
     if(arr != null) return unescape(arr[2]); return null;


}

function setCookie(name,value,expiredays)
{
    var exp  = new Date();    
    exp.setTime(exp.getTime() + expiredays*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();

}

function delCookie(name)//cookie
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}



var dstype_key="ESKdstype";
function setDstypeTxt(){
  var form=document.forms[0];
  var acc=form.dstype;
  if(acc!=undefined){
     var v=getCookie(dstype_key);
     if(v!=undefined){
        acc.value=v;
     }
  }
  
}
function setDstypeCookie(){
 
  var form=document.forms[0];
  var acc=form.dstype;
  if(acc!=undefined){
     setCookie(dstype_key,acc.value,90);
  }
  
}