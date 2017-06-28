<#-- 宏定义 -->
<#-- 字符串默认值,字符串不为空显示自己,为空显示默认值 -->
<#macro strDefault source default=''>
    <#if source?? && source?length gt 0>
        <#t>${source}
    <#else>
        <#t>${default}
    </#if>
</#macro>
<#-- 字符串存在显示true,否则显示false -->
<#macro strExist source trueVal='' falseVal=''>
    <#if source?? && source?length gt 0>
        <#t>${trueVal}
    <#else>
        <#t>${falseVal}
    </#if>
</#macro>