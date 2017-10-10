<input class="easyui-tagbox" id="w-role" label="用户[${userName!}]：" style="width:100%" data-options="
        url:'/roleInfo/userRefRoleInfoData?userInfoId=${userInfoId!}',
        value:'${value!}',
        valueField: 'id',
        textField: 'text',
        limitToList: true,
        prompt: '请选择角色',
        validType: ['length[0,1000]','uniquetag']">
<script type="text/javascript">
    $('#w-center .easyui-tagbox').tagbox();
//    $('#w-role').tagbox('getValues');
    function submitForm() {
        alert(1);
    }
</script>