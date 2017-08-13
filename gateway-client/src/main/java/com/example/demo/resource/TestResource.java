package com.example.demo.resource;

import com.example.demo.StockWriter;
import com.example.demo.dto.StockDto;
import com.example.demo.model.Stock;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    @Autowired
    private StockWriter stockWriter;


    @HystrixCommand(fallbackMethod = "listsfallback")
    @GetMapping
    public Collection<StockDto> lists() {

        ResponseEntity<Resources<Stock>> exchange = restTemplate.exchange("http://stock-server/stocks", HttpMethod.GET
                , null, new ParameterizedTypeReference<Resources<Stock>>() {
                });

        Collection<Stock> content = exchange.getBody().getContent();
        Collection<StockDto> stockDtos = new ArrayList<>();
        content.forEach(stock -> {
            stockDtos.add(new StockDto(stock.getName()));
        });
        return stockDtos;
    }

    public Collection<StockDto> listsfallback(){
        return Collections.emptyList();
    }

    @PostMapping
    public ResponseEntity write(@RequestBody Stock stock){
        stockWriter.writeStock(stock.getName());
        return ResponseEntity.ok().build();
    }
}
