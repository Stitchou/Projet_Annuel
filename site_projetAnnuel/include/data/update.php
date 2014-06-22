<?php
	include("../fonction/config.php");
	//include("../fonction/connect.php");
	include("../fonction/fonction.php");

	$table = $_POST['table'];

	$sql = 'UPDATE ' . $table . ' SET ';

	$sql2 = '';
	$i = 0;
	foreach ($_POST['element'] AS $k => $v) {
			$and = ($i == 0) ? '' : ', ' ;
			$v = ( $k == 'mdp' ) ? md5($v) : $v ;
			$sql2 .= $and . $k . ' = "' . $v . '"'; 
		$i++;
	}
	$i = 0;
	foreach ($_POST['param'] AS $k => $v) {
			$and = ($i == 0) ? ' WHERE ' : ' AND ' ;
			$v = ( $k == 'mdp' ) ? md5($v) : $v ;
			$sql2 .= $and . $k . ' = "' . $v . '"'; 
		$i++;
	}
	$sql .= $sql2;
	echo  $sql;
	//$sql = $_POST['sql'];
	$req = mysql_query($sql);
	$data = mysql_fetch_array($req);
	//$req = $db->query($sql);
	//$data = $req->fetch();
?>