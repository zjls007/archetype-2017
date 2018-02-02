<@override name="link">
<link href="${basePath}/statics/ui/ystep/css/ystep.css" rel="stylesheet" />
</@override>
<@override name="cite">查看</@override>
<@override name="baseEditBody">
    <#if (entity.createUserId)! == userId>
    <div class="ystep" style="height: 100px;margin-top: 40px;margin-left: 40px;"></div>
    </#if>
    <#include "base/baseTaskView.ftl">
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