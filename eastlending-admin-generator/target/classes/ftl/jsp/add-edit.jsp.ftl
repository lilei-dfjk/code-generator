<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ include file="../../commons/res/taglibs.jsp" %>
<style>
/* .attached-file .attached-name {
	max-width: 248px;
}
.attached-file {
	width: 270px;
}
 */
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
</style>
<!-- ajax layout which only needs content area -->
<div class="ajax-content">
	<div class="page-header">
		<h1>
			管理
			<small>
				<i class="ace-icon fa fa-angle-double-right"></i> 维护 <i class="ace-icon fa fa-angle-double-right"></i> 新增
			</small>
		</h1>
	</div><!-- /.page-header -->
	
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div class="row">
				<div class="col-xs-12">
					<form id="save-form" action="<#noparse>${ctx}</#noparse>/${controllerUrl}/save" class="form-horizontal" method="post">
						<h4 class="header blue bolder smaller">基本信息</h4>
						<input id="id" name="id" value="<#noparse>${id}</#noparse>" type="hidden" />
						<#if fieldList?exists>
						   <#list fieldList as field> 
						    	<#if field.index == 0>
						    		<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-sm-4 control-label no-padding-right">${field.name}</label>
											<div class="col-sm-6">
												<div class="clearfix">
													<#if field.fieldType == 'Enum'>
														<select name="${field.code}" class="col-sm-10">
															<option value="">请选择</option>
															<#list field.enumMap?keys as key >
																<option value="${key}">${field.enumMap[key]}</option>
															</#list>
														</select>
														<script type="text/javascript">
															$("select[name = '${field.code}']").val('<#noparse>${</#noparse>${moduleCode}.${field.code}<#noparse>}</#noparse>');
														</script>
													</#if>
													<#if field.fieldType != 'Enum'>
														<input type="text" name="${field.code}" class="col-sm-10" value = '<#noparse>${</#noparse>${moduleCode}.${field.code}<#noparse>}</#noparse>'/>
													</#if>
													
												</div>
											</div>
										</div>
						  	    </#if>
						    	<#if field.index == 1>
							    		<div class="form-group col-xs-6">
											<label class="col-sm-4 control-label no-padding-right">${field.name}</label>
											<div class="col-sm-6">
												<div class="clearfix">
													<#if field.fieldType == 'Enum'>
														<select name="${field.code}" class="col-sm-10">
															<option value="">请选择</option>
															<#list field.enumMap?keys as key >
																<option value="${key}">${field.enumMap[key]}</option>
															</#list>
														</select>
														<script type="text/javascript">
															$("select[name = '${field.code}']").val('<#noparse>${</#noparse>${moduleCode}.${field.code}<#noparse>}</#noparse>');
														</script>
													</#if>
													<#if field.fieldType != 'Enum'>
														<input type="text" name="${field.code}" class="col-sm-10" value = '<#noparse>${</#noparse>${moduleCode}.${field.code}<#noparse>}</#noparse>'/>
													</#if>
												</div>
											</div>
										</div>
									</div>
						  	    </#if>
						    	<#if field.index == 2>
						    		<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-sm-4 control-label no-padding-right">${field.name}</label>
											<div class="col-sm-6">
												<div class="clearfix">
													<#if field.fieldType == 'Enum'>
														<select name="${field.code}" class="col-sm-10">
															<option value="">请选择</option>
															<#list field.enumMap?keys as key >
																<option value="${key}">${field.enumMap[key]}</option>
															</#list>
														</select>
														<script type="text/javascript">
															$("select[name = '${field.code}']").val('<#noparse>${</#noparse>${moduleCode}.${field.code}<#noparse>}</#noparse>');
														</script>
													</#if>
													<#if field.fieldType != 'Enum'>
														<input type="text" name="${field.code}" class="col-sm-10" value = '<#noparse>${</#noparse>${moduleCode}.${field.code}<#noparse>}</#noparse>'/>
													</#if>
												</div>
											</div>
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
								<button type="submit" class="btn btn-success">
									<i class="ace-icon fa fa-check"></i> 保存
								</button>
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