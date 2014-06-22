<div id="profil" style="width: 70%; margin: 0 auto; background: #FFFFFF; opacity: 0.8; padding: 10px 0;" >
	<?php 
		$jsDependencies[] = "include/scripts/profil.js";
		$smenu["signalement"] = "Signalement";
		$smenu["modifMdp"] = "Modifier mon mot de passe";	
		if( $_SESSION['lvl'] == ADMIN )
			$smenu["user"] = "Utilisateurs";
		//if( $_SESSION['lvl'] == USER )
	?>
	<div id="smenu" >
		<ul>
			<?php
				foreach ($smenu as $k => $v) {
					?>
					<li <?php echo ( isset($_GET['spage']) && $_GET['spage'] == $k ) ? ' class="selec" ' : ( ( !isset($_GET['spage']) && $k == 'signalement' ) ? ' class="selec" ' : '' ) ; ?> ><a href="index.php?page=profil&spage=<?php echo $k; ?>" > <?php echo $v; ?> </a></li>
					<?php
				}
			?>
		</ul>
	</div>
	<?php
		$spg = ( isset($_GET['spage']) ) ? $_GET['spage'] : 'signalement' ;
		include('spages/' . $spg . '.php');
	?>
</div>