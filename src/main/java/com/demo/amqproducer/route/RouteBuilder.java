package com.demo.amqproducer.route;

import com.demo.amqproducer.model.SampleMessage;
import com.demo.amqproducer.model.SampleResponse;
import org.apache.camel.ExchangePattern;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class RouteBuilder extends org.apache.camel.builder.RouteBuilder {

    JacksonDataFormat stringFormat = new JacksonDataFormat(SampleMessage.class);
    JacksonDataFormat jsonFormat = new JacksonDataFormat(SampleResponse.class);

    @Override
    public void configure() {
        from("direct:requestMessage").routeId("producerRoute")
                .marshal(stringFormat)
                .to("activemq:queue:sendMessageQueue").setExchangePattern(ExchangePattern.InOut).id("consumerID")
                .unmarshal(jsonFormat)
                .log("${body}")
                .end();

    }
}
