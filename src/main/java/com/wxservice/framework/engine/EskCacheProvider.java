package com.wxservice.framework.engine;

import java.net.URL;
import java.util.Properties;

import net.sf.ehcache.CacheManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CacheProvider;
import org.hibernate.cache.EhCache;
import org.hibernate.cache.EhCacheProvider;
import org.hibernate.cache.Timestamper;
import org.hibernate.cfg.Environment;
import org.hibernate.util.ConfigHelper;
import org.hibernate.util.StringHelper;

public class EskCacheProvider implements CacheProvider {
	private static final Log log = LogFactory.getLog(EhCacheProvider.class);

	private CacheManager manager;

	public Cache buildCache(String name, Properties properties)
			throws CacheException {
		try {
			net.sf.ehcache.Cache cache = manager.getCache(name);
			if (cache == null) {
				log.warn("Could not find configuration [" + name
						+ "]; using defaults.");
				manager.addCache(name);
				cache = manager.getCache(name);
				log.debug("started EHCache region: " + name);
			}
			return new EhCache(cache);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	/**
	 * Returns the next timestamp.
	 */
	public long nextTimestamp() {
		return Timestamper.next();
	}

	/**
	 * Callback to perform any necessary initialization of the underlying cache
	 * implementation during SessionFactory construction.
	 * 
	 * @param properties
	 *            current configuration settings.
	 */
	public void start(Properties properties) throws CacheException {
		if (manager != null) {
			log
					.warn("Attempt to restart an already started EhCacheProvider. Use sessionFactory.close() "
							+ " between repeated calls to buildSessionFactory. Using previously created EhCacheProvider."
							+ " If this behaviour is required, consider using net.sf.ehcache.hibernate.SingletonEhCacheProvider.");
			return;
		}
		try {
			String configurationResourceName = null;
			if (properties != null) {
				configurationResourceName = (String) properties
						.get(Environment.CACHE_PROVIDER_CONFIG);
			}
			if (StringHelper.isEmpty(configurationResourceName)) {
				manager = new CacheManager();
			} else {
				URL url = loadResource(configurationResourceName);
				manager = new CacheManager(url);
			}
		} catch (net.sf.ehcache.CacheException e) {
			// yukky! Don't you have subclasses for that!
			// TODO race conditions can happen here
			if (e.getMessage().startsWith(
					"Cannot parseConfiguration CacheManager. Attempt to create a new instance of "
							+ "CacheManager using the diskStorePath")) {
				throw new CacheException(
						"Attempt to restart an already started EhCacheProvider. Use sessionFactory.close() "
								+ " between repeated calls to buildSessionFactory. Consider using net.sf.ehcache.hibernate.SingletonEhCacheProvider.",
						e);
			} else {
				throw e;
			}
		}
	}

	private URL loadResource(String configurationResourceName) {
		URL url = ConfigHelper.locateConfig(configurationResourceName);
		if (log.isDebugEnabled()) {
			log.debug("Creating EhCacheProvider from a specified resource: "
					+ configurationResourceName + " Resolved to URL: " + url);
		}
		return url;
	}

	/**
	 * Callback to perform any necessary cleanup of the underlying cache
	 * implementation during SessionFactory.close().
	 */
	public void stop() {
		if (manager != null) {
			manager.shutdown();
			manager = null;
		}
	}

	public boolean isMinimalPutsEnabledByDefault() {
		return false;
	}
}
