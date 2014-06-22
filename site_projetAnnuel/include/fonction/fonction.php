<?php
function insert_into($table, $champ){
	global $db;
	// $champ = unserialize($champ);
	$sql = 'INSERT INTO '.$table.'(';
	$sql1 = array();
	$i = 0;
	foreach($champ AS $key => $val){
		if($i == 0){
			$sql .= $key;
			$sql2 = '(:'.$key;
		}else{
			$sql .= ', '.$key;
			$sql2 .= ', :'.$key;
		}
		$i++;
		//echo $key.' => '.$val.'<br/>';
	}
	$sql .= ') ';
	$sql2 .= ') ';
	$sql .= 'Values'.$sql2;
	//echo '<br/><br/><br/><br/><br/><br/><br/>'.$sql.'<br/><br/><br/><br/><br/><br/><br/>';
	$req = $db->prepare($sql);
	foreach($champ AS $key1 => $val1){
		if (is_numeric($champ[$key1])){
			$req->Bindvalue(':'.$key1, $val1, PDO::PARAM_INT);
		}else{
			$req->Bindvalue(':'.$key1, $val1, PDO::PARAM_STR);
		}
	}
	$req->execute();
	//exit;
}
function update($table, $champ, $param=NULL){
	global $db;
	$sql = 'UPDATE '.$table.' SET ';
	$i = 0;
	foreach($champ AS $key => $val){
		if($i == 0)
			$sql .= $key.'=:'.$key;
		else
			$sql .= ', '.$key.'=:'.$key.' ';
		$i++;
	}
	if($param !=NULL){
		foreach($param AS $k1 => $v1){
			$where = (!empty($where))?' AND ':' WHERE ';
			$sql .= $where.' '.$k1.'=:'.$k1.' ';
			// echo $k1.' => '.$v1; exit;
		}
	}
	// echo $sql;
	$req = $db->prepare($sql);
	// echo '<br><br><br><br><br><br>'.$sql.'<br><br>';//exit;
	foreach($champ AS $k => $v){
		if (is_numeric($champ[$k])) 
			$req->Bindvalue(':'.$k , $v, PDO::PARAM_INT);
		else
			$req->Bindvalue(':'.$k, $v, PDO::PARAM_STR);
			
	// echo '<br><br><br>'.$k.' = '.$v.'<br><br><br>';
	}
	if($param !=NULL){
		foreach($param AS $k1 => $v1){
			if (is_numeric($param[$k1]))
				$req->Bindvalue(':'.$k1 , $v1, PDO::PARAM_INT);
			else
				$req->Bindvalue(':'.$k1, $v1, PDO::PARAM_STR);
				
		}
	// echo '<br><br><br>'.$k1.' = '.$v1.'<br><br><br>';//exit;
	}
	$req->execute();
}
function delete($table, $param, $param2){
	global $db;
	$sql = 'DELETE FROM '.$table.' WHERE '.$param.'='.$param2;
	// echo $sql.'<br>';
	$req = $db->prepare($sql);
	$req->execute();
}
function input($nom, $type, $name = NULL, $value = NULL, $num = NULL, $class = NULL, $style = NULL){
	$in = '';
	if($nom != '')
		$in = '<label for="'.$name.'_label" >'.$nom.' : </label>';
	$in .= '	<input type="'.$type.'"';
	if($name != NULL)
		$in .= 'name="'.$name.'" ';
	if( (strtolower($type)) == 'checkbox'){
		if($value == 'on' || $value == 1 || $value == 'oui')
			$in .= ' CHECKED ';
	}elseif( (strtolower($type)) == 'reset' ){
	}elseif( (strtolower($type)) == 'submit' ){
		$in .= $value.' ';
	}elseif( (strtolower($type)) == 'file' ){
		$in .= $value.' ';
	}else{
			$in .= ' value="'.stripslashes($value).'" ';
	}
	if($nom != '' )
		$in .= '  id="'.$name.'_label" ';
	if($num != NULL){
		for($i=0; $i<strlen($num); $i++){
		$y = substr($num, $i, 1);
		if($y == 1)
			$in .= ' onkeydown="IntegerOnly(event);" ';
		}
	}
	if($style != NULL)
		$in .= ' style="'.$style.'" ';
	if($class != NULL){
		$in .= ' '.$class.' ';
	}
	$in .= '  /><br/>';

	echo $in;
}
function radio($name , $value, $val = NULL, $saut_lign = NULL, $i = NULL, $class = NULL){
	$in ='';
	if($i == NULL)
		$i = 0 ;
	foreach($value AS $k => $v ){
		$in .= '<label for="label_'.$name.'_'.$i.'"> '.$k.'</label>';
		$in .= '<input type="radio" name="'.$name.'" value="'.$v.'" ';
		$in .= ' id="label_'.$name.'_'.$i.'" ';
		if($class != NULL)
		$in .= ' '.$class.' ';
		if($val != NULL && $val == $v)
			$in .= ' CHECKED ' ;
		$in .= ' /> ';
		if($saut_lign != NULL)
			$in .= ' '.$saut_lign;
		$i ++ ;
	}
	echo $in;
}
function textarea($nom, $name,  $value = NULL, $class = NULL){
	$in = '';
	if($nom != '')
		$in = '<label for="'.$name.'_label" >'.$nom.' : </label>';
	$in .= '	<textarea name="'.$name.'" ';
	if($nom != '')
		$in .= 'id="'.$name.'_label" ';
	if($class != NULL){
		$in .= ' '.$class.' ';
	}
	if($value != NULL)
		$in .= '>'.stripslashes($value);
	else
		$in .= '>';
	$in .= '</textarea>';

	echo $in;
}
function redirection($redir){
	echo'<meta http-equiv="refresh" content="0; URL='.$redir.'" />';
}
function last($table, $element){
	global $db;
	$sql = 'SELECT * FROM '.$table.' ORDER BY '.$element.' DESC';
	$req = $db->query($sql);
	$d = $req->fetch();
	$id = $d[$element];
	return $id;
}
function compteur(){
	global $db;
	$sql = 'SELECT * FROM compteur ORDER BY id_compteur DESC';
	$req = $db->query($sql);
	$d = $req->fetch();
	$date = date('Y-m');
	if($date == $d['date']){
		$id = $d['bon']+1;
	}else{
		$id = 0;
	}
	$champ = array('date' => $date, 'devis' => $id, 'facture' => $id, 'bon' => $id);
	insert_into('compteur', $champ);
	return $id;
}
function compt($table){
	global $db;
	$sql = 'SELECT id_user FROM '.$table.' ORDER BY id_user DESC';
	$req = $db->query($sql);
	$d = $req->fetch();
	$id = $d['id_user']+1;
	return $id;
}
function PJ_mail($mail, $message, $nomPJ){
	
	$time = date('r');
	$sujet = 'sujet';
	// Pour l'envoyer a d'autre adresse mail ex : ", mail2@mail.com, mail3@mail.com, ..."
	$mail .= "";
	switch(strrchr(basename($nomPJ), ".")){
		case ".gz": $ctype = "application/x-gzip"; break;
		case ".tgz": $ctype = "application/x-gzip"; break;
		case ".zip": $ctype = "application/zip"; break;
		case ".pdf": $ctype = "pdf"; break;
		case ".png": $ctype = "image/png"; break;
		case ".gif": $ctype = "image/gif"; break;
		case ".jpg": $ctype = "image/jpeg"; break;
		case ".txt": $ctype = "text/plain"; break;
		case ".htm": $ctype = "text/html"; break;
		case ".html": $ctype = "text/html"; break;
		case ".csv": $ctype = "application/csv-tab-delimited-table"; break;
		default: $ctype = "application/octet-stream"; break;
	}
	$From  = "From: Société YBECA \n";
	$From .= "MIME-version: 1.0\n";
	$From .= "Content-type: text/html; charset= iso-8859-1\n";
    $From .= 'Content-Type: '.$ctype.'; name="'.$nomPJ.'"'."\n"; 
    $From .= 'Content-Transfer-Encoding: base64'."\n"; 
    $From .= 'Content-Disposition:attachement; filename="'.$nomPJ.'"'."\n\n"; 

     $From .= chunk_split(base64_encode(file_get_contents($nomPJ)))."\n"; 
	mail($mail, $sujet, $message, $From);	
}
function mail2($mail, $sujet, $message){
	
	$mail .= "";
	$From  = "From: Mairie de Crest \n";
	$From .= "MIME-version: 1.0\n";
	$From .= "Content-type: text/html; charset= iso-8859-1\n";
	mail($mail, $sujet, $message, $From);	
}
function mess($submit, $name){
	$mess = '';
	if(isset($_POST[$submit]) && !isset($_POST[$name])) 
	$mess = '<b style="color: red;">Ce champ n\'est pas rempli correctement</b><br/>';
	echo $mess;
	
}
function verif($submit, $name, $correction = NULL ,$value = NULL ){
	$mess = '';
	$a = '<b style="color: red;">';
	$b = '</b>';
	if(isset($_POST[$submit])){
		if(!isset($_POST[$name]) || empty($_POST[$name])) 
			$mess = 'Ce champ n\'est pas rempli correctement<br/>';
		if($correction != NULL){
			for($i=0; $i<strlen($num); $i++){
				$y = substr($num, $i, 1);
				// si $correction contient un "1" il vérifie le format d'une adresse mail 
				if($y == 1)
					$err_mail = verif_mail($value);
			}
			if($err_mail == false){
				$mess .= ' Votre adresse mail n\'est pas valide !! <br/>';
			}
		}
	}
	if($mess != '')
		$mess = $a.$mess.$b;
	return $mess;
}

 function upload_img($_POST,$_FILES, $fichier){
 
	 // Constantes
	 $chemin = 'C:/wamp/www/projetWeb/img/';
	 // $chemin = $_SERVER['DOCUMENT_ROOT'].'/YEHOUDA/projet_annuel/include/image/';
	define('TARGET', $chemin);    // Repertoire cible
	define('MAX_SIZE', 100000);    // Taille max en octets du fichier
	define('WIDTH_MAX', 8000);    // Largeur max de l'image en pixels
	define('HEIGHT_MAX', 8000);    // Hauteur max de l'image en pixels
	 
	// Tableaux de donnees
	$tabExt = array('jpg','gif','png','jpeg','bmp');    // Extensions autorisees
	$infosImg = array();
	 //echo "<script>alert('fonction upload_img')</script>";
	// Variables
	$extension = '';
	$message = '';
	$nomImage = '';
	 //if(!empty($_POST)) echo "<script>alert('post non_vide')</script>";
		 //if(!empty($_FILES)) echo "<script>alert('file non_vide')</script>";
	if(!empty($_POST))
	{
	  // On verifie si le champ est rempli
	  if( !empty($_FILES[$fichier]['name']) )
	  {
	  // echo 'YO'; exit;
		// Recuperation de l'extension du fichier
		$extension  = pathinfo($_FILES[$fichier]['name'], PATHINFO_EXTENSION);
	 
		// On verifie l'extension du fichier
		if(in_array(strtolower($extension),$tabExt))
		{
		  // On recupere les dimensions du fichier
		  $infosImg = getimagesize($_FILES[$fichier]['tmp_name']);
	 
		  // On verifie le type de l'image
		  if($infosImg[2] >= 1 && $infosImg[2] <= 14)
		  {
			// On verifie les dimensions et taille de l'image
			if(($infosImg[0] <= WIDTH_MAX) && ($infosImg[1] <= HEIGHT_MAX) && (filesize($_FILES[$fichier]['tmp_name']) <= MAX_SIZE))
			{
			// echo $_FILES['fichier']['error']; exit;
			  // Parcours du tableau d'erreurs
			  if(isset($_FILES[$fichier]['error']) 
				&& UPLOAD_ERR_OK === $_FILES[$fichier]['error'])
			  {
				// On renomme le fichier
				$nomImage = md5(uniqid()) .'.'. $extension;
	 
				// Si c'est OK, on teste l'upload
				if(move_uploaded_file($_FILES[$fichier]['tmp_name'], TARGET.$nomImage))
				{
					// echo "<script>alert('LA')</script>";
					// echo 'bonjour'; exit;
					// $sql_client_insert =", image ";
					// $sql_client_value =" ,'$nomImage' ";
					$message = 'Logo Enregistré !';
				}
				else
				{
				  // Sinon on affiche une erreur systeme
				  $message = 'Problème lors de la recuperation du logo !';
				}
			  }
			  else
			  {
				$message = 'Une erreur interne a empêché la recuperation du logo';
			  }
			}
			else
			{
			  // Sinon erreur sur les dimensions et taille de l'image
			  $message = 'Erreur dans les dimensions du logo !';
			}
		  }
		  else
		  {
			// Sinon erreur sur le type de l'image
			$message = 'Le fichier à uploader n\'est pas une image !';
		  }
		}
		else
		{
		  // Sinon on affiche une erreur pour l'extension
		  $message = 'L\'extension du fichier est incorrecte !';
		}
	  }
	  else
	  {
		// Sinon on affiche une erreur pour le champ vide
		$message = 'Veuillez remplir le formulaire svp !';
	  }
	  
	}
	//echo $message;
	return $nomImage;
}
function type_select($name, $champ, $slc=NULL, $nom=NULL){
	$slt = '';
	if($nom != NULL)
		$slt .= '<label for="'.$name.'_label">'.$nom.' : </label>';
	$id = ($nom != NULL) ? 'name="'.$name.'" id="'.$name.'_label"' : 'name="'.$name.'"' ;
	$slt .= '<select '.$id.'>';
	foreach($champ AS $k => $v){
		if($slc != NULL)
			$a = ($slc == $k) ? 'SELECTED': '' ;
		$slt .= '<option value="'.$k.'" '.$a.'>'.$v.'</option> ';
	}
	$slt .= '</select>';
	echo $slt;
}
function convertColor($color){
	#convert hexadecimal to RGB
	if(!is_array($color) && preg_match("/^[#]([0-9a-fA-F]{6})$/",$color)){

		$hex_R = substr($color,1,2);
		$hex_G = substr($color,3,2);
		$hex_B = substr($color,5,2);
		$RGB = hexdec($hex_R).",".hexdec($hex_G).",".hexdec($hex_B);

		return $RGB;
	}
	#convert RGB to hexadecimal
	else{
		if(!is_array($color)){
			$color = explode(",",$color);
		}
		foreach($color as $value){
			$hex_value = dechex($value); 
			if(strlen($hex_value)<2){
				$hex_value="0".$hex_value;
			}
			$hex_RGB.=$hex_value;
		}
		return "#".$hex_RGB;
	}
}
function date_form($date, $form = NULL){
	if($form == NULL)
		$form = 1;
	switch($form){
		case 1 ; // cas ou date = d/m/Y H:i:s
			$a = explode('/', $date);
			$c = $a[2].'-'.$a[1].'-'.$a[0];
			$b=date("Y-m-d H:i:s", strtotime($c));
		break;
		case 2 ; // cas ou date = Y-m-d H:i:s
			$c = explode(' ', $date);
			$a = explode('-', $c[0]);
			$b = $a[2].'/'.$a[1].'/'.$a[0];//.' '.$c[1];
		break;
		case 3 ;
			$c = explode(' ', $date);
			$a = explode('-', $c[0]);
			$b = $a[1].'/'.$a[2].'/'.$a[0].' '.$c[1];
		break;
	}
	return $b;
}
function verif_mail($adresse){ 
   $Syntaxe='#^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,6}$#'; 
   if(preg_match($Syntaxe,$adresse)) 
      return true; 
   else 
     return false; 
}
function login_exist($table, $log){
	global $db;
	$a = 0;
	$sql = 'SELECT * FROM  '.$table.' ';
	$req = $db->query($sql);
	while($d = $req->fetch()){
		$login = $d['login'];
		if((strtolower($login)) == (strtolower($log)))
			$a = 1;
	}
	if($a == 1)
		$mess = '<b style="color:red;">Ce login existe deja veuillez en choisir un autre !</b>';
	else
		$mess = '';
	
	return $mess;
}

function isset_bdd($table, $donneTable, $donne){
	global $db;
	$sql = 'SELECT ' . $donneTable . ' FROM ' . $table;
	$req = $db->query($sql);
	$d = $req->fetch();
	if(empty($d[$donneTable]))
		return false;
	else
		return true;
}

function logIn($table, $log, $pwd, $_POST){
	global $db;
	if(isset($_POST)){
		if(empty($_POST[$log]) || empty($_POST[$pwd])){
			if(empty($_POST[$log]))
				$err['log'] = 'Login vide';
			if(empty($_POST['pwd']))
				$err['pwd'] = 'mot de passe vide';
			$_SESSION['err'] = $err;
		}else{
			// echo 'YOP';
			$sql = 'SELECT * FROM ' . $table . ' WHERE  LOWER(TRIM(' . $log . ')) = "'.strtolower($_POST['login']). '" AND ' . $pwd . '="' . $_POST[$pwd]. '"';
			$req = $db->query($sql);
			$d = $req->fetch();
			// echo $sql;
			if(!empty($d['id'])){
			// echo 'YOP';
				$_SESSION['id'] =$d['users_id'];
				$_SESSION['login'] =$d['login'];
				$_SESSION['mail'] =$d['mail'];
			}
		}
	}
}

function countTable($table, $cpt, $condition = null){
	global $db;
	$sql = 'SELECT count(' . $cpt . ') AS nb FROM ' . $table;
	if($condition != null)
		$sql .= ' ' . $condition;
	$req = $db->query($sql);
	$d = $req->fetch();
	return $d['nb'];
}

function liste_fichier($chemin){
	$dirname = $chemin;
	$dir = opendir($dirname); 
	$doc = array();
	while($file = readdir($dir)) {
		if($file != '.' && $file != '..' && !is_dir($dirname.$file))
		{
			$doc[] = $file;
		}
	}

	closedir($dir);
	return $doc;
}


function code($texte){
//Smileys
$texte = str_replace(':D ', '<img src="./images/smileys/heureux.gif" title="heureux" alt="heureux" />', $texte);
$texte = str_replace(':lol: ', '<img src="./images/smileys/lol.gif" title="lol" alt="lol" />', $texte);
$texte = str_replace(':triste:', '<img src="./images/smileys/triste.gif" title="triste" alt="triste" />', $texte);
$texte = str_replace(':frime:', '<img src="./images/smileys/cool.gif" title="cool" alt="cool" />', $texte);
$texte = str_replace(':rire:', '<img src="./images/smileys/rire.gif" title="rire" alt="rire" />', $texte);
$texte = str_replace(':s', '<img src="./images/smileys/confus.gif" title="confus" alt="confus" />', $texte);
$texte = str_replace(':O', '<img src="./images/smileys/choc.gif" title="choc" alt="choc" />', $texte);
$texte = str_replace(':question:', '<img src="./images/smileys/question.gif" title="?" alt="?" />', $texte);
$texte = str_replace(':exclamation:', '<img src="./images/smileys/exclamation.gif" title="!" alt="!" />', $texte);

//Mise en forme du texte
//gras
$texte = preg_replace('`\[g\](.+)\[/g\]`isU', '<strong>$1</strong>', $texte); 
//italique
$texte = preg_replace('`\[i\](.+)\[/i\]`isU', '<em>$1</em>', $texte);
//souligné
$texte = preg_replace('`\[s\](.+)\[/s\]`isU', '<u>$1</u>', $texte);
//lien
$texte = preg_replace('#http://[a-z0-9._/-]+#i', '<a href="$0">$0</a>', $texte);
//etc., etc.

//On retourne la variable texte
return $texte;
}
?>