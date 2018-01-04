<ul id="tree" class="easyui-tree">
    <li>
        <span>菜单</span>
        <ul>
            <!-- 动态获取 -->
            <#if (menuInfoList?size > 1)>
                <@showNode/>
            </#if>
        </ul>
    </li>
</ul>
<#macro showNode pId=0>
    <#list menuInfoList as item>
        <#if item.parentId?? && item.parentId == pId && hasMenuIdList?seq_contains(item.id) && item.nativeState == 1>
            <#if hasChildren(item.id) == "1">
                <li>
                    <span>${item.name}</span>
                    <ul>
                        <@showNode pId=item.id/>
                    </ul>
                </li>
            <#else>
                <li><span><a href="javascript:void(0)" onclick="openTab('${item.url}', '${item.name}')">${item.name}</a></span></li>
            </#if>
        </#if>
    </#list>
</#macro>
<#function hasChildren id>
    <#list menuInfoList as item>
        <#if item.parentId?? && item.parentId == id>
            <!-- 存在叶子节点 -->
            <#return "1">
        </#if>
    </#list>
    <#return "0">
</#function>
