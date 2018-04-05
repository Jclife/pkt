var url_list=[];
layui.use('upload', function(){
    var $ = layui.jquery,upload = layui.upload;
    upload.render({
        elem: '#upImg',
        url: '/seller/upImg',
        multiple: true,
        method:'post',
        accept:'file',
        before: function(obj){
            var count = $("#imgDiv").children("img").length;
            if (count<3){
                obj.preview(function(index, file, result){
                    $('#imgDiv').append('<img style="width: 22%;" src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
                });
                if (count==2){
                    $("#upImg").attr("disabled", true);
                }
            }
        },
        done:function (res) {
            if (res["code"]>1){
                return layer.msg("上传失败")
            }else {
                url_list.push(res["url"]);
                return layer.msg("上传成功");
            }
        },
        error:function () {
           return layer.msg("上传失败，原因未知");
        }
    });
});

function subInfo() {
    var goods={
        goods_name:$("#goods_name").val(),
        goods_cuisine:$("#goods_cuisine").val(),
        goods_price: $("#goods_price").val(),
        goods_price_now:$("#goods_price_now").val(),
        goods_simp_desc:$("#goods_simp_desc").val(),
        goods_classify:$('#goods_classify option:selected').val(),
        goods_desc: $("#goods_desc").val(),
        goods_imgs:JSON.stringify(url_list)
    };
    $.ajax({
        url:'/seller/shelves',
        type:'post',
        async:true,
        data:goods,
        success:function (data) {
            if (data["status"]=="success"){
                $(location).prop('href', '/seller/edit');
            }else{
                layer.msg("提交失败，请重新提交信息");
            }
        },
        error:function (data) {
            console.log(data)
        },
        complete:function () {
            console.log("ajax结束")
        }
    });
}

function modifyBase(item) {
    var id = $(item).attr("gid");
    var goods={
        goods_id:id,
        goods_name:$("#goods_name").val(),
        goods_cuisine:$("#goods_cuisine").val(),
        goods_price: $("#goods_price").val(),
        goods_price_now:$("#goods_price_now").val(),
        goods_simp_desc:$("#goods_simp_desc").val(),
        goods_classify:$('#goods_classify option:selected').val(),
        goods_desc: $("#goods_desc").val(),
    };
    $.ajax({
        url:'/seller/modifyBase',
        type:'post',
        async:true,
        data:goods,
        success:function (data) {
            if (data=="success"){
                var url = "seller/modify/"+id;
                $(location).prop('href',url);
            }else{
                layer.msg("提交失败，请重新提交信息");
            }
        },
        error:function (data) {
            console.log(data)
        },
        complete:function () {
            console.log("ajax结束")
        }
    });
}

function modifyPic(item) {
    var id = $(item).attr("gid");
    var imgJson=JSON.stringify(url_list);
    if (url_list.length>0){
        $.ajax({
            url:'/seller/modifyPic',
            type:'post',
            async:true,
            data:{
                id:id,
                imgJson:imgJson
            },
            success:function (data) {
                if (data=="success"){
                    var url = "seller/modify/"+id;
                    $(location).prop('href',url);
                }else{
                    layer.msg("提交失败，请重新提交信息");
                }
            },
            error:function (data) {
                console.log(data)
            },
            complete:function () {
                console.log("ajax结束")
            }
        });
    }else {
        layer.msg("无任何图片上传,无法提交");
    }

}

function deleteGood(item) {
    var id = $(item).attr("gid");
    layer.confirm('是否将商品下架？', {
        btn: ['是','否'] //按钮
    }, function(){
        $.ajax({
            url: '/seller/deleteGood',
            type: 'post',
            async: true,
            data: {
                id: id
            },
            success: function (data) {
                if (data == "success") {
                    var url = "seller/list"
                    $(location).prop('href', url);
                } else {
                    layer.msg("删除失败，请重新提交信息");
                }
            },
            error: function (data) {
                console.log(data)
            }
        });
    }, function(){
        return
    });

}