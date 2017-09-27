// 打开页签: url 页签的打开的页面地址 title 页签标题
function openTab(url, title){
    var content = '<iframe src="' + url + '" frameborder="0" border="0" marginwidth="0" marginheight="100" scrolling="auto" width="100%" height="100%"/>';
    var option = {
        title: title,
        tabWidth: 180,
        content : content,
        closable: true
    }

    var tab = $('#tt').tabs('getTab', title);
    if (tab) {
        $('#tt').tabs('select', title);
        $('#tt').tabs('update', {
            tab: tab,
            options: option
        });
        tab.panel('refresh');
    } else {
        $('#tt').tabs('add', option);
    }
}