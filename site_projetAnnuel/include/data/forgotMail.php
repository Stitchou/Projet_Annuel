<?php
	include("../fonction/config.php");
	include("../configuration/config.php");
	//include("../fonction/connect.php");
	include("../fonction/fonction.php");
?>
	{
		"success" : true,
		"data" : [
		{
<?php
	$mdp = genererMDP();
	echo '"mdp" : "'.$mdp.'",';
	$champ['mdp'] = md5($mdp);
	if($_POST['email'] != ''){
		$sql = 'SELECT * FROM membres_particuliers WHERE email = "' . $_POST['email'] .'"';
		$req = mysql_query($sql);
		$data = mysql_fetch_array($req);
		$table = 'membres_particuliers';

		if(empty($data['id'])) {
			$sql = 'SELECT id FROM membres_pro WHERE email = "' . $_POST['email'] .'"';
			$req = mysql_query($sql);
			$data = mysql_fetch_array($req);
			$table = 'membres_pro';
		}
		$p['id'] = $data['id'];
		if(!empty($data['id'])) {
			update($table, $champ, $p);
			// envoi de mail

			$mail =  $_POST['email']; // . ', yehouda_arnauve@hotmail.com';//'yvannmahe@hotmail.com';
			$sujet = "TRAVOZEN - Mot de passe oublié";
			$message = "<html><head><meta charset='UTF-8' /></head>Bonjour, 
			<br><br>
			Vous avez signalé avoir perdu votre mot de passe<br>
			Nous l'avons réinitialisé : <br>
			votre nouveau mot de passe : <b>" . $mdp . "</b>
			<br><br>
			Cordialement,
			<br><br>
			L'équipe Travozen<br>
			</html>";
			$email = 'inscription@travozen.com'; $mail .= "";
			$From  = 'From: TRAVOZEN <'.$email.'>' . "\r\n";
			$From .= "Reply-to:TRAVOZEN<".$email.">\n";
			$From .= "Bcc:".MAIL_ADMIN_CACHE.",".$mail."\n";					
			$From .= "MIME-version: 1.0\n";
			$From .= "Content-type: text/html; charset= UTF-8\n";
			//$ret = mail($mail, $sujet, $message, $From);
			try{
				if(!mail($mail, $sujet, $message, $From)){
					//throw new Exception('echec de l\'envoi');
				}
			}catch(Exception $e){
				echo $e->getMessage() ."\n";
			}
		}

	}
?>
			"exist" : <?php echo  ($_POST['email'] == '') ? "\"vide\"" :((!empty($data['id'])) ? "true" : "false") ; ?>,
			"ref" : "<?php echo $data['id'];  ?>"
		}]
	}