<style>
    #menu-all #menu-btn li {
        list-style: none;
        padding: 5px 19px 5px 7px;
        cursor: pointer;
        overflow: hidden;
        font-size: 12px;
    }
    #menu-all #menu-btn li:hover {
        color: #4d4d4d;
        background-color: #9F9F9F;
    }
</style>
<div style="width: 100%; height: 100%;">
    <!--菜单HTML Start-->
    <div id="page-tab">
        <button class="tab-btn" id="page-prev"></button>
        <nav id="page-tab-content">
            <div id="menu-list">
                <a href="javascript:void(0);" onclick="goIndex(this)" data-url="" data-value="" class="active index" style="width: 40px">首页</a>
            </div>
        </nav>
        <button class="tab-btn" id="page-next"></button>
        <div id="page-operation">
            <div id="menu-all">
                <ul id="menu-btn">
                    <li onmousedown="closeOther()">关闭其他</li>
                    <li onmousedown="closeAll()">关闭全部</li>
                </ul>
                <!-- 放开会有纵向菜单导航（有bug） -->
                <#--<ul id="menu-all-ul">-->
                <#--</ul>-->
            </div>
        </div>
    </div>
    <!--菜单HTML End-->
    <!--iframe Start (根据页面顶部占用高度，自行调整高度数值)-->
    <div id="page-content" style="height: calc(100% - 40px);">
        <iframe id="index" class="iframe-content active" data-url="http://www.baidu.com/" data-value="首页" src="http://www.baidu.com/"></iframe>
    </div>
    <!--iframe End-->
</div>
<script type="text/javascript">
    function goIndex(athis) {
        $('div#menu-list a.active').removeClass('active');
        $(athis).addClass('active');
        $('div#page-content iframe#index').addClass('active');
    }
    function closeOther() {
        $('#menu-list a:not(.index, .active)').remove();
        $('div#page-content iframe:not(#index, .active)').remove();
    }
    function closeAll() {
        $('#menu-list a:not(.index)').remove();
        $('div#page-content iframe:not(#index)').remove();
        $('div#page-content iframe#index').addClass('active');
    }
</script>