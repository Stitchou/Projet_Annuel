//alert('mylib');
$('#dept').change(function(){
	var data = {};
	data['sonOf'] = $('#dept').val();
	$.ajax({
		url : '/projetWeb/include/data/datalist.php',
		data : data,
		method: 'POST',
		dataType : 'json',
		success: function(resp) {
			console.log(resp);
			if (resp.success) {
				var selector = $('#ville');
					selector.empty();

				$.each(resp.data, function(i,e){
					selector.append($(['<option value="',e.k,'">',e.v,'</option>'].join('')));
				})
			}
		}
	});
});

/*
$('#sendCom').click(function(){
	var data = {};
	data['pointsPositif'] = $('textarea#commTextPos').val();
	data['pointsNegatif'] = $('textarea#commTextNeg').val();
	data['idResto'] = $('#idResto').val();
	data['idUser'] = $('#idUser').val();
	data['photo1'] = $('#photo1').val();
	//alert(data['pointsPositif']);
	//alert(data['pointsNegatif']);
	//alert(data['idResto']);
	//alert(data['idUser']);
	//alert(data['photo1']);
	$.ajax({
		//alert('ajax'),
		url : '/projetWeb/include/data/comform.php',
		data : data,
		enctype : 'multipart/form-data',
		method: 'GET',
		dataType : 'json',
		success : function(resp){
			//alert('success');
			console.log(resp);
			if(resp.success) {
				alert('votre commentaire a bien été posté');
				var select = $('#listcom');
					select.empty();
			}else {
				alert('passe pas');
			}
		}
	});
});
*/