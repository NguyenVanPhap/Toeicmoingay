$(document).ready(function() {
	ajaxGetComment(1);
	function ajaxGetComment(page) {
		let searchParams = new URLSearchParams(window.location.search);
		var vocabId = searchParams.get('idVocab');
		
		$.ajax({
			type: "GET",
			url: "/api/comment/vocab/id=" + vocabId + "?page=" + page,
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
						+ '<small name="commentDateVocab">' + objres.commentDateVocab + '</small>'
						+ '</span>'
						+ '</span>'
						+ '<p id="contentvocab" name="contentVocabulary">' + objres.contentVocabulary + '</p>'
						+ '</div>'
						+ '</div>'
						+ '<td>'
						+ '<tr>'
				});
				$('#lstcommentvocab').html(divCMT);
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
		comment.vocabularyId = searchParams.get('idVocab');
		comment.contentVocabulary = $('#contentComment').val();
		comment.userName = $('#name_member').val();
		var commentObj = JSON.stringify(comment);
		$.ajax({
			url: "/api/comment/vocab/add-comment",
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