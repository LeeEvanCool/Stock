<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src='/stockWeb/js/echarts.min.js'></script>
<script type="text/javascript" src="/stockWeb/js/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Echarts测试</title>
<script type="text/javascript">
$().ready(function() {
	$.ajax({
		url: "../findList.do",
		type: "POST",
		data: {},
		dataType: "json",
		cache: false,
		success: function(data) {
			delRow(data.name, data.xdata, data.data);
		}
	});
});
function delRow(name, xdata, data){
	//基于准备好的dom，初始化echarts图表
	var myChart = echarts.init(document.getElementById('main'));

	var option = {
		    title : {
		        text: '指数变化',
		        subtext: '个人采集'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:[name]
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : xdata
		        }
		    ],
		    yAxis : [
		        {
		        	type: 'value',
	                scale: true,
	                precision: 2,
	                splitNumber: 9,
	                boundaryGap: [0.01, 0.01],
	                splitArea: { show: true } 
		        }
		    ],
		    series : [
		        {
		            name:'最高',
		            type:'line',
		            data:data,
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            },
		            markLine : {
		                data : [
		                    {type : 'average', name: '平均值'}
		                ]
		            }
		        }
		    ]
		};
	// 为echarts对象加载数据 
	myChart.setOption(option);
}
</script>
</head>
<body>
	<div id="main" style="height: 400px"></div>
	<input type="button" value="aha">
</body>
</html>