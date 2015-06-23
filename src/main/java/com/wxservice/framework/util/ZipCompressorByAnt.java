package com.wxservice.framework.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

public class ZipCompressorByAnt {

	private File zipFile;

	public ZipCompressorByAnt(String pathName) {
		zipFile = new File(pathName);
	}

	public void compress(String srcPathName) {
		this.compress(srcPathName, null, null);
	}

	public void compress(String srcPathName, List includes, List excludes) {
		File srcdir = new File(srcPathName);
		if (!srcdir.exists())
			throw new RuntimeException(srcPathName + "不存在！");

		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(srcdir);
		// fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹
		// eg:zip.setIncludes("*.java");
		// fileSet.setExcludes(...); 排除哪些文件或文件夹
		if (includes != null) {
			for (Object o : includes) {
				String s = (String) o;
				fileSet.setIncludes(s);
			}
		}
		if (excludes != null) {
			for (Object o : excludes) {
				String s = (String) o;
				fileSet.setExcludes(s);
			}
		}
		zip.addFileset(fileSet);

		zip.execute();
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		String filename="c:"+SysUtil._systemUpdateFile;
		zipUcard(filename);
	}

	public static void zipUcard(String filename) {
		System.out.println("正在生成升级文件：");
		ZipCompressorByAnt zca = new ZipCompressorByAnt(filename);
		List excludes=new ArrayList();
		excludes.add("index.jsp");
		excludes.add("ucard-license.dat");
		excludes.add("posindex.jsp");
		excludes.add("**/*svn*");
		excludes.add("**/styles/**");
		excludes.add("**/log/**");
		excludes.add("**/bin/**");
		excludes.add("**/images/**");
		excludes.add("**/pic/**");
		excludes.add("**/picture/**");
		excludes.add("**/picture-user/**");
		excludes.add("sandbox/**");
		excludes.add("**/widgets/**");
		excludes.add("**/uploads/**");
		excludes.add("**/download/**");
		excludes.add("**/WEB-INF/lib/**");
		excludes.add("**/WEB-INF/tld/c.tld");
		excludes.add("**/WEB-INF/tld/extremecomponents.tld");
		excludes.add("**/WEB-INF/tld/struts-bean.tld");
		excludes.add("**/WEB-INF/tld/struts-html.tld");
		excludes.add("**/WEB-INF/tld/struts-logic.tld");
		excludes.add("**/WEB-INF/report/**");
		//excludes.add("**/download/**");
		excludes.add("**/tmp/**");
		excludes.add("**/WEB-INF/classes/config/**");
		//excludes.add("**/WEB-INF/classes/ehcache.xml");
		//excludes.add("**/WEB-INF/classes/spring/datasource.xml");
		excludes.add("**/WEB-INF/classes/log4j.properties");
		excludes.add("**/WEB-INF/*.bak");
		List includes=new ArrayList();
		String path=System.getProperty("user.dir")+"\\src\\main\\webapp";
		String libpath=System.getProperty("user.dir")+"\\src\\main\\webapp\\WEB-INF\\lib";
		File root = new File(libpath);
	    File[] files = root.listFiles();
	    for(int i=0;i<files.length;i++){
	    	File file=(File)files[i];
	    	String libfilename=file.getName();
	    	//phprpc.jar phprpc_client.jar
	    	//if(libfilename.indexOf("phprpc")<0 && libfilename.indexOf("phprpc_client")<0){
	    	//   excludes.add("**/WEB-INF/lib/"+libfilename);
	    	//}
	    }
		System.out.println("压缩路径为："+path);
		zca.compress(path,includes,excludes);
		System.out.println("生成升级文件成功:"+filename);
		
	}
	
	
	
	

}
