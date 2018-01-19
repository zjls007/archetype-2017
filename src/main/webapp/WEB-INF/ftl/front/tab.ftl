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
                <a href="javascript:void(0);" onclick="goIndex(this)" data-url="home" data-value="首页" class="active index" style="width: 40px">首页</a>
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
        <iframe id="index" class="iframe-content active" data-url="" data-value="首页" src="home"></iframe>
    </div>
    <!--iframe End-->
</div>
<script type="text/javascript">
    function newTab(title, url) {
        $('#tabTemp').attr('href', url).html(title).click();
    }
    function goIndex(athis) {
        $('div#menu-list a.active').removeClass('active');
        $(athis).addClass('active');
        $('div#page-content iframe.active').removeClass('active');
        $('div#page-content iframe#index').addClass('active');
    }
    function closeOther() {
        $('#menu-list a:not(.index, .active)').remove();
        $('div#page-content iframe:not(#index, .active)').remove();
    }
    function closeActive(parentTab, reloadParentTab) {
        $('#menu-list a.active').remove();
        $('div#page-content iframe.active').remove();
        if (parentTab) {
            $('#menu-list a[data-value="'+parentTab+'"]').addClass('active');
            $('div#page-content iframe[data-value="'+parentTab+'"]').addClass('active');
            if (reloadParentTab) {
                $(window.parent.document).contents().find('div#page-content iframe[data-value="'+parentTab+'"]')[0].contentWindow.reloadDG();
            }
        }
    }
    <!-- 跳转到指定窗口 -->
    function activateTab(tabName) {
        $('div#menu-list a.active').removeClass('active');
        $('div#menu-list a[data-value="'+tabName+'"]').addClass('active');
        $('div#page-content iframe.active').removeClass('active');
        $('div#page-content iframe[data-value="'+tabName+'"]').addClass('active');
    }
    function closeAll() {
        $('#menu-list a:not(.index)').remove();
        $('div#page-content iframe:not(#index)').remove();
        $('div#page-content iframe#index').addClass('active');
    }
</script>