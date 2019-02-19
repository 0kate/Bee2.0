var userExists = false;
var passwordIsMatch = false;

$(function() {
	usernameInput = $('#usernameInput');
	usernameInput.on('input', function(event) {
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/bee/ajax",
			data: { username: usernameInput.val() },
			dataType: "json",
			beforeSend: function(xhr, setting) {
										if (usernameInput.val() == "") {
											usernameInput.removeClass('is-valid');
											usernameInput.removeClass('is-invalid');
											return false;
										}	
			 						},
			success: function(data) {
									 if (data['result']) {
										 userExists = true;
										 switchInvalid('#usernameInput');
									 } else {
										 userExists = false;
										 switchValid('#usernameInput');
									 }
								 },
			error: function() {
							 $('#userExistsMessage').html('error');
							 }
		});
	});
});

$(function() {
	passwordInput = $('#passwordInput');
	passwordConfirmInput = $('#passwordConfirmInput');
	
	passwordConfirmInput.on('input', function(evnet) {
		if (passwordInput.val() != $(this).val()) {
			passwordIsMatch = false;
			switchInvalid('#passwordConfirmInput');
		} else {
			passwordIsMatch = true;
			switchValid('#passwordConfirmInput');
		}
	});
	
	passwordInput.on('input', function(event) {
		if (passwordConfirmInput.val() != $(this).val()) {
			passwordIsMatch = false;
			switchInvalid('#passwordConfirmInput');
		} else {
			passwordIsMatch = true;
			switchValid('#passwordConfirmInput');
		}
	});
});

$(function() {
	var inputFormsId = ['#emailInput', '#ageInput', '#locationInput'];
	inputFormsId.forEach(function(id) {
		$(id).on('input', function(event) {
  		if ($(this).hasClass('is-invalid')) {
  			$(this).removeClass('is-invalid');
  		}
  	});	
	});
});

$(function() {
	$('#emailInput').on('input', function(event) {
		if ($(this).val().match(/@/) == null) {
			switchInvalid('#emailInput');
		} else {
			switchValid('#emailInput');
		}
	});
});

$(function() {
	$('#ageInput').on('input', function(event) {
		if (!$.isNumeric($(this).val()) || !isAge($(this).val())) {
			switchInvalid('#ageInput');
		} else {
			switchValid('#ageInput');
		}
	});
});

$(function() {
	$('#registForm').submit(function(event) {
		var submitOk = true;
		
		if (userExists) submitOk = false;
		if (!passwordIsMatch) submitOk =  false;
		
		if ($('#passwordInput').val().length < 5) {
			switchInvalid('#passwordInput');
			submitOk = false;
		}
		
		var inputFormsId = ['#usernameInput', '#emailInput', '#ageInput', '#locationInput', '#passwordInput', '#passwordConfirmInput'];
		inputFormsId.forEach(function(id) {
			if ($(id).val() == "") {
				switchInvalid(id);
				submitOk = false;
			} else {
				switchValid(id);
			}
		});
		
		if (!submitOk) return false;
	});
});

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

function isAge(value) {
	var num = parseInt(value, 10);
	return (0 <= num && num <= 100);
}