function query(){
   var form=document.forms[0];
   form.submit();
 }
 function addListValue(id,name){
    var chk=document.getElementsByName("checks");
	if(chk==null) return;
	var idObj=document.all.item(id);
	var nameObj=document.all.item(name);
       for(var i=0;i<chk.length;i++){
          if(chk[i].checked){
             if(trim(idObj.value)==''){
    			 idObj.value=chk[i].value; 
	    		 nameObj.value=chk[i].getAttribute("dataflag");
             }else{
    			 idObj.value=idObj.value+","+chk[i].value; 
	    		 nameObj.value=nameObj.value+","+chk[i].getAttribute("dataflag");

             } 
		  }
       } 
}

 function callReturn(){
	var form=document.forms[0];
     var multivalue=top.multivalue;
	 var multiname=top.multiname;

	  addListValue('multivalue','multiname');
	 multivalue.value=form.multivalue.value;
	 multiname.value=form.multiname.value;
	 top.close();
  }
  

  
  function allSelect(isAll){

    var select;
	if(isAll==0){
      select=false;
    }else{
      select=true;
    }
    var chk=document.getElementsByName("checks");
    for(var i=0;i<chk.length;i++){
       chk[i].checked=select;
    }   
  }
  
  
  	    function appendReturn() {
	var form = document.forms[0];
	var multivalue = top.multivalue;
	var multiname = top.multiname;

	addListValue('multivalue', 'multiname');
	if (multivalue.value == '') {
		multivalue.value = form.multivalue.value;
		multiname.value = form.multiname.value;
	} else {
		if(trim(form.multivalue.value)!=''){
			multivalue.value = multivalue.value + ',' + form.multivalue.value;
			multiname.value = multiname.value + ',' + form.multiname.value;
		}
	}

	var chk =document.getElementsByName("checks");
	if (chk == null)
		return;
	for ( var i = 0; i < chk.length; i++) {
		if (chk[i].checked) {
			chk[i].checked = false;
		}
	}
	form.multivalue.value='';
	form.multiname.value='';
	alert('追加完成');
	// top.close();
}
function colseReturn() {
	top.close();
}