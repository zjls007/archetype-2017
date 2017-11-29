<@override name="menu">
    <div data-options="businessURL:'roleInfo/refPermissionMenu/',iconCls:'icon-undo'">菜单权限</div>
    <div data-options="businessURL:'roleInfo/refPermissionPage/',iconCls:'icon-undo'">页面权限</div>
</@override>
<@override name="th">
    <th data-options="field:'id',checkbox:true"></th>
    <th data-options="field:'operater',align:'center',width:80,<@formatterMenuButton/>">操作</th>
    <th data-options="field:'hasSettingMenu',width:120,align:'center',<@formatterNative/>">是否设置过菜单权限</th>
    <th data-options="field:'getHasSettingPage',width:120,align:'center',<@formatterNative/>">是否设置过页面权限</th>
    <th data-options="field:'native',width:120,align:'center',<@formatterNative/>">系统初始数据</th>
    <th data-options="field:'name',width:200">角色名称</th>
    <th data-options="field:'code',width:200">角色编码</th>
    <th data-options="field:'createTime',width:200,align:'left'">创建时间</th>
    <th data-options="field:'lstUpdTime',width:200,align:'left'">更新时间</th>
</@override>
<@override name="query">
    <@queryInput type='textbox' name='name' label='名称'/>
    <@queryInput type='textbox' name='code' label='编码'/>
    <@br/>
    <@queryInput type='combobox' name='nativeState' label='初始化数据' value="[{id:'',text:'--- 请选择 ---',selected:true},{id:0,text:'否'},{id:1,text:'是'}]"/>
    <@queryInput type='combobox' name='hasSettingMenu' label='设置过菜单' value="[{id:'',text:'--- 请选择 ---',selected:true},{id:0,text:'否'},{id:1,text:'是'}]"/>
    <@queryInput type='combobox' name='getHasSettingPage' label='设置过页面' value="[{id:'',text:'--- 请选择 ---',selected:true},{id:0,text:'否'},{id:1,text:'是'}]"/>
</@override>
<@extends name="/templete/datagrid.ftl"/>