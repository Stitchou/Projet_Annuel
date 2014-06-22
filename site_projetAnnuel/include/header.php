<?php
	session_start();
?>
<html>
	<head>
		<meta charset="UTF-8" />
		<title><?php echo $titlePage?></title>
		<link rel="stylesheet" href="css/style.css" type="text/css" />
		<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/cupertino/jquery-ui.css">
		<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	</head>
	<body>
		<?php
		include_once(INCLUD . "fonction/config.php");
		include_once(INCLUD . "fonction/connect.php");
		include_once(INCLUD . "fonction/fonction.php");
		$balise = false;
		?>
		<div id="content">
			<!--
			<div id="connection" style="float: right;">
				<?php logIn('users', 'pseudo', 'mdp', $_POST); ?>
				<?php if(isset($_SESSION['login'])) {
				 ?>
					Bonjour <?php echo $_SESSION['login']; ?> <a href="logout.php" style="margin-left:10px;position:relative; top:15px;" ><img src="img/icon/logout.png" height="30" ></a>
				<?php } else { 
					?>
					<form method="post" action=""  >
						<table>
							<tr>
								<td><input type="text" placeholder="login" name="login"  id="login" /></td>
								<td><input type="submit" name="co" value="connection"></td>
							</tr>
							<tr>
								<td><input type="password" placeholder="password" name="pwd" id="pwd"  /></td>
								<td><a href="index.php?page=account" id="connect" >Inscription</a></td>
							</tr>
						</table>
					</form>
				<?php } ?>
						
			</div>
			-->
			<div>
				<?php include( INCLUD . 'menu.php' ); ?>
			</div>
			<div id="body-content">
				<div id="page">