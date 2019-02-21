var userExists = false;
$(function() {
	toInput = $('#toInput');
	toInput.on('input', function(event) {
		$.ajax({
			type: "GET",
			url: "/bee/ajax/usrExists",
			data: { username: toInput.val() },
			dataType: "json",
			beforeSend: function(xhr, setting) {
										if (toInput.val() == "") {
											toInput.removeClass('is-valid');
											toInput.removeClass('is-invalid');
											return false;
										}	
			 						},
			success: function(data) {
									 if (!data['result']) {
										 userExists = true;
										 switchInvalid('#toInput');
									 } else {
										 userExists = false;
										 switchValid('#toInput');
									 }
								 },
			error: function() {}
		});
	});
});

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

function switchValid(id) {
	inputForm = $(id);
	inputForm.removeClass('is-invalid');
	inputForm.addClass('is-valid');
}

function switchInvalid(id) {
	inputForm = $(id);
	inputForm.removeClass('is-valid');
	inputForm.addClass('is-invalid');
}