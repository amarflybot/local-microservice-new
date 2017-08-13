package com.example.demo;

import com.example.demo.model.Stock;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(Sink.class)
public class StockServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockServerApplication.class, args);
	}

	@Bean
    public CommandLineRunner commandLineRunner(final StockRepository stockRepository){
	    return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                Arrays.asList("asdasd","asdasqwe","dturtuy").forEach(
                        name-> {
                            stockRepository.save(new Stock(name));
                        });
                stockRepository.findAll().forEach(stock -> {
                    System.out.println(stock);
                });
            }
        };
    }
}
