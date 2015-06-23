<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title></title>
<style type="text/css"> 
html{height:100%}
body{height:100%;margin:0px;padding:0px}
#container{height:100%}
</style>
<script src="http://api.map.baidu.com/api?v=1.3" type="text/javascript"></script>

     <script type="text/javascript">
     function search(){
        
        var key=window.event.keyCode;
    	if(key==13){
    	   query();
        }
     }
         function query(){
           var search = new BMap.LocalSearch(map,{renderOptions:{map:map,autoViewport:true}}); 
           var city=document.all.item("city").value;
           search.search(city);  
           var result=search.getResults();
           map.setCurrentCity(city);
         }
         function callReturn(){
     	   window.lat.value=document.all.item("lat").value;
	       window.lng.value=document.all.item("lng").value;
	       window.close();
         }
       </script>
 
</head>
  <table border="0"  cellspacing="0"  cellpadding="0"  width="100%" >
	<tr class="titleRow" >
		<td align="right">
	        <input type="button" value="搜索" icon="icon-apply"  onclick="javascript:query()"/>
	        <input type="button" value="确定取回" icon="icon-apply"  onclick="javascript:callReturn()"/>		
		</td>
	</tr>
</table>

<table width="100%" bgcolor="gray" width="100%" style="table-layout:fixed;" border="0" cellspacing="0" cellpadding="0">
       <tr>
        <td width="20%" class="label">
            经度
        </td>
        <td width="30%" class="input">
             <input type="text" name="lng"   size="20"   class="field"/>
        <td width="20%" class="label">
            纬度
        </td>
        <td width="30%" class="input">
             <input type="text" name="lat"   size="20"   class="field"/>
        </td>
       </tr>		  
       <tr>
        <td width="20%" class="label">
             区域
        </td>
          <td width="80%" class="input" colspan="3">
             <input type="text" name="city" onkeydown="search()" value="北京"  size="20"   class="field"/>
                                   
          </td> 
       </tr>
</table>
<div style="width:100%;height:100%;border:1px solid gray" id="container"></div>
<script type="text/javascript"> 
// 编写自定义函数，创建标注   
function addMarker(point, index){   
  map.clearOverlays();//移动所有的mark
  var marker = new BMap.Marker(point);    
  /*
  var imgurl=getPath()+"/sandbox/02.png";
  var myIcon = new BMap.Icon(imgurl, new BMap.Size(23, 25), {   
      anchor: new BMap.Size(10, 25),   
      imageOffset: new BMap.Size(0, 0)   
    });         
  var marker = new BMap.Marker(point, {icon: myIcon});
  */   
  map.addOverlay(marker);   
}   
   var map = new BMap.Map("container");
   var point=new BMap.Point(118.979622, 26.253143);
   map.centerAndZoom(point, 5);
   map.enableScrollWheelZoom();
   map.addControl(new BMap.NavigationControl());
   map.addControl(new BMap.ScaleControl());
   map.addControl(new BMap.OverviewMapControl());
   map.addControl(new BMap.MapTypeControl());
   map.setCurrentCity("北京"); 
   map.addEventListener("click", function(e){   
       document.all.item("lat").value=e.point.lat;
       document.all.item("lng").value=e.point.lng;
       addMarker(e.point,0);
   });   
</script>
</html>

