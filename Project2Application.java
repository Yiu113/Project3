package com.example.project2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project2Application {
    @Bean
    public CommandLineRunner databaseSeeder(TenantRepository repository){
        return (args) -> {
            repository.save(new Tenant("Joe", "Joe@gmail.com", "717-123-4567", "03/17/1999", "03/18/2024", 1));
            repository.save(new Tenant("Steve", "Steve@gmail.com", "717-234-5678", "04/18/2000", "05/17/2025", 2));
            repository.save(new Tenant("Tim", "Tim@gmail.com", "717-345-6789", "05/19/2001", "05,20/2026", 3));
            repository.save(new Tenant("Jane", "Jane@gmail.com", "717-456-7890", "06/20/2002", "06/21/2027", 4));
            repository.save(new Tenant("Lisa", "Lisa@gmail.com", "717-123-0987", "07/21/2003", "07/22/2028", 5));
        };
    }
    public CommandLineRunner requestSeeder(RequestRepository repository2){
        return (args) -> {
            repository2.save(new MaintReq(1, "Kitchen", "Sink broke", "12/6/2023", "sink.jpg", "ongoing"));
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Project2Application.class, args);
    }

}
