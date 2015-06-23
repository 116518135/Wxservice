package com.wxservice.framework.web.tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.Preferences;
import org.extremecomponents.table.core.TableProperties;

import com.wxservice.framework.util.MathUtil;

public class PageTableProperties implements Preferences {
	private static Log logger = LogFactory.getLog(TableProperties.class);

	public final static String EXTREMECOMPONENTS_PROPERTIES = "extremecomponents.properties";
	public final static String EXTREMETABLE_PROPERTIES = "extremetable.properties";

	private static Properties properties = null;
	public static int rowDisplayed = 0;

	public void init(Context context, String preferencesLocation) {
		try {
			if (properties == null || properties.size() == 0) {
				properties = new Properties();
				properties.load(TableProperties.class
						.getResourceAsStream(EXTREMETABLE_PROPERTIES));
				if (StringUtils.isNotBlank(preferencesLocation)) {
					InputStream input = TableProperties.class
							.getResourceAsStream(preferencesLocation);
					if (input != null) {
						properties.load(input);
					}
				}
			}
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Could not load the eXtremeTable preferences.", e);
			}
		}
	}

	/**
	 * Get the default property.
	 */
	public String getPreference(String name) {
		return (String) properties.get(name);
	}

	public static int getRowdisplayed() {
		try {
			if (rowDisplayed == 0) {
				Properties te = new Properties();
				te.load(TableProperties.class
						.getResourceAsStream("/config/extremetable.properties"));
				String str=(String) te.get("table.rowsDisplayed");
				rowDisplayed=MathUtil.parsetInt(str, 15);
			}
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Could not load the eXtremeTable preferences.", e);
			}
		}
		return rowDisplayed;
	}
}
