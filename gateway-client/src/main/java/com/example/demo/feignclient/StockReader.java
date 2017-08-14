package com.example.demo.feignclient;

import com.example.demo.model.Stock;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by amarendrakumar on 14/08/17.
 */
//@FeignClient("stock-server")
public interface StockReader {

    @GetMapping("/stocks")
    List<Stock> getAllStocks();
}
