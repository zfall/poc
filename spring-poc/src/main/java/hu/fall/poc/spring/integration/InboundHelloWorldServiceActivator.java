package hu.fall.poc.spring.integration;

import org.springframework.messaging.Message;

public class InboundHelloWorldServiceActivator {

	public void handleMessage(Message<String> message)
	{
		for (String headerKey : message.getHeaders().keySet()) {
			System.out.println("Header: "+headerKey+"="+message.getHeaders().get(headerKey));
		}
		System.out.println("Payload: "+message.getPayload());
	}
}
