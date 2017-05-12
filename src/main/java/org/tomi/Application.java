package org.tomi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.tomi.dictionary.Dictionary;
import org.tomi.dictionary.DictionaryRepository;
import org.tomi.user.User;
import org.tomi.user.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Autowired
    UserRepository userRepository;
    @Autowired
    DictionaryRepository dictionaryRepository;

    @PostConstruct
    void checkitOut() {
        userRepository.save(new User("tomi00"));
        userRepository.save(new User("tedyar"));
        userRepository.save(new User("john"));
        userRepository.save(new User("john2"));

        dictionaryRepository.save(new Dictionary("cannabis"));
        dictionaryRepository.save(new Dictionary("abuse"));
        dictionaryRepository.save(new Dictionary("crack"));
        dictionaryRepository.save(new Dictionary("damn"));
        dictionaryRepository.save(new Dictionary("drunk"));
        dictionaryRepository.save(new Dictionary("grass"));
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
}
