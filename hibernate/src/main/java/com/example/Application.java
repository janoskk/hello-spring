package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class Application {

    //private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Component
    public class MyRunner implements CommandLineRunner {

        private UserRepository repository;

        @Autowired
        MyRunner(UserRepository repository) {
            this.repository = repository;
        }

        @Override
        public void run(String... args) throws Exception {
            System.out.println("Hello, World!");
            repository.save(new User("John", "Smith"));
            try {
                List<User> userList = repository.findByLastName("Smith");
                System.out.println("User name: " + userList.get(0).getFirstName());
            } catch(Exception e) {
                System.err.println("Cannot read user: " + e.getMessage());
            }
        }
    }
}
