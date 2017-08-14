package com.example.demo.feignclient;


import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yahoofinance.Stock;

/**
 * Created by amarendrakumar on 14/08/17.
 */
//@FeignClient("yahoo-stock-server")
public interface YahooStockReader {

    @GetMapping("/yahoo/byCode/{code}")
    Resources<Stock> getStockByCode(@PathVariable("code") String code);
}
