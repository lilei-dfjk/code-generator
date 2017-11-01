package com.coamctech.admin.generator.utils;

import java.util.Map;

/**
 * 生成Admin代码工具类.<br/>
 * 
 * 
 * @author lilei
 * @lastModified 2016-7-15 20:06:34
 */
public abstract class GenerateAdminResourceUtils {
	
	private final static String FTL_CONTROLLER_JAVA  = "java/ModuleController.ftl";
	private final static String FTL_SERVICE_JAVA     = "java/ModuleService.ftl";
	private final static String FTL_SERVICEIMPL_JAVA = "java/ModuleServiceImpl.ftl";
	private final static String FTL_REPOSITORY_JAVA  = "java/ModuleRepository.ftl";
	private final static String FTL_ENTITY_JAVA      = "java/ModuleEntity.ftl";
	
	private final static String FTL_INDEX_JSP        = "jsp/index.jsp.ftl";
	private final static String FTL_INDEX_VIEW_JS    = "js/index/view.js.ftl";
	private final static String FTL_INDEX_MAIN_JS    = "js/index/main.js.ftl";
	private final static String FTL_INDEX_MODEL_JS   = "js/index/model.js.ftl";
	private final static String FTL_INDEX_RM_JS      = "js/index/rm.js.ftl";
	
	private final static String FTL_ADD_EDIT_JSP     = "jsp/add-edit.jsp.ftl";
	private final static String FTL_VIEW_JSP         = "jsp/view.jsp.ftl";
	private final static String FTL_CURD_VIEW_JS     = "js/curd/view.js.ftl";
	private final static String FTL_CURD_MAIN_JS     = "js/curd/main.js.ftl";
	private final static String FTL_CURD_MODEL_JS    = "js/curd/model.js.ftl";
	private final static String FTL_CURD_RM_JS       = "js/curd/rm.js.ftl";
	
	private final static String JSPDIR               = "/jsp/";
	private final static String JAVADIR              = "/java/";
	private final static String JS_INDEX             = "/js/index/";
	private final static String JS_CURD              = "/js/curd/";
	
	/**
	 * 生成JPA实体
	 * @param columns
	 * @param targetUrl
	 */
	public static void generateJavaEntityResources(Map<String, Object> entityMap, String targetUrl) {
		String entityDir = targetUrl + JAVADIR + entityMap.get("className");
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_ENTITY_JAVA, entityMap, entityDir + ".java");
	}
	
	/**
	 * 生成Controller
	 * @param controllerMap
	 * @param targetUrl
	 */
	public static void generateJavaControllerResources(Map<String, Object> controllerMap, String targetUrl) {
		String controllerDir = targetUrl + JAVADIR + controllerMap.get("className");
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_CONTROLLER_JAVA, controllerMap, controllerDir + "Controller.java");
	}
	
	/**
	 * 生成Service
	 * @param map
	 * @param targetUrl
	 */
	public static void generateJavaServiceResources(Map<String, Object> serviceMap, String targetUrl) {
		String serviceDir = targetUrl + JAVADIR + serviceMap.get("className");
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_SERVICE_JAVA, serviceMap, serviceDir + "Service.java");
	}
	
	/**
	 * 生成ServiceImpl
	 * @param map
	 * @param targetUrl
	 */
	public static void generateJavaServiceImplResources(Map<String, Object> serviceIpmlMap, String targetUrl) {
		String serviceImplDir = targetUrl + JAVADIR + serviceIpmlMap.get("className");
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_SERVICEIMPL_JAVA, serviceIpmlMap, serviceImplDir + "ServiceImpl.java");
	}
	
	/**
	 * 生成Repository
	 * @param map
	 * @param targetUrl
	 */
	public static void generateJavaRepositoryResources(Map<String, Object> repositoryMap, String targetUrl) {
		String javaDir = targetUrl + JAVADIR + repositoryMap.get("className");
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_SERVICEIMPL_JAVA, repositoryMap, javaDir + "Repository.java");//生成service.impl
	}
	
	/**
	 * 生成java相关资源
	 * @param javaMap
	 * @param targetUrl
	 */
	public static void generateJavaResources(Map<String, Object> javaMap, String targetUrl) {
		String javaDir = targetUrl + JAVADIR + javaMap.get("className");
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_CONTROLLER_JAVA, javaMap, javaDir + "Controller.java");//生成controller
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_SERVICE_JAVA, javaMap, javaDir + "Service.java");//生成service
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_SERVICEIMPL_JAVA, javaMap, javaDir + "ServiceImpl.java");//生成service.impl
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_REPOSITORY_JAVA, javaMap, javaDir + "Repository.java");//生成Repository
	}
	
	
	/**
	 * 生成主页资源
	 * @param targetUrl
	 * @param indexMap
	 */
	public static void generateIndexResources(Map<String, Object> indexMap,	String targetUrl) {
		String jsDir = targetUrl + JS_INDEX;
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_INDEX_JSP, indexMap,  targetUrl + JSPDIR + "index.jsp");
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_INDEX_MAIN_JS, indexMap, jsDir + "main.js");//生成main.js
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_INDEX_MODEL_JS,indexMap, jsDir + "model.js");//生成model.js
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_INDEX_RM_JS, indexMap,  jsDir + "rm.js");//生成rm.js
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_INDEX_VIEW_JS, indexMap, jsDir + "view.js");//生成view.js
	}
	
	/**
	 * 生成查看页面和修改页面的资源
	 * @param targetUrl
	 * @param curdMap
	 */
	public static void generateCurdResource(Map<String, Object> curdMap, String targetUrl) {
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_VIEW_JSP, curdMap,  targetUrl + JSPDIR + "view.jsp");
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_ADD_EDIT_JSP, curdMap,  targetUrl + JSPDIR + "add-edit.jsp");
		generateCurdResourceJs(curdMap, targetUrl);
	}
	
	/**
	 * 生成curd js资源
	 * @param curdMap
	 * @param targetUrl
	 */
	private static void generateCurdResourceJs(Map<String, Object> curdMap, String targetUrl) {
		String curdJsDir = targetUrl + JS_CURD;
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_CURD_VIEW_JS, curdMap,  curdJsDir + "view.js");//生成view.js
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_CURD_MODEL_JS, curdMap,  curdJsDir + "model.js");//生成model.js
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_CURD_MAIN_JS, curdMap, curdJsDir + "main.js");//生成main.js
		FreeMarkerUtils.combineTemplateWithDataModel(FTL_CURD_RM_JS, curdMap,  curdJsDir + "rm.js");//生成rm.js
	}
	
}
