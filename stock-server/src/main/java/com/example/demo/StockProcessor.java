package com.example.demo;


import com.example.demo.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * Created by amarendrakumar on 13/08/17.
 */
@MessageEndpoint
public class StockProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(StockProcessor.class);

    @Autowired
    private StockRepository stockRepository;

    @ServiceActivator(inputChannel = "input")
    public void savePerson(String name){
        Stock stock = new Stock(name);
        this.stockRepository.save(stock);
        LOGGER.info("New Stock saved -> "+ stock);
    }
}
