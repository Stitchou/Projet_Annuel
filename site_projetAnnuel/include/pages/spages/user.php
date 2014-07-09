<?php
	$jsDependencies[] = "include/scripts/profil.js";
	$ths = array(
			"pseudo",
			"nom",
			"prenom",
			"mail"
		);
	$sql = 'SELECT * FROM users';
	$req = $db->query($sql);
?>
<br/>
<br/>
<table style="width: 100%;" >
	<tr>
		<?php 
			foreach ($ths as $th) {
				echo '<th align="left" >' . $th . '</th>';
			}
		?>
		<th>Action</th>
	</tr>
	<?php
		while ($d = $req->fetch()) {
			?>
			<tr>
				<?php
					foreach ($ths as $th) {
						echo '<td>' . $d[$th] . '</td>';
					}
				?>
				<td><a onclick="delUser(<?php echo $d['users_id'] ?>); return false;" ><img src="img/icon/croix.png" /></a></td>
			</tr>
			<?php
		}
	?>
</table>