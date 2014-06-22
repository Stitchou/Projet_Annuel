<?php
	$owner_email = "yehouda_arnauve@hotmail.com";
	$headers = 'From: RESTOBOOK <yehouda_arnauve@hotmail.com>' ;
	$subject = 'RESTOBOOK : Une demande provenant de ' . $_POST["name"];
	$messageBody = "";
	
	$messageBody .= '<p>Visiteur: ' . $_POST["name"] . '</p>' . "\n";
	$messageBody .= '<br>' . "\n";
	$messageBody .= '<p>Adresse Mail: ' . $_POST['email'] . '</p>' . "\n";
	$messageBody .= '<br>' . "\n";
	$messageBody .= '<p>Numéros de téléphone: ' . $_POST['phone'] . '</p>' . "\n";
	$messageBody .= '<br>' . "\n";
	$messageBody .= '<p>Message: ' . $_POST['message'] . '</p>' . "\n";
	
	if($_POST["stripHTML"] == 'true'){
		$messageBody = strip_tags($messageBody);
	}

	try{
		if(!mail($owner_email, $subject, $messageBody, $headers)){
			throw new Exception('echec de l\'envoi');
		}else{
			echo 'mail envoyé';
		}
	}catch(Exception $e){
		echo $e->getMessage() ."\n";
	}
?>