package com.wxservice.framework.start;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.wxservice.framework.engine.support.SysConfig;

public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
	protected void convertProperties(Properties props) {
		super.convertProperties(props);
		SysConfig.props.putAll(props);
	}
}
