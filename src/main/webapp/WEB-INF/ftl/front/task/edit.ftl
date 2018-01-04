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
    <form class="layui-form" method="post" action="userInfo/saveOrUpdate">
        <input type="hidden" name="id" value="${(userInfo.id)!}">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名1111：</label>
            <div class="layui-input-inline">
                <input type="text" name="userName" value="${(userInfo.userName)!}" lay-verify="userName" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"><font color="red">*</font>必填</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">手机号码：</label>
            <div class="layui-input-inline">
                <input type="text" name="mobilePhoneNumber" value="${(userInfo.mobilePhoneNumber)!}" lay-verify="" placeholder="请输入手机号码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
            <label class="layui-form-label">电话号码：</label>
            <div class="layui-input-inline">
                <input type="text" name="telNo" value="${(userInfo.telNo)!}" lay-verify="" placeholder="请输电话号码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">邮箱：</label>
            <div class="layui-input-inline">
                <input type="text" name="email" value="${(userInfo.email)!}" lay-verify="" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">姓名：</label>
            <div class="layui-input-inline">
                <input type="text" name="fullName" value="${(userInfo.fullName)!}" lay-verify="" placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>

            <label class="layui-form-label">出生年月：</label>
            <div class="layui-input-inline">
                <input type="text" name="birthday" value="${(userInfo.birthday?string("yyyy-MM-dd"))!}" id="birthday" placeholder="请输入出生年月" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">性别：</label>
            <div class="layui-input-inline">
                <input type="radio" name="sex" value="Man" title="男" ${((userInfo.sex=='Man')?string('checked', ''))!}>
                <input type="radio" name="sex" value="Woman" title="女" ${((userInfo.sex=='Woman')?string('checked', ''))!}>
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

        //常规用法
        laydate.render({
            elem: '#birthday'
        });

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
                url: 'userInfo/saveOrUpdate',
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
