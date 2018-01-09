<@override name="body">
<div class="layui-tab layui-tab-brief" lay-filter="reFulsh">
    <ul class="layui-tab-title">
        <li class="layui-this">${modelNameCN!}信息</li>
        <li class=""><i class="layui-icon">&#x1002;</i>刷新</li>
    </ul>
    <div class="layui-tab-content">
    </div>
</div>

<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;height: 190px">
    <div class="layui-carousel" id="imgCarousel">
        <div carousel-item="">
            <#list (entity.imgList)! as item>
                <div><img src="img/${item.id!}/0"></div>
            </#list>
        </div>
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
                ,carousel = layui.carousel
                ,$ = layui.$
                ,element = layui.element; //元素操作

        <#include "../templete/jqueryError.ftl"/>

        //监听Tab切换
        element.on('tab(reFulsh)', function(data){
            location.reload();
        });

        //图片轮播
        carousel.render({
            elem: '#imgCarousel'
            ,width: '778px'
            ,height: '440px'
            ,interval: 5000
        });
    });
</script>
</@override>
<@extends name="/base.ftl"/>
