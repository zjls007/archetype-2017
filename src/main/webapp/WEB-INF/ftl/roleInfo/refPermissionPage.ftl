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
    ${roleInfoId!}
    <input id="permissionId" name="permissionId" data-options="panelHeight:'auto',label:'权限:',required:false,width:240,valueField:'id',textField:'text',url:'roleInfo/getPermissionData'"/>
    <div style="margin-top: 5px"></div>
    <input id="w-operation" style="width: 250px" data-options="
        url:'operationInfo/operationInfoData',
        value:'${value!}',
        label:'权限操作:',
        valueField: 'id',
        textField: 'text',
        limitToList: true,
        prompt: '请选择权限操作',
        validType: ['length[0,1000]','uniquetag']">
    <div style="margin-top: 5px"></div>
</div>
<script type="text/javascript">
    $('#dg-ref').datagrid();
    $('#permissionId').combobox();
    $('#w-operation').tagbox();
</script>