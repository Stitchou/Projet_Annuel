<?php
	$err = array();
	$jsDependencies[] = "include/scripts/connect.js";
	// INSCRIPTION
	if(isset($_POST['pseudo'])){
		foreach($_POST AS $k => $v){
			if($k != 'in' && $k != 'mdp2')
				$param[$k] = $v;
		}
		insert_into('users', $param);
		$_SESSION['id'] = $d['id'];
		$_SESSION['pseudo'] = $d['pseudo'];
		$_SESSION['mail'] = $d['mail'];
		$_SESSION['lvl'] = 1;
		redirection('index.php');
	}

	// CONNEXION
	if(isset($_POST['pseudo_co'])){
		//echo 'YOP';
		//echo '<script>alert("yop")</script>';
		$log_co = $_POST['pseudo_co'];
		$mdp_co = $_POST['mdp'];
		// echo 'YOP';
		$sql = 'SELECT * FROM users WHERE  LOWER(TRIM(pseudo)) = "' . strtolower($_POST['pseudo_co']) . '" AND mdp="' . $_POST['mdp'] . '"';
		$req = $db->query($sql);
		$d = $req->fetch();
		//echo $sql;
		if(!empty($d['users_id'])){
		// echo 'YOP';
			$_SESSION['id'] = $d['users_id'];
			$_SESSION['pseudo'] = $d['pseudo'];
			$_SESSION['mail'] = $d['mail'];
			$_SESSION['lvl'] = $d['level'];
			redirection('index.php');
		} else {
			?>
			<script>
				$( '.dialog' ).html(
					"<p  style='color: #FF0000;' >" +
					" Erreur <b>Login / Mot de passe</b> veuillez reesayez " +
					"</p>"
					);
				
				$( '.dialog' ).dialog("open");
			</script>
			<?php
		}
	}
?>
<div id="connexionPage" style="margin: 0 auto; width: 60%;" >
	<div style="color: #FF0000; ">
		<b>
	<?php
//		$i = 1;
//		foreach ($err as $error) {
//			echo 'ERREUR ' . $i . ' : <b> ' . $error . '</b><br/>';
//			$i++;
//		}
//	?>
		</b>
	</div>
	<div id="connexion">
		<fieldset class="field" style="width: 300px;" >
			<h4>Connexion</h4>
			<form method="post" action="" class="connexion" >
				<table border="0" >
				<tr><td><label for="pseudo" ><b>pseudo : </b>		</label>	</td><td><input type="text" name="pseudo_co" value="<?php echo ( !empty($_POST['pseudo_co']) ) ? $_POST['pseudo_co'] : '' ; ?>"  id="pseudo" /></td></tr>
				<tr><td><label for="mdp" ><b>Mot de passe : </b>	</label>	</td><td><input type="password" name="mdp" id="mdp" value="<?php echo ( !empty($_POST['mdp']) ) ? $_POST['mdp'] : '' ; ?>" /></td></tr>
				<tr><td></td><td><input type="button" class="co" name="co" value="connection"></td><td></td></tr>
				</table>
			</form>
		</fieldset>
	</div>
	<div id="inscription">
		<fieldset class="field" style="width: 300px;" >
			<h4>Inscription</h4>
			<form method="post" action="" class="inscription" >
				<input type="hidden" name="actif" value="1" />
				<table border="0" >
				<tr><td><label for="pseudo"  ><b>pseudo : </b>		</label>	</td> <td><input type="text" name="pseudo" value="<?php echo ( !empty($_POST['pseudo']) ) ? $_POST['pseudo'] : '' ?>" id="pseudo" /></td></tr>
				<tr><td><label for="nom"  ><b>nom : </b>			</label>	</td> <td><input type="text" name="nom" value="<?php echo ( !empty($_POST['nom']) ) ? $_POST['nom'] : '' ?>" id="nom" /></td></tr>
				<tr><td><label for="prenom"  ><b>prenom : </b>		</label>	</td> <td><input type="text" name="prenom" value="<?php echo ( !empty($_POST['prenom']) ) ? $_POST['prenom'] : '' ?>" id="prenom" /></td></tr>
				<tr><td><label for="mail" ><b>mail : </b>			</label>	</td> <td><input type="text" name="mail" value="<?php echo ( !empty($_POST['mail']) ) ? $_POST['mail'] : '' ?>" id="mail"  /></td></tr>
				<tr><td><label for="mdp"   ><b>Mot de passe : </b>	</label>	</td> <td><input type="password" name="mdp" id="mdp" value="<?php echo ( !empty($_POST['mdp']) ) ? $_POST['mdp'] : '' ; ?>" /></td></tr>
				<tr><td><label for="mdp2" ><b>Confirme : </b>		</label>	</td> <td><input type="password" name="mdp2" id="mdp2" value="<?php echo ( !empty($_POST['mdp2']) ) ? $_POST['mdp2'] : '' ; ?>" /></td></tr>
				<tr><td></td><td><input type="button" class="in" name="in" value="inscription"></td></tr>
				</table>
			</form>
		</fieldset>
	</div>
	<div class="dialog" ></div>
</div>