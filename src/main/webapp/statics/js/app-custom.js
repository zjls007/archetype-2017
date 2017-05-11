function queryParams() {
    var param = {
        ajaxOrigin:'datagrid'

    };
    return param;
}
function onLoadSuccess(data) {
    if (data.error) {
        alert(data.error);
        location.href="index";
    }
}
function submitForm(formId){
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
}
function clearForm(formId){
    $('#' + formId).form('clear');
}
