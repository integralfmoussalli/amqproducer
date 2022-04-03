package com.demo.amqproducer.controller;

import com.demo.amqproducer.model.SampleMessage;
import com.demo.amqproducer.model.SampleResponse;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//https://camel.apache.org/manual/producertemplate.html
@RestController
public class AMQController {

    @Autowired
     ProducerTemplate producerTemplate;

    @Autowired
    CamelContext camelContext;

    @PostMapping
    public SampleResponse sendMessage (@RequestBody SampleMessage sampleMessage) throws Exception{

        return producerTemplate.requestBody("direct:requestMessage",
                sampleMessage,
                SampleResponse.class);

    }

    @GetMapping(value = "/hello", produces = "application/json")
    public String hello() {
        return "{ \"body\": \"G'Day m8\" }";
    }

}
