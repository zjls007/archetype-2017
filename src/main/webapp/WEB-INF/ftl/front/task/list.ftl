<@override name="form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">编号：</label>
            <div class="layui-input-inline">
                <input type="text" name="taskNum" placeholder="编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">标题：</label>
            <div class="layui-input-inline">
                <input type="text" name="title" placeholder="标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">难度：</label>
            <div class="layui-input-inline">
                <select name="difficult" lay-search="">
                    <option value="">---请选择---</option>
                    <@util.select optionList=selectMap['task.difficult']/>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">类型：</label>
            <div class="layui-input-inline">
                <select name="type" lay-search="">
                    <option value="">---请选择---</option>
                    <@util.select optionList=selectMap['task.type']/>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">状态：</label>
            <div class="layui-input-inline">
                <select name="state" lay-search="">
                    <option value="">---请选择---</option>
                    <@util.select optionList=selectMap['task.state']/>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">来源：</label>
            <div class="layui-input-inline">
                <select name="source" lay-search="">
                    <option value="1">我发布的</option>
                    <option value="2">我认领的</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">截止日期：</label>
            <div class="layui-input-inline" >
                <input type="text" name="dueDateBegin" id="dueDateBegin" placeholder="截止日期范围开始" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid">~</div>
            <div class="layui-input-inline">
                <input type="text" name="dueDateEnd" id="dueDateEnd" placeholder="截止日期范围结束" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">创建时间：</label>
            <div class="layui-input-inline" >
                <input type="text" name="createTimeBegin" id="createTimeBegin" placeholder="创建时间范围开始" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid">~</div>
            <div class="layui-input-inline">
                <input type="text" name="createTimeEnd" id="createTimeEnd" placeholder="创建时间范围结束" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
</@override>
<@override name="fullHeight">365</@override>
<@override name="th">
    <th lay-data="{type:'checkbox'}"></th>
    <th lay-data="{field: 'taskNum', width:140}">编号</th>
    <th lay-data="{field:'title', width: 200}">标题</th>
    <th lay-data="{field:'difficult', width: 80, align:'center'}">难度</th>
    <th lay-data="{field:'type', width: 80, align:'center'}">类型</th>
    <th lay-data="{field:'state', width: 80, templet: '#stateTpl', align:'center'}">状态</th>
    <th lay-data="{field:'dueDate', width:180, align:'center'}">截止日期</th>
    <th lay-data="{field:'createTime', width:180, align:'center'}">创建日期</th>
    <th lay-data="{minWidth:180,align:'left', toolbar: '#bar'}">操作</th>
</@override>
<@override name="tableBar">
    <script type="text/html" id="bar">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
        {{d.state == '发布' || d.state == '认领' ? '<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>' : ''}}
        {{d.state == '发布' || d.state == '认领' ? '<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>' : ''}}
    </script>
    <script type="text/html" id="stateTpl">
        {{d.state == '发布' || d.state == '认领' ? '<span class="layui-badge layui-bg-blue">'+d.state+'</span>' : '<span class="layui-badge layui-bg-green">'+d.state+'</span>'}}
    </script>
</@override>
<#if false>
    <script>
</#if>
<@override name="otherScript">
    //常规用法
    laydate.render({
    elem: '#dueDateBegin'
    });

    laydate.render({
    elem: '#dueDateEnd'
    });

    //监听锁定操作
    form.on('checkbox(accountLocked)', function(obj){
        $.ajax({
            async: true,
            type: 'POST',
            url: 'userInfo/changeLockState',
            data: {userInfoId:this.value, accountLocked:obj.elem.checked==true?"1":"0"},
            dataType: 'json',
            success: function (data) {
                if (data.code == 'success') {
                    layer.msg(obj.elem.checked ? "账号已锁定!" : "账号已取消锁定!");
                } else {
                    layer.msg("锁定失败!");
                }
            }
        });
    });
</@override>
<#if false>
    </script>
</#if>
<@extends name="../templete/layTable.ftl"/>