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
option = {
    title : {
        text: '店铺合格率',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br />{b} : {c} ({d}%)"
    },
    legend: {
        orient : 'vertical',
        x : 'left',
        data:['合格指标','不合格指标']
    },
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '55%',
            center: ['50%', 225],
            data:[
                ${passjson}
            ]
        }
    ]
};

option2 = {
    title : {
        text: '最近三次店铺合格率对比',
        x:'center'
    },
    tooltip : {
        trigger: 'axis',
        axisPointer : {
            type: 'shadow'
        }
    },
    xAxis : [
        {
            type : 'category',
            data : [${passq}]
        }
    ],
    yAxis : [
        {
            type : 'value',
            splitArea : {show : true}
        }
    ],
    grid: {
        x2:40
    },
    series : [
        {
            name:'合格指标',
            type:'bar',
            stack: '总量',
            data:[${passqy}]
        },
        {
            name:'不合格指标',
            type:'bar',
            stack: '总量',
            data:[${passqn}]
        },
    ]
};

myChart2 = echarts.init(document.getElementById('main2'));
myChart2.setOption(option2);

myChart.connect(myChart2);
myChart2.connect(myChart);

setTimeout(function(){
    window.onresize = function() {
        myChart.resize();
        myChart2.resize();
    }
},200)
                    </textarea>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			<div id="graphic" class="span8">
				<div id="title" class="title">
					<div class="row-fluid">
						<div class="span12" style="text-align: center;">
							<h4>${requestScope.storename}</h4>
							<p>${requestScope.taskname}&nbsp;${requestScope.xddate}</p>
							<p>巡店员：${requestScope.user}</p>

						</div>
					</div>
				</div>
			</div>
			<div id="main" class="main"></div>
			<div id="main2" class="main"></div>
			<div class="container">
				<div class="row">
					<div class="span12">
						<ul class="thumbnails">
							<li class="span4">
								<div class="thumbnail">${requestScope.infohtml}</div>
					</div>

					</li>


					</ul>

				</div>
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