				</div>
			</div>
			<div id="footer" >
				<ul>
					<?php
						foreach ($menu as $k => $v ) {
							echo '<li>';
							if($k == 'logout' )
								echo '<a href="' . $k . '.php">' . $v . '</a>';
							else
								echo '<a href="' . $url . $k . '">' . $v . '</a>';
							echo '</li> | ';
						}
					?>
				</ul>
				<div>CopyRight &copy; Tous droit réservés ESGI 2014</div>
			</div>
		</div>
		<?php 
			//$jsDependencies[] = "http://code.jquery.com/jquery-1.11.0.min.js"
			//$jsDependencies[] = "include/scripts/myLib.js"
			//$jsDependencies[] = "include/scripts/contact-form.js"
		if($balise) { 
		?>
			<script src="include/scripts/script.js"></script>
		<?php 
		}
		if (isset($jsDependencies)){
			foreach($jsDependencies as $jsd){
				?>
				<script src="<?php echo $jsd; ?>"></script>
				<?php
			}
		}
		?>

	</body>
</html>