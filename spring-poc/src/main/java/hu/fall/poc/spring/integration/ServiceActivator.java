package hu.fall.poc.spring.integration;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceActivator {
	public static void main(String[] args) {
		String config = "spring/integration/service-activator.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
		context.start();

		TestBean testBean = (TestBean)context.getBean("testBean");
		System.out.println(testBean.append("aaa", "bbb"));
		
		context.close();
	}
}
