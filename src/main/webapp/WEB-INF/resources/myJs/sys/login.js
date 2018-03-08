function click_login() {
    var phone = $("#login_phone").val();
    var password = $("#login_password").val();

    $.ajax({
        url:'/pkt/login_check',
        type:'post',
        async:true,
        data:{
            phone:phone,
            password:password
        },
        beforeSend:function (xhr) {
            console.log(xhr+"   开始发送前");
        },
        success:function (data) {
            if (data["status"]=="ok"){
                layer.confirm('欢迎登录，是否同步当前购物车到账号(同步后可以长期保存在账号里,不同步则有时限)', {
                    btn: ['好的','不用了']
                }, function(){
                    $(location).prop('href', '/pkt/syncSession');
                },function () {
                    $(location).prop('href', '/pkt/index');
                });
            }else{
                layer.confirm('登录失败，请重新输入账号密码', {
                    btn: ['好的']
                }, function(){
                    window.location.reload();
                });
            }
        },
        error:function (data) {
            console.log(data)
        },
        complete:function () {
            console.log("ajax结束")
        }
    })
}

function click_register() {

    if ($("#register_password").val()==$("#repeat_password").val()){
        var password = $("#register_password").val();
        var name = $("#register_name").val();
        var phone = $("#register_phone").val();
        var email = $("#register_email").val();

        $.ajax({
            url:'/pkt/register_check',
            type:'post',
            async:true,
            data:{
                phone:phone,
                name:name,
                password:password,
                email:email
            },
            beforeSend:function (xhr) {
                console.log(xhr+"   开始发送前");
            },
            success:function (data) {
                if (data["status"]=="ok"){
                    layer.confirm('注册成功，请返回登录', {
                        btn: ['好的']
                    }, function(){
                        window.location.reload();
                    });
                }else{
                    layer.confirm('注册失败，原因未知', {
                        btn: ['好的']
                    }, function(){
                        window.location.reload();
                    });
                }
            },
            error:function () {
                layer.confirm('发生未知错误', {
                    btn: ['好的']
                }, function(){
                    window.location.reload();
                });
            },
            complete:function () {
                console.log("ajax结束")
            }
        })

    }else {
        $("#register_password").val("");
        $("#repeat_password").val("");
        layer.msg("请输入重复相同密码");
    }

}