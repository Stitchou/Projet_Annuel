<?php
	try{ 
		$pdo_options[PDO::ATTR_ERRMODE] = PDO::ERRMODE_EXCEPTION;
		$db = new PDO(BDD_HOST, BDD_LOGIN, BDD_PASS, $pdo_options);
	} catch(exception $e) {
		die('Erreur : '.$e->getMessage());
	}

?>