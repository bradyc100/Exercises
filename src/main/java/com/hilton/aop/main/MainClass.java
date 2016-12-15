package com.hilton.aop.main;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hilton.aop.bo.TestBO;
import com.hilton.aop.interceptors.TestInterceptor;

public class MainClass {
	
	public static void main(String[] args) {
		ApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("beans.xml");
			TestBO bo = (TestBO)context.getBean("testBOImpl");
			ProxyFactory pf = new ProxyFactory(bo);
			pf.addAdvice(new TestInterceptor());
			
			TestBO proxyBO = (TestBO) pf.getProxy();
			proxyBO.calculate('.', 4.5, 6.5, 10.1, 9.999999);
		} catch (Exception e) {
			System.out.println("Exception message: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if(context != null) {
				((ConfigurableApplicationContext)context).close();
			}
		}
	}
}
