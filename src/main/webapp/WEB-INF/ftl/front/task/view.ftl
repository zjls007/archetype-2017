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
            $.ajax({
                async: true,
                type: 'POST',
                url: 'task/saveOrUpdateNotes',
                data: data.field,
                dataType: 'json',
                success: function (data) {
                    if (data.code == 'success') {
                        layer.msg("保存成功!");
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
                            layer.msg(data.message, {icon: 5, shift: 6});
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
