var url = "";
var tags="";

layui.use('upload', function(){
    var $ = layui.jquery,upload = layui.upload;
    var uploadInst = upload.render({
        elem:'#uploadImg',
        url:'/seller/upImg',
        method:'post',
        accept:'file',
        before:function (obj) {
            obj.preview(function (index,file,result) {
                $("#setImg").attr('src',result);
            })
        },
        done:function (res) {
            if (res["code"]>1){
                return layer.msg("上传失败")
            }else {
                url=res["url"];
                return layer.msg("上传成功");
            }
        },
        error:function () {
            var demoText = $("#ImgText");
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });
});

function setTags() {
    var str ="";
    var tepStr = "";
    var setStr = "";
    $("input[type='checkbox']").each(function(){
         if($(this).is(":checked")){
             str =$(this).val()+"/";
             tepStr += str;
         }
    });
    tags = tepStr;
    var e_array=new Array();
    e_array[0]="home";
    e_array[1]="high_set";
    e_array[2]="PK";
    e_array[3]="delicate";

    var c_array=new Array();
    c_array[0]="家常";
    c_array[1]="高定";
    c_array[2]="私人厨房";
    c_array[3]="精致";
    var tmpArr=tepStr.split("/");
    var tmpSetStr = "";
    for(var i = 0,len=e_array.length; i < len; i++) {
        for (var j=0,l=tmpArr.length;j<l;j++){
            if (e_array[i]==tmpArr[j]){
                tmpSetStr = c_array[i]+"、";
                setStr +=tmpSetStr;
            }
        }
    }
    setStr=(setStr.substring(setStr.length-1)=='、')?setStr.substring(0,setStr.length-1):setStr;
    $("#store_tags").val(setStr);
    $('#myModal').modal('hide');
}

function initTags() {
    var store_tags = $("#store_tags").val();
    var tmpArr=store_tags.split("、");
    var tmpStr = "";
    var map = {};
    map['家常']="home";
    map['高定']="high_set";
    map['私人厨房']="PK";
    map['精致']="delicate";
    for (var i = 0;i<tmpArr.length;i++){
        tmpStr += map[tmpArr[i]]+"/";
    }
    tags = tmpStr;
    console.log(tmpStr);
    return tags;
}

function subInfo() {
    var store_name = $("#store_name").val();
    var store_intro = $("#store_intro").val();
    var store_head = isEmpty(url)?url:$("#setImg").attr("tValue");
    var store_address = getMyValue();
    var store_tag=tags;
    if (store_tag==""){
        store_tag = initTags();
    }
    if (!isEmpty(store_address)||store_address==""){
        store_address = $("#store_address").attr("placeholder");
    }
    var store_point = getPotion()['lng']+","+getPotion()['lat'];
    if(getPotion()['lng']==undefined){
        store_point = $("#mapPoint").attr("pointValue");
    }
    if (isEmpty(store_name)&&isEmpty(store_intro)&&isEmpty(store_address)&&isEmpty(store_tag)&&isEmpty(store_point)){
        $.ajax({
            url:'/seller/subInfo',
            type:'post',
            async:true,
            data:{
                store_name:store_name,
                store_tags:store_tag,
                store_intro:store_intro,
                store_head:store_head,
                store_address:store_address,
                store_point:store_point
            },
            success:function (data) {
                if (data["status"]=="success"){
                    $(location).prop('href', '/seller/perfect');
                }else{
                    layer.confirm('提交失败，请重新提交信息', {
                        btn: ['好的']
                    }, function(){
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
}

function isEmpty(value) {
    if (value==undefined||value==''||value==null){
        return false;
    }else {
        return true;
    }
}

