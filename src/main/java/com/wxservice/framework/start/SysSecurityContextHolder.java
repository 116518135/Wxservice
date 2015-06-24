package com.wxservice.framework.start;


public class SysSecurityContextHolder {
	private static ThreadLocalSecurityContextHolderStrategy strategy;
	private static int initializeCount = 0;

	static {
		initialize();
	}

	public static void clearContext() {
		strategy.clearContext();
	}

	
	public static SysSecurityContext getContext() {
		return strategy.getContext();
	}


	public static int getInitializeCount() {
		return initializeCount;
	}

	private static void initialize() {

		strategy = new ThreadLocalSecurityContextHolderStrategy();

		initializeCount++;
	}


	public static void setContext(SysSecurityContext context) {
		strategy.setContext(context);
	}

}
