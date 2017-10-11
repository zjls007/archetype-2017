<@override name="menu">
    <div data-options="businessURL:'roleInfo/refPermission/',iconCls:'icon-undo'">权限</div>
</@override>
<@override name="th">
    <th data-options="field:'id',checkbox:true"></th>
    <th data-options="field:'operater',align:'center',width:80,<@formatterMenuButton/>">操作</th>
    <th data-options="field:'name',width:200">角色名称</th>
    <th data-options="field:'code',width:200">角色编码</th>
    <th data-options="field:'createTime',width:200,align:'left'">创建时间</th>
    <th data-options="field:'lstUpdTime',width:200,align:'left'">更新时间</th>
</@override>
<@override name="query">
    <@queryInput type='textbox' name='name' label='名称'/>
    <@queryInput type='textbox' name='code' label='编码'/>
</@override>
<@extends name="../templete/datagrid.ftl"/>