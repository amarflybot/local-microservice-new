package com.example.demo.resource;

import com.example.demo.yahoo.YahooStockDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amarendrakumar on 13/08/17.
 */
@RestController
@RequestMapping("/yahoo")
public class YahooStockResource {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackStock")
    @GetMapping("/{code}")
    public YahooStockDTO getYahooStockByCode(@PathVariable("code") String code) {

        Map<String, String> params = new HashMap<>();
        params.put("code",code);
        ResponseEntity<YahooStockDTO> exchange = restTemplate.exchange("http://yahoo-stock-server/yahoo/byCode/{code}"
                , HttpMethod.GET
                , null, new ParameterizedTypeReference<YahooStockDTO>() {
                }, params);

        YahooStockDTO content = exchange.getBody();

        return content;
    }

    public YahooStockDTO fallbackStock(String code){
        return null;
    }
}
