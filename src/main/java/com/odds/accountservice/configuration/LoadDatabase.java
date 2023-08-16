package com.odds.accountservice.configuration;

import com.odds.accountservice.model.Account;
import com.odds.accountservice.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    public CommandLineRunner initDatabase(AccountRepository repository) {

        return args -> {
          log.info("Loading Account: " + repository.save(new Account("Mohamed", "Fofana", "kanfory.development@gmail.com", "000-000-0000", "000000000")));
          log.info("Loading Account: " + repository.save(new Account("Mimi", "Ukpong", "ukpongmimi@gmail.com", "000-000-0000", "000000000")));
          log.info("Loading Account: " + repository.save(new Account("Mira", "Fofana", "fofana.mira@gmail.com", "000-000-0000", "000000000")));
          log.info("Loading Account: " + repository.save(new Account("Kadija", "Fofana", "kadi.fof@gmail.com", "000-000-0000", "000000000")));
        };
    }
}
