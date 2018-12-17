/* 管理员信息 */
$.ajax({
	url:'/jsonAction/account_currentAdminMsg.action?v=' + new Date().getTime(),
	type:'GET', 		//GET
	async:true,    		//或false,是否异步
	timeout:5000,    	//超时时间
	dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	success:function(data,textStatus,jqXHR){
		if(data.statusCode == 200 && data.admin!=null) {
			var admin = data.admin;
			var msg = '<img src="/upload'+admin.adminImageURL+'" />'+'<span>'+admin.adminName+'</span>';
			$('#userMsg').html(msg);
		}else{
			location.href='/admin/login.html';
		}
	},
	error:function(xhr,textStatus){
		alert("请求失败，请稍候再试");
	}
}) 	
/* 注销登录 */
function signoutAlert() { 
	layer.confirm('确定要注销登录吗?', { icon: 3, title: '系统提示' }, function (index) {
		layer.close(index);
		window.location.href='/account_adminLoginOut.action?v=' + new Date().getTime();
    });
} 
/* TabBar按钮 */
layui.use('layer', function() {
	var $ = layui.jquery,
		layer = layui.layer;
	/* 收藏家主页 */
	$('#homePage').on('click', function() {
		window.open("http://www.chao80.com/");
    });
});