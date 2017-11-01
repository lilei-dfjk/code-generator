<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ include file="../../commons/res/taglibs.jsp"%>
<style>
.marginB20 {
	margin-bottom: 20px;
}

.attached-file {
    width: 78%;
}

ul.attachment-list {
   width:90%}

.attached-file .attached-name {
    max-width: 87%;
}

.top_5{
	margin-top: 5px;
}
</style>
<!-- ajax layout which only needs content area -->
<div class="ajax-content">
	<div class="page-header">
		<h1>
			管理 <small> <i class="ace-icon fa fa-angle-double-right"></i>
				维护 <i class="ace-icon fa fa-angle-double-right"></i> 查看信息
			</small>
		</h1>
	</div>
	<!-- /.page-header -->

	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div class="row">
				<div class="col-xs-12">
					<form id="view-form" action="" class="form-horizontal" method="post">
						<h4 class="header blue bolder smaller">基本信息</h4>
						<#if fieldList?exists>
						   <#list fieldList as field> 
						    	<#if field.index == 0>
						    		<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-sm-4 control-label no-padding-right">${field.name}：</label>
											<lable class="col-sm-6 top_5"><#noparse>${</#noparse>${moduleCode}.${field.code}<#noparse>}</#noparse></lable>
										</div>
						  	    </#if>
						    	<#if field.index == 1>
							    		<div class="form-group col-xs-6">
											<label class="col-sm-2 control-label no-padding-right">${field.name}：</label>
											<lable class="col-sm-6 top_5"><#noparse>${</#noparse>${moduleCode}.${field.code}<#noparse>}</#noparse></lable>
										</div>
									</div>
						  	    </#if>
						    	<#if field.index == 2>
						    		<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-sm-4 control-label no-padding-right">${field.name}：</label>
											<lable class="col-sm-6 top_5"><#noparse>${</#noparse>${moduleCode}.${field.code}<#noparse>}</#noparse></lable>
										</div>
									</div>
						  	    </#if>
						  </#list>
						</#if>
						<div class="clearfix form-actions">
							<div class="col-md-offset-5 col-md-7">
								<a href="<#noparse>${ctx}</#noparse>/index#${controllerUrl}" class="btn btn-info"> 
									<i class="ace-icon fa fa-arrow-left"></i> 返回
								</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	ace.load_ajax_scripts([]);
	seajs.use("<#noparse>${my}</#noparse>/js/${controllerUrl}/curd/main", function(main) {
		new main();
	});
</script>