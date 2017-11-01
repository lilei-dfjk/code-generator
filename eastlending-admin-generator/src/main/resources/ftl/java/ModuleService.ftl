package ${packagePre}.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ${packagePre}.dao.jpa.entity.${className};

public interface ${className}Service {

	Page<${className}> find${className}List(${className} ${moduleCode}, Integer pageNumber, Integer pageSize);

	${className} save${className}(${className} ${moduleCode});
	
	${className} edit${className}(${className} ${moduleCode});

	void del${className}(String id);

	${className} findById(String id);
	
	public List<${className}> findAll();
	
}
