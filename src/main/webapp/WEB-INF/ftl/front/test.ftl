<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="${basePath}/front/">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>xxxx</title>
    <link href="${basePath}/statics/ui/select2/css/select2.min.css" rel="stylesheet" />
    <style>
        body{margin: 10px;}
    </style>
</head>
<body>
<select class="select2" name="userList"></select>

<script src="${basePath}/statics/js/jquery.min.js"></script>
<script src="${basePath}/statics/ui/select2/js/select2.full.min.js"></script>
<script src="${basePath}/statics/ui/select2/js/i18n/zh-CN.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        <@select2.init placeholder='请选择用户' url='userInfo/getUserList' multi='true'/>
    });
</script>
</body>
</html>
