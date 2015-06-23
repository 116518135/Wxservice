package com.wxservice.framework.components.mail;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.TitleTag;

/**
 * 常用的HTML标签过滤
 * 
 * @author mzhanker
 */
public class HtmlNodeFilters {

	/**
	 * 用于提取页面的图像  
	 * 
	 */
	public final static NodeFilter imageFilter = new NodeFilter() {
		public boolean accept(Node node) {
			return (node instanceof ImageTag);
		}
	};
	/**
	 * 用于提取页面的表格
	 * 
	 */
	public final static NodeFilter tableFilter = new NodeFilter() {
		public boolean accept(Node node) {
			return (node instanceof TableTag);
		}
	};
	/**
	 * 用于提取页面的标题
	 * 
	 */
	public final static NodeFilter titleFilter = new NodeFilter() {
		public boolean accept(Node node) {
			return (node instanceof TitleTag);
		}
	};
}
