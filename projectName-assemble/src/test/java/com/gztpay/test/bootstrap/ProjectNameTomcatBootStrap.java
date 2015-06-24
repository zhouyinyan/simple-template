/**
 * created since 2012-5-23
 */
package com.gztpay.test.bootstrap;


import com.gztpay.test.bootstrap.tomcat.TomcatBootstrapHelper;

/**
 * @author jlcon
 * @version $Id: ProjectNameTomcatBootStrap.java,v 2.0 2015-6-23 下午6:12:16 JLCON Exp $
 */
public class ProjectNameTomcatBootStrap {
 
	public static void main(String[] args) throws Exception {
		new TomcatBootstrapHelper(8080,false, "test").start();
	}
}
