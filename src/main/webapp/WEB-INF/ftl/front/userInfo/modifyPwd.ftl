<@override name="body">
<div class="layui-tab layui-tab-brief" lay-filter="reFulsh">
    <ul class="layui-tab-title">
        <li class="layui-this">用户信息</li>
        <li class=""><i class="layui-icon">&#x1002;</i>刷新</li>
    </ul>
    <div class="layui-tab-content">
    </div>
</div>
<blockquote class="layui-elem-quote layui-quote-nm">
    <form class="layui-form" method="post" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">旧密码：</label>
            <div class="layui-input-inline">
                <input type="password" name="oldPassword" value="" lay-verify="required" placeholder="请输旧密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">初始密码为：123</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">新密码：</label>
            <div class="layui-input-inline">
                <input type="password" name="password" value="" lay-verify="required" placeholder="请输新密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">密码确认：</label>
            <div class="layui-input-inline">
                <input type="password" name="passwordConfirm" value="" lay-verify="required" placeholder="请输密码确认" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
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
                ,form = layui.form
                ,$ = layui.$
                ,element = layui.element; //元素操作

        <#include "../templete/jqueryError.ftl"/>

        //监听Tab切换
        element.on('tab(reFulsh)', function(data){
            location.reload();
        });

        //监听提交
        form.on('submit(submit)', function(data){
            $.ajax({
                async: true,
                type: 'POST',
                url: 'modifyPwd',
                data: data.field,
                dataType: 'json',
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg("修改成功!");
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
