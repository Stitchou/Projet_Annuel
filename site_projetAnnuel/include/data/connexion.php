<?php
	include("../fonction/config.php");
	include("../fonction/connect.php");
	include("../fonction/fonction.php");

	$sql = 'SELECT * FROM user WHERE  LOWER(TRIM(login)) = "'.strtolower($_POST['login']). '" AND pwd="' . md5($_POST['pwd']). '"';
	$req = $db->query($sql);
	$d = $req->fetch();
	$_SESSION['id'] = $d['id'];
	$_SESSION['login'] = $d['login'];
?>