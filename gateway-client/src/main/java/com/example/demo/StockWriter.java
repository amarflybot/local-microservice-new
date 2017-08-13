package com.example.demo;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Created by amarendrakumar on 13/08/17.
 */
@MessagingGateway
public interface StockWriter {

    @Gateway(requestChannel = "output")
    void writeStock(String name);
}
