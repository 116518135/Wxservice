function trim(str) {
  var i;
  for (i=str.length-1; i>=0 && str.charAt(i)==' '; i--);
  if (i != str.length-1) str=str.slice(0,i+1);
  for (i=0; i<str.length && str.charAt(i)==' '; i++);
  if (i != 0) str = str.slice(i,str.length);
  return str;
}

function Round(Num , Bit) 
{ 
  
     digit = Math.round(Num * Math.pow (10 , Bit)) / Math.pow(10 , Bit);
	 if(String(digit).indexOf(".")==-1){
		 strZero = "0";
		 for(i=1;i<Bit;i++){
			 strZero = strZero +"0";
		 }
		 digit =digit+"."+strZero;
	 } else {
		 inde = String(digit).indexOf(".");
		 strDigit = String(digit).substring(inde,String(digit).length);
		 len = strDigit.length-1;
		 if(len<Bit){
		   strZero= "0";
		   for(i=1;i<Bit-len;i++){
				 strZero = strZero + "0";
			 }
			 digit = digit + strZero;
		 }
	 }
	 return digit;
}      

function nextFocus(next){
    var key=window.event.keyCode;
    
	if(key==13){
	   var obj=document.getElementsByName(next)[0];
	   obj.focus();
	   /* 
       var obj=document.all.item(next);
	   if(isNaN(obj.length) || obj.tagName=='SELECT'){
	     obj.focus();
	   }else{
         obj[0].focus();
       }
       */
    }
}


function ValidNum_ext(min,max){
  var str = trim(window.event.srcElement.value);
  var bool=false;
  if (str.length==0) return true;
  var digits = ".0123456789";
  for (var i=0; i < str.length; i++){
    if (digits.indexOf(str.charAt(i)) == -1) {
      bool=true;
	  break;
    }
  }

  if(bool){
	  alert("输入的数字格式错误.");
	  window.event.srcElement.value="1";
      window.event.srcElement.focus(window.event.srcElement.value);
	  return;
  }
  var tempvalue = parseFloat(str);
 
  if ((tempvalue < min) || (tempvalue > max))
  {
	  alert("输入的值不在范围之内.["+min+"-"+max+"]");
	  window.event.srcElement.value="1";
      window.event.srcElement.focus(window.event.srcElement.value);
	  return; 
  }
}

function validNum(min,max,str){
  var bool=false;
  if (str.length==0) return true;
  var digits = ".0123456789";
  for (var i=0; i < str.length; i++){
    if (digits.indexOf(str.charAt(i)) == -1) {
      return false;
    }
  }
  var tempvalue = parseFloat(str);
 
  if (tempvalue < min || tempvalue > max)
  {
	  return false;
  }
  return true;
}
//是否为正数
function isInt() {
  var str = trim(window.event.srcElement.value);
  var bool=false;
  if (str.length==0) return true;
  var digits = "0123456789";
  for (var i=0; i < str.length; i++){
    if (digits.indexOf(str.charAt(i)) == -1) {
      bool=true;
	  break;
    }
  }

  if(bool){
	  alert("输入的正数格式错误.");
      window.event.srcElement.focus();
  }
}
//是否为整数
function isNum() {
  var str = trim(window.event.srcElement.value);
  var bool=false;
  if (str.length==0) return true;
  var digits = "-0123456789";
  for (var i=0; i < str.length; i++){
    if (digits.indexOf(str.charAt(i)) == -1) {
      bool=true;
	  break;
    }
  }

  if(bool){
	  alert("输入的整数格式错误.");
      window.event.srcElement.focus();
  }
}
//是否为实数
function isReal() {
  var str = trim(window.event.srcElement.value);
  var bool=false;
  if (str.length==0) return true;
  var digits = "-.0123456789";
  for (var i=0; i < str.length; i++){
    if (digits.indexOf(str.charAt(i)) == -1) {
      bool=true;
	  break;
    }
  }

  if(bool){
	  alert("输入的实数格式错误.");
      window.event.srcElement.focus();
  }
}
//是否为正数
function isPlus(value) {
  var str = trim(value);
  var bool=true;
  if (str.length==0) return true;
  var digits = ".0123456789";
  for (var i=0; i < str.length; i++){
    if (digits.indexOf(str.charAt(i)) == -1) {
      bool=false;
	  break;
    }
  }
	return bool;
}

function isAllInt(value) {
  var str = trim(value);
  var bool=true;
  if (str.length==0) return true;
  var digits = "-0123456789";
  for (var i=0; i < str.length; i++){
    if (digits.indexOf(str.charAt(i)) == -1) {
      bool=false;
	  break;
    }
  }
	return bool;
}




 function isdate(strDate){
   var strSeparator = "/"; //日期分隔符
   var strDateArray;
   var intYear;
   var intMonth;
   var intDay;
   var boolLeapYear;
   
   strDateArray = strDate.split(strSeparator);
   
   
   if(strDateArray.length!=3) return false;
   
   intYear = parseInt(strDateArray[0],10);
   intMonth = parseInt(strDateArray[1],10);
   intDay = parseInt(strDateArray[2],10);
   if(intYear>9999 || intYear<1000) return false;
   if(isNaN(intYear)||isNaN(intMonth)||isNaN(intDay)) return false;
   
   if(intMonth>12||intMonth<1) return false;
   
   if((intMonth==1||intMonth==3||intMonth==5||intMonth==7||intMonth==8||intMonth==10||intMonth==12)&&(intDay>31||intDay<1)) return false;
   
   if((intMonth==4||intMonth==6||intMonth==9||intMonth==11)&&(intDay>30||intDay<1)) return false;
   
   if(intMonth==2){
      if(intDay<1) return false;
      
      boolLeapYear = false;
      if((intYear%100)==0){
         if((intYear%400)==0) boolLeapYear = true;
      }
      else{
         if((intYear%4)==0) boolLeapYear = true;
      }
      
      if(boolLeapYear){
         if(intDay>29) return false;
      }
      else{
         if(intDay>28) return false;
      }
   }
   return true;
}
//检查日期
function checkDate(s_date,isblank){
  var strDate=s_date.value;
  if(isblank){//日期允许为空
     if(strDate!=''){
       if(!isdate(s_date.value)){
         alert("输入的日期格式错误.");
		 s_date.focus();
       }
     }
  }else{//日期不允许为空
       if(!isdate(s_date.value)){
         alert("输入的日期格式错误.");
		 s_date.focus();
       }
  }
}


 function ismonth(strDate){
   var strSeparator = "/"; //日期分隔符
   var strDateArray;
   var intYear;
   var intMonth;
   var intDay;
   var boolLeapYear;
   
   strDateArray = strDate.split(strSeparator);
   
   if(strDateArray.length!=2) return false;
   
   intYear = parseInt(strDateArray[0],10);
   if(intYear>9999 || intYear<1000) return false;
   intMonth = parseInt(strDateArray[1],10);
   if(isNaN(intYear)||isNaN(intMonth)) return false;
   
   if(intMonth>12||intMonth<1) return false;
   
   return true;
}

function checkMonth(s_date,isblank){
  var strDate=s_date.value;
  if(isblank){//日期允许为空
     if(strDate!=''){
       if(!ismonth(s_date.value)){
         alert("输入的日期格式错误.");
		 s_date.focus();
       }
     }
  }else{//日期不允许为空
       if(!ismonth(s_date.value)){
         alert("输入的日期格式错误.");
		 s_date.focus();
       }
  }

  
}

function email() {
  var emailStr = trim(window.event.srcElement.value);
  if (emailStr.length == 0) {
    return true;
  }
  var emailPat=/^(.+)@(.+)$/;
  var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
  var validChars="\[^\\s" + specialChars + "\]";
  var quotedUser="(\"[^\"]*\")";
  var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
  var atom=validChars + '+';
  var word="(" + atom + "|" + quotedUser + ")";
  var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
  var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
  var matchArray=emailStr.match(emailPat);
  if (matchArray == null) {
    return false;
  }
  var user=matchArray[1];
  var domain=matchArray[2];
  if (user.match(userPat) == null) {
    return false;
  }
  var IPArray = domain.match(ipDomainPat);
  if (IPArray != null) {
    for (var i = 1; i <= 4; i++) {
      if (IPArray[i] > 255) {
        return false;
      }
    }
    return true;
  }
  var domainArray=domain.match(domainPat);
  if (domainArray == null) {
    return false;
  }
  var atomPat=new RegExp(atom,"g");
  var domArr=domain.match(atomPat);
  var len=domArr.length;
  if ((domArr[domArr.length-1].length < 2) ||(domArr[domArr.length-1].length > 3)) {
     return false;
  }
  if (len < 2) {
     return false;
  }
  return true;
}

function isEmail() {
  if(!email()){
    alert("输入Email格式错误.");
    window.event.srcElement.focus();
  }
}


function isTel() {
  str = trim(window.event.srcElement.value);
  var bool=false;
  if (str.length==0) return true;
  var digits = "0123456789()-";
  for (var i=0; i < str.length; i++){
    if (digits.indexOf(str.charAt(i)) == -1) {
      bool=true;
	    break;
    }
  }
  if(bool){
	  alert("输入的电话格式错误");
      window.event.srcElement.focus();
  }

}



 function validateLength(maxLength){
     
	 var swap=window.event.srcElement;
	 var value=swap.value;
	 if(value.length>maxLength){
        alert("可输入的最大长度为："+"["+maxLength +"]");
		swap.focus(); 
     } 
	 
 }
 
 function isCode() {
  str = trim(window.event.srcElement.value);
  var bool=false;
  if (str.length==0) return true;
  var digits = "-~!@#$%^&*()*0123456789_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  for (var i=0; i < str.length; i++){
    if (digits.indexOf(str.charAt(i)) == -1) {
      bool=true;
	    break;
    }
  }
  if(bool){
	  alert("输入的编码格式错误.");
      window.event.srcElement.focus();
  }

}

