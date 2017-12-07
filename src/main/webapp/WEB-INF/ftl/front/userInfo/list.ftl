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
<div class="layui-tab layui-tab-brief" lay-filter="reFulsh">
    <ul class="layui-tab-title">
        <li class="layui-this">用户管理</li>
        <li class=""><i class="layui-icon">&#x1002;</i>刷新</li>
    </ul>
    <div class="layui-tab-content">
    </div>
</div>
<blockquote class="layui-elem-quote layui-quote-nm">
    <button class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon">&#xe608;</i>添加</button>
    <button class="layui-btn layui-btn-primary layui-btn-sm"><i class="layui-icon">&#xe640;</i>批量删除</button>
    <button class="layui-btn layui-btn-primary layui-btn-sm query"><i class="layui-icon">&#xe615;</i>搜索</button>
    <button class="layui-btn layui-btn-primary layui-btn-sm reset"><i class="layui-icon">&#xe633;</i>重置</button>
    <div style="height: 20px"></div>
    <form class="layui-form" action="" id="f-query">
        <button class="layui-btn layui-btn-primary layui-btn-sm reset" style="display: none"><i class="layui-icon">&#xe633;</i>重置</button>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户名：</label>
                <div class="layui-input-inline">
                    <input type="text" name="userName" placeholder="用户名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">真实姓名：</label>
                <div class="layui-input-inline">
                    <input type="text" name="fullName" placeholder="真实姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">手机号码：</label>
                <div class="layui-input-inline">
                    <input type="text" name="mobileNo" placeholder="手机号码" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">注册时间：</label>
                <div class="layui-input-inline">
                    <input type="text" name="createTimeBegin" id="createTimeBegin" placeholder="注册时间范围开始" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">~</label>
                <div class="layui-input-inline">
                    <input type="text" name="createTimeEnd" id="createTimeEnd" placeholder="注册时间范围结束" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <select name="accountLocked" lay-verify="required" lay-search="">
                        <option value="">---请选择---</option>
                        <option value="0">未锁定</option>
                        <option value="1">已锁定</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
</blockquote>
<table class="layui-table" id="dg" lay-filter="data" lay-data="{height: 'full-300', page: true, limit:30, url:'front/userInfo/data'}">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox'}"></th>
        <th lay-data="{field: 'userName', width:120}">用户名</th>
        <th lay-data="{field:'mobilePhoneNumber', width: 120}">手机号码</th>
        <th lay-data="{field:'email', width: 120}">邮箱</th>
        <th lay-data="{field:'fullName', width:120}">真实姓名</th>
        <th lay-data="{field:'telNo', width:120}">电话号码</th>
        <th lay-data="{field:'createTime', width:180, align:'center'}">注册时间</th>
        <th lay-data="{field:'accountLocked', width:120, sort: true, templet: '#accountLocked', unresize: true}">锁定状态</th>
        <th lay-data="{minWidth:120,align:'left', toolbar: '#bar'}">操作</th>
    </tr>
    </thead>
</table>
<script type="text/html" id="accountLocked">
    <input type="checkbox" name="lock" value="{{d.id}}" title="锁定" lay-filter="accountLocked" {{ d.accountLocked == 1 ? 'checked' : '' }}>
</script>

<script type="text/html" id="bar">
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
                ,laydate = layui.laydate
                ,form = layui.form
                ,$ = layui.$
                ,element = layui.element; //元素操作

        //监听Tab切换
        element.on('tab(reFulsh)', function(data){
            location.reload();
        });

        //监听锁定操作
        form.on('checkbox(accountLocked)', function(obj){
            $.ajax({
                async: true,
                type: 'POST',
                url: 'front/userInfo/changeLockState',
                data: {userInfoId:this.value, accountLocked:obj.elem.checked==true?"1":"0"},
                dataType: 'json',
                success: function (data) {
                    if (data.code == 0) {
                    } else {
                        $.messager.alert('错误',data.message,'error');
                    }
                }
            });
        });

        //常规用法
        laydate.render({
            elem: '#createTimeBegin'
        });

        laydate.render({
            elem: '#createTimeEnd'
        });

        $('.query').on('click', function(){
            var param = {};
            var jsonArray = $('#f-query').serializeArray();
            for (var i = 0; i < jsonArray.length; i++) {
                var name = jsonArray[i].name;
                var value = jsonArray[i].value;
                param[name] = value;
            }

            //执行重载
            table.reload('dg', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: param
            });
        });

        $('.reset').on('click', function () {
            alert(1);
            form.resetForm();
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
