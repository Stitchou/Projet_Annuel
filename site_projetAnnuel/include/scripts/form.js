//alert('form.js');
var global = this;

rbook = rbook || {};
rbook.form = r.book.form || {};

rbook.form.form = function(cfg){
	var me = this
	me.cfg = cfg;
	me.balise = $('<div class="form" />');

	$(me.cfg.renderTo).append(me.balise);
	me.balise.append(me.balise = $('<ol/>'));

	if(cfg.inputs){
		$.each(cfg.inputs, function(i, item){
			me.addInput(item);
		});
	}
	var sub = $('<button>Valider</button>');
	me.balise.append(sub);
}

esgi.form.Form.prototype = {
	addInput: function(inputCfg) {
		var me = this,
			line = $('<li/>');
		me._inputs = me._inputs ||  {};
		me.el.append(line);
		inputCfg.renderTo = line;
		me._inputs[inputCfg.name] = new esgi.form.inputs[inputCfg.type](inputCfg);
	}
}

esgi.form.inputs = esgi.form.inputs || {};

var proto = {
	init: function() {
		var me = this;
		$(me.cfg.renderTo).append(me.el);
	},
	getValue: function() {
		return this.el.val();
	}
};

esgi.form.inputs.Text = function(cfg) {
	this.cfg = cfg;
	this.el = $('<input/>');
	this.init();
}
esgi.form.inputs.Text.prototype = proto;

esgi.form.inputs.Textarea = function(cfg) {
	this.cfg = cfg;
	this.el = $('<textarea/>');
	this.init();
}
