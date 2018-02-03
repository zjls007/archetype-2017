<#if (entity.taskNoteDTOList)??>
<blockquote class="layui-elem-quote">每日笔记</blockquote>
<blockquote class="layui-elem-quote layui-quote-nm">
    <ul class="layui-timeline">
        <#list entity.taskNoteDTOList as item>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis"></i>
                <div class="layui-timeline-content layui-text">
                    <h3 class="layui-timeline-title">${(item.date)?string('MM月dd日')}</h3>
                    <#if (item.type)! == 'edit'>
                        <#if item.lessEqualNow>
                        <textarea class="layui-textarea layui-hide" name="task.content" lay-verify="content" id="${(item.md5)!}">${(item.remark)!}</textarea>
                        </#if>
                    <#else>
                        <#if item.lessEqualNow>
                        <#if (item.remark)! == ''>
                        暂无笔记
                        <#else>
                        ${(item.remark)!}
                        </#if>
                        </#if>
                    </#if>
                </div>
            </li>
        </#list>
        <li class="layui-timeline-item">
            <i class="layui-icon layui-timeline-axis"></i>
            <div class="layui-timeline-content layui-text">
                <div class="layui-timeline-title">完成</div>
            </div>
        </li>
    </ul>
</blockquote>
</#if>
<#if false>
<script>
</#if>
<#macro initEdit>
    <#if (entity.taskNoteDTOList)??>
        <#list entity.taskNoteDTOList as item>
            <#if (item.type)! == 'edit' && (item.lessEqualNow)>
            var edit_${(item.md5)!} = layedit.build('${(item.md5)!}',  {
            tool: ['strong','italic','underline','del','|','left', 'center', 'right', '|','link', 'unlink']
            });
            </#if>
        </#list>
    </#if>
</#macro>
<#if false>
</script>
</#if>