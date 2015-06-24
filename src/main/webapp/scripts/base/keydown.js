
//回车，移到下一个焦点事件
function nextFocus(next){
    var key=window.event.keyCode;
    
	if(key==13){
	   var obj=document.getElementsByName(next)[0];
	   obj.focus();
    }
}


//  上：38   
//  下：40   
//  左：37   
//  右：39
   function nextArray(next){
    var key=window.event.keyCode;
	if(key==13){
       var array=document.getElementsByName(next);
	   var src=window.event.srcElement;
         for(var i=0;i<array.length;i++){
         if(src==array[i]){
			  if(i<array.length-1){
                 var next=array[i+1];
                 next.focus();
				 break;
              }
         }
       }
    }

    if(tablerow=='undefined' || tablecol=='undefined' || tablerow==null || tablecol==null || tablerow==0 || tablecol==0){
      return;
    }
   
    var array=document.all.item(next);
	var src=window.event.srcElement;
    if(key==38){  //上
	   if(isNaN(array.length)){
	     src.focus();
	   }else{
         for(var i=0;i<array.length;i++){
           if(src==array[i]){
              var iRow=Math.floor(i/tablecol);
              if(iRow==0){
                return;
              }
              var iCol=i%tablecol;
              var idx=(iRow-1)*tablecol+iCol;
              var next=array[idx];
              next.focus();
           }
         
		 }
       }       
    }
    
   if(key==40){  //下
	   if(isNaN(array.length)){
	     src.focus();
	   }else{
         for(var i=0;i<array.length;i++){
           if(src==array[i]){
              var iRow=Math.floor(i/tablecol);
              if(iRow==tablerow-1){
                return;
              }
              var iCol=i%tablecol;
              var idx=(iRow+1)*tablecol+iCol;
              var next=array[idx];
              next.focus();
           }
         
		 }
       }       
    }    
    
   if(key==37){  //左
	   if(isNaN(array.length)){
	     src.focus();
	   }else{
         for(var i=0;i<array.length;i++){
           if(src==array[i]){
              var iCol=Math.floor(i%tablecol);
              if(iCol==0){
                return;
              }
              var idx=i-1;
              var next=array[idx];
              next.focus();
           }
         
		 }
       }       
    }    
    
   if(key==39){  //右
	   if(isNaN(array.length)){
	     src.focus();
	   }else{
         for(var i=0;i<array.length;i++){
           if(src==array[i]){
              var iCol=Math.floor(i%tablecol);
              if(iCol==tablecol-1){
                return;
              }
              var idx=i+1;
              var next=array[idx];
              next.focus();
           }
         
		 }
       }       
    }   
 
    
      
}
 