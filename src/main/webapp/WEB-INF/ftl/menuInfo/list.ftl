<html>
<head>
    <title>列表</title>
    <link rel="stylesheet" type="text/css" href="/statics/js/easyui/easyui.css">
    <link rel="stylesheet" type="text/css" href="/statics/js/easyui/icon.css">
</head>
<body>
<table id="tg" class="easyui-treegrid" style="width:600px;height:400px"
       data-options="url:'/menuInfo/data',
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
        <th data-options="field:'name',width:180">菜单名称</th>
        <th data-options="field:'url',width:180">URL</th>
        <th data-options="field:'btn',align:'center',width:140,formatter:formatter">操作</th>
    </tr>
    </thead>
</table>
<div style="display: none">
    <a href="javascript:void(0)" class="mb">操作</a>
</div>
<div id="tb-menu" style="padding:2px 5px;">

</div>
<div id="mm" class="easyui-menu" style="width:200px;">
    <div data-options="iconCls:'icon-edit'" onclick="appendMenu(this)">编辑</div>
    <div data-options="iconCls:'icon-delete'" onclick="appendMenu(1)">删除</div>
    <div data-options="iconCls:'icon-undo'" onclick="appendMenu(1)">插入同级菜单</div>
    <div data-options="iconCls:'icon-redo'" onclick="appendMenu(2)">插入子级菜单</div>
    <div class="menu-sep"></div>
    <div>Exit</div>
</div>
<script type="text/javascript" src="/statics/js/jquery.min.js"></script>
<script type="text/javascript" src="/statics/js/ajaxFileUpload.js"></script>
<script type="text/javascript" src="/statics/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/statics/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/statics/js/jquery.form.js"></script>
<script type="text/javascript" src="/statics/js/app-custom.js"></script>
<script type="text/javascript">
    function formatter(value,row,index){
        return $('.mb').parent().html();
    }

    function onLoadSuccess(data) {
        $('.mb').each(function () {
           $(this).menubutton({
               iconCls: 'icon-edit',
               menu: '#mm'
           });
        });
    }
    function appendMenu(type) {
        var parentId;
        if (type == 1) {
            parentId = selectNode._parentId;
        } else {
            parentId = selectNode.id;
        }
        $('#tg').treegrid('append',{
            parent: parentId,
            data: [{
                id: '',
                name: 'name73',
            }]
        });
    }
</script>
</body>
</html>
