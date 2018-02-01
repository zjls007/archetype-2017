<@override name="cite">编辑</@override>
<@override name="baseEditBody">
<blockquote class="layui-elem-quote layui-quote-nm">
    <input type="hidden" name="id" value="${(entity.id)!}">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名：</label>
        <div class="layui-input-inline">
            <input type="text" name="userName" value="${(entity.userName)!}" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"><font color="red">*</font>必填</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">手机号码：</label>
        <div class="layui-input-inline">
            <input type="text" name="mobilePhoneNumber" value="${(entity.mobilePhoneNumber)!}" lay-verify="" placeholder="请输入手机号码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
        <label class="layui-form-label">电话号码：</label>
        <div class="layui-input-inline">
            <input type="text" name="telNo" value="${(entity.telNo)!}" lay-verify="" placeholder="请输电话号码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">邮箱：</label>
        <div class="layui-input-inline">
            <input type="text" name="email" value="${(entity.email)!}" lay-verify="" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">姓名：</label>
        <div class="layui-input-inline">
            <input type="text" name="fullName" value="${(entity.fullName)!}" lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"><font color="red">*</font>必填</div>

        <label class="layui-form-label">出生年月：</label>
        <div class="layui-input-inline">
            <input type="text" name="birthday" value="${(entity.birthday?string("yyyy-MM-dd"))!}" id="birthday" placeholder="请输入出生年月" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">性别：</label>
        <div class="layui-input-inline">
            <input type="radio" name="sex" value="Man" title="男" ${((entity.sex=='Man')?string('checked', ''))!}>
            <input type="radio" name="sex" value="Woman" title="女" ${((entity.sex=='Woman')?string('checked', ''))!}>
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
    </div>
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
            content: function(value){
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
                        if (data.code == 'param_error') {
                            var d = JSON.parse(data.message);
                            var input = $('input[name="' + d.field + '"]');
                            if (input) {
                                input.addClass("layui-form-danger");
                                input.focus();
                            }
                            layer.msg(d.msg, {icon: 5, shift: 6});
                        } else {
                            layer.msg(data.message, {time:3000});
                        }
                    }
                }
            });
            return false;
        });
    });
</script>
</@override>
<@extends name="../templete/baseEdit.ftl"/>
