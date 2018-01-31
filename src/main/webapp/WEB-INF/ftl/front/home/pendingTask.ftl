<table class="layui-table" id="dg" lay-filter="pendingTask" lay-data="{height: '240', page: true, limit:10, url:'task/pending'}">
    <thead>
    <tr>
        <th lay-data="{field: 'taskNum',event: 'taskView', style:'cursor: pointer;'}">待处理任务</th>
        <th lay-data="{maxWidth:120,align:'left', toolbar: '#pendingTaskBar'}">操作</th>
    </tr>
    </thead>
</table>
<script type="text/html" id="pendingTaskBar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">开始</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">挂起</a>
</script>