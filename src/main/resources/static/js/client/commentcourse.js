$(document).ready(function() {
	ajaxGetComment(1);
	function ajaxGetComment(page) {
		let searchParams = new URLSearchParams(window.location.search);
		var courseId = searchParams.get('courseId');
		
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/api/comment/course/id=" + courseId + "?page=" + page,
			success: function(result) {
				var divCMT = "";
				$.each(result.object, function(i, objres) {
					divCMT += '<tr>'
						+ '<td>'	
						+ '<div class = "row">'			
						+ '<div class="col-md-2 avatar" style="padding: 10px;">'
						+ '<img class="circle" src="/static/images/avatardefault.jpg" alt="" width="50%" height="100%">'
						+ '</div>'
						+ '<div class="col-md-10 box">'
						+ '<span id="name_member" class="name-right">' 
						+ '<b>' + objres.userName + '</b>'
						+ '<span class="date-left">'
						+ '<small name="commentDateCourse">' + objres.commentDateCourse + '</small>'
						+ '</span>'
						+ '</span>'
						+ '<p id="contentcourse name="contentCourse">' + objres.contentCmtCourse + '</p>'
						+ '</div>'
						+ '</div>'
						+ '<td>'
						+ '<tr>'
				});
				$('#lstcommentcourse').html(divCMT);
			},
			error: function(e) {
				alert("Error: ", e);
				console.log("Error", e);
			}
		});
	};
	
	var comment = {};
	$('#btnComment').click(function(){
		let searchParams = new URLSearchParams(window.location.search);
		comment.courseId = searchParams.get('courseId');
		comment.contentCmtCourse = $('#contentComment').val();
		comment.userName = $('#name_member').val();
		var commentObj = JSON.stringify(comment);
		$.ajax({
			url: "http://localhost:8080/api/comment/course/add-comment",
			method: 'POST',
			data: commentObj,
			contentType: 'application/json; charset = utf-8',
			success: function(){
				// alert('comment successfully');
				ajaxGetComment(1);
			},
			error:function(error){
				alert(error);
			}
		})

	});
	
	$(document).on('click', '.directpage', function(event) {
		var directId = $(this).attr('id');
		var fields = directId.split('.');
		var page = fields[1];
		ajaxGetForCauHoi(page);
	});
});