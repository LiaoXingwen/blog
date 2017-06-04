function changeStyle(bg,color) {
	$.get('./setter/changestyle',{
		bgcolor:bg,
		fontcolor:color
	},function(data){
		var json = eval('('+data+')')
		if (json.state==10000) {
			alert("设置成功")
		}else{
			alert("设置失败")
		}
	})
}