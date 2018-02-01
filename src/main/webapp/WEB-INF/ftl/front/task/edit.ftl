<@override name="cite">编辑</@override>
<@override name="baseEditBody">
<blockquote class="layui-elem-quote layui-quote-nm">
    <input type="hidden" name="task.id" value="${(entity.id)!}">
    <div class="layui-form-item">
        <label class="layui-form-label">任务标题</label>
        <div class="layui-input-inline">
            <input type="text" name="task.title" value="${(entity.title)!}" lay-verify="required" autocomplete="off" placeholder="请输入任务标题" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"><font color="red">*</font>必填</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">任务类型</label>
        <div class="layui-input-block">
            <input lay-filter="taskType" type="radio" name="task.type" value="assign" title="指派" <#if !entity??>checked</#if> ${((entity.type=='assign')?string('checked', ''))!}>
            <input lay-filter="taskType" type="radio" name="task.type" value="take" title="认领" ${((entity.type=='take')?string('checked', ''))!}>
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
                <@util.select optionList=selectMap['task.difficult'] value=(entity.difficult)!/>
            </select>
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
        <label class="layui-form-label">截止日期：</label>
        <div class="layui-input-inline">
            <input type="text" name="task.dueDate" value="${(entity.dueDate?string("yyyy-MM-dd"))!}" id="dueDate" placeholder="截止日期" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">任务描述</label>
        <div class="layui-input-block">
            <textarea class="layui-textarea layui-hide" name="task.content" lay-verify="content" id="editor">${(entity.content)!}</textarea>
        </div>
    </div>
</blockquote>

<div class="layui-tab layui-tab-brief">
    <ul class="layui-tab-title">
        <li class="layui-this">附件<#if util.listIsEmpty((entity.attachmentList)!)==0><span class="layui-badge-dot"></span></#if></li>
        <li>图片<#if util.listIsEmpty((entity.imgList)!)==0><span class="layui-badge-dot"></span></#if></li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <@webuploader.htmlFile attachmentList=(entity.attachmentList)!/>
        </div>
        <div class="layui-tab-item ">
            <@webuploader.html imgList=(entity.imgList)! size=6 name="imgList" marginLeft="120"/>
        </div>
    </div>
</div>
</@override>
<@override name="script">
<script>
    $(document).ready(function() {
        <#assign multi='false'/>
        <#if ((entity.type)!)=='take'>
            <#assign multi='true'/>
        </#if>
        <@select2.init id='userIdList' placeholder='请选择用户' url='userInfo/getUserList' multi=multi/>
        <@select2.setVal id='userIdList' list=(entity.userList)! />
        <@webuploader.init/>
        <@webuploader.initUploadFile/>
        $('#submit').on({click: function () {
            $('#submit1').click();
        }});
        $('body').on({click: function () {
            $('.layui-layedit-focus').each(function () {
                $(this).removeClass('layui-layedit-focus');
            });
        }});
    });

    function downloadFile(e) {
        var fileMd5 = $(e).attr('fileMD5');
        window.open("file/"+fileMd5);
    }

    function clearFile(e) {
        $(e).parent('div').remove();
    }

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
        var editIndex = layedit.build('editor',  {
            tool: ['strong','italic','underline','del','|','left', 'center', 'right', '|','link', 'unlink']
        });

        //自定义验证规则
        form.verify({
            content: function(value){
                layedit.sync(editIndex);
                var text = layedit.getText(editIndex);
                if (text == null || text == '') {
                    $('.layui-layedit').addClass('layui-layedit-focus');
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
                        parent.closeActive('任务列表', true);
                    } else {
                        if (data.code == 'param_error') {
                            var d = JSON.parse(data.message);
                            var input = $('input[name="' + d.field + '"]');
                            if (input) {
                                input.addClass("layui-form-danger");
                                input.focus();
                            }
                            layer.msg(d.msg, {icon: 5, shift: 6});
                        } else {
                            layer.msg(data.message, {time:3000});
                        }
                    }
                }
            });
            return false;
        });
    });
</script>
</@override>
<@extends name="../templete/baseEdit.ftl"/>
