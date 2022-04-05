package com.demo.amqproducer;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.apache.camel.builder.Builder.constant;

@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest
class AmqproducerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	 ProducerTemplate producerTemplate;

	@Autowired
	private CamelContext camelContext;

	@EndpointInject("mock:test")
	MockEndpoint mockEndpoint;

	@Value("classpath:sampleResponse.json")
	Resource testResponse;

	@Value("classpath:sampleMessage.json")
	Resource testMessage;

	@BeforeTestClass
	public void setMockEndpoint() throws Exception {

	String response = FileUtils.readFileToString(testResponse.getFile(), Charset.defaultCharset());

		AdviceWith.adviceWith(camelContext,
				"producerRoute",
				a -> a.weaveById("consumerID")
						.replace()
						.to(mockEndpoint)
						.setBody(constant(response))

			);
	}

	@Test
	public void sendProducerTest() throws IOException {
		String message = FileUtils.readFileToString(testMessage.getFile(), Charset.defaultCharset());
		producerTemplate.sendBody("direct:requestMessage", message);
		mockEndpoint.expectedBodiesReceived(1);
	}







}
