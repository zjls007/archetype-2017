<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="${basePath}">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>layui在线调试</title>
    <link rel="stylesheet" href="statics/ui/layui-2.2.3/css/layui.css">
    <style>
        body{margin: 10px;}
    </style>
</head>
<body>
<div style="height: 80px;">
    真实姓名：
    <div class="layui-inline">
        <input class="layui-input" name="fullName" id="fullName" autocomplete="off">
    </div>
    <button class="layui-btn query" data-type="reload">搜索</button>
</div>
<table class="layui-table" id="dg" lay-filter="data" lay-data="{height: 'full-120', cellMinWidth: 80, page: true, limit:30, url:'front/userInfo/data'}">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox'}">ID</th>
        <th lay-data="{field: 'userName', width:200}">用户名</th>
        <th lay-data="{field:'fullName', width:100}">真实姓名</th>
        <th lay-data="{field:'telNo', width:100, sort: true}">电话号码</th>
        <th lay-data="{field:'mobileNo', minWidth: 150}">手机号</th>
        <th lay-data="{fixed: 'right', align:'left', toolbar: '#barDemo'}">操作</th>
    </tr>
    </thead>
</table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="statics/ui/layui-2.2.3/layui.js"></script>
<script>
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
        var laydate = layui.laydate //日期
                , table = layui.table //  表单
                ,layer = layui.layer //弹层
                ,element = layui.element; //元素操作

        var $ = layui.$, active = {
            reload: function(){
                var fullName = $('#fullName');
                //执行重载
                table.reload('dg', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        fullName: fullName.val()
                    }
                });
            }
        };

        $('.query').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        //监听工具条
        table.on('tool(data)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                parent.newTab('用户查看', 'front/userInfo/edit');
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){
                layer.msg(data.id);
            }
        });
    });
</script>
</body>
</html>
