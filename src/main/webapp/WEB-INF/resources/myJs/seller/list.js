layui.use(['laypage', 'layer'], function() {
    var laypage = layui.laypage, layer = layui.layer;
    var pathName = window.location.pathname;
    var curr = pathName.split("/")[5];
    var limit = pathName.split("/")[6];
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
            var type,page,limit,content;
            if (!first){
                if (urlList.length==3){
                    content = "all";
                    type = 5;
                    page = obj["curr"];
                    limit = obj["limit"];
                }else {
                    content = urlList[3];
                    type = urlList[4];
                    page = obj["curr"];
                    limit = obj["limit"];
                }
                if ((limit*(page-1))>getCount()){
                    var url = "seller/find/"+content+"/"+type+"/1/"+limit;
                    $(location).prop('href', url);
                }else {
                    var url = "seller/find/"+content+"/"+type+"/"+page+"/"+limit;
                    $(location).prop('href', url);
                }
            }
        }
    });
});
function findGoods() {
    var type = $("#find_type option:selected").val();
    var content = $("#find_name").val();
    if (content==""){
        content="all";
    }
    var url = "/seller/find/"+content+"/"+type+"/1/6";
    $(location).prop('href', url);
}