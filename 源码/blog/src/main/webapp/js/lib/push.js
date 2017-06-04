function selectObjectChange() {
	var value = $("#object").val();
	if(value=="所有人"){
		$('#numdiv').hide(200);
	}else{
		$('#numdiv').show(400);
	}
}

function selectTypeChange() {
	var value = $("#type").val();
	if(value.indexOf("不限时")>-1){
		$('#timediv').hide(200);
	}else{
		$('#timediv').show(400);
	}
}