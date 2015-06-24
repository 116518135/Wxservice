package com.wxservice.framework.report.html;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wxservice.database.po.report.Trpreportcondition;
public interface HtmlProcess {
   public static String DEFAULT_WEBPATH="${ctx}";
   public static String DEFAULT_VALUE="${default}";
   public static String DEFAULT_NAME="${name}";

   public static String VAR_KEY_VALUE="$value";
   public abstract String processHtml(Trpreportcondition condition,HttpServletRequest request);
   public abstract Map<String, String> processValue(Trpreportcondition condition,HttpServletRequest request);
}
