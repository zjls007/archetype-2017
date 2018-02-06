<@override name="link">
<link href="${basePath}/statics/ui/ystep/css/ystep.css" rel="stylesheet" />
</@override>
<@override name="cite">查看</@override>
<@override name="baseEditBody">
<#if (entity.createUserId)! == userId>
<div class="ystep" style="height: 100px;margin-top: 40px;margin-left: 40px;"></div>
</#if>
<#include "base/baseTaskView.ftl">
<#include "base/note.ftl">
</@override>
<@override name="bottomBtn">
    <#include "base/viewButton.ftl">
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
        <@btn/>

        $.ajax({
            async: true,
            type: 'POST',
            url: 'task/step/${(entity.id)!}',
            dataType: 'json',
            success: function (data) {
                if (data.code == 'success') {
                    $(".ystep").loadStep({
                        size: "large",
                        color: "green",
                        steps: data.data.steps
                    });
                    $(".ystep").setStep(data.data.step);
                } else {
                    layer.msg(data.message, {icon: 2, shift: 6});
                }
            }
        });
    });

    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
        var laydate = layui.laydate
                , table = layui.table
                ,layer = layui.layer
                ,layedit = layui.layedit
                ,form = layui.form
                ,$ = layui.$
                ,element = layui.element;

        <#include "../templete/jqueryError.ftl"/>

        <@initEdit/>

        <@syncEdit/>

        //监听提交
        form.on('submit(submit)', function(data){
            <@util.ajaxSubmit url='task/saveOrUpdateNotes' data='data.field'/>
            return false;
        });
    });
</script>
</@override>
<@extends name="../templete/baseEdit.ftl"/>
