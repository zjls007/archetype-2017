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

function queryParams() {
    var param = {
        ajaxOrigin:'datagrid'
    };
    onQueryParam(param);
    return param;
}
function onQueryParam(param) {

}
function onLoadSuccess(data) {
    if (data.error) {
        alert(data.error);
        location.href="index";
    }
}
function dgQuery() {
    $('#dg').datagrid('load');
}
function submitForm() {
    var form = $('#f-edit');
    if (form.form('validate')) {
        form.ajaxSubmit({
            success: function (data) {
                if (data.code == 0) {
                    $('#w').window('close')
                    $('#dg').datagrid('reload');
                } else {
                    alert(data.message);
                }
            }
        });
    }
}

function delData(url) {
    $('#w').window('close');
    var checked = $('#dg').datagrid('getChecked');
    if (checked.length == 0) {
        alert("请选择数据!");
    }
    var idList=[];
    for (var i = 0; i < checked.length; i++) {
        idList.push(checked[i].id);
    }
    $.ajax({
        async: true,
        type: 'POST',
        url: url,
        data: JSON.stringify(idList),
        dataType: 'json',
        contentType:"application/json",
        success: function (data) {
            if (data.code == 0) {
                $('#dg').datagrid('reload');
            } else {
                alert(data.message);
            }
        },
        error: function () {
            alert("出错了!");
        }
    });
}

function onDblClickRow(index,row) {
    doEdit(row);
}

function editData() {
    var checked = $('#dg').datagrid('getChecked');
    if (checked.length == 0) {
        alert("请选择数据!");
    } else if (checked.length > 1) {
        alert("只能编辑一天数据!");
    } else {
        doEdit(checked[0]);
    }
}

function doEdit(row) {
    $('#f-edit').form('clear');
    $('#w').window('open');
    for (var key in row) {
        var f = $('#d-edit');
        var textbox = $('#f-textbox-' + key);
        if (textbox.length == 1) {
            textbox.textbox('setValue', row[key]);
        } else {
            var input = f.find('input[name=' + key + ']');
            if (input.length == 1) {
                input.val(row[key]);
            }
        }
    }
}

function addData() {
    $('#f-edit').form('clear');
    $('#w').window('open');
}