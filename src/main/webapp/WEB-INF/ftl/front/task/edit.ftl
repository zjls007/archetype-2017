<@override name="body">
<div class="layui-tab layui-tab-brief" lay-filter="reFulsh">
    <ul class="layui-tab-title">
        <li class="layui-this">${modelNameCN!}信息</li>
        <li class=""><i class="layui-icon">&#x1002;</i>刷新</li>
    </ul>
    <div class="layui-tab-content">
    </div>
</div>
<blockquote class="layui-elem-quote layui-quote-nm">
    <form class="layui-form" method="post" action="task/saveOrUpdate">
        <input type="hidden" name="id" value="${(entity.id)!}">

        <div class="layui-form-item">
            <label class="layui-form-label">任务标题</label>
            <div class="layui-input-block">
                <input type="text" name="title" value="${(entity.title)!}" lay-verify="title" autocomplete="off" placeholder="请输入任务标题" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">任务描述</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea layui-hide" name="content" lay-verify="content" id="editor">${(entity.content)!}</textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-primary layui-btn-sm add" lay-submit="" lay-filter="submit"><i class="layui-icon">&#xe610;</i>提交</button>
                <button class="layui-btn layui-btn-primary layui-btn-sm reset" type="reset"><i class="layui-icon">&#xe633;</i>重置</button>
            </div>
        </div>
    </form>
</blockquote>
</@override>
<@override name="script">
<script>
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
        var laydate = layui.laydate //日期
                , table = layui.table //  表单
                ,layer = layui.layer //弹层
                ,laydate = layui.laydate
                ,layedit = layui.layedit
                ,form = layui.form
                ,$ = layui.$
                ,element = layui.element; //元素操作

        <#include "../templete/jqueryError.ftl"/>

        //监听Tab切换
        element.on('tab(reFulsh)', function(data){
            location.reload();
        });

        //常规用法
        laydate.render({
            elem: '#birthday'
        });

        //创建一个编辑器
        var editIndex = layedit.build('editor');

        //自定义验证规则
        form.verify({
            userName: function(value){
                var errorMsg = $('form').data('errorMsg');
                if (errorMsg) {
                    return errorMsg;
                }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        //监听提交
        form.on('submit(submit)', function(data){
            $.ajax({
                async: true,
                type: 'POST',
                url: 'task/saveOrUpdate',
                data: data.field,
                dataType: 'json',
                success: function (data) {
                    if (data.code == 'success') {
                        layer.msg("保存成功!");
                        parent.closeActive('用户管理', true);
                    } else {
                        layer.msg(data.message, {time:0});
                    }
                }
            });
            return false;
        });
    });
</script>
</@override>
<@extends name="/base.ftl"/>
