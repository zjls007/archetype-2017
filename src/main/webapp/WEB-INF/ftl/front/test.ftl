<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="${basePath}/front/">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>xxxx</title>
    <link href="${basePath}/statics/ui/select2/css/select2.min.css" rel="stylesheet" />
    <link href="${basePath}/statics/ui/webuploader-0.1.5/webuploader.css" rel="stylesheet" />
    <link href="${basePath}/statics/css/style.css" rel="stylesheet" />
    <style>
        body{margin: 10px;}
    </style>
</head>
<body>
<#import "common/webuploader.ftl" as webuploader>
<!--dom结构部分-->
<div id="uploader-demo">
    <!--用来存放item-->
    <div id="fileList" class="uploader-list"></div>
    <div id="filePicker">选择图片</div>
</div>

<@webuploader.html size=5 name="a.b" marginLeft="110"/>
<@webuploader.html size=5 name="b.c" marginLeft="50"/>

<script src="${basePath}/statics/js/jquery.min.js"></script>
<script src="${basePath}/statics/ui/select2/js/select2.full.min.js"></script>
<script src="${basePath}/statics/ui/select2/js/i18n/zh-CN.js"></script>
<script src="${basePath}/statics/ui/webuploader-0.1.5/webuploader.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        <@webuploader.init/>
    });
</script>
</body>
</html>
