package com.wxservice.framework.start;

import org.springframework.util.Assert;


public class ThreadLocalSecurityContextHolderStrategy {

	public ThreadLocalSecurityContextHolderStrategy() {
	}

	public void clearContext() {
		contextHolder.set(null);
	}

	public SysSecurityContext getContext() {
		if (contextHolder.get() == null)
			contextHolder.set(new SysSecurityContext()); //取不到，默认一个空的MySecurityContext
		return (SysSecurityContext) contextHolder.get();
	}

	public void setContext(SysSecurityContext context) {
		Assert.notNull(context,
				"Only non-null SecurityContext instances are permitted");
		contextHolder.set(context);
	}

	private static ThreadLocal contextHolder = new ThreadLocal();

}
