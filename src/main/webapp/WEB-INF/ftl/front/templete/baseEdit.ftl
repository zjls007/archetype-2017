<@override name="body">
<form class="layui-form" method="post">
<style>
    .layui-form-mid {
        width: 240px;
    }
    .layui-layedit-focus {
        border-color: #FF5722!important;
    }
</style>
<div style="padding-top: 65px;padding-bottom: 50px;">
    <@block name="baseEditBody">
    </@block>
</div>
<div style="position: fixed; bottom: 0px; left: 0px;background-color: #fff;width: 100%;z-index: 9999;border-top: 1px solid #e6e6e6">
    <div style="margin-left: 80px;margin-top: 8px" class="layui-form">
        <@block name="bottomBtn">
            <button class="layui-btn layui-btn-sm add" lay-submit lay-filter="submit" id="submit"><i class="layui-icon">&#xe610;</i>提交</button>
            <button class="layui-btn layui-btn-sm reset" type="reset"><i class="layui-icon">&#xe633;</i>重置</button>
        </@block>
    </div>
</div>
</form>
</@override>
<@override name="script">
</@override>
<@extends name="/base.ftl"/>