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