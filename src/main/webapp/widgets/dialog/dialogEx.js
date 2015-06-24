   window.closeDialog=function(){
         new dialog().reset();
        var form=document.forms[0];
        form.submit();
    }
    
   window.closeDialogNoRefresh=function(){
         new dialog().reset();
    }
    
function openWindow(_sUrl, _sWidth, _sHeight, _sTitle, imagePath,_sScroll)
{
	var oEdit = new dialog(imagePath);
	oEdit.init('yes');
	oEdit.set('title', _sTitle ? _sTitle : "ϵͳ弹出框" );
	oEdit.set('width', _sWidth);
	oEdit.set('height', _sHeight);
	oEdit.set('auto', 'n');
	oEdit.open(_sUrl, _sScroll != "yes" ? 'no' : 'yes');
}


function openPrintWindow(_sUrl,_sTitle)
{
	var oEdit = new dialog("widgets/dialog");
	oEdit.init('yes');
	oEdit.set('title', _sTitle ? _sTitle : "ϵͳ弹出框" );
	oEdit.set('width', '600');
	oEdit.set('height', '500');
	oEdit.set('auto', 'n');
	oEdit.open(_sUrl,'yes');
}

function openSubWindow600(_sUrl,_sTitle)
{
	var oEdit = new dialog("widgets/dialog");
	oEdit.init('yes');
	oEdit.set('title', _sTitle );
	oEdit.set('width', '600');
	oEdit.set('height', '450');
	oEdit.set('auto', 'n');
	oEdit.open(_sUrl,'yes');
}
function openSubWindow700(_sUrl,_sTitle)
{
	var oEdit = new dialog("widgets/dialog");
	oEdit.init('yes');
	oEdit.set('title', _sTitle);
	oEdit.set('width', '700');
	oEdit.set('height', '500');
	oEdit.set('auto', 'n');
	oEdit.open(_sUrl,'yes');

}

function openSubWindow800(_sUrl,_sTitle)
{
	var oEdit = new dialog("widgets/dialog");
	oEdit.init('yes');
	oEdit.set('title', _sTitle);
	oEdit.set('width', '800');
	oEdit.set('height', '600');
	oEdit.set('auto', 'n');
	oEdit.open(_sUrl,'yes');

}

function openSubWindow500(_sUrl,_sTitle)
{
	var oEdit = new dialog("widgets/dialog");
	oEdit.init('yes');
	oEdit.set('title', _sTitle);
	oEdit.set('width', '500');
	oEdit.set('height', '350');
	oEdit.set('auto', 'n');
	oEdit.open(_sUrl,'yes');
}

function closeWindow(){
  new dialog().reset();
}