<@override name="cite">查看</@override>
<@override name="baseEditBody">
<blockquote class="layui-elem-quote layui-quote-nm">
    <input type="hidden" name="task.id" value="${(entity.id)!}">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.userName)!}</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">手机号码：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.mobilePhoneNumber)!}</div>
        <label class="layui-form-label">电话号码：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.telNo)!}</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">邮箱：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.email)!}</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">姓名：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.fullName)!}</div>
        <label class="layui-form-label">出生年月：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.birthday?string("yyyy-MM-dd"))!}</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">性别：</label>
        <div class="layui-form-mid layui-word-aux">${((entity.sex=='Woman')?string('女', '男'))!}</div>
    </div>
</blockquote>
</@override>
<@override name="bottomBtn">
<#if (entity.showBeginBtn)!>
<button class="layui-btn layui-btn-primary layui-btn-sm add" id="submit"><i class="layui-icon">&#xe623;</i>开始</button>
</#if>
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
    });
</script>
</@override>
<@extends name="../templete/baseEdit.ftl"/>
