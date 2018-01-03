<@override name="body">
<div class="layui-tab layui-tab-brief" lay-filter="reFulsh">
    <ul class="layui-tab-title">
        <li class="layui-this">${modelName!}</li>
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

    <button class="layui-btn layui-btn-primary layui-btn-sm download" style="margin-left: 50px"><i class="layui-icon">&#xe61e;</i>下载模板</button>
    <button class="layui-btn layui-btn-primary layui-btn-sm import"><i class="layui-icon">&#xe62f;</i>导入</button>
    <button class="layui-btn layui-btn-primary layui-btn-sm export"><i class="layui-icon">&#xe601;</i>导出</button>
    <div style="height: 20px"></div>
    <form class="layui-form" action="" id="f-query">
        <button class="layui-btn layui-btn-primary layui-btn-sm" type="reset" id="reset" style="display: none">重置</button>
        <@block name="form">
        </@block>
    </form>
</blockquote>
<table class="layui-table" id="dg" lay-filter="data" lay-data="{height: 'full-300', page: true, limit:10, url:'${dataUrl!}'}">
    <thead>
    <tr>
        <@block name="th">
        </@block>
    </tr>
    </thead>
</table>
<@block name="tableBar">
</@block>
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

        <#include "jqueryError.ftl"/>

        //监听Tab切换
        element.on('tab(reFulsh)', function(data){
            location.reload();
        });

        //常规用法
        laydate.render({
            elem: '#createTimeBegin'
        });

        laydate.render({
            elem: '#createTimeEnd'
        });

        $('.add').on('click', function () {
            parent.newTab('添加用户', '${editUrl!}');
        });

        $('.download,.import,.export').on('click', function () {
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
                    url: '${delUrl!}',
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
                parent.newTab('用户查看', 'userInfo/edit/' + data.id);
            } else if(layEvent === 'del'){
                delFn([data.id]);
            } else if(layEvent === 'edit'){
                parent.newTab('用户查看', 'userInfo/edit/' + data.id);
            }
        });
        <@block name="otherScript">
        </@block>
    });
</script>
</@override>
<@extends name="/base.ftl"/>