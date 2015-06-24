package com.wxservice.framework.generator.util;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityParser {
	VelocityContext mainContext;

	Template mainTemplate;

	HashMap properties;
	
	boolean overwrite=false;

	public VelocityParser() {
		mainContext = null;
		mainTemplate = null;
		properties = new HashMap();
		properties.put("input.encoding", "UTF-8");
		properties.put("output.encoding", "UTF-8");
	}

	public void addProperty(String key, String value) {
		properties.put(key, value);
	}

	public void addToContext(String key, Object value) {
		if (mainContext == null)
			mainContext = new VelocityContext();
		mainContext.put(key, value);
	}

	public void addToContext(VelocityContext chainCtx) {
		mainContext = new VelocityContext(chainCtx);
	}

	public VelocityContext getCurrentContext() {
		return mainContext;
	}

	public void processTemplate(String filename) throws Exception {


		// Writer writer = new FileWriter(new File(filename));
		StringWriter writer = new StringWriter();
		if (mainTemplate != null)
			mainTemplate.merge(mainContext, writer);
		StringBuffer buf = writer.getBuffer();
		FileHelper.saveToFile(filename, buf.toString(),this.overwrite);
		// writer.close();

	}

	public void parseVelocity(String templateFile) throws Exception {
		try {
			String key;
			for (Iterator it = properties.keySet().iterator(); it.hasNext(); Velocity
					.setProperty(key, properties.get(key)))
				key = (String) it.next();

			Velocity.init();
			mainTemplate = Velocity.getTemplate(templateFile);
		} catch (Exception ex) {
			System.out.println("Error processing template file: "
					+ templateFile);
			ex.printStackTrace();
			throw ex;
		}
	}

	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}
}
