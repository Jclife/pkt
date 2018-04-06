<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<base href="<%=basePath%>">
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="description" content="xxxxx">
<meta name="author" content="xxxxx">
<meta name="keyword" content="xxxxx">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${sellerName}完善信息</title>
<!-- start: Css -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/bootstrap.min.css">
<!-- plugins -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/plugins/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/plugins/simple-line-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/plugins/animate.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/plugins/fullcalendar.min.css"/>
<link href="<%=basePath%>/resources/asset/css/style.css" rel="stylesheet">
<link href="<%=basePath%>/resources/layui/css/layui.css" rel="stylesheet">
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=xq2eQ5Dz8alIO2k593NVITIVYM8E7zCT"></script>
  <style type="text/css">
    body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
    #l-map{height:300px;width:50%;}
    #r-result{width:100%;}
  </style>
<!-- end: Css -->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="<%=basePath%>/resources/asset/js/html5shiv.min.js"></script>
      <script src="<%=basePath%>/resources/asset/js/respond.min.js"></script>
    <![endif]-->
</head>
<body id="mimin" class="dashboard">
<!-- start: Header -->
<nav class="navbar navbar-default header navbar-fixed-top">
  <div class="col-md-12 nav-wrapper">
    <div class="navbar-header" style="width:100%;">
      <div class="opener-left-menu is-open"> <span class="top"></span> <span class="middle"></span> <span class="bottom"></span> </div>
      <a href="<%=basePath%>/seller/index" class="navbar-brand"> <b>返回首页</b> </a>
      <ul class="nav navbar-nav navbar-right user-nav">
        <c:choose>
          <c:when test="${sellerName==null}">
            <li class="user-name"><span>请登录</span></li>
            <li class="dropdown avatar-dropdown">
              <img src="<%=basePath%>/resources/asset/img/avatar.jpg" class="img-circle avatar" alt="user name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"/>
              <ul class="dropdown-menu user-dropdown">
                <li><a href="<%=basePath%>/seller/login"><span class="fa fa-power-off"></span> 返回登录</a></li>
              </ul>
            </li>
          </c:when>
          <c:otherwise>
            <li class="user-name"><p id="seller_name_qu">${sellerName}</p></li>
            <li class="dropdown avatar-dropdown">
              <c:choose>
                <c:when test="${store_info.store_head==null}">
                  <img src="<%=basePath%>/resources/asset/img/avatar.jpg" class="img-circle avatar" alt="user name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"/>
                </c:when>
                <c:otherwise>
                    <img src="http://123.207.250.128:8888/${store_info["store_head"]}" class="img-circle avatar" alt="user name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"/>
                </c:otherwise>
              </c:choose>
              <ul class="dropdown-menu user-dropdown">
                <li><a onclick="seller_exit()" style="cursor:pointer;"><span class="fa fa-power-off"></span> 退出登录</a></li>
              </ul>
            </li>
          </c:otherwise>
        </c:choose>

      </ul>
    </div>
  </div>
</nav>
<!-- end: Header -->
<div class="container-fluid mimin-wrapper"> 
  <!-- start:Left Menu -->
  <div id="left-menu">
    <div class="sub-left-menu scroll">
      <ul class="nav nav-list">
        <li>
          <div class="left-bg"></div>
        </li>
        <li class="ripple"> <a class="tree-toggle nav-header"> <span class="fa-diamond fa"></span> 基本 <span class="fa-angle-right fa right-arrow text-right"></span> </a>
          <ul class="nav nav-list tree">
            <li><a href="<%=basePath%>/seller/index">返回首页</a></li>
            <li><a href="<%=basePath%>/seller/perfect">信息完善</a></li>
          </ul>
        </li>
        <li class="ripple"> <a class="tree-toggle nav-header"> <span class="fa-diamond fa"></span> 功能列表 <span class="fa-angle-right fa right-arrow text-right"></span> </a>
          <ul class="nav nav-list tree">
            <li><a href="<%=basePath%>/seller/operation">菜品操作</a></li>
            <li><a href="<%=basePath%>/seller/edit">菜品添加</a></li>
            <li><a href="<%=basePath%>/seller/list">菜品列表</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
  <!-- end: Left Menu --> 
  
  <!-- start: content -->
  <div id="content">
    <div class="panel">
      <div class="panel-body">
        <div class="col-md-12">
          <h3 class="animated fadeInLeft">完善信息</h3>
          <p class="animated fadeInDown"> 基本 <span class="fa-angle-right fa"></span> 完善信息 </p>
        </div>
      </div>
    </div>
    <div class="form-element">
      <div class="col-md-12 padding-0">
        <div class="col-md-12">

          <div class="panel form-element-padding">
            <div class="panel-heading">
              <h4>店铺基本信息</h4>
            </div>
            <div class="panel-body" style="padding-bottom:30px;">
              <div class="col-md-12">

                <div class="form-group">
                  <label class="col-sm-2 control-label text-right">店铺名称</label>
                  <div class="col-sm-6">
                    <input type="text" class="form-control" id="store_name" value="${store_info["store_name"]}">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label text-right">店铺简介</label>
                  <div class="col-sm-6">
                    <input type="text" class="form-control" id="store_intro" value="${store_info["store_intro"]}">
                  </div>
                </div>
                <div class="form-group" id="mapPoint" pointValue="${store_info["store_point"]}">
                  <label class="col-sm-2 control-label text-right">地图显示</label>
                  <div id="l-map"></div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label text-right">店铺地址</label>
                  <div class="col-sm-6">
                    <div id="r-result">
                      <input type="text" class="form-control" id="store_address" size="20" placeholder="${store_info["store_address"]}">
                    </div>
                    <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
                  </div>
                </div>
                <div class="form-group" style="padding-top: 15px">
                  <label class="col-sm-2 control-label text-right">店铺标签</label>
                  <div class="col-sm-3">
                    <input type="text" class="form-control" readonly="readonly" id="store_tags" value="${tags}">
                  </div>
                  <div class="col-sm-3">
                    <button class="btn btn-primary" data-toggle="modal" data-target="#myModal">选择商品类型</button>
                  </div>
                </div>
                <div class="form-group layui-upload">
                  <label class="col-sm-2 control-label text-right">店铺头像</label>
                  <div class="col-sm-3 layui-upload-list" style="margin: 0">
                    <c:choose>
                      <c:when test="${store_info.store_head==null}">
                        <img class="layui-upload-img" id="setImg" style="width: 100%;height: 230px;">
                      </c:when>
                      <c:otherwise>
                        <img tValue="${store_info["store_head"]}" class="layui-upload-img" id="setImg" style="width: 100%;height: 230px;" src="http://123.207.250.128:8888/${store_info["store_head"]}">
                      </c:otherwise>
                    </c:choose>
                    <p id="ImgText"></p>
                  </div>
                  <div class="col-sm-3">
                    <button type="button" class="layui-btn" id="uploadImg">上传图片</button>
                  </div>
                </div>
              </div>

            </div>
          </div>
          <div class="col-md-12 text-center">
              <input class="submit btn btn-danger" type="button" onclick="subInfo()" value="提交">
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- end: content --> 
  
</div>

<!--弹出复选-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog form-element">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">选择店铺类型</h4>
      </div>
      <div class="modal-body form-element-padding">
        <div class="form-group" id="TypeTags">
          <div class="col-sm-3 padding-0">
            <input type="checkbox" name="option" value="home">
            家常 </div>
          <div class="col-sm-3 padding-0">
            <input type="checkbox" name="option" value="high_set">
            高定 </div>
          <div class="col-sm-3 padding-0">
            <input type="checkbox" name="option" value="PK">
            私人厨房 </div>
          <div class="col-sm-3 padding-0">
            <input type="checkbox" name="option" value="delicate">
            精致 </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" onclick="setTags()">确定提交</button>
      </div>
    </div>
    <!-- /.modal-content --> 
  </div>
  <!-- /.modal --> 
</div>
<!--弹出复选 end--> 

<!-- start: Javascript --> 
<script src="<%=basePath%>/resources/asset/js/jquery.min.js"></script> 
<script src="<%=basePath%>/resources/asset/js/jquery.ui.min.js"></script> 
<script src="<%=basePath%>/resources/asset/js/bootstrap.min.js"></script> 
<!-- plugins --> 
<script src="<%=basePath%>/resources/asset/js/plugins/jquery.nicescroll.js"></script> 
<!-- custom -->
<script src="<%=basePath%>/resources/myJs/seller/map.js"></script>
<script src="<%=basePath%>/resources/asset/js/main.js"></script>
<script src="<%=basePath%>/resources/layui/layui.js"></script>
<script src="<%=basePath%>/resources/myJs/seller/perfect.js"></script>
<script src="<%=basePath%>/resources/myJs/seller/index.js"></script>
<!-- end: Javascript -->
<script>
  function getPointValue() {
      return
  }
</script>
</body>
</html>