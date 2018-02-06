<#if false>
    <script>
</#if>

<!-- 下拉框option  optionList为List<SelectOptionVO> value 为下拉框值-->
<#macro select optionList value=''>
    <#if optionList??>
        <#list optionList as item>
        <option value="${item.value}" <#if value?? && (value==item.value)>selected</#if>>${item.name}</option>
        </#list>
    </#if>
</#macro>

<#function listIsEmpty list>
    <#if list??>
        <#if (list?size > 0)>
            <#return 0>
        </#if>
    </#if>
    <#return 1>
</#function>

<#macro ajaxSubmit url='' data=''>
    $.ajax({
        async: true,
        type: 'POST',
        url: '${url!}',
        data: ${data!},
        dataType: 'json',
        success: function (data) {
            if (data.code == 'success') {
                layer.msg('保存成功!', {icon: 1, shift: 6});
                if (refreshParentTab) {
                    setTimeout(refreshParentTab, 1000);
                }
            } else {
                if (data.code == 'param_error') {
                    var d = JSON.parse(data.message);
                    var input = $('input[name="' + d.field + '"]');
                    if (input) {
                        input.addClass("layui-form-danger");
                        input.focus();
                    }
                    layer.msg(d.msg, {icon: 5, shift: 6});
                } else {
                    layer.msg(data.message, {icon: 2, shift: 6});
                }
            }
        }
    });
</#macro>

<#macro ajax url='' data=''>
    $.ajax({
        async: true,
        type: 'POST',
        url: '${url!}',
        <#if data?? && data != ''>
            data: ${data!},
        </#if>
        dataType: 'json',
        success: function (data) {
            if (data.code == 'success') {
                layer.msg('成功!', {icon: 1, shift: 6});
            } else {
                layer.msg(data.message, {icon: 2, shift: 6});
            }
        }
    });
</#macro>

<#if false>
    </script>
</#if>