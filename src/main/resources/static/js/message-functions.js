$(function() {
	$('#messageForm').submit(function(event) {
		if ($('#toInput').val() == "") {
			alert("Please enter destination user.");
			$('#toInput').addClass('is-invalid');
			return false;
		}
		
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
		
		alert("Success : Message is sended.");
	});
});

$(function() {
	$('#toInput').on('input', function(event) {
		$(this).removeClass('is-invalid');
		if (!isEmpty('#titleInput') && !isEmpty('#textInput')) $(this).addClass('is-valid');
	});
});

$(function() {
	$('#titleInput').on('input', function(event) {
		$(this).removeClass('is-invalid');
		if (!isEmpty('#toInput') && !isEmpty('#textInput')) $(this).addClass('is-valid');
	});
});

$(function() {
	$('#textInput').on('input', function(event) {
		$(this).removeClass('is-invalid');
		if (!isEmpty('#toInput') && !isEmpty('#titleInput')) $(this).addClass('is-valid');
	});
});

function isEmpty(id) {
	input = $(id);
	return input.val() == "";
}