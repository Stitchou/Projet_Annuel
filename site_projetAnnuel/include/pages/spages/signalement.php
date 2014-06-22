
	<?php 
		$jsDependencies[] = "include/scripts/profil.js";
		$id =  $_SESSION['id'];
		if( $_SESSION['lvl'] == USER ){
			$sql = 'SELECT * FROM events e, type_event te WHERE te.type_event = e.type_event AND e.users_id = ' . $id ;
			$req = $db->query($sql);
		?>	
		<h3> Vos signalements : </h3>
		<ul>
		<?php
			while ( $d = $req->fetch() ) {
				?>
				<li><?php echo $d['nom'] ?></li>
				<?php
			}
		?>
		</ul>
		<?php
		} else if( $_SESSION['lvl'] == ADMIN ){
			$sql = 'SELECT te.nom AS tenom, u.*, event_id FROM events e, type_event te, users u WHERE te.type_event = e.type_event AND e.users_id = u.users_id';
			$req = $db->query($sql);
		?>	
		<h3> Vos signalements : </h3>
		<table style="width: 100%;" >
			<tr>
				<td><b>N°</b></td>
				<td><b>signalement</b></td>
				<td><b>pseudo</b></td>
				<td><b>nom</b></td>
				<td><b>prenom</b></td>
				<td><b>action</b></td>
			</tr>
		<?php
			$i = 1;
			while ( $d = $req->fetch() ) {
				?>
				<tr id="event_<?php echo $d['event_id'] ?>" >
					<td><?php echo $i ?></td>
					<td><?php echo $d['tenom'] ?></td>
					<td><?php echo $d['pseudo'] ?></td>
					<td><?php echo $d['nom'] ?></td>
					<td><?php echo $d['prenom'] ?></td>
					<td><a onclick="delSignalement(<?php echo $d['event_id'] ?>); return false;" ><img src="img/icon/croix.png" /></a></td>
				</tr>
				<?php
				$i++;
			}
		?>
		</table>
		<?php } ?>