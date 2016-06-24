<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>${appName} - ${title}</title>
    <link rel="icon" type="image/ico" href="${ctx}static/app/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${ctx}static/ace/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}static/ace/dist/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${ctx}static/ace/dist/css/jquery.gritter.min.css"/>

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="${ctx}static/ace/dist/css/ace-fonts.min.css"/>

    <!-- skins -->
    <link rel="stylesheet" href="${ctx}static/ace/dist/css/ace-skins.min.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="${ctx}static/ace/dist/css/ace.min.css" class="ace-main-stylesheet"
          id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}static/ace/dist/css/ace-part2.min.css" class="ace-main-stylesheet"/>
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}static/ace/dist/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->
    <link rel="stylesheet" href="${ctx}static/app/css/app.css"/>

    <!-- ace settings handler -->
    <script src="${ctx}static/ace/dist/js/ace-extra.min.js"></script>
    <script src="${ctx}static/ace/dist/js/jquery.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${ctx}static/ace/dist/js/html5shiv.min.js"></script>
    <script src="${ctx}static/ace/dist/js/respond.min.js"></script>
    <![endif]-->

<@block name="app-style"/>
</head>
<@app_user>
<body class="${bodyClass!'skin-3'}">

<script>
    var skin_class = ace.cookie.get("skin");
    if (skin_class) {
        $("body").removeClass('no-skin skin-1 skin-2 skin-3');
        $("body").addClass(skin_class);
    }
</script>

    <#include "include/navbar.ftl">

<div id="main-container" class="main-container">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="page-content">
                <#include "include/settings.ftl">
                <@block name="app-content"/>
            </div>
        </div>
    </div>
</div>

    <#include "include/footer.ftl">

    <#include "include/modal.ftl">

<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>

<script>var ctx = '${ctx}';</script>
<script src="${ctx}static/ace/dist/js/bootstrap.min.js"></script>
<script src="${ctx}static/libs/jquery/jquery.bootstrap.min.js"></script>
<script src="${ctx}static/ace/dist/js/jquery.gritter.min.js"></script>
<script src="${ctx}static/libs/jquery/jquery.form.min.js"></script>
<script src="${ctx}static/libs/jquery/jquery.validate.min.js"></script>
<script src="${ctx}static/libs/jquery/jquery.validate.extends.js"></script>
<script src="${ctx}static/ace/dist/js/ace-extra.min.js"></script>
<script src="${ctx}static/ace/dist/js/ace-elements.min.js"></script>
<script src="${ctx}static/ace/dist/js/ace.min.js"></script>
<script src="${ctx}static/ace/dist/js/ace.skin.js"></script>
<script src="${ctx}static/app/js/config.js"></script>
<script src="${ctx}static/app/js/app.js"></script>
    <@block name="app-script"/>
</@app_user>
</body>
</html>
