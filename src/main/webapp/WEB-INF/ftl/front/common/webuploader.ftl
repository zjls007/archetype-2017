<#macro html imgList size=1 name='' marginLeft='110' p1='' p2=''>
    <#list 1..size as i>
        <#-- 定义变量开始 -->
        <#local nameVal = name/>
        <#if size gt 1>
            <#local nameVal = name + "[" + (i-1) + "]"/>
        </#if>
        <#local p1Val = p1/>
        <#if size gt 1 && p1 != ''>
            <#local p1Val = p1 + "[" + (i-1) + "]"/>
        </#if>
        <#local p2Val = p2/>
        <#if size gt 1 && p2 != ''>
            <#local p2Val = p2 + "[" + (i-1) + "]"/>
        </#if>
        <#-- 定义变量结束 -->

    <div class="uploadImg" style="<#if (i gt 1 && imgList?size == 0) || (i gt 1 && (i - 1) gt imgList?size)>display:none;</#if><#if i != 1>margin-left: 40px;<#else>margin-left: ${marginLeft!}px;</#if>"
         <#if i != 1>tag="hidden"</#if>
         <#if name!=''>group="${name!}"</#if>
        >
        <#if imgList??>
        <#if i gt imgList?size>
            <i class="file_ico"></i>
            <p class="add-img">添加图片</p>
            <img class="add" style="display: none">
            <i class="del_close" style="display:none"></i>
            <input type="hidden" class="imgMD5" name="${nameVal!}"/>
            <input type="hidden" name="${p1Val!}"/>
            <input type="hidden" name="${p2Val!}"/>
        <#else>
            <i class="file_ico" style="display: none"></i>
            <p class="add-img" style="display: none">添加图片</p>
            <img class="add" src="img/${(imgList[i-1].id)!}">
            <i class="del_close"></i>
            <input type="hidden" class="imgMD5" name="${nameVal!}" value="${(imgList[i-1].id)!}"/>
            <input type="hidden" name="${p1Val!}" value="${(imgList[i-1].p1)!}"/>
            <input type="hidden" name="${p2Val!}" value="${(imgList[i-1].p2)!}"/>
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
            div.find("i.del_close").hide();
            alert('上传失败');
        } else {
            div.find('input:hidden.imgMD5').val(data.data);
            div.find('img.add').show();
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
        div.find("i.del_close").hide();
         alert('上传失败');
    });

    $("div.uploadImg i.del_close").on({click: function () {
        $(this).hide();
        var div = $(this).parents("div.uploadImg");
        div.find("img.add").attr("src", "");
        div.find("input:hidden").val("");
        div.find("i.file_ico").show();
        div.find("p").show();
        div.find('img.add').hide();
    }});
</#macro>
</script>