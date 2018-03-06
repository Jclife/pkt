function shopCart(item) {
    var id = $(item).attr("class");
    var t = $(item).get(0).tagName;
    var addNum;
    if (t=="BUTTON"){
        addNum = $("#setAddNum").val();
    }else if(t=="A"){
        addNum = 1;
    }
    addNum = parseInt(addNum);
    $.ajax({
        url: '/pkt/shopCartList',
        type: 'post',
        async: true,
        data: {
            id: id,
            addNum:addNum
        },
        success: function (data) {
            layer.msg('加入成功', {
                offset: ['40%', '50%'],
                anim: 6
            });
            var allNum = $("#allListNumber").attr("data-count");
            allNum = parseInt(allNum);
            var tmpObj ;

            var isExit = false;
            var cl = $("#cartLists li");
            var cn = new Array();
            for (var i = 0; i < cl.length; i++) {
                cn.push($(cl[i]).find("p:eq(0)"));
            }
            if (cl.length==0){
                $("#allListNumber").attr("data-count",(allNum+1));
                $("#cartLists").append("<li class="+data.id+">" +
                    "<a onclick='deleteCart(this)' tValue='"+data.id+"' class='remove'>×</a>" +
                    "<a href='<%=basePath%>/pkt/shopDetail'>" +
                    "<img src='http://123.207.250.128:8888/"+data.imgs+"' style='width: 80px;height: 80px;'/>" +
                    data.name+"&nbsp;" +
                    "</a>" +
                    "<span class='quantity'><p class='number' style='display: inline-block;'> "+data.num+"</p> × ￥<p style='display: inline-block;' class='price'>"+data.price+"</p></span>" +
                    "</li>");
                var allPrice;
                allPrice = data.price;
                allPrice = parseFloat(allPrice);
                $("#addAllPrice").html(allPrice);
            }else {
                for (var i = 0; i < cl.length; i++) {
                    if (id==$(cl[i]).attr("class")){
                        isExit = true;
                        tmpObj = $(cn[i]);
                    }
                }
                if (isExit){
                    var num = tmpObj.text();
                    num = parseInt(num);
                    num = num+addNum;
                    tmpObj.html(num);
                }else {
                    $("#allListNumber").attr("data-count",(allNum+1));
                    $("#cartLists").append("<li class="+data.id+">" +
                        "<a onclick='deleteCart(this)' tValue='"+data.id+"' class='remove'>×</a>" +
                        "<a href='<%=basePath%>/pkt/shopDetail'>" +
                        "<img src='http://123.207.250.128:8888/"+data.imgs+"' style='width: 80px;height: 80px;'/>" +
                        data.name+"&nbsp;" +
                        "</a>" +
                        "<span class='quantity'><p class='number' style='display: inline-block;'> "+data.num+"</p> × ￥<p style='display: inline-block;' class='price'>"+data.price+"</p></span>" +
                        "</li>")
                }

                var tmpPrice = getAllPrice();
                $("#addAllPrice").html(tmpPrice);
            }

        },
        error: function (data) {
            console.log(data)
        }
    });

}

function toShop(item) {
    var goods_id = $(item).attr("class");
    var url = 'pkt/shopDetail/'+goods_id;
    $(location).prop('href', url);
}

function deleteCart(item) {
    var id = $(item).attr("tValue");
    $.ajax({
        url: '/pkt/deleteCart',
        type: 'post',
        async: true,
        data: {
            id: id
        },
        success: function (data) {
            if (data=="success"){
                var cl = $("#cartLists li");
                for (var i = 0; i < cl.length; i++) {
                    if (id==$(cl[i]).attr("class")){
                        $(cl[i]).remove();
                    }
                }
                var tmpPrice = getAllPrice();
                var allNum = $("#allListNumber").attr("data-count");
                allNum = parseInt(allNum);
                $("#allListNumber").attr("data-count",(allNum-1));
                $("#addAllPrice").html(tmpPrice);
                layer.msg('删除成功', {
                    offset: ['40%', '50%'],
                    anim: 6
                });
            }else if (data=="error"){
                layer.msg('发生未知原因，请重新尝试', {
                    offset: ['40%', '50%'],
                    anim: 6
                });
            }
        },
        error:function () {

        }
    });

}

function getAllPrice() {
    var ncl = $("#cartLists li");
    var ncn = new Array();
    var ncp = new Array();
    var tmpPrice = 0;
    for (var i = 0; i < ncl.length; i++) {
        ncn.push($(ncl[i]).find("p:eq(0)"));
        ncp.push($(ncl[i]).find("p:eq(1)"));
    }
    for (var i = 0; i < ncn.length; i++) {
        tmpPrice = tmpPrice + parseInt($(ncn[i]).text()) * parseFloat($(ncp[i]).text());
    }
    tmpPrice = parseFloat(tmpPrice).toFixed(2);
    return tmpPrice;
}

function changeNum(item) {
    var num = $(item).val();
    var goods_id = $(item).attr("tValue");
    $.ajax({
        url: '/pkt/changeNum',
        type: 'post',
        async: true,
        data: {
            id: goods_id,
            num:num
        },
        success: function (data) {
            if (data=="success"){
                var price = $(item).attr("tPrice");
                var tmpPrice = num*parseFloat(price).toFixed(2);
                $(item).parent().parent().parent().find("td:eq(3)").find("span:eq(0)").html("¥"+tmpPrice);

                var priceList = $("#goodsList tr");
                var allPrice = 0;
                allPrice = parseInt(allPrice);
                for (var i =0;i<priceList.length-1;i++){
                    var tP=$(priceList[i]).find("td:eq(2)").find("div:eq(0)").find("input:eq(0)").attr("tPrice");
                    var tN=$(priceList[i]).find("td:eq(2)").find("div:eq(0)").find("input:eq(0)").val();
                    var p = tN*parseFloat(tP).toFixed(2);
                    allPrice = allPrice+p;
                }
                $("#smallPrice").html("¥"+allPrice);
                $("#allPrice").html("¥"+allPrice);
            }else if (data=="error"){
                layer.msg('发生未知原因，请重新尝试', {
                    offset: ['40%', '50%'],
                    anim: 6
                });
            }
        },
        error:function () {

        }
    });
}

function cleanCart() {
    var name = $("#fName").val();
    var address = $("#fAddress").val();
    var phone = $("#fPhone").val();

    var userInfo = {};
    userInfo["name"] = name;
    userInfo["address"] = address;
    userInfo["phone"] = phone;
    if (isEmpty(name)&&isEmpty(address)&&isEmpty(phone)){
        var json = JSON.stringify(userInfo);
        $.ajax({
            url: '/pkt/payForCart',
            type: 'post',
            async: true,
            data: {
                userInfo:json
            },
            success: function (data) {
                if (data=="ok"){
                    layer.msg('完成订单', {
                        offset: ['40%', '50%'],
                        anim: 6
                    });
                    $(location).prop('href', '/pkt/personal');
                }
            },
            error:function () {

            }
        });
    }else {
        layer.msg('请填写完整信息', {
            offset: ['40%', '50%'],
            anim: 6
        });

    }
}

function isEmpty(val) {
    if (val==null||val==""||val==undefined) return false;
    else return true;
}

function remind() {
    layer.msg('抱歉，暂未开放回复评论功能，敬请期待', {
        offset: ['40%', '50%'],
        anim: 6
    });
}