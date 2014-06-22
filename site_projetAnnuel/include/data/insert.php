<?php
	include("../fonction/config.php");
	//include("../fonction/connect.php");
	include("../fonction/fonction.php");

	insert_into($_POST['table'], $_POST['element']);

?>