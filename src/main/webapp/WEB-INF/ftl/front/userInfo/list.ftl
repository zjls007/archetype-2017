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
<div>
    真实姓名：
    <div class="layui-inline">
        <input class="layui-input" name="fullName" id="fullName" autocomplete="off">
    </div>
    <button class="layui-btn query" data-type="reload">搜索</button>
</div>
<table class="layui-hide" id="dg" lay-filter="demo"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="statics/ui/layui-2.2.3/layui.js"></script>
<script>
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
        var laydate = layui.laydate //日期
                ,laypage = layui.laypage //分页
                ,layer = layui.layer //弹层
                ,table = layui.table //表格
                ,element = layui.element; //元素操作

        //执行一个 table 实例
        table.render({
            elem: '#dg',
            height: 'full-200',
            url: 'front/userInfo/data', //数据接口
            page: true, //开启分页
            method: 'post',
            cols: [[ //表头
                {checkbox: true, fixed: true},
                {field: 'userName', title: '用户名', width:200},
                {field: 'fullName', title: '真实姓名', sort: true, width:200},
                {field: 'telNo', title: '电话号码', width:200},
                {field: 'mobileNo', title: '手机号', width:200},
                {fixed: 'right', title: '操作', align:'left', toolbar: '#barDemo'}
            ]]
        });

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
        table.on('tool(demo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
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
