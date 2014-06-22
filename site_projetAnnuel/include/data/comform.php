<?php
	include("../fonction/config.php");
	include("../fonction/connect.php");
	include("../fonction/fonction.php");

	$_POST['photo1'] = upload_img($_POST,$_FILES, $_POST['photo1']);

	foreach ($_POST as $k => $v) {
		if($k != 'sendCom' && $k != 'file')
			$champ[$k] = $v;
	}

	insert_into('commentaires', $champ);
?>

	{
		"success" : true,
		"data" : [
	<?php
		$cptMax = countTable('commentaires', 'idCommentaire');
		$sql = 'SELECT * FROM commentaires ORDER BY idCommentaire DESC';
		$req = $db->query($sql);
		$i = 1;
		while( $d = $req->fetch() ){
	?>
		{
			"k" : "<?php echo $d['pointsPositif']; ?>",
			"v" : "<?php echo $d['pointsNegatif']; ?>"
		}<?php if($i < $cptMax){ ?>,
		<?php } $i++; ?>
	<?php } ?>
	]
}