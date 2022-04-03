package com.demo.amqproducer.route;

import com.demo.amqproducer.model.SampleMessage;
import com.demo.amqproducer.model.SampleResponse;
import com.demo.amqproducer.processor.SampleResponseProcessor;
import org.apache.camel.ExchangePattern;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteBuilder extends org.apache.camel.builder.RouteBuilder {


    JacksonDataFormat stringFormat = new JacksonDataFormat(SampleMessage.class);
    JacksonDataFormat jsonFormat = new JacksonDataFormat(SampleResponse.class);

    @Override
    public void configure() {
        from("direct:requestMessage")
                .marshal(stringFormat)
                .to("activemq:queue:sendMessageQueue").setExchangePattern(ExchangePattern.InOut)
                .unmarshal(jsonFormat)
                .log("${body}")
                .end();

    }
}
