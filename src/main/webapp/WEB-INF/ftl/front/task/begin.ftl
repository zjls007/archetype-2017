<@override name="link">
<link href="${basePath}/statics/ui/ystep/css/ystep.css" rel="stylesheet" />
</@override>
<@override name="cite">查看</@override>
<@override name="baseEditBody">
    <#if (entity.createUserId)! == userId>
    <div class="ystep" style="height: 100px;margin-top: 40px;margin-left: 40px;"></div>
    </#if>
<blockquote class="layui-elem-quote layui-quote-nm">
    <input type="hidden" name="task.id" value="${(entity.id)!}">
    <div class="layui-form-item">
        <label class="layui-form-label">任务标题：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.title)!}</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">任务类型：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.type)!}</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">人员</label>
        <div class="layui-form-mid layui-word-aux"><@select2.showUserName list=(entity.userList)! /></div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">难度：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.difficult)!}</div>
        <div class="layui-form-mid layui-word-aux"></div>
        <label class="layui-form-label">截止日期：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.dueDate?string("yyyy-MM-dd"))!}</div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">任务描述：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.content)!}</div>
    </div>
</blockquote>
<div class="layui-tab layui-tab-brief">
    <ul class="layui-tab-title">
        <li class="layui-this">附件<#if util.listIsEmpty((entity.attachmentList)!)==0><span class="layui-badge-dot"></span></#if></li>
        <li>图片<#if util.listIsEmpty((entity.imgList)!)==0><span class="layui-badge-dot"></span></#if></li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <@webuploader.htmlFileView attachmentList=(entity.attachmentList)!/>
        </div>
        <div class="layui-tab-item ">
            <@webuploader.htmlView imgList=(entity.imgList)! size=6 name="imgList" marginLeft="120"/>
        </div>
    </div>
</div>
<blockquote class="layui-elem-quote">每日笔记</blockquote>
<blockquote class="layui-elem-quote layui-quote-nm">
    <ul class="layui-timeline">
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">8月18日</h3>
                <textarea class="layui-textarea layui-hide" name="task.content" lay-verify="content" id="editor"></textarea>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">8月16日</h3>
                <textarea class="layui-textarea layui-hide" name="task.content" lay-verify="content" id="editor1"></textarea>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <h3 class="layui-timeline-title">8月15日</h3>
                <textarea class="layui-textarea layui-hide" name="task.content" lay-verify="content" id="editor2"></textarea>
            </div>
        </li>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title">完成</div>
            </div>
        </li>
    </ul>
</blockquote>
</@override>
<@override name="bottomBtn">
    <#if (entity.showBeginBtn)!>
    <button class="layui-btn layui-btn-primary layui-btn-sm add" id="submit"><i class="layui-icon">&#xe623;</i>开始</button>
    </#if>
</@override>
<@override name="script">
<script src="${basePath}/statics/ui/ystep/js/ystep.js"></script>
<script>
    function downloadFile(e) {
        var fileMd5 = $(e).attr('fileMD5');
        window.open("file/"+fileMd5);
    }

    function photos(index) {
        $.ajax({
            async: true,
            type: 'POST',
            url: 'task/photos',
            data: {taskId: "${(entity.id)!}", index: index},
            dataType: 'json',
            success: function (data) {
                if (data.code == 'success') {
                    layer.photos(data.data);
                } else {
                    layer.msg(data.message, {time:3000});
                }
            }
        });
    }

    $(document).ready(function() {
        $(".ystep").loadStep({
            size: "large",
            color: "green",
            steps: [{
                title: "发布",
                content: ""
            },{
                title: "认领",
                content: ""
            },{
                title: "开始",
                content: ""
            },{
                title: "挂起",
                content: ""
            },{
                title: "完成",
                content: ""
            }]
        });
        $(".ystep").setStep(3);
    });

    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
        var laydate = layui.laydate //日期
                , table = layui.table //  表单
                ,layer = layui.layer //弹层
                ,laydate = layui.laydate
                ,layedit = layui.layedit
                ,form = layui.form
            <#if (entity.imgList)?? && entity.imgList?size gt 0>
                    ,carousel = layui.carousel
            </#if>
                ,$ = layui.$
                ,element = layui.element; //元素操作

        <#include "../templete/jqueryError.ftl"/>

        //监听Tab切换
        element.on('tab(reFulsh)', function(data){
            location.reload();
        });

        //创建一个编辑器
        var editIndex = layedit.build('editor',  {
            tool: ['strong','italic','underline','del','|','left', 'center', 'right', '|','link', 'unlink']
        });

        //创建一个编辑器
        var editIndex1 = layedit.build('editor1',  {
            tool: ['strong','italic','underline','del','|','left', 'center', 'right', '|','link', 'unlink']
        });

        //创建一个编辑器
        var editIndex2 = layedit.build('editor2',  {
            tool: ['strong','italic','underline','del','|','left', 'center', 'right', '|','link', 'unlink']
        });

    });
</script>
</@override>
<@extends name="../templete/baseEdit.ftl"/>
