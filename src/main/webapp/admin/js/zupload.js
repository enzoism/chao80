var WY={
    upload:{
      __PUBLIC__ : '/img/',
      init:function(url,img){
        this.url=url;
        if(img){
          this.__PUBLIC__=img;
        }
        var s=$('#WY_L_image').val();
        if(!s){return;}
        s=s.split(',');
        var h=[],i=0;
        for(i;i<s.length;i++){
          h.push('<div class="images_sort"><img src="'+this.__PUBLIC__+s[i]+'"><span onclick="WY.upload.remove_img(this)" class="glyphicon glyphicon-remove"></span></div>');
        }
        $('#WY_files_show').html(h.join(''));
      },
      upload_pool:{},
      handleFiles:function(){
        var files = this.files,
          arrFiles = [],
          i=0,
          len=files.length,
          file;
        for (i;i<len;i++){
          file = files[i];
          if (file.type.indexOf("image") == 0) {
            if (file.size >= 5120000) {
              alert('您这张"'+ file.name +'"图片大小过大，应小于5M');
            } else {
              arrFiles.push(file);  
            }     
          } else {
            alert('文件"' + file.name + '"不是图片。');  
          }
        }
        i=0;
        len=arrFiles.length;
        var h=[];
        for(i;i<len;i++){
          h.push('<div id="WY_file_upload_progress_box_'+i+'" class="upload_progress "><div class="row">');
          h.push('<div class="col-md-8">'+arrFiles[i].name+'</div>');
          h.push('<div class="col-md-4 text-align-right"><a onclick="WY.upload.remove('+i+')">删除</a></div>');
          h.push('</div><progress id="WY_file_upload_progress_'+i+'" value="" max="'+arrFiles[i].size+'"></progress></div>');
        }
        $('#WY_files_progress').html(h.join(''));
        WY.upload.upload_pool={
          files:arrFiles,
          count:len,
          tmp:0
        };
      },
      funUploadFile:function (fileFilter) {
        for (var i = 0, file; file = fileFilter[i]; i++) {
          this.ajaxUpload(file,i);
        }
      },
      ajaxUpload:function(file,i){
        var that=this;
        var xhr, formData;
        if(file.active){
          return ;
        }
        file.active = true;
        xhr = new window.XMLHttpRequest();
        formData = new window.FormData();
        xhr.open('POST', this.url);
        xhr.upload.onprogress = function(event) {
          if (!event.lengthComputable) {
            return;
          }
          $('#WY_file_upload_progress_'+i).val(event.loaded);
        };
        xhr.upload.onerror=function(e){
          //console.log("onerror:"+e);
          that.upload_pool.count--;
        };
        xhr.onload = function () {
            that.upload_pool.tmp++;
            var r=eval("("+xhr.responseText+")");
            if(r.result){
              var val=$('#WY_L_image').val();
              if(val){
                val=val.split(',');
              }else{
                val=[];
              }
              val.push(r.result[0].uploadURL);
              $('#WY_L_image').val(val.join(','));
              var h='<div class="images_sort">'+
                  '<img src="'+that.__PUBLIC__+r.result[0].uploadURL+'" >'+
                  '<span onclick="WY.upload.remove_img(this)" class="glyphicon glyphicon-remove"></span>'+
              '</div>'
              $('#WY_files_show').append(h);
            }
            $('#WY_file_upload_progress_box_'+i).remove();
            if(that.upload_pool.tmp==that.upload_pool.count){
              $('#WY_files').val('');
              $('#WY_uploading').hide();
            }
        };
        formData.append("upload", file, file.name);
        xhr.send(formData);
      },
      remove:function(i){
        $('#WY_file_upload_progress_box_'+i).remove();
        this.upload_pool.files.splice(i,1);
        this.upload_pool.count--;
      },
      submit:function(){
        this.funUploadFile(this.upload_pool.files);
      },
      remove_img:function(x){
        var i=$(x).parent().index(),val=$('#WY_L_image').val().split(',');
        val.splice(i,1);
        $('#WY_L_image').val(val.join(','));
        $(x).parent().remove();
      }
    }
  };