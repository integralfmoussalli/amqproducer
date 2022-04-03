package com.demo.amqproducer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SampleResponse {

    String response;

    public SampleResponse(String response) {
        this.response = response;
    }

    public SampleResponse() {
    }




}
