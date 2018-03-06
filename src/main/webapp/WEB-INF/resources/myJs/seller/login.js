function login() {
    var account = $("#account").val();
    var password = $("#password").val();

    $.ajax({
        url:'/seller/login_check',
        type:'post',
        async:true,
        data:{
            account:account,
            password:password
        },
        beforeSend:function (xhr) {
            console.log(xhr+"   开始发送前");
        },
        success:function (data) {
            if (data["status"]=="success"){

                console.log("到了login的success")
                $(location).prop('href', '/seller/index');
            }else{
                console.log("到了login的error")
                layer.confirm('登录失败，请重新登录', {
                    btn: ['好的']
                }, function(){
                    $(location).prop('href', '/seller/login');
                });
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