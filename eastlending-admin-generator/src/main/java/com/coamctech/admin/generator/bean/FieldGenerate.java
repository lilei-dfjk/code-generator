package com.coamctech.admin.generator.bean;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.coamctech.admin.generator.utils.CommonUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldGenerate {
	
	private String code; //字段代码
	private String name; //字段名称
	private String fieldType; //字段类型
	private Map<String, String> enumMap; //枚举类
	private boolean nullable; //是否可为空
	private boolean condition; //是否是查询条件
	private boolean queryable; //是否是查询显示列
	private boolean primaryKey; //是否主键
	private Integer orderBy; //是否是排序字段 -1表示非排序字段
	private Integer length; //字段长度
	private int index; //0,1表示字段奇偶数 2表示奇数且为最后一列，3表示偶数为最后一列
	@SuppressWarnings("unused")
	private String codeForFirstLetterToUpper; //字段名首字母大写
	@SuppressWarnings("unused")
	private String databaseColumn; //字段对应数据库代码
	
	public String getDatabaseColumn() {
		return CommonUtils.camelToUnderline(code);
	}
	
	public String getCodeForFirstLetterToUpper() {
		return CommonUtils.FirstToUpper(code);
	}

	public FieldGenerate(String code, String name, String fieldType,
			Map<String, String> enumMap, boolean nullable, int index) {
		super();
		this.code = code;
		this.name = name;
		this.fieldType = fieldType;
		this.enumMap = enumMap;
		this.nullable = nullable;
		this.index = index;
	}
	
	
	

}
