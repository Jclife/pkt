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
<meta name="description" content="12">
<meta name="author" content="12">
<meta name="keyword" content="12">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${sellerName}后台主页</title>
<!-- start: Css -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/js/layer/skin/layer.css">
<!-- plugins -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/plugins/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/plugins/simple-line-icons.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/plugins/animate.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/asset/css/plugins/fullcalendar.min.css"/>
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
      <ul class="nav navbar-nav navbar-right user-nav">
        <c:choose>
          <c:when test="${sellerName==null}">
            <li class="user-name"><span>请登录</span></li>
            <li class="dropdown avatar-dropdown"> <img src="<%=basePath%>/resources/asset/img/avatar.jpg" class="img-circle avatar" alt="user name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"/>
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
        <div class="col-md-6 col-sm-12">
          <c:choose>
            <c:when test="${sellerName==null}">
              <a href="<%=basePath%>/seller/login"><h3 class="animated fadeInLeft">请返回登录</h3></a>
            </c:when>
            <c:otherwise>
              <h3 class="animated fadeInLeft">欢迎您：${sellerName}</h3>
              <p class="animated fadeInDown"><span class="fa  fa-map-marker"></span>${store_info["store_address"]}</p>
            </c:otherwise>
          </c:choose>

        </div>
      </div>
    </div>
    <div class="col-md-12" style="padding:20px;">
      <div class="col-md-12 padding-0">

        <div class="col-md-8 padding-0">
          <div class="col-md-12 padding-0">
            <%--待发货--%>
            <div class="col-md-6">
              <div class="panel box-v1">
                <div class="panel-heading bg-white border-none">
                  <div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
                    <h4 class="text-left">热卖榜</h4>
                  </div>
                  <div class="col-md-6 col-sm-6 col-xs-6 text-right">
                    <h4> <span class="icon-check icons icon text-right"></span> </h4>
                  </div>
                </div>
                <div class="panel-body text-center">
                  <ul class="normal-list">
                    <c:forEach items="${hotList}" var="item" begin="0" end="7">
                      <li class="text-left"><span class="right">${item.goods_sale_count}</span><a><span class="fa fa-angle-right"></span>${item.goods_name}</a></li>
                    </c:forEach>
                  </ul>
                  <hr/>
                </div>
              </div>
            </div>

            <%--已售出--%>
            <div class="col-md-6">
              <div class="panel box-v1">
                <div class="panel-heading bg-white border-none">
                  <div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
                    <h4 class="text-left">收入榜</h4>
                  </div>
                  <div class="col-md-6 col-sm-6 col-xs-6 text-right">
                    <h4> <span class="icon-bag icons icon text-right"></span> </h4>
                  </div>
                </div>
                <div class="panel-body text-center">
                  <ul class="normal-list">
                    <c:forEach items="${moneyList}" var="item" begin="0" end="7">
                      <li class="text-left"><span class="right">¥ ${item["money"]}</span><a><span class="fa fa-angle-right"></span>${item["name"]}</a></li>
                    </c:forEach>
                  </ul>
                  <hr/>
                </div>
              </div>
            </div>
          </div>
        </div>

        <%--个人信息模块--%>
        <div class="col-md-4">
          <div class="col-md-12 padding-0">
            <div class="panel box-v2">
              <div class="panel-heading padding-0"> <img src="<%=basePath%>/resources/asset/img/bg2.jpg" class="box-v2-cover img-responsive"/>
                <div class="box-v2-detail">
                  <c:choose>
                    <c:when test="${store_info.store_head==null}">
                      <img src="<%=basePath%>/resources/asset/img/avatar.jpg" class="img-responsive"/>
                    </c:when>
                    <c:otherwise>
                      <img src="http://123.207.250.128:8888/${store_info["store_head"]}" class="img-responsive"/>
                    </c:otherwise>
                  </c:choose>
                  <h4>${sellerName}</h4>
                </div>
              </div>
              <div class="panel-body">
                <div class="col-md-12 padding-0 text-center">
                  <div class="col-md-4 col-sm-4 col-xs-6 padding-0">
                    <h3>${allNum}</h3>
                    <p>已售出</p>
                  </div>
                  <div class="col-md-4 col-sm-4 col-xs-6 padding-0">
                    <h3>${commentNum}</h3>
                    <p>评论数</p>
                  </div>
                  <div class="col-md-4 col-sm-4 col-xs-6 padding-0">
                    <h3>${money}</h3>
                    <p>总收入</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- end: content --> 
  
</div>

<!-- start: Javascript --> 
<script src="<%=basePath%>/resources/asset/js/jquery.min.js"></script>
<script src="<%=basePath%>/resources/js/layer/layer.js"></script>
<script src="<%=basePath%>/resources/asset/js/jquery.ui.min.js"></script>
<script src="<%=basePath%>/resources/asset/js/bootstrap.min.js"></script> 
<!-- plugins --> 
<script src="<%=basePath%>/resources/asset/js/plugins/jquery.nicescroll.js"></script> 
<!-- custom --> 
<script src="<%=basePath%>/resources/asset/js/main.js"></script> 
<script src="<%=basePath%>/resources/myJs/seller/index.js"></script>
<!-- end: Javascript -->
</body>
</html>