define(function(require, exports, module) {
	var model = require("./model");
	var rm = require("./rm");
	var shiro = require("my/js/utils/shiro");
	var controllerUrl = '${controllerUrl}';
	
	var view = Backbone.View.extend({
		el: "div.ajax-content",
		events: {
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() {
			this.validateForm();
		},
		search: function() {
			this.dt.api().draw();
		},
		validateForm: function() {		
			var viewSelf = this;
			$("#save-form").validate({
				rules: rm.rules,
				messages: rm.messages,
				submitHandler: function(form) {
					var formSelf = $(form);
					viewSelf.model.submitForm(formSelf, function(data) {
						if (data.success) {
							bootbox.alert("保存成功", function() {
								window.location.href = $$ctx + "/index#" + controllerUrl;
							});
						}else {
							bootbox.alert("保存失败");
						}
					});
				}
			});
		}
	});
	module.exports = view;
});