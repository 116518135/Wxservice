  function print(){
             var form=document.forms[0];
	         var no=form.no[0].value;
             var action=form.action;
             action=action+form.inputtype.value;
             var printpath=action.substring(action.lastIndexOf("/")+1);
	         var where="no='"+no+"'"
	         var url =getPath()+"/print.do?method=print&where="
	          +where+"&printpath="+printpath;
	         winOpen(url,800,600,"_blank");
           
  }
  
    function baseprint(field){
          var value=document.all.item(field)[0].value;      
             var form=document.forms[0];
             var action=form.action;
             var printpath=action.substring(action.lastIndexOf("/")+1);
	         var where=field+"='"+value+"'"
	         var url =getPath()+"/print.do?method=print&where="
	          +where+"&printpath="+printpath;
	         winOpen(url,800,600,"_blank");
           
    }
    function baseprint_int(field){
          var value=document.all.item(field).value;      
             var form=document.forms[0];
             var action=form.action;
             var printpath=action.substring(action.lastIndexOf("/")+1);
	         var where=field+"="+value;
	         var url =getPath()+"/print.do?method=print&where="
	          +where+"&printpath="+printpath;
	         winOpen(url,800,600,"_blank");
           
    }
function submitForm(method){
   var form=document.forms[0];
   form.method.value=method;
   form.submit();
}
function submitFormConfirm(method,message){
   var form=document.forms[0];
   if(confirm(message+"?")){
     form.method.value=method;
     form.submit();
   }  
}


  function getFormQueryString(form){
  	var elements = doucment.forms[0].getElementsByTagName('input');
  	var querystring='';
  	for(var i=0;i<elements.length;i++){
  		var element=elements[i];
        var tagName=element.tagName;
  		var key = element.name
  		var value = element.value;
  		if(key=='method') continue;
  		if(key=='button') continue;
		querystring=querystring+"&"+encodeURIComponent(key)+"="+encodeURIComponent(value);
 	}
  	return querystring;
  }
  
    function getFirstElement(){
  	var elements =document.forms[0].getElementsByTagName('input');
  	var querystring='';
  	for(var i=0;i<elements.length;i++){
  		var element=elements[i];
        var type=element.type;
        var isreadonly=element.getAttribute("readonly")
        
        if(type=='text'){
        	if(isreadonly){
        		
        	}else{
        		return element;
        	}
        }
 	}
  	return null;
  }
  

    

function winOpen(url,sWidth,sHeight,sName) {
  if (sWidth == null ) sWidth  = screen.availWidth;
  if (sHeight == null) sHeight = screen.availHeight;
  if (sName  ==  null || sName=='undefined') sName   = "_blank";
  t = (screen.availHeight-sHeight-27) / 2 ;
  l = (screen.availWidth-sWidth) / 2 ;
  if (parseInt(sWidth) > screen.availWidth || sWidth == "full") {
    sWidth = screen.availWidth;
    l = 0
  }
  if (parseInt(sHeight) > screen.availHeight || sHeight == "full") {
    sHeight = screen.availHeight;
    t = 0;
  }
   return window.open(url,sName,"status=yes,width=" + sWidth + ",height=" + sHeight + ",top=" + t + ",left=" + l +",titlebar=no,status=no,scrollbars =yes,toolbar=no,menuar=no,location=no,resizable=yes,z-lock=yes");
}    



  
  
