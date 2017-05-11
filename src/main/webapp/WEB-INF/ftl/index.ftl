<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="/statics/js/easyui/easyui.css">
    <link rel="stylesheet" type="text/css" href="/statics/js/easyui/icon.css">
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:60px;"><a href="logout" class="easyui-linkbutton">退出</a></div>
<div data-options="region:'west',split:false,collapsed:false,title:'West'" style="width:220px;padding:10px;">
    <a href="javascript:void(0)" onclick="addPanel()" class="easyui-linkbutton">瞄瞄</a>
</div>
<div data-options="region:'south',border:false" style="height:20px;text-align: center">copyright@2017</div>
<div data-options="region:'center',title:'Center',noheader:true,border:false">
    <div id="tt" class="easyui-tabs" data-options="tabWidth:180,fit:true">
    </div>
</div>

<script type="text/javascript" src="/statics/js/jquery.min.js"></script>
<script type="text/javascript" src="/statics/js/ajaxFileUpload.js"></script>
<script type="text/javascript" src="/statics/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/statics/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/statics/js/jquery.form.js"></script>
<script type="text/javascript" src="/statics/js/app-custom.js"></script>
<script>
    var index = 0;
    function addPanel(){
        src = "userInfo/list";
        $('#tt').tabs('add',{
            title: 'Tab',
            tabWidth: 180,
            href:src,
            closable: true
        });
    }
</script>
</body>
</html>