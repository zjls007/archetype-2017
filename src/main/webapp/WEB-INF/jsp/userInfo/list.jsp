<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<html>
<head>
    <title>列表</title>
    <base href="<%=path%>">
    <link rel="stylesheet" type="text/css" href="/statics/js/easyui/easyui.css">
    <link rel="stylesheet" type="text/css" href="/statics/js/easyui/icon.css">
</head>
<body>
<table class="easyui-datagrid" id="dg"
       data-options="singleSelect:true,
                     collapsible:false,
                     fit:true,
                     rownumbers:true,
                     onLoadSuccess:onLoadSuccess,
                     noheader:true,
                     toolbar:'#tb',
                     pagination:true,
                     url:'userInfo/data',
                     queryParams:{ajaxOrigin:'datagrid'},
                     method:'post'">
    <thead>
    <tr>
        <th data-options="field:'id',checkbox:true,"></th>
        <th data-options="field:'userName',width:250">用户名</th>
        <th data-options="field:'fullName',width:200">真实姓名</th>
        <th data-options="field:'telNo',width:350,align:'left'">电话号码</th>
        <th data-options="field:'mobileNo',width:200">手机号码</th>
        <th data-options="field:'signNo',width:200">登录次数</th>
        <th data-options="field:'accountLocked',width:200">账户是否锁定</th>
        <th data-options="field:'createTime',width:200,align:'left'">创建时间</th>
        <th data-options="field:'modifyTime',width:200,align:'left'">更新时间</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$('#w').window('open')">上传</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    <br>
    上传时间: <input class="easyui-datebox" style="width:110px">
    ~: <input class="easyui-datebox" style="width:110px">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="query()">搜索</a>
</div>

<div id="w" class="easyui-window" title="文件上传" data-options="iconCls:'icon-save',closed:true,collapsible:false" style="width:500px;height:200px;padding:10px;">
    <p><input type="file" id="file1" name="file" /></p>
    <input type="button" value="上传" onclick="ajaxFileUpload()"/>
</div>

<script type="text/javascript" src="/statics/js/jquery.min.js"></script>
<script type="text/javascript" src="/statics/js/ajaxFileUpload.js"></script>
<script type="text/javascript" src="/statics/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/statics/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    function onLoadSuccess(data) {
        if (data.error) {
            alert(data.error);
            location.href="index";
        }
    }
</script>
</body>
</html>
