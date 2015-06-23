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
	<style>

	</style>
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
        text: '个人巡店率',
        subtext: '巡店累积次数:${count}',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        x : 'center',
        y : 'bottom',
        data:[${namestr}]
    },

    series : [
       
        {
            name:'巡店次数',
            type:'pie',
            radius : [30, 110],
            center : ['50%', '50%'],
            roseType : 'radius',
            data:[
               ${countstr}
            ]
        }
    ]
};
                    
                    </textarea>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->

			<div id="main" class="main" ></div>
			
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