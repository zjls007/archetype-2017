<@override name="form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户名：</label>
            <div class="layui-input-inline">
                <input type="text" name="userName" placeholder="用户名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">真实姓名：</label>
            <div class="layui-input-inline">
                <input type="text" name="fullName" placeholder="真实姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">手机号码：</label>
            <div class="layui-input-inline">
                <input type="text" name="mobileNo" placeholder="手机号码" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">状态：</label>
            <div class="layui-input-inline">
                <select name="accountLocked" lay-verify="required" lay-search="">
                    <option value="">---请选择---</option>
                    <option value="0">未锁定</option>
                    <option value="1">已锁定</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">注册时间：</label>
            <div class="layui-input-inline" >
                <input type="text" name="createTimeBegin" id="createTimeBegin" placeholder="注册时间范围开始" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid">~</div>
            <div class="layui-input-inline">
                <input type="text" name="createTimeEnd" id="createTimeEnd" placeholder="注册时间范围结束" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
</@override>
<@override name="th">
    <th lay-data="{type:'checkbox'}"></th>
    <th lay-data="{field: 'userName', width:120}">用户名</th>
    <th lay-data="{field:'mobilePhoneNumber', width: 120}">手机号码</th>
    <th lay-data="{field:'email', width: 120}">邮箱</th>
    <th lay-data="{field:'fullName', width:120}">真实姓名</th>
    <th lay-data="{field:'telNo', width:120}">电话号码</th>
    <th lay-data="{field:'createTime', width:180, align:'center'}">注册时间</th>
    <th lay-data="{field:'lastLoginTime', width:180, align:'center'}">最后登录时间</th>
    <th lay-data="{field:'accountLocked', width:120, sort: true, templet: '#accountLocked', unresize: true}">锁定状态</th>
    <th lay-data="{minWidth:180,align:'left', toolbar: '#bar'}">操作</th>
</@override>
<@extends name="../templete/layTable.ftl"/>