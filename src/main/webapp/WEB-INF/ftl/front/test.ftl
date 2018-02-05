<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="${basePath}/front/">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>xxxx</title>
    <link href="${basePath}/statics/ui/select2/css/select2.min.css" rel="stylesheet" />
    <link href="${basePath}/statics/ui/webuploader-0.1.5/webuploader.css" rel="stylesheet" />
    <link href="${basePath}/statics/css/style.css" rel="stylesheet" />

    <link href="${basePath}/statics/ui/tab/css/tabstyle-min.css" rel="stylesheet" />
</head>
<body>
<a href="http://www.baidu.com" class="easy-tab">百度</a>
<div id="page-tab">
    <button class="tab-btn" id="page-prev"></button>
    <nav id="page-tab-content">
        <div id="menu-list">
            <a href="javascript:void(0);" data-url="" data-value="首页" class="" style="width: 40px">首页</a>
            <a href="javascript:void(0);" data-url="" data-value="任务列表" class="active">任务列表<i class="menu-close"></i></a>
            <a href="javascript:void(0);" data-url="" data-value="百度" class="">百度<i class="menu-close"></i></a>
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
    <iframe class="iframe-content" data-url="" data-value="首页" src="home"></iframe>
    <iframe class="iframe-content active" data-url="" data-value="任务列表" src="http://localhost/front/task/list"></iframe>
    <iframe class="iframe-content" data-url="" data-value="百度" src="http://www.baidu.com"></iframe>
</div>

<script src="${basePath}/statics/js/jquery.min.js"></script>
<script src="${basePath}/statics/ui/select2/js/select2.full.min.js"></script>
<script src="${basePath}/statics/ui/select2/js/i18n/zh-CN.js"></script>
<script src="${basePath}/statics/ui/webuploader-0.1.5/webuploader.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('div#page-tab div#menu-list i.menu-close').on({click: tabClose});
        $('div#page-tab div#menu-list a').on({click: tabActive});
        $('a.easy-tab').on({click: function () {
            openTab($(this).attr('href'), $(this).text());
            return false;
        }});
    })

    function tabActive() {
        var a = $(this);
        if (!a.hasClass('active')) {
            $('div#page-tab div#menu-list a.active').removeClass('active');
            $('div#page-content iframe.active').removeClass('active');
            a.addClass('active');
            $('div#page-content iframe[data-value='+a.attr('data-value')+']').addClass('active');
        }
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

    function openTab(url, title) {
        $('div#page-tab div#menu-list a.active').removeClass('active');
        var a = $('<a href="javascript:void(0);" data-url=""></a>').attr('data-value', title).addClass('active').text(title);
        $('<i class="menu-close"></i>').appendTo(a);
        $('div#page-tab div#menu-list').append(a);

        $('div#page-tab div#menu-list i.menu-close').unbind('click');
        $('div#page-tab div#menu-list i.menu-close').on({click: tabClose});

        $('div#page-content iframe.active').removeClass('active');
        var iframe = $('<iframe class="iframe-content active" data-url=""></iframe>').attr('data-value', title).attr('src', url);
        $('div#page-content').append(iframe);

        $('div#page-tab div#menu-list a').unbind('click');
        $('div#page-tab div#menu-list a').on({click: tabActive});
    }
</script>
</body>
</html>
