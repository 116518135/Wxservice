
  function getDirectory() {   
    try {   
        var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939";  //选择框提示信息   
        var Shell = new ActiveXObject("Shell.Application");   
        var Folder = Shell.BrowseForFolder(0, Message, 64, 17);//起始目录为：我的电脑   
        if (Folder != null) {   
            Folder = Folder.items();  // 返回 FolderItems 对象   
            Folder = Folder.item();  // 返回 Folderitem 对象   
            Folder = Folder.Path;   // 返回路径   
            if (Folder.charAt(Folder.length - 1) != "\\") {   
                Folder = Folder + "\\";   
            }   
            return Folder;   
        }   
    }   
    catch (e) {   
        alert(e.message);   
    }   
}  
  			
  function xslquery(){
        var DCube1 = document.getElementById("Dcube1");
	    if (DCube1.RowCount==0){ 
    		alert("没有数据可导出！");
			return;
		}
       	var fileName = getDirectory()+"\\"+document.all.item("reportid").value+".xls";
		var myApp = new ActiveXObject( "Excel.Application"); 
		DCube1.ExportToExcel(fileName, true,true, true, true);
  }
  
 function MSComDlg_CommonDialog(){
    if(!ActiveXObject)return;
    var obj=new ActiveXObject('MSComDlg.CommonDialog');
    var filename=false;
    try{
            obj.Filter='所有文件(*.*)';
            obj.FilterIndex = 1;
            obj.MaxFileSize = 255;
            if(cmd==0){
                obj.ShowSave();
            }else{
                obj.ShowOpen();
            }
        
        filename = obj.filename;
    }catch(e){alert(e.message);}
    return filename;
} 
  
  
  function getTemplate(){
 	   var TmpFileName=  getDirectory()+"\\"+document.all.item("reportid").value+".tpl";
 	   return TmpFileName;
  }
     function saveTemplate(){
			var SOLayout = 0; 
 			var TmpFileName= getTemplate();
 			var DCube1 = document.getElementById("Dcube1");
   			DCube1.Save(TmpFileName,SOLayout);
			alert("保存模板成功!"); 
	 }
	
	 
	 function loadTemplate(){
			document.all.item("fileselect").click();
			var DCube1 = document.getElementById("Dcube1");
			DCube1.Load(document.all.item("fileselect").value);
			DCube1.style.visibility="visible";
	 }

  function htmlquery(){
     var form=document.forms[0];
     var title=form.moduleTitle.value;
     var action=form.action;
     var querystring='?method=cacheHtml&reportid='+form.reportid.value;
     var url=action+querystring;
     winOpen(url);
  }  
  
  function readme(){
    var querystring='?forward=dcubehelp';
    var url=getPath()+"/report.do"+querystring;
    winOpen(url,520,300);
  }
  
  function isSetDimValue(){
     for(var i=0;i<header.length;i++){
         var dd=header[i];
         if(dd.dimvalueflag==1){
            return true;
         }
         
     }
     return false;
  }
   function show()
	{
        var DCube1 = document.getElementById("Dcube1");
	    DCube1.style.visibility="visible";
		var DCCT_UNBOUND=99;
		var DCFNone=0;
	    var DCFSum=1;
		var DCFAverage=2;
		var DCPage=4;
		var DCRow=2;
		var DCColumn=1;
		var DCData=3;
		var thisField;
		var  trCount;
		DCube1.DCConnectType = DCCT_UNBOUND;
		DCube1.Fields.DeleteAll();
		
        for(var i=0;i<header.length;i++){
           var dd=header[i];
           if(dd.dimvalueflag==1){
              thisField=DCube1.Fields.Add(dd.field, dd.title, DCData);
           }else{
              thisField=DCube1.Fields.Add(dd.field, dd.title, DCRow);
              if(i==0){
                thisField.GroupFooterCaption = "合计:";
              }else{
                thisField.GroupFooterVisible = false;
              }
           }
        }
      
	      DCube1.RefreshData();
	      DCube1.AutoDataRefresh = true;
	  	
	 }
