$(function(){

	$('.error').fadeOut(0);
	
	// reset form and hide all errors
/*	$("a#clear").click(function(){
		$('.error').fadeOut(0);
		$('form#contact-form').clearForm();
	});
*/	
	// show message error if after editing
	// the name field contains improper value
	$("input#name").blur(function(){
		if(validateInput('name')){
			if(!validateName()){
				$("label#name_error").fadeOut(0);
				$("label#name_error2").fadeIn(250);
			}
		}else{
			$("label#name_error2").fadeOut(0);
		}
	});
	
	// show message error if after editing
	// the email field contains improper value
	$("input#email").blur(function(){
		if(validateInput('email')){
			if(!validateEmail()){
				$("label#email_error").fadeOut(0);
				$("label#email_error2").fadeIn(250);
			}
		}else{
			$("label#email_error2").fadeOut(0);
		}
	});
	
	// show message error if after editing
	// the phone field contains improper value
	$("input#phone").blur(function(){
		if(validateInput('phone')){
			if(!validatePhone()){
				$("label#phone_error").fadeOut(0);
				$("label#phone_error2").fadeIn(250);
			}
		}else{
			$("label#phone_error2").fadeOut(0);
		}
	});
	
	// show message error if after editing
	// the message field contains improper value
	$("textarea#message").blur(function(){
		if(validateTextArea('message')){
			if(!validateMessage()){
				$("label#message_error").fadeOut(0);
				$("label#message_error2").fadeIn(250);
			}
		}else{
			$("label#message_error2").fadeOut(0);
		}
	});
	
	$("input#name").keydown(function(){
		if(validateInput('name')){
			$("label#name_error").fadeOut(0);
		}
		if(validateName()){
			$("label#name_error2").fadeOut(0);
		}
	});
	
	$("input#email").keydown(function(){
		if(validateInput('email')){
			$("label#email_error").fadeOut(0);
		}
		if(validateEmail()){
			$("label#email_error2").fadeOut(0);
		}
	});
	
	$("input#phone").keydown(function(){
		if(validateInput('phone')){
			$("label#phone_error").fadeOut(0);
		}
		if(validatePhone()){
			$("label#phone_error2").fadeOut(0);
		}
	});
	
	$("textarea#message").keydown(function(){
		if(validateTextArea('message')){
			$("label#message_error").fadeOut(0);
		}
		if(validateMessage()){
			$("label#message_error2").fadeOut(0);
		}
	});
	
	var owner_email = $("input#owner_email").val();
	if(!isValidEmailAddress(owner_email)){
		$('#contact_form').html("<label class='error'></label>")
	}
		
	$("a#submit").click(function(){
		// validate and process form
		//alert('submit');
		var quit = false;
		if(validateName()){
			name = validateName();
			// alert('NAME : ' + name);
			$("label#name_error").fadeOut(0);
			$("label#name_error2").fadeOut(0);
		}else if(validateInput('name')){
			$("label#name_error").fadeOut(0);
			$("label#name_error2").fadeIn(250);
			quit = true;
		}else{
			$("label#name_error").fadeIn(250);
			$("label#name_error2").fadeOut(0);
			quit = true;
		}
		if(validateEmail()){
			email = validateEmail();
			$("label#email_error").fadeOut(0);
			$("label#email_error2").fadeOut(0);
		}else if(validateInput('email')){
			$("label#email_error").fadeOut(0);
			$("label#email_error2").fadeIn(250);
			quit = true;
		}else{
			$("label#email_error").fadeIn(250);
			$("label#email_error2").fadeOut(0);
			quit = true;
		}
		if(validatePhone()){
			phone = validatePhone();
			$("label#phone_error").fadeOut(0);
			$("label#phone_error2").fadeOut(0);
		}else if(validateInput('phone')){
			$("label#phone_error").fadeOut(0);
			$("label#phone_error2").fadeIn(250);
			quit = true;
		}else{
			$("label#phone_error").fadeIn(250);
			$("label#phone_error2").fadeOut(0);
			quit = true;
		}
		if(validateMessage()){
			message = validateMessage();
			$("label#message_error").fadeOut(0);
			$("label#message_error2").fadeOut(0);
		}else if(validateTextArea('message')){
			$("label#message_error").fadeOut(0);
			$("label#message_error2").fadeIn(250);
			quit = true;
		}else{
			$("label#message_error").fadeIn(250);
			$("label#message_error2").fadeOut(0);
			quit = true;
		}
		if(quit){
			return false;
		}
		
		var stripHTML = $("input#stripHTML").val();
		var smtpMailServer = $("input#smtpMailServer").val();
		
		var dataString = 'name=' + name + '&email=' + email + '&phone=' + phone + '&message=' + message + '&owner_email=' + owner_email + '&stripHTML=' + stripHTML + '&smtpMailServer=' + smtpMailServer;
		
		var mailHandlerURL = "include/data/MailHandler.php";
		$.ajax({
			type: "POST",
			url: mailHandlerURL,
			data: dataString,
			success: function(){
				$('.error').fadeOut(0);
				$('form#contact-form').clearForm();
				$('#contact_form').html("<div class='download-box'><div><p>Formulaire soumis ! </p></div></div>")
					.append("<br><label for='message'><b>&nbsp;Nous vous recontacterons.</b></label>")
					.fadeOut(0)
					.fadeIn(1500, function(){
						$('#contact_form').append("<br><br><span class='button1'><span></span><button><a href='#' id='back' onclick='window.location.reload(); return false;'>Retour</a></button></span>");
						//button animate
						$('.button1').hover(function(){
								$(this).find('span').stop().animate({backgroundPosition:'-80 -45'},400, 'easeInQuad')
							}, function(){
								$(this).find('span').stop().animate({backgroundPosition:'-310 -45'},400, 'easeOutQuad')
							}
						)
					});
			}
		});				
		return false;
	});
});
$.fn.clearForm = function(){
	return this.each(function(){
		var type = this.type, tag = this.tagName.toLowerCase();
		if (tag == 'form'){
			return $(':input',this).clearForm();
		}
		if (type == 'text' || type == 'password' || tag == 'textarea'){
			this.value = '';
		}else if (type == 'checkbox' || type == 'radio'){
			this.checked = false;
		}else if (tag == 'select'){
			this.selectedIndex = -1;
		}
	});
};
function isValidName(name){
	var pattern = new RegExp(/^[a-zA-Z'][a-zA-Z-' ]+[a-zA-Z']?$/);
	
	return pattern.test(name);
}

function isValidEmailAddress(emailAddress){
	var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
	
	return pattern.test(emailAddress);
}


function isValidPhoneNumber(phoneNumber){
	var pattern = new RegExp(/^\+?(\d[\d\-\+\(\) ]{5,}\d$)/);
	
	return pattern.test(phoneNumber);
}

function validateName(){
	var name = $("input#name").val();
	if(isValidName(name)){
		return name;
	}else{
		return false;
	}
}

function validateEmail(){
	var email = $("input#email").val();
	if(!isValidEmailAddress(email)){
		return false;
	}else{
		return email;
	}
}

function validatePhone(){
	var phone = $("input#phone").val();
	if(!isValidPhoneNumber(phone)){
		return false;
	}else{
		return phone;
	}
}

function validateMessage(){
	var message = $("textarea#message").val();
	if(message.length <= 19){
		return false;
	}else{
		return message;
	}
}

// make sure visitor does not input a blank field
function validateInput(field){
	var fieldObject = $("input#" + field + "").val();
	if(fieldObject.length < 1){
		return false;
	}else{
		return true;
	}
}

function validateTextArea(field){
	var fieldObject = $("textarea#" + field + "").val();
	if(fieldObject.length < 1){
		return false;
	}else{
		return true;
	}
}