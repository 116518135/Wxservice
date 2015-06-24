(function($){
	$.fn.btn = function(){
		var btn = this.data("_self");;
		if(btn){
			return btn;
		};
		this.init = function(){
			var obj = $(this);
			var id=$(this).attr('id')||"gen"+Math.random();
			var icon = $(this).attr('icon')||'icon-btncom';
			var bntStr=[
				'<table id="',id,'" class="z-btn" cellSpacing=0 cellPadding=0 border=0><tbody><tr>',
					'<TD class=z-btn-left><i>&nbsp;</i></TD>',
					'<TD class=z-btn-center><EM unselectable="on">',
						'<BUTTON class="z-btn-text ',icon,'" >',$(this).attr('value'),'</BUTTON>',
					'</EM></TD>',
					'<TD class=z-btn-right><i>&nbsp;</i></TD>',
				'</tr></tbody></table>'
			];
			var bnt = $(bntStr.join('')).btn();
			bnt._click = eval(obj.attr("onclick"));
			bnt.disable();
			if(obj.attr("disabled"))
				bnt.disable();
			else bnt.enable();
			$(this).replaceWith(bnt);
			bnt.data("_self", bnt);  
			return bnt;
		};
		this.enable = function(){
			this.removeClass("z-btn-dsb");
			this.click(this._click);
			this.hover(
				  function () {
				    $(this).addClass("z-btn-over");
				  },
				  function () {
				    $(this).removeClass("z-btn-over");
				  }
				)
		};
		this.disable = function(){
			 this.addClass("z-btn-dsb");
			 this.unbind("hover");
			 this.unbind("click");
		};  
		return this;
	};
	
	$(function(){
		$("input[type='button']").each(function(){
			$(this).btn().init();
		});
	})
})(jQuery);	