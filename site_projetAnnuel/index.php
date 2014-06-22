<?php
	//include($_SERVER['DOCUMENT_ROOT'] . '/include/configuration/config.php');
	include($_SERVER['DOCUMENT_ROOT'] . '/site_projetAnnuel/include/configuration/config.php');
	//echo $_SERVER['DOCUMENT_ROOT'] . '/include/configuration/config.php';
	
	$titlePage = "";
	$url = 'index.php?page=';
	include(INCLUD . 'header.php');

	$pg = (isset($_GET['page']) && !empty($_GET['page'])) ? ($_GET['page']) : 'home' ;
	$pge = PAGE . $pg . '.php';


	if(file_exists($pge)){
		include($pge);
	}else{
		echo 'Page en construction !';
	}

	//$jsDependencies[] = 'include/scripts/form.js';
	//$jsDependencies[] = 'include/scripts/workcom.js';

	include(INCLUD . 'footer.php');
?>