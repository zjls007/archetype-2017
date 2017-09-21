<@override name="th">
    <th data-options="field:'id',checkbox:true"></th>
    <th data-options="field:'name',width:200">角色名称</th>
    <th data-options="field:'code',width:200">角色编码</th>
    <th data-options="field:'createTime',width:200,align:'left'">创建时间</th>
    <th data-options="field:'lstUpdTime',width:200,align:'left'">更新时间</th>
</@override>
<@override name="query">
    <input class="easyui-textbox" id="q-textbox-name" name="name" style="width:240px;margin-left: 5px;" data-options="label:'名称:'"/>
    <input class="easyui-textbox" id="q-textbox-code" name="code" style="width:240px;margin-left: 5px;" data-options="label:'编码:'"/>
</@override>
<@override name="form">
    <div style="margin-bottom:20px">
        <input class="easyui-textbox" id="f-textbox-type" name="type" style="width:240px" data-options="label:'类型:',required:true"/>
    </div>
    <div style="margin-bottom:20px">
        <input class="easyui-textbox" id="f-textbox-dataId" name="dataId" style="width:240px" data-options="label:'数据id:',required:true"/>
    </div>
    <div style="margin-bottom:20px">
        <input class="easyui-textbox" id="f-textbox-name" name="name" style="width:240px" data-options="label:'名称:',required:true"/>
    </div>
    <div style="margin-bottom:20px">
        <input class="easyui-textbox" id="f-textbox-code" name="code" style="width:240px" data-options="label:'编码:',required:true"/>
    </div>
</@override>
<@extends name="../templete/datagrid.ftl"/>