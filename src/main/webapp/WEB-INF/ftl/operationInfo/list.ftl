<@override name="th">
    <th data-options="field:'id',checkbox:true"></th>
    <th data-options="field:'name',width:200">操作名称</th>
    <th data-options="field:'code',width:200">操作编码</th>
    <th data-options="field:'createTime',width:200,align:'left'">创建时间</th>
    <th data-options="field:'lstUpdTime',width:200,align:'left'">更新时间</th>
</@override>
<@override name="query">
    <@queryInput type='textbox' name='name' label='名称'/>
    <@queryInput type='textbox' name='code' label='编码'/>
</@override>
<@extends name="../templete/datagrid.ftl"/>