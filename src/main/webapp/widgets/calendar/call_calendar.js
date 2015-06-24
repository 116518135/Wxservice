function selected(cal, date) {
  cal.sel.value = date; 
  if (cal.dateClicked) {
			cal.callCloseHandler();
  }
}
function closeHandler(cal) {
  cal.hide();           
  cal.destroy();
  calendar = null;
}
var calendar=null;
function showCalendar(id, format, showsTime) {

  var el =document.all.item(id);

  if (calendar != null ) {
     calendar.hide(); 
  } else {
  
    var cal = new Calendar(true, null, selected, closeHandler);
    if (typeof showsTime == "string") {
      cal.showsTime = true;
      cal.time24 = (showsTime == "24");
    }
    calendar = cal;              
    cal.setRange(1900, 2070);     
    cal.create();
  }
  calendar.setDateFormat(format);    
  calendar.parseDate(el.value);      
  calendar.sel = el;               

  calendar.showAtElement(el.nextSibling, "Br");    
  return false;
}

