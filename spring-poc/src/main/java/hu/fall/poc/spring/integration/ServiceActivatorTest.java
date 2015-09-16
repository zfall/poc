package hu.fall.poc.spring.integration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceActivatorTest {
	public static void main(String[] args) throws Exception{
		String config = "spring/integration/service-activator.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
		context.start();

		ConnectionFactory connectionFactory = (ConnectionFactory)context.getBean("connectionFactory");
		Connection connection = connectionFactory.createConnection();
		try {
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			try {
				TextMessage textMessage = session.createTextMessage("any kind of text");
				Queue helloWorldDestination = session.createQueue("helloWorldDestination");
				MessageProducer messageProducer = session.createProducer(helloWorldDestination);
				messageProducer.send(textMessage);
			} finally {
				session.close();
			}
		} finally {
			connection.close();
		}

		Thread.sleep(10000);
		context.close();
	}
}
