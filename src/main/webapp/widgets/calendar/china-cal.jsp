<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>BODY {
	FONT-FAMILY: arial,sans-serif
}
TD {
	FONT-FAMILY: arial,sans-serif
}
DIV {
	FONT-FAMILY: arial,sans-serif
} 
SPAN {
	FONT-FAMILY: arial,sans-serif
}
P {
	FONT-FAMILY: arial,sans-serif
}
A {
	COLOR: #0000cc
}
A:visited {
	COLOR: #551a8b
}
A:active {
	COLOR: #ff0000
}
BODY {
	PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px; BACKGROUND-COLOR: white
}
</STYLE>

<SCRIPT>window['__isgadget']=true;</SCRIPT>

<SCRIPT src="china-cal.js"></SCRIPT>

<SCRIPT>gadgets.window=gadgets.window||{};
(function(){gadgets.window.getViewportDimensions=function(){var A=0;
var B=0;
if(self.innerHeight){A=self.innerWidth;
B=self.innerHeight
}else{if(document.documentElement&&document.documentElement.clientHeight){A=document.documentElement.clientWidth;
B=document.documentElement.clientHeight
}else{if(document.body){A=document.body.clientWidth;
B=document.body.clientHeight
}}}return{width:A,height:B}
}
})();;
gadgets.window=gadgets.window||{};
(function(){var C;
function A(F,D){var E=window.getComputedStyle(F,"");
var G=E.getPropertyValue(D);
G.match(/^([0-9]+)/);
return parseInt(RegExp.$1,10)
}function B(){var M=0;
var K=[document.body];
while(K.length>0){var G=K.shift();
var F=G.childNodes;
if(typeof G.style!=="undefined"){var H=G.style.overflowY;
if(!H){var J=document.defaultView.getComputedStyle(G,null);
H=J?J.overflowY:null
}if(H!="visible"&&H!="inherit"){var L=G.style.height;
if(!L){var J=document.defaultView.getComputedStyle(G,null);
L=J?J.height:""
}if(L.length>0&&L!="auto"){continue
}}}for(var I=0;
I<F.length;
I++){var E=F[I];
if(typeof E.offsetTop!=="undefined"&&typeof E.offsetHeight!=="undefined"){var D=E.offsetTop+E.offsetHeight+A(E,"margin-bottom");
M=Math.max(M,D)
}K.push(E)
}}return M+A(document.body,"border-bottom")+A(document.body,"margin-bottom")+A(document.body,"padding-bottom")
}gadgets.window.adjustHeight=function(I){var F=parseInt(I,10);
var E=false;
if(isNaN(F)){E=true;
var K=gadgets.window.getViewportDimensions().height;
var D=document.body;
var J=document.documentElement;
if(document.compatMode==="CSS1Compat"&&J.scrollHeight){F=J.scrollHeight!==K?J.scrollHeight:J.offsetHeight
}else{if(navigator.userAgent.indexOf("AppleWebKit")>=0){F=B()
}else{if(D&&J){var G=J.scrollHeight;
var H=J.offsetHeight;
if(J.clientHeight!==H){G=D.scrollHeight;
H=D.offsetHeight
}if(G>K){F=G>H?G:H
}else{F=G<H?G:H
}}}}}if(F!==C&&!isNaN(F)&&!(E&&F===0)){C=F;
gadgets.rpc.call(null,"resize_iframe",null,F)
}}
}());
var _IG_AdjustIFrameHeight=gadgets.window.adjustHeight;;
(function(){function d(a,b){var c=window.getComputedStyle(a,"");c=c.getPropertyValue(b);c.match(/^([0-9]+)/);c=parseInt(RegExp.$1,10);return!isNaN(c)?c:0}window._IG_GetDivHeight=function(a){a=document.getElementById(a);if(a!=null&&navigator.userAgent.indexOf("AppleWebKit")>=0){var b=0;if(typeof a.offsetTop!=="undefined"&&typeof a.offsetHeight!=="undefined")b=a.offsetTop+a.offsetHeight+d(a,"margin-bottom")+d(a,"margin-top");return b=b+d(document.body,"border-bottom")+d(document.body,"margin-bottom")+
d(document.body,"padding-bottom")}return null};var e=null,g=(new Date).getTime()+18E5;gadgets.util.registerOnLoadHandler(function(){g=(new Date).getTime()});var f=false;window._IG_AdjustIFrameHeight=function(a){if(e){window.clearTimeout(e);e=null}var b=10;if((new Date).getTime()-g>6E3)f=true;f||(b=800);e=window.setTimeout(function(){gadgets.window.adjustHeight(a);f=true;e=null},b)}})();
;
var tamings___=tamings___||[];
tamings___.push(function(A){caja___.whitelistFuncs([[gadgets.window,"adjustHeight"],[gadgets.window,"getViewportDimensions"]])
});;
gadgets.Prefs.prototype.set=function(D,E){var G=false;
if(arguments.length>2){var F={};
for(var C=0,B=arguments.length;
C<B;
C+=2){F[arguments[C]]=arguments[C+1]
}G=gadgets.Prefs.setInternal_(F)
}else{G=gadgets.Prefs.setInternal_(D,E)
}if(!G){return 
}var A=[null,"set_pref",null,gadgets.util.getUrlParameters().ifpctok||gadgets.util.getUrlParameters().rpctoken||0].concat(Array.prototype.slice.call(arguments));
gadgets.rpc.call.apply(gadgets.rpc,A)
};
gadgets.Prefs.prototype.setArray=function(C,D){for(var B=0,A=D.length;
B<A;
++B){if(typeof D[B]!=="number"){D[B]=D[B].replace(/\|/g,"%7C")
}}this.set(C,D.join("|"))
};;
gadgets.config.init({"shindig.auth":{"authToken":"","trustedJson":""},"rpc":{"useLegacyProtocol":true,"parentRelayUrl":"/ig/ifpc_relay"},"core.util":{"dynamic-height":{},"setprefs":{},"core":{}},"core.io":{"jsonProxyUrl":"//%host%/gadgets/makeRequest","proxyUrl":"//www.ig.gmodules.com/gadgets/proxy/refresh=%refresh%&container=%container%%rewriteMime%&gadget=%gadget%/%rawurl%"}});
</SCRIPT>

<SCRIPT>gadgets.Prefs.setMessages_({"title":"中国农历","thumbnail":"/ig/modules/chinagadgets/chinacalendar/zh-CN_ALL-thm.png","screenshot":"/ig/modules/chinagadgets/chinacalendar/zh-CN_ALL.png","description":"中国农历，阴历和阳历对照，还可以回溯到1901年或前瞻到2049年进行查询，真方便！"});gadgets.Prefs.setDefaultPrefs_({"isMonthCal":"1"});gadgets.io.preloaded_=[];</SCRIPT>

<STYLE>.button {
	FONT-SIZE: 12px; LINE-HEIGHT: 120%; HEIGHT: 1.8em
}
.input1 {
	BORDER-RIGHT: #cccccc 1px solid; PADDING-RIGHT: 1px; BORDER-TOP: #cccccc 1px solid; PADDING-LEFT: 1px; FONT-SIZE: 12px; PADDING-BOTTOM: 1px; BORDER-LEFT: #cccccc 1px solid; COLOR: #333; PADDING-TOP: 1px; BORDER-BOTTOM: #cccccc 1px solid; FONT-FAMILY: "arial", "sans-serif"
}
.ccal {
	BORDER-RIGHT: #eaf0f6 1px solid; BORDER-TOP: #eaf0f6 1px solid; FONT-SIZE: 13px; BORDER-LEFT: #eaf0f6 1px solid; LINE-HEIGHT: 100%; BORDER-BOTTOM: #eaf0f6 1px solid; FONT-FAMILY: "arial", "sans-serif"; TEXT-ALIGN: center
}
.cdate {
	FONT-WEIGHT: normal; FONT-SIZE: 12px; COLOR: #666
}
.ccal-header {
	FONT-SIZE: 13px; BACKGROUND: #eaf0f6; COLOR: #333; TEXT-DECORATION: none
}
A:link {
	COLOR: #00c; TEXT-DECORATION: none
}
A:visited {
	COLOR: #00c; TEXT-DECORATION: none
}
A:hover {
	TEXT-DECORATION: underline
}
A:active {
	TEXT-DECORATION: underline
}
A:hover EM {
	TEXT-DECORATION: underline
}
A:active EM {
	TEXT-DECORATION: underline
}
A:active {
	COLOR: #c60b02
}
.ccal-weekdayheader {
	BORDER-BOTTOM: #eaf0f6 1px solid
}
.ccal-weekendheader {
	COLOR: #c60b02; BORDER-BOTTOM: #eaf0f6 1px solid
}
.ccal-today {
	BORDER-RIGHT: #abc 1px solid; BORDER-TOP: #567 1px solid; FONT-WEIGHT: bold; FONT-SIZE: 14px; BACKGROUND: #9ab; BORDER-LEFT: #567 1px solid; COLOR: #fff; BORDER-BOTTOM: #abc 1px solid
}
.ccal-today-mouseover {
	BORDER-RIGHT: #9bd 1px solid; BORDER-TOP: #246 1px solid; FONT-WEIGHT: bold; FONT-SIZE: 14px; BACKGROUND: #dfe1e9; BORDER-LEFT: #246 1px solid; CURSOR: pointer; COLOR: #fff; BORDER-BOTTOM: #9bd 1px solid
}
.ccal-weekday {
	FONT-WEIGHT: bold; FONT-SIZE: 14px
}
.ccal-weekday-mouseover {
	FONT-WEIGHT: bold; FONT-SIZE: 14px; BACKGROUND: #dfe1e9; CURSOR: pointer
}
.ccal-weekend {
	FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: #c60b02
}
.ccal-weekend-mouseover {
	FONT-WEIGHT: bold; FONT-SIZE: 14px; BACKGROUND: #dfe1e9; CURSOR: pointer; COLOR: #c60b02
}
.ccal-day {
	BORDER-RIGHT: #99ccff 1px solid; BORDER-TOP: #99ccff 1px solid; FONT-SIZE: 10px; BORDER-LEFT: #99ccff 1px solid; COLOR: #333; BORDER-BOTTOM: #99ccff 1px solid; FONT-FAMILY: "arial", "sans-serif"; TEXT-ALIGN: center
}
.ccal-day0 {
	BACKGROUND: url(/ig/modules/chinagadgets/chinacalendar/chinacalendar_contents/header.gif) no-repeat
}
.ccal-day1 {
	FONT-WEIGHT: bold; FONT-SIZE: 25px; BACKGROUND: #99ccff; COLOR: #333; FONT-FAMILY: "arial", "sans-serif"; TEXT-ALIGN: center
}
.ccal-day2 {
	FONT-WEIGHT: bold; FONT-SIZE: 70px; TEXT-ALIGN: center
}
.ccal-day3 {
	FONT-WEIGHT: bold; FONT-SIZE: 25px; TEXT-ALIGN: center
}
.ccal-day31 {
	FONT-WEIGHT: bold; FONT-SIZE: 20px; TEXT-ALIGN: center
}
.ccal-day4 {
	FONT-SIZE: 16px; TEXT-ALIGN: center
}
.ccal-day5 {
	FONT-SIZE: 20px; TEXT-ALIGN: center
}
.ccal-day6 {
	BACKGROUND: url(/ig/modules/chinagadgets/chinacalendar/chinacalendar_contents/spliter.gif) no-repeat
}
</STYLE>

<SCRIPT>var params = gadgets.util.getUrlParameters();var url = params['parent'] + '/ig/images/rpc_relay.html';gadgets.rpc.setRelayUrl('..', url, false);</SCRIPT>

<META content="MSHTML 6.00.2900.6049" name=GENERATOR></HEAD>
<BODY dir=ltr>
<SCRIPT>String.prototype.a=function(){return this.replace(/[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/ig,"**").length};
function ChinaCalendar(){var r=new Array(19416,19168,42352,21717,53856,55632,91476,22176,39632,21970,19168,42422,42192,53840,119381,46400,54944,44450,38320,84343,18800,42160,46261,27216,27968,109396,11104,38256,21234,18800,25958,54432,59984,28309,23248,11104,100067,37600,116951,51536,54432,120998,46416,22176,107956,9680,37584,53938,43344,46423,27808,46416,86869,19872,42448,83315,21200,43432,59728,27296,44710,43856,19296,43748,42352,21088,62051,55632,23383,22176,38608,19925,19152,42192,54484,53840,
54616,46400,46496,103846,38320,18864,43380,42160,45690,27216,27968,44870,43872,38256,19189,18800,25776,29859,59984,27480,21952,43872,38613,37600,51552,55636,54432,55888,30034,22176,43959,9680,37584,51893,43344,46240,47780,44368,21977,19360,42416,86390,21168,43312,31060,27296,44368,23378,19296,42726,42208,53856,60005,54576,23200,30371,38608,19415,19152,42192,118966,53840,54560,56645,46496,22224,21938,18864,42359,42160,43600,111189,27936,44448),J=new Array("\u7532","\u4e59","\u4e19","\u4e01","\u620a",
"\u5df1","\u5e9a","\u8f9b","\u58ec","\u7678"),K=new Array("\u5b50","\u4e11","\u5bc5","\u536f","\u8fb0","\u5df3","\u5348","\u672a","\u7533","\u9149","\u620c","\u4ea5"),y=new Array("\u9f20","\u725b","\u864e","\u5154","\u9f99","\u86c7","\u9a6c","\u7f8a","\u7334","\u9e21","\u72d7","\u732a"),s=new Array("\u5c0f\u5bd2","\u5927\u5bd2","\u7acb\u6625","\u96e8\u6c34","\u60ca\u86f0","\u6625\u5206","\u6e05\u660e","\u8c37\u96e8","\u7acb\u590f","\u5c0f\u6ee1","\u8292\u79cd","\u590f\u81f3","\u5c0f\u6691","\u5927\u6691",
"\u7acb\u79cb","\u5904\u6691","\u767d\u9732","\u79cb\u5206","\u5bd2\u9732","\u971c\u964d","\u7acb\u51ac","\u5c0f\u96ea","\u5927\u96ea","\u51ac\u81f3"),L=new Array(0,21208,42467,63836,85337,107014,128867,150921,173149,195551,218072,240693,263343,285989,308563,331033,353350,375494,397447,419210,440795,462224,483532,504758),p=new Array("\u65e5","\u4e00","\u4e8c","\u4e09","\u56db","\u4e94","\u516d","\u4e03","\u516b","\u4e5d","\u5341","\u5341\u4e00","\u5341\u4e8c"),M=new Array("\u521d","\u5341","\u5eff",
"\u5345","\u3000"),U=new Array("1","2","3","4","5","6","7","8","9","10","11","12"),N=new Array("SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"),z=new Array("0101*\u5143\u65e6","0214 \u60c5\u4eba\u8282","0308 \u5987\u5973\u8282","0312 \u690d\u6811\u8282","0315 \u6d88\u8d39\u8005\u6743\u76ca\u65e5","0401 \u611a\u4eba\u8282","0501*\u52b3\u52a8\u8282","0504 \u9752\u5e74\u8282","0512 \u62a4\u58eb\u8282","0601 \u513f\u7ae5\u8282","0701 \u5efa\u515a\u8282 \u9999\u6e2f\u56de\u5f52\u7eaa\u5ff5",
"0801 \u5efa\u519b\u8282","0808 \u7236\u4eb2\u8282","0909 \u6bdb\u6cfd\u4e1c\u901d\u4e16\u7eaa\u5ff5","0910 \u6559\u5e08\u8282","0928 \u5b54\u5b50\u8bde\u8fb0","1001*\u56fd\u5e86\u8282","1006 \u8001\u4eba\u8282","1024 \u8054\u5408\u56fd\u65e5","1112 \u5b59\u4e2d\u5c71\u8bde\u8fb0\u7eaa\u5ff5","1220 \u6fb3\u95e8\u56de\u5f52\u7eaa\u5ff5","1225 \u5723\u8bde\u8282","1226 \u6bdb\u6cfd\u4e1c\u8bde\u8fb0\u7eaa\u5ff5"),A=new Array("0101*\u6625\u8282","0115 \u5143\u5bb5\u8282","0505*\u7aef\u5348\u8282","0707 \u4e03\u5915",
"0715 \u4e2d\u5143\u8282","0815*\u4e2d\u79cb\u8282","0909 \u91cd\u9633\u8282","1208 \u814a\u516b\u8282","1224 \u5c0f\u5e74","0100*\u9664\u5915"),B=new Array("0520 \u6bcd\u4eb2\u8282"),l=new Date,v=new _IG_Prefs(0);function O(b){var a,c=348;for(a=32768;a>8;a>>=1)c+=r[b-1900]&a?1:0;return c+w(b)}function w(b){return C(b)?r[b-1900]&65536?30:29:0}function C(b){return r[b-1900]&15}function D(b,a){return r[b-1900]&65536>>a?30:29}function P(b){var a,c=0,e=0,k=new Date(1900,0,31),g=(b-k)/86400000;
this.dayCyl=g+40;this.monCyl=14;for(a=1900;a<2050&&g>0;a++){e=O(a);g-=e;this.monCyl+=12}if(g<0){g+=e;a--;this.monCyl-=12}this.year=a;this.yearCyl=a-1864;c=C(a);this.isLeap=false;for(a=1;a<13&&g>0;a++){if(c>0&&a==c+1&&this.isLeap==false){--a;this.isLeap=true;e=w(this.year)}else e=D(this.year,a);if(this.isLeap==true&&a==c+1)this.isLeap=false;g-=e;this.isLeap==false&&this.monCyl++}if(g==0&&c>0&&a==c+1)if(this.isLeap)this.isLeap=false;else{this.isLeap=true;--a;--this.monCyl}if(g<0){g+=e;--a;--this.monCyl}this.month=
a;this.day=g+1}function Q(b,a){return 32-(new Date(b,a,32)).getDate()}function t(b){return J[b%10]+K[b%12]}function R(b,a,c,e,k,g,o,d,f,i,j){this.sYear=b;this.sMonth=a;this.sDay=c;this.week=e;this.lYear=k;this.lMonth=g;this.lDay=o;this.isLeap=d;this.cYear=f;this.cMonth=i;this.cDay=j;this.solarTerms=this.solarFestival=this.lunarFestival=this.jiari=this.color="";this.isToday=false}function E(b,a){var c=new Date(3.15569259747E10*(b-1900)+L[a]*60000+Date.UTC(1900,0,6,2,5));return c.getUTCDate()}function S(b,
a){var c,e,k,g,o=1,d,f=0,i,j,q=new Array(3),m=0,n=0;c=new Date(b,a,1);this.length=Q(b,a);this.firstWeek=c.getDay();for(var h=0;h<this.length;h++){if(o>f){c=new Date(b,a,h+1);e=new P(c);k=e.year;g=e.month;o=e.day;f=(d=e.isLeap)?w(k):D(k,g);if(m==0)n=g;q[m++]=h-o+1}this[h]=new R(b,a+1,h+1,(h+this.firstWeek)%7,k,g,o++,d,t(e.yearCyl),t(e.monCyl),t(e.dayCyl++))}i=E(b,a*2)-1;j=E(b,a*2+1)-1;this[i].solarTerms=s[a*2];this[j].solarTerms=s[a*2+1];if(s[a*2]=="\u6e05\u660e")this[i].jiari="1";else if(s[a*2+1]==
"\u6e05\u660e")this[j].jiari="1";for(h in z)if(z[h].match(/^(\d{2})(\d{2})([\s\*])(.+)$/))if(Number(RegExp.$1)==a+1){this[Number(RegExp.$2)-1].solarFestival+=RegExp.$4;if(RegExp.$3=="*")this[Number(RegExp.$2)-1].jiari="1"}for(h in A)if(A[h].match(/^(\d{2})(.{2})([\s\*])(.+)$/)){i=Number(RegExp.$1)-n;if(i==-11)i=1;if(i>=0&&i<m){j=q[i]+Number(RegExp.$2)-1;if(j>=0&&j<this.length){this[j].lunarFestival+=RegExp.$4;if(RegExp.$3=="*")this[j].jiari="1"}}}for(h in B)if(B[h].match(/^(\d{2})(\d)(\d)([\s\*])(.+)$/))if(Number(RegExp.$1)==
a+1){i=Number(RegExp.$2);j=Number(RegExp.$3);var u=(this.firstWeek>j?7:0)+7*(i-1)+j-this.firstWeek;this[u].solarFestival+=RegExp.$5+" "}if(b==l.getFullYear()&&a==l.getMonth()){this[l.getDate()-1].isToday=true;ccal.returntoday.style.display="none"}else ccal.returntoday.style.display=""}function F(b){var a;switch(b){case 10:a="\u521d\u5341";break;case 20:a="\u4e8c\u5341";break;case 30:a="\u4e09\u5341";break;default:a=M[Math.floor(b/10)];a+=p[b%10]}return a}function T(b,a,c){var e=c[0].lYear;ccal.lunarStatus.innerHTML=
"\u519c\u5386"+t(c[0].lYear-1900+36)+"("+y[(c[0].lYear-4)%12]+")\u5e74";function k(d){var f=c[d].lunarFestival||c[d].solarFestival||c[d].solarTerms;if(f)c[d].color="#12b";else f=c[d].lDay==1?(c[d].isLeap?"\u95f0":"")+p[c[d].lMonth]+"\u6708":F(c[d].lDay);return f}function g(){var d=[];d.push("<tr>");for(var f=0;f<5;++f)d.push('<td class="ccal-weekdayheader">'+p[(f+1)%7]+"</td>");for(f=5;f<7;++f)d.push('<td class="ccal-weekendheader">'+p[(f+1)%7]+"</td>");d.push("</tr>");return d.join("")}function o(){var d=
[],f=0,i=c.firstWeek,j=c.length;function q(n,h,u){f||d.push("<tr>");if(n){var G=h;if(h&&h.charAt(0)!="<"&&h.a()>6)G=h.substr(0,2)+"..";d.push('<td class="ccal-',u?"today":f<5?"weekday":"weekend",'" onmouseover="return ',"ccal.onCellOver(event, this, '"+h+"', "+n+');"',' onmouseout="return ccal.onCellOut(event, this, '+n+');"',' onclick="return ccal.onCellClick(event, this, '+n+');">','<span style="display: block; padding: 0; margin-bottom: 3px;',(c[n-1].jiari?" color:#C60B02;":"")+'">'+n+"</span>",
'<span class="cdate" style="display: block; padding: 0;'," margin: 0;"+(u?"color: #fff":""),'">'+G+"</span></td>")}else d.push('<td class="ccal-',f<5?"weekday":"weekend",'">',"&nbsp;</td>");if(++f==7){d.push("</tr>");f=0}}for(i=(i+6)%7;i--;)q(0,0,0);for(var m=1;m<=j;++m)q(m,k(m-1),c[m-1].isToday);for(;f>0;)q(0,0,0);return d.join("")}return['<table class="ccal" cellspacing="0" cellpadding="3" width="100%">',g(),o(),"</table>"].join("")}function H(b){if(typeof b.disabled!="undefined")b.disabled=true;
else if(typeof b.buttonDisabled!="undefined")b.buttonDisabled=true}function I(b){if(typeof b.disabled!="undefined")b.disabled=false;else if(typeof b.buttonDisabled!="undefined")b.buttonDisabled=false}function x(){var b=ccal.cld[ccal.curDay-1];ccal.dayCal_yearmonth.innerHTML=b.sYear+"\u5e74"+b.sMonth+"\u6708";ccal.dayCal_date.innerHTML=b.sDay;ccal.dayCal_date.style.color=b.week==0||b.week==6||b.jiari?"#C60B02":"#333";ccal.dayCal_day.innerHTML="\u661f\u671f"+p[b.week];ccal.dayCal_enday.innerHTML=N[b.week];
ccal.dayCal_lunaryear.innerHTML=b.cYear+"("+y[(b.lYear-4)%12]+")\u5e74";ccal.dayCal_lunarmd.innerHTML=p[b.lMonth]+"\u6708"+F(b.lDay);ccal.dayCal_lunarfestival.innerHTML=b.lunarFestival||b.solarFestival||b.solarTerms;ccal.dayCal_lunargzmd.innerHTML=b.cMonth+"\u6708&nbsp;"+b.cDay+"\u65e5";var a=ccal.SM.selectedIndex;if(ccal.SY.selectedIndex==0&&a==0&&ccal.curDay==1)H(ccal.dayCal_prevday);else if(!ccal.dayCal_prevday.disabled||!ccal.dayCal_prevday.buttonDisabled)I(ccal.dayCal_prevday);a=ccal.SM.selectedIndex;
if(ccal.SY.selectedIndex==ccal.SY.length-1&&a==ccal.SM.length-1&&!ccal.cld[ccal.curDay])H(ccal.dayCal_nextday);else if(!ccal.dayCal_nextday.disabled||!ccal.dayCal_nextday.buttonDisabled)I(ccal.dayCal_nextday)}this.onCellOver=function(b,a,c,e){if(e){a.oldclass=a.className;a.className+="-mouseover";this.festivalStatus.innerHTML=c}};this.onCellOut=function(b,a,c){if(c){a.className=a.oldclass;this.festivalStatus.innerHTML=""}};this.onCellClick=function(b,a,c){c&&this.gotoDayCal(c)};this.prevDay=function(){this.curDay--;
if(!this.curDay){this.update("lastmonth");this.curDay=this.cld.length}x()};this.nextDay=function(){this.curDay++;if(!this.cld[this.curDay-1]){this.update("nextmonth");this.curDay=1}x()};this.gotoDayCal=function(b){this.curDay=b;x();this.dayCal.style.display="";this.monthCal.style.display="none";_IG_AdjustIFrameHeight();v.set("isMonthCal",0)};this.returnMonthCal=function(){this.dayCal.style.display="none";this.monthCal.style.display="";_IG_AdjustIFrameHeight();v.set("isMonthCal",1)};this.changeCld=
function(){var b,a;b=this.SY.selectedIndex+1901;a=this.SM.selectedIndex;if(this.SY.selectedIndex==0&&a==0){this.lastmonth.style.color="#9ab";this.lastmonth.style.cursor="default"}else if(this.lastmonth.style.cursor=="default"){this.lastmonth.style.color="";this.lastmonth.style.cursor="pointer"}if(this.SY.selectedIndex==this.SY.length-1&&a==this.SM.length-1){this.nextmonth.style.color="#9ab";this.nextmonth.style.cursor="default"}else if(this.nextmonth.style.cursor=="default"){this.nextmonth.style.color=
"";this.nextmonth.style.cursor="pointer"}this.cld=new S(b,a);this.container.innerHTML=T(b,a,this.cld);_IG_AdjustIFrameHeight();return false};this.update=function(b){switch(b){case "lastmonth":if(this.SM.selectedIndex>0)this.SM.selectedIndex--;else if(this.SY.selectedIndex>0){this.SM.selectedIndex=11;this.SY.selectedIndex--}break;case "nextmonth":if(this.SM.selectedIndex<11)this.SM.selectedIndex++;else if(this.SY.selectedIndex<this.SY.length-1){this.SM.selectedIndex=0;this.SY.selectedIndex++}break;
default:}return this.changeCld()};this.init=function(b){this.monthCal=_gel("Month_Cal");this.SY=_gel("SY");this.SM=_gel("SM");var a;for(a=1901;a<2050;a++){var c=document.createElement("option");c.value=a;c.appendChild(document.createTextNode(a));this.SY.appendChild(c)}for(a=1;a<13;a++){c=document.createElement("option");c.value=a;c.appendChild(document.createTextNode(a));this.SM.appendChild(c)}this.SY.selectedIndex=l.getFullYear()-1901;this.SM.selectedIndex=l.getMonth();this.returntoday=_gel("returntoday");
this.lastmonth=_gel("lastmonth");this.nextmonth=_gel("nextmonth");this.lunarStatus=_gel("lunarStatus");this.festivalStatus=_gel("festivalStatus");this.dayCal=_gel("Day_Cal");this.dayCal_yearmonth=_gel("DayCal_yearmonth");this.dayCal_date=_gel("DayCal_date");this.dayCal_day=_gel("DayCal_day");this.dayCal_enday=_gel("DayCal_enday");this.dayCal_lunarfestival=_gel("DayCal_lunarfestival");this.dayCal_lunaryear=_gel("DayCal_lunaryear");this.dayCal_lunarmd=_gel("DayCal_lunarmd");this.dayCal_lunargzmd=_gel("DayCal_lunargzmd");
this.dayCal_prevday=_gel("prevday");this.dayCal_nextday=_gel("nextday");this.container=_gel(b);this.changeCld();var e=v.getInt("isMonthCal");e==0&&this.gotoDayCal(l.getDate())};this.backToday=function(){l=new Date;this.SY.selectedIndex=l.getFullYear()-1901;this.SM.selectedIndex=l.getMonth();return this.changeCld()}}var ccal=new ChinaCalendar;_IG_RegisterOnloadHandler(function(){ccal.init("cal")});</SCRIPT>

<CENTER>
<TABLE class=ccal id=Month_Cal cellSpacing=0 cellPadding=0 width="100%">
  <TBODY>
  <TR>
    <TD>
      <TABLE class=ccal-header cellPadding=0 width="100%">
        <TBODY>
        <TR width="100%">
          <TD align=left width=30>&nbsp;</TD>
          <TD align=middle><A id=lastmonth title=上一月 
            onclick="return ccal.update('lastmonth');" 
            href="http://www.ig.gmodules.com/gadgets/ifr?exp_rpc_js=1&amp;exp_track_js=1&amp;url=http%3A%2F%2Fwww.google.com%2Fig%2Fmodules%2Fchinagadgets%2Fchinacalendar%2Fchinacalendar.xml&amp;container=ig&amp;view=default&amp;lang=zh-cn&amp;country=HK&amp;v=9bbeedcd0f6efea7&amp;parent=http://www.google.com.hk&amp;libs=core:core.io:core.iglegacy:auth-refresh&amp;is_signedin=1&amp;synd=ig&amp;view=default#"><<</A> 
            &nbsp;公元&nbsp;<SELECT class=input1 id=SY 
            onchange="return ccal.changeCld()"></SELECT>&nbsp;年&nbsp; <SELECT 
            class=input1 id=SM 
            onchange="return ccal.changeCld()"></SELECT>&nbsp;月&nbsp; <A 
            id=nextmonth title=下一月 onclick="return ccal.update('nextmonth');" 
            href="http://www.ig.gmodules.com/gadgets/ifr?exp_rpc_js=1&amp;exp_track_js=1&amp;url=http%3A%2F%2Fwww.google.com%2Fig%2Fmodules%2Fchinagadgets%2Fchinacalendar%2Fchinacalendar.xml&amp;container=ig&amp;view=default&amp;lang=zh-cn&amp;country=HK&amp;v=9bbeedcd0f6efea7&amp;parent=http://www.google.com.hk&amp;libs=core:core.io:core.iglegacy:auth-refresh&amp;is_signedin=1&amp;synd=ig&amp;view=default#">>></A> 
          </TD>
          <TD align=right width=30><A id=returntoday 
            onclick="return ccal.backToday();" 
            href="http://www.ig.gmodules.com/gadgets/ifr?exp_rpc_js=1&amp;exp_track_js=1&amp;url=http%3A%2F%2Fwww.google.com%2Fig%2Fmodules%2Fchinagadgets%2Fchinacalendar%2Fchinacalendar.xml&amp;container=ig&amp;view=default&amp;lang=zh-cn&amp;country=HK&amp;v=9bbeedcd0f6efea7&amp;parent=http://www.google.com.hk&amp;libs=core:core.io:core.iglegacy:auth-refresh&amp;is_signedin=1&amp;synd=ig&amp;view=default#">今天</A> 
          </TD></TR></TBODY></TABLE></TD></TR>
  <TR>
    <TD id=cal></TD></TR>
  <TR>
    <TD>
      <TABLE class=ccal-header width="100%">
        <TBODY>
        <TR>
          <TD id=lunarStatus align=left></TD>
          <TD id=festivalStatus 
align=right></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
<TABLE id=Day_Cal style="DISPLAY: none" height=280 cellSpacing=0 cellPadding=0 
width=290>
  <TBODY>
  <TR>
    <TD class=ccal-day0 height=35></TD></TR>
  <TR>
    <TD class=ccal-day1 id=DayCal_yearmonth height=35>2009年4月</TD></TR>
  <TR>
    <TD>
      <TABLE class=ccal-day id=Day_Cal height="100%" cellSpacing=0 cellPadding=0 
      width="100%">
        <TBODY>
        <TR>
          <TD align=middle width="55%">
            <TABLE>
              <TBODY>
              <TR>
                <TD class=ccal-day2 id=DayCal_date>20</TD></TR>
              <TR>
                <TD class=ccal-day3 id=DayCal_day>星期五</TD></TR>
              <TR>
                <TD class=ccal-day31 
          id=DayCal_enday>FRIDAY</TD></TR></TBODY></TABLE></TD>
          <TD class=ccal-day6 align=middle width="5%">&nbsp;</TD>
          <TD align=middle width="40%">
            <TABLE>
              <TBODY>
              <TR>
                <TD class=ccal-day4 id=DayCal_lunaryear>己丑[牛]年</TD></TR>
              <TR>
                <TD class=ccal-day4 id=DayCal_lunarmd>三月初八</TD></TR>
              <TR>
                <TD class=ccal-day5 id=DayCal_lunarfestival height=50>谷雨</TD></TR>
              <TR>
                <TD class=ccal-day4 
              id=DayCal_lunargzmd>戊辰月&nbsp;丙申日</TD></TR></TBODY></TABLE></TD></TR>
        <TR>
          <TD colSpan=3 height=40>
            <TABLE width="100%">
              <TBODY>
              <TR align=middle>
                <TD width="30%"><INPUT class=button id=prevday onclick="return ccal.prevDay();" type=button value=前一天 name=prevday></TD>
                <TD width="40%"><INPUT class=button id=returnlunar onclick="return ccal.returnMonthCal();" type=button value=返回月历 name=returnlunar></TD>
                <TD width="30%"><INPUT class=button id=nextday onclick="return ccal.nextDay();" type=button value=后一天 name=nextday></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></CENTER>
<SCRIPT>gadgets.util.runOnLoadHandlers();</SCRIPT>
</BODY></HTML>
