<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>${appName} - 首页</title>
    <link rel="icon" type="image/ico" href="${ctx}static/app/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <link rel="stylesheet" href="${ctx}static/libs/jquery/jquery.fullPage.css"/>
    <link rel="stylesheet" href="${ctx}static/app/css/index.css"/>
</head>

<@current_user>
<body>

<div class="section">
    <div class="page">
        <a href="${ctx}user?id=${app_author.id}" class="logo">
            <#if app_author.largeAvatar!="">
                <img src="${ctx}${app_author.largeAvatar}" alt="${app_author.realname}"/>
            <#else>
                <img src="${ctx}static/ace/dist/avatars/profile-pic.jpg" alt="${app_author.realname}"/>
            </#if>
        </a>
        <a href="${ctx}user?id=${app_author.id}" class="go">
            我的主页
        </a>
    </div>
</div>

<div class="section">
    <div class="page">
        <a href="${ctx}category/list?code=picture" class="go">
            花样相册
        </a>
    </div>
</div>

<div class="section">
    <div class="page">
        <a href="${ctx}category/list?code=music" class="go">
            音乐盛会
        </a>
    </div>
</div>

<div class="section">
    <div class="page">
        <a href="${ctx}category/list?code=video" class="go">
            视频专区
        </a>
    </div>
</div>

<div class="section">
    <div class="page">
        <a href="${ctx}category/list?code=download" class="go">
            资源下载
        </a>
    </div>
</div>

<script src="${ctx}static/ace/dist/js/jquery.min.js"></script>
<script src="${ctx}static/libs/jquery/jquery-ui-1.10.3.min.js"></script>
<script src="${ctx}static/libs/jquery/jquery.fullPage.min.js"></script>
<script src="${ctx}static/app/js/web/index/index.js"></script>
</body>

</@current_user>
</html>

