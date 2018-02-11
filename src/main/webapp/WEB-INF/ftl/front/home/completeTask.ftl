<table class="layui-table" lay-filter="completeTask" lay-data="{height: '240', page: true, limit:10, url:'task/complete'}">
    <thead>
    <tr>
        <th lay-data="{field: 'taskNum',event: 'taskView', style:'cursor: pointer;'}">已完成任务</th>
        <th lay-data="{maxWidth:120,align:'left', toolbar: '#completeTaskBar'}">操作</th>
    </tr>
    </thead>
</table>
<script type="text/html" id="completeTaskBar">
</script>