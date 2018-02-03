<#if (entity.showEditBtn)?? && entity.showEditBtn>
<button class="layui-btn layui-btn-sm view-edit-btn" type="reset"><i class="layui-icon">&#xe623;</i>编辑</button>
</#if>
<#if (entity.showBeginBtn)?? && entity.showBeginBtn>
<button class="layui-btn layui-btn-sm" type="reset" onclick="javascript:parent.newTab('${modelNameCN!}开始', '${basePath}/front/task/begin/task/${(entity.id)!}')"><i class="layui-icon">&#xe623;</i>开始</button>
</#if>
<#if (entity.showSaveNoteBtn)?? && entity.showSaveNoteBtn>
<button class="layui-btn layui-btn-sm" lay-submit lay-filter="submit"><i class="layui-icon">&#xe60a;</i>保存笔记</button>
</#if>
<#if (entity.showCompleteBtn)?? && entity.showCompleteBtn>
<button class="layui-btn layui-btn-sm view-complete-btn" type="reset"><i class="layui-icon">&#xe616;</i>完成</button>
</#if>
<#if false>
<script>
</#if>
    <#macro btn>
    $('.view-edit-btn').on({click:function () {
        parent.newTab('${modelNameCN!}编辑', '${basePath}/front/task/edit/task/${(entity.id)!}');
    }});
    $('.view-save-note-btn').on({click:function () {
        alert(1);
    }});
    $('.view-complete-btn').on({click:function () {
        alert(2);
    }});
    </#macro>
<#if false>
</script>
</#if>
