<table id="tg" class="easyui-treegrid"
       data-options="url:'menuInfo/data',
           fit:true,
           noheader:true,
           singleSelect:false,
           border:false,
           rownumbers: true,
           onLoadSuccess:onLoadSuccess,
           animate: true,
           collapsible: true,
           fitColumns: false,
           method: 'post',
           idField:'id',
           treeField:'name'">
    <thead>
    <tr>
        <th data-options="field:'id',checkbox:true"></th>
        <th data-options="field:'name',width:180">菜单名称</th>
    </tr>
    </thead>
</table>
<script type="text/javascript">
    function onLoadSuccess() {
        var values = "${values!}".split(",");
        for (var i = 0; i < values.length; i++) {
            if (values[i]) {
                $('#tg').treegrid('checkRow', values[i]);
            }
        }
    }

    $('#tg').treegrid();
    $('.submit').unbind();
    $('.submit').on({click:function () {
        var rows = $('#tg').treegrid('getChecked');
        var values = '';
        for (row in rows) {
            values += rows[row].id + ',';
        }
        $.ajax({
            url: 'roleInfo/saveRefPermissionMenu',
            method: 'post',
            data: {roleInfoId:${roleInfoId!},values:values},
            success: function (data) {
                if (data.code == 0) {
                    $('#w').window('close')
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
    }});
</script>