<script>
<#macro setVal list id>
    <#if (!id??) || (!list??) || list?size == 0>
        <#return>
    </#if>
    var select = $('#${id}');

    <#list list as item>
    var option = new Option("${item.text}", "${item.id}", true, true);
    select.append(option).trigger('change');

    select.trigger({
        type: 'select2:select',
        params: {
            data: {id:"${item.id}", text:"${item.text}"}
        }
    });
    </#list>
</#macro>

<!-- 给后台list赋下标 -->
<#macro setIndex id='' name=''>
    var val = $("#${id!}").select2("val");
    if (val != null) {
        if (typeof val == 'string') {
            data.field["${name!}[0]"]=val;
        } else {
            for (var i = 0; i < val.length; i++) {
                data.field["${name!}["+i+"]"]=val[i];
            }
        }
    }
</#macro>

<!-- 给后台list赋下标 -->
<#macro setIndex1 id='' name=''>
var val = $("#${id!}").select2("val");
if (val != null) {
    if (typeof val == 'string') {
        data.push({"name": "${name!}[0]", "value": val});
    } else {
        for (var i = 0; i < val.length; i++) {
            data.push({"name": "${name!}["+i+"]", "value": val[i]});
        }
    }
}
</#macro>

<!-- 初始化控件 -->
<#macro init class='select2' id='' width='400' placeholder='请选择用户' url='userInfo/getUserList' multi="false">
    <!-- $('.select2').select2('destroy')-->
    <#assign classOrId = '.'+class>
    <#if id?? && id !=''>
        <#local classOrId = '#'+id>
    </#if>
    <!-- 解决多选切换不成单选 -->
    $('select[multiple=""]').removeAttr('multiple');
    $('select[multiple="multiple"]').removeAttr('multiple');
    $('${classOrId!}').select2({
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
    $('${classOrId!}').val(null).trigger('change');
    <!-- 解决占位符宽度不够 -->
    $('input.select2-search__field').width('120px');
</#macro>
</script>