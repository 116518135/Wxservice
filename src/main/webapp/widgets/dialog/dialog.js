var auto_close_hand;
var auto_close_hand1;
var auto_close_hand2;
var is_opera_d  = (navigator.userAgent.toLowerCase().indexOf('opera') != -1);
String.prototype.trim = function()
{
	return this.replace(/(^[ |��]*)|([ |��]*$)/g, "");
}

function $(s)
{
	if(document.getElementById)
	{
        var o=document.getElementById(s);
        if(o!=undefined && o!=null){		
		   return o;
		}
	}
	return document.all.item(s);
}
function $$(s)
{
	return document.frames?document.frames[s]:$(s).contentWindow;
}
function $c(s)
{
	return document.createElement(s);
}
function swap(s,a,b,c)
{
	$(s)[a]=$(s)[a]==b?c:b;
}
function exist(s)
{
	return $(s)!=null;
}
function dw(s)
{
	document.write(s);
}
function hide(s)
{
	$(s).style.display=$(s).style.display=="none"?"":"none";
}
function isNull(_sVal)
{
	return (_sVal == "" || _sVal == null || _sVal == "undefined");
}
function removeNode(s)
{
	if(exist(s))
	{
		$(s).innerHTML = '';
		$(s).removeNode?$(s).removeNode():$(s).parentNode.removeChild($(s));
	}
}

function dialog(imagePath)
{
	var titile = '';
	var auto = 'y';
	var width = 240;
	var height = 120;
	var src = "";	
	if(imagePath==null) imagePath=''
	var sFunc = '<input id="dialogOk" type="button" value=" ȷ �� " onclick="new dialog().reset();" /> <input id="dialogCancel" type="button" value=" ȡ �� " onclick="new dialog().reset();" />';
	var sClose = '<input type="image" id="dialogBoxClose" onclick="new dialog().reset();" src="'+getPath()+"/"+imagePath+'/close.gif" border="0" width="16" height="16" align="absmiddle" />';
	var sBody = '\
		<table id="dialogBodyBox" border="0" align="center" cellpadding="0" cellspacing="6" width="100%">\
			<tr height="10"><td colspan="4"></td></tr>\
			<tr><td colspan="4" align="center">\
			<div id="dialogMsgDiv" style="text-align:center"><div id="dialogMsg" style="font-size:12px;line-height:180%;"></div></div>\
			</td></tr>\
			<tr><td id="dialogFunc" colspan="4" align="center">' + sFunc + '</td></tr>\
			<tr height="5"><td colspan="4" align="center"></td></tr>\
		</table>\
	';
	var sIfram = '\
		<iframe id="dialogIframBG" name="dialogIframBG" frameborder="0" marginheight="0" marginwidth="0" hspace="0" vspace="0" scrolling="no" style="position:absolute;z-index:8;display:none;"></iframe>\
	';

	var sBox = '\
		<div id="dialogBox" style="border:1px solid #1e4775;display:none;z-index:10;width:'+width+'px;">\
		<table width="100%" border="0" cellpadding="0" cellspacing="0">\
			<tr height="24" bgcolor="#6795B4">\
				<td>\
					<table onselectstart="return false;" style="-moz-user-select:none;" width="100%" border="0" cellpadding="0" cellspacing="0" style="background-color:#5499dc;  height:25px; border-top:1px solid #92c3ec;">\
						<tr>\
							<td width="6" height="24"></td>\
							<td id="dialogBoxTitle" onmousedown="new dialog().moveStart(event, \'dialogBox\')" style="color:#fff;cursor:move;font-size:12px;font-weight:bold;">&nbsp;</td>\
							<td id="dialogClose" width="20" align="right" valign="middle">\
								' + sClose + '\
							</td>\
							<td width="6"></td>\
						</tr>\
					</table>\
				</td>\
			</tr>\
			<tr id="dialogHeight" style="height:' + height + '" valign="top">\
				<td id="dialogBody" bgcolor="#ffffff">' + sBody + '</td>\
			</tr>\
		</table></div>\
		<div id="dialogBoxShadow" style="display:none;z-index:9;"></div>\
	';
	var sBG = '\
		<div id="dialogBoxBG" style="position:absolute;top:0px;left:0px;width:100%;height:100%;"></div>\
	';

	this.show = function()
	{
		this.middle('dialogBox');
		if ($('dialogIframBG'))
		{
			$('dialogIframBG').style.top = $('dialogBox').style.top;
			$('dialogIframBG').style.left = $('dialogBox').style.left;
			$('dialogIframBG').style.width = $('dialogBox').offsetWidth;
			$('dialogIframBG').style.height = $('dialogBox').offsetHeight;
			$('dialogIframBG').style.display = 'block';
		}		
		this.shadow();
	}

	this.reset = function()
	{
		this.close();
	}

	this.close = function()
	{
		if ($('dialogIframBG'))
		{
			$('dialogIframBG').style.display = 'none';
		}
		$('dialogBox').style.display='none';
		$('dialogBoxBG').style.display='none';
		$('dialogBoxShadow').style.display = "none";
		$('dialogBody').innerHTML = sBody;
	}
	this.html = function(_sHtml)
	{
		$("dialogBody").innerHTML = _sHtml;
		this.show();
	}

	this.init = function(big_msg)
	{
		$('dialogCase') ? $('dialogCase').parentNode.removeChild($('dialogCase')) : function(){};
		var oDiv = document.createElement('span');
		oDiv.id = "dialogCase";
		if ('yes' == big_msg)
		{
			oDiv.innerHTML = sBG + sBox;
		}
		else
		{
			if (!is_opera_d)
			{
				oDiv.innerHTML = sBG + sIfram + sBox;
			}
			else
			{
				oDiv.innerHTML = sBG + sBox;
			}
		}
		document.body.appendChild(oDiv);
		$('dialogBoxBG').style.height = document.body.scrollHeight;
	}

	this.button = function(_sId, _sFuc)
	{
		if($(_sId))
		{
			$(_sId).style.display = '';
			if($(_sId).addEventListener)
			{
				if($(_sId).act)
				{
					$(_sId).removeEventListener('click', function(){eval($(_sId).act)}, false);
				}
				$(_sId).act = _sFuc;
				$(_sId).addEventListener('click', function(){eval(_sFuc)}, false);
			}
			else
			{
				if($(_sId).act)
				{
					$(_sId).detachEvent('onclick', function(){eval($(_sId).act)});
				}
				$(_sId).act = _sFuc;
				$(_sId).attachEvent('onclick', function(){eval(_sFuc)});
			}
		}
	}

	this.shadow = function()
	{
		var oShadow = $('dialogBoxShadow');
		var oDialog = $('dialogBox');
		oShadow['style']['position'] = "absolute";
		oShadow['style']['background']	= "#000";
		oShadow['style']['display']	= "";
		oShadow['style']['opacity']	= "0.2";
		oShadow['style']['filter'] = "alpha(opacity=20)";
		oShadow['style']['top'] = oDialog.offsetTop + 6;
		oShadow['style']['left'] = oDialog.offsetLeft + 6;
		oShadow['style']['width'] = oDialog.offsetWidth;
		oShadow['style']['height'] = oDialog.offsetHeight;
	}

	this.open = function(_sUrl, _sMode)
	{
		this.show();
		if(!_sMode || _sMode == "no" || _sMode == "yes"){
			var openIframe = "<iframe width='100%' height='100%' name='iframe_parent' id='iframe_parent' src='" + _sUrl + "' frameborder='0' scrolling='" + _sMode + "'></iframe>";
			$("dialogBody").innerHTML = openIframe;
			//$("iframe_parent").focus();
			//document.frames['dialogBody'].focus();
		}
	}

	this.showWindow = function(_sUrl, _iWidth, _iHeight, _sMode)
	{
		var oWindow;
		var sLeft = (screen.width) ? (screen.width - _iWidth)/2 : 0;
		var iTop = -80 + (screen.height - _iHeight)/2;
		iTop = iTop > 0 ? iTop : (screen.height - _iHeight)/2;
		var sTop = (screen.height) ? iTop : 0;
		if(window.showModalDialog && _sMode == "m"){
			oWindow = window.showModalDialog(_sUrl,"","dialogWidth:" + _iWidth + "px;dialogheight:" + _iHeight + "px");
		} else {
			oWindow = window.open(_sUrl, '', 'height=' + _iHeight + ', width=' + _iWidth + ', top=' + sTop + ', left=' + sLeft + ', toolbar=no, menubar=no, scrollbars=' + _sMode + ', resizable=no,location=no, status=no');
			this.reset();
		}
	}

	this.event = function(_sMsg, _sOk, _sCancel, _sClose)
	{
		$('dialogFunc').innerHTML = sFunc;
		$('dialogClose').innerHTML = sClose;
		$('dialogBodyBox') == null ? $('dialogBody').innerHTML = sBody : function(){};
		if (width > 400 && height > 300)
		{
			$('dialogMsg') ? $('dialogMsg').innerHTML = _sMsg  : function(){};
			$('dialogMsg') ? $('dialogMsg')['style']['fontWeight'] = "bold" : function(){};
			$('dialogMsg') ? $('dialogMsg')['style']['fontSize'] = "15px" : function(){};
			$('dialogMsg') ? $('dialogMsg')['style']['color'] = "#ff9900" : function(){};
			$('dialogMsg') ? $('dialogMsg')['style']['height'] = "150px" : function(){};
		}
		else
		{
			$('dialogMsg') ? $('dialogMsg').innerHTML = _sMsg  : function(){};
		}

		_sOk && _sOk != "" ? this.button('dialogOk', _sOk) : $('dialogOk').style.display = 'none';
		_sCancel && _sCancel != "" ? this.button('dialogCancel', _sCancel) : $('dialogCancel').style.display = 'none';
		//_sOk ? this.button('dialogOk', _sOk) : _sOk == "" ? function(){} : $('dialogCancel').style.display = 'none';
		//_sCancel ? this.button('dialogCancel', _sCancel) : _sCancel == "" ? function(){} : $('dialogCancel').style.display = 'none';
		_sClose ? this.button('dialogBoxClose', _sClose) : function(){};

		this.show();
	}

	this.set = function(_oAttr, _sVal)
	{
		var oShadow = $('dialogBoxShadow');
		var oDialog = $('dialogBox');
		var oHeight = $('dialogHeight');

		if(_sVal != '')
		{
			switch(_oAttr)
			{
				case 'title':
					$('dialogBoxTitle').innerHTML = _sVal;
					title = _sVal;
					break;
				case 'width':
					oDialog['style']['width'] = _sVal;
					width = _sVal;
					break;
				case 'height':
					oHeight['style']['height'] = _sVal;
					height = _sVal;
					break;
				case 'src':
					$('dialogMsgDiv').innerHTML = '\
						<table border="0" align="center" cellpadding="0" cellspacing="0" width="100%">\
							<tr>\
								<td width="30%" align="center"><img id="dialogBoxFace" src="' + path + 'login_wrong.gif" /></td>\
								<td id="dialogMsg" style="font-size:12px;line-height:180%;" width="70%"></td>\
							</tr>\
						</table>\
					';
					$('dialogBoxFace') ? $('dialogBoxFace').src = path + _sVal + '.gif' : function(){};
					src = _sVal;
					break;
				case 'auto':
					auto = _sVal;
			}
		}
		this.middle('dialogBox');
		oShadow['style']['top'] = oDialog.offsetTop + 6;
		oShadow['style']['left'] = oDialog.offsetLeft + 6;
		oShadow['style']['width'] = oDialog.offsetWidth;
		oShadow['style']['height'] = oDialog.offsetHeight;
	}

	this.moveStart = function (event, _sId)
	{
		var oObj = $(_sId);
		oObj.onmousemove = mousemove;
		oObj.onmouseup = mouseup;
		oObj.setCapture ? oObj.setCapture() : function(){};
		oEvent = window.event ? window.event : event;
		var dragData = {x : oEvent.clientX, y : oEvent.clientY};
		var backData = {x : parseInt(oObj.style.top), y : parseInt(oObj.style.left)};
		function mousemove()
		{
			var oEvent = window.event ? window.event : event;
			var iLeft = oEvent.clientX - dragData["x"] + parseInt(oObj.style.left);
			var iTop = oEvent.clientY - dragData["y"] + parseInt(oObj.style.top);
			oObj.style.left = iLeft;
			oObj.style.top = iTop;
			$('dialogBoxShadow').style.left = iLeft + 6;
			$('dialogBoxShadow').style.top = iTop + 6;
			if ($('dialogIframBG'))
			{
				$('dialogIframBG').style.left = iLeft;
				$('dialogIframBG').style.top = iTop;
			}
			dragData = {x: oEvent.clientX, y: oEvent.clientY};

		}
		function mouseup()
		{
			var oEvent = window.event ? window.event : event;
			oObj.onmousemove = null;
			oObj.onmouseup = null;
			if(oEvent.clientX < 1 || oEvent.clientY < 1 || oEvent.clientX > document.body.clientWidth || oEvent.clientY > document.body.clientHeight){
				oObj.style.left = backData.y;
				oObj.style.top = backData.x;
				$('dialogBoxShadow').style.left = backData.y + 6;
				$('dialogBoxShadow').style.top = backData.x + 6;
				if ($('dialogIframBG'))
				{
					$('dialogIframBG').style.left = backData.y;
					$('dialogIframBG').style.top = backData.x;
				}
			}
			oObj.releaseCapture ? oObj.releaseCapture() : function(){};
		}
	}

	this.hideModule = function(_sType, _sDisplay)
	{
		var aIframe = parent.document.getElementsByTagName("iframe");
		var aType = document.getElementsByTagName(_sType);
		var iChildObj, iChildLen;
		for (var i = 0; i < aType.length; i++)
		{
			aType[i].style.display	= _sDisplay;
		}
		for (var j = 0; j < aIframe.length; j++)
		{
			iChildObj = document.frames ? document.frames[j] : aIframe[j].contentWindow;
			try
			{
				iChildLen = iChildObj.document.body.getElementsByTagName(_sType).length;
				for (var k = 0; k < iChildLen; k++)
				{
					iChildObj.document.body.getElementsByTagName(_sType)[k].style.display = _sDisplay;
				}
			}
			catch (e){}
		}
	}

	this.middle = function(_sId)
	{
		try
		{
			var aIframe = parent.document.getElementById("iframe_parent");
		}
		catch (e){}
		if (aIframe) {
			var sClientWidth = aIframe.offsetWidth;
			var sClientHeight = aIframe.offsetHeight;
			var sScrollTop = 0;
		} else {
			var sClientWidth = parent ? parent.document.body.clientWidth : document.body.clientWidth;
			var sClientHeight = parent ? parent.document.body.clientHeight : document.body.clientHeight;
			var sScrollTop = parent ? parent.document.body.scrollTop : document.body.scrollTop;
		}
		var sleft = (document.body.clientWidth / 2) - ($(_sId).offsetWidth / 2);
		var iTop = -80 + (sClientHeight / 2 + sScrollTop) - ($(_sId).offsetHeight / 2);
		var sTop = iTop > 0 ? iTop : (sClientHeight / 2 + sScrollTop) - ($(_sId).offsetHeight / 2);
		$(_sId)['style']['display'] = '';
		$(_sId)['style']['position'] = "absolute";
		$(_sId)['style']['left'] = sleft;
		$(_sId)['style']['top'] = sTop;
	}
}



