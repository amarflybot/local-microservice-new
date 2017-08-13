package com.example.demo;

import com.example.demo.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by amarendrakumar on 13/08/17.
 */
@RepositoryRestResource
public interface StockRepository extends JpaRepository<Stock, Long> {
}
