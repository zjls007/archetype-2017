<@override name="body">
<div class="layui-tab layui-tab-brief" lay-filter="reFulsh">
    <ul class="layui-tab-title">
        <li class="layui-this">${modelNameCN!}信息</li>
        <li class=""><i class="layui-icon">&#x1002;</i>刷新</li>
    </ul>
    <div class="layui-tab-content">
    </div>
</div>
<form class="layui-form" method="post" action="task/saveOrUpdate">
    <blockquote class="layui-elem-quote layui-quote-nm">
        <input type="hidden" name="id" value="${(entity.task.id)!}">
        <div class="layui-form-item">
            <label class="layui-form-label">任务标题</label>
            <div class="layui-input-inline">
                <input type="text" name="task.title" value="${(entity.task.title)!}" lay-verify="required" autocomplete="off" placeholder="请输入任务标题" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"><font color="red">*</font>必填</div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">任务类型</label>
            <div class="layui-input-block">
                <input lay-filter="taskType" type="radio" name="task.type" value="assign" title="指派" <#if !entity??>checked</#if> ${((entity.task.type=='assign')?string('checked', ''))!}>
                <input lay-filter="taskType" type="radio" name="task.type" value="take" title="认领" ${((entity.task.type=='take')?string('checked', ''))!}>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">人员</label>
            <div class="layui-input-block">
                <select type="hidden" class="select2" id="userIdList" lay-ignore></select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">难度：</label>
            <div class="layui-input-inline">
                <select name="task.difficult">
                    <option value="easy">简单</option>
                    <option value="normal">一般</option>
                    <option value="hard">困难</option>
                </select>
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
            <label class="layui-form-label">截止日期：</label>
            <div class="layui-input-inline">
                <input type="text" name="task.dueDate" value="${(entity.task.dueDate?string("yyyy-MM-dd"))!}" id="dueDate" placeholder="截止日期" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>

        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">任务描述</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea layui-hide" name="task.content" lay-verify="content" id="editor">${(entity.task.content)!}</textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-primary layui-btn-sm add" lay-submit="" lay-filter="submit"><i class="layui-icon">&#xe610;</i>提交</button>
                <button class="layui-btn layui-btn-primary layui-btn-sm reset" type="reset"><i class="layui-icon">&#xe633;</i>重置</button>
            </div>
        </div>
    </blockquote>

    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;height: 190px">
        <div style="margin-bottom: 8px">上传图片:</div>
        <@webuploader.html size=5 name="imgList" marginLeft="110"/>
    </blockquote>
</form>
</@override>
<@override name="script">
<script>
    $(document).ready(function() {
        <@select2.init id='userIdList' placeholder='请选择用户' url='userInfo/getUserList' multi='false'/>
        <@webuploader.init/>
    });

    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
        var laydate = layui.laydate //日期
                , table = layui.table //  表单
                ,layer = layui.layer //弹层
                ,laydate = layui.laydate
                ,layedit = layui.layedit
                ,form = layui.form
                ,$ = layui.$
                ,element = layui.element; //元素操作

        <#include "../templete/jqueryError.ftl"/>

        //监听Tab切换
        element.on('tab(reFulsh)', function(data){
            location.reload();
        });

        //常规用法
        laydate.render({
            elem: '#dueDate'
        });

        form.on('radio(taskType)', function(data){
            if (data.value == 'assign') {
                <@select2.init id='userIdList' placeholder='请选择用户' url='userInfo/getUserList' multi='false'/>
            } else if (data.value == 'take') {
                <@select2.init id='userIdList' placeholder='请选择用户' url='userInfo/getUserList' multi='true'/>
            }
        });

        //创建一个编辑器
        var editIndex = layedit.build('editor');

        //自定义验证规则
        form.verify({
            content: function(value){
                layedit.sync(editIndex);
                var text = layedit.getText(editIndex);
                if (text == null || text == '') {
                    return '任务内容不能为空!';
                }
            }
        });

        //监听提交
        form.on('submit(submit)', function(data){
            <@select2.setIndex id='userIdList' name='userIdList'/>
            $.ajax({
                async: true,
                type: 'POST',
                url: 'task/saveOrUpdateDTO',
                data: data.field,
                dataType: 'json',
                success: function (data) {
                    if (data.code == 'success') {
                        layer.msg("保存成功!");
                        parent.closeActive('用户管理', true);
                    } else {
                        layer.msg(data.message, {time:3000});
                    }
                }
            });
            return false;
        });
    });
</script>
</@override>
<@extends name="/base.ftl"/>
