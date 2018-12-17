/**
 * Created by wenbin.lu on 2017/10/12.
 */
Chao.museum = {
    init: function () {
        $('.main_nav a').eq(1).addClass('cur');
        var that=this;
        $.ajax({
            url:Chao.APP.DATA+'release_queryCategaryList.action',
            success:function (rs) {
                var i=0,d=rs.categaryList,len=d.length,h=['<a data_cateSequence="0">全部</a>'];
                for(i;i<len;i++){
                    h.push('<a data_cateSequence="'+d[i].cateSequence+'">'+d[i].cateName+'</a>');
                }
                $('#aside_nav_con').html(h.join(''));
                $('#aside_nav_con a').eq(0).addClass('cur');
                that.cateSequence=0;
                // that.cateSequence=d[0].cateSequence;
                $('#aside_nav_con a').click(function () {
                    $(this).siblings().removeClass('cur').end().addClass('cur');
                    that.cateSequence=$(this).attr('data_cateSequence');
                    that.reset();
                });
                $('#aside_nav a').click(function(){
                    var category=parseInt($(this).attr('data'));
                    if(that.category!=category){
                        that.category=category;
                        that.setNav();
                        try{
                            localStorage.setItem('museum_category',that.category);
                        }catch (e){}
                    }
                });
                var category;
                try{
                    category=localStorage.getItem('museum_category');
                }catch (e){}
                if(category=="1"){
                    that.category=1;
                }else{
                    that.category=2;
                }
                that.setNav();
                $(window).scroll(function(){
                    var scrollTop = $(this).scrollTop(),
                        totalHeight = $(document).height(),
                        windowHeight = $(this).height();
                    if( totalHeight-(scrollTop + windowHeight) <100 ){
                        Chao.museum.load();
                    }
                });
            },
            error:function(r){Chao.err.ajax(r);}
        });
    },
    setNav:function () {
        $('#aside_nav a').removeClass('cur');
        if(this.category==2){
            $('#museum-product').addClass('cur');
        }else{
            $('#museum-user').addClass('cur');
        }
        this.reset();
    },
    reset:function () {
        this.lock=false;
        this.end=false;
        this.page=1;
        //clear data
        var className=this.category==1?"collectors":"masonry_box";
        document.getElementById('masonry').className=className;
        document.getElementById('masonry').innerHTML="";
        $('#masonry').attr({"style":""});
        document.title=this.category==1?"藏家":"藏品";
        this.load();
    },
    cateSequence:0,
    category:1,
    lock:false,
    locked:function(){
        this.lock=true;
        $('#lock').show();
    },
    unlocked:function(){
        $('#lock').hide();
        this.lock=false;
    },
    end:false,
    ended:function () {
        this.end=true;
        $('#end').show();
    },
    start:function () {
        this.end=false;
        $('#end').hide();
    },
    restart:function () {
      this.page=1;
      this.start();
    },
    page:1,
    load:function () {
        if(this.end){
            return false;
        }
        if(this.lock){
            return false;
        }
        this.locked();
        var that=this;
        if(this.category==1){
            //cangjia
            var d={
                pageNo:this.page,
                pageSize:12
            };
            if(this.cateSequence!="0"){
                d.cateSequence=this.cateSequence;
            }
            $.ajax({
                url:Chao.APP.DATA+'account_queryCateUserDataList.action',
                data:d,
                beforeSend:function(){},
                success:function(r){
                    that.page++;
                    if(r.statusCode=="200"){
                        var i=0,d=r.userList,len=d.length,h=[],j=0,jlen=0,e=[];
                        for(i;i<len;i++){
                        	console.log(d[i].userSequence);
                            h.push('<div class="item">');
                            h.push('<div class="user-info">');
                            h.push('<div class="head"><a href="collectors.html?id='+d[i].userSequence+'"><img src="'+Chao.util.imgFormat(d[i].userImageURL)+'" alt=""></a></div>');
                            h.push('<div class="name">'+d[i].userName+'</div>');
                            h.push('<div class="summary">粉丝 <span class="fans">'+d[i].fansCount+'</span>|关注 <span class="focus">'+d[i].attentCount+'</span>| 赞 <span class="fave">'+d[i].likedCount+'</span></div>');
                            h.push('<div class="tags">');
                            if(d[i].cateMapList){
                                h.push('<a>'+Chao.util.cateMapList(d[i].cateMapList,2).join('</a><a>')+'</a>');
                            }else{
                            }
                            h.push('</div>');
                            if(d[i].hasAttention && d[i].hasAttention!="false"){
                                h.push('<a class="btn" onclick="Chao.museum.fave_remove(this)" title="取消关注" data_userSequence="'+d[i].userSequence+'">已关注</a>');
                            }else{
                                h.push('<a class="btn" onclick="Chao.museum.fave_add(this)" title="点击关注" data_userSequence="'+d[i].userSequence+'">关注</a>');
                            }
                            h.push('</div>');
                            h.push('<div class="imgs">');
                            j=0;
                            e=d[i].colList;
                            jlen=e.length>6?6:e.length;
                            for(j;j<jlen;j++){
                                try{
                                    h.push('<div class="img img'+(j+1)+'"><a href="detail.html?id='+e[j].colSequence+'" target="_blank"><img src="'+Chao.util.imgFormat(e[j].colThumb)+'"/></a></div>');
                                }catch(e){}
                            }
                            h.push('</div>');
                            h.push('</div>');
                        }
                        if(r.pageInfo.currentPageNo=="1"){
                            $('#masonry').html(h.join(''));
                        }else{
                            $('#masonry').append(h.join(''));
                        }
                        document.getElementById('masonry').className='collectors';
                        that.unlocked();
                        if(len<12){
                            that.ended();
                        }
                    }
                },
                error:function(r){Chao.err.ajax(r);}
            });
        }else if(this.category==2){
            // 藏品
            var d={
                pageNo:this.page,pageSize:12
            };
            if(this.cateSequence!="0"){
                d.cateSequence=this.cateSequence;
            }
            $.ajax({
                url:Chao.APP.DATA+'collection_queryCateCollectionDataList.action',
                data:d,
                beforeSend:function(){},
                success:function(r) {
                    that.page++;
                    if (r.statusCode == "200") {
                        var i=0,d=r.collectionList,len=d.length,h=[],X,Y;
                        if(r.pageInfo.currentPageNo=="1"){
                            h.push('<div id="masonry_box">');
                        }
                        for(i;i<len;i++){
                            X=d[i].imgWidth||2;
                            Y=d[i].imgHeigh||2;
                            h.push('<div class="item item_'+X+'_'+Y+'">');
                            h.push('<div class="image"><img onclick="Chao.museum.detail('+d[i].colSequence+')" src="'+Chao.util.imgFormat(d[i].colThumb)+'"/></div>');
                            h.push('<div class="summary">');
                            h.push('<h2>'+d[i].colTitle+'</h2>');
                            // h.push('<h3>'+d[i].colSubTitle+'</h3>');
                            h.push('<h3>by '+d[i].userName+'</h3>');
                            h.push('<p>'+d[i].colContent+'</p>');
                            if(isNaN(d[i].colPrice)){
                                h.push('<div>市场价：<b>'+d[i].colPrice+'</b></div>');
                            }else{
                                h.push('<div>市场价：<b>'+d[i].colPrice+'RMB</b></div>');
                            }
                            h.push('<section>');
                            h.push('<a class="more" onclick="Chao.museum.detail('+d[i].colSequence+')" >more</a>');
                            if(d[i].hasAttention && d[i].hasAttention!="false"){
                                h.push('<b class="fave cur" onclick="Chao.museum.attention_remove(this)" data_colSequence="'+d[i].colSequence+'">'+d[i].colLikeNum+'</b>');
                            }else{
                                h.push('<b class="fave" onclick="Chao.museum.attention_add(this)" data_colSequence="'+d[i].colSequence+'">'+d[i].colLikeNum+'</b>');
                            }
                            h.push('</section>');
                            h.push('</div>');
                            h.push('</div>');
                        }
                        if(r.pageInfo.currentPageNo=="1"){
                            h.push('</div>');
                        }
                        if(r.pageInfo.currentPageNo=="1"){
                            $('#masonry').attr({"style":""}).html(h.join(''));
                            document.getElementById('masonry').className='masonry_box';
                            $('#masonry_box').masonry({
                                itemSelector:".item",
                                gutter:0,
                                columnWidth:240
                            });
                        }else{
                            $('#masonry_box').append(h.join(''));
                            document.getElementById('masonry').className='masonry_box';
                            $('#masonry_box').masonry('reload');
                        }

                        that.unlocked();
                        if(len<12){
                            that.ended();
                        }
                    }
                },
                error:function(r){Chao.err.ajax(r);}
            });
        }
    },
    attention_add:function (e) {
        // 关注藏品
        var colSequence=$(e).attr('data_colSequence');
        $.ajax({
            url:Chao.APP.DATA+'attention_addCollectionAttention.action',
            data:{colSequence:colSequence},
            beforeSend:function(){},
            success:function(r){
                if(r.statusCode=="200"){
                    toastr.success('您已成功收藏');
                    var n=parseInt($(e).html());
                    $(e).html(n+1).addClass('cur').attr({"onclick":"Chao.museum.attention_remove(this)","title":"取消收藏"});
                    Chao.user.checkStatus();
                }
            },
            error:function(r){Chao.err.ajax(r);}
        });
    },
    attention_remove:function (e) {
        //2-取消关注藏品
        var colSequence=$(e).attr('data_colSequence');
        $.ajax({
            url:Chao.APP.DATA+'attention_removeCollectionAttention.action',
            data:{colSequence:colSequence},
            beforeSend:function(){},
            success:function(r){
                if(r.statusCode=="200"){
                    toastr.success('您已取消收藏');
                    var n=parseInt($(e).html());
                    n=n-1>0?(n-1):0;
                    $(e).html(n).removeClass('cur').attr({"onclick":"Chao.museum.attention_add(this)","title":"点击收藏"});
                    Chao.user.checkStatus();
                }
            },
            error:function(r){Chao.err.ajax(r);}
        });
    },
    fave_add:function (e) {
        //关注用户
        var userSequence=$(e).attr('data_userSequence');
        $.ajax({
            url:Chao.APP.DATA+'attention_addUserAttention.action',
            data:{userSequence:userSequence},
            beforeSend:function(){},
            success:function(r){
                if(r.statusCode=="200"){
                    toastr.success('您已成功关注');
                    $(e).html('已关注').attr({"onclick":"Chao.museum.fave_remove(this)","title":"取消关注"});
                    var o=$(e).parent().find('.fans');
                    var n=parseInt(o.html());
                    o.html(n+1);
                    Chao.user.checkStatus();
                }
            },
            error:function(r){Chao.err.ajax(r);}
        });
    },
    fave_remove:function (e) {
        // 取消关注用户
        var userSequence=$(e).attr('data_userSequence');
        $.ajax({
            url:Chao.APP.DATA+'attention_removeUserAttention.action',
            data:{userSequence:userSequence},
            beforeSend:function(){},
            success:function(r){
                if(r.statusCode=="200"){
                    toastr.success('您已取消关注');
                    $(e).html('关注').attr({"onclick":"Chao.museum.fave_add(this)","title":"点击关注"});
                    var o=$(e).parent().find('.fans');
                    var n=parseInt(o.html());
                    n=n-1>0?(n-1):0;
                    o.html(n);
                    Chao.user.checkStatus();
                }
            },
            error:function(r){Chao.err.ajax(r);}
        });
    },
    tmp_colSequence:0,
    detail:function(colSequence){
        if(this.tmp_colSequence==colSequence){
            $('#museum_preview').show();
        }else{
            var that=this;
            $.ajax({
                url:Chao.APP.DATA+'collection_queryCollectionMsg.action',
                data:{colSequence:colSequence},
                beforeSend:function(){
                    Chao.dialog.loading();
                },
                success:function(r){
                    Chao.dialog.unloading();
                    if(r.statusCode=="200"){
                        that.tmp_colSequence=colSequence;
                        $('#museum_preview').html('');
                        var d=r.collection,i=0,len=d.imageList.length,j=0,jlen=0,h=[],className='';
                        if(len>0){
                            h.push('<div class="image" id="detail_img_show"><img src="'+Chao.util.imgFormat(d.imageList[0].imgURL)+'" ></div>');
                        }else{
                            h.push('<div class="image"></div>');
                        }
                        h.push('<div class="content">');
                        h.push('<div class="user"><a href="collectors.html?id='+d.user.userSequence+'">');
                        h.push('<img src="'+Chao.util.imgFormat(d.user.userImageURL)+'" alt=""/>');
                        h.push('<span>'+d.user.userName+'</span></a>');
                        h.push('</div>');
                        h.push('<div class="time">'+d.colDate+'</div>');
                        h.push('<div class="name">'+d.colTitle+'</div>');
                        h.push('<div class="subtitle">'+d.colSubTitle+'</div>');
                        if(d.hasAttention && d.hasAttention!="false"){
                            h.push('<div class="fave cur" onclick="Chao.museum.attention_remove(this)" data_colSequence="'+d.colSequence+'">'+d.colLikeNum+'</div>');
                        }else{
                            h.push('<div class="fave" onclick="Chao.museum.attention_add(this)" data_colSequence="'+d.colSequence+'">'+d.colLikeNum+'</div>');
                        }
                        h.push('<div class="section slimScroll">'+d.colContent+'</div>');
                        if(isNaN(d.colPrice)){
                            h.push('<div class="price">市场价：<b>'+d.colPrice+'</b></div>');
                        }else{
                            h.push('<div class="price">市场价：<b>'+d.colPrice+'RMB</b></div>');
                        }
                        h.push('<a href="detail.html?id='+d.colSequence+'" target="_blank" class="view">查看全文</a>');
                        j=0;
                        jlen=d.imageList.length;
                        h.push('<div class="imgs">');
                        if(jlen>3){
                            h.push('<a class="to_left" onclick="Chao.museum.l()"></a>');
                            h.push('<a class="to_right" onclick="Chao.museum.r()"></a>');
                        }
                        h.push('<div class="img_content"><div id="imgs_a" style="width:'+(135*jlen)+'px">');
                        for(j;j<jlen;j++){
                            if(j==0){
                                className='class="cur"';
                            }else{
                                className='';
                            }
                            h.push('<div '+className+'><img data_img="'+Chao.util.imgFormat(d.imageList[j].imgURL)+'" src="'+Chao.util.imgFormat_min(d.imageList[j].imgURL)+'" onclick="Chao.museum.detail_img(this)" /></div>');
                        }
                        h.push('</div></div></div>');
                        h.push('</div>');
                        h.push('<i class="close" onclick="$(this).parent().hide()"></i>');
                        $('#museum_preview').html(h.join('')).show();
                        $('#museum_preview .slimScroll').slimScroll({
                            alwaysVisible: true,
                            railVisible: true,
                            height:$('#museum_preview .slimScroll').height()
                        });
                    }
                },
                error:function(r){
                    Chao.dialog.unloading();
                    Chao.err.ajax(r);
                }
            });
        }
    },
    detail_img:function (e) {
        var src=$(e).attr('data_img');
        $(e).parent().siblings().removeClass('cur').end().addClass('cur');
        $('#detail_img_show').html('<img src="'+src+'"/>');
    },
    l:function () {
        $('#imgs_a').stop().animate({"left":0});
    },r:function () {
        $('#imgs_a').stop().animate({"left":-($('#imgs_a').width()-480)});
    }
};
Chao.museum.init();

