$(function(){
    $(".per_couponul li").click(function(){
        $(".per_couponul li").removeClass("active");
        $(this).addClass("active");
        $(".per_coupon_box").hide();
        $(".per_coupon_box").eq($(".per_couponul li").index(this)).fadeIn();
    });
    $(".per_nav li").click(function(){
        $(".per_nav li").removeClass("active");
        $(this).addClass("active");
        $(".per_box").hide();
        $(".per_box").eq($(".per_nav li").index(this)).fadeIn();
    });
    $(".per_nav2 li").click(function(){
        $(".per_nav2 li").removeClass("active");
        $(this).addClass("active");
        $(".per_box").hide();
        $(".per_box").eq($(".per_nav2 li").index(this)).fadeIn();
    });
    $(".popover-options a").popover({html : true });
});

function closePop(item) {
    var id = $(item).attr("aria-describedby");
    var listDiv = $(".popover");
    for (var i = 0;i<listDiv.length;i++){
        if (id!=$(listDiv[i]).attr("id")){
            $(listDiv[i]).hide();
        }
    }
}

function getComment(item) {
    var text = $(item).parent().find("input:eq(0)").val();
    if(text==null||text==undefined||text==''){
        layer.msg('请添加评论内容', {
            offset: ['40%', '50%'],
            anim: 6
        });
    }else {
        $(item).parent().parent().hide();
        var id = $(item).parent().find("input:eq(0)").attr("tid");
        var com_id = $(item).parent().find("input:eq(0)").attr("cid");
        $.ajax({
            url: '/pkt/addComment',
            type: 'post',
            async: true,
            data: {
                text:text,
                goods_id:id,
                com_id:com_id
            },
            success: function (data) {
                if (data=="ok"){
                    layer.msg('评论添加成功', {
                        offset: ['40%', '50%'],
                        anim: 6
                    });
                    window.location.reload();
                }
            },
            error:function () {

            }
        });
    }
}

function changePass() {
    var oldPass = $("#oldPass").val();
    var newPass = $("#newPass").val();
    var repeatPass = $("#repeatPass").val();
    if (newPass==repeatPass){
        $.ajax({
            url: '/pkt/modifyPass',
            type: 'post',
            async: true,
            data: {
                oldPass:oldPass,
                newPass:newPass
            },
            success: function (data) {
                if (data=="ok"){
                    layer.msg('密码修改成功', {
                        offset: ['40%', '50%'],
                        anim: 6
                    });
                    window.location.reload();
                }
            },
            error:function () {

            }
        });
    }else {
        layer.msg('密码不一致，请重新输入', {
            offset: ['40%', '50%'],
            anim: 6
        });
        $("#oldPass").val('');
        $("#newPass").val('');
        $("#repeatPass").val('');
    }
}

function confirmReceipt(item) {
    var comId = $(item).attr("gid");
    $.ajax({
        url: '/pkt/changeGoodsStatus',
        type: 'post',
        async: true,
        data: {
            com_id:comId
        },
        success: function (data) {
            if (data=="ok"){
                layer.msg("已经确定收货", {
                    offset: ['40%', '50%'],
                    anim: 6
                });
                window.location.reload();
            }else {
                layer.msg("发生未知错误", {
                    offset: ['40%', '50%'],
                    anim: 6
                });
                window.location.reload();
            }
        },
        error:function () {

        }
    });
}

function checkMap(item) {
    var cid = $(item).attr("gid");
    console.log(cid);
    layer.open({
        type: 2,
        title: '查看配送流程',
        offset: ['100px', '30%'],
        shadeClose: true,
        shade: false,
        maxmin: false, //开启最大化最小化按钮
        area: ['893px', '600px'],
        content: '//localhost:8080/pkt/checkDelivery/'+cid
    });
}

