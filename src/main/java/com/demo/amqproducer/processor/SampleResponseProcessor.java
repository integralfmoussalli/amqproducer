package com.demo.amqproducer.processor;

import com.demo.amqproducer.model.SampleResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SampleResponseProcessor implements Processor {

    @Override
    public void process(Exchange exchange)  {

        SampleResponse sampleResponse = exchange.getIn().getBody(SampleResponse.class);
    }
}
