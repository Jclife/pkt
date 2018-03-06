<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<base href="<%=basePath%>">
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="Keywords" content="网站关键词">
    <meta name="Description" content="网站介绍">
    <link rel="stylesheet" href="<%=basePath%>/resources/backLoginRe/css/base.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/backLoginRe/css/iconfont.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/backLoginRe/css/reg.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/js/layer/skin/layer.css">
</head>
<body>
    <div id="ajax-hook"></div>
    <div class="wrap">
        <div class="wpn">
            <div class="form-data pos">
                <!--<p class="tel-warn hide"><i class="icon-warn"></i></p>-->
                <div class="change-login">
                    <p class="account_number on">用户注册</p>
                </div>
                <div class="form1">
                    <p class="p-input pos">
                        <label for="account">手机号</label>
                        <input type="number" id="account" autocomplete="off">
                    </p>
                    <p class="p-input pos" id="in_password">
                        <label for="InPassword">输入密码</label>
                        <input type="password" id="InPassword">
                    </p>
                    <p class="p-input pos" id="re_password">
                        <label for="RePassword">输入密码</label>
                        <input type="password" id="RePassword">
                    </p>
                    <p class="p-input pos" id="InName">
                        <label for="name">请输入厨房名称</label>
                        <input type="text" id="name">
                    </p>
                </div>
                <div class="reg_checkboxline pos">
                    <span class="z"><i class="icon-ok-sign boxcol" nullmsg="请同意!"></i></span>
                    <input type="hidden" name="agree" value="1">
                    <div class="Validform_checktip"></div>
                    <p>我已阅读并接受 <a href="#" target="_brack">《PKT协议说明》</a></p>
                </div>
                <button class="lang-btn" onclick="to_register()">注册</button>
                <div class="bottom-info">已有账号，<a href="<%=basePath%>/seller/login">马上登录</a></div>
                <div class="third-party">
                    <a href="#" class="log-qq icon-qq-round"></a>
                    <a href="#" class="log-qq icon-weixin"></a>
                    <a href="#" class="log-qq icon-sina1"></a>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=basePath%>/resources/backLoginRe/js/jquery.js"></script>
    <script src="<%=basePath%>/resources/js/layer/layer.js"></script>
    <script src="<%=basePath%>/resources/backLoginRe/js/agree.js"></script>
    <script src="<%=basePath%>/resources/myJs/seller/register.js"></script>
</body>
</html>