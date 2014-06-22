<?php
			$jsDependencies[] = "include/scripts/mdp.js";
			if(isset($_POST['mdp'])){
 				$table = 'users';
				$sql = 'SELECT * FROM ' . $table . ' WHERE  LOWER(TRIM(' . 'mail' . ')) = "'.strtolower($_SESSION['mail']). '" AND ' . 'mdp' . '="' . $_POST['amdp']. '"';
				$req = $db->query($sql);
				$d = $req->fetch();
				if(!empty($d['users_id'])){
					$champ = array();
					$_POST['mdp'] = $_POST['mdp'];
					foreach ($_POST as $k => $v) {
						if($k != '' && $k != 'amdp' && $k != 'mdp2' )
							$champ[$k] = $v;
					}
					$param['users_id'] = $_SESSION['id'];
					update('users', $champ, $param);

					echo '<div class="confirmBox" ><p style="color:#00FF00;"> Votre mot de passe a bien été changé </p></div>';
				}
			}
?>
		<h1> Modifier mon mot de passe </h1>
		<form  method="post" class="mdpForm" >
			<table style="margin: auto;" >
				<tr>
					<td class="tableleft" width="300px"><span style="font-style:italic;font-size:10px">*</span> Ancien Mot de passe : </td>
					<td lass="tablerightP" width="300px" >
						<input type="password" name="amdp" style="border: #DEDEDE 1px solid ; background: #FFF;" />
					</td>
				</tr>
				<tr>
					<td class="tableleft" ><span style="font-style:italic;font-size:10px">*</span> Nouveau Mot de passe : </td>
					<td lass="tablerightP" >
						<input type="password" name="mdp" style="border: #DEDEDE 1px solid ; background: #FFF;" />
					</td>
				</tr>
				<tr>
					<td class="tableleft" ><span style="font-style:italic;font-size:10px">*</span> Confirmez votre Nouveau mot de passe : </td>
					<td lass="tablerightP"  >
						<input type="password" name="mdp2" style="border: #DEDEDE 1px solid ; background: #FFF;" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2" > <br /><input type="button" class="mdp" value="Valider" /> </td>
				</tr>
			</table>
			<input type="hidden" name="mail" value="<?php echo $_SESSION['mail']; ?>" >
		</form>

		<div class="dialog" ></div>