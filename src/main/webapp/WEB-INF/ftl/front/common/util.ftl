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