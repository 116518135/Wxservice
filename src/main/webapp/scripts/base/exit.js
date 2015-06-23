    window.onbeforeunload = onbeforeunload_handler;   
    window.onunload = onunload_handler;
    function onbeforeunload_handler(){   
        var warning="确认离开系统";           
        return warning;   
    }  
    function onunload_handler(){   
        var qstr="";
        var url=getPath()+"/login.do?method=exit";
        Ext.Ajax.request({
             url: url, 
             params:qstr,  
             method: 'post',   
             callback: onunload_handler_callback 
        });
    }    
    var onunload_handler_callback =function(options, success, response){
    
    } 