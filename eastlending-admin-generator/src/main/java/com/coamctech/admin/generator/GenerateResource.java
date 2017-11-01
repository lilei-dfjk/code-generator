package com.coamctech.admin.generator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.coamctech.admin.generator.bean.FieldGenerate;
import com.coamctech.admin.generator.bean.ModelProperty;
import com.coamctech.admin.generator.utils.CommonUtils;
import com.coamctech.admin.generator.utils.GenerateAdminResourceUtils;
import com.google.common.collect.ImmutableMap;

public class GenerateResource {

	private String tableName; // 数据库表名称
	private ModelProperty modelProperty; // 设置信息
	private List<FieldGenerate> fieldList; // 字段集合
	private Set<String> importsPackage; // 生成实体需要导入的包

	private String moduleCode; // 模块代码
	private String controllerUrl; // controller路径
	private String targetUrl; // 生成代码位置
	private String className; // 生成类名称
	private String packagePre; // 包前缀

	List<FieldGenerate> conditionFieldList = null; // 首页查询条件展示字段
	List<FieldGenerate> columnFieldList = null; // 首页列表展示字段
	List<FieldGenerate> editFieldList = null; // 增加修改展示字段
	String orderBy = ""; // 排序

	public GenerateResource(String tableName, ModelProperty modelProperty,
			List<FieldGenerate> fieldList, Set<String> importsPackage)
			throws Exception {
		this.tableName = tableName;
		this.modelProperty = modelProperty;
		this.fieldList = fieldList;
		this.init();
	}

	public void init() throws Exception {
		className = CommonUtils.getClassNameByTableName(tableName);
		moduleCode = CommonUtils.FirstToLower(className);
		packagePre = modelProperty.getPackagePre();
		controllerUrl = CommonUtils.camelToMiddleline(moduleCode);
		targetUrl = modelProperty.getOutputPath() + "/";
		targetUrl += controllerUrl;

		editFieldList = new ArrayList<FieldGenerate>(fieldList);
		conditionFieldList = new ArrayList<>();
		columnFieldList = new ArrayList<>();
		
		List<FieldGenerate> ordeyByFields = new ArrayList<FieldGenerate>();

		for (FieldGenerate field : fieldList) {
			field.setCode(CommonUtils.underlineToCamel(field.getCode()));
			if (field.getFieldType().contains(".")) {
				Class<?> cla = Class.forName(field.getFieldType());
				field.setFieldType(cla.getSimpleName());
			}
			
			if (field.isCondition()) {
				FieldGenerate fieldGenerate = new FieldGenerate(); 
				BeanUtils.copyProperties(fieldGenerate, field);
				conditionFieldList.add(fieldGenerate);
			}
			if (field.isQueryable()) {
				FieldGenerate fieldGenerate = new FieldGenerate(); 
				BeanUtils.copyProperties(fieldGenerate, field);
				columnFieldList.add(fieldGenerate);
			}
			if (field.getOrderBy() != -1) {
				FieldGenerate fieldGenerate = new FieldGenerate(); 
				BeanUtils.copyProperties(fieldGenerate, field);
				ordeyByFields.add(fieldGenerate);
			}
			

		}

		// 获取首页查询条件字段
		conditionFieldList = getFieldList(conditionFieldList);
		// 获取首页列表显示字段
		columnFieldList = getFieldList(columnFieldList);
		// 获取增加和查看页面的展示字段
		editFieldList = getEditFieldList(editFieldList);
		// 获取排序--默认desc
		orderBy = getOrderBy(ordeyByFields);
	}

	public void generateAdminResource() {
		Map<String, String> baseMap = ImmutableMap.of("moduleCode", moduleCode,
				"className", className, "packagePre", packagePre,
				"controllerUrl", controllerUrl);

		Map<String, Object> indexMap = new HashMap<String, Object>(baseMap);
		indexMap.put("conditionFieldList", conditionFieldList);
		indexMap.put("columnFieldList", columnFieldList);
		indexMap.put("orderBy", orderBy);

		Map<String, Object> curdMap = new HashMap<String, Object>(baseMap);
		curdMap.put("fieldList", editFieldList);

		Map<String, Object> entityMap = new HashMap<String, Object>(baseMap);
		entityMap.put("tableName", tableName);
		entityMap.put("fieldsList", fieldList);
		entityMap.put("importsPackage", importsPackage);

		GenerateAdminResourceUtils.generateJavaEntityResources(entityMap, targetUrl);
		GenerateAdminResourceUtils.generateJavaResources(indexMap, targetUrl);
		GenerateAdminResourceUtils.generateIndexResources(indexMap, targetUrl);
		GenerateAdminResourceUtils.generateCurdResource(curdMap, targetUrl);
	}

	private List<FieldGenerate> getEditFieldList(List<FieldGenerate> fields) {
		if (null != fields && fields.size() > 0) {
			for (FieldGenerate field : fields) {
				if (field.isPrimaryKey()) {
					fields.remove(field);
					break;
				}
			}
			getFieldList(fields);
		}
		return fields;
	}

	private List<FieldGenerate> getFieldList(List<FieldGenerate> fields) {
		if (null != fields && fields.size() > 0) {
			for (int i = 0; i < fields.size(); i++) {
				FieldGenerate field = fields.get(i);
				int index = i % 2;
				if (i == fields.size() - 1) {
					if (fields.size() % 2 == 1) {
						index = 2;// 当index为2时说明，查询字段为奇数，且是最后一个字段
					} else {
						index = 3;// 当index为3时说明，查询字段为偶数，且是最后一个字段
					}
				}
				field.setIndex(index);
			}
		}
		return fields;
	}

	private String getOrderBy(List<FieldGenerate> fields) {

		StringBuffer orderBy = new StringBuffer();

		if (null != fields && fields.size() > 0) {
			orderBy.append("order by ");
			Comparator<FieldGenerate> fieldForOrderBy = (f1, f2) -> f1
					.getOrderBy().compareTo(f2.getOrderBy());
			fields.sort(fieldForOrderBy);

			int i = 0;
			for (FieldGenerate field : fields) {
				orderBy.append(field.getDatabaseColumn());
				if (++i != fields.size()) {
					orderBy.append(", ");
				} else {
					orderBy.append(" desc");
				}
			}
		}

		return orderBy.toString();
	}

}
