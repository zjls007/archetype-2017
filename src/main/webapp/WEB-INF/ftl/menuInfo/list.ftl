<@override name="th">
    <th data-options="field:'name',width:180,editor:'textbox',styler:styler">菜单名称</th>
    <th data-options="field:'url',width:180,editor:'textbox',styler:styler">URL</th>
    <th data-options="field:'native',width:80,align:'center',<@formatterNative/>">系统初始数据</th>
    <th data-options="field:'btn',align:'center',width:140,formatter:formatter">操作</th>
</@override>
<@extends name="/templete/treegrid.ftl"/>