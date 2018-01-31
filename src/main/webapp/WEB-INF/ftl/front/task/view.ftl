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

        <#if (entity.imgList)?? && entity.imgList?size gt 0>
        //图片轮播
        carousel.render({
            elem: '#imgCarousel'
            ,width: 'auto'
            ,height: '440px'
            ,interval: 5000
        });
        </#if>
    });
</script>
</@override>
<@extends name="../templete/baseEdit.ftl"/>
