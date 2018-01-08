<#macro html size=1 name='' marginLeft='110' p1='' p2=''>
    <#list 1..size as i>
    <div class="uploadImg" style="<#if i != 1>display:none;margin-left: 40px;<#else>margin-left: ${marginLeft!}px;</#if>"
         <#if i != 1>tag="hidden"</#if>
         <#if name!=''>group="${name!}"</#if>
        >
        <i class="file_ico"></i>
        <p class="add-img">添加图片</p>
        <img class="add">
        <i class="del_close"></i>
        <#if size == 1>
            <input type="hidden" name="${name!}"/>
            <#if p1 != ''>
                <input type="hidden" name="${p1!}"/>
            </#if>
            <#if p2 != ''>
                <input type="hidden" name="${p2!}"/>
            </#if>
        <#else>
            <input type="hidden" name="${name!}[${i-1}]"/>
            <#if p1 != ''>
                <input type="hidden" name="${p1!}[${i-1}]"/>
            </#if>
            <#if p2 != ''>
                <input type="hidden" name="${p2!}[${i-1}]"/>
            </#if>
        </#if>
    </div>
    </#list>
</#macro>
<script>
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
        pick: {
            id : '.uploadImg',
            // 一次只能传一个文件
            multiple: false
        },
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
            var div = $('#rt_' + file.source.ruid).parents('div.uploadImg');
            div.find('img.add').attr( 'src', src );
            div.find("i.file_ico").hide();
            div.find("i.del_close").show();
            div.find("p").hide();
        }, 140, 160 );
    });

    uploader.on("uploadSuccess", function( file, data){
        var div = $('#rt_' + file.source.ruid).parents('div.uploadImg');
        if ( data.code!="success") {
            div.find('img').attr( 'src', '');
            div.find("i.file_ico").show();
            div.find("p").show();
            alert('上传失败');
        } else {
            div.find('input:hidden').val(data.data);

            var next = div.next('div.uploadImg[tag="hidden"][group=' + div.attr('group') +']');
            if (next.length > 0) {
                next.show();
            }
        }
    });

    // 文件上传失败，显示上传出错。
    uploader.on( 'uploadError', function( file ) {
        $('#rt_' + file.source.ruid).parents('div.uploadImg').find('img').attr( 'src', '');
        div.find("i.file_ico").show();
        div.find("p").show();
         alert('上传失败');
    });

    $("div.uploadImg i.del_close").on({click: function () {
        $(this).hide();
        var div = $(this).parents("div.uploadImg");
        div.find("img.add").attr("src", "");
        div.find("input:hidden").val("");
        div.find("i.file_ico").show();
        div.find("p").show();
    }});
</#macro>
</script>