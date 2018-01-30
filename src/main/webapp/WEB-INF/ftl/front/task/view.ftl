<@override name="baseEditBody">
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
        <div class="layui-form-mid layui-word-aux">${(entity.type)!}</div>
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
</@override>
<@override name="script">
<script>
    $(document).ready(function() {
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
