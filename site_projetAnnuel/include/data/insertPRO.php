<?php
	include("../fonction/config.php");
	include("../configuration/config.php");
	include("../fonction/fonction.php");
	define('SITE_LIEN', 'http://s523506334.onlinehome.fr/TRAVOZEN-V2/');

	$id_page = $_POST['id_page'];
	//
	$sqlgerant = "SELECT * from pro_pages where id ='".$id_page."'";
	$reqgerant = mysql_query($sqlgerant);
	
	$mdp = genererMDP();
	echo '"mdp" : "'.$mdp.'",';
	$mdpcrypter = md5($mdp);
	
    while ($datagerant = mysql_fetch_array($reqgerant)) {
		$_POST['element']['langue'] = 'fr';
		$_POST['element']['societe'] = $datagerant['societe'];
		$_POST['element']['adresse'] = $datagerant['adresse'];
		$_POST['element']['adresse2'] = $datagerant['adresse2'];
		$_POST['element']['cp'] = $datagerant['cp'];
		$_POST['element']['ville'] = $datagerant['ville'];
		$_POST['element']['pays'] = $datagerant['pays'];
		$_POST['element']['latitude'] = $datagerant['latitude'];
		$_POST['element']['longitude'] = $datagerant['longitude'];
		$_POST['element']['tel'] = $datagerant['tel'];
		$_POST['element']['activite_principale'] = $datagerant['activite_principale'];
		$_POST['element']['email'] = $datagerant['email'];
		$_POST['element']['mdp'] = $mdpcrypter;
		$_POST['element']['etat'] = 'En attente';		
	}

		
	$idmembres_pro = insert_into($_POST['table'], $_POST['element']);
	//
	$sqlproupdate = "update pro_pages set id_pro = '".$idmembres_pro."' where id = '".$id_page."'";
	echo $sqlproupdate;
	$reqproupdate = mysql_query($sqlproupdate);

	$to  = $_POST['to'];
	$subject = $_POST['subject'];
	//$message = $_POST['message'];
	$societe = $_POST['societe'];
	$emailFrom = $_POST['emailFrom'];

	$message = 'Bonjour '.$datagerant['societe'].' <br><br>Nous vous remercions d\'avoir reconnu être le gérant de cette entreprise sur le site www.travozen.com <br>Voici vos identifiants : <br>Login : Votre adresse mail<br>Mot de passe : <b>'.$mdp.'</b><br><br>Pour finaliser votre inscription il ne vous reste plus qu\'à cliquer sur le lien d\'activation ci-dessous :<br><a href="'.SITE_LIEN.'pro_valider_email.php?id='.$idmembres_pro.'">lien activation </a><br><br>Pensez, svp à completer vos informations sur Travozen.com<br><br>Si vous rencontrez des difficultés, merci de nous contacter au 0892 00 12 44
<br><br>Cordialement,<br><br>L\'équipe Travozen';
	
	$mailBcc = MAIL_ADMIN_CACHE.','.$_POST['message_mail']; //$_POST['mailBcc'];
	sendMail($to, $subject, $message, $societe, $emailFrom, $mailBcc);
	
?>