

function delSignalement( id ){
	var data = {};
	data['param'] = {};
	data['table'] = 'events';
	data['param']['event_id'] = id;
	$.ajax({
		data : data,
		url : 'include/data/delete.php',
		method : 'POST',
		success : function(){
			$( ['#event_', id].join('') ).fadeOut(100);
		}
	})
}

function delUser( id ){
	var data = {};
	data['param'] = {};
	data['table'] = 'users';
	data['param']['users_id'] = id;
	$.ajax({
		data : data,
		url : 'include/data/delete.php',
		method : 'POST',
		success : function(){
			$( ['#event_', id].join('') ).fadeOut(100);
		}
	})
}