package com.example.demo.resource;

import com.example.demo.model.Stock;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by amarendrakumar on 13/08/17.
 */
@RestController
@RequestMapping("/stocks")
public class TestResource {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "listsfallback")
    @GetMapping
    public Collection<Stock> lists() {

        ResponseEntity<Resources<Stock>> exchange = restTemplate.exchange("http://stock-server/stocks", HttpMethod.GET
                , null, new ParameterizedTypeReference<Resources<Stock>>() {
                });

        return exchange.getBody().getContent();
    }

    public Collection<Stock> listsfallback(){
        return Collections.emptyList();
    }
}
