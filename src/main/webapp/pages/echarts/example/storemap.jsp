<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/include/header.jsp" %>
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

    <link href="${ctx}/pages/echarts/asset/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/pages/echarts/asset/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="${ctx}/pages/echarts/asset/css/codemirror.css" rel="stylesheet">
    <link href="${ctx}/pages/echarts/asset/css/monokai.css" rel="stylesheet">
    <link href="${ctx}/pages/echarts/asset/css/echartsHome.css" rel="stylesheet">
    <link rel="shortcut icon" href="${ctx}/pages/echarts/asset/ico/favicon.png">
</head>

<body>

    <div class="container-fluid">
        <div class="row-fluid">
            <div id="sidebar-code" class="span4" style="display: none">
                <div class="well sidebar-nav">
                    <div class="nav-header"><a href="#" onclick="autoResize()" class="icon-resize-full" id ="icon-resize" ></a>option</div>
                    <textarea id="code" name="code">
option = {
    title : {
        text: '全国门店分布图',
      
        x:'center'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        orient: 'vertical',
        x:'left',
        data:['门店']
    },


    series : [
        {
            name: '门店',
            type: 'map',
            mapType: 'china',
            hoverable: false,
             roam:true,
            
            data : [],
            markPoint : {
                symbolSize: 5,       // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
                itemStyle: {
                    normal: {
                        borderColor: '#87cefa',
                        borderWidth: 1,            // 标注边线线宽，单位px，默认为1
                        label: {
                            show: false
                        }
                    },
                    emphasis: {
                        borderColor: '#1e90ff',
                        borderWidth: 5,
                        label: {
                            show: false
                        }
                    }
                },
                data : [
                    
                  ${storenamejson}
                ]
            },
            geoCoord: {
               
                 ${storepositionjson}

            }
        }
    ]
};
                    
                    
                    
                    </textarea>
              </div><!--/.well -->
            </div><!--/span-->
            <div id="graphic" class="span8">
                <div id="main" class="main"></div>
            </div><!--/span-->
        </div><!--/row-->
        <hr>
    </div><!--/.fluid-container-->

    <script src="${ctx}/pages/echarts/asset/js/jquery.js"></script>
    <script src="${ctx}/pages/echarts/asset/js/echartsExample.js?v=2"></script>
</body>
</html>