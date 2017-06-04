function delArt(aid) {
	$.post('./delete',{'aid':aid},function(data){
		if(data=='success'){
			$('#aid-'+aid).hide();
		}else{
			alert("删除失败")
		}
	})
} 