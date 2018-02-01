<@override name="body">
<form class="layui-form" method="post" action="${modelName!}/saveOrUpdate">
<style>
    .layui-form-mid {
        width: 240px;
    }
    .layui-layedit-focus {
        border-color: #FF5722!important;
    }
</style>
<div class="box-refresh">
    <a href="javascript:location.replace(location.href);" title="刷新"><i class="fa"></i></a>
</div>

<div style="position: fixed;top: 0px;left: 0px;z-index: 9999;background-color: #fff;height: 40px;border-bottom: 1px solid #e6e6e6;width: 100%">
    <span class="layui-breadcrumb" lay-separator="/" style="margin-left: 40px;margin-top: 8px;display: block">
    <#if navigation??>
        <a href="javascript:void(0)" onclick="javascript:parent.activateTab('${navigation!}');">${navigation!}</a>
    </#if>
      <a><cite><@block name="cite"></@block></cite></a>
    </span>

</div>
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