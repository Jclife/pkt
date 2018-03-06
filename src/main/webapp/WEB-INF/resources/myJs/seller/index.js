function seller_exit() {
    var storeName = $("#seller_name_qu").text();
    $.ajax({
        url:'/seller/sellerExit',
        type:'post',
        async:true,
        data:{
            storeName:storeName
        },
        success:function (data) {
            if (data=="LogOut"){
                layer.tips(storeName+"已经登出");
                $(location).prop('href', '/seller/login')
            }else if (data=="noUser"){
                layer.tips(storeName+"账号没有登录");
                $(location).prop('href', '/seller/index')
            }
        },
        error:function (data) {
            layer.tips("发生未知错误");
            console.log(data)
        },
        complete:function () {
            console.log("结束")
        }
    })
}