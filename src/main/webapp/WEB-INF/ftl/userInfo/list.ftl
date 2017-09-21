<@override name="th">
    <th data-options="field:'id',checkbox:true,"></th>
    <th data-options="field:'userName',width:200">用户名</th>
    <th data-options="field:'fullName',width:200">真实姓名</th>
    <th data-options="field:'telNo',width:200,align:'left'">电话号码</th>
    <th data-options="field:'mobileNo',width:200">手机号码</th>
    <th data-options="field:'signNo',width:200">登录次数</th>
    <th data-options="field:'accountLocked',width:200">账户是否锁定</th>
    <th data-options="field:'createTime',width:200,align:'left'">注册时间</th>
    <th data-options="field:'modifyTime',width:200,align:'left'">更新时间</th>
</@override>
<@override name="query">
    注册时间: <input class="easyui-datebox" id="q-textbox-name"  style="width:110px">
    ~ <input class="easyui-datebox" id="q-textbox-name" style="width:110px">
</@override>
<@override name="form">
    <@formInput type='textbox' name='userName' label='用户名' required='true'/>
    <@formInput type='passwordbox' name='password' label='密码' required='true'/>
    <@formInput type='textbox' name='fullName' label='真实姓名' required='false'/>
    <@formInput type='textbox' name='telNo' label='电话号码' required='false'/>
    <@formInput type='textbox' name='mobileNo' label='手机号码' required='false'/>
    <div style="margin-bottom:20px">
        <select id="f-textbox-accountLocked" class="easyui-combobox" name="accountLocked" data-options="label:'锁定状态:',required:true,width:240">
            <option value="0">未锁定</option>
            <option value="1">锁定</option>
        </select>
    </div>
</@override>
<@extends name="../templete/datagrid.ftl"/>