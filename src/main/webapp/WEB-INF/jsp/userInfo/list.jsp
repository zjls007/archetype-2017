<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<html>
<head>
    <title>列表</title>
    <base href="<%=path%>">
    <link rel="stylesheet" type="text/css" href="/statics/js/easyui/easyui.css">
    <link rel="stylesheet" type="text/css" href="/statics/js/easyui/icon.css">
    <script type="text/javascript" src="/statics/js/jquery.min.js"></script>
    <script type="text/javascript" src="/statics/js/ajaxFileUpload.js"></script>
    <script type="text/javascript" src="/statics/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/statics/js/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        function query() {
            $('#dg').datagrid('load',{
                createTimeBegin: '2017-3-27',
                createTimeEnd: '2017-4-1'
            });
        }
        function ajaxFileUpload() {
            $.ajaxFileUpload
            (
                    {
                        url: '/resources', //用于文件上传的服务器端请求地址
                        secureuri: false, //是否需要安全协议，一般设置为false
                        fileElementId: 'file1', //文件上传域的ID
                        dataType: 'json', //返回值类型 一般设置为json
                        type:'post',
                        success: function (data, status)  //服务器成功响应处理函数
                        {
                            alert(data.resourceId);
                        },
                        error: function (data, status, e)//服务器响应失败处理函数
                        {
                            alert(e);
                        }
                    }
            )
            return false;
        }
    </script>
</head>
<body>
<table class="easyui-datagrid" id="dg"
       data-options="singleSelect:true,
                     collapsible:false,
                     fit:true,
                     rownumbers:true,
                     noheader:true,
                     toolbar:'#tb',
                     pagination:true,
                     url:'userInfo/data',
                     method:'get'">
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
</body>
</html>
