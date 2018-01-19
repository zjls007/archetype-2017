<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="${basePath}/front/">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>牛x系统</title>
    <link rel="stylesheet" href="${basePath}/statics/ui/layui-2.2.5/css/layui.css">
    <link rel="stylesheet" href="${basePath}/statics/ui/select2/css/select2.min.css">
    <link href="${basePath}/statics/ui/webuploader-0.1.5/webuploader.css" rel="stylesheet" />
    <link href="${basePath}/statics/css/style.css" rel="stylesheet" />
    <style>
        body{margin: 10px;}
    </style>
    <#import "common/webuploader.ftl" as webuploader>
    <#import "common/select2.ftl" as select2>
</head>
<body>
<@block name="body">
</@block>
<script src="${basePath}/statics/ui/layui-2.2.5/layui.all.js"></script>
<script src="${basePath}/statics/js/jquery.min.js"></script>
<script src="${basePath}/statics/ui/select2/js/select2.full.min.js"></script>
<script src="${basePath}/statics/ui/select2/js/i18n/zh-CN.js"></script>
<script src="${basePath}/statics/ui/webuploader-0.1.5/webuploader.min.js"></script>
<@block name="script">
</@block>
</body>
</html>
