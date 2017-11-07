<table id="dg-ref"
       data-options="singleSelect:false,
                    collapsible:false,
                    rownumbers:true,
                    noheader:true,
                    border:false,
                    fit:true,
                    onLoadSuccess:onLoadSuccess,
                    onLoadError:onLoadError,
                    onDblClickRow:onDblClickRow,
                    toolbar:'#tb-ref',
                    pagination:true,
                    url:'',
                    queryParams:queryParams(),
                    method:'post'">
    <thead>
    <tr>
        <th data-options="field:'id',checkbox:true"></th>
        <th data-options="field:'name',width:200">权限</th>
        <th data-options="field:'code',width:200">权限操作</th>
    </tr>
    </thead>
</table>
<div id="tb-ref" style="padding:2px 5px;">
    <input id="permissionId" name="permissionId" data-options="panelHeight:'auto',label:'权限:',required:false,width:240,valueField:'id',textField:'text',url:'roleInfo/getPermissionData'">
</div>
<script type="text/javascript">
    $('#dg-ref').datagrid();
    $('#permissionId').combobox();
</script>