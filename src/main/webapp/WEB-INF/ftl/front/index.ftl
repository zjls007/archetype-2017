<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="${basePath}/front/">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>牛X系统</title>
    <link rel="stylesheet" href="${basePath}/statics/ui/layui-2.2.5/css/layui.css">
    <link rel="stylesheet" href="${basePath}/statics/ui/tab/css/tabstyle-min.css">
    <style type="text/css">
        .layui-layout-admin .layui-body {
            bottom: 0px;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">牛X系统</div>
        <@shiro.hasRole name="sys_admin">
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item"><a href="${basePath}/admin/index">后台管理</a></li>
            </ul>
        </@shiro.hasRole>
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
                    <dd><a href="modifyPwd" class="easy-tab">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="${basePath}/admin/logout">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <#include "menu.ftl"/>
    </div>

    <div class="layui-body">
        <#include "tab.ftl"/>
    </div>
    <div class="layui-footer" style="display: none;height: 0px">
    </div>
</div>
<script src="${basePath}/statics/js/jquery.min.js"></script>
<script src="${basePath}/statics/ui/layui-2.2.5/layui.all.js"></script>
<script type="text/javascript">
    <@easyTab/>

    //JavaScript代码区域
    layui.use('element', function(){
        var $ = layui.jquery,
                layer = layui.layer,
                element = layui.element;
        $('.lay-tab-index i').remove();

        window.reLogin = function(){
            layer.open({
                content: '请重新登录!'
                ,closeBtn: 0
                ,btnAlign: 'c'
                ,btn: ['确定']
                ,yes: function(index, layero){
                    location.href = "";
                }
            });
        }
    });
</script>
</body>
</html>