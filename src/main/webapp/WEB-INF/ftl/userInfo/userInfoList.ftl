<html>
<head>
    <title>列表</title>
</head>
<body>
<table class="easyui-datagrid" id="dg-userInfo"
       data-options="singleSelect:true,
                     collapsible:false,
                     fit:true,
                     rownumbers:true,
                     onLoadSuccess:onLoadSuccess,
                     noheader:true,
                     toolbar:'#tb-userInfo',
                     pagination:true,
                     url:'userInfo/data',
                     queryParams:queryParams(),
                     method:'post'">
    <thead>
    <tr>
        <th data-options="field:'id',checkbox:true,"></th>
        <th data-options="field:'userName',width:200">用户名</th>
        <th data-options="field:'fullName',width:200">真实姓名</th>
        <th data-options="field:'telNo',width:200,align:'left'">电话号码</th>
        <th data-options="field:'mobileNo',width:200">手机号码</th>
        <th data-options="field:'signNo',width:200">登录次数</th>
        <th data-options="field:'accountLocked',width:200">账户是否锁定</th>
        <th data-options="field:'createTime',width:200,align:'left'">注册时间</th>
        <th data-options="field:'modifyTime',width:200,align:'left'">更新时间</th>
    </tr>
    </thead>
</table>
<div id="tb-userInfo" style="padding:2px 5px;">
    <form id="f-query-userInfo">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="$('#w-userInfo').window('open')">注册新用户</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    <br>
    注册时间: <input class="easyui-datebox" style="width:110px">
    ~ <input class="easyui-datebox" style="width:110px">
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="dgQuery('userInfo')">搜索</a>
    </form>
</div>

<div id="w-userInfo" class="easyui-window" title="注册用户" data-options="iconCls:'icon-save',closed:true,collapsible:false" style="width:500px;height:200px;padding:10px;">
    <div style="text-align:center;padding:5px 0">
    <form id="f-userInfo-regist" method="post" action="regist">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="userName" style="width:240px" data-options="label:'用户名:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-passwordbox"" name="password" style="width:240px" data-options="label:'密码:',required:true">
        </div>
    </form>
        </div>
    <div style="text-align:center;padding:5px 0">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('f-userInfo-regist')" style="width:80px">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm('f-userInfo-regist')" style="width:80px">重置</a>
    </div>
</div>
</body>
</html>
