function search() {
	window.location.href = './index?type=viewCount&search='+$('.search').val();
}
function hidetip() {
	$('#tip').hide('100');
}
function showtip() {
	if($('.search').val().length==0)
	$('#tip').show('100');
}
window.onload = function() {
	if($('.search').val().length>0)
	$('#tip').hide();
}