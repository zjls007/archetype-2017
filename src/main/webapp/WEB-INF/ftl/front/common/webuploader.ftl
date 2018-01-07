<#macro init>
    // 初始化Web Uploader
    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: '${basePath}/statics/ui/webuploader-0.1.5/Uploader.swf',
        // 文件接收服务端。
        server: 'upload',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '.uploadImg',
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        // 同一图片可以重复上传
        duplicate :true,
        // 只允许选择图片文件。
        accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
        }
    });

    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }
            $('#rt_' + file.source.ruid).parents('div.uploadImg').find('img').attr( 'src', src );
        }, 140, 160 );
    });

    uploader.on("uploadSuccess", function( file, data){
        if ( data.code!="success") {
            $('#rt_' + file.source.ruid).parents('div.uploadImg').find('img').attr( 'src', '');
            alert('上传失败');
        } else {
            $('#rt_' + file.source.ruid).parents('div.uploadImg').find('input:hidden').val(data.data);
        }
    });

    // 文件上传失败，显示上传出错。
    uploader.on( 'uploadError', function( file ) {
        $('#rt_' + file.source.ruid).parents('div.uploadImg').find('img').attr( 'src', '');
         alert('上传失败');
    });
</#macro>