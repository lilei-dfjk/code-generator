<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ include file="../../commons/res/taglibs.jsp" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="org.apache.commons.lang3.time.DateUtils"%>

<!-- ajax layout which only needs content area -->
<div class="ajax-content">
	<div class="page-header">
		<h1>
			管理
			<small>
				<i class="ace-icon fa fa-angle-double-right"></i>维护
				<span class="pull-right">
					<%-- <shiro:hasPermission name="${controllerUrl}:mng:add"> --%>
						<a id="btn-add" class="btn btn-sm btn-success"><i class="ace-icon fa fa-plus"></i> 新增</a>
					<%-- </shiro:hasPermission> --%>
				</span>
			</small>
		</h1>
	</div><!-- /.page-header -->
	
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->
			<div class="row">
				<div class="col-xs-12">
					<form id="condition-form" class="form-horizontal" method="post">
						<h4 class="header blue bolder smaller">查询条件</h4>
						<#if conditionFieldList?exists>
						   <#list conditionFieldList as field> 
						    	<#if field.index == 0>
						    		<div class="form-group">
							    		<label class="col-sm-2 control-label no-padding-right">${field.name}：</label>
										<label class="col-sm-4">
											<span class="col-sm-8">
												<#if field.fieldType == 'Enum'>
												<select id="${field.code}" name="${field.code}" class="col-sm-10">
													<option value="">请选择</option>
													<#list field.enumMap?keys as key >
														<option value="${key}">${field.enumMap[key]}</option>
													</#list>
												</select>	
												</#if>
												<#if field.fieldType != 'Enum'>
												<input type="text" id="${field.code}" name="${field.code}" class="form-control" />
												</#if>
											</span>
										</label>
						  	    </#if>
						    	<#if field.index == 1 || field.index == 3>
							    		<label class="col-sm-2 control-label no-padding-right">${field.name}：</label>
										<label class="col-sm-4">
											<span class="col-sm-8">
												<#if field.fieldType == 'Enum'>
												<select id="${field.code}" name="${field.code}" class="col-sm-10">
													<option value="">请选择</option>
													<#list field.enumMap?keys as key >
														<option value="${key}">${field.enumMap[key]}</option>
													</#list>
												</select>	
												</#if>
												<#if field.fieldType != 'Enum'>
												<input type="text" id="${field.code}" name="${field.code}" class="form-control" />
												</#if>
											</span>
										</label>
									</div>
						  	    </#if>
						    	<#if field.index == 2>
						    		<div class="form-group">
							    		<label class="col-sm-2 control-label no-padding-right">${field.name}：</label>
										<label class="col-sm-4">
											<span class="col-sm-8">
												<#if field.fieldType == 'Enum'>
												<select id="${field.code}" name="${field.code}" class="col-sm-10">
													<option value="">请选择</option>
													<#list field.enumMap?keys as key >
														<option value="${key}">${field.enumMap[key]}</option>
													</#list>
												</select>	
												</#if>
												<#if field.fieldType != 'Enum'>
												<input type="text" id="${field.code}" name="${field.code}" class="form-control" />
												</#if>
											</span>
										</label>
									</div>
						  	    </#if>
						  </#list>
						</#if>
						<div class="clearfix form-actions">
							<div class="col-md-offset-5 col-md-7">
								<button type="button" id="btn-search" class="btn btn-sm btn-purple"><i class="ace-icon fa fa-search"></i> 查询</button> &nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</div>
					</form>
				
					<h4 class="header blue bolder smaller">查询结果</h4>
					<table id="result-tbl" class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<#if columnFieldList?exists>
									  <#list columnFieldList as field> 
					                 	<th width="10%">${field.name}</th>
					                </#list>
								</#if>
								<th width="10%">操作</th>
							</tr>
						</thead>
				</div>
					</table>
			</div>
		</div>
	</div>
</div>
<script type="x-tmpl-mustache" id="dt-row-operation">
	<div class="btn-group">
		<button type="button" role="edit" data-id="{{id}}" class="btn btn-xs btn-warning" title="修改" {{#cannotEdit}}disabled="disabled"{{/cannotEdit}}><i class="ace-icon fa fa-edit"></i></button>
		<button type="button" role="delete" data-id="{{id}}" class="btn btn-xs btn-danger" title="删除" {{#cannotDelete}}disabled="disabled"{{/cannotDelete}}><i class="ace-icon fa fa-trash-o"></i></button>
		<button type="button" role="view" data-id="{{id}}" class="btn btn-xs btn-info" title="查看"><i class='ace-icon fa fa-eye'></i></button>
	</div>
</script>

<script type="text/javascript">
	ace.load_ajax_scripts([]);
	seajs.use("<#noparse>${my}</#noparse>/js/${controllerUrl}/index/main", function(main) {
		new main();
	});
</script>