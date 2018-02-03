<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="${basePath}/front/">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>牛X系统</title>
    <link rel="stylesheet" href="${basePath}/statics/ui/layui-2.2.5/css/layui.css">
    <link rel="stylesheet" href="${basePath}/statics/ui/tab/css/tabstyle-min.css">
</head>
<body>

<div class="layui-row">
    <div class="layui-col-xs6">
        <div style="padding: 40px 40px 20px 140px;">
            <#include "home/pendingTask.ftl"/>
        </div>
    </div>
    <div class="layui-col-xs6">
        <div style="padding: 40px 140px 20px 40px;">
            <table class="layui-table" id="dg" lay-filter="data" lay-data="{height: '240', page: true, limit:10, url:'${dataUrl!}'}">
                <thead>
                <tr>
                    <th lay-data="{field: 'taskNum', width:400}">待认领任务</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<div class="layui-row">
    <div class="layui-col-xs6">
        <div style="padding: 40px 40px 20px 140px;">
            <table class="layui-table" id="dg" lay-filter="data" lay-data="{height: '240', page: true, limit:10, url:'${dataUrl!}'}">
                <thead>
                <tr>
                    <th lay-data="{field: 'taskNum', width:400}">已完成任务</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
    <div class="layui-col-xs6">
        <div style="padding: 40px 140px 20px 40px;">
        </div>
    </div>
</div>



<#--<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>-->
<script src="${basePath}/statics/js/jquery.min.js"></script>
<script src="${basePath}/statics/ui/tab/tab-min.js"></script>
<script src="${basePath}/statics/ui/layui-2.2.5/layui.all.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['table'], function(){
        var table = layui.table;
        //监听单元格事件
        table.on('tool(pendingTask)', function(obj){
            var data = obj.data;
            if(obj.event === 'taskView'){
                parent.newTab('任务查看', 'task/view/home/'+data.id);
            }
        });
    });
</script>
</body>
</html>