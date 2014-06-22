<?php
	include("../fonction/config.php");
	include("../fonction/connect.php");
	include("../fonction/fonction.php");
?>
	{
		"success" : true,
		"data" : [
		{
<?php
	$table = $_POST['table'];

	$sql = 'SELECT * FROM ' . $table . ' WHERE '; // . $_POST['element'] . ' = "' . $_POST['valeur'] . '" ';
	//$sql2 = implode(" AND ", $_POST['element']);
	$sql2 = '';
	$i = 0;
	foreach ($_POST['element'] AS $k => $v) {
		$and = ($i == 0) ? '' : ' AND ' ;
		//$v = ( $k == 'mdp' ) ? md5($v) : $v ;
		$sql2 .= $and . $k . ' = "' . $v . '"'; 
		$i++;
	}
	$sql .= $sql2;
	$req = $db->query($sql);
	$data = $req->fetch();
?>
			"exist" : <?php echo (!empty($data[$_POST['reference']])) ? "true" : "false" ; ?>,
			"ref" : "<?php echo $data[$_POST['reference']];  ?>"
		}]
	}