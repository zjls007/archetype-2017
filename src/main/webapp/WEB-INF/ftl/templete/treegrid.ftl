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
           idField:'id',
           treeField:'name'">
        <thead>
        <tr>
            <th data-options="field:'name',width:180,editor:'textbox',styler:styler">菜单名称</th>
            <th data-options="field:'url',width:180,editor:'textbox',styler:styler">URL</th>
            <th data-options="field:'btn',align:'center',width:140,formatter:formatter">操作</th>
        </tr>
        </thead>
    </table>
    <div style="display: none" id="hideDiv">
        <a href="javascript:void(0)" id="mb">操作</a>
    </div>
    <div id="tb-menu" style="padding:12px 8px;">
        <a id="btn" href="javascript:void(0)" onclick="save()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
        <a id="btn" href="javascript:void(0)" onclick="undo()" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">全部撤销</a>
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
    globalId = 0;
    function styler(value,row,index) {
        var operateId = $('#tg').data('colorId');
        if (operateId == row.id) {
            return {class:'c1',style:'color:red'};
        }
        return '';
    }
    function formatter(value,row,index){
        return $('#mb').clone().attr("id", 'mb' + row.id).attr("dataId", row.id).attr("_parentId", row._parentId).addClass("mb").prop("outerHTML");
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
            alert('请先结束编辑!');
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
                alert("出错了!");
            }
        });
    }
    function doMenu() {
        $('.mb').each(function () {
            // 渲染过的不用再次渲染
            if ($(this).data('drawing') != 'true') {
                var id = $(this).attr("dataId");
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
                    div = div.children('div#mm-del').attr('onclick',"removeNode('" + id + "')").parent();
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
                alert('不能同时编辑多行!');
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
            alert('不能同时编辑多行!');
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
            alert('不能同时编辑多行!');
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
            alert("已经在最顶部了!");
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
            alert("已经在最底部了!");
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
</script>
</@override>
<@extends name="/base.ftl"/>