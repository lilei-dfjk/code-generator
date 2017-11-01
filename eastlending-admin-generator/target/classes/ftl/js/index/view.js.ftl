define(function(require, exports, module) {
	var model = require("./model");
	var rm = require("./rm");
	var shiro = require("my/js/utils/shiro");
	var controllerUrl = '${controllerUrl}';
	
	var view = Backbone.View.extend({
		el: "div.ajax-content",
		events: {
			"click #btn-search": "search",
			"click #btn-add": "add",
			"click button[role='edit']": "edit",
			"click button[role='view']": "view",
			"click button[role='delete']": "del"
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() {
			this.initDataTables();
		},
		search: function() {
			this.dt.api().draw();
		},
		initDataTables: function(e) {
			var viewSelf = this;
			this.dt = $("#result-tbl").dataTable({
				searching: false, // 禁用dataTables自带的查询框
				ordering: false, // 禁用排序
				ajax: {
					type: "POST",
					url: $$ctx + "/" +controllerUrl+ "/condition",
					data: function(data) {
				<#if conditionFieldList?exists>
					 <#list conditionFieldList as field> 
					 	<#if field.index != 2 && field.index != 3>
					 		data.${field.code} = $("#${field.code}").val(),
					 	</#if>
					 	<#if field.index == 2 || field.index == 3>
					 		data.${field.code} = $("#${field.code}").val()
					 	</#if>
	                	  
	                </#list>
				</#if>
					}
				},
				columns: [
					<#if columnFieldList?exists>
						 <#list columnFieldList as field> 
		                   {data: "${field.code}"},
		                </#list>
					</#if>
					
			        {data: "operation", render: function(data, type, row) {
			        	return Mustache.render($("#dt-row-operation").html(), {
			        		id: row.id,
//			        		permitToEdit: shiro.hasPermission("${controllerUrl}:mng:edit"),
//				        	permitToEditStatus: shiro.hasPermission("${controllerUrl}:mng:edit"),
//				        	permitToDelete: shiro.hasPermission("${controllerUrl}:mng:delete")
			        	});
	                }}
			    ]
			});
		},
		add: function() {
			window.location.href = $$ctx + "/index#"+controllerUrl+"/add";
		},
		edit: function(e) {
			var $btnSelf = $(e.currentTarget);
			window.location.href = $$ctx + "/index#"+controllerUrl+"/edit/" + $btnSelf.data("id");
		},
		view: function(e) {
			var $btnSelf = $(e.currentTarget);
			window.location.href = $$ctx + "/index#"+controllerUrl+"/view/" + $btnSelf.data("id");
		},
		del: function(e) {
			var $this = this;
			var $btnSelf = $(e.currentTarget);
			var id = $btnSelf.data("id");
			bootbox.confirm({
				message: "您确定要删除吗？",
				buttons: {
					confirm: {
						label: "<i class='ace-icon fa fa-trash-o'></i> 确定",
						className: "btn-danger btn-sm",
					},
					cancel: {
						label: "<i class='ace-icon fa fa-times'></i> 取消",
						className: "btn-sm",
					}
				},
				callback: function(result) {
					if (result) {
						$.get($$ctx + "/"+controllerUrl+"/del/" + id, function(data) {
							if (data.success) {
								bootbox.alert("删除成功！", function() {
									$this.dt.api().draw();
								})
							}
						});
					}
				}
			});
			
		}
	});
	module.exports = view;
});