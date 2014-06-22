
	$(function() {
		$( ".dialog" ).dialog({
			width: 800,
			height: 500,
			position : [500, 50],
      		autoOpen: false,
			show: {
				effect: "puff",
				duration: 1000
			},
			hide: { // effect : bounce, explode, blind, puff, scale, transfer
				effect: "scale",
				duration: 1000
			},
			buttons : {
				"OK" : function(){
					$( this ).dialog("close");
				}
			}
		});
	});

	$( 'input[name=co]' ).click(function(){
		//alert("yoooop");
		var i = 0;
	    var pseudo = $("#connexion input[name=pseudo_co]").val();
	    var mdp = $("#connexion input[name=mdp]").val();
	    //var mail = $("input[name=mail]").val();
	    var err = {};
		var error = "<p style=\"color:#000000;font-weihgt: bold;\" ><b>Veuillez corriger les erreurs suivantes : </b></p>";

		if( pseudo == '' )
			err['Pseudo'] = " est vide ";
		if( mdp == '' )
			err['Mot de passe'] = " est vide ";

		/*
		if( mail != '' && isEmail(mail) ){
			var data = {};
			data['table'] = 'membres_pro';
			data['element'] = 'mail';
			data['valeur'] = mail;
			data['reference'] = 'id';
			$.ajax({
				data : data,
    			async: false,
				url : 'include/data/exist.php',
				method : 'POST',
				dataType : 'Json',
				success : function(resp){
					if( resp.data[0].exist )
						err['Adresse mail'] = 'existe deja dans notre base de donnée ! ';
				}
			});
		}
		*/
		if( pseudo != '' && mdp != '' ) {
			var data = {};
			data['table'] = 'users';
			data['element'] = {};
			data['element']['pseudo'] = pseudo;
			data['element']['mdp'] = mdp;
			data['reference'] = 'users_id';
			$.ajax({
				data : data,
    			async: false,
				url : 'include/data/exist.php',
				method : 'POST',
				dataType : 'Json',
				success : function(resp){
					if( !resp.data[0].exist )
						err['pseudo / mot de passe'] = 'est erroné ';
				}
			});
		}

		for( k in err ){
			error += (["<p style='color: #FF0000;' > le champ <b>", k, "</b> ", err[k], "</p>"].join(''));
			i++;
		}
		$(".dialog").html(error);
		if(i > 0){
			$( ".dialog" ).dialog( "open" );
		} else {
			$('form.connexion').submit();
		}
	})

	$( 'input[name=in]' ).click(function(){
		//alert("yoooop");
		var i = 0;
	    var pseudo = $("#inscription input[name=pseudo]").val();
	    var mdp = $("#inscription input[name=mdp]").val();
	    var mdp2 = $("#inscription input[name=mdp2]").val();
	    var email = $("#inscription input[name=mail]").val();
	    var err = {};
		var error = "<p style=\"color:#000000;font-weihgt: bold;\" ><b>Veuillez corriger les erreurs suivantes : </b></p>";

		if( pseudo == '' )
			err['Pseudo'] = " est vide ";
		if( mdp == '' )
			err['Mot de passe'] = " est vide ";

		if( mdp == '' ) 
			err["mot de passe"] = 'est obligatoire';
		if( mdp2 == '' )
			err["confirmation mot de passe"] = 'est obligatoire';
		if(mdp != mdp2)
			err["les deux mot de passe"] = 'sont différents';
		if(mdp.length < 6)
			err["Mot de passe"] = "n'est pas assez long (6 caracteres minimum)";

		if( email == '' )
			err["Adresse Email"] = 'est vide'
		if( email != '' && !isEmail(email) )
            err["Adresse email"] = 'n\'est pas valide';


		if( mail != '' && isEmail(mail) ){
			var data = {};
			data['table'] = 'users';
			data['element'] = {};
			data['element']['mail'] = mail;
			data['reference'] = 'users_id';
			$.ajax({
				data : data,
    			async: false,
				url : 'include/data/exist.php',
				method : 'POST',
				dataType : 'Json',
				success : function(resp){
					if( resp.data[0].exist )
						err['Adresse mail'] = 'existe deja dans notre base de donnée ! ';
				}
			});
		}

		for( k in err ){
			error += (["<p style='color: #FF0000;' > le champ <b>", k, "</b> ", err[k], "</p>"].join(''));
			i++;
		}
		$(".dialog").html(error);
		if(i > 0){
			$( ".dialog" ).dialog( "open" );
		} else {
			$('form.inscription').submit();
		}
	})



function isEmail(myVar){
     // La 1ère étape consiste à définir l'expression régulière d'une adresse email
     var regEmail = new RegExp('^[0-9a-z._-]+@{1}[0-9a-z.-]{2,}[.]{1}[a-z]{2,5}$','i');

     return regEmail.test(myVar);
   }