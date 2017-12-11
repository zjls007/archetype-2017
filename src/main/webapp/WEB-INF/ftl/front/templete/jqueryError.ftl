    var onError = function (xhr, status, error) {
        var statuCode = xhr.status;
        if (!statuCode) {
            statuCode = status.status;
        }
        // 未登录
        if (statuCode == 403) {
            if (window.parent.length == 0) {
                layer.open({
                    content: '请重新登录!'
                    ,closeBtn: 0
                    ,btnAlign: 'c'
                    ,btn: ['确定']
                    ,yes: function(index, layero){
                        location.href = "";
                    }
                });
            } else {
                parent.reLogin();
            }
        } else if (statuCode == 404) {
            layer.msg('请求地址不存在!');
        } else if (statuCode == 500) {
            layer.msg('服务器内部错误!');
        } else if (statuCode == 0) {
            layer.msg('服务器已关闭!');
        }else {
            layer.msg('未知异常!');
        }
    };

    // jquery全局ajax设置
    $(document).ajaxError(onError);