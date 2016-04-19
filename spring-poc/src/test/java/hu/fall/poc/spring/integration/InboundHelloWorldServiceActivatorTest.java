package hu.fall.poc.spring.integration;

import org.testng.annotations.Test;

@Test
public class InboundHelloWorldServiceActivatorTest extends BaseIntegrationTest {

	@Override
	protected String getConfigFile() {
		return "spring/integration/service-activator.xml";
	}

	@Override
	@Test(enabled=false)
	protected void testContent() throws Exception
	{
		sendTextMessage("helloWorldDestination", "any kind of text");
	}
}
