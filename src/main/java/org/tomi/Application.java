package org.tomi;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tomi.dictionary.Dictionary;
import org.tomi.dictionary.DictionaryRepository;
import org.tomi.user.User;
import org.tomi.user.UserRepository;

@SpringBootApplication
public class Application {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private DictionaryRepository dictionaryRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  private void postConstruct() {
    userRepository.save(new User("tomi00"));
    userRepository.save(new User("tedyar"));
    userRepository.save(new User("johnnn"));
    userRepository.save(new User("johnnn2"));

    dictionaryRepository.save(new Dictionary("cannabis"));
    dictionaryRepository.save(new Dictionary("abuse"));
    dictionaryRepository.save(new Dictionary("crack"));
    dictionaryRepository.save(new Dictionary("damn"));
    dictionaryRepository.save(new Dictionary("drunk"));
    dictionaryRepository.save(new Dictionary("grass"));
  }
}
