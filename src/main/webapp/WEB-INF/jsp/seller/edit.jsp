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
<title>${sellerName}上架物品</title>
<!-- start: Css -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/bootstrap.min.css">
<!-- plugins -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/plugins/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/plugins/simple-line-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/plugins/animate.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/plugins/fullcalendar.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/layui/css/layui.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/js/layer/skin/layer.css"/>
<link href="<%=basePath%>/resources/asset/css/style.css" rel="stylesheet">
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
                <li><a href="<%=basePath%>/pkt/index">返回官网</a></li>
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
                <li><a href="<%=basePath%>/pkt/index">返回官网</a></li>
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
          <h3 class="animated fadeInLeft">添加新品</h3>
          <p class="animated fadeInDown"> 菜品功能 <span class="fa-angle-right fa"></span> 菜品添加 </p>
        </div>
      </div>
    </div>
    <div class="form-element">
      <div class="col-md-12 padding-0">
        <div class="col-md-12">
          <div class="panel form-element-padding">
            <div class="panel-heading">
              <h4>新品上架</h4>
            </div>
            <div class="panel-body" style="padding-bottom:30px;">
              <div class="col-md-12">
                <div class="form-group">
                  <label class="col-sm-2 control-label text-right">菜品名称</label>
                  <div class="col-sm-3">
                    <input type="text" class="form-control" id="goods_name">
                  </div>
                  <label class="col-sm-2 control-label text-right">菜系</label>
                  <div class="col-sm-3">
                    <input type="text" class="form-control" id="goods_cuisine">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label text-right">菜品价格</label>
                  <div class="col-sm-3">
                    <input name="price_min" placeholder="￥" id="goods_price" autocomplete="off" class="layui-input form-control" type="text">
                  </div>
                  <label class="col-sm-2 control-label text-right">促销价格</label>
                  <div class="col-sm-3">
                    <input name="price_min" placeholder="￥" id="goods_price_now" autocomplete="off" class="layui-input form-control" type="text">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label text-right">菜品简单描述</label>
                  <div class="col-sm-3">
                    <input type="text" class="form-control" id="goods_simp_desc">
                  </div>
                  <label class="col-sm-2 control-label text-right">类型</label>
                  <div class="col-sm-3">
                      <select class="form-control" id="goods_classify">
                        <option value="1">荤菜</option>
                        <option value="2">汤</option>
                        <option value="3">小炒</option>
                        <option value="4">火锅</option>
                      </select>
                  </div>
                </div>

                <div class="form-group">
                  <label class="col-sm-2 control-label text-right">商品具体描述</label>
                  <div class="col-sm-6">
                    <input type="text" class="form-control" id="goods_desc">
                  </div>
                </div>


                <div class="form-group">
                  <label class="col-sm-2 control-label text-right">图片上传</label>
                  <div class="layui-upload col-sm-10">
                    <button type="button" class="layui-btn" id="upImg">上传</button><p>注意:最多可以上传三张图片</p>
                    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                      预览图：
                      <div class="layui-upload-list" id="imgDiv"></div>
                    </blockquote>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-12 text-center" style="margin:20px;">
              <input class="submit btn btn-danger" type="button" onclick="subInfo()" value="提交">
            </div>
        </div>
      </div>
    </div>
  </div>
  <!-- end: content --> 
  
</div>

<!-- start: Javascript --> 
<script src="<%=basePath%>/resources/asset/js/jquery.min.js"></script> 
<script src="<%=basePath%>/resources/asset/js/jquery.ui.min.js"></script> 
<script src="<%=basePath%>/resources/asset/js/bootstrap.min.js"></script> 
<script src="<%=basePath%>/resources/layui/layui.js"></script>
<script src="<%=basePath%>/resources/js/layer/layer.js"></script>
<!-- plugins -->
<script src="<%=basePath%>/resources/asset/js/plugins/jquery.nicescroll.js"></script>
<!-- custom -->
<script src="<%=basePath%>/resources/asset/js/main.js"></script>
<script src="<%=basePath%>/resources/myJs/seller/edit.js"></script>
<script src="<%=basePath%>/resources/myJs/seller/index.js"></script>
<!-- end: Javascript -->
</body>
</html>