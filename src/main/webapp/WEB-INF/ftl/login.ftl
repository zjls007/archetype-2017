<!DOCTYPE html>
<html>
<head>
    <title>登录页面</title>
    <link rel="stylesheet" type="text/css" href="/statics/js/easyui/easyui.css">
    <link rel="stylesheet" type="text/css" href="/statics/js/easyui/icon.css">
</head>
<body>
<div style="width: 400px;position: absolute;height: 185;left: 50%;top: 50%;margin-left: -200px;margin-top: -200px;">
<div class="easyui-panel" title="登录" style="width:100%;max-width:400px;padding:30px 60px;">
    <form id="ff" method="post" action="signIn">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="principal" style="width:100%" data-options="label:'用户名:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-passwordbox"" name="credentials" style="width:100%" data-options="label:'密码:',required:true">
        </div>
    </form>
    <div style="text-align:center;padding:5px 0">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">登录</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">重置</a>
    </div>
</div>
</div>
<script type="text/javascript" src="/statics/js/jquery.min.js"></script>
<script type="text/javascript" src="/statics/js/ajaxFileUpload.js"></script>
<script type="text/javascript" src="/statics/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/statics/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/statics/js/jquery.form.js"></script>
<script>
    function submitForm(){
        $("#ff").ajaxSubmit({
            success : function(data) {
                if (data.code == 0) {
                    location.href="index";
                    return;
                }
                alert(data.message);
            }
        });
    }
    function clearForm(){
        $('#ff').form('clear');
    }
</script>
</body>
</html>