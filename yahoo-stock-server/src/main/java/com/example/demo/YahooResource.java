package com.example.demo;

import org.springframework.web.bind.annotation.*;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.Map;

/**
 * Created by amarendrakumar on 13/08/17.
 */
@RestController
@RequestMapping("/yahoo")
public class YahooResource {

    //INTC
    @GetMapping("/byCode/{code}")
    public Stock getStockByCode(@PathVariable("code") String code) throws IOException {
        return YahooFinance.get(code);
    }

    //TSLA
    @GetMapping("/byCodeWithHistory/{code}")
    public Stock getStockByCodeWithHistory(@PathVariable("code") String code) throws IOException {
        return YahooFinance.get(code, true);
    }

    //"INTC", "BABA", "TSLA", "AIR.PA", "GOOG"
    @PostMapping("/byCodes")
    public Map<String, Stock> getStocksByCodes(@RequestBody String... code) throws IOException {
        return YahooFinance.get(code);
    }
}
