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
    <div id="page-tab">
        <button class="tab-btn" id="page-prev"></button>
        <nav id="page-tab-content">
            <div id="menu-list">
                <a href="javascript:void(0);" data-url="" data-value="首页" class="active index" style="width: 40px">首页</a>
                <#if false>
                    <a href="javascript:void(0);" data-url="" data-value="任务列表" class="">任务列表<i class="menu-close"></i></a>
                </#if>
            </div>
        </nav>
        <button class="tab-btn" id="page-next"></button>
        <div id="page-operation">
            <div id="menu-all" style="display: none;">
                <ul id="menu-btn">
                    <li onmousedown="closeOther()">关闭其他</li>
                    <li onmousedown="closeAll()">关闭全部</li>
                </ul>
            </div>
        </div>
    </div>

    <div id="page-content" style="height: calc(100% - 40px);">
        <iframe class="iframe-content active index" data-url="" data-value="首页" src="home"></iframe>
        <#if false>
            <iframe class="iframe-content" data-url="" data-value="任务列表" src="http://localhost/front/task/list"></iframe>
        </#if>
    </div>
</div>
<#if false>
<script>
</#if>
<#macro easyTab>
    $(function () {
        $('div#page-tab div#menu-list i.menu-close').on({click: tabClose});
        $('div#page-tab div#menu-list a').on({click: tabActive});
        $('a.easy-tab').on({click: initLink});
        $('div#page-operation').on({click: function () {
            $('div#menu-all').show();
        }});
    })

    function initLink() {
        var title = $(this).text();
        var url = $(this).attr('href');
        openTab(title, url);
        return false;
    }

    function addTab(title, url) {
        var a = $('<a href="javascript:void(0);" data-url=""></a>').attr('data-value', title).addClass('active').text(title);
        $('<i class="menu-close"></i>').appendTo(a);
        $('div#page-tab div#menu-list').append(a);

        $('div#page-tab div#menu-list i.menu-close').unbind('click');
        $('div#page-tab div#menu-list i.menu-close').on({click: tabClose});
        $('div#page-tab div#menu-list a').unbind('click');
        $('div#page-tab div#menu-list a').on({click: tabActive});

        var iframe = $('<iframe class="iframe-content active" data-url=""></iframe>').attr('data-value', title).attr('src', url);
        $('div#page-content').append(iframe);
    }

    function openTab(title, url, refresh) {
        var tab = findTabByTitle(title);
        if (tab.length == 0) {
            removeActiveTab();
            addTab(title, url);
        } else {
            if (!tab.hasClass('active')) {
                removeActiveTab();
                activateTab(title);
            }
            if (refresh) {
                var iframe = findIframeByTitle(title);
                iframe.attr('src', url);
            }
        }
    }

    function findTabByTitle(title) {
        return $('div#page-tab div#menu-list a[data-value="'+title+'"]');
    }

    function findIframeByTitle(title) {
        return $('div#page-content iframe[data-value="'+title+'"]');
    }

    function tabActive() {
        var a = $(this);
        if (!a.hasClass('active')) {
            removeActiveTab();
            a.addClass('active');
            $('div#page-content iframe[data-value='+a.attr('data-value')+']').addClass('active');
        }
    }

    function removeActiveTab() {
        $('div#page-tab div#menu-list a.active').removeClass('active');
        $('div#page-content iframe.active').removeClass('active');
    }

    function tabClose() {
        var a = $(this).parent('a');
        if (a.hasClass('active')) {
            var next = nextE(a);
            next.addClass('active');
            a.remove();
            var iframe = $('div#page-content iframe.active');
            next = nextE(iframe);
            next.addClass('active');
            iframe.remove();
        } else {
            a.remove();
            $('div#page-content iframe[data-value='+a.attr('data-value')+']').remove();
        }
    }

    function nextE(a) {
        var next = a.next();
        if (next.length == 0) {
            next = a.prev();
        }
        return next;
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
        removeActiveTab();
        $('div#menu-list a[data-value="'+tabName+'"]').addClass('active');
        $('div#page-content iframe[data-value="'+tabName+'"]').addClass('active');
    }
    function closeAll() {
        $('#menu-list a:not(.index)').remove();
        $('div#page-content iframe:not(.index)').remove();
        $('div#page-content iframe.index').addClass('active');
    }
</#macro>
<#if false>
</script>
</#if>
