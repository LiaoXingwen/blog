function tostar() {
	star.animate(star.value()+1);
	$('#starbtn').attr('class','btn btn-warning btn-xs');

}
function delstar() {
	star.animate(star.value()-1);
	$('#starbtn').attr('class','btn btn-default btn-xs');
}


function dostar(path,id) {
	var starbtn = $('#starbtn');
//	已经收藏
	if(starbtn.attr('class')=='btn btn-warning btn-xs'){
		$.get(path+'/follow/cancel/'+id , function (data) {
			var json = eval('('+data+')')
			if (json.state==10000) {
				delstar()
			}else{
				alert(json.message);
			}
		})
	}else{
		$.get(path+'/follow/'+id , function (data) {
			var json = eval('('+data+')')
			if (json.state==10000) {
				tostar() 
			}else{
				alert(json.message);
			}
		})
	}
}

function toLogin(path) {
	window.location.href=path+"/login"
}

//头像上传
function upload(url) {
	$('#uploadForm').ajaxSubmit({
		error: function(xhr) {
			console.log(xhr.statu);
			status('Error: ' + xhr.status);
			alert("连接异常")
		},

		success: function(response) {
			var json = eval('('+response+')')
			if (json.state==10000) {
				$('#headimg').attr('src',json.message);

				$.get(url+'?headurl='+json.message,function(date,state){
					if(state='success'){
						var obj = eval('('+response+')');
						if (json.state==10000) {

						}else{

						}
					}else{
						alert("连接异常")
					}

				})
			}else{
				alert("上传失败:"+json.message);
			}

		}
	});
	return false;
}

function uploadHead() {
	$('.file').click();
}