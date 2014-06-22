<div id="menu">
	<ul>
		<?php
			$menu['home'] = 'Home';
			//$menu['faq'] = 'FAQ';
			$menu['download'] = 'Download';
			if(isset($_SESSION['pseudo'])) {
				$menu['profil'] = $_SESSION['pseudo'];
				$menu['logout'] = 'Logout';
			} else {
				$menu['account'] = 'Account';
			}
			foreach ($menu as $k => $v ) {
				echo '<li>';
				if( $k == 'logout' )
					echo '<a href="' . $k . '.php"><img src="img/icon/' . $k . '.png" height="30" > ' . $v . '</a>';
				else
					echo '<a href="' . $url . $k . '"><img src="img/icon/' . $k . '.png" height="30" > ' . $v . '</a>';
				echo '</li>';
			}
		?>
	</ul>
</div>