$(function() {
	$('#postForm').submit(function(event) {
		if ($('#titleInput').val() == "") {
			alert("Please enter title.");
			$('#titleInput').addClass('is-invalid');
			return false;
		}
		
		if ($('#textInput').val() == "") {
			alert("Please enter text.");
			$('#textInput').addClass('is-invalid');
			return false;
		}
	});
});

$(function() {
	$('#titleInput').on('input', function(event) {
		$(this).removeClass('is-invalid');
		if ($('#textInput').val() != "") $(this).addClass('is-valid');
	});
});

$(function() {
	$('#textInput').on('input', function(event) {
		$(this).removeClass('is-invalid');
		if ($('#titleInput').val() != "") $(this).addClass('is-valid');
	});
});

$(function() {
	setInterval(function() {
		$.ajax({
			type: "GET",
			url: "/bee/ajax/getFollower",
			data: { username: $('#username').val() },
			dataType: "json",
			success: function(data) {
				$('#follower').html(data['result']);
			},
			error: function() {
			}
		});
		
		$.ajax({
			type: "GET",
			url: "/bee/ajax/getFollowing",
			data: { username: $('#username').val() },
			dataType: "json",
			success: function(data) {
				$('#following').html(data['result']);
			},
			error: function() {
			}
		});
	}, 500);
});