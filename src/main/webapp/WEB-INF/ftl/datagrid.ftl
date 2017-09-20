<@override name="body">
<table class="easyui-datagrid" id="dg"
       data-options="singleSelect:false,
                    collapsible:false,
                    rownumbers:true,
                    noheader:true,
                    border:false,
                    fit:true,
                    onLoadSuccess:onLoadSuccess,
                    toolbar:'#tb-userInfo',
                    pagination:true,
                    url:'/${url}',
                    queryParams:queryParams(),
                    method:'post'">
    <thead>
    <tr>
        <th data-options="field:'id',checkbox:true"></th>
        <th data-options="field:'name',width:200">角色名称</th>
        <th data-options="field:'code',width:200">角色编码</th>
        <th data-options="field:'createTime',width:200,align:'left'">注册时间</th>
        <th data-options="field:'modifyTime',width:200,align:'left'">更新时间</th>
    </tr>
    </thead>
</table>
</@override>
<@extends name="base.ftl"/>