function save(){
	var html = testEditor.getPreviewedHTML();
	var md = testEditor.getMarkdown() ;
	var title = $('#title').val();
	var label = $('#label').val();
	if(checkNull(3,title)&&checkNull(3,md)&&checkMax(12,label)){
		$('#md-html').val(html);
		$('#form').submit();
	}else{
		alert("标题或者内容不能少于3个字符,标签不能多于12个字符")
	}
}
function push(aid){

	var html = testEditor.getPreviewedHTML();
	var md = testEditor.getMarkdown() ;
	var title = $('#title').val();
	var label = $('#label').val();
	if(checkNull(3,title)&&checkNull(3,md)&&checkMax(8,label)){
		$('#md-html').val(html);
		if(typeof(aid)!="undefined"){
			$('#form').attr('action','./push/'+aid)
		}else{
			$('#form').attr('action','./push')
		}
		
		$('#form').submit();
	}else{
		alert("标题或者内容不能少于3个字符,标签不能多于12个字符")
	}

}

function checkNull(minlength,value) {
	if(value.trim().length>minlength){
		return true ; 
	}else{
		return false ; 
	}
}
function checkMax(maxlength,value) {
	if(value.trim().length<maxlength){
		return true ; 
	}else{
		return false ; 
	}
}
