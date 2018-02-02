<blockquote class="layui-elem-quote layui-quote-nm">
    <input type="hidden" name="task.id" value="${(entity.id)!}">
    <div class="layui-form-item">
        <label class="layui-form-label">任务标题：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.title)!}</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">任务类型：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.type)!}</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">人员</label>
        <div class="layui-form-mid layui-word-aux"><@select2.showUserName list=(entity.userList)! /></div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">难度：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.difficult)!}</div>
        <div class="layui-form-mid layui-word-aux"></div>
        <label class="layui-form-label">截止日期：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.dueDate?string("yyyy-MM-dd"))!}</div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">任务描述：</label>
        <div class="layui-form-mid layui-word-aux">${(entity.content)!}</div>
    </div>
</blockquote>
<div class="layui-tab layui-tab-brief">
    <ul class="layui-tab-title">
        <li class="layui-this">附件<#if util.listIsEmpty((entity.attachmentList)!)==0><span class="layui-badge-dot"></span></#if></li>
        <li>图片<#if util.listIsEmpty((entity.imgList)!)==0><span class="layui-badge-dot"></span></#if></li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
        <@webuploader.htmlFileView attachmentList=(entity.attachmentList)!/>
        </div>
        <div class="layui-tab-item ">
        <@webuploader.htmlView imgList=(entity.imgList)! size=6 name="imgList" marginLeft="120"/>
        </div>
    </div>
</div>