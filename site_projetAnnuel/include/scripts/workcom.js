//alert('commentaire');

var commentaire = new rbook.form.form({
	renderTo : '#workCom',
	url : '';
	inputs : [{
		name : 'titre',
		type : 'text'
	}, {
		type : 'textarea',
		name : 'Description'
	}]
});