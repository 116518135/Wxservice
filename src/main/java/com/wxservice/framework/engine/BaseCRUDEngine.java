package com.wxservice.framework.engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.wxservice.framework.exception.BusinessException;
import com.wxservice.framework.util.StringUtil;
import com.wxservice.framework.util.SystemLogger;
import com.wxservice.framework.web.action.ActionContext;

public abstract class BaseCRUDEngine extends BaseEngine {
	public static final String NOEXISTRECORD="当前记录不存在";
	protected abstract Class getEntityClass();

	protected abstract String getTableKeyField();
	
	

	/**
	 * 取得主键参数
	 * 
	 * @param context
	 * @return
	 */
	protected Serializable getEntityId(ActionContext context) {
		try {
			Serializable id = (Serializable) PropertyUtils.getSimpleProperty(
					context.getForm(), this.getTableKeyField());
			return id;
		} catch (Exception e) {
			throw new IllegalArgumentException("取实体主键值发生错误......", e);
		}
	}

	/**
	 * 取主键值，用于写log
	 * 
	 * @param entity
	 * @return
	 */
	protected String getIdValue(Object entity) {
		String field = this.getTableKeyField();
		String value = "";
		if (entity != null) {
			try {
				value = (String) BeanUtils.getProperty(entity, field);
			} catch (Exception e) {
				SystemLogger.error("取主键值发生错误", e);
			}
		}
		return value;
	}

	/**
	 * 处理操作失败的时候情形
	 * 
	 * @param context
	 * @param e
	 * @param actionName
	 * @param isWriteRequest
	 * @param entity
	 */
	protected void processFailure(ActionContext context, Exception e,
			boolean isWriteRequest, Object entity) {
		String pkid = this.getIdValue(entity);
		this.processFailure(context, e, isWriteRequest, pkid);
	}

	/**
	 * 处理操作成功的时候的情形
	 * 
	 * @param context
	 * @param actionName
	 * @param isWriteRequest
	 * @param entity
	 */
	protected void processSuccess(ActionContext context,
			boolean isWriteRequest, Object entity) {
		String pkid = this.getIdValue(entity);
		this.processSuccess(context, isWriteRequest, pkid);
	}

	/**
	 * 生成一个实例
	 * 
	 * @return
	 * @throws Exception
	 */
	public Object createEntity() throws Exception {
		Object object = null;
		object = getEntityClass().newInstance();
		return object;
	}

	/**
	 * 判断是否唯一
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public boolean isUniques(Object entity) throws Exception {
		return dao.isUnique(this.getEntityClass(), entity);
	}

    /**
     * 删除前的判断 在spring中配置.配置格式如下 
     * <property name="scriptBeforDelete"> <map> <entry
	 * key="aa"> <list> <value> select 1 from ttt as a
	 * where tttid </value> </list> </entry> <map> </property>
     * @param setKey
     * @param value
     * @param otherConidtion
     * @return
     */
	public boolean isCanDelete(String setKey,String value,
			String otherConidtion) throws Exception{
		boolean result = true;
		try {
			if(serviceConfig.getScriptBeforeDelete()==null) return result;
			List scriptList = (ArrayList) serviceConfig.getScriptBeforeDelete()
					.get(setKey);
			if (scriptList == null || scriptList.size()==0) {
				return true;
			}
			StringBuffer hsql = new StringBuffer();
			if (scriptList != null && scriptList.size() > 0) {
				for (int i = 0; i < scriptList.size(); i++) {
					String sql = scriptList.get(i).toString();
					hsql.setLength(0);
					hsql.append(sql);
					if(sql.indexOf("where")<0){
						hsql.append(" where 1=1 ");
					}
					if(StringUtil.isNotBlank(value)){
					  hsql.append("=");
					  hsql.append(value);
					}
					if (StringUtils.isNotBlank(otherConidtion)) {
						hsql.append(" and ");
						hsql.append(otherConidtion);
					}
					SystemLogger.debug(hsql.toString());
					List lt = dao.getJdbcManager()
							.queryForList(hsql.toString());
					if (lt != null && lt.size() > 0) {
						result = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			BusinessException be = new BusinessException("配置删除检查的脚本有错误", e);
			throw be;
		}
		return result;
	}
}
