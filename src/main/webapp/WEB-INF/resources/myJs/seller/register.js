function to_register() {
    var store_account = $("#account").val();
    var store_password = $("#InPassword").val();
    var RePassword = $("#RePassword").val();
    var store_name = $("#name").val();
    if (store_password==RePassword){
        var phoneReg=/^[1][3,4,5,7,8][0-9]{9}$/;
        var passReg= /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$/;
        if (!phoneReg.test(store_account)){
            layer.msg("账号错误，请输入正确手机号")
            $("#account").val('');
        }else if (!passReg.test(store_password)){
            layer.msg('注册失败，请输入不小于8位且包含大小写英文字母密码');
            $("#InPassword").val('');
            $("#RePassword").val('');
        }else {
            $.ajax({
                url:'/seller/to_register',
                type:'post',
                async:true,
                data:{
                    store_account:store_account,
                    store_password:store_password,
                    store_name:store_name
                },
                beforeSend:function (xhr) {
                    console.log(xhr+"   开始发送前");
                },
                success:function (data) {
                    console.log(data["status"]);
                    if (data["status"]=="success"){
                        layer.confirm('注册成功，请返回登录', {
                            btn: ['好的']
                        }, function(){
                            $(location).prop('href', '/seller/login');
                        });
                    }else if (data["status"]=="error"){
                        layer.msg("注册失败，请重新注册");
                    }else if (data["status"]=="isExit"){
                        layer.msg("注册失败，该账号已经存在");
                        $("#account").val('');
                    }
                },
                error:function () {
                    layer.tips("注册失败，原因未知");
                },
                complete:function () {
                    console.log("结束")
                }
            });
        }
    }else {
        $("#InPassword").val('');
        $("#RePassword").val('');
        layer.tips("输入密码不一致，请重新输入");
    }

}