define(function(require, exports, module) {
	var rm = {
			rules : {
		<#if fieldList?exists>
		 <#list fieldList as field> 
			 <#if field.nullable == false || field.fieldType == 'Integer' || field.fieldType == 'Double' || field.fieldType == 'Float'>
			    ${field.code} : {
			    	<#if field.nullable == false>
			    		 required : true,
			    	</#if>
			    	<#if field.fieldType == 'Integer' || field.fieldType == 'Double' || field.fieldType == 'Float'>
			    		 number : true,
			    	</#if>
			    },
			 </#if>
		 </#list>
		</#if>
	
			},
			messages: {
		<#if fieldList?exists>
		 <#list fieldList as field> 
			 <#if field.nullable == false || field.fieldType == 'Integer' || field.fieldType == 'Double' || field.fieldType == 'Float'>
			    ${field.code} : {
			    	<#if field.nullable == false>
			    		 required : "此处为必填项",
			    	</#if>
			    	<#if field.fieldType == 'Integer' || field.fieldType == 'Double' || field.fieldType == 'Float'>
			    		 number : "请填写数字",
			    	</#if>
			    },
			 </#if>
		 </#list>
		</#if>
	
			}
	};
	module.exports = rm;
});