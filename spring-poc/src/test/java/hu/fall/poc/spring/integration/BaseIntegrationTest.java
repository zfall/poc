package hu.fall.poc.spring.integration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public abstract class BaseIntegrationTest {

	protected ClassPathXmlApplicationContext context;

	@Test
	void defaultTest() throws Exception{
		context = new ClassPathXmlApplicationContext(getConfigFile());
		context.start();

		testContent();

		Thread.sleep(10000);
		context.close();
	}

	protected abstract String getConfigFile();

	protected abstract void testContent() throws Exception;

	protected void sendTextMessage(String queueName, String message) throws Exception {
		ConnectionFactory connectionFactory = (ConnectionFactory)context.getBean("connectionFactory");
		Connection connection = connectionFactory.createConnection();
		try {
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			try {
				TextMessage textMessage = session.createTextMessage(message);
				Queue helloWorldDestination = session.createQueue(queueName);
				MessageProducer messageProducer = session.createProducer(helloWorldDestination);
				messageProducer.send(textMessage);
			} finally {
				session.close();
			}
		} finally {
			connection.close();
		}
	}
}
