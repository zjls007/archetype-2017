<ul id="tree" class="easyui-tree">
    <li>
        <span>菜单</span>
        <ul>
            <!-- 动态获取 -->
            <#if (menuInfoList?size > 1)>
                <@showTree/>
            <!-- 手动书写 -->
            <#else>
                <li>
                    <span>系统管理</span>
                    <ul>
                        <li><span><a href="javascript:void(0)" onclick="openTab('menuInfo/list', '菜单管理')">菜单管理</a></span></li>
                        <li><span><a href="javascript:void(0)" onclick="openTab('userInfo/list', '用户管理')">用户管理</a></span></li>
                        <li><span><a href="javascript:void(0)" onclick="openTab('')">用户组管理</a></span></li>
                        <li><span><a href="javascript:void(0)" onclick="openTab()">角色管理</a></span></li>
                        <li><span><a href="javascript:void(0)" onclick="openTab()">权限管理</a></span></li>
                    </ul>
                </li>
            </#if>
        </ul>
    </li>
</ul>
<#macro showTree pId=0>
    <#list menuInfoList as item>
        <#if item.parentId?? && item.parentId == pId>
            <li>
                <span>${item.name}</span>
                <ul>
                    <@showNode pId=item.id/>
                </ul>
            </li>
        </#if>
    </#list>
</#macro>
<#macro showNode pId>
    <#list menuInfoList as item>
        <#if item.parentId?? && item.parentId == pId>
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
