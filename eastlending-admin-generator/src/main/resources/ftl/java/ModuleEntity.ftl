package ${packagePre}.dao.jpa.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
<#if importsPackage?exists>
	<#list importsPackage as package>
import 	${package};
	</#list>
</#if>


@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "${tableName}")
public class ${className} {
<#if fieldsList?exists>
	<#list fieldsList as field>
		<#if field.name !=''>
	/**
	 * ${field.name}</br>
	 */		
		</#if>
		<#if field.primaryKey == true>
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 36)
	private String id;
		</#if>
		<#if field.primaryKey != true>
	@Column(<#if field.length?exists>length = ${field.length},</#if> nullable = ${field.nullable?c})
			<#if field.orderBy != -1>
	@OrderBy("${field.orderBy}")		
			</#if>
	private ${field.fieldType} ${field.code};
	
		</#if>
	</#list>
</#if>
}
