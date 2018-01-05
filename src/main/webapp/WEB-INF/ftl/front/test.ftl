<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="${basePath}/front/">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>xxxx</title>
    <link href="${basePath}/statics/ui/select2/css/select2.min.css" rel="stylesheet" />
    <style>
        body{margin: 10px;}
    </style>
</head>
<body>
<select class="select2" name="userList"></select>

<script src="${basePath}/statics/js/jquery.min.js"></script>
<script src="${basePath}/statics/ui/select2/js/select2.full.min.js"></script>
<script src="${basePath}/statics/ui/select2/js/i18n/zh-CN.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('.select2').select2({
            language : "zh-CN",
            theme: "classic",
            width: '400',
            minimumInputLength : 1,
            placeholder: {
                id: '',
                text: '请选择用户'
            },
            allowClear: true,
            ajax: {
                delay: 500,
                url: 'userInfo/getUserList',
                dataType: 'json',
                data: function (params) {
                    var query = {
                        q: params.term,
                        pageSize: 10,
                        curPage: params.page || 1
                    }
                    return query;
                },
                processResults: function (data) {
                    return {
                        results: data.data.data,
                        pagination: {
                            more: data.data.hasNextPage
                        }
                    };
                },
                cache : false
            }
        });
    });
</script>
</body>
</html>
