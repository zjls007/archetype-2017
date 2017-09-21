<@override name="th">
    <th data-options="field:'id',checkbox:true,"></th>
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
    <input class="easyui-textbox" name="userName" data-options="label:'用户名:',required:false,width:240"/>
    <input class="easyui-textbox" name="fullName" data-options="label:'真实姓名:',required:false,width:240"/>
    <input class="easyui-textbox" name="telNo" data-options="label:'电话号码:',required:false,width:240"/>
    <input class="easyui-textbox" name="mobileNo" data-options="label:'手机号码:',required:false,width:240"/>
    <input class="easyui-datebox" name="createTimeBegin" data-options="label:'注册时间:',required:false,width:200" >~
    <input class="easyui-datebox" name="createTimeEnd" data-options="label:'',required:false,width:110" >
</@override>
<@override name="form">
    <@formInput type='textbox' name='userName' label='用户名' required='true'/>
    <@formInput type='passwordbox' name='password' label='密码' required='true'/>
    <@formInput type='textbox' name='fullName' label='真实姓名' required='false'/>
    <@formInput type='textbox' name='telNo' label='电话号码' required='false'/>
    <@formInput type='textbox' name='mobileNo' label='手机号码' required='false'/>
    <@formInput type='combobox' name='accountLocked' label='锁定状态' required='true' value="[{id:0,text:'未锁定',selected:true},{id:1,text:'锁定'}]"/>
</@override>
<@override name="script">
    function formatterAccountLocked(value,row,index) {
        if (value == 1) {
            return "锁定";
        }
        return "未锁定";
    }
</@override>
<@extends name="../templete/datagrid.ftl"/>