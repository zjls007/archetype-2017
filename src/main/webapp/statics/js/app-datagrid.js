$(function () {
    // 打开弹窗
    $('.add').on({
        // 方法中可以直接中 $(this)获取对象本身
        click: openWindow,
        mouseover:function(){}
    });
    // 编辑
    $('.edit').on({click:editData});
    // 删除
    $('.delete').on({click:delData});
    // 提交
    $('.submit').on({click:submitForm});
    // 重置
    $('.reset').on({click:resetForm});
});

<!-- 打开弹窗 -->
function openWindow(row) {
    $('#w-center').empty();
    $.ajax({
        method: 'post',
        url: $('#add').attr('actionUrl'),
        success: function (data) {
            var getHtml = $(data);
            var temp = $('<code></code>').append(getHtml);
            $('#w-center').append(temp.html());
            initFormBox();
            if (row) {
                setValue(row);
            }
        },
        error: function () {
            $.messager.alert('警告','请求异常!','warning');
        }
    });
    $('#w').window('open');
}

function initFormBox() {
    $('.textbox').textbox();
    $('.passwordbox').passwordbox();
    $('.combobox').combobox();
}

function onDblClickRow(index,row) {
    doEdit(row);
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

<!-- 编辑最终掉的方法 -->
function doEdit(row) {
    openWindow(row);
    // 存放编辑重置的数据
    $('#resetForm').data('row', row);
}

function setValue(row) {
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

function resetForm() {
    $('#f-edit').form('clear');
    var row = $('#resetForm').data('row');
    if (row) {
        setValue(row);
    }
}

<!-- 删除数据 -->
function delData() {
    var url = $(this).attr('delUrl');
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
    initMenuButton();
    if (data.error) {
        alert(data.error);
        location.href="index";
    }
}

function initMenuButton() {
    // 初始化datagrid-menuButton
    $('.easyui-menubutton').each(function () {
        var id = $(this).attr('dataId');
        var html = $('#mm').clone().attr('id', 'mm' + id).attr('dataId', id).prop('outerHTML');
        $('#mmDiv').append(html);
        $(this).menubutton({
            iconCls: 'icon-edit',
            menu: '#mm'+ id
        });
    });
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
        if (window.parent.length == 0) {
            $.messager.alert({
                title: '提示',
                msg: '<div style="text-align:center;margin-top:18px">请重新登录!</div>',
                icon:'info',
                fn: function(){
                    window.parent.location.href='/login';
                }
            });
        } else {
            window.parent.doLogin();
        }
    } else {
        $.messager.alert('错误','出错啦!','error');
    }
}

function onWClose() {
    $('#resetForm').data('row', '');
}

function onWOpen() {
}

function onClickMenu(menu) {
    var type = menu.businessType;
    var id = $(menu.target).parent().attr('dataId');
    alert(id + '-' + type);
}