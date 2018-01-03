<@override name="body">
<div class="layui-tab layui-tab-brief" lay-filter="reFulsh">
    <ul class="layui-tab-title">
        <li class="layui-this">用户信息</li>
        <li class=""><i class="layui-icon">&#x1002;</i>刷新</li>
    </ul>
    <div class="layui-tab-content">
    </div>
</div>

<div class="layui-collapse" lay-accordion="">
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">数字签名</h2>
        <div class="layui-colla-content layui-show">
            <p>数字签名，就是只有信息的发送者才能产生的别人无法伪造的一段数字串，这段数字串同时也是对信息的发送者发送信息真实性的一个有效证明。
                <br>
                数字签名是非对称密钥加密技术与数字摘要技术的应用。</p>
            <p>
            这里使用“SHA1withRSA”可以升级为“SHA256withRSA”</p>
            客户端：客户端发送签名和参数(公共参数+业务参数+私钥 产生 签名)<br>
            服务端：使用公钥验签客户端的参数和签名
        </div>
    </div>
</div>
<div style="margin: 50px"></div>
<blockquote class="layui-elem-quote layui-quote-nm">
    <form class="layui-form" method="post" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">客户id：</label>
            <div class="layui-input-inline">
                <input type="text" name="appId" value="040ad53eb37f780fc07f2e0ca0c8524a" lay-verify="" placeholder="请输客户id" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
            <label class="layui-form-label">请求参数：</label>
            <div class="layui-input-inline">
                <input type="text" name="param" value="&age=18&sex=man&height=1.78" lay-verify="" placeholder="请输请求参数" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux"></div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-primary layui-btn-sm add" lay-submit="" lay-filter="submit"><i class="layui-icon">&#xe610;</i>提交</button>
                <button class="layui-btn layui-btn-primary layui-btn-sm reset" type="reset"><i class="layui-icon">&#xe633;</i>重置</button>
            </div>
        </div>
    </form>
</blockquote>
</@override>
<@override name="script">
<script>
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
        var laydate = layui.laydate //日期
                , table = layui.table //  表单
                ,layer = layui.layer //弹层
                ,laydate = layui.laydate
                ,form = layui.form
                ,$ = layui.$
                ,element = layui.element; //元素操作

        <#include "../templete/jqueryError.ftl"/>

        //监听Tab切换
        element.on('tab(reFulsh)', function(data){
            location.reload();
        });

        //监听提交
        form.on('submit(submit)', function(data){
            $.ajax({
                async: true,
                type: 'POST',
                url: 'signature/getSign',
                data: data.field,
                dataType: 'json',
                success: function (jsonData) {
                    for (var key in jsonData) {
                        data.field[key]=jsonData[key];
                    }
                    $.ajax({
                        async: true,
                        type: 'POST',
                        url: 'signature/serviceIntercept',
                        data: data.field,
                        dataType: 'text',
                        success: function (data) {
                            layer.msg(data, {time:0});
                        }
                    });

                }
            });
            return false;
        });
    });
</script>
</@override>
<@extends name="/base.ftl"/>
