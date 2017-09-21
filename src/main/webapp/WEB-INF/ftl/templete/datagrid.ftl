<@override name="body">
<table class="easyui-datagrid" id="dg"
       data-options="singleSelect:false,
                    collapsible:false,
                    rownumbers:true,
                    noheader:true,
                    border:false,
                    fit:true,
                    onLoadSuccess:onLoadSuccess,
                    onDblClickRow:onDblClickRow,
                    toolbar:'#tb',
                    pagination:true,
                    url:'/${dataUrl}',
                    queryParams:queryParams(),
                    method:'post'">
    <thead>
    <tr>
        <@block name="th">
        </@block>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    <div>
        <div style="padding: 5px 8px 10px 8px">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addData()">添加</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editData()">修改</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delData('/${delUrl}')">删除</a>
        </div>
        <div style="padding: 0px 8px 10px 8px">
            <form id="f-query">
                <@block name="query">
                </@block>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" style="width:80px" onclick="dgQuery()">搜索</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="$('#f-query').form('clear');" style="width:80px">重置</a>
            </form>
        </div>
    </div>
</div>
<div id="w" class="easyui-window" title="弹窗" data-options="iconCls:'icon-save',closed:true,collapsible:false,onClose:onWClose,onOpen:onWOpen" style="width:450px;height:400px;padding:10px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',noheader:true,border:false">
            <div id="d-edit" style="text-align:center;padding:5px 0">
                <form id="f-edit" method="post" action="/${actionUrl}">
                    <input type="hidden" name="id" value=""/>
                    <@block name="form">
                    </@block>
                </form>
            </div>
        </div>
        <div data-options="region:'south',noheader:true,border:false">
            <div style="text-align:center;padding:5px 0">
                <a href="javascript:void(0);" class="easyui-linkbutton" id="submitForm" onclick="submitForm()" style="width:80px">提交</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" id="resetForm" onclick="resetForm()" style="width:80px">重置</a>
            </div>
        </div>
    </div>
</div>
</@override>
<@override name="script">
</@override>
<@extends name="../base.ftl"/>
<!-- 宏 -->
<#macro formInput type='textbox' name='' label='' required='false' value=''>
    <div style="margin-bottom:20px">
    <#if type == 'textbox'>
        <input class="easyui-${type}" id="f-textbox-${name}" name="${name}" value="${value}" data-options="label:'${label}:',required:${required},width:240"/>
    <#elseif type == 'combobox'>
        <!-- 这里不通过class: easyui-combobox初始化，因为combobox在windows的内容中初始化会有不能选中默认值的问题，通过window的onOpen在打开时初始化 -->
        <input class="combobox" id="f-combobox-${name}" name="${name}" style="z-index: 99999999" data-options="label:'${label}:',required:${required},width:240,valueField:'id',textField:'text',data:${value}">
    </#if>
    </div>
</#macro>