package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by amarendrakumar on 14/08/17.
 */
interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByUsername(String username);
}
