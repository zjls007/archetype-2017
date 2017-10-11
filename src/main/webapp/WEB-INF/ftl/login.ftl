<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <base href="${basePath}">
    <title>系统登录</title>
    <link rel="stylesheet" type="text/css" href="/statics/js/easyui/easyui.css">
    <link rel="stylesheet" type="text/css" href="/statics/js/easyui/icon.css">
</head>
<body>
<div style="width: 400px;position: absolute;height: 185;left: 50%;top: 50%;margin-left: -200px;margin-top: -200px;">
    <div class="easyui-panel" title="登录" style="width:100%;max-width:400px;padding:30px 60px 10px 60px;">
        <form id="f-edit" method="post" action="login">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="principal" style="width:100%" data-options="label:'用户名:',required:true">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-passwordbox" name="credentials" style="width:100%" data-options="label:'密码:',required:true">
            </div>
         </form>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0);" class="easyui-linkbutton" id="submitForm" onclick="login()" style="width:80px">登录</a>
            <a href="javascript:void(0);" class="easyui-linkbutton" onclick="$('#f-edit').form('clear');" style="width:80px">重置</a>
        </div>
        <div style="text-align:center;padding:10px 0px 10px 0px">
            还没有账户？去<a href="regist" style="width:80px">注册</a>吧!
        </div>
    </div>
</div>

<script type="text/javascript" src="/statics/js/jquery.min.js"></script>
<script type="text/javascript" src="/statics/js/ajaxFileUpload.js"></script>
<script type="text/javascript" src="/statics/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/statics/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/statics/js/jquery.form.js"></script>
<script type="text/javascript" src="/statics/js/app-custom.js"></script>
<script type="text/javascript">
    function login() {
        var form = $('#f-edit');
        if (form.form('validate')) {
            form.ajaxSubmit({
                success: function (data) {
                    if (data.code == 0) {
                        location.href = 'index';
                    } else {
                        $.messager.alert('错误',data.message,'error');
                    }
                },
                error: function(xhr, status, error) {
                    $.messager.alert('错误','登录失败!','error');
                }
            });
        }
    }
</script>
</body>
</html>