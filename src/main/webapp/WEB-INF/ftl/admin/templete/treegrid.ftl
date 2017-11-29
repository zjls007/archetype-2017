<@override name="body">
    <table id="tg" class="easyui-treegrid"
           data-options="url:'${dataUrl!}',
           fit:true,
           noheader:true,
           border:false,
           rownumbers: true,
           animate: true,
           collapsible: true,
           fitColumns: false,
           toolbar:'#tb-menu',
           method: 'post',
           onLoadSuccess:onLoadSuccess,
           onLoadError:onLoadError,
           idField:'id',
           treeField:'name'">
        <thead>
        <tr>
            <@block name="th">
            </@block>
        </tr>
        </thead>
    </table>
    <div style="display: none" id="hideDiv">
        <a href="javascript:void(0)" id="mb">操作</a>
    </div>
    <div id="tb-menu" style="padding:12px 8px;">
        <a id="btn" href="javascript:void(0)" class="easyui-linkbutton save" data-options="iconCls:'icon-save'">保存</a>
        <a id="btn" href="javascript:void(0)" class="easyui-linkbutton undo" data-options="iconCls:'icon-undo'">全部撤销</a>
    </div>
    <div id="mm" style="width:200px;">
        <div data-options="iconCls:''" id="mm-edit">编辑</div>
        <div data-options="iconCls:'icon-delete'" id="mm-del">删除</div>
        <div data-options="iconCls:'icon-undo'" id="mm-brother">插入同级菜单</div>
        <div data-options="iconCls:'icon-redo'" id="mm-children">插入子级菜单</div>
        <div data-options="iconCls:'icon-tip'" id="mm-up">上移</div>
        <div data-options="iconCls:'icon-tip'" id="mm-down">下移</div>
        <div class="menu-sep"></div>
        <div>Exit</div>
    </div>
</@override>
<@override name="script">
<script type="text/javascript">
    $(function () {
        $('.save').on({click: save});
        $('.undo').on({click: undo});
    })
    globalId = 0;
    function styler(value,row,index) {
        var operateId = $('#tg').data('colorId');
        if (operateId == row.id) {
            return {class:'c1',style:'color:red'};
        }
        return '';
    }
    function formatter(value,row,index){
        return $('#mb').clone().attr("id", 'mb' + row.id).attr("dataId", row.id).attr("native", row.native).attr("_parentId", row._parentId).addClass("mb").prop("outerHTML");
    }
    function onLoadSuccess(data) {
        updateParentId();
        doMenu();
    }
    function updateParentId() {
        var treeData = $('#tg').treegrid('getChildren', 0);
        for (var i = 1; i < treeData.length; i++) {
            // 更新parentId为字符串类型
            $('#tg').treegrid('update',{
                id: treeData[i].id,
                row: {
                    _parentId: treeData[i]._parentId.toString()
                }
            });
        }
    }
    function undo() {
        $('#tg').treegrid('reload');
    }
    function save() {
        var editId = $('#tg').data('editId');
        if (editId) {
            $.messager.alert('警告','请先结束编辑!','warning');
            return;
        }
        var jsonStr = JSON.stringify($('#tg').treegrid('getChildren', 0));
        $.ajax({
            async: true,
            data: {jsonStr: jsonStr},
            type: 'POST',
            dataType: 'json',
            url: '${actionUrl!}',
            success: function (data) {
                $('#tg').data('colorId', 'none');
                $('#tg').treegrid('reload');
            },
            error: function () {
                $.messager.alert('错误','出错了!','error');
            }
        });
    }
    function doMenu() {
        $('.mb').each(function () {
            // 渲染过的不用再次渲染
            if ($(this).data('drawing') != 'true') {
                var id = $(this).attr("dataId");
                var native = $(this).attr("native");
                var _parentId = $(this).attr("_parentId");
                var newId = 'mm' + id;
                var div = $('#mm').clone().attr("id", newId);
                // 顶级菜单不能修改和删除
                if (id == 0) {
                    div = div.children('div#mm-edit').attr('data-options','disabled:true').html('编辑').parent();
                    div = div.children('div#mm-del').attr('data-options','disabled:true').parent();
                    div = div.children('div#mm-brother').attr('data-options','disabled:true').parent();
                    div = div.children('div#mm-children').attr('onclick',"addChildrenNode('0')").parent();
                    div = div.children('div#mm-up').attr('data-options','disabled:true').parent();
                    div = div.children('div#mm-down').attr('data-options','disabled:true').parent();
                } else {
                    var editId = $('#tg').data('editId');
                    var editStr = "编辑";
                    if (editId && editId == id) {
                        editStr = "结束编辑";
                    }
                    div = div.children('div#mm-edit').attr('onclick',"edit('" + id + "')").html(editStr).parent();
                    if (native && native == 1) {
                        div = div.children('div#mm-del').attr('data-options','disabled:true').parent();
                    } else {
                        div = div.children('div#mm-del').attr('onclick',"removeNode('" + id + "')").parent();
                    }
                    div = div.children('div#mm-brother').attr('onclick',"addBrotherNode(" + id + "," + _parentId + ")").parent();
                    div = div.children('div#mm-children').attr('onclick',"addChildrenNode(" + id + ")").parent();
                    div = div.children('div#mm-up').attr('onclick',"upNode('" + id + "')").parent();
                    div = div.children('div#mm-down').attr('onclick',"downNode('" + id + "')").parent();
                }
                div.appendTo('#hideDiv');
                $(this).menubutton({
                    iconCls: 'icon-edit',
                    menu: '#'+newId
                });
                $(this).data('drawing', 'true');
            }
        });
    }
    function edit(operateId) {
        var editId = $('#tg').data('editId');
        if (editId) {
            if (editId != operateId) {
                $.messager.alert('警告','不能同时编辑多行!','warning');
                return;
            }
            $('#tg').data('colorId', operateId);
            $('#tg').data('editId', '');
            $('#mb' + operateId).data('drawing', 'false');
            $('#mm' + operateId).menu('destroy');
            $('#mb' + operateId).menubutton('destroy');
            $('#tg').treegrid('endEdit', operateId);
            doMenu();
        } else {
            $('#tg').data('editId', operateId);
            $('#tg').treegrid('beginEdit', operateId);
            $('#mm'+operateId).children('div#mm-edit').children('div').text("结束编辑");
        }
    }
    function addBrotherNode(beforeId, parentId) {
        var editId = $('#tg').data('editId');
        if (editId) {
            $.messager.alert('警告','不能同时编辑多行!','warning');
            return;
        }
        var id = --globalId;
        $('#tg').treegrid('insert',{
            after: beforeId,
            data: {
                id: id,
                name: id,
                _parentId: parentId
            }
        });
        // 更新parentId为字符串类型
        $('#tg').treegrid('update',{
            id: id,
            row: {
                _parentId: parentId.toString()
            }
        });
        $('#tg').treegrid('beginEdit', id);
        $('#tg').data('editId', id);
        doMenu();
    }
    function addChildrenNode(parentId) {
        var editId = $('#tg').data('editId');
        if (editId) {
            $.messager.alert('警告','不能同时编辑多行!','warning');
            return;
        }
        var id = --globalId;
        $('#tg').treegrid('append',{
            parent: parentId,
            data: [{
                id: id,
                name: id,
            }]
        });
        $('#tg').treegrid('beginEdit', id);
        $('#tg').data('editId', id);
        doMenu();
    }
    function upNode(operateId) {
        var node = $('#tg').treegrid('find', operateId);
        var parentId = node._parentId;
        var brotherNodes = $('#tg').treegrid('getParent', node.id).children;
        var beforeId;
        for (var i = 0; i < brotherNodes.length; i++) {
            if (brotherNodes[i].id == operateId) {
                break;
            }
            beforeId = brotherNodes[i].id;
        }
        if (!beforeId) {
            $.messager.alert('警告','已经在最顶部了!','warning');
            return;
        }
        $('#mm' + operateId).menu('destroy');
        $('#mb' + operateId).menubutton('destroy');
        $('#mb' + operateId).data('drawing', 'false');
        $('#tg').treegrid('remove', operateId);
        $('#tg').treegrid('insert',{
            before: beforeId,
            data: node
        });
        // 更新parentId为字符串类型
        $('#tg').treegrid('update',{
            id: node.id,
            row: {
                _parentId: parentId.toString()
            }
        });
        doMenu();
    }
    function downNode(operateId) {
        var node = $('#tg').treegrid('find', operateId);
        var parentId = node._parentId;
        var brotherNodes = $('#tg').treegrid('getParent', node.id).children;
        var beforeId;
        for (var i = brotherNodes.length - 1; i >= 0; i--) {
            if (brotherNodes[i].id == operateId) {
                break;
            }
            beforeId = brotherNodes[i].id;
        }
        if (!beforeId) {
            $.messager.alert('警告','已经在最底部了!','warning');
            return;
        }
        $('#mm' + operateId).menu('destroy');
        $('#mb' + operateId).menubutton('destroy');
        $('#mb' + operateId).data('drawing', 'false');
        $('#tg').treegrid('remove', operateId);
        $('#tg').treegrid('insert',{
            after: beforeId,
            data: node
        });
        // 更新parentId为字符串类型
        $('#tg').treegrid('update',{
            id: node.id,
            row: {
                _parentId: parentId.toString()
            }
        });
        doMenu();
    }
    function removeNode(operateId) {
        $('#tg').data('editId', '');
        $('#mm' + operateId).menu('destroy');
        $('#mb' + operateId).menubutton('destroy');
        $('#tg').treegrid('remove', operateId);
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
                        window.parent.hrefIndex();
                    }
                });
            } else {
                window.parent.doLogin();
            }
        } else {
            $.messager.alert('错误','出错啦!','error');
        }
    }
</script>
</@override>
<@extends name="/base.ftl"/>
<#macro formatterNative>
    formatter:function(value,row,index) {
        if (row.native == 1) {
            return '<font color=\'green\'>是</font>';
        }
        return '否';
    }
</#macro>