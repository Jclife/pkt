layui.use(['laypage', 'layer'], function() {
    var laypage = layui.laypage, layer = layui.layer;
    var pathName = window.location.pathname;
    var curr = pathName.split("/")[4];
    var limit = pathName.split("/")[5];
    laypage.render({
        elem: 'page'
        ,count: getCount()
        ,curr:curr==undefined?1:curr
        ,limit:limit==undefined?6:limit
        ,limits: [6, 12, 18, 24]
        ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
        ,jump: function(obj,first){
            var path = window.location.pathname;
            var urlList = path.split("/");
            var type,page,limit;
            if (!first){
                if (urlList.length==3){
                    type =  1;
                    page = obj["curr"];
                    limit = obj["limit"];
                }else {
                    type =  pathName.split("/")[3];
                    page = obj["curr"];
                    limit = obj["limit"];
                }
                if ((limit*(page-1))>getCount()){
                    var url = "seller/operation/"+type+"/1/"+limit;
                    $(location).prop('href', url);
                }else {
                    var url = "seller/operation/"+type+"/"+page+"/"+limit;
                    $(location).prop('href', url);
                }
            }
        }
    });
});
var select = document.getElementById('find_type');
select.onchange = function(){
    console.log("  change  ");
    var type = $("#find_type option:selected").val();
    var url = "seller/operation/"+type+"/1/"+6;
    $(location).prop('href', url);
};

function confirmDelivery(item){
    var id = $(item).attr("id");
    $.ajax({
        url:'/seller/confirmDelivery',
        type:'post',
        async:true,
        data:{
            id:id
        },
        success:function (data) {
            if (data=="ok"){
                var pathName = window.location.pathname;
                $(location).prop('href', pathName);
            }else{
                layer.confirm('发生错误，请重新提交信息', {
                    btn: ['好的']
                }, function(){
                });
            }
        },
        error:function (data) {
            layer.msg("发生未知错误");
            console.log(data)
        },
        complete:function () {
            console.log("结束")
        }
    })
}
