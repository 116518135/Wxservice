<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ECharts</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="ECharts">
<meta name="author" content="linzhifeng@baidu.com">

<script src="${ctx}/pages/echarts/asset/js/esl/esl.js"></script>
<script src="${ctx}/pages/echarts/asset/js/codemirror.js"></script>
<script src="${ctx}/pages/echarts/asset/js/javascript.js"></script>

<link href="${ctx}/pages/echarts/asset/css/bootstrap.css"
	rel="stylesheet">
<link href="${ctx}/pages/echarts/asset/css/bootstrap-responsive.css"
	rel="stylesheet">
<link href="${ctx}/pages/echarts/asset/css/codemirror.css"
	rel="stylesheet">
<link href="${ctx}/pages/echarts/asset/css/monokai.css" rel="stylesheet">
<link href="${ctx}/pages/echarts/asset/css/echartsHome.css?v=1"
	rel="stylesheet">
<link rel="shortcut icon"
	href="${ctx}/pages/echarts/asset/ico/favicon.png">
</head>

<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<div id="sidebar-code" class="span4" style="display: none">
				<div class="well sidebar-nav">
					<div class="nav-header">
						<a href="#" onclick="autoResize()" class="icon-resize-full"
							id="icon-resize"></a>option
					</div>
					<textarea id="code" name="code">
var effect = {
    show: true,
    scaleSize: require('zrender/tool/env').canvasSupported ? 1 : 2,
    period: 30,             // 运动周期，无单位，值越大越慢
    color: '#fff',
    shadowColor: 'rgba(220,220,220,0.8)',
    shadowBlur : 15 
};
function itemStyle(idx) {
    return {
        normal: {
            color:'#fff',
            borderWidth:1,
            borderColor:['rgba(30,144,255,1)','lime'][idx],
            lineStyle: {
                type: 'solid',
                shadowColor : ['rgba(30,144,255,1)','lime'][idx], //默认透明
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowOffsetY: 0
            }
        }
    }
};
option = {
    backgroundColor: '#1b1b1b',
    color: ['rgba(30,144,255,1)','lime'],
    title : {
        text: '个人巡店员路线图',
        subtext:'',
        sublink: '',
        x:'center',
        textStyle : {
            color: '#fff'
        }
    },
    tooltip : {
        trigger: 'item',
        formatter: function(v) {
            return v[1].replace(':', ' > ');
        }
    },
    legend: {
        orient: 'vertical',
        x:'left',
        selectedMode:'single',
        data:[],
        textStyle : {
            color: '#fff'
        }
    },
    toolbox: {
        show : true,
        orient : 'vertical',
        x: 'right',
        y: 'center',
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    series : [
        {
            name: '八纵通道',
            type: 'map',
            roam: true,
            hoverable: false,
            mapType: 'china',
            itemStyle:{
                normal:{
                    borderColor:'rgba(100,149,237,1)',
                    borderWidth:0.5,
                    areaStyle:{
                        color: '#1b1b1b'
                    }
                }
            },
            data:[],
            markLine : {
                symbol: ['circle', 'circle'],  
                symbolSize : 1,
                effect : effect,
                itemStyle : itemStyle(0),
                smooth:true,
                data : [
                   ${storename}
                ]
            },
            geoCoord: {
                  ${storelola}
            }
        }
    ]
};
                    
                                </textarea>
				</div>
				<!--/.well -->
			</div>

			<div id="graphic" class="span8">
				<div class="row-fluid">
					<div class="span12" style="text-align: center;">
						<h4>巡店任务：第三季度巡店任务</h4>
					</div>
				</div>
				<!--/span-->
				<div id="main" class="main"></div>
			</div>
		</div>

	</div>
	<!--/span-->
	</div>
	<!--/row-->
	<hr>

	</div>
	<!--/.fluid-container-->
	<script src="${ctx}/pages/echarts/asset/js/jquery.js"></script>
	<script src="${ctx}/pages/echarts/asset/js/echartsExample.js?v=2"></script>
</body>
</html>