<div id="d-edit" style="text-align:center;padding:5px 0;">
    <form id="f-edit" method="post" action="/userInfo/saveOrUpdate">
        <input type="hidden" name="id" value=""/>
    <@block name="form">
    </@block>
    </form>
</div>
<#macro formInput type='textbox' name='' label='' required='false' value=''>
<div style="margin-bottom:20px">
    <#if type == 'textbox'>
        <input class="textbox" id="f-textbox-${name}" name="${name}" value="${value}" data-options="label:'${label}:',required:${required},width:240"/>
    <#elseif type == 'passwordbox'>
        <input class="passwordbox" name="${name}" data-options="label:'${label}:',required:${required},width:240"/>
    <#elseif type == 'combobox'>
        <input class="combobox" id="f-combobox-${name}" name="${name}" data-options="panelHeight:'auto',label:'${label}:',required:${required},width:240,valueField:'id',textField:'text',data:${value}">
    </#if>
</div>
</#macro>
