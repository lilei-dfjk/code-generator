define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		submitForm: function($form, callback) {
			$form.ajaxSubmit(function(data) {
				callback(data);
			});
		}
	});
	
	module.exports = model;
});