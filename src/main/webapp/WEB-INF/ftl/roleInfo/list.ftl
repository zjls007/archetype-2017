<@override name="body">
<table class="easyui-datagrid" id="dg"
       data-options="singleSelect:false,
                    collapsible:false,
                    rownumbers:true,
                    noheader:true,
                    border:false,
                    fit:true,
                    onLoadSuccess:onLoadSuccess,
                    onDblClickRow:onDblClickRow,
                    toolbar:'#tb',
                    pagination:true,
                    url:'/${dataUrl}',
                    queryParams:queryParams(),
                    method:'post'">
    <thead>
    <tr>
        <th data-options="field:'id',checkbox:true"></th>
        <th data-options="field:'name',width:200">角色名称</th>
        <th data-options="field:'code',width:200">角色编码</th>
        <th data-options="field:'createTime',width:200,align:'left'">创建时间</th>
        <th data-options="field:'lstUpdTime',width:200,align:'left'">更新时间</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    <div>
        <div style="padding: 5px 8px 10px 8px">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addData()">添加</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editData()">修改</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delData('/${delUrl}')">删除</a>
        </div>
        <div style="padding: 0px 8px 10px 8px">
            <form id="f-query">
                <input class="easyui-textbox" id="q-textbox-name" name="name" style="width:240px;margin-left: 5px;" data-options="label:'名称:'"/>
                <input class="easyui-textbox" id="q-textbox-code" name="code" style="width:240px;margin-left: 5px;" data-options="label:'编码:'"/>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="dgQuery()">搜索</a>
            </form>
        </div>
    </div>
</div>
<div id="w" class="easyui-window" title="弹窗" data-options="iconCls:'icon-save',closed:true,collapsible:false" style="width:500px;height:200px;padding:10px;">
    <div id="d-edit" style="text-align:center;padding:5px 0">
        <form id="f-edit" method="post" action="/${actionUrl}">
            <input type="hidden" name="id" value=""/>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" id="f-textbox-name" name="name" style="width:240px" data-options="label:'名称:',required:true"/>
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" id="f-textbox-code" name="code" style="width:240px" data-options="label:'编码:',required:true"/>
            </div>
        </form>
    </div>
    <div style="text-align:center;padding:5px 0">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#f-edit').form('clear');" style="width:80px">重置</a>
    </div>
</div>
</@override>
<@extends name="../datagrid.ftl"/>