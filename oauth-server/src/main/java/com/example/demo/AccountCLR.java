package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * Created by amarendrakumar on 14/08/17.
 */
@Component
class AccountCLR implements CommandLineRunner {

	private final AccountRepository accountRepository;

	@Autowired
	public AccountCLR(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		Stream.of("amar,pass", "prateek,pass123", "deepesh,cloud123")
				.map(x -> x.split(","))
				.forEach(tpl -> accountRepository.save(new Account(tpl[0], tpl[1], true)));
	}
}
