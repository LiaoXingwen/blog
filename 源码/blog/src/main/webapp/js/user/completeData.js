$(function () {
	$('#username').popover()
	$('#des').popover()


});
//头像上传
function upload() {
	$('#uploadForm').ajaxSubmit({
		error: function(xhr) {
			console.log(xhr.statu);
			status('Error: ' + xhr.status);
		},

		success: function(response) {
			var json = eval('('+response+')')
			if (json.state==10000) {
				$('#headimg').attr('src',json.message);
				$('#url').val(json.message);
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


















//去除html标签：
function del_html_tags(str)
{
	var words = '';
	words = str.replace(/<[^>]+>/g,"");
	return words;
}

//去除空格：
function Trim(str,is_global)
{
	var result;
	result = str.replace(/(^\s+)|(\s+$)/g,"");
	if(is_global.toLowerCase()=="g")
		result = result.replace(/\s/g,"");
	return result;
} 


function get_length(str)
{
	var char_length = 0;
	for (var i = 0; i < str.length; i++){
		var son_char = str.charAt(i);
		//如果是汉字，长度大于2，其他任何字符（包括￥等特殊字符，长度均为1）另外：根据需求规则，限制n个字，一个字=2个字符
		encodeURI(son_char).length > 2 ? char_length += 1 : char_length += 0.5;
	}
	return char_length; 
}


function checkUserName(){
	var username = $('#username');
	var val = username.val();
	if(del_html_tags(val).length>4){
		$.post('./checkUsername', {username: username.val()}, function(data, status) {
			if(data=="notExist"){
				$('#namediv').attr('class','form-group has-feedback has-success')
				username.attr('data-content','输入的昵称有效');
				username.click();
				$('#submit').removeAttr("disabled"); 
			}else{
				$('#namediv').attr('class','form-group has-feedback has-error')
				username.attr('data-content','输入的昵称已经被人注册了,换一个试试');
				username.focus();
				username.click();
				$('#submit').attr('disabled','disabled')
			}
		});
	}else{
		$('#namediv').attr('class','form-group has-feedback has-error')
		username.attr('data-content','输入的昵称无效');
		username.focus();
		username.click();
		$('#submit').attr('disabled','disabled')
	}
}

function show() {
	$("#add").modal("show");
}

function  changeChecked() {
	var sex = '男';
	if(!$('#man').is(':checked')){
		sex = '女'
	}

	$('#sex').val=sex;

}

