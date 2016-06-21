<#assign avatar=app_user.largeAvatar/>
<#if app_user.largeAvatar == ''>
    <#assign avatar='static/ace/dist/avatars/profile-pic.jpg'/>
</#if>

<div class="row">
    <div class="col-xs-12">
        <div id="user-profile-1" class="user-profile row">
            <div class="col-xs-12 col-sm-3 center">
                <div>
                    <span class="profile-picture">
                        <img src="${ctx}${avatar}"
                             class="editable img-responsive editable-click editable-empty"/>
                    </span>

                    <div class="space-4"></div>

                    <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                        <div class="inline position-relative">
                            <a href="#" class="user-title-label">
                                <i class="ace-icon fa fa-circle light-green"></i>
                                &nbsp;
                                <span class="white">${app_user.realname}</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-xs-12 col-sm-9">
                <div class="space-6"></div>

                <div class="alert alert-block alert-success">
                    <button type="button" class="close" data-dismiss="alert">
                        <i class="ace-icon fa fa-times"></i>
                    </button>

                    <i class="ace-icon fa fa-leaf green"></i>

                    <strong class="green">
                    <@c.substring str="${app_user.mood}" len=60 default="这孩子太懒了, 尽然没有留下心情..."/>
                    </strong>
                </div>

                <div class="left">
                    <span class="btn btn-app btn-sm btn-light no-hover">
                        <span class="line-height-1 bigger-170 blue"> 1411 </span>

                        <br>
                        <span class="line-height-1 smaller-90"> 访问总量 </span>
                    </span>

                    <span class="btn btn-app btn-sm btn-yellow no-hover">
                        <span class="line-height-1 bigger-170"> 32 </span>

                        <br>
                        <span class="line-height-1 smaller-90"> 关注我的 </span>
                    </span>

                    <span class="btn btn-app btn-sm btn-pink no-hover">
                        <span class="line-height-1 bigger-170"> 4 </span>

                        <br>
                        <span class="line-height-1 smaller-90"> 我关注的 </span>
                    </span>

                    <span class="btn btn-app btn-sm btn-grey no-hover">
                        <span class="line-height-1 bigger-170"> 23 </span>

                        <br>
                        <span class="line-height-1 smaller-90"> 今日访客 </span>
                    </span>

                    <span class="btn btn-app btn-sm btn-success no-hover">
                        <span class="line-height-1 bigger-170"> 7 </span>

                        <br>
                        <span class="line-height-1 smaller-90"> 相册 </span>
                    </span>

                    <span class="btn btn-app btn-sm btn-primary no-hover">
                        <span class="line-height-1 bigger-170"> 55 </span>

                        <br>
                        <span class="line-height-1 smaller-90"> 好友 </span>
                    </span>
                </div>

                <div class="space-10"></div>

                <div class="left">
                    <a href="#" class="btn btn-link">
                        <i class="ace-icon fa fa-plus-circle bigger-120 green"></i>
                        加为好友
                    </a>

                    <a href="#" class="btn btn-link">
                        <i class="ace-icon fa fa-heart bigger-120 red"></i>
                        添加关注
                    </a>

                    <a href="#" class="btn btn-link">
                        <i class="ace-icon fa fa-envelope bigger-120 pink"></i>
                        发送消息
                    </a>

                    <a href="#" class="btn btn-link">
                        <i class="ace-icon fa fa-globe bigger-120 blue"></i>
                        Ta的主页
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>