<table class="layui-table" lay-filter="takeTask" lay-data="{height: '240', page: true, limit:10, url:'task/take'}">
    <thead>
    <tr>
        <th lay-data="{field: 'taskNum',event: 'taskView', style:'cursor: pointer;'}">待认领任务</th>
        <th lay-data="{maxWidth:120,align:'left', toolbar: '#takeTaskBar'}">操作</th>
    </tr>
    </thead>
</table>
<script type="text/html" id="takeTaskBar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">认领</a>
</script>