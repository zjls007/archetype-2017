<#macro init class='select2' width='400' placeholder='请选择用户' url='userInfo/getUserList' multi="false">
    $('.${class!}').select2({
        <#if multi=='true'>
            multiple : "multiple",
        </#if>
        language : "zh-CN",
        theme: "classic",
        width: '${width!}',
        minimumInputLength : 1,
        placeholder: {
        id: '',
        text: '${placeholder!}'
        },
        allowClear: true,
        ajax: {
            delay: 250,
            url: '${url!}',
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
</#macro>