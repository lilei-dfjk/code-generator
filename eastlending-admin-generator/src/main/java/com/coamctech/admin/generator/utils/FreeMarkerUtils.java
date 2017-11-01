package com.coamctech.admin.generator.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.io.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class FreeMarkerUtils {

	private static Configuration config = new Configuration(
			Configuration.VERSION_2_3_23);

	static {
		try {
			String ftlpath = FreeMarkerUtils.class.getResource("/").getPath() + "ftl";
			config.setDirectoryForTemplateLoading(new File(ftlpath)); // freemarker模板所在目录
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 合并模板和数据模型
	 * 
	 * @param template
	 *            src/main/resources/ftl下的文件名
	 * @param dataModel
	 * @return
	 */
	public static String combineTemplateWithDataModel(String template,
			Object dataModel, String fileName) {
		try {
			FileOutputStream fos = FileUtils.openOutputStream(new File(fileName));
			Writer w = new OutputStreamWriter(fos);
			Template tpl = config.getTemplate(template, "UTF-8");
			tpl.process(dataModel, w);
			w.flush();
			w.close();
			return w.toString();
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		return "";
	}

}
