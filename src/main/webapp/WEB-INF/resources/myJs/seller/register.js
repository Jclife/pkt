function to_register() {
    var store_account = $("#account").val();
    var store_password = $("#InPassword").val();
    var store_name = $("#name").val();
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
            if (data["status"]=="success"){
                layer.confirm('注册成功，请返回登录', {
                    btn: ['好的']
                }, function(){
                    $(location).prop('href', '/seller/login');
                });
            }else{
                layer.tips("注册失败，请重新注册");
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