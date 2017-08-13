package com.example.demo;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by amarendrakumar on 13/08/17.
 */

public interface StockChannels {

    @Output
    MessageChannel output();

}
