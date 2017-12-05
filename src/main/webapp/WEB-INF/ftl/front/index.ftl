<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="${basePath}">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>牛X系统</title>
    <link rel="stylesheet" href="statics/ui/layui-2.2.3/css/layui.css">
    <link rel="stylesheet" href="statics/ui/tab/css/tabstyle-min.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">牛X系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="admin/index">后台管理</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="">消息<span class="layui-badge">0</span></a>
            </li>
            <li class="layui-nav-item">
                <a href="">个人中心<span class="layui-badge-dot"></span></a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                ${userInfo.fullName!}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="admin/logout">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <#include "menu.ftl"/>
    </div>

    <div class="layui-body">
        <#include "tab.ftl"/>
    </div>
    <div class="layui-footer" style="text-align: center">
        copyright@2017
    </div>
</div>
<#--<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>-->
<script src="statics/js/jquery.min.js"></script>
<script src="statics/ui/tab/tab-min.js"></script>
<script src="statics/ui/layui-2.2.3/layui.js"></script>
<script>
    //初始化a标签链接到tab
    $("a.tab").tab();
    //JavaScript代码区域
    layui.use('element', function(){
        var $ = layui.jquery,
                element = layui.element;
        $('.lay-tab-index i').remove();
    });
</script>
</body>
</html>