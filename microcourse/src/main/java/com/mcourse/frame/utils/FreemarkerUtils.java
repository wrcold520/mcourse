package com.mcourse.frame.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mcourse.frame.exception.UtilsException;
import com.mcourse.frame.utils.json.JsonUtils;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerUtils {

	private static final Logger logger = LoggerFactory.getLogger(FreemarkerUtils.class);

	private static final String TAG_TEMPLATE_STRING = "TAG_TemplateString";

	private static String templateDirpath = "src/main/com/mcourse/frame/db/generator/template";

	private static Configuration getFreemarkerCfg() throws IOException {
		Configuration cfg = new Configuration(Configuration.getVersion());
		cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
		cfg.setOutputEncoding(StandardCharsets.UTF_8.name());
		cfg.setEncoding(Locale.CHINA, StandardCharsets.UTF_8.name());
		return cfg;
	}

	/**
	 * 根据模板和数据模型生成文件
	 * 
	 * @param templateDirpath
	 *            模板所在的目录
	 * @param ftl
	 *            模板文件名称
	 * @param dataModel
	 *            数据模型
	 * @param distFilepath
	 *            输出文件的位置
	 */
	public static void renderFile(String templateDirpath, String ftl, Map<String, Object> dataModel,
			String distFilepath) {
		logger.info("渲染文件模板: [ " + ftl + " --> " + distFilepath + " ], dataModel: " + JsonUtils.stringify(dataModel));

		Writer fileWriter = null;
		try {
			Configuration cfg = getFreemarkerCfg();
			cfg.setDirectoryForTemplateLoading(new File(templateDirpath));// 设置模板目录

			Template fmTemplate = cfg.getTemplate(ftl);

			// 如果文件不存在，创建文件
			File distFile = new File(distFilepath);
			FileUtils.createNewFile(distFile);

			fileWriter = new FileWriter(distFile);
			fmTemplate.process(dataModel, fileWriter);
		} catch (Exception e) {
			throw new UtilsException("渲染【文件模板】发生异常", e);
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					throw new UtilsException("渲染【文件模板】关闭fileWriter发生异常！", e);
				}
			}
		}
	}

	public static String renderString(String strTemplate, Map<String, Object> dataModel) {
		StringWriter stringWriter = null;
		String renderResult = null;
		try {
			StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
			stringTemplateLoader.putTemplate(TAG_TEMPLATE_STRING, strTemplate);
			Configuration cfg = getFreemarkerCfg();
			cfg.setTemplateLoader(stringTemplateLoader);
			Template fmTemplate = cfg.getTemplate(TAG_TEMPLATE_STRING);
			stringWriter = new StringWriter();
			fmTemplate.process(dataModel, stringWriter);
		} catch (Exception e) {
			throw new UtilsException("渲染【字符串模板】发生异常", e);
		} finally {
			if (stringWriter != null) {
				try {
					stringWriter.close();
				} catch (IOException e) {
					throw new UtilsException("渲染【字符串模板】关闭fileWriter发生异常！", e);
				}
			}
		}
		return renderResult;
	}

	public static String getTemplateDirpath() {
		return templateDirpath;
	}

	public static void setTemplateDirpath(String templateDirpath) {
		FreemarkerUtils.templateDirpath = templateDirpath;
	}

}
