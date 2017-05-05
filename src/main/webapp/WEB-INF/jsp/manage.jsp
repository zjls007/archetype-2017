<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>列表</title>
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
                     url:'resources',
                     method:'get'">
    <thead>
    <tr>
        <th data-options="field:'a',checkbox:true,"></th>
        <th data-options="field:'resourceId',width:250">资源id</th>
        <th data-options="field:'groupName',width:200">组名</th>
        <th data-options="field:'remoteFileName',width:350,align:'left'">远程文件名</th>
        <th data-options="field:'extName',width:200">扩展名</th>
        <th data-options="field:'fileName',width:200">原始文件名</th>
        <th data-options="field:'fileSize',width:200">文件大小</th>
        <th data-options="field:'createTime',width:200,align:'left'">上传时间</th>
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
