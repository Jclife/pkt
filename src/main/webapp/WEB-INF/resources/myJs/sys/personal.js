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
            }
        },
        error:function () {

        }
    });

}