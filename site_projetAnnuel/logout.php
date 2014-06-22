<?php 
session_start(); 
unset($_SESSION['level']); 
$_SESSION = array();
session_destroy();
header ('location: index.php');
?>