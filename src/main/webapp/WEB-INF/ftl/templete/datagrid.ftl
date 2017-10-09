<@override name="body">
<table class="easyui-datagrid" id="dg"
       data-options="singleSelect:false,
                    collapsible:false,
                    rownumbers:true,
                    noheader:true,
                    border:false,
                    fit:true,
                    onLoadSuccess:onLoadSuccess,
                    onLoadError:onLoadError,
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
        <div style="display: none">
            <!-- class="easyui-menu" -->
            <div id="mm" style="width:200px;" data-options="onClick:onClickMenu">
                <@block name="menu">
                </@block>
                <#--划痕 <div class="menu-sep"></div>-->
            </div>
            <div id="mmDiv"></div>
        </div>
        <div style="padding: 5px 8px 10px 8px">
            <a href="javascript:void(0)" class="easyui-linkbutton add" id="add" actionUrl="/${editUrl}" iconCls="icon-add" plain="true">添加</a>
            <a href="javascript:void(0)" class="easyui-linkbutton edit" iconCls="icon-edit" plain="true">修改</a>
            <a href="javascript:void(0)" class="easyui-linkbutton delete" delUrl="/${delUrl}" iconCls="icon-remove" plain="true">删除</a>
        </div>
        <div style="padding: 0px 8px 10px 8px">
            <form id="f-query">
                <@block name="query">
                </@block>
                <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" style="margin-left:20px;width:80px" onclick="dgQuery()">搜索</a>
                <a href="javascript:void(0);" class="easyui-linkbutton" onclick="$('#f-query').form('clear');" style="margin-left:10px;width:80px">重置</a>
            </form>
        </div>
    </div>
</div>
<div id="w" class="easyui-window" title="弹窗" data-options="iconCls:'icon-save',closed:true,collapsible:false,onClose:onWClose,onOpen:onWOpen" style="width:450px;height:400px;padding:10px;">
    <div class="easyui-layout" data-options="fit:true">
        <div id="w-center" data-options="region:'center',noheader:true,border:false">
        </div>
        <div data-options="region:'south',noheader:true,border:false">
            <div style="text-align:center;padding:5px 0">
                <a href="javascript:void(0);" class="easyui-linkbutton submit" id="submitForm" style="width:80px">提交</a>
                <a href="javascript:void(0);" class="easyui-linkbutton reset" id="resetForm" style="width:80px">重置</a>
            </div>
        </div>
    </div>
</div>
</@override>
<@override name="script">
</@override>
<@extends name="/base.ftl"/>
<!-- 宏 -->
<#macro queryInput type='textbox' name='' label='' required='false' value=''>
    <div style="float: left;margin: 0px 20px 20px 0px">
        <#if type == 'textbox'>
            <input class="easyui-textbox" id="q-textbox-${name}" name="${name}" data-options="label:'${label}:',required:${required},width:240"/>
        <#elseif type == 'combobox'>
            <input class="easyui-combobox" id="q-combobox-${name}" name="${name}" data-options="panelHeight:'auto',label:'${label}:',required:${required},width:240,valueField:'id',textField:'text',data:${value}">
        <#elseif type == 'datebox'>
            <input class="easyui-datebox" name="${name?split(",")[0]}" data-options="label:'${label}:',required:false,width:200" >~
            <input class="easyui-datebox" name="${name?split(",")[1]}" data-options="label:'',required:false,width:110" >
        </#if>
    </div>
</#macro>

<#macro br>
    <div style="clear: both;"></div>
</#macro>

<#macro formatterMenuButton>
    formatter:function(value,row,index) {
        return $('<a>操作</a>').addClass('easyui-menubutton').attr('href', 'javascript:void(0)').attr('dataId', row.id).prop('outerHTML');
    }
</#macro>