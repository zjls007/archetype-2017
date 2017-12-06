<div class="layui-side-scroll">
    <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
    <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <a class="tab" id="tabTemp" href="front/userInfo/list" style="display: none;">用户管理</a>
        <!-- 动态获取 -->
        <#if (menuInfoList?size > 1)>
            <@showNode/>
        </#if>
    </ul>
</div>
<#macro showNode pId=0>
    <#local isFirst="1">
    <#list menuInfoList as item>
        <#if item.parentId?? && item.parentId == pId && hasMenuIdList?seq_contains(item.id) && item.nativeState == 0>
            <#if hasChildren(item.id) == "1">
            <li class="layui-nav-item <#if isFirst=='1'>layui-nav-itemed</#if>">
                <#local isFirst="0">
                <a class="" href="javascript:void(0);">${item.name}</a>
                <dl class="layui-nav-child">
                    <@showNode pId=item.id/>
                </dl>
            </li>
            <#else>
            <#if pId==0>
            <li class="layui-nav-item"><a href="${item.url}">${item.name}</a></li>
            <#else>
            <dd><a class="tab" href="${item.url}">${item.name}</a></dd>
            </#if>
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