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
function submitForm(formId, modelName) {
    var form = $('#' + formId);
    if (form.form('validate')) {
        form.ajaxSubmit({
            success: function (data) {
                if (data.code == 0) {
                    if (modelName) {
                        $('#w-' + modelName).window('close')
                        $('#dg-' + modelName).datagrid('reload');
                    } else {
                        location.href = "index";
                    }
                } else {
                    alert(data.message);
                }
            }
        });
    }
}
function clearForm(formId) {
    $('#' + formId).form('clear');
}
