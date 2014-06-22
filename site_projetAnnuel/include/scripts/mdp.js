	$(function() {
		$( ".dialog" ).dialog({
			width: 600,
			autoOpen: false,
			modal: true,
			open: function (event, ui) {
		        $(".ui-widget-overlay").css({
		            opacity: 0.5,
		            filter: "Alpha(Opacity=0)",
		            backgroundColor: "#FFFFFF",
		            backgroundImage: "none"
		        });
    		},
    		buttons : {
    			"OK" : function(){
    				$( this ).dialog( "close" );
    			}
    		},
			show: {
				effect: "puff",
				duration: 1000
			},
			hide: { // effect : bounce, explode, blind, puff, scale, transfer
				effect: "scale",
				duration: 1000
			}
		});
		$( ".confirmBox" ).dialog({
			width: 600,
			autoOpen: false,
			modal: true,
			open: function (event, ui) {
		        $(".ui-widget-overlay").css({
		            opacity: 0.5,
		            filter: "Alpha(Opacity=0)",
		            backgroundColor: "#FFFFFF",
		            backgroundImage: "none"
		        });
    		},
    		buttons : {
    			"OK" : function(){
    				$( this ).dialog( "close" );
    			}
    		},
			show: {
				effect: "puff",
				duration: 1000
			},
			hide: { // effect : bounce, explode, blind, puff, scale, transfer
				effect: "scale",
				duration: 1000
			}
		});
	});

	$( "input[type=button].mdp" ).click(function() { 
	    
		var mdpa = $("input[name=amdp]").val();
		var mdp = $("input[name=mdp]").val();
		var mdp2 = $("input[name=mdp2]").val();
		var mail = $("input[name=mail]").val();
		
		var err = {};
		var error = "<p style=\"color:#000000;font-weihgt: bold;\" ><b>Veuillez corriger les erreurs suivantes : </b></p>";
		var dial = $( ".dialog" );
		var i = 0;
		
		if( mdpa == '' ) 
			err["l'ancien mot de passe"] = 'est obligatoire';

		if( mdpa != '' ){
			var data = {};
			data['table'] = 'users';
			data['element'] = {
				'mdp' : mdpa,
				'mail' : mail
			};
			data['reference'] = 'users_id';
			$.ajax({
				data : data,
				url : 'include/data/exist.php',
				method : 'POST',
				async : false,
				dataType : 'Json',
				success : function( resp ) {
					if( !resp.data[0].exist )
						err["l'ancien mot de passe"] = " n'est pas bon " ;
				}
			})
		}
		if( mdp == '' ) 
			err["mot de passe"] = 'est obligatoire';
		if( mdp2 == '' )
			err["confirmation mot de passe"] = 'est obligatoire';

		if(mdp != mdp2)
			err["Mot de passe non confirmé"] = 'les deux mot de passe sont différents';
		if(mdp != '' && mdp.length < 6)
			err["Mot de passe"] = "n'est pas assez long (6 caracteres minimum)";
		
		for( k in err ){
			error += (["<p> le champ <b>", k, "</b> ", err[k], "</p>"].join(''));
			i++;
		}
		dial.html(error);
		if(i > 0){
    		$(".dialog").dialog('option', 'position', [500, 50]);
    												//[left, top]
			$( ".dialog" ).dialog( "open" );
			//$(".ui-dialog-titlebar").css('background-color', '#ff0000');
		} else {
			$('.mdpForm').submit();
		}
	});
	