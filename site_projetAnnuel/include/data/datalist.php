<?php
	include("../fonction/config.php");
	include("../fonction/connect.php");
	include("../fonction/fonction.php");
?>
	{
		"success" : true,
		"data" : [
	<?php
		$cptMax = countTable('ville', 'idVille', 'WHERE idDepartement='. $_POST['sonOf']);
		$sql = 'SELECT * FROM ville WHERE idDepartement='. $_POST['sonOf'];
		$req = $db->query($sql);
		$i = 1;
		while( $d = $req->fetch() ){
	?>
		{
			"k" : "<?php echo $d['idVille']; ?>",
			"v" : "<?php echo $d['nomVille']; ?>"
		}<?php if($i < $cptMax){ ?>,
		<?php } $i++; ?>
	<?php } ?>
	]
}