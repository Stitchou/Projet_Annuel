<?php
	include("../fonction/config.php");
	include("../configuration/config.php");
	//include("../fonction/connect.php");
	include("../fonction/fonction.php");

	//sendMail($to, $subject, $message, $societe, $emailFrom, $mailBcc = null);
	$to  = $_POST['to'];
	$subject = $_POST['subject'];
	$message = $_POST['message'];
	$societe = $_POST['societe'];
	$emailFrom = $_POST['emailFrom'];
	$mailBcc = MAIL_ADMIN_CACHE.','.$_POST['message_mail']; //$_POST['mailBcc'];
	sendMail($to, $subject, $message, $societe, $emailFrom, $mailBcc);
	

?>