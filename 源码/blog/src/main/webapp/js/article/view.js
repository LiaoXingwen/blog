var path ; 
function setPath(path) {
	this.path = path ; 
}
function thumbsup() {
	$('#thumbsup').attr('disabled','disabled');
	$.post('./thumbsup',function(data){
		var dataObj=eval("("+data+")");
		if(dataObj.state==10000){
			var obj = eval("("+dataObj.message+")");
			changeView(obj);
		}else{
			window.location.href = path+"/login"
		}
		$('#thumbsup').removeAttr('disabled');
	})
}

function delthumbsup() {
	$('#thumbsup').attr('disabled','disabled');
	$.post('./delthumbs',function(data){
		var dataObj=eval("("+data+")");
		if(dataObj.state==10000){
			var obj = eval("("+dataObj.message+")");
			changeView(obj);
		}else{
			window.location.href =  path+"/login"
		}
		$('#thumbsup').removeAttr('disabled');
	})
}

function thumbsdown() {
	$('#thumbsdown').attr('disabled','disabled');
	$.post('./thumbsdown',function(data){
		var dataObj=eval("("+data+")");
		if(dataObj.state==10000){
			var obj = eval("("+dataObj.message+")");
			changeView(obj);
		}else{
			window.location.href = path+"/login"
		}
		$('#thumbsdown').removeAttr('disabled');
	})
}
function delthumbsdown() {	
	$('#thumbsdown').attr('disabled','disabled');
	$.post('./delthumbs',function(data){
		var dataObj=eval("("+data+")");
		if(dataObj.state==10000){
			var obj = eval("("+dataObj.message+")");
			changeView(obj);
		}else{
			alert(dataObj.message);
		}
		$('#thumbsdown').removeAttr('disabled');
	})}

/**
 * 根据返回json来改变view的状态
 * @param obj
 */
function changeView(obj) {
	if(obj.isup){
		$('#thumbsup').attr('onclick','delthumbsup()');
		$('#thumbsup').attr('class','btn btn-success');
	}else{
		$('#thumbsup').attr('onclick','thumbsup()');
		$('#thumbsup').attr('class','btn btn-default');
	}
	if(obj.isdown){
		$('#thumbsdown').attr('onclick','delthumbsdown()');
		$('#thumbsdown').attr('class','btn btn-warning');
	}else{
		$('#thumbsdown').attr('onclick','thumbsdown()');
		$('#thumbsdown').attr('class','btn btn-default');
	}

	$('#thumbsdownnum').text(obj.downcount);
	$('#thumbsupnum').text(obj.upcount);
}

function thumbs() {	
	$.post('./thumbs',function(data){
		var dataObj=eval("("+data+")");
		if(dataObj.state==10000){
			var obj = eval("("+dataObj.message+")");
			changeView(obj);
		}else{
			alert(dataObj.message);
		}
	})}


function submit(path){
	$('#aComment').ajaxSubmit({
		error: function(xhr) {
			console.log(xhr.statu);
			status('Error: ' + xhr.status);
		},
		success: function(response) {
			var json = eval('('+response+')')
			if (json.state==10000) {
				var obj = eval('('+json.message+')'); 
				$('.commentcontent').val('');
				$("html,body").animate({scrollTop: $('#'+jsonToView(obj,path)).offsetTop}, 500);
			}else{
				window.location.href = path+"/login"
			}
		}
	});
	return false;
}

function getCommentList(pager,type,oid,path) {
	$.post('../comment',{
		pager:pager,
		type:type,
		oid:oid
	},function(data){
		var json = eval('('+data+')');
		if (json.state==10000) {
			var obj = eval('('+json.message+')');
			if (obj.list.length==0||obj.maxPager<pager) {
				$('.showcomment').attr('disabled','disabled');
			}else{
				for (var int = 0; int < obj.list.length; int++) {
					jsonToView(obj.list[int],path);
				}
				$('.showcomment').attr('onclick','getCommentList('+(pager+1)+','+type+','+oid+')');
				if (obj.maxPager==pager){
					$('.showcomment').hide();
					footer();
				}
			}
		}else{
			window.location.href = path+"/login"
		}
	});
}
function jsonToView(json,path) {
	var panel = $('.hr') ; //评论区容器
	panel.before('<div class="row commentmodel commentdiv_'+json.cid+'"> <img src="'+json.headURl+'" class="img-circle"><a style="color: black;" href="'+path+'/user/show/'+json.uid+'">'+json.username+'</a> : <span class="content">'+json.content+'</span> ---- <span class="date">'+json.date+'</span><hr></div>');
	return 'commentdiv_'+json.cid ; 
}
function footer() {
	var panel = $('.hr') ; //评论区容器
	panel.after('<p align="center">------- 没有更多数据了 -------');
}
