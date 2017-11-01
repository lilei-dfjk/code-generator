package com.coamctech.admin.generator.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class ModelProperty implements Serializable {
	static final long serialVersionUID = 1L;
	private String jdbcUrl;
	private String jdbcUser;
	private String jdbcPass;
	private String packagePre; //生成代码包前缀${package-pre} eg:com.coamctech.eastlending.fisp
	private String outputPath; //代码生成路径
	
}
