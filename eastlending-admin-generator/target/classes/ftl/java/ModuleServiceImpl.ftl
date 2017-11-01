package ${packagePre}.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${packagePre}.admin.service.${className}Service;
import ${packagePre}.commons.dynamicquery.DynamicQuery;
import ${packagePre}.dao.jpa.entity.${className};
import ${packagePre}.dao.jpa.repository.${className}Repository;

@Transactional
@Service
public class ${className}ServiceImpl implements ${className}Service {

	@Autowired
	private ${className}Repository ${moduleCode}Repository;
	
	@Autowired
	private DynamicQuery dynamicQuery;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public Page<${className}> find${className}List(${className} ${moduleCode}, Integer pageNumber, Integer pageSize) {
		StringBuffer jpql = new StringBuffer("select f from ${className} f where 1=1 ");
		int seqno = 1;
		List<Object> params = new ArrayList<>();
		
<#if conditionFieldList?exists>
	 <#list conditionFieldList as field> 
	 	<#if field.fieldType == "String">
	 	if (StringUtils.isNotEmpty(${moduleCode}.get${field.codeForFirstLetterToUpper}())) {
			jpql.append(" and f.${field.databaseColumn} = ?").append(seqno++);
			params.add(${moduleCode}.get${field.codeForFirstLetterToUpper}());
		}
	 	</#if>
	 	<#if field.fieldType != "String">
	 	if (null != ${moduleCode}.get${field.codeForFirstLetterToUpper}()) {
			jpql.append(" and f.${field.databaseColumn} = ?").append(seqno++);
			params.add(${moduleCode}.get${field.codeForFirstLetterToUpper}());
		}
	 	</#if>
    	  
    </#list>
</#if>
		jpql.append("${orderBy}");
		return dynamicQuery.query(${className}.class, new PageRequest(pageNumber - 1, pageSize), jpql.toString(), params.toArray());
	}


	@Override
	public ${className} save${className}(${className} ${moduleCode}) {
		return ${moduleCode}Repository.save(${moduleCode});
	}
	
	@Override
	public ${className} edit${className}(${className} ${moduleCode}) {
		return ${moduleCode}Repository.save(${moduleCode});
	}

	@Override
	public void del${className}(String id) { 
		${moduleCode}Repository.delete(id);
	}


	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public ${className} findById(String id) {
		return ${moduleCode}Repository.findOne(id);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<${className}> findAll() {
		return (List<${className}>) ${moduleCode}Repository.findAll();
	}

}
