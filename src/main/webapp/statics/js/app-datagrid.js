$(function () {
    // 打开弹窗
    $('.add').on('click', function () {
        addData();
    });
    // 编辑
    $('.edit').on('click', function () {
        editData();
    });
    // 删除
    $('.delete').on('click', function () {
        delData($(this).attr('delUrl'));
    });
    // 提交
    $('.submit').on('click', function () {
        submitForm();
    });
    // 重置
    $('.reset').on('click', function () {
        resetForm();
    });
});

<!-- 打开弹窗 -->
function addData() {
    $('#f-edit').form('clear');
    $('#w').window('open');
}
<!-- 编辑数据 -->
function editData() {
    var checked = $('#dg').datagrid('getChecked');
    if (checked.length == 0) {
        $.messager.alert('警告','请选择数据!','warning');
    } else if (checked.length > 1) {
        $.messager.alert('警告','只能编辑一天数据!','warning');
    } else {
        doEdit(checked[0]);
    }
}
<!-- 删除数据 -->
function delData(url) {
    $('#w').window('close');
    var checked = $('#dg').datagrid('getChecked');
    if (checked.length == 0) {
        $.messager.alert('警告','请选择数据!','warning');
        return;
    }
    $.messager.confirm('警告','确定要删除吗?',function(r){
        if (r){
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
                        $.messager.alert('错误',data.message,'error');
                    }
                },
                error: function () {
                    $.messager.alert('错误','请选择数据!','error');
                }
            });
        }
    });
}


function queryParams() {
    var param = {
        ajaxOrigin:'datagrid'
    };
    var jsonArray = $('#f-query').serializeArray();
    for (var i = 0; i < jsonArray.length; i++) {
        var name = jsonArray[i].name;
        var value = jsonArray[i].value;
        param[name] = value;
    }
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
    $('#dg').datagrid('options').queryParams = queryParams();
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
                    $.messager.alert('错误',data.message,'error');
                }
            },
            error: function(xhr, status, error) {
                // 未登录
                if (xhr.status == 403) {
                    window.parent.doLogin();
                } else {
                    $.messager.alert('错误','保存出错!','error');
                }
            }
        });
    }
}

function onLoadError(xhr, status, error) {
    // 未登录
    if (xhr.status == 403) {
        window.parent.doLogin();
    } else {
        $.messager.alert('错误','保存出错!','error');
    }
}

function resetForm() {
    $('#f-edit').form('clear');
    var row = $('#resetForm').data('row');
    if (row) {
        doEdit(row);
    }
}

function onWClose() {
    $('#resetForm').data('row', '');
}

function onWOpen() {
    $('.combobox').combobox();
}

function onDblClickRow(index,row) {
    doEdit(row);
}

<!-- 编辑最终掉的方法 -->
function doEdit(row) {
    // 存放编辑重置的数据
    $('#resetForm').data('row', row);
    $('#f-edit').form('clear');
    $('#w').window('open');
    for (var key in row) {
        var f = $('#d-edit');
        var textbox = $('#f-textbox-' + key);
        var combobox = $('#f-combobox-' + key);
        if (textbox.length == 1) {
            textbox.textbox('setValue', row[key]);
        } else if (combobox.length == 1) {
            combobox.combobox('setValue', row[key]);
        } else {
            var input = f.find('input[name=' + key + ']');
            if (input.length == 1) {
                input.val(row[key]);
            }
        }
    }
}