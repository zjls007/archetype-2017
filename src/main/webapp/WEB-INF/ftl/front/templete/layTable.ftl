<@override name="body">
<div class="layui-tab layui-tab-brief" lay-filter="reFulsh">
    <ul class="layui-tab-title">
        <li class="layui-this">用户管理</li>
        <li class=""><i class="layui-icon">&#x1002;</i>刷新</li>
    </ul>
    <div class="layui-tab-content">
    </div>
</div>
<blockquote class="layui-elem-quote layui-quote-nm">
    <!-- 图标地址：http://www.layui.com/doc/element/icon.html -->
    <button class="layui-btn layui-btn-primary layui-btn-sm add"><i class="layui-icon">&#xe608;</i>添加</button>
    <button class="layui-btn layui-btn-primary layui-btn-sm batchDelete"><i class="layui-icon">&#xe640;</i>批量删除</button>
    <button class="layui-btn layui-btn-primary layui-btn-sm query"><i class="layui-icon">&#xe615;</i>搜索</button>
    <button class="layui-btn layui-btn-primary layui-btn-sm reset"><i class="layui-icon">&#xe633;</i>重置</button>

    <button class="layui-btn layui-btn-primary layui-btn-sm import" style="margin-left: 50px"><i class="layui-icon">&#xe61e;</i>下载模板</button>
    <button class="layui-btn layui-btn-primary layui-btn-sm import"><i class="layui-icon">&#xe62f;</i>导入</button>
    <button class="layui-btn layui-btn-primary layui-btn-sm export"><i class="layui-icon">&#xe601;</i>导出</button>
    <div style="height: 20px"></div>
    <form class="layui-form" action="" id="f-query">
        <button class="layui-btn layui-btn-primary layui-btn-sm" type="reset" id="reset" style="display: none">重置</button>
        <@block name="form">
        </@block>
    </form>
</blockquote>
<table class="layui-table" id="dg" lay-filter="data" lay-data="{height: 'full-300', page: true, limit:10, url:'front/userInfo/data'}">
    <thead>
    <tr>
        <@block name="th">
        </@block>
    </tr>
    </thead>
</table>
<script type="text/html" id="accountLocked">
    <input type="checkbox" name="lock" value="{{d.id}}" title="锁定" lay-filter="accountLocked" {{ d.accountLocked == 1 ? 'checked' : '' }} {{ d.id == 1 ? 'disabled' : '' }}>
</script>

<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</@override>
<@override name="script">
<script>
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
        var laydate = layui.laydate //日期
                , table = layui.table //  表单
                ,layer = layui.layer //弹层
                ,laydate = layui.laydate
                ,form = layui.form
                ,$ = layui.$
                ,element = layui.element; //元素操作

        var onError = function (xhr, status, error) {
            var statuCode = xhr.status;
            if (!statuCode) {
                statuCode = status.status;
            }
            // 未登录
            if (statuCode == 403) {
                if (window.parent.length == 0) {
                    layer.open({
                        content: '请重新登录!'
                        ,closeBtn: 0
                        ,btnAlign: 'c'
                        ,btn: ['确定']
                        ,yes: function(index, layero){
                            location.href = "";
                        }
                    });
                } else {
                    parent.reLogin();
                }
            } else if (statuCode == 404) {
                layer.msg('请求地址不存在!');
            } else if (statuCode == 500) {
                layer.msg('服务器内部错误!');
            } else if (statuCode == 0) {
                layer.msg('服务器已关闭!');
            }else {
                layer.msg('未知异常!');
            }
        };

        // jquery全局ajax设置
        $(document).ajaxError(onError);

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
                        layer.msg(obj.elem.checked ? "账号已锁定!" : "账号已取消锁定!");
                    } else {
                        layer.msg("锁定失败!");
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

        $('.add').on('click', function () {
            parent.newTab('添加用户', 'front/userInfo/edit');
        });

        $('.import').on('click', function () {
            layer.msg("暂无功能!");
        });

        $('.export').on('click', function () {
            layer.msg("暂无功能!");
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
            $('#reset').click();
            layer.msg('搜索条件已重置!');
        });

        $('.batchDelete').on('click', function () {
            var checkStatus = table.checkStatus('dg')
                    ,data = checkStatus.data;
            var idList=[];
            for (var i = 0; i < data.length; i++) {
                idList.push(data[i].id);
            }
            if (idList.length == 0) {
                layer.msg('请选择数据!');
            } else {
                delFn(idList);
            }
        });

        var delFn = function (idList) {
            layer.confirm('确定要删除吗?', function(index){
                layer.close(index);
                $.ajax({
                    async: true,
                    type: 'POST',
                    url: 'front/userInfo/delete',
                    data: JSON.stringify(idList),
                    dataType: 'json',
                    contentType:"application/json",
                    success: function (data) {
                        if (data.code == 0) {
                            table.reload('dg');
                        } else {
                            layer.msg(data.message);
                        }
                    }
                });
            });
        };

        window.reloadDG = function () {
            table.reload('dg');
        };

        //监听工具条
        table.on('tool(data)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                parent.newTab('用户查看', 'front/userInfo/edit/' + data.id);
            } else if(layEvent === 'del'){
                delFn([data.id]);
            } else if(layEvent === 'edit'){
                parent.newTab('用户查看', 'front/userInfo/edit/' + data.id);
            }
        });
    });
</script>
</@override>
<@extends name="/base.ftl"/>