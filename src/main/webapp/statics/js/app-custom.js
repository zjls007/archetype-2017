function queryParams() {
    var param = {
        ajaxOrigin:'datagrid'
    };
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
function dgQuery(modelName) {
    $('#dg-' + modelName).datagrid('load');
}
function submitForm(formId) {
    var form = $('#' + formId);
    if (form.form('validate')) {
        form.ajaxSubmit({
            success: function (data) {
                if (data.code == 0) {
                    location.href = "index";
                    return;
                }
                alert(data.message);
            }
        });
    }
    // $('#dg-userInfo').datagrid('reload');
}
function clearForm(formId) {
    $('#' + formId).form('clear');
}
