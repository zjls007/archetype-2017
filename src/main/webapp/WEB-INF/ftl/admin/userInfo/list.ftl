<@override name="menu">
    <div data-options="businessURL:'userInfo/refRoleInfo/',iconCls:'icon-undo'">角色</div>
    <div data-options="businessURL:'userInfo/refRoleInfo/',iconCls:'icon-undo'">用户组</div>
</@override>
<@override name="th">
    <th data-options="field:'id',checkbox:true"></th>
    <th data-options="field:'operater',align:'center',width:80,<@formatterMenuButton/>">操作</th>
    <th data-options="field:'perm',width:80,align:'center',<@formatterNative/>">系统初始数据</th>
    <th data-options="field:'userName',width:200">用户名</th>
    <th data-options="field:'fullName',width:200">真实姓名</th>
    <th data-options="field:'telNo',width:200,align:'left'">电话号码</th>
    <th data-options="field:'mobileNo',width:200">手机号码</th>
    <th data-options="field:'signNo',width:200">登录次数</th>
    <th data-options="field:'accountLocked',width:200,formatter:formatterAccountLocked">账户是否锁定</th>
    <th data-options="field:'createTime',width:200,align:'left'">注册时间</th>
    <th data-options="field:'modifyTime',width:200,align:'left'">更新时间</th>
</@override>
<@override name="query">
    <@queryInput type='textbox' name='userName' label='用户名'/>
    <@queryInput type='textbox' name='fullName' label='真实姓名'/>
    <@queryInput type='textbox' name='telNo' label='电话号码'/>
    <@queryInput type='textbox' name='mobileNo' label='手机号码'/>
    <@br/>
    <@queryInput type='combobox' name='accountLocked' label='锁定状态' value="[{id:'-1',text:'--- 请选择 ---',selected:true},{id:0,text:'未锁定'},{id:1,text:'锁定'}]"/>
    <@queryInput type='datebox' name='createTimeBegin,createTimeEnd' label='注册时间'/>
</@override>
<@override name="script">
<script type="text/javascript">
    function formatterAccountLocked(value,row,index) {
        if (value == 1) {
            return "锁定";
        }
        return "未锁定";
    }
</script>
</@override>
<@extends name="../templete/datagrid.ftl"/>