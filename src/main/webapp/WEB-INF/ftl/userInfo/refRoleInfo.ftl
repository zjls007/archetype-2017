<input class="easyui-tagbox" id="w-role" label="用户[${userName!}]：" style="width:100%" data-options="
        url:'roleInfo/userRefRoleInfoData?userInfoId=${userInfoId!}',
        value:'${value!}',
        valueField: 'id',
        textField: 'text',
        limitToList: true,
        prompt: '请选择角色',
        validType: ['length[0,1000]','uniquetag']">
<script type="text/javascript">
    $('#w-center .easyui-tagbox').tagbox();
    $('.submit').unbind();
    $('.submit').on({click:function () {
        $.ajax({
            url: 'userInfo/saveRefRoleInfo',
            method: 'post',
            data: {userInfoId:${userInfoId!},values:$('#w-role').tagbox('getValues').toString()},
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