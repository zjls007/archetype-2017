<@override name="form">
    <@formInput type='textbox' name='userName' label='用户名' required='true'/>
    <@formInput type='textbox' name='fullName' label='真实姓名' required='false'/>
    <@formInput type='textbox' name='telNo' label='电话号码' required='false'/>
    <@formInput type='textbox' name='mobileNo' label='手机号码' required='false'/>
    <@formInput type='combobox' name='accountLocked' label='锁定状态' required='true' value="[{id:0,text:'未锁定',selected:true},{id:1,text:'锁定'}]"/>
</@override>
<@extends name="/templete/edit.ftl"/>