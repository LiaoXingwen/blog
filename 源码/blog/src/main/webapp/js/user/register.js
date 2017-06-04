
//验证是否符合邮箱
function isEmail(str){
	var reg=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	return reg.test(str);
}

function checkEmail(){
	var email = $('#email');
	var val = email.val();
	if(isEmail(val)){
		$('#emaildiv').attr('class','form-group has-feedback has-success')
		checkConfirmPassword();
		$.get('./user/checkEmail', {email: email.val()}, function(data, status) {
				if(data=="notExist"){
					email.attr('data-content','输入的邮箱好像有效');
					email.click();
					checkConfirmPassword();
				}else{
					$('#emaildiv').attr('class','form-group has-feedback has-error')
					email.attr('data-content','输入的邮箱已经被人注册了,您可能要  <a href="./login">登陆</a>  或  <a href="./login">忘记密码？</a>');
					email.focus();
					email.click();
					$('#submit').attr('disabled','disabled')
				}
		});
	}else{
		$('#emaildiv').attr('class','form-group has-feedback has-error')
		email.attr('data-content','输入的邮箱无效');
		email.focus();
		email.click();
		$('#submit').attr('disabled','disabled')
	}
}


function submit(){
	$('#submit').attr('disabled','disabled')
}

//显示密码
function showPassword() {
	var view = $('#showView');
	if(view.attr('class')=='form-control-feedback glyphicon glyphicon-eye-close'){
		view.attr('class','form-control-feedback glyphicon glyphicon-eye-open');
		$('#password').attr('type','password');
	}else{
		view.attr('class','form-control-feedback glyphicon glyphicon-eye-close');
		$('#password').attr('type','text');
	}
}

function checkPassword() { 
	var password = $('#password') 
	var val = password.val() ; 
	var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g"); 
	var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g"); 
	var enoughRegex = new RegExp("(?=.{6,}).*", "g"); 

	if (false == enoughRegex.test(val)) { 
		password.attr('data-content','密码不符合要求，不通过。密码至少需要六位以上包含字母 或 特殊符号 或 数字 其中任意两项');
		$('#passworddiv').attr('class','form-group has-feedback has-error')
		$('#submit').attr('disabled','disabled')
		//如果密码为6为及以下
		return false ; 
	} 
	else if (strongRegex.test(val)) { 
		//密码为八位及以上并且字母数字特殊字符三项都包括,强度最强 
		password.attr('data-content','密码本地认证通过');
		$('#passworddiv').attr('class','form-group has-feedback has-success')
		return true ; 
	} 
	else if (mediumRegex.test(val)) { 
		//密码为七位及以上并且字母、数字、特殊字符三项中有两项，强度是中等 
		password.attr('data-content','密码本地认证通过');
		$('#passworddiv').attr('class','form-group has-feedback has-success');
		return true ; 
	} 
	else { 
		//如果密码为6为及以下，就算字母、数字、特殊字符三项都包括，强度也是弱的 
		password.attr('data-content','密码不符合要求，不通过。密码至少需要六位以上包含字母 或 特殊符号 或 数字 其中任意两项');
		$('#passworddiv').attr('class','form-group has-feedback has-error')
		$('#submit').attr('disabled','disabled')
		
		return false ; 
	} 
}


function checkConfirmPassword() {
	var confirm = $("#confirmPassword")
	var password = $('#password') 
	if(checkPassword()){
	
	if(confirm.val()==password.val()){
		$('#confirmDiv').attr('class','form-group has-feedback has-success')
		$('#confirmView').attr('class','form-control-feedback glyphicon glyphicon-ok')
		$('#submit').removeAttr("disabled"); 
		return true;
		
	}else{
		$('#confirmDiv').attr('class','form-group has-feedback has-error')
		$('#confirmView').attr('class','form-control-feedback glyphicon glyphicon-remove')
		$('#submit').attr('disabled','disabled')
		return false;
	}
	}else{
		$('#confirmDiv').attr('class','form-group has-feedback has-error')
		$('#confirmView').attr('class','form-control-feedback glyphicon glyphicon-remove')
		$('#submit').attr('disabled','disabled')
		return false;
	}
}


$(document).ready(function(){

	$("#email").focus(function(){
		$("#email").css("background-color","#ffffCC");
	});

	$("#email").blur(function(){
		$("#email").css("background-color","#ffffff");
	});

	$("#password").focus(function(){
		if($('#emaildiv').attr('class')!='form-group has-feedback has-success'){
			$("#email").focus();
			
		}else{
			$("#password").css("background-color","#ffffCC");
			$('#email').popover('hide')
		}

	});

	$("#password").blur(function(){
		$("#password").css("background-color","#ffffff");
	});

	$("#confirmPassword").focus(function(){
		$('#email').popover('hide')
		$('#password').popover('hide')
		if($('#emaildiv').attr('class')!='form-group has-feedback has-success'){
			$("#email").focus();
		}else{
			if($('#passworddiv').attr('class')!='form-group has-feedback has-success'){
				$("#password").focus();
			}else{
				$("#password").popover('hide')
			}
		}
	});

	$("#confirmPassword").blur(function(){
		$("#confirmPassword").css("background-color","#ffffff");
	});
	
	//绑定输入事件
	$('#email').bind('input', function () {
		checkEmail()
	})
	
	$('#password').bind('input', function () {
		checkPassword()
		checkConfirmPassword()
	})
	$('#confirmPassword').bind('input', function () {
		checkConfirmPassword()
	})

}); 


$(function () {
	$('#email').popover()
	$('#password').popover()
	
	
});

