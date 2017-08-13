package com.example.demo.resource;

import com.example.demo.dto.StockDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;

import java.util.*;

/**
 * Created by amarendrakumar on 13/08/17.
 */
@RestController
@RequestMapping("/yahoo")
public class YahooStockResource {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "listsfallbackStock")
    @GetMapping("/{code}")
    public Collection<StockDto> lists(@PathVariable("code") String code) {

        Map<String, String> params = new HashMap<>();
        params.put("code",code);
        ResponseEntity<Resources<Stock>> exchange = restTemplate.exchange("http://yahoo-stock-server/yahoo/byCode/{code}"
                , HttpMethod.GET
                , null, new ParameterizedTypeReference<Resources<Stock>>() {
                }, params);

        Collection<Stock> content = exchange.getBody().getContent();
        List<StockDto> stockDtos = new ArrayList<>();
        content.forEach(stock -> {
            stockDtos.add(new StockDto(stock.getName()));
        });
        return stockDtos;
    }

    public Collection<StockDto> listsfallbackStock(String code){
        List<StockDto> stockDtos = new ArrayList<>();
        stockDtos.add(new StockDto("DefaultNull"));
        return stockDtos;
    }
}
